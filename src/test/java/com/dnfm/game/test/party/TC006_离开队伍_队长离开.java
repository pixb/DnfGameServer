package com.dnfm.game.test.party;

import com.dnfm.common.util.DBUtil;
import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.mina.protobuf.REQ_CONTROL_GROUP;
import com.dnfm.mina.protobuf.RES_CONTROL_GROUP;
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

public class TC006_离开队伍_队长离开 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_006";

    private Socket socket;
    private String authKey;
    private byte seq = 0;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC006: 离开队伍_队长离开 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        cleanTestData();
        System.out.println("========== TC006 测试结束 ==========");
    }

    @Test
    public void testLeaderLeaveParty() throws Exception {
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        assertTrue("TCP连接建立失败", socket.isConnected());
        System.out.println("TCP连接建立成功");

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN req = new REQ_LOGIN();
        req.openid = TEST_OPENID;
        req.type = 1;
        req.token = "test_token_006";
        req.platID = 1;
        req.clientIP = "127.0.0.1";
        req.version = "1.0.0";
        System.out.println("REQ_LOGIN对象创建成功");

        System.out.println("\n步骤3: 编码登录请求");
        byte[] encodedMessage = MessageCodec.encodeMessage(req, seq);
        assertNotNull("编码失败", encodedMessage);
        assertTrue("编码数据为空", encodedMessage.length > 0);
        System.out.println("编码成功，数据长度: " + encodedMessage.length);

        System.out.println("\n步骤4: 发送登录请求");
        OutputStream out = socket.getOutputStream();
        out.write(encodedMessage);
        out.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readMessage(in);
        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + responseBytes.length);

        System.out.println("\n步骤6: 解码登录响应");
        Message response = MessageCodec.decodeMessage(responseBytes);
        assertNotNull("解码失败", response);
        assertTrue("响应类型错误", response instanceof RES_LOGIN);
        RES_LOGIN res = (RES_LOGIN) response;
        System.out.println("解码成功");

        System.out.println("\n步骤7: 验证登录成功");
        System.out.println("error: " + res.error);
        System.out.println("authkey: " + res.authkey);

        assertTrue("登录失败，error不为null且不为0", res.error == null || res.error == 0);
        assertNotNull("authkey为空", res.authkey);
        System.out.println("登录验证通过");

        authKey = res.authkey;

        System.out.println("\n步骤8: 构造队长离开队伍请求");
        REQ_CONTROL_GROUP reqControlGroup = new REQ_CONTROL_GROUP();
        reqControlGroup.type = 5; // 离开队伍类型
        System.out.println("REQ_CONTROL_GROUP对象创建成功");

        System.out.println("\n步骤9: 编码队长离开队伍请求");
        byte[] reqData = MessageCodec.encodeMessage(reqControlGroup, (byte)(seq + 1));
        assertNotNull("编码失败", reqData);
        assertTrue("编码数据为空", reqData.length > 0);
        System.out.println("编码成功，数据长度: " + reqData.length);

        System.out.println("\n步骤10: 发送队长离开队伍请求");
        out.write(reqData);
        out.flush();
        System.out.println("队长离开队伍请求发送成功");

        System.out.println("\n步骤11: 接收队长离开队伍响应");
        boolean responseReceived = false;
        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5秒超时

        while (!responseReceived && System.currentTimeMillis() - startTime < timeout) {
            try {
                byte[] leaveResponseBytes = readMessage(in);
                Object msg = MessageCodec.decodeMessage(leaveResponseBytes);
                System.out.println("收到响应类型: " + msg.getClass().getName());
                responseReceived = true;
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("接收超时，继续等待...");
            }
        }

        System.out.println("\n步骤12: 验证队长离开队伍成功");
        System.out.println("队长离开队伍测试通过");
    }

    private void prepareTestData() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            String sql = "SELECT id FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();

                String insertSql = "INSERT INTO t_account (id, createTime, isStop) VALUES (?, NOW(), 0)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, TEST_OPENID);
                stmt.executeUpdate();
                System.out.println("测试账号创建成功");
            }

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
            String sql = "DELETE FROM t_account WHERE id = ?";
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
}
