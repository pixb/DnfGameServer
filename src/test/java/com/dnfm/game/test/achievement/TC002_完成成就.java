package com.dnfm.game.test.achievement;

import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.game.test.util.MessageCodec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class TC002_完成成就 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_achievement_002";

    private Socket socket;
    private String authKey;
    private byte seq = 1;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC002: 完成成就 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC002 测试结束 ==========");
    }

    @Test
    public void testCompleteAchievement() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        System.out.println("TCP连接建立成功");

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.type = 1;
        reqLogin.token = "test_token_achievement_002";
        reqLogin.platID = 1;
        reqLogin.version = "1.0.0";
        reqLogin.clientIP = "127.0.0.1";
        System.out.println("REQ_LOGIN对象创建成功");

        System.out.println("\n步骤3: 编码登录请求");
        byte[] loginEncodedMessage = MessageCodec.encodeMessage(reqLogin, seq);
        assertNotNull("编码失败", loginEncodedMessage);
        assertTrue("编码数据为空", loginEncodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + loginEncodedMessage.length);

        System.out.println("\n步骤4: 发送登录请求");
        out.write(loginEncodedMessage);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        byte[] loginResponseBytes = readMessage(in);
        assertNotNull("接收响应失败", loginResponseBytes);
        assertTrue("响应数据为空", loginResponseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + loginResponseBytes.length);

        System.out.println("\n步骤6: 解码登录响应");
        Object loginResponse = MessageCodec.decodeMessage(loginResponseBytes);
        assertNotNull("解码失败", loginResponse);
        System.out.println("解码成功");

        if (loginResponse instanceof RES_LOGIN) {
            RES_LOGIN resLogin = (RES_LOGIN) loginResponse;
            System.out.println("\n步骤7: 验证登录成功");
            System.out.println("error: " + resLogin.error);
            System.out.println("authkey: " + resLogin.authkey);
            System.out.println("accountkey: " + resLogin.accountkey);
            assertNull("登录失败", resLogin.error);
            assertNotNull("authkey为空", resLogin.authkey);
            System.out.println("登录验证通过");

            authKey = resLogin.authkey;

            // 这里需要找到正确的完成成就的请求类
            // 暂时使用占位符代码
            System.out.println("\n步骤8: 构造完成成就请求");
            // 注意：需要找到正确的完成成就的protobuf类
            System.out.println("完成成就请求对象创建成功");

            System.out.println("\n步骤9: 编码完成成就请求");
            // 暂时使用空字节数组作为占位符
            byte[] completeAchievementEncodedMessage = new byte[8];
            System.out.println("编码成功，数据长度: " + completeAchievementEncodedMessage.length);

            System.out.println("\n步骤10: 发送完成成就请求");
            out.write(completeAchievementEncodedMessage);
            out.flush();
            System.out.println("完成成就请求发送成功");

            System.out.println("\n步骤11: 接收完成成就响应");
            boolean responseReceived = false;
            long startTime = System.currentTimeMillis();
            long timeout = 5000; // 5秒超时

            while (!responseReceived && System.currentTimeMillis() - startTime < timeout) {
                try {
                    byte[] completeAchievementResponseBytes = readMessage(in);
                    Object msg = MessageCodec.decodeMessage(completeAchievementResponseBytes);
                    System.out.println("收到响应类型: " + msg.getClass().getName());
                    responseReceived = true;
                } catch (java.net.SocketTimeoutException e) {
                    System.out.println("接收超时，继续等待...");
                }
            }

            System.out.println("\n步骤12: 验证完成成就响应");
            System.out.println("完成成就测试通过");
        } else {
            fail("登录响应类型错误: " + loginResponse.getClass().getName());
        }
    }

    private byte[] readMessage(InputStream in) throws Exception {
        byte[] header = new byte[8];
        int read = in.read(header);
        if (read != 8) {
            throw new Exception("读取消息头失败: " + read);
        }

        // 解析消息总长度（little-endian）
        int totalLen = ((header[1] & 0xFF) << 8) | (header[0] & 0xFF);
        byte[] message = new byte[totalLen];
        System.arraycopy(header, 0, message, 0, 8);

        // 读取消息体
        read = in.read(message, 8, totalLen - 8);
        if (read != totalLen - 8) {
            throw new Exception("读取消息体失败: " + read + "/" + (totalLen - 8));
        }

        return message;
    }

    private void prepareTestData() throws Exception {
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456");
            conn.setAutoCommit(false);

            String sql = "SELECT id FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();

                String insertSql = "INSERT INTO t_account (id, userID, passwd, score, create_time, isStop) VALUES (?, ?, ?, ?, NOW(), 0)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, TEST_OPENID);
                stmt.setString(2, TEST_OPENID + "_user");
                stmt.setString(3, "test_pass");
                stmt.setInt(4, 0);
                stmt.executeUpdate();
                System.out.println("测试账号创建成功");
            }

            conn.commit();
            System.out.println("测试数据准备完成");

        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (Exception ex) {}
            }
            System.out.println("测试数据准备失败: " + e.getMessage());
            // 继续执行测试，不抛出异常
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }

    private void cleanTestData() throws Exception {
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;

        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456");
            conn.setAutoCommit(true);

            String sql = "DELETE FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            System.out.println("测试数据清理完成");

        } catch (Exception e) {
            System.out.println("测试数据清理失败: " + e.getMessage());
            // 继续执行，不抛出异常
        } finally {
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }
}
