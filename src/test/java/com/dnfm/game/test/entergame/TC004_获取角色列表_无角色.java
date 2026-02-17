package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.character.model.REQ_GET_CHARACTER_LIST;
import com.dnfm.game.character.model.RES_GET_CHARACTER_LIST;
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
 * TC004_获取角色列表_无角色
 *
 * 测试目的: 验证没有角色的玩家获取角色列表时返回空列表，并且不报错
 * 测试类型: 边界测试
 * 优先级: 高
 */
public class TC004_获取角色列表_无角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_004";
    private static final String TEST_DEVICE_ID = "device_test_004";
    private static final long TEST_ACCOUNT_ID = 10004;

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

        // 1.3 准备测试账号
        prepareTestData();

        System.out.println("========== 测试环境准备完成 ==========");
    }

    @Test
    public void testGetCharacterListWithNoCharacter() throws Exception {
        System.out.println("========== 开始测试: TC004_获取角色列表_无角色 ==========");

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

        // 步骤6: 验证角色列表为空
        System.out.println("步骤6: 验证角色列表为空");
        assertEquals("错误码不为0", 0, res.getError());
        assertNotNull("角色列表为null（应该返回空数组）", res.getCharacters());
        assertEquals("角色数量不为0", 0, res.getCharacters().size());
        System.out.println("角色列表验证通过");
        System.out.println("  - 错误码: " + res.getError());
        System.out.println("  - 角色列表: 非空");
        System.out.println("  - 角色数量: " + res.getCharacters().size());

        // 步骤7: 验证数据库无角色记录
        System.out.println("步骤7: 验证数据库无角色记录");
        verifyNoCharacterRecords();

        // 步骤8: 验证响应数据结构
        System.out.println("步骤8: 验证响应数据结构");
        verifyResponseStructure(res);

        // 步骤9: 验证日志记录
        System.out.println("步骤9: 验证日志记录");
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
     * 验证无角色记录
     */
    private void verifyNoCharacterRecords() throws Exception {
        String sql = "SELECT COUNT(*) FROM t_character WHERE accountID = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, TEST_ACCOUNT_ID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int count = rs.getInt(1);
        assertEquals("数据库角色记录不为0（应该为0）", 0, count);
        System.out.println("数据库角色记录验证通过: 0 条");

        rs.close();
        stmt.close();
    }

    /**
     * 验证响应数据结构
     */
    private void verifyResponseStructure(RES_GET_CHARACTER_LIST res) {
        assertNotNull("响应对象为null", res);
        assertNotNull("characters字段为null（应该返回空数组）", res.getCharacters());
        assertTrue("characters数组不为空（应该为空数组）", res.getCharacters().isEmpty());
        System.out.println("响应数据结构验证通过");
        System.out.println("  - 响应对象: 不为null");
        System.out.println("  - characters字段: 不为null");
        System.out.println("  - characters数组: 空");
    }

    /**
     * 验证日志记录
     */
    private void verifyLogRecord() throws Exception {
        // 这里简化处理，实际应该检查日志文件
        System.out.println("日志记录验证: 需要检查日志文件中是否记录了获取角色列表事件");
        System.out.println("  - 搜索关键字: " + TEST_OPENID);
        System.out.println("  - 日志级别: INFO");
        System.out.println("  - 日志内容: 应该包含角色数量为0的信息");
        System.out.println("  - 错误日志: 不应该存在");

        // TODO: 实际实现应该读取日志文件并验证
        // String logContent = readLogFile("logs/game.log");
        // assertTrue("日志中未记录获取角色列表事件", logContent.contains(TEST_OPENID));
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
        PreparedStatement stmt = dbConnection.prepareStatement(insertAccountSql);
        stmt.setString(1, TEST_OPENID);
        stmt.setLong(2, TEST_ACCOUNT_ID);
        stmt.executeUpdate();
        stmt.close();
        System.out.println("创建测试账号: " + TEST_ACCOUNT_ID);

        // 确保没有角色
        String deleteCharacterSql = "DELETE FROM t_character WHERE accountID = ?";
        PreparedStatement stmt2 = dbConnection.prepareStatement(deleteCharacterSql);
        stmt2.setLong(1, TEST_ACCOUNT_ID);
        int characterCount = stmt2.executeUpdate();
        stmt2.close();
        System.out.println("删除角色记录: " + characterCount + " 条（确保没有角色）");
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

        // 步骤10: 清理测试数据
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
