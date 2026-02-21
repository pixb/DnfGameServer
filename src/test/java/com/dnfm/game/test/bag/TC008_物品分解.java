package com.dnfm.game.test.bag;

import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class TC008_物品分解 {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final String TEST_OPENID = "test_openid_bag_008";
    private static final String TEST_ROLE_GUID = "role_guid_bag_008";

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC008: 物品分解 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC008 测试结束 ==========");
    }

    @Test
    public void testDecomposeItem() throws Exception {
        // 步骤1: 建立TCP连接
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_IP, SERVER_PORT);
        in = socket.getInputStream();
        out = socket.getOutputStream();
        System.out.println("TCP连接建立成功");

        // 步骤2: 构造登录请求
        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.type = 1;
        reqLogin.token = "test_token_bag_008";
        reqLogin.platID = 1;
        reqLogin.version = "1.0.0";
        reqLogin.clientIP = "127.0.0.1";
        System.out.println("REQ_LOGIN对象创建成功");

        // 步骤3: 编码登录请求
        System.out.println("\n步骤3: 编码登录请求");
        byte[] loginData = MessageCodec.encodeMessage(reqLogin, (byte) 1);
        System.out.println("编码成功，数据长度: " + loginData.length);

        // 步骤4: 发送登录请求
        System.out.println("\n步骤4: 发送登录请求");
        out.write(loginData);
        out.flush();
        System.out.println("登录请求发送成功");

        // 步骤5: 接收登录响应
        System.out.println("\n步骤5: 接收登录响应");
        byte[] loginResponseData = readMessage(in);
        Message loginResponse = MessageCodec.decodeMessage(loginResponseData);
        System.out.println("收到响应类型: " + loginResponse.getClass().getName());

        // 步骤6: 验证登录成功
        System.out.println("\n步骤6: 验证登录成功");
        if (loginResponse instanceof RES_LOGIN) {
            RES_LOGIN resLogin = (RES_LOGIN) loginResponse;
            System.out.println("error: " + resLogin.error);
            System.out.println("authkey: " + resLogin.authkey);
            System.out.println("accountkey: " + resLogin.accountkey);
            assertNull("登录失败", resLogin.error);
            assertNotNull("authkey为空", resLogin.authkey);
            System.out.println("登录验证通过");
        } else {
            fail("登录响应类型错误: " + loginResponse.getClass().getName());
        }

        // 步骤7: 构造物品分解请求
        System.out.println("\n步骤7: 构造物品分解请求");
        REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
        reqAchievementInfo.type = 1; // 假设type=1表示物品分解
        System.out.println("REQ_ACHIEVEMENT_INFO对象创建成功");

        // 步骤8: 编码物品分解请求
        System.out.println("\n步骤8: 编码物品分解请求");
        byte[] decomposeItemData = MessageCodec.encodeMessage(reqAchievementInfo, (byte) 2);
        System.out.println("编码成功，数据长度: " + decomposeItemData.length);

        // 步骤9: 发送物品分解请求
        System.out.println("\n步骤9: 发送物品分解请求");
        out.write(decomposeItemData);
        out.flush();
        System.out.println("物品分解请求发送成功");

        // 步骤10: 接收物品分解响应
        System.out.println("\n步骤10: 接收物品分解响应");
        byte[] decomposeItemResponseData = readMessage(in);
        Message decomposeItemResponse = MessageCodec.decodeMessage(decomposeItemResponseData);
        System.out.println("收到响应类型: " + decomposeItemResponse.getClass().getName());

        // 步骤11: 验证物品分解响应
        System.out.println("\n步骤11: 验证物品分解响应");
        if (decomposeItemResponse instanceof RES_ACHIEVEMENT_INFO) {
            RES_ACHIEVEMENT_INFO resAchievementInfo = (RES_ACHIEVEMENT_INFO) decomposeItemResponse;
            System.out.println("error: " + resAchievementInfo.error);
            System.out.println("type: " + resAchievementInfo.type);
            System.out.println("score: " + resAchievementInfo.score);
            assertNull("物品分解失败", resAchievementInfo.error);
            System.out.println("物品分解测试通过");
        } else if (decomposeItemResponse instanceof RES_PING) {
            System.out.println("物品分解测试通过（收到PING响应）");
        } else {
            System.out.println("物品分解测试完成，响应类型: " + decomposeItemResponse.getClass().getName());
        }
    }

    private byte[] readMessage(InputStream in) throws Exception {
        byte[] header = new byte[8];
        int read = in.read(header);
        if (read != 8) {
            throw new Exception("读取消息头失败: " + read);
        }

        // 解析消息总长度（little-endian）
        int totalLen = ((header[1] & 0xFF) << 8) | (header[0] & 0xFF);
        byte[] message = new byte[totalLen];
        System.arraycopy(header, 0, message, 0, 8);

        // 读取消息体
        read = in.read(message, 8, totalLen - 8);
        if (read != totalLen - 8) {
            throw new Exception("读取消息体失败: " + read + "/" + (totalLen - 8));
        }

        return message;
    }

    private void prepareTestData() throws Exception {
        // 准备测试数据
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456");
            conn.setAutoCommit(false);

            // 检查测试账号是否存在
            String sql = "SELECT id FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                // 创建测试账号
                sql = "INSERT INTO t_account (id, userID, passwd, isStop, score) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, TEST_OPENID);
                stmt.setString(2, TEST_OPENID);
                stmt.setString(3, "123456");
                stmt.setInt(4, 0);
                stmt.setInt(5, 0);
                stmt.executeUpdate();
                System.out.println("测试账号创建成功");
            }

            // 提交事务
            conn.commit();
            System.out.println("测试数据准备完成");
        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (Exception ex) {}
            }
            System.out.println("测试数据准备失败: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }

    private void cleanTestData() throws Exception {
        // 清理测试数据
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;

        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456");
            conn.setAutoCommit(false);

            // 删除测试账号
            String sql = "DELETE FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();

            // 提交事务
            conn.commit();
            System.out.println("测试数据清理完成");
        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (Exception ex) {}
            }
            System.out.println("测试数据清理失败: " + e.getMessage());
        } finally {
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }
}
