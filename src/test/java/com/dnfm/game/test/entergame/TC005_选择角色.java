package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.character.model.REQ_SELECT_CHARACTER;
import com.dnfm.game.character.model.RES_SELECT_CHARACTER;
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
import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * TC005_选择角色
 *
 * 测试目的: 验证玩家能成功选择角色，并更新角色的最后登录时间
 * 测试类型: 功能测试
 * 优先级: 高
 */
public class TC005_选择角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_005";
    private static final String TEST_DEVICE_ID = "device_test_005";
    private static final long TEST_ACCOUNT_ID = 10005;
    private static final long CHARACTER_GUID = 1001;
    private static final String CHARACTER_NAME = "TestPlayer1";

    private Socket socket;
    private Connection dbConnection;
    private String authKey;
    private Timestamp originalLastLoginTime;

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
    public void testSelectCharacter() throws Exception {
        System.out.println("========== 开始测试: TC005_选择角色 ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 玩家登录获取authKey
        System.out.println("步骤2: 玩家登录获取authKey");
        loginAndGetAuthKey();

        // 步骤3: 构造选择角色请求
        System.out.println("步骤3: 构造选择角色请求");
        REQ_SELECT_CHARACTER req = new REQ_SELECT_CHARACTER();
        req.setAuthKey(authKey);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_SELECT_CHARACTER> reqCodec = ProtobufProxy.create(REQ_SELECT_CHARACTER.class);
        byte[] reqBytes = reqCodec.encode(req);

        assertNotNull("请求序列化失败", reqBytes);
        assertTrue("请求数据为空", reqBytes.length > 0);
        System.out.println("请求序列化成功，长度: " + reqBytes.length);

        // 步骤4: 发送选择角色请求
        System.out.println("步骤4: 发送选择角色请求");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(15000); // 15秒超时

        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("选择角色请求发送成功");

        // 步骤5: 接收并解析响应
        System.out.println("步骤5: 接收并解析响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);

        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("响应接收成功，长度: " + responseBytes.length);

        Codec<RES_SELECT_CHARACTER> resCodec = ProtobufProxy.create(RES_SELECT_CHARACTER.class);
        RES_SELECT_CHARACTER res = resCodec.decode(responseBytes);

        assertNotNull("响应反序列化失败", res);
        System.out.println("响应反序列化成功");

        // 步骤6: 验证选择角色成功
        System.out.println("步骤6: 验证选择角色成功");
        assertEquals("错误码不为0", 0, res.getError());
        assertNotNull("角色信息为空", res.getCharacter());
        assertEquals("角色GUID不正确", CHARACTER_GUID, res.getCharacter().getCharguid());
        assertEquals("角色状态不正确", 1, res.getCharacter().getStatus());
        System.out.println("选择角色验证通过");
        System.out.println("  - 错误码: " + res.getError());
        System.out.println("  - 角色GUID: " + res.getCharacter().getCharguid());
        System.out.println("  - 角色状态: " + res.getCharacter().getStatus());

        // 步骤7: 验证角色基本信息
        System.out.println("步骤7: 验证角色基本信息");
        verifyCharacterBasicInfo(res.getCharacter());

        // 步骤8: 验证最后登录时间更新
        System.out.println("步骤8: 验证最后登录时间更新");
        verifyLastLoginTimeUpdate();

        // 步骤9: 验证角色状态更新
        System.out.println("步骤9: 验证角色状态更新");
        verifyCharacterStatusUpdate();

        // 步骤10: 验证日志记录
        System.out.println("步骤10: 验证日志记录");
        verifyLogRecord();

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
     * 验证角色基本信息
     */
    private void verifyCharacterBasicInfo(CharacterInfo character) {
        assertEquals("角色GUID不正确", CHARACTER_GUID, character.getCharguid());
        assertEquals("角色名称不正确", CHARACTER_NAME, character.getName());
        assertEquals("角色职业不正确", 1, character.getJob());
        assertEquals("角色等级不正确", 10, character.getLevel());
        assertNotNull("角色创建时间为空", character.getCreateTime());
        assertNotNull("角色最后登录时间为空", character.getLastLoginTime());

        System.out.println("角色基本信息验证通过");
        System.out.println("  - charguid: " + character.getCharguid());
        System.out.println("  - name: " + character.getName());
        System.out.println("  - job: " + character.getJob());
        System.out.println("  - level: " + character.getLevel());
        System.out.println("  - createTime: " + character.getCreateTime());
        System.out.println("  - lastLoginTime: " + character.getLastLoginTime());
    }

    /**
     * 验证最后登录时间更新
     */
    private void verifyLastLoginTimeUpdate() throws Exception {
        String sql = "SELECT lastLoginTime FROM t_character WHERE charguid = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, CHARACTER_GUID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        Timestamp newLastLoginTime = rs.getTimestamp("lastLoginTime");
        assertNotNull("最后登录时间为空", newLastLoginTime);

        // 验证最后登录时间在最近1分钟内
        long timeDiff = System.currentTimeMillis() - newLastLoginTime.getTime();
        assertTrue("最后登录时间不在最近1分钟内", timeDiff < 60 * 1000);

        // 验证最后登录时间已更新（与原始时间对比）
        long timeDiffFromOriginal = newLastLoginTime.getTime() - originalLastLoginTime.getTime();
        assertTrue("最后登录时间未更新", timeDiffFromOriginal > 23 * 60 * 60 * 1000); // 至少23小时

        System.out.println("最后登录时间验证通过");
        System.out.println("  - 原始时间: " + originalLastLoginTime);
        System.out.println("  - 更新后时间: " + newLastLoginTime);
        System.out.println("  - 时间差: " + (timeDiffFromOriginal / (60 * 60 * 1000)) + " 小时");

        rs.close();
        stmt.close();
    }

    /**
     * 验证角色状态更新
     */
    private void verifyCharacterStatusUpdate() throws Exception {
        // 验证选中角色状态
        String sql1 = "SELECT status FROM t_character WHERE charguid = ?";
        PreparedStatement stmt1 = dbConnection.prepareStatement(sql1);
        stmt1.setLong(1, CHARACTER_GUID);

        ResultSet rs1 = stmt1.executeQuery();
        assertTrue("查询失败", rs1.next());
        int selectedStatus = rs1.getInt("status");
        assertEquals("选中角色状态不正确", 1, selectedStatus);
        System.out.println("选中角色状态验证通过: " + selectedStatus);

        rs1.close();
        stmt1.close();

        // 验证其他角色状态（如果有的话）
        String sql2 = "SELECT charguid, status FROM t_character WHERE accountID = ? AND charguid != ?";
        PreparedStatement stmt2 = dbConnection.prepareStatement(sql2);
        stmt2.setLong(1, TEST_ACCOUNT_ID);
        stmt2.setLong(2, CHARACTER_GUID);

        ResultSet rs2 = stmt2.executeQuery();
        while (rs2.next()) {
            long otherCharguid = rs2.getLong("charguid");
            int otherStatus = rs2.getInt("status");
            assertEquals("其他角色状态不正确", 0, otherStatus);
            System.out.println("其他角色状态验证通过: charguid=" + otherCharguid + ", status=" + otherStatus);
        }

        rs2.close();
        stmt2.close();
    }

    /**
     * 验证日志记录
     */
    private void verifyLogRecord() throws Exception {
        // 这里简化处理，实际应该检查日志文件
        System.out.println("日志记录验证: 需要检查日志文件中是否记录了选择角色事件");
        System.out.println("  - 搜索关键字: " + TEST_OPENID);
        System.out.println("  - 日志级别: INFO");
        System.out.println("  - 日志内容: 应该包含选择角色信息");
        System.out.println("  - 角色GUID: " + CHARACTER_GUID);
        System.out.println("  - 角色名称: " + CHARACTER_NAME);

        // TODO: 实际实现应该读取日志文件并验证
        // String logContent = readLogFile("logs/game.log");
        // assertTrue("日志中未记录选择角色事件", logContent.contains(TEST_OPENID));
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

        // 创建测试角色，最后登录时间设置为1天前
        String insertCharacterSql = "INSERT INTO t_character (charguid, accountID, name, job, level, createTime, lastLoginTime, status) VALUES (?, ?, ?, ?, ?, NOW(), DATE_SUB(NOW(), INTERVAL 1 DAY), 0)";
        PreparedStatement stmt2 = dbConnection.prepareStatement(insertCharacterSql);
        stmt2.setLong(1, CHARACTER_GUID);
        stmt2.setLong(2, TEST_ACCOUNT_ID);
        stmt2.setString(3, CHARACTER_NAME);
        stmt2.setInt(4, 1); // 战士
        stmt2.setInt(5, 10);
        stmt2.executeUpdate();
        stmt2.close();
        System.out.println("创建测试角色: " + CHARACTER_GUID);

        // 获取原始最后登录时间
        String selectSql = "SELECT lastLoginTime FROM t_character WHERE charguid = ?";
        PreparedStatement stmt3 = dbConnection.prepareStatement(selectSql);
        stmt3.setLong(1, CHARACTER_GUID);
        ResultSet rs = stmt3.executeQuery();
        if (rs.next()) {
            originalLastLoginTime = rs.getTimestamp("lastLoginTime");
            System.out.println("原始最后登录时间: " + originalLastLoginTime);
        }
        rs.close();
        stmt3.close();
    }

    /**
     * 清理测试数据
     */
    private void cleanupTestData() throws Exception {
        System.out.println("清理测试数据");

        // 删除角色
        String deleteCharacterSql = "DELETE FROM t_character WHERE accountID = ?";
        PreparedStatement stmt1 = dbConnection.prepareStatement(deleteCharacterSql);
        stmt1.setLong(1, TEST_ACCOUNT_ID);
        int characterCount = stmt1.executeUpdate();
        stmt1.close();
        System.out.println("删除角色记录: " + characterCount + " 条");

        // 删除账号
        String deleteAccountSql = "DELETE FROM t_account WHERE accountID = ?";
        PreparedStatement stmt2 = dbConnection.prepareStatement(deleteAccountSql);
        stmt2.setLong(1, TEST_ACCOUNT_ID);
        int accountCount = stmt2.executeUpdate();
        stmt2.close();
        System.out.println("删除账号记录: " + accountCount + " 条");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("========== 开始清理测试数据 ==========");

        // 步骤11: 清理测试数据
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
