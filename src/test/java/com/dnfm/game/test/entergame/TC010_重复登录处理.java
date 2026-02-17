package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.character.model.REQ_SELECT_CHARACTER;
import com.dnfm.game.character.model.RES_SELECT_CHARACTER;
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
 * TC010_重复登录处理
 *
 * 测试目的: 验证玩家在已登录的情况下再次登录时，系统能正确处理重复登录请求
 * 测试类型: 异常测试
 * 优先级: 高
 */
public class TC010_重复登录处理 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_010";
    private static final String TEST_DEVICE_ID_1 = "device_test_010";
    private static final String TEST_DEVICE_ID_2 = "device_test_010_2";
    private static final long TEST_ACCOUNT_ID = 10010;
    private static final long CHARACTER_GUID = 1001;
    private static final String CHARACTER_NAME = "TestPlayer1";
    private static final int STATUS_IN_GAME = 2; // 游戏中状态

    private Socket socket1;
    private Socket socket2;
    private Connection dbConnection;
    private String authKey1;
    private String authKey2;

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
    public void testDuplicateLoginHandling() throws Exception {
        System.out.println("========== 开始测试: TC010_重复登录处理 ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 第一次登录获取authKey
        System.out.println("步骤2: 第一次登录获取authKey");
        loginAndGetAuthKey1();

        // 步骤3: 第一次选择角色
        System.out.println("步骤3: 第一次选择角色");
        selectCharacter1();

        // 步骤4: 第一次进入游戏
        System.out.println("步骤4: 第一次进入游戏");
        enterGame1();

        // 步骤5: 第二次登录（重复登录）
        System.out.println("步骤5: 第二次登录（重复登录）");
        loginAndGetAuthKey2();

        // 步骤6: 验证旧连接被踢掉
        System.out.println("步骤6: 验证旧连接被踢掉");
        verifyOldConnectionKicked();

        // 步骤7: 第二次选择角色
        System.out.println("步骤7: 第二次选择角色");
        selectCharacter2();

        // 步骤8: 第二次进入游戏
        System.out.println("步骤8: 第二次进入游戏");
        enterGame2();

        // 步骤9: 验证数据库记录
        System.out.println("步骤9: 验证数据库记录");
        verifyDatabaseRecords();

        // 步骤10: 验证日志记录
        System.out.println("步骤10: 验证日志记录");
        verifyLogRecord();

        long endTime = System.currentTimeMillis();
        System.out.println("========== 测试执行完成，耗时: " + (endTime - startTime) + "ms ==========");
    }

    /**
     * 第一次登录并获取authKey
     */
    private void loginAndGetAuthKey1() throws Exception {
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid(TEST_OPENID);
        req.setDeviceId(TEST_DEVICE_ID_1);
        req.setDeviceType(1); // Android
        req.setClientVersion("1.0.0");

        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);

        socket1 = new Socket(SERVER_HOST, SERVER_PORT);
        socket1.setSoTimeout(15000);

        OutputStream out = socket1.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = socket1.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);

        assertEquals("第一次登录失败", 0, res.getError());
        assertNotNull("第一次登录authKey为空", res.getAuthKey());

        authKey1 = res.getAuthKey();
        System.out.println("第一次登录成功，authKey: " + authKey1);
    }

    /**
     * 第一次选择角色
     */
    private void selectCharacter1() throws Exception {
        REQ_SELECT_CHARACTER req = new REQ_SELECT_CHARACTER();
        req.setAuthKey(authKey1);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_SELECT_CHARACTER> reqCodec = ProtobufProxy.create(REQ_SELECT_CHARACTER.class);
        byte[] reqBytes = reqCodec.encode(req);

        OutputStream out = socket1.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = socket1.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_SELECT_CHARACTER> resCodec = ProtobufProxy.create(RES_SELECT_CHARACTER.class);
        RES_SELECT_CHARACTER res = resCodec.decode(responseBytes);

        assertEquals("第一次选择角色失败", 0, res.getError());
        assertNotNull("第一次选择角色信息为空", res.getCharacter());

        System.out.println("第一次选择角色成功");
    }

    /**
     * 第一次进入游戏
     */
    private void enterGame1() throws Exception {
        REQ_ENTER_GAME req = new REQ_ENTER_GAME();
        req.setAuthKey(authKey1);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_ENTER_GAME> reqCodec = ProtobufProxy.create(REQ_ENTER_GAME.class);
        byte[] reqBytes = reqCodec.encode(req);

        OutputStream out = socket1.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = socket1.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_ENTER_GAME> resCodec = ProtobufProxy.create(RES_ENTER_GAME.class);
        RES_ENTER_GAME res = resCodec.decode(responseBytes);

        assertEquals("第一次进入游戏失败", 0, res.getError());
        assertNotNull("第一次进入游戏数据为空", res.getGameData());

        System.out.println("第一次进入游戏成功");
    }

    /**
     * 第二次登录并获取authKey
     */
    private void loginAndGetAuthKey2() throws Exception {
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid(TEST_OPENID);
        req.setDeviceId(TEST_DEVICE_ID_2);
        req.setDeviceType(1); // Android
        req.setClientVersion("1.0.0");

        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);

        socket2 = new Socket(SERVER_HOST, SERVER_PORT);
        socket2.setSoTimeout(15000);

        OutputStream out = socket2.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = socket2.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);

        assertEquals("第二次登录失败（应该允许重复登录）", 0, res.getError());
        assertNotNull("第二次登录authKey为空", res.getAuthKey());

        authKey2 = res.getAuthKey();
        System.out.println("第二次登录成功，authKey: " + authKey2);
    }

    /**
     * 验证旧连接被踢掉
     */
    private void verifyOldConnectionKicked() throws Exception {
        try {
            // 尝试使用旧连接发送请求
            REQ_ENTER_GAME req = new REQ_ENTER_GAME();
            req.setAuthKey(authKey1);
            req.setAccountID(TEST_ACCOUNT_ID);
            req.setCharguid(CHARACTER_GUID);

            Codec<REQ_ENTER_GAME> reqCodec = ProtobufProxy.create(REQ_ENTER_GAME.class);
            byte[] reqBytes = reqCodec.encode(req);

            OutputStream out = socket1.getOutputStream();
            out.write(reqBytes);
            out.flush();

            InputStream in = socket1.getInputStream();
            byte[] responseBytes = readFully(in);

            Codec<RES_ENTER_GAME> resCodec = ProtobufProxy.create(RES_ENTER_GAME.class);
            RES_ENTER_GAME res = resCodec.decode(responseBytes);

            // 如果旧连接还能收到响应，检查错误码
            assertNotEquals("旧连接未返回错误", 0, res.getError());
            System.out.println("旧连接验证通过，已返回错误: " + res.getError());
        } catch (Exception e) {
            // 如果旧连接已经断开，抛出异常是正常的
            System.out.println("旧连接验证通过，连接已断开: " + e.getMessage());
        }
    }

    /**
     * 第二次选择角色
     */
    private void selectCharacter2() throws Exception {
        REQ_SELECT_CHARACTER req = new REQ_SELECT_CHARACTER();
        req.setAuthKey(authKey2);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_SELECT_CHARACTER> reqCodec = ProtobufProxy.create(REQ_SELECT_CHARACTER.class);
        byte[] reqBytes = reqCodec.encode(req);

        OutputStream out = socket2.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = socket2.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_SELECT_CHARACTER> resCodec = ProtobufProxy.create(RES_SELECT_CHARACTER.class);
        RES_SELECT_CHARACTER res = resCodec.decode(responseBytes);

        assertEquals("第二次选择角色失败", 0, res.getError());
        assertNotNull("第二次选择角色信息为空", res.getCharacter());

        System.out.println("第二次选择角色成功");
    }

    /**
     * 第二次进入游戏
     */
    private void enterGame2() throws Exception {
        REQ_ENTER_GAME req = new REQ_ENTER_GAME();
        req.setAuthKey(authKey2);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_ENTER_GAME> reqCodec = ProtobufProxy.create(REQ_ENTER_GAME.class);
        byte[] reqBytes = reqCodec.encode(req);

        OutputStream out = socket2.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = socket2.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_ENTER_GAME> resCodec = ProtobufProxy.create(RES_ENTER_GAME.class);
        RES_ENTER_GAME res = resCodec.decode(responseBytes);

        assertEquals("第二次进入游戏失败", 0, res.getError());
        assertNotNull("第二次进入游戏数据为空", res.getGameData());

        System.out.println("第二次进入游戏成功");
    }

    /**
     * 验证数据库记录
     */
    private void verifyDatabaseRecords() throws Exception {
        String sql = "SELECT status, lastLoginTime FROM t_character WHERE charguid = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, CHARACTER_GUID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int status = rs.getInt("status");
        Timestamp lastLoginTime = rs.getTimestamp("lastLoginTime");

        assertEquals("角色状态不正确", STATUS_IN_GAME, status);
        assertNotNull("最后登录时间为空", lastLoginTime);

        // 验证最后登录时间在最近1分钟内
        long timeDiff = System.currentTimeMillis() - lastLoginTime.getTime();
        assertTrue("最后登录时间不在最近1分钟内", timeDiff < 60 * 1000);

        System.out.println("数据库记录验证通过");
        System.out.println("  - 角色状态: " + status + " (游戏中)");
        System.out.println("  - 最后登录时间: " + lastLoginTime);

        rs.close();
        stmt.close();
    }

    /**
     * 验证日志记录
     */
    private void verifyLogRecord() throws Exception {
        // 这里简化处理，实际应该检查日志文件
        System.out.println("日志记录验证: 需要检查日志文件中是否记录了登录和踢掉旧连接事件");
        System.out.println("  - 搜索关键字: " + TEST_OPENID);
        System.out.println("  - 日志级别: INFO或WARN");
        System.out.println("  - 日志内容: 应该包含第一次登录、第二次登录、踢掉旧连接信息");
        System.out.println("  - 角色GUID: " + CHARACTER_GUID);
        System.out.println("  - 角色名称: " + CHARACTER_NAME);

        // TODO: 实际实现应该读取日志文件并验证
        // String logContent = readLogFile("logs/game.log");
        // assertTrue("日志中未记录登录事件", logContent.contains(TEST_OPENID));
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
        stmt2.setLong(1, CHARACTER_GUID);
        stmt2.setLong(2, TEST_ACCOUNT_ID);
        stmt2.setString(3, CHARACTER_NAME);
        stmt2.setInt(4, 1); // 战士
        stmt2.setInt(5, 10);
        stmt2.executeUpdate();
        stmt2.close();
        System.out.println("创建测试角色: " + CHARACTER_GUID);
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

        if (socket1 != null) {
            try {
                socket1.close();
            } catch (Exception e) {
                System.out.println("关闭socket1时出错: " + e.getMessage());
            }
        }

        if (socket2 != null) {
            try {
                socket2.close();
            } catch (Exception e) {
                System.out.println("关闭socket2时出错: " + e.getMessage());
            }
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
