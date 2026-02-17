package com.dnfm.game.test.entergame;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.game.auth.model.REQ_LOGIN;
import com.dnfm.game.auth.model.RES_LOGIN;
import com.dnfm.game.character.model.REQ_SELECT_CHARACTER;
import com.dnfm.game.character.model.RES_SELECT_CHARACTER;
import com.dnfm.game.entergame.model.REQ_ENTER_GAME;
import com.dnfm.game.entergame.model.RES_ENTER_GAME;
import com.dnfm.game.entergame.model.GameData;
import com.dnfm.game.entergame.model.CharacterInfo;
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

import static org.junit.Assert.*;

/**
 * TC007_进入游戏
 *
 * 测试目的: 验证玩家能成功进入游戏，并加载完整的玩家数据
 * 测试类型: 功能测试
 * 优先级: 高
 */
public class TC007_进入游戏 {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final String TEST_OPENID = "test_openid_007";
    private static final String TEST_DEVICE_ID = "device_test_007";
    private static final long TEST_ACCOUNT_ID = 10007;
    private static final long CHARACTER_GUID = 1001;
    private static final String CHARACTER_NAME = "TestPlayer1";
    private static final int STATUS_IN_GAME = 2; // 游戏中状态

    private Socket socket;
    private Connection dbConnection;
    private String authKey;

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

        // 1.3 准备测试账号和角色
        prepareTestData();

