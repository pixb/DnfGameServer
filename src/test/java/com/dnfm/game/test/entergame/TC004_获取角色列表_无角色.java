package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.common.util.DBUtil;
import com.dnfm.mina.protobuf.REQ_CHARACTER_INFO;
import com.dnfm.mina.protobuf.RES_CHARACTER_INFO;
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

public class TC004_获取角色列表_无角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_004";

    private Socket socket;
    private String authKey;

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

        System.out.println("\n步骤2: 构造角色信息请求");
        REQ_CHARACTER_INFO req = new REQ_CHARACTER_INFO();
        req.authkey = authKey;
        req.option = 1;
        req.charlist = null;
        System.out.println("REQ_CHARACTER_INFO对象创建成功");

        System.out.println("\n步骤3: 序列化角色信息请求");
        Codec<REQ_CHARACTER_INFO> reqCodec = ProtobufProxy.create(REQ_CHARACTER_INFO.class);
        byte[] reqBytes = reqCodec.encode(req);
        assertNotNull("序列化失败", reqBytes);
        assertTrue("序列化数据为空", reqBytes.length > 0);
        System.out.println("序列化成功，数据长度: " + reqBytes.length);

        System.out.println("\n步骤4: 发送角色信息请求");
        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("角色信息请求发送成功");

        System.out.println("\n步骤5: 接收角色信息响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);
        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + responseBytes.length);

        System.out.println("\n步骤6: 反序列化角色信息响应");
        Codec<RES_CHARACTER_INFO> resCodec = ProtobufProxy.create(RES_CHARACTER_INFO.class);
        RES_CHARACTER_INFO res = resCodec.decode(responseBytes);
        assertNotNull("反序列化失败", res);
        System.out.println("反序列化成功");

        System.out.println("\n步骤7: 验证角色列表为空");
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

        System.out.println("\n步骤8: 数据库验证");
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
}
