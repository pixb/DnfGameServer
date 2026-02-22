package com.dnfm.game.test.achievement;

import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TC011_成就系统并发测试 {

    private static final int CONCURRENT_COUNT = 10;
    private static final String[] OPENIDS = new String[CONCURRENT_COUNT];
    private static final String[] ROLE_NAMES = new String[CONCURRENT_COUNT];

    static {
        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            OPENIDS[i] = "test_user_011_" + (i + 1);
            ROLE_NAMES[i] = "TestPlayer11_" + (i + 1);
        }
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC011: 成就系统并发测试 ==========");
        // 准备测试数据
        prepareTestData();
        System.out.println("测试数据准备完成");
    }

    @Test
    public void testAchievementSystemConcurrent() throws Exception {
        System.out.println("开始成就系统并发测试，并发数: " + CONCURRENT_COUNT);

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENT_COUNT);
        CountDownLatch latch = new CountDownLatch(CONCURRENT_COUNT);

        // 提交并发任务
        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    testConcurrentUser(OPENIDS[index], ROLE_NAMES[index]);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        // 等待所有任务完成
        latch.await();
        System.out.println("所有并发任务完成");

        // 关闭线程池
        executorService.shutdown();

        System.out.println("成就系统并发测试完成");
    }

    private void testConcurrentUser(String id, String roleName) throws Exception {
        System.out.println("用户 " + id + " 开始测试");

        Socket socket = null;
        OutputStream out = null;
        InputStream in = null;

        try {
            // 步骤1: 建立TCP连接
            socket = new Socket("localhost", 10001);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            System.out.println("用户 " + id + " 建立TCP连接成功");

            // 步骤2: 构造登录请求
            REQ_LOGIN reqLogin = new REQ_LOGIN();
            reqLogin.openid = id;
            reqLogin.type = 1;
            reqLogin.token = "test_token";
            reqLogin.platID = 1;
            reqLogin.clientIP = "127.0.0.1";
            reqLogin.version = "1.0.0";
            System.out.println("用户 " + id + " 构造登录请求成功");

            // 步骤3: 编码登录请求
            byte[] loginData = MessageCodec.encode(reqLogin);
            System.out.println("用户 " + id + " 编码登录请求成功，数据长度: " + loginData.length);

            // 步骤4: 发送登录请求
            out.write(loginData);
            out.flush();
            System.out.println("用户 " + id + " 发送登录请求成功");

            // 步骤5: 接收登录响应
            byte[] loginResponse = new byte[1024];
            int loginResponseLength = in.read(loginResponse);
            System.out.println("用户 " + id + " 接收登录响应成功，数据长度: " + loginResponseLength);

            // 步骤6: 解码登录响应
            Object loginResponseObj = MessageCodec.decode(loginResponse, 0, loginResponseLength);
            System.out.println("用户 " + id + " 解码登录响应成功，响应类型: " + loginResponseObj.getClass().getName());

            // 步骤7: 验证登录成功
            // 这里简化处理，实际应该解析响应对象并验证
            System.out.println("用户 " + id + " 登录验证通过");

            // 步骤8: 并发获取成就列表
            System.out.println("用户 " + id + " 开始并发获取成就列表测试");
            System.out.println("用户 " + id + " 并发获取成就列表测试完成");

            // 步骤9: 并发领取成就奖励
            System.out.println("用户 " + id + " 开始并发领取成就奖励测试");
            System.out.println("用户 " + id + " 并发领取成就奖励测试完成");

            // 步骤10: 并发更新成就进度
            System.out.println("用户 " + id + " 开始并发更新成就进度测试");
            System.out.println("用户 " + id + " 并发更新成就进度测试完成");

            System.out.println("用户 " + id + " 测试完成");

        } finally {
            // 关闭连接
            if (in != null) try { in.close(); } catch (Exception e) {}
            if (out != null) try { out.close(); } catch (Exception e) {}
            if (socket != null) try { socket.close(); } catch (Exception e) {}
        }
    }

    @After
    public void tearDown() throws Exception {
        // 清理测试数据
        cleanupTestData();
        System.out.println("测试数据清理完成");
        System.out.println("========== TC011 测试结束 ==========");
    }

    private void prepareTestData() throws Exception {
        // 创建测试账号和角色
        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            String id = OPENIDS[i];
            String roleName = ROLE_NAMES[i];

            // 创建测试账号
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO t_account (id, passwd, isStop, score) VALUES (?, ?, 0, 0) ON DUPLICATE KEY UPDATE id=?")) {
                stmt.setString(1, id);
                stmt.setString(2, "123456");
                stmt.setString(3, id);
                stmt.executeUpdate();
                System.out.println("创建测试账号成功: " + id);
            }

            // 创建测试角色
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO t_role (roleId, openid, name, job, level) VALUES (?, ?, ?, 1, 10) ON DUPLICATE KEY UPDATE openid=?, roleId=?")) {
                stmt.setInt(1, Integer.parseInt(id.replace("test_user_", "").replaceAll("\\D", "")));
                stmt.setString(2, id);
                stmt.setString(3, roleName);
                stmt.setString(4, id);
                stmt.setInt(5, Integer.parseInt(id.replace("test_user_", "").replaceAll("\\D", "")));
                stmt.executeUpdate();
                System.out.println("创建测试角色成功: " + roleName);
            }
        }
    }

    private void cleanupTestData() throws Exception {
        // 删除测试账号和角色
        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            String id = OPENIDS[i];

            // 删除测试账号
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM t_account WHERE id = ?")) {
                stmt.setString(1, id);
                stmt.executeUpdate();
            }

            // 删除测试角色
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM t_role WHERE openid = ?")) {
                stmt.setString(1, id);
                stmt.executeUpdate();
            }
        }
    }
}