        System.out.println("========== 测试环境准备完成 ==========");
    }

    @Test
    public void testEnterGame() throws Exception {
        System.out.println("========== 开始测试: TC007_进入游戏 ==========");

        long startTime = System.currentTimeMillis();

        // 步骤2: 玩家登录获取authKey
        System.out.println("步骤2: 玩家登录获取authKey");
        loginAndGetAuthKey();

        // 步骤3: 选择角色
        System.out.println("步骤3: 选择角色");
        selectCharacter();

        // 步骤4: 构造进入游戏请求
        System.out.println("步骤4: 构造进入游戏请求");
        REQ_ENTER_GAME req = new REQ_ENTER_GAME();
        req.setAuthKey(authKey);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_ENTER_GAME> reqCodec = ProtobufProxy.create(REQ_ENTER_GAME.class);
        byte[] reqBytes = reqCodec.encode(req);

        assertNotNull("请求序列化失败", reqBytes);
        assertTrue("请求数据为空", reqBytes.length > 0);
        System.out.println("请求序列化成功，长度: " + reqBytes.length);

        // 步骤5: 发送进入游戏请求
        System.out.println("步骤5: 发送进入游戏请求");
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        socket.setSoTimeout(20000); // 20秒超时（进入游戏可能需要更长时间）

        OutputStream out = socket.getOutputStream();
        out.write(reqBytes);
        out.flush();
        System.out.println("进入游戏请求发送成功");

        // 步骤6: 接收并解析响应
        System.out.println("步骤6: 接收并解析响应");
        InputStream in = socket.getInputStream();
        byte[] responseBytes = readFully(in);

        assertNotNull("响应数据为空", responseBytes);
        assertTrue("响应数据为空", responseBytes.length > 0);
        System.out.println("响应接收成功，长度: " + responseBytes.length);

        Codec<RES_ENTER_GAME> resCodec = ProtobufProxy.create(RES_ENTER_GAME.class);
        RES_ENTER_GAME res = resCodec.decode(responseBytes);

        assertNotNull("响应反序列化失败", res);
        System.out.println("响应反序列化成功");

        // 步骤7: 验证进入游戏成功
        System.out.println("步骤7: 验证进入游戏成功");
        assertEquals("错误码不为0", 0, res.getError());
        assertNotNull("角色信息为空", res.getCharacter());
        assertEquals("角色GUID不正确", CHARACTER_GUID, res.getCharacter().getCharguid());
        assertNotNull("游戏数据为空", res.getGameData());
        System.out.println("进入游戏验证通过");
        System.out.println("  - 错误码: " + res.getError());
        System.out.println("  - 角色GUID: " + res.getCharacter().getCharguid());

        // 步骤8: 验证角色基本信息
        System.out.println("步骤8: 验证角色基本信息");
        verifyCharacterBasicInfo(res.getCharacter());

        // 步骤9: 验证背包数据
        System.out.println("步骤9: 验证背包数据");
        verifyBagData(res.getGameData());

        // 步骤10: 验证装备数据
        System.out.println("步骤10: 验证装备数据");
        verifyEquipmentData(res.getGameData());

        // 步骤11: 验证技能数据
        System.out.println("步骤11: 验证技能数据");
        verifySkillData(res.getGameData());

        // 步骤12: 验证任务数据
        System.out.println("步骤12: 验证任务数据");
        verifyQuestData(res.getGameData());

        // 步骤13: 验证成就数据
        System.out.println("步骤13: 验证成就数据");
        verifyAchievementData(res.getGameData());

        // 步骤14: 验证数据库记录
        System.out.println("步骤14: 验证数据库记录");
        verifyDatabaseRecords();

        // 步骤15: 验证日志记录
        System.out.println("步骤15: 验证日志记录");
        verifyLogRecord();

        long endTime = System.currentTimeMillis();
        System.out.println("========== 测试执行完成，耗时: " + (endTime - startTime) + "ms ==========");
    }

    /**
     * 登录并获取authKey
     */
    private void loginAndGetAuthKey() throws Exception {
        REQ_LOGIN req = new REQ_LOGIN();
        req.setOpenid(TEST_OPENID);
        req.setDeviceId(TEST_DEVICE_ID);
        req.setDeviceType(1); // Android
        req.setClientVersion("1.0.0");

        Codec<REQ_LOGIN> reqCodec = ProtobufProxy.create(REQ_LOGIN.class);
        byte[] reqBytes = reqCodec.encode(req);

        Socket loginSocket = new Socket(SERVER_HOST, SERVER_PORT);
        loginSocket.setSoTimeout(15000);

        OutputStream out = loginSocket.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = loginSocket.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_LOGIN> resCodec = ProtobufProxy.create(RES_LOGIN.class);
        RES_LOGIN res = resCodec.decode(responseBytes);

        assertEquals("登录失败", 0, res.getError());
        assertNotNull("authKey为空", res.getAuthKey());

        authKey = res.getAuthKey();
        System.out.println("登录成功，authKey: " + authKey);

        loginSocket.close();
    }

    /**
     * 选择角色
     */
    private void selectCharacter() throws Exception {
        REQ_SELECT_CHARACTER req = new REQ_SELECT_CHARACTER();
        req.setAuthKey(authKey);
        req.setAccountID(TEST_ACCOUNT_ID);
        req.setCharguid(CHARACTER_GUID);

        Codec<REQ_SELECT_CHARACTER> reqCodec = ProtobufProxy.create(REQ_SELECT_CHARACTER.class);
        byte[] reqBytes = reqCodec.encode(req);

        Socket selectSocket = new Socket(SERVER_HOST, SERVER_PORT);
        selectSocket.setSoTimeout(15000);

        OutputStream out = selectSocket.getOutputStream();
        out.write(reqBytes);
        out.flush();

        InputStream in = selectSocket.getInputStream();
        byte[] responseBytes = readFully(in);

        Codec<RES_SELECT_CHARACTER> resCodec = ProtobufProxy.create(RES_SELECT_CHARACTER.class);
        RES_SELECT_CHARACTER res = resCodec.decode(responseBytes);

        assertEquals("选择角色失败", 0, res.getError());
        assertNotNull("角色信息为空", res.getCharacter());

        System.out.println("选择角色成功");

        selectSocket.close();
    }

    /**
     * 验证角色基本信息
     */
    private void verifyCharacterBasicInfo(CharacterInfo character) {
        assertEquals("角色GUID不正确", CHARACTER_GUID, character.getCharguid());
        assertEquals("角色名称不正确", CHARACTER_NAME, character.getName());
        assertEquals("角色职业不正确", 1, character.getJob());
        assertEquals("角色等级不正确", 10, character.getLevel());
        assertNotNull("角色HP为空", character.getHp());
        assertTrue("角色HP应该大于0", character.getHp() > 0);
        assertNotNull("角色MP为空", character.getMp());
        assertTrue("角色MP应该大于0", character.getMp() > 0);
        assertNotNull("角色经验为空", character.getExp());
        assertNotNull("角色金币为空", character.getGold());
        assertTrue("角色金币应该大于等于0", character.getGold() >= 0);

        System.out.println("角色基本信息验证通过");
        System.out.println("  - charguid: " + character.getCharguid());
        System.out.println("  - name: " + character.getName());
        System.out.println("  - job: " + character.getJob());
        System.out.println("  - level: " + character.getLevel());
        System.out.println("  - hp: " + character.getHp());
        System.out.println("  - mp: " + character.getMp());
        System.out.println("  - exp: " + character.getExp());
        System.out.println("  - gold: " + character.getGold());
    }

    /**
     * 验证背包数据
     */
    private void verifyBagData(GameData gameData) {
        assertNotNull("背包对象为空", gameData.getBag());
        assertNotNull("背包物品为空", gameData.getBag().getItems());
        assertTrue("背包物品数量不足", gameData.getBag().getItems().size() >= 5);
        System.out.println("背包数据验证通过，物品数量: " + gameData.getBag().getItems().size());
    }

    /**
     * 验证装备数据
     */
    private void verifyEquipmentData(GameData gameData) {
        assertNotNull("装备对象为空", gameData.getEquipments());
        assertTrue("装备数量不足", gameData.getEquipments().size() >= 1);
        System.out.println("装备数据验证通过，装备数量: " + gameData.getEquipments().size());
    }

    /**
     * 验证技能数据
     */
    private void verifySkillData(GameData gameData) {
        assertNotNull("技能对象为空", gameData.getSkills());
        assertTrue("技能数量不足", gameData.getSkills().size() >= 3);
        System.out.println("技能数据验证通过，技能数量: " + gameData.getSkills().size());
    }

    /**
     * 验证任务数据
     */
    private void verifyQuestData(GameData gameData) {
        assertNotNull("任务对象为空", gameData.getQuests());
        assertTrue("任务数量不足", gameData.getQuests().size() >= 2);
        System.out.println("任务数据验证通过，任务数量: " + gameData.getQuests().size());
    }

    /**
     * 验证成就数据
     */
    private void verifyAchievementData(GameData gameData) {
        assertNotNull("成就对象为空", gameData.getAchievements());
        assertTrue("成就数量不足", gameData.getAchievements().size() >= 1);
        System.out.println("成就数据验证通过，成就数量: " + gameData.getAchievements().size());
    }

    /**
     * 验证数据库记录
     */
    private void verifyDatabaseRecords() throws Exception {
        String sql = "SELECT status, lastLoginTime FROM t_character WHERE charguid = ?";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setLong(1, CHARACTER_GUID);

        ResultSet rs = stmt.executeQuery();
        assertTrue("查询失败", rs.next());

        int status = rs.getInt("status");
        Timestamp lastLoginTime = rs.getTimestamp("lastLoginTime");

        assertEquals("角色状态不正确", STATUS_IN_GAME, status);
        assertNotNull("最后登录时间为空", lastLoginTime);

        // 验证最后登录时间在最近1分钟内
        long timeDiff = System.currentTimeMillis() - lastLoginTime.getTime();
        assertTrue("最后登录时间不在最近1分钟内", timeDiff < 60 * 1000);

        System.out.println("数据库记录验证通过");
        System.out.println("  - 角色状态: " + status + " (游戏中)");
        System.out.println("  - 最后登录时间: " + lastLoginTime);

        rs.close();
        stmt.close();
    }

    /**
     * 验证日志记录
     */
    private void verifyLogRecord() throws Exception {
        // 这里简化处理，实际应该检查日志文件
        System.out.println("日志记录验证: 需要检查日志文件中是否记录了进入游戏事件");
        System.out.println("  - 搜索关键字: " + TEST_OPENID);
        System.out.println("  - 日志级别: INFO");
        System.out.println("  - 日志内容: 应该包含进入游戏信息");
        System.out.println("  - 角色GUID: " + CHARACTER_GUID);
        System.out.println("  - 角色名称: " + CHARACTER_NAME);

        // TODO: 实际实现应该读取日志文件并验证
        // String logContent = readLogFile("logs/game.log");
        // assertTrue("日志中未记录进入游戏事件", logContent.contains(TEST_OPENID));
    }

    /**
     * 准备测试数据
     */
    private void prepareTestData() throws Exception {
        System.out.println("准备测试数据");

        // 删除旧数据
        cleanupTestData();

        // 创建测试账号
        String insertAccountSql = "INSERT INTO t_account (openid, accountID, createTime, status) VALUES (?, ?, NOW(), 1)";
        PreparedStatement stmt1 = dbConnection.prepareStatement(insertAccountSql);
        stmt1.setString(1, TEST_OPENID);
        stmt1.setLong(2, TEST_ACCOUNT_ID);
        stmt1.executeUpdate();
        stmt1.close();
        System.out.println("创建测试账号: " + TEST_ACCOUNT_ID);

        // 创建测试角色
        String insertCharacterSql = "INSERT INTO t_character (charguid, accountID, name, job, level, createTime, status) VALUES (?, ?, ?, ?, ?, NOW(), 1)";
        PreparedStatement stmt2 = dbConnection.prepareStatement(insertCharacterSql);
        stmt2.setLong(1, CHARACTER_GUID);
        stmt2.setLong(2, TEST_ACCOUNT_ID);
        stmt2.setString(3, CHARACTER_NAME);
        stmt2.setInt(4, 1); // 战士
        stmt2.setInt(5, 10);
        stmt2.executeUpdate();
        stmt2.close();
        System.out.println("创建测试角色: " + CHARACTER_GUID);

        // 配置角色背包数据
        String insertBagSql = "INSERT INTO t_character_bag (charguid, index, itemID, count) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt3 = dbConnection.prepareStatement(insertBagSql);
        for (int i = 1; i <= 5; i++) {
            stmt3.setLong(1, CHARACTER_GUID);
            stmt3.setInt(2, i);
            stmt3.setInt(3, 1000 + i);
            stmt3.setInt(4, 6 - i);
            stmt3.executeUpdate();
        }
        stmt3.close();
        System.out.println("配置角色背包数据: 5个物品");

        // 配置角色装备数据
        String insertEquipmentSql = "INSERT INTO t_character_equipment (charguid, guid, index, itemID) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt4 = dbConnection.prepareStatement(insertEquipmentSql);
        stmt4.setLong(1, CHARACTER_GUID);
        stmt4.setLong(2, 2001);
        stmt4.setInt(3, 1);
        stmt4.setInt(4, 2001);
        stmt4.executeUpdate();
        stmt4.close();
        System.out.println("配置角色装备数据: 1件装备");

        // 配置角色技能数据
        String insertSkillSql = "INSERT INTO t_character_skill (charguid, skillID, level) VALUES (?, ?, ?)";
        PreparedStatement stmt5 = dbConnection.prepareStatement(insertSkillSql);
        for (int i = 1; i <= 3; i++) {
            stmt5.setLong(1, CHARACTER_GUID);
            stmt5.setInt(2, 100 + i);
            stmt5.setInt(3, 1);
            stmt5.executeUpdate();
        }
        stmt5.close();
        System.out.println("配置角色技能数据: 3个技能");

        // 配置角色任务数据
        String insertQuestSql = "INSERT INTO t_character_quest (charguid, questID, status) VALUES (?, ?, ?)";
        PreparedStatement stmt6 = dbConnection.prepareStatement(insertQuestSql);
        for (int i = 1; i <= 2; i++) {
            stmt6.setLong(1, CHARACTER_GUID);
            stmt6.setInt(2, 1000 + i);
            stmt6.setInt(3, i == 1 ? 1 : 0);
            stmt6.executeUpdate();
        }
        stmt6.close();
        System.out.println("配置角色任务数据: 2个任务");

        // 配置角色成就数据
        String insertAchievementSql = "INSERT INTO t_character_achievement (charguid, achievementID, status) VALUES (?, ?, ?)";
        PreparedStatement stmt7 = dbConnection.prepareStatement(insertAchievementSql);
        stmt7.setLong(1, CHARACTER_GUID);
        stmt7.setInt(2, 1001);
        stmt7.setInt(3, 1);
        stmt7.executeUpdate();
        stmt7.close();
        System.out.println("配置角色成就数据: 1个成就");
    }

    /**
     * 清理测试数据
     */
    private void cleanupTestData() throws Exception {
        System.out.println("清理测试数据");

        // 删除角色数据
        String[] deleteTables = {
            "t_character_achievement",
            "t_character_quest",
            "t_character_skill",
            "t_character_equipment",
            "t_character_bag"
        };

        for (String table : deleteTables) {
            String sql = "DELETE FROM " + table + " WHERE charguid = ?";
            PreparedStatement stmt = dbConnection.prepareStatement(sql);
            stmt.setLong(1, CHARACTER_GUID);
            int count = stmt.executeUpdate();
            stmt.close();
            System.out.println("删除" + table + "记录: " + count + " 条");
        }

        // 删除角色
        String deleteCharacterSql = "DELETE FROM t_character WHERE accountID = ?";
        PreparedStatement stmt1 = dbConnection.prepareStatement(deleteCharacterSql);
        stmt1.setLong(1, TEST_ACCOUNT_ID);
        int characterCount = stmt1.executeUpdate();
        stmt1.close();
        System.out.println("删除角色记录: " + characterCount + " 条");

        // 删除账号
        String deleteAccountSql = "DELETE FROM t_account WHERE accountID = ?";
        PreparedStatement stmt2 = dbConnection.prepareStatement(deleteAccountSql);
        stmt2.setLong(1, TEST_ACCOUNT_ID);
        int accountCount = stmt2.executeUpdate();
        stmt2.close();
        System.out.println("删除账号记录: " + accountCount + " 条");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("========== 开始清理测试数据 ==========");

        // 步骤16: 清理测试数据
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
