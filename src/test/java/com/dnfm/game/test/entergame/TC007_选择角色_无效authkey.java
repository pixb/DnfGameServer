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

public class TC007_选择角色_无效authkey {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 20001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_007";

    private Socket socket;
    private String authKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC007: 选择角色_无效authkey ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC007 测试结束 ==========");
    }

    @Test
    public void testSelectInvalidAuthkey() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造进入城镇请求（无效authkey）");
        REQ_ENTER_TO_TOWN req = new REQ_ENTER_TO_TOWN();
        req.setAuthkey("invalid_authkey_007");
        req.setTown(1);
        req.setArea(1);
        req.setPosx(0);
        req.setPosy(0);
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

        System.out.println("\n步骤7: 验证选择角色失败");
        System.out.println("error: " + res.getError());

        assertNotEquals("选择角色应该失败，但error为0", Integer.valueOf(0), res.getError());
        System.out.println("错误码验证通过，选择角色失败");
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

            conn.commit();
            authKey = "test_authkey_007";
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
            String sql = "DELETE FROM t_account WHERE openid = ?";
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
