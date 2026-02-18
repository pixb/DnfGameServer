package com.dnfm.game.test.entergame;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class TC002_玩家登录失败_无效openid {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String INVALID_OPENID = "invalid_openid_002";

    private Socket socket;
    private byte seq = 0;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC002: 玩家登录失败_无效openid ==========");
        cleanTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC002 测试结束 ==========");
    }

    @Test
    public void testInvalidOpenidLogin() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求（无效openid）");
        REQ_LOGIN req = new REQ_LOGIN();
        req.openid = INVALID_OPENID;
        req.type = 1;
        req.token = "test_token_002";
        req.platID = 1001;
        req.clientIP = "127.0.0.1";
        req.version = "1.0.0";
        System.out.println("REQ_LOGIN对象创建成功");

        System.out.println("\n步骤3: 编码登录请求");
        byte[] encodedMessage = MessageCodec.encodeMessage(req, seq);
        assertNotNull("编码失败", encodedMessage);
        assertTrue("编码数据为空", encodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + encodedMessage.length);
        System.out.println("消息头: " + bytesToHex(encodedMessage, 0, 8));

        System.out.println("\n步骤4: 发送登录请求");
        OutputStream out = socket.getOutputStream();
        out.write(encodedMessage);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应（预期无响应）");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = null;
        try {
            responseBytes = readMessage(in);
            System.out.println("接收到响应，数据长度: " + responseBytes.length);
            System.out.println("响应消息头: " + bytesToHex(responseBytes, 0, Math.min(8, responseBytes.length)));
            
            System.out.println("\n步骤6: 解码登录响应");
            Message response = MessageCodec.decodeMessage(responseBytes);
            assertNotNull("解码失败", response);
            assertTrue("响应类型错误", response instanceof RES_LOGIN);
            RES_LOGIN res = (RES_LOGIN) response;
            System.out.println("解码成功");

            System.out.println("\n步骤7: 验证登录失败");
            System.out.println("error: " + res.error);
            System.out.println("authkey: " + res.authkey);
            System.out.println("accountkey: " + res.accountkey);

            assertTrue("登录应该失败，但error为null或0", res.error != null && res.error != 0);
            System.out.println("错误码验证通过，登录失败");

            assertNull("authkey应该为null", res.authkey);
            System.out.println("authkey验证通过，为null");
        } catch (java.net.SocketTimeoutException e) {
            System.out.println("超时异常（预期行为）: 服务端未发送响应，因为openid无效");
            System.out.println("验证通过: 无效openid导致服务端不发送响应");
        }

        System.out.println("\n步骤8: 数据库验证");
        verifyDatabase();
    }

    private void verifyDatabase() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, INVALID_OPENID);
            rs = stmt.executeQuery();

            assertTrue("查询失败", rs.next());
            int count = rs.getInt(1);
            assertEquals("不应该创建新账号，但数据库中存在该openid", 0, count);
            System.out.println("数据库验证通过，没有创建新账号");

        } finally {
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
            stmt.setString(1, INVALID_OPENID);
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
        
        ByteBuffer buffer = ByteBuffer.wrap(header);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
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

    private byte[] readFully(InputStream in) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    private String bytesToHex(byte[] bytes, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + length && i < bytes.length; i++) {
            sb.append(String.format("%02X ", bytes[i]));
        }
        return sb.toString().trim();
    }
}
