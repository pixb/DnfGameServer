package com.dnfm.game.test.entergame;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.REQ_CHARACTER_INFO;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_CHARACTER_INFO;
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

public class TC004_获取角色列表_无角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_004";

    private Socket socket;
    private String authKey;
    private byte seq = 1;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC004: 获取角色列表_无角色 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC004 测试结束 ==========");
    }

    @Test
    public void testGetCharacterListNoCharacters() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN loginReq = new REQ_LOGIN();
        loginReq.openid = TEST_OPENID;
        loginReq.token = "test_token_004";
        loginReq.platID = 1001;
        loginReq.clientIP = "127.0.0.1";
        loginReq.version = "1.0.0";
        System.out.println("REQ_LOGIN对象创建成功");

        System.out.println("\n步骤3: 编码登录请求");
        byte[] loginMessage = MessageCodec.encodeMessage(loginReq, seq);
        assertNotNull("编码失败", loginMessage);
        assertTrue("编码数据为空", loginMessage.length > 0);
        System.out.println("编码成功，数据长度: " + loginMessage.length);

        System.out.println("\n步骤4: 发送登录请求");
        OutputStream out = socket.getOutputStream();
        out.write(loginMessage);
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
        RES_LOGIN loginRes = (RES_LOGIN) loginResponse;
        System.out.println("解码成功");

        System.out.println("\n步骤7: 验证登录成功");
        assertTrue("登录失败，error不为null且不为0", loginRes.error == null || loginRes.error == 0);
        assertNotNull("authkey为空", loginRes.authkey);
        System.out.println("登录验证通过");

        authKey = loginRes.authkey;
        seq++;

        System.out.println("\n步骤8: 构造角色信息请求");
        REQ_CHARACTER_INFO req = new REQ_CHARACTER_INFO();
        req.authkey = authKey;
        req.option = 1;
        req.charlist = null;
        System.out.println("REQ_CHARACTER_INFO对象创建成功");

        System.out.println("\n步骤9: 编码角色信息请求");
        byte[] encodedMessage = MessageCodec.encodeMessage(req, seq);
        assertNotNull("编码失败", encodedMessage);
        assertTrue("编码数据为空", encodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + encodedMessage.length);

        System.out.println("\n步骤10: 发送角色信息请求");
        out.write(encodedMessage);
        out.flush();
        System.out.println("角色信息请求发送成功");

        System.out.println("\n步骤11: 接收角色信息响应");
        RES_CHARACTER_INFO res = null;
        while (res == null) {
            byte[] responseBytes = readMessage(in);
            // 解码响应
            Message response = MessageCodec.decodeMessage(responseBytes);
            if (response instanceof RES_CHARACTER_INFO) {
                System.out.println("收到RES_CHARACTER_INFO响应，退出循环");
                res = (RES_CHARACTER_INFO) response;
            } else {
                System.out.println("收到未知响应类型，继续等待: " + response.getClass().getName());
            }
        }

        System.out.println("\n步骤12: 验证角色列表为空");
        System.out.println("error: " + res.error);
        System.out.println("角色数量: " + (res.charlist != null ? res.charlist.size() : 0));

        assertEquals("获取角色列表失败，error不为0", Integer.valueOf(0), res.error);
        System.out.println("错误码验证通过");

        if (res.charlist == null) {
            System.out.println("角色列表为null，验证通过");
        } else if (res.charlist.size() == 0) {
            System.out.println("角色列表为空，验证通过");
        } else {
            fail("角色列表应该为空，但包含" + res.charlist.size() + "个角色");
        }

        System.out.println("\n步骤13: 数据库验证");
        verifyDatabase();
    }

    private void verifyDatabase() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM t_role WHERE openid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            assertTrue("查询失败", rs.next());
            int count = rs.getInt(1);
            assertEquals("数据库中应该没有角色数据", 0, count);
            System.out.println("数据库验证通过，角色数量: " + count);

        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    private void prepareTestData() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            String accountSql = "SELECT id FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(accountSql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();

                String insertAccountSql = "INSERT INTO t_account (id, createTime, isStop) VALUES (?, NOW(), 0)";
                stmt = conn.prepareStatement(insertAccountSql);
                stmt.setString(1, TEST_OPENID);
                stmt.executeUpdate();
                stmt.close();
            }

            String deleteCharSql = "DELETE FROM t_role WHERE openid = ?";
            stmt = conn.prepareStatement(deleteCharSql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            stmt.close();

            conn.commit();
            authKey = "test_authkey_004";
            System.out.println("测试数据准备完成，账号无角色");

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
            String sql = "DELETE FROM t_role WHERE openid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            stmt.close();

            sql = "DELETE FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            System.out.println("测试数据清理完成");

        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
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
}
