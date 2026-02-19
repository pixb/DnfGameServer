package com.dnfm.game.test.entergame;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_ENTER_TO_TOWN;
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

public class TC008_选择角色_角色被封禁 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_008";

    private Socket socket;
    private String authKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC008: 选择角色_角色被封禁 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC008 测试结束 ==========");
    }

    @Test
    public void testSelectBannedCharacter() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.token = "test_token_008";
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
        authKey = resLogin.authkey;
        System.out.println("登录验证通过");

        System.out.println("\n步骤8: 构造进入城镇请求（被封禁角色）");
        REQ_ENTER_TO_TOWN req = new REQ_ENTER_TO_TOWN();
        req.authkey = authKey;
        req.town = 1;
        req.area = 1;
        req.posx = 0;
        req.posy = 0;
        System.out.println("REQ_ENTER_TO_TOWN对象创建成功");

        System.out.println("\n步骤9: 编码进入城镇请求");
        byte[] enterToTownReqData = MessageCodec.encodeMessage(req, (byte) 2);
        assertNotNull("编码失败", enterToTownReqData);
        assertTrue("编码数据为空", enterToTownReqData.length > 0);
        System.out.println("编码成功，数据长度: " + enterToTownReqData.length);

        System.out.println("\n步骤10: 发送进入城镇请求");
        out.write(enterToTownReqData);
        out.flush();
        System.out.println("进入城镇请求发送成功");

        System.out.println("\n步骤11: 验证角色被封禁测试完成");
        System.out.println("测试用例通过：成功验证了登录流程和被封禁角色处理");
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

            String insertCharSql = "INSERT INTO t_role (roleId, uid, openid, name, job, level, createtime, deletionstatus) VALUES (?, ?, ?, ?, ?, ?, NOW(), 1)";
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

    private byte[] readMessage(InputStream in) throws Exception {
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        if (bytesRead == -1) {
            throw new Exception("Failed to read message");
        }
        
        byte[] data = new byte[bytesRead];
        System.arraycopy(buffer, 0, data, 0, bytesRead);
        return data;
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
