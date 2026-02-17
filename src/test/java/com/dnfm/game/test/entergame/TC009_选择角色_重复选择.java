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

public class TC009_选择角色_重复选择 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 20001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_009";

    private Socket socket;
    private String authKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC009: 选择角色_重复选择 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC009 测试结束 ==========");
    }

    @Test
    public void testSelectDuplicateCharacter() throws Exception {
        System.out.println("\n步骤1: 第一次选择角色");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        REQ_ENTER_TO_TOWN req1 = new REQ_ENTER_TO_TOWN();
        req1.setAuthkey(authKey);
        req1.setTown(1);
        req1.setArea(1);
        req1.setPosx(0);
        req1.setPosy(0);

        Codec<REQ_ENTER_TO_TOWN> reqCodec = ProtobufProxy.create(REQ_ENTER_TO_TOWN.class);
        byte[] reqBytes1 = reqCodec.encode(req1);

        OutputStream out1 = socket.getOutputStream();
        out1.write(reqBytes1);
        out1.flush();

        InputStream in1 = socket.getInputStream();
        byte[] responseBytes1 = readFully(in1);

        Codec<RES_ENTER_TO_TOWN> resCodec = ProtobufProxy.create(RES_ENTER_TO_TOWN.class);
        RES_ENTER_TO_TOWN res1 = resCodec.decode(responseBytes1);

        assertEquals("第一次选择角色失败", Integer.valueOf(0), res1.getError());
        System.out.println("第一次选择角色成功");

        socket.close();

        System.out.println("\n步骤2: 第二次选择角色（重复选择）");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);

        REQ_ENTER_TO_TOWN req2 = new REQ_ENTER_TO_TOWN();
        req2.setAuthkey(authKey);
        req2.setTown(1);
        req2.setArea(1);
        req2.setPosx(0);
        req2.setPosy(0);

        byte[] reqBytes2 = reqCodec.encode(req2);

        OutputStream out2 = socket.getOutputStream();
        out2.write(reqBytes2);
        out2.flush();

        InputStream in2 = socket.getInputStream();
        byte[] responseBytes2 = readFully(in2);

        RES_ENTER_TO_TOWN res2 = resCodec.decode(responseBytes2);

        System.out.println("error: " + res2.getError());

        System.out.println("\n步骤3: 验证重复选择结果");
        System.out.println("第二次选择角色结果: " + (res2.getError() == 0 ? "成功" : "失败"));
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
            authKey = "test_authkey_009";
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
