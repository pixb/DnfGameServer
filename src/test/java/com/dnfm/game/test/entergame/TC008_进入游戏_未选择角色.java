package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.entergame.model.REQ_ENTER_GAME;
import com.dnfm.game.entergame.model.RES_ENTER_GAME;
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
 * TC008_进入游戏_未选择角色
 *
 * 测试目的: 验证玩家在未选择角色的情况下尝试进入游戏时返回正确的错误码
 * 测试类型: 异常测试
 * 优先级: 高
 */
public class TC008_进入游戏_未选择角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_008";
    private static final String TEST_DEVICE_ID = "device_test_008";
    private static final long TEST_ACCOUNT_ID = 10008;
    private static final long CHARACTER_GUID = 1001;
    private static final int ERROR_CODE_CHARACTER_NOT_SELECTED = 2002; // 角色未选择错误码

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
    public void testEnterGameWithoutSelectingCharacter() throws Exception {
        System.out.println("========== 开始测试: TC008_进入游戏_未选择角色 ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 玩家登录获取authKey
        System.out.println("步骤2: 玩家登录获取authKey");
        loginAndGetAuthKey();

        // 步骤3: 构造进入游戏请求（未选择角色）
        System.out.println("步骤3: 构造进入游戏请求（未选择角色）");
        REQ_ENTER_GAME req = new REQ_ENTER_GAME();
        req.setAuthKey(authKey);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID); // 角色未选择

        Codec<REQ_ENTER_GAME> reqCodec = ProtobufProxy.create(REQ_ENTER_GAME.class);
        byte[] reqBytes = reqCodec.encode(req);

        assertNotNull("请求序列化失败", reqBytes);
        assertTrue("请求数据为空", reqBytes.length > 0);
        System.out.println("请求序列化成功，长度: " + reqBytes.length);

        // 步骤4: 发送进入游戏请求
        System.out.println("步骤4: 发送进入游戏请求");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(15000); // 15秒超时

        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("进入游戏请求发送成功");

        // 步骤5: 接收并解析响应
        System.out.println("步骤5: 接收并解析响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);

        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("响应接收成功，长度: " + responseBytes.length);

        Codec<RES_ENTER_GAME> resCodec = ProtobufProxy.create(RES_ENTER_GAME.class);
        RES_ENTER_GAME res = resCodec.decode(responseBytes);

        assertNotNull("响应反序列化失败", res);
        System.out.println("响应反序列化成功");

        // 步骤6: 验证进入游戏失败
        System.out.println("步骤6: 验证进入游戏失败");
        assertNotEquals("错误码为0（应该非0）", 0, res.getError());
        assertEquals("错误码不正确", ERROR_CODE_CHARACTER_NOT_SELECTED, res.getError());
        assertNull("角色信息不为null（应该为null）", res.getCharacter());
        assertNull("游戏数据不为null（应该为null）", res.getGameData());
        System.out.println("进入游戏失败验证通过");
        System.out.println("  - 错误码: " + res.getError() + " (角色未选择)");
        System.out.println("  - 角色信息: null");
        System.out.println("  - 游戏数据: null");

        // 步骤7: 验证角色状态未改变
        System.out.println("步骤7: 验证角色状态未改变");
        verifyCharacterStatusUnchanged();

        // 步骤8: 验证日志记录
        System.out.println("步骤8: 验证日志记录");
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
     * 验证角色状态未改变
     */
    private void verifyCharacterStatusUnchanged() throws Exception {
        String sql = "SELECT status, lastLoginTime FROM t_character WHERE charguid = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, CHARACTER_GUID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int currentStatus = rs.getInt("status");
        Timestamp currentLastLoginTime = rs.getTimestamp("lastLoginTime");

        assertEquals("角色状态被修改（应该为0）", 0, currentStatus);
        assertEquals("最后登录时间被修改（应该保持原值）", originalLastLoginTime, currentLastLoginTime);

        System.out.println("角色状态验证通过");
        System.out.println("  - 原始状态: 0 (未选择)");
        System.out.println("  - 当前状态: " + currentStatus + " (未选择)");
        System.out.println("  - 原始最后登录时间: " + originalLastLoginTime);
        System.out.println("  - 当前最后登录时间: " + currentLastLoginTime);

        rs.close();
        stmt.close();
    }

    /**
     * 验证日志记录
     */
    private void verifyLogRecord() throws Exception {
        // 这里简化处理，实际应该检查日志文件
        System.out.println("日志记录验证: 需要检查日志文件中是否记录了进入游戏失败事件");
        System.out.println("  - 搜索关键字: " + TEST_OPENID);
        System.out.println("  - 日志级别: ERROR或WARN");
        System.out.println("  - 日志内容: 应该包含进入游戏失败信息");
        System.out.println("  - 错误原因: 角色未选择");

        // TODO: 实际实现应该读取日志文件并验证
        // String logContent = readLogFile("logs/game.log");
        // assertTrue("日志中未记录进入游戏失败事件", logContent.contains(TEST_OPENID));
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

        // 创建测试角色（未选择状态）
        String insertCharacterSql = "INSERT INTO t_character (charguid, accountID, name, job, level, createTime, lastLoginTime, status) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), 0)";
        PreparedStatement stmt2 = dbConnection.prepareStatement(insertCharacterSql);
        stmt2.setLong(1, CHARACTER_GUID);
        stmt2.setLong(2, TEST_ACCOUNT_ID);
        stmt2.setString(3, "TestPlayer1");
        stmt2.setInt(4, 1); // 战士
        stmt2.setInt(5, 10);
        stmt2.executeUpdate();
        stmt2.close();
        System.out.println("创建测试角色: " + CHARACTER_GUID + " (未选择状态)");

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

        // 步骤9: 清理测试数据
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
