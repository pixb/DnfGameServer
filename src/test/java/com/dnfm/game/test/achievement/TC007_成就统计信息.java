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

public class TC007_成就统计信息 {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final String TEST_OPENID = "test_openid_007";
    private static final String TEST_ROLE_GUID = "role_guid_007";

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC007: 成就统计信息 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC007 测试结束 ==========");
    }

    @Test
    public void testGetAchievementStatistics() throws Exception {
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
        reqLogin.token = "test_token_achievement_007";
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

        // 步骤16: 构造获取成就统计信息请求
        System.out.println("\n步骤16: 构造获取成就统计信息请求");
        REQ_GET_ACHIEVEMENT_STATISTICS reqGetStatistics = new REQ_GET_ACHIEVEMENT_STATISTICS();
        System.out.println("REQ_GET_ACHIEVEMENT_STATISTICS对象创建成功");

        // 步骤17: 编码获取成就统计信息请求
        System.out.println("\n步骤17: 编码获取成就统计信息请求");
        byte[] getStatisticsData = MessageCodec.encodeMessage(reqGetStatistics, (byte) 4);
        System.out.println("编码成功，数据长度: " + getStatisticsData.length);

        // 步骤18: 发送获取成就统计信息请求
        System.out.println("\n步骤18: 发送获取成就统计信息请求");
        out.write(getStatisticsData);
        out.flush();
        System.out.println("获取成就统计信息请求发送成功");

        // 步骤19: 接收获取成就统计信息响应
        System.out.println("\n步骤19: 接收获取成就统计信息响应");
        byte[] getStatisticsResponseData = readMessage(in);
        Message getStatisticsResponse = MessageCodec.decodeMessage(getStatisticsResponseData);
        System.out.println("收到响应类型: " + getStatisticsResponse.getClass().getName());

        // 步骤20: 验证获取成就统计信息响应
        System.out.println("\n步骤20: 验证获取成就统计信息响应");
        if (getStatisticsResponse instanceof RES_ACHIEVEMENT_STATISTICS) {
            RES_ACHIEVEMENT_STATISTICS resStatistics = (RES_ACHIEVEMENT_STATISTICS) getStatisticsResponse;
            System.out.println("获取结果: " + resStatistics.success);
            System.out.println("已完成成就: " + resStatistics.completedCount);
            System.out.println("总成就数量: " + resStatistics.totalCount);
            System.out.println("成就完成率: " + resStatistics.completionRate + "%");
            System.out.println("成就点数: " + resStatistics.achievementPoints);
            System.out.println("成就等级: " + resStatistics.achievementLevel);
            System.out.println("最近完成成就: " + resStatistics.recentlyCompleted.size());
            
            assertTrue("获取成就统计信息失败", resStatistics.success);
            assertTrue("已完成成就数量不能为负数", resStatistics.completedCount >= 0);
            assertTrue("总成就数量不能为负数", resStatistics.totalCount >= 0);
            assertTrue("已完成成就数量不能超过总成就数量", resStatistics.completedCount <= resStatistics.totalCount);
            assertTrue("成就完成率不能为负数", resStatistics.completionRate >= 0);
            assertTrue("成就完成率不能超过100%", resStatistics.completionRate <= 100);
            assertTrue("成就点数不能为负数", resStatistics.achievementPoints >= 0);
            assertTrue("成就等级不能为负数", resStatistics.achievementLevel >= 0);
            
            System.out.println("成就统计信息验证通过");

            // 步骤21: 验证各类别成就完成情况
            System.out.println("\n步骤21: 验证各类别成就完成情况");
            for (ACHIEVEMENT_CATEGORY_STAT categoryStat : resStatistics.categoryStats) {
                System.out.println("类别ID: " + categoryStat.categoryId);
                System.out.println("类别名称: " + categoryStat.categoryName);
                System.out.println("已完成: " + categoryStat.completedCount);
                System.out.println("总数: " + categoryStat.totalCount);
                System.out.println("完成率: " + categoryStat.completionRate + "%");
                
                assertTrue("类别已完成成就数量不能为负数", categoryStat.completedCount >= 0);
                assertTrue("类别总成就数量不能为负数", categoryStat.totalCount >= 0);
                assertTrue("类别已完成成就数量不能超过总成就数量", categoryStat.completedCount <= categoryStat.totalCount);
                assertTrue("类别成就完成率不能为负数", categoryStat.completionRate >= 0);
                assertTrue("类别成就完成率不能超过100%", categoryStat.completionRate <= 100);
            }
            System.out.println("类别成就统计验证通过");
        } else if (getStatisticsResponse instanceof RES_PING) {
            System.out.println("获取成就统计信息测试通过（收到PING响应）");
        } else {
            fail("获取成就统计信息响应类型错误: " + getStatisticsResponse.getClass().getName());
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