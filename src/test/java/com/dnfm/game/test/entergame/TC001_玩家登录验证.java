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

public class TC001_玩家登录验证 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 20001;
    private static final int CONNECT_TIMEOUT = 5000;

    private Socket socket;
    private String authKey;
    private String accountKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC001: 玩家登录验证 ==========");
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC001 测试结束 ==========");
    }

    @Test
    public void testPlayerLogin() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid("test_openid_001");
        req.setType(1);
        req.setToken("test_token_001");
        req.setPlatID(1001);
        req.setClientIP("127.0.0.1");
        req.setVersion("1.0.0");
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

        System.out.println("\n步骤7: 验证登录结果");
        System.out.println("error: " + res.getError());
        System.out.println("authkey: " + res.getAuthkey());
        System.out.println("accountkey: " + res.getAccountkey());
        System.out.println("servertime: " + res.getServertime());
        System.out.println("localtime: " + res.getLocaltime());

        assertEquals("登录失败，error不为0", Integer.valueOf(0), res.getError());
        assertNotNull("authkey为空", res.getAuthkey());
        assertTrue("authkey长度不合理", res.getAuthkey().length() > 0);
        assertNotNull("accountkey为空", res.getAccountkey());
        assertNotNull("servertime为空", res.getServertime());
        assertTrue("servertime无效", res.getServertime() > 0);
        assertNotNull("localtime为空", res.getLocaltime());
        System.out.println("登录验证通过");

        authKey = res.getAuthkey();
        accountKey = res.getAccountkey();

        System.out.println("\n步骤8: 数据库验证");
        verifyDatabase();
    }

    private void verifyDatabase() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM t_account WHERE openid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "test_openid_001");
            rs = stmt.executeQuery();

            assertTrue("账号不存在", rs.next());
            System.out.println("数据库验证通过，账号存在");

            String openid = rs.getString("openid");
            assertEquals("openid不匹配", "test_openid_001", openid);
            System.out.println("openid验证通过: " + openid);

        } finally {
            if (rs != null) rs.close();
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
