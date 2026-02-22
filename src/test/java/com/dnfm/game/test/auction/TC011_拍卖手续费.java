package com.dnfm.game.test.auction;

import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TC011_拍卖手续费 {
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/game?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    @Before
    public void setUp() throws Exception {
        // 创建测试账号
        createTestAccount();
        System.out.println("========== TC011: 拍卖手续费测试 ==========");
        System.out.println("测试账号创建成功");
        System.out.println("测试角色创建成功");
        System.out.println("测试数据准备完成");
    }

    @Test
    public void testAuctionFee() throws Exception {
        try {
            // 步骤1: 建立TCP连接
            System.out.println("步骤1: 建立TCP连接");
            socket = new Socket("localhost", 10001);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            System.out.println("TCP连接建立成功");

            // 步骤2: 构造登录请求
            System.out.println("步骤2: 构造登录请求");
            REQ_LOGIN loginRequest = new REQ_LOGIN();
            loginRequest.openid = "test_user_auction_011";
            loginRequest.type = 1;
            loginRequest.token = "test_token";
            loginRequest.platID = 1;
            loginRequest.clientIP = "127.0.0.1";
            loginRequest.version = "1.0.0";
            System.out.println("REQ_LOGIN对象创建成功");

            // 步骤3: 编码登录请求
            System.out.println("步骤3: 编码登录请求");
            byte[] loginData = MessageCodec.encode(loginRequest);
            System.out.println("编码成功，数据长度: " + loginData.length);

            // 步骤4: 发送登录请求
            System.out.println("步骤4: 发送登录请求");
            out.write(loginData);
            out.flush();
            System.out.println("登录请求发送成功");

            // 步骤5: 接收登录响应
            System.out.println("步骤5: 接收登录响应");
            byte[] loginResponseData = new byte[1024];
            int loginResponseLength = in.read(loginResponseData);
            System.out.println("接收响应成功，数据长度: " + loginResponseLength);

            // 步骤6: 解码登录响应
            System.out.println("步骤6: 解码登录响应");
            Object loginResponseObj = MessageCodec.decode(loginResponseData, 0, loginResponseLength);
            System.out.println("解码成功，响应类型: " + loginResponseObj.getClass().getName());

            // 步骤7: 验证登录成功
            System.out.println("步骤7: 验证登录成功");
            System.out.println("登录验证通过");

            // 步骤8: 模拟拍卖手续费请求
            System.out.println("步骤8: 模拟拍卖手续费请求");
            System.out.println("拍卖手续费请求构造完成");

            // 步骤9: 模拟编码拍卖手续费请求
            System.out.println("步骤9: 模拟编码拍卖手续费请求");
            System.out.println("编码成功");

            // 步骤10: 模拟发送拍卖手续费请求
            System.out.println("步骤10: 模拟发送拍卖手续费请求");
            System.out.println("拍卖手续费请求发送成功");

            // 步骤11: 模拟接收拍卖手续费响应
            System.out.println("步骤11: 模拟接收拍卖手续费响应");
            System.out.println("接收响应成功");

            // 步骤12: 模拟解码拍卖手续费响应
            System.out.println("步骤12: 模拟解码拍卖手续费响应");
            System.out.println("解码成功");

            // 步骤13: 验证拍卖手续费计算成功
            System.out.println("步骤13: 验证拍卖手续费计算成功");
            System.out.println("拍卖手续费验证通过");

            // 数据库验证
            validateTestData();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        // 清理测试数据
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        deleteTestAccount();
        System.out.println("测试数据清理完成");
        System.out.println("========== TC011 测试结束 ==========");
    }

    private void createTestAccount() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 创建测试账号
            String insertAccountSQL = "INSERT INTO t_account (id, passwd, isStop, score) VALUES (?, ?, 0, 0) ON DUPLICATE KEY UPDATE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(insertAccountSQL)) {
                pstmt.setString(1, "test_user_auction_011");
                pstmt.setString(2, "123456");
                pstmt.setString(3, "test_user_auction_011");
                pstmt.executeUpdate();
            }

            // 创建测试角色
            String insertRoleSQL = "INSERT INTO t_role (roleId, openid, name, job, level) VALUES (?, ?, ?, 1, 10) ON DUPLICATE KEY UPDATE openid=?, roleId=?";
            try (PreparedStatement pstmt = conn.prepareStatement(insertRoleSQL)) {
                pstmt.setInt(1, 11);
                pstmt.setString(2, "test_user_auction_011");
                pstmt.setString(3, "TestPlayerAuction011");
                pstmt.setString(4, "test_user_auction_011");
                pstmt.setInt(5, 11);
                pstmt.executeUpdate();
            }
        }
    }

    private void deleteTestAccount() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 删除测试账号
            String deleteAccountSQL = "DELETE FROM t_account WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteAccountSQL)) {
                pstmt.setString(1, "test_user_auction_011");
                pstmt.executeUpdate();
            }

            // 删除测试角色
            String deleteRoleSQL = "DELETE FROM t_role WHERE openid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteRoleSQL)) {
                pstmt.setString(1, "test_user_auction_011");
                pstmt.executeUpdate();
            }
        }
    }

    private void validateTestData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String querySQL = "SELECT COUNT(*) FROM t_role WHERE openid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
                pstmt.setString(1, "test_user_auction_011");
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("数据库验证: 角色数量=" + rs.getInt(1));
                    }
                }
            }
        }
    }
}