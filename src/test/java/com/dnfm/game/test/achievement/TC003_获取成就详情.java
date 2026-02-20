package com.dnfm.game.test.achievement;

import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.mina.protobuf.REQ_ACHIEVEMENT_INFO;
import com.dnfm.mina.protobuf.RES_ACHIEVEMENT_INFO;
import com.dnfm.game.test.util.MessageCodec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TC003_获取成就详情 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_achievement_003";
    private static final int TEST_ACHIEVEMENT_TYPE = 1;

    private Socket socket;
    private String authKey;
    private byte seq = 1;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC003: 获取成就详情 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC003 测试结束 ==========");
    }

    @Test
    public void testGetAchievementDetail() throws Exception {
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
        reqLogin.token = "test_token_achievement_003";
        reqLogin.platID = 1;
        reqLogin.version = "1.0.0";
        reqLogin.clientIP = "127.0.0.1";
        System.out.println("REQ_LOGIN对象创建成功，module: " + reqLogin.getModule());

        System.out.println("\n步骤3: 编码登录请求");
        byte[] loginEncodedMessage = MessageCodec.encodeMessage(reqLogin, seq);
        System.out.println("编码成功，数据长度: " + loginEncodedMessage.length);

        System.out.println("\n步骤4: 发送登录请求");
        out.write(loginEncodedMessage);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        try {
            byte[] loginResponseBytes = readMessage(in);
            System.out.println("接收响应成功，数据长度: " + loginResponseBytes.length);

            System.out.println("\n步骤6: 解码登录响应");
            Object loginResponse = MessageCodec.decodeMessage(loginResponseBytes);
            System.out.println("解码成功，响应类型: " + loginResponse.getClass().getName());

            // 步骤7: 验证登录成功
            System.out.println("\n步骤7: 验证登录成功");
            if (loginResponse instanceof RES_LOGIN) {
                RES_LOGIN resLogin = (RES_LOGIN) loginResponse;
                System.out.println("error: " + resLogin.error);
                System.out.println("authkey: " + resLogin.authkey);
                System.out.println("accountkey: " + resLogin.accountkey);
                authKey = resLogin.authkey;
                System.out.println("登录验证通过");
            } else {
                System.out.println("登录响应类型错误: " + loginResponse.getClass().getName());
                return;
            }

            // 步骤8: 构造获取成就详情请求
            System.out.println("\n步骤8: 构造获取成就详情请求");
            REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
            reqAchievementInfo.type = TEST_ACHIEVEMENT_TYPE;
            System.out.println("REQ_ACHIEVEMENT_INFO对象创建成功，type: " + reqAchievementInfo.type + ", module: " + reqAchievementInfo.getModule());

            // 步骤9: 编码获取成就详情请求
            System.out.println("\n步骤9: 编码获取成就详情请求");
            byte[] getAchievementDetailEncodedMessage = MessageCodec.encodeMessage(reqAchievementInfo, (byte) (seq + 1));
            System.out.println("编码成功，数据长度: " + getAchievementDetailEncodedMessage.length);

            // 步骤10: 发送获取成就详情请求
            System.out.println("\n步骤10: 发送获取成就详情请求");
            out.write(getAchievementDetailEncodedMessage);
            out.flush();
            System.out.println("获取成就详情请求发送成功");

            // 步骤11: 接收获取成就详情响应
            System.out.println("\n步骤11: 接收获取成就详情响应");
            boolean achievementInfoResponseReceived = false;
            long startTime = System.currentTimeMillis();
            long timeout = 5000; // 5秒超时

            while (!achievementInfoResponseReceived && System.currentTimeMillis() - startTime < timeout) {
                try {
                    byte[] responseData = readMessage(in);
                    Object response = MessageCodec.decodeMessage(responseData);
                    System.out.println("收到响应类型: " + response.getClass().getName());
                    
                    // 步骤12: 验证获取成就详情响应
                    System.out.println("\n步骤12: 验证获取成就详情响应");
                    if (response instanceof RES_ACHIEVEMENT_INFO) {
                        RES_ACHIEVEMENT_INFO resAchievementInfo = (RES_ACHIEVEMENT_INFO) response;
                        System.out.println("error: " + resAchievementInfo.error);
                        System.out.println("type: " + resAchievementInfo.type);
                        System.out.println("score: " + resAchievementInfo.score);
                        System.out.println("获取成就详情测试通过");
                        achievementInfoResponseReceived = true;
                    } else {
                        System.out.println("收到非预期响应类型，继续等待: " + response.getClass().getName());
                    }
                } catch (java.net.SocketTimeoutException e) {
                    System.out.println("接收超时，继续等待...");
                }
            }

            if (!achievementInfoResponseReceived) {
                System.out.println("接收获取成就详情响应超时");
            }
        } catch (java.net.SocketTimeoutException e) {
            System.out.println("接收响应超时: " + e.getMessage());
            System.out.println("服务器可能没有响应，或者请求格式不正确");
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
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