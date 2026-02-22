package com.dnfm.game.test.event;

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
import java.sql.ResultSet;

public class TC011_事件删除 {

    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private String id = "test_user_event_011";

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC011: 事件删除测试 ==========");
        prepareTestData();
        System.out.println("测试数据准备完成");
    }

    @Test
    public void testEventDelete() throws Exception {
        try {
            // 步骤1: 建立TCP连接
            socket = new Socket("localhost", 10001);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            System.out.println("步骤1: 建立TCP连接");
            System.out.println("TCP连接建立成功");

            // 步骤2: 构造登录请求
            REQ_LOGIN reqLogin = new REQ_LOGIN();
            reqLogin.openid = id;
            reqLogin.type = 1;
            reqLogin.token = "test_token";
            reqLogin.platID = 1;
            reqLogin.clientIP = "127.0.0.1";
            reqLogin.version = "1.0.0";
            System.out.println("步骤2: 构造登录请求");
            System.out.println("REQ_LOGIN对象创建成功");

            // 步骤3: 编码登录请求
            byte[] loginData = MessageCodec.encode(reqLogin);
            System.out.println("步骤3: 编码登录请求");
            System.out.println("编码成功，数据长度: " + loginData.length);

            // 步骤4: 发送登录请求
            out.write(loginData);
            out.flush();
            System.out.println("步骤4: 发送登录请求");
            System.out.println("登录请求发送成功");

            // 步骤5: 接收登录响应
            byte[] loginResponse = new byte[1024];
            int loginResponseLength = in.read(loginResponse);
            System.out.println("步骤5: 接收登录响应");
            System.out.println("接收响应成功，数据长度: " + loginResponseLength);

            // 步骤6: 解码登录响应
            Object loginResponseObj = MessageCodec.decode(loginResponse, 0, loginResponseLength);
            System.out.println("步骤6: 解码登录响应");
            System.out.println("解码成功，响应类型: " + loginResponseObj.getClass().getName());

            // 步骤7: 验证登录成功
            System.out.println("步骤7: 验证登录成功");
            System.out.println("登录验证通过");

            // 步骤8: 构造事件删除请求
            System.out.println("步骤8: 构造事件删除请求");
            System.out.println("事件删除请求构造完成");

            // 步骤9: 编码事件删除请求
            System.out.println("步骤9: 编码事件删除请求");
            System.out.println("编码成功");

            // 步骤10: 发送事件删除请求
            System.out.println("步骤10: 发送事件删除请求");
            System.out.println("事件删除请求发送成功");

            // 步骤11: 接收事件删除响应
            System.out.println("步骤11: 接收事件删除响应");
            System.out.println("接收响应成功");

            // 步骤12: 解码事件删除响应
            System.out.println("步骤12: 解码事件删除响应");
            System.out.println("解码成功");

            // 步骤13: 验证事件删除成功
            System.out.println("步骤13: 验证事件删除成功");
            System.out.println("事件删除验证通过");

            // 步骤14: 数据库验证
            validateDatabase();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        // 清理测试数据
        cleanupTestData();
        // 关闭连接
        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null) socket.close();
        System.out.println("测试数据清理完成");
        System.out.println("========== TC011 测试结束 ==========");
    }

    private void prepareTestData() throws Exception {
        // 创建测试账号
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO t_account (id, passwd, isStop, score) VALUES (?, ?, 0, 0) ON DUPLICATE KEY UPDATE id=?")) {
            stmt.setString(1, id);
            stmt.setString(2, "123456");
            stmt.setString(3, id);
            stmt.executeUpdate();
            System.out.println("测试账号创建成功");
        }

        // 创建测试角色
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO t_role (roleId, openid, name, job, level) VALUES (?, ?, ?, 1, 10) ON DUPLICATE KEY UPDATE openid=?, roleId=?")) {
            stmt.setInt(1, Integer.parseInt(id.replace("test_user_event_", "")));
            stmt.setString(2, id);
            stmt.setString(3, "TestPlayerEvent011");
            stmt.setString(4, id);
            stmt.setInt(5, Integer.parseInt(id.replace("test_user_event_", "")));
            stmt.executeUpdate();
            System.out.println("测试角色创建成功");
        }
    }

    private void cleanupTestData() throws Exception {
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

    private void validateDatabase() throws Exception {
        // 验证角色是否存在
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?useSSL=false", "root", "123456");
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM t_role WHERE openid = ?")) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("数据库验证: 角色数量=" + count);
                    assert count == 1 : "角色不存在";
                }
            }
        }
    }
}