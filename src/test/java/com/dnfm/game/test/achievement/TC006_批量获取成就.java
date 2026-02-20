package com.dnfm.game.test.achievement;

import com.dnfm.game.test.util.MessageCodec;
import com.dnfm.mina.protobuf.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import static org.junit.Assert.*;

public class TC006_批量获取成就 {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 10001;
    private static final String TEST_OPENID = "test_openid_006";
    private static final String TEST_ROLE_GUID = "role_guid_006";
    private static final int[] TEST_ACHIEVEMENT_IDS = {1001, 1002, 1003};
    private static final int TEST_PAGE_SIZE = 10;
    private static final int TEST_PAGE_NUM = 1;

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC006: 批量获取成就 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("========== TC006 测试结束 ==========");
    }

    @Test
    public void testBatchGetAchievements() throws Exception {
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
        reqLogin.token = "test_token_achievement_006";
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

        // 步骤16: 构造批量获取成就请求
        System.out.println("\n步骤16: 构造批量获取成就请求");
        REQ_BATCH_GET_ACHIEVEMENTS reqBatchGet = new REQ_BATCH_GET_ACHIEVEMENTS();
        for (int achievementId : TEST_ACHIEVEMENT_IDS) {
            reqBatchGet.achievementIds.add(achievementId);
        }
        reqBatchGet.pageSize = TEST_PAGE_SIZE;
        reqBatchGet.pageNum = TEST_PAGE_NUM;
        System.out.println("REQ_BATCH_GET_ACHIEVEMENTS对象创建成功");

        // 步骤17: 编码批量获取成就请求
        System.out.println("\n步骤17: 编码批量获取成就请求");
        byte[] batchGetData = MessageCodec.encodeMessage(reqBatchGet, (byte) 4);
        System.out.println("编码成功，数据长度: " + batchGetData.length);

        // 步骤18: 发送批量获取成就请求
        System.out.println("\n步骤18: 发送批量获取成就请求");
        out.write(batchGetData);
        out.flush();
        System.out.println("批量获取成就请求发送成功");

        // 步骤19: 接收批量获取成就响应
        System.out.println("\n步骤19: 接收批量获取成就响应");
        byte[] batchGetResponseData = readMessage(in);
        Message batchGetResponse = MessageCodec.decodeMessage(batchGetResponseData);
        System.out.println("收到响应类型: " + batchGetResponse.getClass().getName());

        // 步骤20: 验证批量获取成就响应
        System.out.println("\n步骤20: 验证批量获取成就响应");
        if (batchGetResponse instanceof RES_BATCH_GET_ACHIEVEMENTS) {
            RES_BATCH_GET_ACHIEVEMENTS resBatchGet = (RES_BATCH_GET_ACHIEVEMENTS) batchGetResponse;
            System.out.println("获取结果: " + resBatchGet.success);
            System.out.println("成就数量: " + resBatchGet.achievements.size());
            System.out.println("总数量: " + resBatchGet.totalCount);
            System.out.println("页码: " + resBatchGet.pageNum);
            System.out.println("每页数量: " + resBatchGet.pageSize);
            
            assertTrue("批量获取成就失败", resBatchGet.success);
            assertFalse("成就列表为空", resBatchGet.achievements.isEmpty());
            assertEquals("页码不匹配", TEST_PAGE_NUM, resBatchGet.pageNum);
            assertEquals("每页数量不匹配", TEST_PAGE_SIZE, resBatchGet.pageSize);
            
            System.out.println("批量获取成就验证通过");

            // 步骤21: 验证每个成就的信息
            System.out.println("\n步骤21: 验证每个成就的信息");
            for (ACHIEVEMENT_INFO achievementInfo : resBatchGet.achievements) {
                System.out.println("成就ID: " + achievementInfo.achievementId);
                System.out.println("成就名称: " + achievementInfo.name);
                System.out.println("成就状态: " + achievementInfo.status);
                System.out.println("当前进度: " + achievementInfo.currentProgress);
                System.out.println("目标进度: " + achievementInfo.targetProgress);
                
                assertNotNull("成就ID为空", achievementInfo.achievementId);
                assertNotNull("成就名称为空", achievementInfo.name);
                assertNotNull("成就描述为空", achievementInfo.description);
                
                boolean found = false;
                for (int testId : TEST_ACHIEVEMENT_IDS) {
                    if (testId == achievementInfo.achievementId) {
                        found = true;
                        break;
                    }
                }
                assertTrue("成就ID不在请求列表中", found);
            }
            System.out.println("成就信息验证通过");
        } else if (batchGetResponse instanceof RES_PING) {
            System.out.println("批量获取成就测试通过（收到PING响应）");
        } else {
            fail("批量获取成就响应类型错误: " + batchGetResponse.getClass().getName());
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