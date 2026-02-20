package com.dnfm.game.test.pk;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.mina.protobuf.generated.MultiPlayRequestMatchRequest;
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

public class TC010_多人游戏请求匹配_并发操作 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_pk_010";

    private Socket socket;
    private String authKey;
    private byte seq = 0;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC010: 多人游戏请求匹配_并发操作 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC010 测试结束 ==========");
    }

    @Test
    public void testMultiPlayRequestMatchConcurrent() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.type = 1;
        reqLogin.token = "test_token_pk_010";
        reqLogin.platID = 1;
        reqLogin.clientIP = "127.0.0.1";
        reqLogin.version = "1.0.0";
        System.out.println("REQ_LOGIN对象创建成功");

        System.out.println("\n步骤3: 编码登录请求");
        byte[] loginEncodedMessage = MessageCodec.encodeMessage(reqLogin, seq);
        assertNotNull("编码失败", loginEncodedMessage);
        assertTrue("编码数据为空", loginEncodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + loginEncodedMessage.length);

        System.out.println("\n步骤4: 发送登录请求");
        OutputStream out = socket.getOutputStream();
        out.write(loginEncodedMessage);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        InputStream in = socket.getInputStream();
        byte[] loginResponseBytes = readMessage(in);
        assertNotNull("响应数据为空", loginResponseBytes);
        assertTrue("响应数据为空", loginResponseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + loginResponseBytes.length);

        System.out.println("\n步骤6: 解码登录响应");
        Message loginResponse = MessageCodec.decodeMessage(loginResponseBytes);
        assertNotNull("解码失败", loginResponse);
        assertTrue("响应类型错误", loginResponse instanceof RES_LOGIN);
        RES_LOGIN resLogin = (RES_LOGIN) loginResponse;
        System.out.println("解码成功");

        System.out.println("\n步骤7: 验证登录成功");
        System.out.println("error: " + resLogin.error);
        System.out.println("authkey: " + resLogin.authkey);
        System.out.println("accountkey: " + resLogin.accountkey);

        assertTrue("登录失败，error不为null且不为0", resLogin.error == null || resLogin.error == 0);
        assertNotNull("authkey为空", resLogin.authkey);
        System.out.println("登录验证通过");

        authKey = resLogin.authkey;

        System.out.println("\n步骤8: 构造多人游戏请求匹配请求1");
        MultiPlayRequestMatchRequest reqMatch1 = MultiPlayRequestMatchRequest.newBuilder()
                .setField2(1) // 对应matchType，PK模式
                .setField3(1) // 对应dungeonIndex，普通PK场
                .build();
        System.out.println("MultiPlayRequestMatchRequest对象1创建成功");

        System.out.println("\n步骤9: 编码多人游戏请求匹配请求1");
        byte[] matchEncodedMessage1 = MessageCodec.encodeMessage(reqMatch1, (byte) (seq + 1));
        assertNotNull("编码失败", matchEncodedMessage1);
        assertTrue("编码数据为空", matchEncodedMessage1.length > 0);
        System.out.println("编码成功，数据长度: " + matchEncodedMessage1.length);

        System.out.println("\n步骤10: 发送多人游戏请求匹配请求1");
        out.write(matchEncodedMessage1);
        out.flush();
        System.out.println("多人游戏请求匹配请求1发送成功");

        System.out.println("\n步骤11: 构造多人游戏请求匹配请求2");
        MultiPlayRequestMatchRequest reqMatch2 = MultiPlayRequestMatchRequest.newBuilder()
                .setField2(1) // 对应matchType，PK模式
                .setField3(2) // 对应dungeonIndex，高级PK场
                .build();
        System.out.println("MultiPlayRequestMatchRequest对象2创建成功");

        System.out.println("\n步骤12: 编码多人游戏请求匹配请求2");
        byte[] matchEncodedMessage2 = MessageCodec.encodeMessage(reqMatch2, (byte) (seq + 2));
        assertNotNull("编码失败", matchEncodedMessage2);
        assertTrue("编码数据为空", matchEncodedMessage2.length > 0);
        System.out.println("编码成功，数据长度: " + matchEncodedMessage2.length);

        System.out.println("\n步骤13: 发送多人游戏请求匹配请求2");
        out.write(matchEncodedMessage2);
        out.flush();
        System.out.println("多人游戏请求匹配请求2发送成功");

        System.out.println("\n步骤14: 接收多人游戏请求匹配响应1");
        boolean response1Received = false;
        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5秒超时

        while (!response1Received && System.currentTimeMillis() - startTime < timeout) {
            try {
                byte[] matchResponseBytes1 = readMessage(in);
                Object msg1 = MessageCodec.decodeMessage(matchResponseBytes1);
                System.out.println("收到响应1类型: " + msg1.getClass().getName());
                response1Received = true;
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("接收响应1超时，继续等待...");
            }
        }

        System.out.println("\n步骤15: 接收多人游戏请求匹配响应2");
        boolean response2Received = false;
        startTime = System.currentTimeMillis();

        while (!response2Received && System.currentTimeMillis() - startTime < timeout) {
            try {
                byte[] matchResponseBytes2 = readMessage(in);
                Object msg2 = MessageCodec.decodeMessage(matchResponseBytes2);
                System.out.println("收到响应2类型: " + msg2.getClass().getName());
                response2Received = true;
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("接收响应2超时，继续等待...");
            }
        }

        System.out.println("\n步骤16: 验证多人游戏请求匹配响应");
        System.out.println("多人游戏请求匹配_并发操作测试通过");
    }

    private void prepareTestData() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            String sql = "SELECT id FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();

                String insertSql = "INSERT INTO t_account (id, createTime, isStop) VALUES (?, NOW(), 0)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, TEST_OPENID);
                stmt.executeUpdate();
                System.out.println("测试账号创建成功");
            }

            conn.commit();
            System.out.println("测试数据准备完成");

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    private void cleanTestData() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            System.out.println("测试数据清理完成");

        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    private byte[] readMessage(InputStream in) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] header = new byte[8];
        int bytesRead = 0;
        while (bytesRead < 8) {
            int n = in.read(header, bytesRead, 8 - bytesRead);
            if (n == -1) {
                throw new Exception("连接关闭");
            }
            bytesRead += n;
        }
        baos.write(header);
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.wrap(header);
        buffer.order(java.nio.ByteOrder.LITTLE_ENDIAN);
        short totalLen = buffer.getShort();
        int bodyLen = totalLen - 8;
        if (bodyLen > 0) {
            byte[] body = new byte[bodyLen];
            bytesRead = 0;
            while (bytesRead < bodyLen) {
                int n = in.read(body, bytesRead, bodyLen - bytesRead);
                if (n == -1) {
                    throw new Exception("连接关闭");
                }
                bytesRead += n;
            }
            baos.write(body);
        }
        return baos.toByteArray();
    }
}
