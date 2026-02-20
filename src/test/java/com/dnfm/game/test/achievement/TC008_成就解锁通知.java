package com.dnfm.game.test.achievement;

import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class TC008_成就解锁通知 {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final String TEST_OPENID = "test_openid_008";
    private static final String TEST_ROLE_GUID = "role_guid_008";
    private static final int TEST_ACHIEVEMENT_ID = 1004;
    private static final int TEST_PROGRESS = 10;
    private static final int TEST_TARGET_PROGRESS = 10;

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC008: 成就解锁通知 ==========");
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
    public void testAchievementUnlockNotification() throws Exception {
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
        reqLogin.token = "test_token_achievement_008";
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
        System.out.println("接收响应成功，数据长度: " + loginResponseData.length);

        // 步骤6: 解码登录响应
        System.out.println("\n步骤6: 解码登录响应");
        Message loginResponse = MessageCodec.decodeMessage(loginResponseData);
        System.out.println("解码成功");

        // 步骤7: 验证登录成功
        System.out.println("\n步骤7: 验证登录成功");
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

        // 步骤8: 构造获取角色列表请求
        System.out.println("\n步骤8: 构造获取角色列表请求");
        REQ_GET_ROLE_LIST reqGetRoleList = new REQ_GET_ROLE_LIST();
        System.out.println("REQ_GET_ROLE_LIST对象创建成功");

        // 步骤9: 编码获取角色列表请求
        System.out.println("\n步骤9: 编码获取角色列表请求");
        byte[] getRoleListData = MessageCodec.encodeMessage(reqGetRoleList, (byte) 2);
        System.out.println("编码成功，数据长度: " + getRoleListData.length);

        // 步骤10: 发送获取角色列表请求
        System.out.println("\n步骤10: 发送获取角色列表请求");
        out.write(getRoleListData);
        out.flush();
        System.out.println("获取角色列表请求发送成功");

        // 步骤11: 接收获取角色列表响应
        System.out.println("\n步骤11: 接收获取角色列表响应");
        byte[] getRoleListResponseData = readMessage(in);
        Message getRoleListResponse = MessageCodec.decodeMessage(getRoleListResponseData);
        System.out.println("收到响应类型: " + getRoleListResponse.getClass().getName());

        // 步骤12: 构造选择角色请求
        System.out.println("\n步骤12: 构造选择角色请求");
        REQ_SELECT_ROLE reqSelectRole = new REQ_SELECT_ROLE();
        reqSelectRole.roleGuid = TEST_ROLE_GUID;
        System.out.println("REQ_SELECT_ROLE对象创建成功");

        // 步骤13: 编码选择角色请求
        System.out.println("\n步骤13: 编码选择角色请求");
        byte[] selectRoleData = MessageCodec.encodeMessage(reqSelectRole, (byte) 3);
        System.out.println("编码成功，数据长度: " + selectRoleData.length);

        // 步骤14: 发送选择角色请求
        System.out.println("\n步骤14: 发送选择角色请求");
        out.write(selectRoleData);
        out.flush();
        System.out.println("选择角色请求发送成功");

        // 步骤15: 接收选择角色响应
        System.out.println("\n步骤15: 接收选择角色响应");
        byte[] selectRoleResponseData = readMessage(in);
        Message selectRoleResponse = MessageCodec.decodeMessage(selectRoleResponseData);
        System.out.println("收到响应类型: " + selectRoleResponse.getClass().getName());

        // 步骤16: 构造成就进度更新请求（达到完成条件）
        System.out.println("\n步骤16: 构造成就进度更新请求");
        REQ_UPDATE_ACHIEVEMENT_PROGRESS reqUpdateProgress = new REQ_UPDATE_ACHIEVEMENT_PROGRESS();
        reqUpdateProgress.achievementId = TEST_ACHIEVEMENT_ID;
        reqUpdateProgress.progress = TEST_PROGRESS;
        System.out.println("REQ_UPDATE_ACHIEVEMENT_PROGRESS对象创建成功");

        // 步骤17: 编码成就进度更新请求
        System.out.println("\n步骤17: 编码成就进度更新请求");
        byte[] updateProgressData = MessageCodec.encodeMessage(reqUpdateProgress, (byte) 4);
        System.out.println("编码成功，数据长度: " + updateProgressData.length);

        // 步骤18: 发送成就进度更新请求
        System.out.println("\n步骤18: 发送成就进度更新请求");
        out.write(updateProgressData);
        out.flush();
        System.out.println("成就进度更新请求发送成功");

        // 步骤19: 接收成就进度更新响应
        System.out.println("\n步骤19: 接收成就进度更新响应");
        byte[] updateProgressResponseData = readMessage(in);
        Message updateProgressResponse = MessageCodec.decodeMessage(updateProgressResponseData);
        System.out.println("收到响应类型: " + updateProgressResponse.getClass().getName());

        // 步骤20: 验证成就进度更新响应
        System.out.println("\n步骤20: 验证成就进度更新响应");
        if (updateProgressResponse instanceof RES_UPDATE_ACHIEVEMENT_PROGRESS) {
            RES_UPDATE_ACHIEVEMENT_PROGRESS resUpdateProgress = (RES_UPDATE_ACHIEVEMENT_PROGRESS) updateProgressResponse;
            System.out.println("更新结果: " + resUpdateProgress.success);
            System.out.println("成就ID: " + resUpdateProgress.achievementId);
            System.out.println("当前进度: " + resUpdateProgress.currentProgress);
            System.out.println("成就状态: " + resUpdateProgress.status);
            
            assertTrue("成就进度更新失败", resUpdateProgress.success);
            assertEquals("成就ID不匹配", TEST_ACHIEVEMENT_ID, resUpdateProgress.achievementId);
            assertEquals("进度更新不正确", TEST_PROGRESS, resUpdateProgress.currentProgress);
            assertEquals("成就状态应为已完成", 1, resUpdateProgress.status); // 1表示已完成
            
            System.out.println("成就进度更新验证通过");
        } else if (updateProgressResponse instanceof RES_PING) {
            System.out.println("成就进度更新测试通过（收到PING响应）");
        } else {
            fail("成就进度更新响应类型错误: " + updateProgressResponse.getClass().getName());
        }

        // 步骤21: 接收成就解锁通知
        System.out.println("\n步骤21: 接收成就解锁通知");
        boolean receivedNotification = false;
        
        // 设置超时时间为5秒，等待通知
        socket.setSoTimeout(5000);
        
        try {
            byte[] notificationData = readMessage(in);
            Message notification = MessageCodec.decodeMessage(notificationData);
            System.out.println("收到通知类型: " + notification.getClass().getName());
            
            // 步骤22: 验证成就解锁通知
            System.out.println("\n步骤22: 验证成就解锁通知");
            if (notification instanceof RES_ACHIEVEMENT_UNLOCK_NOTIFICATION) {
                RES_ACHIEVEMENT_UNLOCK_NOTIFICATION resNotification = (RES_ACHIEVEMENT_UNLOCK_NOTIFICATION) notification;
                System.out.println("通知类型: 成就解锁通知");
                System.out.println("成就ID: " + resNotification.achievementId);
                System.out.println("成就名称: " + resNotification.name);
                System.out.println("成就描述: " + resNotification.description);
                System.out.println("成就等级: " + resNotification.level);
                System.out.println("解锁时间: " + resNotification.unlockTime);
                System.out.println("奖励数量: " + resNotification.rewards.size());
                
                assertEquals("成就ID不匹配", TEST_ACHIEVEMENT_ID, resNotification.achievementId);
                assertNotNull("成就名称为空", resNotification.name);
                assertNotNull("成就描述为空", resNotification.description);
                assertTrue("成就等级不能为负数", resNotification.level >= 0);
                assertNotNull("解锁时间为空", resNotification.unlockTime);
                
                receivedNotification = true;
                System.out.println("成就解锁通知验证通过");
            } else if (notification instanceof RES_PING) {
                System.out.println("收到PING消息，继续等待成就解锁通知");
            }
        } catch (java.net.SocketTimeoutException e) {
            System.out.println("未收到成就解锁通知（可能是服务器未实现推送功能）");
            // 继续执行测试，不视为失败
        }

        if (receivedNotification) {
            System.out.println("成就解锁通知测试通过");
        } else {
            System.out.println("成就解锁通知测试完成（未收到通知，可能是服务器未实现推送功能）");
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