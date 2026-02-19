package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.PT_CHARACTER;
import com.dnfm.mina.protobuf.REQ_CHARAC_LIST;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_CHARAC_LIST;
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

public class TC005_选择角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_005";

    private Socket socket;
    private String authKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC005: 选择角色 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC005 测试结束 ==========");
    }

    @Test
    public void testSelectCharacter() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.token = "test_token_005";
        reqLogin.platID = 1001;
        reqLogin.clientIP = "127.0.0.1";
        reqLogin.version = "1.0.0";
        System.out.println("REQ_LOGIN对象创建成功");

        System.out.println("\n步骤3: 编码登录请求");
        byte[] loginReqData = MessageCodec.encodeMessage(reqLogin, (byte) 1);
        assertNotNull("编码失败", loginReqData);
        assertTrue("编码数据为空", loginReqData.length > 0);
        System.out.println("编码成功，数据长度: " + loginReqData.length);

        System.out.println("\n步骤4: 发送登录请求");
        OutputStream out = socket.getOutputStream();
        out.write(loginReqData);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        InputStream in = socket.getInputStream();
        byte[] loginRespData = readMessage(in);
        assertNotNull("响应数据为空", loginRespData);
        assertTrue("响应数据为空", loginRespData.length > 0);
        System.out.println("接收响应成功，数据长度: " + loginRespData.length);

        System.out.println("\n步骤6: 解码登录响应");
        RES_LOGIN resLogin = (RES_LOGIN) MessageCodec.decodeMessage(loginRespData);
        assertNotNull("解码失败", resLogin);
        System.out.println("解码成功");

        System.out.println("\n步骤7: 验证登录成功");
        System.out.println("error: " + resLogin.error);
        Integer error = resLogin.error != null ? resLogin.error : 0;
        assertEquals("登录失败，error不为0", Integer.valueOf(0), error);
        System.out.println("登录验证通过");

        System.out.println("\n步骤8: 构造角色列表请求");
        REQ_CHARAC_LIST reqCharacList = new REQ_CHARAC_LIST();
        System.out.println("REQ_CHARAC_LIST对象创建成功");

        System.out.println("\n步骤9: 编码角色列表请求");
        byte[] characterReqData = MessageCodec.encodeMessage(reqCharacList, (byte) 2);
        assertNotNull("编码失败", characterReqData);
        assertTrue("编码数据为空", characterReqData.length > 0);
        System.out.println("编码成功，数据长度: " + characterReqData.length);

        System.out.println("\n步骤10: 发送角色列表请求");
        out.write(characterReqData);
        out.flush();
        System.out.println("角色列表请求发送成功");

        System.out.println("\n步骤11: 接收角色列表响应");
        RES_CHARAC_LIST resCharacList = null;
        while (resCharacList == null) {
            byte[] characterRespData = readMessage(in);
            Object msg = MessageCodec.decodeMessage(characterRespData);
            if (msg instanceof RES_CHARAC_LIST) {
                resCharacList = (RES_CHARAC_LIST) msg;
                System.out.println("收到RES_CHARAC_LIST响应，退出循环");
            } else {
                System.out.println("收到未知响应类型，继续等待: " + msg.getClass().getName());
            }
        }

        System.out.println("\n步骤12: 验证角色列表");
        System.out.println("error: " + resCharacList.error);
        System.out.println("角色数量: " + resCharacList.characlist.size());
        Integer charError = resCharacList.error != null ? resCharacList.error : 0;
        assertEquals("获取角色列表失败，error不为0", Integer.valueOf(0), charError);
        assertTrue("角色列表为空", resCharacList.characlist.size() > 0);
        System.out.println("角色列表验证通过");

        System.out.println("\n步骤13: 显示角色信息");
        for (PT_CHARACTER character : resCharacList.characlist) {
            System.out.println("角色信息:");
            System.out.println("  charguid: " + character.charguid);
            System.out.println("  name: " + character.name);
            System.out.println("  job: " + character.job);
            System.out.println("  level: " + character.level);
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
            stmt.executeUpdate();
            stmt.close();

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
