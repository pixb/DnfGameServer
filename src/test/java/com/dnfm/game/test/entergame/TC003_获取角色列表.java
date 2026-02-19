package com.dnfm.game.test.entergame;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.REQ_CHARAC_LIST;
import com.dnfm.mina.protobuf.RES_CHARAC_LIST;
import com.dnfm.mina.protobuf.PT_CHARACTER;
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

public class TC003_获取角色列表 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_003";

    private Socket socket;
    private String authKey;
    private byte seq = 0;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC003: 获取角色列表 ==========");
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
    public void testGetCharacterList() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        com.dnfm.mina.protobuf.REQ_LOGIN loginReq = new com.dnfm.mina.protobuf.REQ_LOGIN();
        loginReq.openid = TEST_OPENID;
        loginReq.type = 1;
        loginReq.token = "test_token_003";
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
        assertTrue("响应类型错误", loginResponse instanceof com.dnfm.mina.protobuf.RES_LOGIN);
        com.dnfm.mina.protobuf.RES_LOGIN loginRes = (com.dnfm.mina.protobuf.RES_LOGIN) loginResponse;
        System.out.println("解码成功");

        System.out.println("\n步骤7: 验证登录成功");
        assertTrue("登录失败，error不为null且不为0", loginRes.error == null || loginRes.error == 0);
        assertNotNull("authkey为空", loginRes.authkey);
        System.out.println("登录验证通过");

        authKey = loginRes.authkey;
        seq++;

        System.out.println("\n步骤8: 构造角色列表请求");
        REQ_CHARAC_LIST req = new REQ_CHARAC_LIST();
        System.out.println("REQ_CHARAC_LIST对象创建成功");

        System.out.println("\n步骤9: 编码角色列表请求");
        byte[] encodedMessage = MessageCodec.encodeMessage(req, seq);
        assertNotNull("编码失败", encodedMessage);
        assertTrue("编码数据为空", encodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + encodedMessage.length);
        System.out.println("消息头: " + bytesToHex(encodedMessage, 0, 8));

        System.out.println("\n步骤10: 发送角色列表请求");
        out.write(encodedMessage);
        out.flush();
        System.out.println("角色列表请求发送成功");

        System.out.println("\n步骤11: 接收角色列表响应");
        RES_CHARAC_LIST res = null;
        
        // 循环接收响应，直到收到RES_CHARAC_LIST响应
        while (res == null) {
            byte[] responseBytes = readMessage(in);
            assertNotNull("响应数据为空", responseBytes);
            assertTrue("响应数据为空", responseBytes.length > 0);
            System.out.println("接收响应成功，数据长度: " + responseBytes.length);
            System.out.println("响应消息头: " + bytesToHex(responseBytes, 0, Math.min(8, responseBytes.length)));

            System.out.println("\n步骤12: 解码角色列表响应");
            Message response = MessageCodec.decodeMessage(responseBytes);
            assertNotNull("解码失败", response);
            
            if (response instanceof com.dnfm.mina.protobuf.RES_PING) {
                System.out.println("收到RES_PING响应，继续等待");
            } else if (response instanceof RES_CHARAC_LIST) {
                System.out.println("收到RES_CHARAC_LIST响应，退出循环");
                res = (RES_CHARAC_LIST) response;
                System.out.println("解码成功");
            } else {
                System.out.println("收到未知响应类型，继续等待: " + response.getClass().getName());
            }
        }

        System.out.println("\n步骤13: 验证角色列表");
        System.out.println("error: " + res.error);
        System.out.println("角色数量: " + (res.characlist != null ? res.characlist.size() : 0));

        assertTrue("获取角色列表失败，error不为null且不为0", res.error == null || res.error == 0);
        System.out.println("错误码验证通过");

        assertNotNull("角色列表为空", res.characlist);
        assertTrue("角色列表为空", res.characlist.size() > 0);
        System.out.println("角色列表验证通过，角色数量: " + res.characlist.size());

        for (PT_CHARACTER character : res.characlist) {
            System.out.println("角色信息:");
            System.out.println("  charguid: " + character.charguid);
            System.out.println("  name: " + character.name);
            System.out.println("  job: " + character.job);
            System.out.println("  level: " + character.level);

            assertNotNull("角色guid为空", character.charguid);
            assertNotNull("角色名称为空", character.name);
            assertNotNull("角色职业为空", character.job);
            assertNotNull("角色等级为空", character.level);
        }

        System.out.println("\n步骤14: 数据库验证");
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
            assertTrue("数据库中没有角色数据", count > 0);
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

            String insertCharSql = "INSERT INTO t_role (roleId, uid, openid, name, job, level, createtime, deletionstatus) VALUES (?, ?, ?, ?, ?, ?, NOW(), 0)";
            stmt = conn.prepareStatement(insertCharSql);
            stmt.setInt(1, 1);
            stmt.setLong(2, 1001);
            stmt.setString(3, TEST_OPENID);
            stmt.setString(4, "TestPlayer1");
            stmt.setInt(5, 1);
            stmt.setInt(6, 10);
            System.out.println("插入角色SQL: " + insertCharSql);
            System.out.println("参数: roleId=1, uid=1001, openid=" + TEST_OPENID + ", name=TestPlayer1, job=1, level=10");
            stmt.executeUpdate();
            stmt.close();

            conn.commit();
            authKey = "test_authkey_003";
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
