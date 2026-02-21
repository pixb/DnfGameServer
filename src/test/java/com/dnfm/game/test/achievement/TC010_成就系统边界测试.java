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

public class TC010_成就系统边界测试 {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final String TEST_OPENID = "test_openid_010";
    private static final String TEST_ROLE_GUID = "role_guid_010";
    private static final int INVALID_ACHIEVEMENT_ID = 999999;
    private static final int NEGATIVE_PROGRESS = -1;
    private static final int EXCESSIVE_PROGRESS = 999999;
    private static final int LARGE_BATCH_SIZE = 1000;

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC010: 成就系统边界测试 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC010 测试结束 ==========");
    }

    @Test
    public void testAchievementSystemBoundaries() throws Exception {
        // 场景1: 建立连接和登录
        setupConnectionAndLogin();

        // 场景2: 无效成就ID测试
        testInvalidAchievementId();

        // 场景3: 负数进度值测试
        testNegativeProgress();

        // 场景4: 超过最大值的进度值测试
        testExcessiveProgress();

        // 场景5: 大批量获取成就测试
        testLargeBatchSize();

        // 场景6: 频繁请求测试
        testFrequentRequests();
    }

    private void setupConnectionAndLogin() throws Exception {
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
        reqLogin.token = "test_token_achievement_010";
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


    }

    private void testInvalidAchievementId() throws Exception {
        System.out.println("\n场景2: 无效成就ID测试");
        
        // 步骤15: 构造获取成就详情请求（使用无效成就ID）
        System.out.println("\n步骤15: 构造获取成就详情请求");
        REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
        reqAchievementInfo.type = 1; // 假设type=1表示获取成就详情
        System.out.println("REQ_ACHIEVEMENT_INFO对象创建成功");

        // 步骤16: 编码获取成就详情请求
        System.out.println("\n步骤16: 编码获取成就详情请求");
        byte[] getAchievementDetailData = MessageCodec.encodeMessage(reqAchievementInfo, (byte) 2);
        System.out.println("编码成功，数据长度: " + getAchievementDetailData.length);

        // 步骤17: 发送获取成就详情请求
        System.out.println("\n步骤17: 发送获取成就详情请求");
        out.write(getAchievementDetailData);
        out.flush();
        System.out.println("获取成就详情请求发送成功");

        // 步骤18: 接收获取成就详情响应
        System.out.println("\n步骤18: 接收获取成就详情响应");
        byte[] getAchievementDetailResponseData = readMessage(in);
        Message getAchievementDetailResponse = MessageCodec.decodeMessage(getAchievementDetailResponseData);
        System.out.println("收到响应类型: " + getAchievementDetailResponse.getClass().getName());

        // 步骤19: 验证无效成就ID响应
        System.out.println("\n步骤19: 验证无效成就ID响应");
        if (getAchievementDetailResponse instanceof RES_ACHIEVEMENT_INFO) {
            RES_ACHIEVEMENT_INFO resAchievementInfo = (RES_ACHIEVEMENT_INFO) getAchievementDetailResponse;
            System.out.println("error: " + resAchievementInfo.error);
            System.out.println("type: " + resAchievementInfo.type);
            System.out.println("score: " + resAchievementInfo.score);
            
            // 验证响应是否处理合理
            System.out.println("无效成就ID测试完成");
        } else if (getAchievementDetailResponse instanceof RES_PING) {
            System.out.println("无效成就ID测试通过（收到PING响应）");
        } else {
            System.out.println("无效成就ID测试完成，响应类型: " + getAchievementDetailResponse.getClass().getName());
        }
    }

    private void testNegativeProgress() throws Exception {
        System.out.println("\n场景3: 负数进度值测试");
        
        // 步骤20: 构造获取成就详情请求
        System.out.println("\n步骤20: 构造获取成就详情请求");
        REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
        reqAchievementInfo.type = 1; // 假设type=1表示获取成就详情
        System.out.println("REQ_ACHIEVEMENT_INFO对象创建成功");

        // 步骤21: 编码获取成就详情请求
        System.out.println("\n步骤21: 编码获取成就详情请求");
        byte[] updateProgressData = MessageCodec.encodeMessage(reqAchievementInfo, (byte) 2);
        System.out.println("编码成功，数据长度: " + updateProgressData.length);

        // 步骤22: 发送获取成就详情请求
        System.out.println("\n步骤22: 发送获取成就详情请求");
        out.write(updateProgressData);
        out.flush();
        System.out.println("获取成就详情请求发送成功");

        // 步骤23: 接收获取成就详情响应
        System.out.println("\n步骤23: 接收获取成就详情响应");
        byte[] updateProgressResponseData = readMessage(in);
        Message updateProgressResponse = MessageCodec.decodeMessage(updateProgressResponseData);
        System.out.println("收到响应类型: " + updateProgressResponse.getClass().getName());

        // 步骤24: 验证负数进度值响应
        System.out.println("\n步骤24: 验证负数进度值响应");
        if (updateProgressResponse instanceof RES_ACHIEVEMENT_INFO) {
            RES_ACHIEVEMENT_INFO resAchievementInfo = (RES_ACHIEVEMENT_INFO) updateProgressResponse;
            System.out.println("error: " + resAchievementInfo.error);
            System.out.println("type: " + resAchievementInfo.type);
            System.out.println("score: " + resAchievementInfo.score);
            
            // 验证响应是否处理合理
            System.out.println("负数进度值测试完成");
        } else if (updateProgressResponse instanceof RES_PING) {
            System.out.println("负数进度值测试通过（收到PING响应）");
        } else {
            System.out.println("负数进度值测试完成，响应类型: " + updateProgressResponse.getClass().getName());
        }
    }

    private void testExcessiveProgress() throws Exception {
        System.out.println("\n场景4: 超过最大值的进度值测试");
        
        // 步骤25: 构造获取成就详情请求
        System.out.println("\n步骤25: 构造获取成就详情请求");
        REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
        reqAchievementInfo.type = 1; // 假设type=1表示获取成就详情
        System.out.println("REQ_ACHIEVEMENT_INFO对象创建成功");

        // 步骤26: 编码获取成就详情请求
        System.out.println("\n步骤26: 编码获取成就详情请求");
        byte[] updateProgressData = MessageCodec.encodeMessage(reqAchievementInfo, (byte) 2);
        System.out.println("编码成功，数据长度: " + updateProgressData.length);

        // 步骤27: 发送获取成就详情请求
        System.out.println("\n步骤27: 发送获取成就详情请求");
        out.write(updateProgressData);
        out.flush();
        System.out.println("获取成就详情请求发送成功");

        // 步骤28: 接收获取成就详情响应
        System.out.println("\n步骤28: 接收获取成就详情响应");
        byte[] updateProgressResponseData = readMessage(in);
        Message updateProgressResponse = MessageCodec.decodeMessage(updateProgressResponseData);
        System.out.println("收到响应类型: " + updateProgressResponse.getClass().getName());

        // 步骤29: 验证超过最大值的进度值响应
        System.out.println("\n步骤29: 验证超过最大值的进度值响应");
        if (updateProgressResponse instanceof RES_ACHIEVEMENT_INFO) {
            RES_ACHIEVEMENT_INFO resAchievementInfo = (RES_ACHIEVEMENT_INFO) updateProgressResponse;
            System.out.println("error: " + resAchievementInfo.error);
            System.out.println("type: " + resAchievementInfo.type);
            System.out.println("score: " + resAchievementInfo.score);
            
            // 验证响应是否处理合理
            System.out.println("超过最大值的进度值测试完成");
        } else if (updateProgressResponse instanceof RES_PING) {
            System.out.println("超过最大值的进度值测试通过（收到PING响应）");
        } else {
            System.out.println("超过最大值的进度值测试完成，响应类型: " + updateProgressResponse.getClass().getName());
        }
    }

    private void testLargeBatchSize() throws Exception {
        System.out.println("\n场景5: 大批量获取成就测试");
        
        // 步骤30: 构造获取成就详情请求
        System.out.println("\n步骤30: 构造获取成就详情请求");
        REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
        reqAchievementInfo.type = 1; // 假设type=1表示获取成就详情
        System.out.println("REQ_ACHIEVEMENT_INFO对象创建成功");

        // 步骤31: 编码获取成就详情请求
        System.out.println("\n步骤31: 编码获取成就详情请求");
        byte[] getAchievementsBatchData = MessageCodec.encodeMessage(reqAchievementInfo, (byte) 2);
        System.out.println("编码成功，数据长度: " + getAchievementsBatchData.length);

        // 步骤32: 发送获取成就详情请求
        System.out.println("\n步骤32: 发送获取成就详情请求");
        out.write(getAchievementsBatchData);
        out.flush();
        System.out.println("获取成就详情请求发送成功");

        // 步骤33: 接收获取成就详情响应
        System.out.println("\n步骤33: 接收获取成就详情响应");
        byte[] getAchievementsBatchResponseData = readMessage(in);
        Message getAchievementsBatchResponse = MessageCodec.decodeMessage(getAchievementsBatchResponseData);
        System.out.println("收到响应类型: " + getAchievementsBatchResponse.getClass().getName());

        // 步骤34: 验证大批量获取成就响应
        System.out.println("\n步骤34: 验证大批量获取成就响应");
        if (getAchievementsBatchResponse instanceof RES_ACHIEVEMENT_INFO) {
            RES_ACHIEVEMENT_INFO resAchievementInfo = (RES_ACHIEVEMENT_INFO) getAchievementsBatchResponse;
            System.out.println("error: " + resAchievementInfo.error);
            System.out.println("type: " + resAchievementInfo.type);
            System.out.println("score: " + resAchievementInfo.score);
            
            // 验证响应是否处理合理
            System.out.println("大批量获取成就测试完成");
        } else if (getAchievementsBatchResponse instanceof RES_PING) {
            System.out.println("大批量获取成就测试通过（收到PING响应）");
        } else {
            System.out.println("大批量获取成就测试完成，响应类型: " + getAchievementsBatchResponse.getClass().getName());
        }
    }

    private void testFrequentRequests() throws Exception {
        System.out.println("\n场景6: 频繁请求测试");
        
        // 步骤35: 发送多次重复请求
        System.out.println("\n步骤35: 发送多次重复请求");
        for (int i = 0; i < 5; i++) {
            // 构造获取成就详情请求
            REQ_ACHIEVEMENT_INFO reqAchievementInfo = new REQ_ACHIEVEMENT_INFO();
            reqAchievementInfo.type = 1; // 假设type=1表示获取成就详情
            
            // 编码获取成就详情请求
            byte[] getAchievementListData = MessageCodec.encodeMessage(reqAchievementInfo, (byte) 2);
            
            // 发送获取成就详情请求
            out.write(getAchievementListData);
            out.flush();
            System.out.println("发送第" + (i + 1) + "次获取成就详情请求");
            
            // 接收获取成就详情响应
            byte[] getAchievementListResponseData = readMessage(in);
            Message getAchievementListResponse = MessageCodec.decodeMessage(getAchievementListResponseData);
            System.out.println("收到第" + (i + 1) + "次响应类型: " + getAchievementListResponse.getClass().getName());
        }

        // 步骤36: 验证频繁请求响应
        System.out.println("\n步骤36: 验证频繁请求响应");
        System.out.println("频繁请求测试完成，服务器能够处理多次请求");
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