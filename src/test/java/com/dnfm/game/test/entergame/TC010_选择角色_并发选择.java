package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.common.util.DBUtil;
import com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN;
import com.dnfm.mina.protobuf.RES_ENTER_TO_TOWN;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class TC010_选择角色_并发选择 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 20001;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String TEST_OPENID = "test_openid_010";

    private String authKey;

    @Before
    public void setUp() throws Exception {
        System.out.println("========== TC010: 选择角色_并发选择 ==========");
        prepareTestData();
    }

    @After
    public void tearDown() throws Exception {
        cleanTestData();
        System.out.println("========== TC010 测试结束 ==========");
    }

    @Test
    public void testConcurrentSelectCharacter() throws Exception {
        System.out.println("\n步骤1: 创建并发选择任务");
        final int THREAD_COUNT = 3;
        Thread[] threads = new Thread[THREAD_COUNT];
        final int[] successCount = new int[1];
        final int[] failCount = new int[1];
        successCount[0] = 0;
        failCount[0] = 0;

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程" + threadIndex + ": 开始选择角色");
                        Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                        socket.setSoTimeout(CONNECT_TIMEOUT);

                        REQ_ENTER_TO_TOWN req = new REQ_ENTER_TO_TOWN();
                        req.authkey = authKey;
                        req.town = 1;
                        req.area = 1;
                        req.posx = 0;
                        req.posy = 0;

                        Codec<REQ_ENTER_TO_TOWN> reqCodec = ProtobufProxy.create(REQ_ENTER_TO_TOWN.class);
                        byte[] reqBytes = reqCodec.encode(req);

                        OutputStream out = socket.getOutputStream();
                        out.write(reqBytes);
                        out.flush();

                        InputStream in = socket.getInputStream();
                        byte[] responseBytes = readFully(in);

                        Codec<RES_ENTER_TO_TOWN> resCodec = ProtobufProxy.create(RES_ENTER_TO_TOWN.class);
                        RES_ENTER_TO_TOWN res = resCodec.decode(responseBytes);

                        if (res.error == 0) {
                            successCount[0]++;
                            System.out.println("线程" + threadIndex + ": 选择角色成功");
                        } else {
                            failCount[0]++;
                            System.out.println("线程" + threadIndex + ": 选择角色失败，error=" + res.error);
                        }

                        socket.close();
                    } catch (Exception e) {
                        failCount[0]++;
                        System.out.println("线程" + threadIndex + ": 发生异常 - " + e.getMessage());
                    }
                }
            });
        }

        System.out.println("\n步骤2: 启动所有线程");
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].start();
        }

        System.out.println("\n步骤3: 等待所有线程完成");
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }

        System.out.println("\n步骤4: 验证并发选择结果");
        System.out.println("成功次数: " + successCount[0]);
        System.out.println("失败次数: " + failCount[0]);
        System.out.println("总次数: " + (successCount[0] + failCount[0]));

        assertTrue("至少应该有一次成功", successCount[0] > 0);
        assertTrue("总次数应该等于线程数", (successCount[0] + failCount[0]) == THREAD_COUNT);
        System.out.println("并发选择测试通过");
    }

    private void prepareTestData() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            String accountSql = "SELECT accountID FROM t_account WHERE openid = ?";
            stmt = conn.prepareStatement(accountSql);
            stmt.setString(1, TEST_OPENID);
            rs = stmt.executeQuery();

            Long accountID = null;
            if (rs.next()) {
                accountID = rs.getLong("accountID");
            }
            rs.close();
            stmt.close();

            if (accountID == null) {
                String insertAccountSql = "INSERT INTO t_account (openid, createTime, status) VALUES (?, NOW(), 1)";
                stmt = conn.prepareStatement(insertAccountSql, java.sql.Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, TEST_OPENID);
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    accountID = rs.getLong(1);
                }
                rs.close();
                stmt.close();
            }

            String deleteCharSql = "DELETE FROM t_character WHERE accountID = ?";
            stmt = conn.prepareStatement(deleteCharSql);
            stmt.setLong(1, accountID);
            stmt.executeUpdate();
            stmt.close();

            String insertCharSql = "INSERT INTO t_character (charguid, accountID, name, job, level, createTime, status) VALUES (?, ?, ?, ?, ?, NOW(), 1)";
            stmt = conn.prepareStatement(insertCharSql);
            stmt.setLong(1, 1001);
            stmt.setLong(2, accountID);
            stmt.setString(3, "TestPlayer1");
            stmt.setInt(4, 1);
            stmt.setInt(5, 10);
            stmt.executeUpdate();
            stmt.close();

            conn.commit();
            authKey = "test_authkey_010";
            System.out.println("测试数据准备完成");

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    private void cleanTestData() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM t_character WHERE accountID = (SELECT accountID FROM t_account WHERE openid = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            stmt.close();

            sql = "DELETE FROM t_account WHERE openid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, TEST_OPENID);
            stmt.executeUpdate();
            System.out.println("测试数据清理完成");

        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    private byte[] readFully(InputStream in) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }
}
