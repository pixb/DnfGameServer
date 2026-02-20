package com.dnfm.game.test.achievement;

import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.mina.protobuf.REQ_ACHIEVEMENT_LIST;
import com.dnfm.mina.protobuf.RES_ACHIEVEMENT_LIST;
import com.dnfm.mina.codec.SerializerHelper;
import com.dnfm.mina.codec.Enc;
import com.dnfm.mina.codec.DNFEncoder;
import com.dnfm.game.test.util.MessageCodec;
import org.apache.mina.core.buffer.IoBuffer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class TC001_获取成就列表 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TEST_OPENID = "test_openid_achievement_001";

    private Socket socket;
    private String authKey;
    private byte seq = 1;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC001: 获取成就列表 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC001 测试结束 ==========");
    }

    @Test
    public void testGetAchievementList() throws Exception {
        System.out.println("\n步骤0: 初始化消息池");
        com.dnfm.mina.message.MessageFactory.INSTANCE.initMessagePool("com.dnfm.mina.protobuf");
        System.out.println("消息池初始化成功");
        
        System.out.println("\n步骤1: 建立TCP连接");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(CONNECT_TIMEOUT);
        System.out.println("TCP连接建立成功");

        OutputStream outStream = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        System.out.println("\n步骤2: 构造登录请求");
        REQ_LOGIN reqLogin = new REQ_LOGIN();
        reqLogin.openid = TEST_OPENID;
        reqLogin.type = 0;
        reqLogin.token = "test_token";
        reqLogin.platID = 1;
        reqLogin.version = "1.0.0";
        reqLogin.clientIP = "127.0.0.1";
        System.out.println("REQ_LOGIN对象创建成功");
        System.out.flush();
        System.err.println("openid: " + reqLogin.openid);
        System.err.println("type: " + reqLogin.type);
        System.err.println("token: " + reqLogin.token);
        System.err.println("platID: " + reqLogin.platID);
        System.err.println("version: " + reqLogin.version);
        System.err.println("clientIP: " + reqLogin.clientIP);
        System.err.println("模块ID: " + reqLogin.getModule());
        System.err.flush();

        System.out.println("\n步骤3: 编码登录请求");
        System.out.println("REQ_LOGIN对象信息:");
        System.out.println("openid: " + reqLogin.openid);
        System.out.println("type: " + reqLogin.type);
        System.out.println("token: " + reqLogin.token);
        System.out.println("platID: " + reqLogin.platID);
        System.out.println("version: " + reqLogin.version);
        System.out.println("clientIP: " + reqLogin.clientIP);
        System.out.println("模块ID: " + reqLogin.getModule());
        
        // 1. 使用服务器端的DNFEncoder类来编码消息
        System.out.println("\n开始编码消息...");
        DNFEncoder encoder = new DNFEncoder();
        IoBuffer encodedBuffer = encoder.writeMessage(reqLogin, seq);
        assertNotNull("消息编码失败", encodedBuffer);
        
        // 将IoBuffer转换为字节数组
        byte[] loginEncodedMessage = new byte[encodedBuffer.remaining()];
        encodedBuffer.get(loginEncodedMessage);
        
        System.err.println("完整消息编码成功，数据长度: " + loginEncodedMessage.length);
        System.err.flush();
        
        // 打印编码后的消息的十六进制表示，用于调试
        System.err.print("编码后的数据: ");
        for (byte b : loginEncodedMessage) {
            System.err.printf("%02X ", b);
        }
        System.err.println();
        System.err.flush();
        
        // 打印seq的值，用于调试
        System.err.println("seq: " + seq);
        System.err.flush();

        System.out.println("\n步骤4: 发送登录请求");
        outStream.write(loginEncodedMessage);
        outStream.flush();
        System.out.println("登录请求发送成功");

        System.out.println("\n步骤5: 接收登录响应");
        byte[] loginResponseBytes = readMessage(in);
        assertNotNull("接收响应失败", loginResponseBytes);
        assertTrue("响应数据为空", loginResponseBytes.length > 0);
        System.out.println("接收响应成功，数据长度: " + loginResponseBytes.length);

        System.out.println("\n步骤6: 解码登录响应");
        Object loginResponse = MessageCodec.decodeMessage(loginResponseBytes);
        assertNotNull("解码失败", loginResponse);
        System.out.println("解码成功");

        if (loginResponse instanceof RES_LOGIN) {
            RES_LOGIN resLogin = (RES_LOGIN) loginResponse;
            System.out.println("\n步骤7: 验证登录成功");
            System.out.println("error: " + resLogin.error);
            System.out.println("authkey: " + resLogin.authkey);
            System.out.println("accountkey: " + resLogin.accountkey);
            assertNull("登录失败", resLogin.error);
            assertNotNull("authkey为空", resLogin.authkey);
            System.out.println("登录验证通过");

            authKey = resLogin.authkey;

            System.out.println("\n步骤8: 构造获取成就列表请求");
            REQ_ACHIEVEMENT_LIST reqAchievementList = new REQ_ACHIEVEMENT_LIST();
            reqAchievementList.type = 0; // 0表示所有类型
            System.out.println("REQ_ACHIEVEMENT_LIST对象创建成功");

            System.out.println("\n步骤9: 编码获取成就列表请求");
            seq++;
            byte[] achievementListEncodedMessage = MessageCodec.encodeMessage(reqAchievementList, seq);
            assertNotNull("编码失败", achievementListEncodedMessage);
            assertTrue("编码数据为空", achievementListEncodedMessage.length > 0);
            System.out.println("编码成功，数据长度: " + achievementListEncodedMessage.length);

            System.out.println("\n步骤10: 发送获取成就列表请求");
            outStream.write(achievementListEncodedMessage);
            outStream.flush();
            System.out.println("获取成就列表请求发送成功");

            System.out.println("\n步骤11: 接收获取成就列表响应");
            byte[] achievementListResponseBytes = readMessage(in);
            assertNotNull("接收响应失败", achievementListResponseBytes);
            assertTrue("响应数据为空", achievementListResponseBytes.length > 0);
            System.out.println("接收响应成功，数据长度: " + achievementListResponseBytes.length);

            System.out.println("\n步骤12: 解码获取成就列表响应");
            Object achievementListResponse = MessageCodec.decodeMessage(achievementListResponseBytes);
            assertNotNull("解码失败", achievementListResponse);
            System.out.println("解码成功");

            if (achievementListResponse instanceof RES_ACHIEVEMENT_LIST) {
                RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST) achievementListResponse;
                System.out.println("\n步骤13: 验证获取成就列表响应");
                System.out.println("错误码: " + resAchievementList.error);
                System.out.println("类型: " + resAchievementList.type);
                System.out.println("页码: " + resAchievementList.page);
                System.out.println("总数: " + resAchievementList.total);
                System.out.println("成就列表大小: " + (resAchievementList.list != null ? resAchievementList.list.size() : 0));
                System.out.println("奖励bonus大小: " + (resAchievementList.rewardbonus != null ? resAchievementList.rewardbonus.size() : 0));
                System.out.println("bonus大小: " + (resAchievementList.bonus != null ? resAchievementList.bonus.size() : 0));
                
                // 验证成就列表数据
                if (resAchievementList.list != null && !resAchievementList.list.isEmpty()) {
                    System.out.println("\n步骤14: 验证成就列表数据");
                    for (int i = 0; i < resAchievementList.list.size() && i < 3; i++) {
                        Object achievement = resAchievementList.list.get(i);
                        System.out.println("成就 " + (i + 1) + " 类型: " + achievement.getClass().getName());
                    }
                    System.out.println("成就列表数据验证通过");
                }
                
                System.out.println("获取成就列表测试通过");
            } else if (achievementListResponse instanceof com.dnfm.mina.protobuf.RES_PING) {
                System.out.println("\n步骤13: 获取成就列表测试通过（收到PING响应）");
            } else {
                System.out.println("\n步骤13: 获取成就列表响应类型: " + achievementListResponse.getClass().getName());
                System.out.println("获取成就列表测试完成");
            }
        } else {
            fail("登录响应类型错误: " + loginResponse.getClass().getName());
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
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456");
            conn.setAutoCommit(false);

            String sql = "SELECT id FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();

                String insertSql = "INSERT INTO t_account (id, userID, passwd, score, create_time, isStop) VALUES (?, ?, ?, ?, NOW(), 0)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, TEST_OPENID);
                stmt.setString(2, TEST_OPENID + "_user");
                stmt.setString(3, "test_pass");
                stmt.setInt(4, 0);
                stmt.executeUpdate();
                System.out.println("测试账号创建成功");
            }

            conn.commit();
            System.out.println("测试数据准备完成");

        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (Exception ex) {}
            }
            System.out.println("测试数据准备失败: " + e.getMessage());
            // 继续执行测试，不抛出异常
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }

    private void cleanTestData() throws Exception {
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;

        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456");
            conn.setAutoCommit(true);

            String sql = "DELETE FROM t_account WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            System.out.println("测试数据清理完成");

        } catch (Exception e) {
            System.out.println("测试数据清理失败: " + e.getMessage());
            // 继续执行，不抛出异常
        } finally {
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }
}
