package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.common.util.DBUtil;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
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

public class TC002_玩家登录失败_无效openid {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 20001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String INVALID_OPENID = "invalid_openid_002";

    private Socket socket;

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

        System.out.println("\n步骤3: 序列化登录请求");
        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);
        assertNotNull("序列化失败", reqBytes);
        assertTrue("序列化数据为空", reqBytes.length > 0);
        System.out.println("序列化成功，数据长度: " + reqBytes.length);

        System.out.println("\n步骤4: 发送登录请求");
        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);
        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + responseBytes.length);

        System.out.println("\n步骤6: 反序列化登录响应");
        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);
        assertNotNull("反序列化失败", res);
        System.out.println("反序列化成功");

        System.out.println("\n步骤7: 验证登录失败");
        System.out.println("error: " + res.error);
        System.out.println("authkey: " + res.authkey);
        System.out.println("accountkey: " + res.accountkey);

        assertNotEquals("登录应该失败，但error为0", Integer.valueOf(0), res.error);
        System.out.println("错误码验证通过，登录失败");

        assertNull("authkey应该为null", res.authkey);
        System.out.println("authkey验证通过，为null");

        assertNull("accountkey应该为null", res.accountkey);
        System.out.println("accountkey验证通过，为null");

        System.out.println("\n步骤8: 数据库验证");
        verifyDatabase();
    }

    private void verifyDatabase() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM t_account WHERE openid = ?";
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
            String sql = "DELETE FROM t_account WHERE openid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, INVALID_OPENID);
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
