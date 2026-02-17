package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
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
 * TC002_玩家登录失败_无效openid
 *
 * 测试目的: 验证使用无效openid登录时返回正确的错误码，并且不创建任何数据库记录
 * 测试类型: 异常测试
 * 优先级: 高
 */
public class TC002_玩家登录失败_无效openid {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String INVALID_OPENID = "invalid_openid_001";
    private static final String TEST_DEVICE_ID = "device_test_002";
    private static final int ERROR_CODE_ACCOUNT_NOT_EXIST = 1001; // 账号不存在错误码

    private Socket socket;
    private Connection dbConnection;

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

        // 1.3 确保测试账号不存在
        ensureAccountNotExists();

        System.out.println("========== 测试环境准备完成 ==========");
    }

    @Test
    public void testPlayerLoginWithInvalidOpenid() throws Exception {
        System.out.println("========== 开始测试: TC002_玩家登录失败_无效openid ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 构造登录请求（无效openid）
        System.out.println("步骤2: 构造登录请求（无效openid）");
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid(INVALID_OPENID);
        req.setDeviceId(TEST_DEVICE_ID);
        req.setDeviceType(1); // Android
        req.setClientVersion("1.0.0");

        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);

        assertNotNull("请求序列化失败", reqBytes);
        assertTrue("请求数据为空", reqBytes.length > 0);
        System.out.println("请求序列化成功，长度: " + reqBytes.length);

        // 步骤3: 发送登录请求
        System.out.println("步骤3: 发送登录请求");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(15000); // 15秒超时

        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("登录请求发送成功");

        // 步骤4: 接收并解析响应
        System.out.println("步骤4: 接收并解析响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);

        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("响应接收成功，长度: " + responseBytes.length);

        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);

        assertNotNull("响应反序列化失败", res);
        System.out.println("响应反序列化成功");

        // 步骤5: 验证登录失败
        System.out.println("步骤5: 验证登录失败");

        // 5.1 验证错误码
        assertNotEquals("错误码为0（应该非0）", 0, res.getError());
        System.out.println("错误码验证通过: " + res.getError());

        // 5.2 验证错误码具体值
        assertEquals("错误码不正确", ERROR_CODE_ACCOUNT_NOT_EXIST, res.getError());
        System.out.println("错误码值验证通过: " + res.getError() + " (账号不存在)");

        // 5.3 验证authKey
        assertNull("authKey不为null（应该为null）", res.getAuthKey());
        System.out.println("authKey验证通过: null");

        // 5.4 验证账号信息
        assertNull("账号信息不为null（应该为null）", res.getAccount());
        System.out.println("账号信息验证通过: null");

        // 5.5 验证角色列表
        assertNull("角色列表不为null（应该为null）", res.getCharacters());
        System.out.println("角色列表验证通过: null");

        // 步骤6: 验证数据库无登录记录
        System.out.println("步骤6: 验证数据库无登录记录");
        verifyNoLoginRecord();

        // 步骤7: 验证数据库无token记录
        System.out.println("步骤7: 验证数据库无token记录");
        verifyNoTokenRecord();

        // 步骤8: 验证数据库无账号记录
        System.out.println("步骤8: 验证数据库无账号记录");
        verifyNoAccountRecord();

        // 步骤9: 验证日志记录
        System.out.println("步骤9: 验证日志记录");
        verifyLogRecord();

        long endTime = System.currentTimeMillis();
        System.out.println("========== 测试执行完成，耗时: " + (endTime - startTime) + "ms ==========");
    }

    /**
     * 验证无登录记录
     */
    private void verifyNoLoginRecord() throws Exception {
        String sql = "SELECT COUNT(*) FROM t_account_login_log WHERE openid = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setString(1, INVALID_OPENID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int count = rs.getInt(1);
        assertEquals("登录记录不为0（应该为0）", 0, count);
        System.out.println("登录记录验证通过: 0 条");

        rs.close();
        stmt.close();
    }

    /**
     * 验证无token记录
     */
    private void verifyNoTokenRecord() throws Exception {
        String sql = "SELECT COUNT(*) FROM t_account_token WHERE accountID IN (SELECT accountID FROM t_account WHERE openid = ?)";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setString(1, INVALID_OPENID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int count = rs.getInt(1);
        assertEquals("token记录不为0（应该为0）", 0, count);
        System.out.println("token记录验证通过: 0 条");

        rs.close();
        stmt.close();
    }

    /**
     * 验证无账号记录
     */
    private void verifyNoAccountRecord() throws Exception {
        String sql = "SELECT COUNT(*) FROM t_account WHERE openid = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setString(1, INVALID_OPENID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int count = rs.getInt(1);
        assertEquals("账号记录不为0（应该为0）", 0, count);
        System.out.println("账号记录验证通过: 0 条");

        rs.close();
        stmt.close();
    }

    /**
     * 验证日志记录
     */
    private void verifyLogRecord() throws Exception {
        // 这里简化处理，实际应该检查日志文件
        // 可以通过读取日志文件或使用日志框架的API来验证
        System.out.println("日志记录验证: 需要检查日志文件中是否记录了登录失败事件");
        System.out.println("  - 搜索关键字: " + INVALID_OPENID);
        System.out.println("  - 日志级别: ERROR或WARN");
        System.out.println("  - 日志内容: 应该包含登录失败信息");

        // TODO: 实际实现应该读取日志文件并验证
        // String logContent = readLogFile("logs/game.log");
        // assertTrue("日志中未记录登录失败事件", logContent.contains(INVALID_OPENID));
    }

    /**
     * 确保测试账号不存在
     */
    private void ensureAccountNotExists() throws Exception {
        System.out.println("确保测试账号不存在");

        // 删除账号记录
        String deleteAccountSql = "DELETE FROM t_account WHERE openid = ?";
        PreparedStatement stmt1 = dbConnection.prepareStatement(deleteAccountSql);
        stmt1.setString(1, INVALID_OPENID);
        int accountCount = stmt1.executeUpdate();
        stmt1.close();
        System.out.println("删除账号记录: " + accountCount + " 条");

        // 删除登录记录
        String deleteLoginLogSql = "DELETE FROM t_account_login_log WHERE openid = ?";
        PreparedStatement stmt2 = dbConnection.prepareStatement(deleteLoginLogSql);
        stmt2.setString(1, INVALID_OPENID);
        int loginLogCount = stmt2.executeUpdate();
        stmt2.close();
        System.out.println("删除登录记录: " + loginLogCount + " 条");

        // 删除token记录
        String deleteTokenSql = "DELETE FROM t_account_token WHERE accountID IN (SELECT accountID FROM t_account WHERE openid = ?)";
        PreparedStatement stmt3 = dbConnection.prepareStatement(deleteTokenSql);
        stmt3.setString(1, INVALID_OPENID);
        int tokenCount = stmt3.executeUpdate();
        stmt3.close();
        System.out.println("删除token记录: " + tokenCount + " 条");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("========== 开始清理测试数据 ==========");

        // 步骤10: 清理测试数据
        if (dbConnection != null) {
            // 确认数据库无记录
            verifyNoLoginRecord();
            verifyNoTokenRecord();
            verifyNoAccountRecord();

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
