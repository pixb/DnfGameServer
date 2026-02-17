package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.character.model.REQ_GET_CHARACTER_LIST;
import com.dnfm.game.character.model.RES_GET_CHARACTER_LIST;
import com.dnfm.game.character.model.CharacterInfo;
import com.dnfm.game.common.util.DBUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

/**
 * TC003_获取角色列表
 *
 * 测试目的: 验证已登录玩家能获取到正确的角色列表，包括角色的所有基本信息
 * 测试类型: 功能测试
 * 优先级: 高
 */
public class TC003_获取角色列表 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_003";
    private static final String TEST_DEVICE_ID = "device_test_003";
    private static final long TEST_ACCOUNT_ID = 10003;
    private static final long CHARACTER_GUID_1 = 1001;
    private static final long CHARACTER_GUID_2 = 1002;

    private Socket socket;
    private Connection dbConnection;
    private String authKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== 开始准备测试环境 ==========");

        // 步骤1: 准备测试环境
        // 1.1 检查Java服务端是否启动
        Process process = Runtime.getRuntime().exec("ps aux | grep java");
        process.waitFor();
        assertTrue("Java服务端未启动", process.exitValue() == 0);

        // 1.2 检查数据库连接
        dbConnection = DBUtil.getConnection();
        assertNotNull("数据库连接失败", dbConnection);

        // 1.3 准备测试账号和角色
        prepareTestData();

        System.out.println("========== 测试环境准备完成 ==========");
    }

    @Test
    public void testGetCharacterList() throws Exception {
        System.out.println("========== 开始测试: TC003_获取角色列表 ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 玩家登录获取authKey
        System.out.println("步骤2: 玩家登录获取authKey");
        loginAndGetAuthKey();

        // 步骤3: 构造获取角色列表请求
        System.out.println("步骤3: 构造获取角色列表请求");
        REQ_GET_CHARACTER_LIST req = new REQ_GET_CHARACTER_LIST();
        req.setAuthKey(authKey);
        req.setAccountID(TEST_ACCOUNT_ID);

        Codec<REQ_GET_CHARACTER_LIST> reqCodec = ProtobufProxy.create(REQ_GET_CHARACTER_LIST.class);
        byte[] reqBytes = reqCodec.encode(req);

        assertNotNull("请求序列化失败", reqBytes);
        assertTrue("请求数据为空", reqBytes.length > 0);
        System.out.println("请求序列化成功，长度: " + reqBytes.length);

        // 步骤4: 发送获取角色列表请求
        System.out.println("步骤4: 发送获取角色列表请求");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(15000); // 15秒超时

        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("获取角色列表请求发送成功");

        // 步骤5: 接收并解析响应
        System.out.println("步骤5: 接收并解析响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);

        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("响应接收成功，长度: " + responseBytes.length);

        Codec<RES_GET_CHARACTER_LIST> resCodec = ProtobufProxy.create(RES_GET_CHARACTER_LIST.class);
        RES_GET_CHARACTER_LIST res = resCodec.decode(responseBytes);

        assertNotNull("响应反序列化失败", res);
        System.out.println("响应反序列化成功");

        // 步骤6: 验证角色列表基本信息
        System.out.println("步骤6: 验证角色列表基本信息");
        assertEquals("错误码不为0", 0, res.getError());
        assertNotNull("角色列表为空", res.getCharacters());
        assertEquals("角色数量不正确", 2, res.getCharacters().size());
        System.out.println("角色列表基本信息验证通过");
        System.out.println("  - 错误码: " + res.getError());
        System.out.println("  - 角色数量: " + res.getCharacters().size());

        // 步骤7: 验证角色1信息
        System.out.println("步骤7: 验证角色1信息");
        CharacterInfo char1 = res.getCharacters().get(0);
        verifyCharacter1(char1);

        // 步骤8: 验证角色2信息
        System.out.println("步骤8: 验证角色2信息");
        CharacterInfo char2 = res.getCharacters().get(1);
        verifyCharacter2(char2);

        // 步骤9: 验证角色装备信息
        System.out.println("步骤9: 验证角色装备信息");
        verifyEquipments(char1, char2);

        // 步骤10: 验证角色背包信息
        System.out.println("步骤10: 验证角色背包信息");
        verifyBags(char1, char2);

        // 步骤11: 验证数据库记录
        System.out.println("步骤11: 验证数据库记录");
        verifyDatabaseRecords();

        long endTime = System.currentTimeMillis();
        System.out.println("========== 测试执行完成，耗时: " + (endTime - startTime) + "ms ==========");
    }

    /**
     * 登录并获取authKey
     */
    private void loginAndGetAuthKey() throws Exception {
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid(TEST_OPENID);
        req.setDeviceId(TEST_DEVICE_ID);
        req.setDeviceType(1); // Android
        req.setClientVersion("1.0.0");

        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);

        Socket loginSocket = new Socket(SERVER_HOST, SERVER_PORT);
        loginSocket.setSoTimeout(15000);

        OutputStream out = loginSocket.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = loginSocket.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);

        assertEquals("登录失败", 0, res.getError());
        assertNotNull("authKey为空", res.getAuthKey());

        authKey = res.getAuthKey();
        System.out.println("登录成功，authKey: " + authKey);

        loginSocket.close();
    }

    /**
     * 验证角色1信息
     */
    private void verifyCharacter1(CharacterInfo char1) {
        assertNotNull("角色1为空", char1);
        assertEquals("角色1charguid不正确", CHARACTER_GUID_1, char1.getCharguid());
        assertEquals("角色1name不正确", "TestPlayer1", char1.getName());
        assertEquals("角色1job不正确", 1, char1.getJob());
        assertEquals("角色1level不正确", 10, char1.getLevel());
        assertEquals("角色1status不正确", 1, char1.getStatus());
        assertNotNull("角色1createTime为空", char1.getCreateTime());
        assertNotNull("角色1lastLoginTime为空", char1.getLastLoginTime());

        System.out.println("角色1信息验证通过");
        System.out.println("  - charguid: " + char1.getCharguid());
        System.out.println("  - name: " + char1.getName());
        System.out.println("  - job: " + char1.getJob());
        System.out.println("  - level: " + char1.getLevel());
        System.out.println("  - status: " + char1.getStatus());
    }

    /**
     * 验证角色2信息
     */
    private void verifyCharacter2(CharacterInfo char2) {
        assertNotNull("角色2为空", char2);
        assertEquals("角色2charguid不正确", CHARACTER_GUID_2, char2.getCharguid());
        assertEquals("角色2name不正确", "TestPlayer2", char2.getName());
        assertEquals("角色2job不正确", 2, char2.getJob());
        assertEquals("角色2level不正确", 20, char2.getLevel());
        assertEquals("角色2status不正确", 1, char2.getStatus());
        assertNotNull("角色2createTime为空", char2.getCreateTime());
        assertNotNull("角色2lastLoginTime为空", char2.getLastLoginTime());

        System.out.println("角色2信息验证通过");
        System.out.println("  - charguid: " + char2.getCharguid());
        System.out.println("  - name: " + char2.getName());
        System.out.println("  - job: " + char2.getJob());
        System.out.println("  - level: " + char2.getLevel());
        System.out.println("  - status: " + char2.getStatus());
    }

    /**
     * 验证角色装备信息
     */
    private void verifyEquipments(CharacterInfo char1, CharacterInfo char2) {
        assertNotNull("角色1装备为空", char1.getEquipments());
        assertTrue("角色1装备数量不足", char1.getEquipments().size() >= 1);
        System.out.println("角色1装备验证通过，装备数量: " + char1.getEquipments().size());

        assertNotNull("角色2装备为空", char2.getEquipments());
        assertTrue("角色2装备数量不足", char2.getEquipments().size() >= 1);
        System.out.println("角色2装备验证通过，装备数量: " + char2.getEquipments().size());
    }

    /**
     * 验证角色背包信息
     */
    private void verifyBags(CharacterInfo char1, CharacterInfo char2) {
        assertNotNull("角色1背包为空", char1.getBag());
        assertNotNull("角色1背包物品为空", char1.getBag().getItems());
        assertTrue("角色1背包物品数量不足", char1.getBag().getItems().size() >= 5);
        System.out.println("角色1背包验证通过，物品数量: " + char1.getBag().getItems().size());

        assertNotNull("角色2背包为空", char2.getBag());
        assertNotNull("角色2背包物品为空", char2.getBag().getItems());
        assertTrue("角色2背包物品数量不足", char2.getBag().getItems().size() >= 5);
        System.out.println("角色2背包验证通过，物品数量: " + char2.getBag().getItems().size());
    }

    /**
     * 验证数据库记录
     */
    private void verifyDatabaseRecords() throws Exception {
        String sql = "SELECT * FROM t_character WHERE accountID = ? ORDER BY charguid";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, TEST_ACCOUNT_ID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("数据库角色记录不存在", rs.next());

        // 验证角色1
        assertEquals("数据库角色1charguid不正确", CHARACTER_GUID_1, rs.getLong("charguid"));
        assertEquals("数据库角色1name不正确", "TestPlayer1", rs.getString("name"));
        assertEquals("数据库角色1job不正确", 1, rs.getInt("job"));
        assertEquals("数据库角色1level不正确", 10, rs.getInt("level"));

        // 验证角色2
        assertTrue("数据库角色2记录不存在", rs.next());
        assertEquals("数据库角色2charguid不正确", CHARACTER_GUID_2, rs.getLong("charguid"));
        assertEquals("数据库角色2name不正确", "TestPlayer2", rs.getString("name"));
        assertEquals("数据库角色2job不正确", 2, rs.getInt("job"));
        assertEquals("数据库角色2level不正确", 20, rs.getInt("level"));

        System.out.println("数据库记录验证通过");

        rs.close();
        stmt.close();
    }

    /**
     * 准备测试数据
     */
    private void prepareTestData() throws Exception {
        System.out.println("准备测试数据");

        // 删除旧数据
        cleanupTestData();

        // 创建测试账号
        String insertAccountSql = "INSERT INTO t_account (openid, accountID, createTime, status) VALUES (?, ?, NOW(), 1)";
        PreparedStatement stmt1 = dbConnection.prepareStatement(insertAccountSql);
        stmt1.setString(1, TEST_OPENID);
        stmt1.setLong(2, TEST_ACCOUNT_ID);
        stmt1.executeUpdate();
        stmt1.close();
        System.out.println("创建测试账号: " + TEST_ACCOUNT_ID);

        // 创建测试角色
        String insertCharacterSql = "INSERT INTO t_character (charguid, accountID, name, job, level, createTime, status) VALUES (?, ?, ?, ?, ?, NOW(), 1)";
        PreparedStatement stmt2 = dbConnection.prepareStatement(insertCharacterSql);

        // 角色1
        stmt2.setLong(1, CHARACTER_GUID_1);
        stmt2.setLong(2, TEST_ACCOUNT_ID);
        stmt2.setString(3, "TestPlayer1");
        stmt2.setInt(4, 1); // 战士
        stmt2.setInt(5, 10);
        stmt2.executeUpdate();

        // 角色2
        stmt2.setLong(1, CHARACTER_GUID_2);
        stmt2.setLong(2, TEST_ACCOUNT_ID);
        stmt2.setString(3, "TestPlayer2");
        stmt2.setInt(4, 2); // 法师
        stmt2.setInt(5, 20);
        stmt2.executeUpdate();

        stmt2.close();
        System.out.println("创建测试角色: " + CHARACTER_GUID_1 + ", " + CHARACTER_GUID_2);

        // TODO: 创建角色装备和背包数据
        // 这里简化处理，实际应该创建完整的装备和背包数据
        System.out.println("注意: 装备和背包数据需要手动创建或使用脚本创建");
    }

    /**
     * 清理测试数据
     */
    private void cleanupTestData() throws Exception {
        System.out.println("清理测试数据");

        // 删除角色背包
        String deleteBagSql = "DELETE FROM t_character_bag WHERE charguid IN (?, ?)";
        PreparedStatement stmt1 = dbConnection.prepareStatement(deleteBagSql);
        stmt1.setLong(1, CHARACTER_GUID_1);
        stmt1.setLong(2, CHARACTER_GUID_2);
        stmt1.executeUpdate();
        stmt1.close();

        // 删除角色装备
        String deleteEquipSql = "DELETE FROM t_character_equipment WHERE charguid IN (?, ?)";
        PreparedStatement stmt2 = dbConnection.prepareStatement(deleteEquipSql);
        stmt2.setLong(1, CHARACTER_GUID_1);
        stmt2.setLong(2, CHARACTER_GUID_2);
        stmt2.executeUpdate();
        stmt2.close();

        // 删除角色
        String deleteCharacterSql = "DELETE FROM t_character WHERE accountID = ?";
        PreparedStatement stmt3 = dbConnection.prepareStatement(deleteCharacterSql);
        stmt3.setLong(1, TEST_ACCOUNT_ID);
        int characterCount = stmt3.executeUpdate();
        stmt3.close();
        System.out.println("删除角色记录: " + characterCount + " 条");

        // 删除账号
        String deleteAccountSql = "DELETE FROM t_account WHERE accountID = ?";
        PreparedStatement stmt4 = dbConnection.prepareStatement(deleteAccountSql);
        stmt4.setLong(1, TEST_ACCOUNT_ID);
        int accountCount = stmt4.executeUpdate();
        stmt4.close();
        System.out.println("删除账号记录: " + accountCount + " 条");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("========== 开始清理测试数据 ==========");

        // 步骤12: 清理测试数据
        if (dbConnection != null) {
            cleanupTestData();
            dbConnection.close();
        }

        if (socket != null) {
            socket.close();
        }

        System.out.println("========== 测试数据清理完成 ==========");
    }

    /**
     * 完整读取输入流
     */
    private byte[] readFully(InputStream in) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }
}
