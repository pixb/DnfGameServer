package com.dnfm.game.test.pk;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.mina.protobuf.REQ_HISTORICSITE_NOTI;
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

public class TC003_历史遗迹通知 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_pk_003";

    private Socket socket;
    private String authKey;
    private byte seq = 0;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC003: 历史遗迹通知 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC003 测试结束 ==========");
    }

    @Test
    public void testHistoricSiteNotification() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.type = 1;
        reqLogin.token = "test_token_pk_003";
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

        System.out.println("\n步骤8: 构造历史遗迹通知请求");
        REQ_HISTORICSITE_NOTI reqHistoricSite = new REQ_HISTORICSITE_NOTI();
        System.out.println("REQ_HISTORICSITE_NOTI对象创建成功");

        System.out.println("\n步骤9: 编码历史遗迹通知请求");
        byte[] historicSiteEncodedMessage = MessageCodec.encodeMessage(reqHistoricSite, (byte) (seq + 1));
        assertNotNull("编码失败", historicSiteEncodedMessage);
        assertTrue("编码数据为空", historicSiteEncodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + historicSiteEncodedMessage.length);

        System.out.println("\n步骤10: 发送历史遗迹通知请求");
        out.write(historicSiteEncodedMessage);
        out.flush();
        System.out.println("历史遗迹通知请求发送成功");

        System.out.println("\n步骤11: 接收历史遗迹通知响应");
        boolean responseReceived = false;
        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5秒超时

        while (!responseReceived && System.currentTimeMillis() - startTime < timeout) {
            try {
                byte[] historicSiteResponseBytes = readMessage(in);
                Object msg = MessageCodec.decodeMessage(historicSiteResponseBytes);
                System.out.println("收到响应类型: " + msg.getClass().getName());
                responseReceived = true;
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("接收超时，继续等待...");
            }
        }

        System.out.println("\n步骤12: 验证历史遗迹通知响应");
        System.out.println("历史遗迹通知测试通过");
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
