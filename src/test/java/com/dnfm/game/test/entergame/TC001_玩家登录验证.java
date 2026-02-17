package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.common.util.DBUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * TC001_玩家登录验证
 *
 * 测试目的: 验证玩家使用正确的openid能否成功登录，并返回正确的authKey和玩家信息
 * 测试类型: 功能测试
 * 优先级: 高
 */
public class TC001_玩家登录验证 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_001";
    private static final String TEST_DEVICE_ID = "device_test_001";

    private Socket socket;
    private Connection dbConnection;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== 开始准备测试环境 ==========");

        // 步骤1: 准备测试环境
        // 1.1 检查Java服务端是否启动
        Process process = Runtime.getRuntime().exec("ps aux | grep java");
        process.waitFor();
        assertTrue("Java服务端未启动", process.exitValue() == 0);

        // 1.2 检查数据库连接
        dbConnection = DBUtil.getConnection();
        assertNotNull("数据库连接失败", dbConnection);

        // 1.3 清理测试数据
        cleanupTestData();

        System.out.println("========== 测试环境准备完成 ==========");
    }

    @Test
    public void testPlayerLogin() throws Exception {
        System.out.println("========== 开始测试: TC001_玩家登录验证 ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 构造登录请求
        System.out.println("步骤2: 构造登录请求");
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid(TEST_OPENID);
        req.setDeviceId(TEST_DEVICE_ID);
        req.setDeviceType(1); // Android
        req.setClientVersion("1.0.0");

        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);

        assertNotNull("请求序列化失败", reqBytes);
        assertTrue("请求数据为空", reqBytes.length > 0);
        System.out.println("请求序列化成功，长度: " + reqBytes.length);

        // 步骤3: 发送登录请求
        System.out.println("步骤3: 发送登录请求");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(15000); // 15秒超时

        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("登录请求发送成功");

        // 步骤4: 接收并解析响应
        System.out.println("步骤4: 接收并解析响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);

        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("响应接收成功，长度: " + responseBytes.length);

        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);

        assertNotNull("响应反序列化失败", res);
        System.out.println("响应反序列化成功");

        // 步骤5: 验证登录结果
        System.out.println("步骤5: 验证登录结果");

        // 5.1 验证错误码
        assertEquals("错误码不为0", 0, res.getError());
        System.out.println("错误码验证通过: " + res.getError());

        // 5.2 验证authKey
        assertNotNull("authKey为空", res.getAuthKey());
        assertTrue("authKey长度不符合要求", res.getAuthKey().length() >= 32 && res.getAuthKey().length() <= 64);
        System.out.println("authKey验证通过: " + res.getAuthKey());

        // 5.3 验证账号信息
        assertNotNull("账号信息为空", res.getAccount());
        assertNotNull("openid为空", res.getAccount().getOpenid());
        assertEquals("openid不匹配", TEST_OPENID, res.getAccount().getOpenid());
        assertNotNull("accountID为空", res.getAccount().getAccountID());
        assertNotNull("createTime为空", res.getAccount().getCreateTime());
        System.out.println("账号信息验证通过");
        System.out.println("  - openid: " + res.getAccount().getOpenid());
        System.out.println("  - accountID: " + res.getAccount().getAccountID());
        System.out.println("  - createTime: " + res.getAccount().getCreateTime());

        // 5.4 验证角色列表
        assertNotNull("角色列表为空", res.getCharacters());
        System.out.println("角色列表验证通过，角色数量: " + res.getCharacters().size());

        // 步骤6: 验证数据库记录
        System.out.println("步骤6: 验证数据库记录");
        verifyLoginRecord();

        // 步骤7: 验证token生成
        System.out.println("步骤7: 验证token生成");
        verifyTokenRecord(res.getAuthKey(), res.getAccount().getAccountID());

        long endTime = System.currentTimeMillis();
        System.out.println("========== 测试执行完成，耗时: " + (endTime - startTime) + "ms ==========");
    }

    /**
     * 验证登录记录
     */
    private void verifyLoginRecord() throws Exception {
        String sql = "SELECT * FROM t_account_login_log WHERE openid = ? ORDER BY loginTime DESC LIMIT 1";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setString(1, TEST_OPENID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("登录记录不存在", rs.next());

        Timestamp loginTime = rs.getTimestamp("loginTime");
        assertNotNull("登录时间为空", loginTime);

        // 验证登录时间在最近5分钟内
        long timeDiff = System.currentTimeMillis() - loginTime.getTime();
        assertTrue("登录时间不在最近5分钟内", timeDiff < 5 * 60 * 1000);

        String loginIP = rs.getString("loginIP");
        assertEquals("登录IP不匹配", "127.0.0.1", loginIP);

        String deviceId = rs.getString("deviceId");
        assertEquals("设备ID不匹配", TEST_DEVICE_ID, deviceId);

        System.out.println("登录记录验证通过");
        System.out.println("  - loginTime: " + loginTime);
        System.out.println("  - loginIP: " + loginIP);
        System.out.println("  - deviceId: " + deviceId);

        rs.close();
        stmt.close();
    }

    /**
     * 验证token记录
     */
    private void verifyTokenRecord(String authKey, long accountID) throws Exception {
        String sql = "SELECT * FROM t_account_token WHERE accountID = ? ORDER BY createTime DESC LIMIT 1";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, accountID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("token记录不存在", rs.next());

        String token = rs.getString("token");
        assertEquals("token不匹配", authKey, token);

        Timestamp expireTime = rs.getTimestamp("expireTime");
        assertNotNull("token过期时间为空", expireTime);

        // 验证token有效期约为7天
        long expectedExpireTime = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000;
        long timeDiff = Math.abs(expireTime.getTime() - expectedExpireTime);
        assertTrue("token有效期不正确", timeDiff < 60 * 1000); // 允许1分钟误差

        int status = rs.getInt("status");
        assertEquals("token状态不正确", 1, status);

        System.out.println("token记录验证通过");
        System.out.println("  - token: " + token);
        System.out.println("  - expireTime: " + expireTime);
        System.out.println("  - status: " + status);

        rs.close();
        stmt.close();
    }

    /**
     * 清理测试数据
     */
    private void cleanupTestData() throws Exception {
        System.out.println("清理测试数据");

        // 删除登录记录
        String deleteLoginLogSql = "DELETE FROM t_account_login_log WHERE openid = ?";
        PreparedStatement stmt1 = dbConnection.prepareStatement(deleteLoginLogSql);
        stmt1.setString(1, TEST_OPENID);
        int loginLogCount = stmt1.executeUpdate();
        stmt1.close();
        System.out.println("删除登录记录: " + loginLogCount + " 条");

        // 删除token记录
        String deleteTokenSql = "DELETE FROM t_account_token WHERE accountID IN (SELECT accountID FROM t_account WHERE openid = ?)";
        PreparedStatement stmt2 = dbConnection.prepareStatement(deleteTokenSql);
        stmt2.setString(1, TEST_OPENID);
        int tokenCount = stmt2.executeUpdate();
        stmt2.close();
        System.out.println("删除token记录: " + tokenCount + " 条");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("========== 开始清理测试数据 ==========");

        // 步骤8: 清理测试数据
        if (dbConnection != null) {
            cleanupTestData();
            dbConnection.close();
        }

        if (socket != null) {
            socket.close();
        }

        System.out.println("========== 测试数据清理完成 ==========");
    }

    /**
     * 完整读取输入流
     */
    private byte[] readFully(InputStream in) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }
}
