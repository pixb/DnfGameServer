package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.common.util.DBUtil;
import com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN;
import com.dnfm.mina.protobuf.RES_ENTER_TO_TOWN;
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

public class TC005_选择角色 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 20001;
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

        System.out.println("\n步骤2: 构造进入城镇请求");
        REQ_ENTER_TO_TOWN req = new REQ_ENTER_TO_TOWN();
        req.authkey = authKey;
        req.town = 1;
        req.area = 1;
        req.posx = 0;
        req.posy = 0;
        System.out.println("REQ_ENTER_TO_TOWN对象创建成功");

        System.out.println("\n步骤3: 序列化进入城镇请求");
        Codec<REQ_ENTER_TO_TOWN> reqCodec = ProtobufProxy.create(REQ_ENTER_TO_TOWN.class);
        byte[] reqBytes = reqCodec.encode(req);
        assertNotNull("序列化失败", reqBytes);
        assertTrue("序列化数据为空", reqBytes.length > 0);
        System.out.println("序列化成功，数据长度: " + reqBytes.length);

        System.out.println("\n步骤4: 发送进入城镇请求");
        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("进入城镇请求发送成功");

        System.out.println("\n步骤5: 接收进入城镇响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);
        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + responseBytes.length);

        System.out.println("\n步骤6: 反序列化进入城镇响应");
        Codec<RES_ENTER_TO_TOWN> resCodec = ProtobufProxy.create(RES_ENTER_TO_TOWN.class);
        RES_ENTER_TO_TOWN res = resCodec.decode(responseBytes);
        assertNotNull("反序列化失败", res);
        System.out.println("反序列化成功");

        System.out.println("\n步骤7: 验证进入城镇成功");
        System.out.println("error: " + res.error);

        assertEquals("进入城镇失败，error不为0", Integer.valueOf(0), res.error);
        System.out.println("错误码验证通过");

        System.out.println("\n步骤8: 数据库验证");
        verifyDatabase();
    }

    private void verifyDatabase() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM t_character WHERE accountID = (SELECT accountID FROM t_account WHERE openid = ?)";
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

            String accountSql = "SELECT accountID FROM t_account WHERE openid = ?";
            stmt = conn.prepareStatement(accountSql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            Long accountID = null;
            if (rs.next()) {
                accountID = rs.getLong("accountID");
            }
            rs.close();
            stmt.close();

            if (accountID == null) {
                String insertAccountSql = "INSERT INTO t_account (openid, createTime, status) VALUES (?, NOW(), 1)";
                stmt = conn.prepareStatement(insertAccountSql, java.sql.Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, TEST_OPENID);
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    accountID = rs.getLong(1);
                }
                rs.close();
                stmt.close();
            }

            String deleteCharSql = "DELETE FROM t_character WHERE accountID = ?";
            stmt = conn.prepareStatement(deleteCharSql);
            stmt.setLong(1, accountID);
            stmt.executeUpdate();
            stmt.close();

            String insertCharSql = "INSERT INTO t_character (charguid, accountID, name, job, level, createTime, status) VALUES (?, ?, ?, ?, ?, NOW(), 1)";
            stmt = conn.prepareStatement(insertCharSql);
            stmt.setLong(1, 1001);
            stmt.setLong(2, accountID);
            stmt.setString(3, "TestPlayer1");
            stmt.setInt(4, 1);
            stmt.setInt(5, 10);
            stmt.executeUpdate();
            stmt.close();

            conn.commit();
            authKey = "test_authkey_005";
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
            String sql = "DELETE FROM t_character WHERE accountID = (SELECT accountID FROM t_account WHERE openid = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            stmt.close();

            sql = "DELETE FROM t_account WHERE openid = ?";
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
