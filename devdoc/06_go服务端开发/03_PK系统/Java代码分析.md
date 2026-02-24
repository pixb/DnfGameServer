# PK系统Java代码分析

## 1. 核心类与接口

### 1.1 PK匹配相关类

- **PKMatcher**：负责PK匹配逻辑，根据玩家属性进行智能匹配
  - 核心方法：`matchPlayers(List<Player> players)` - 匹配玩家
  - 依赖：`PlayerService`、`PKConfig`

- **PKQueue**：维护PK等待队列
  - 核心方法：`addPlayer(Player player)` - 添加玩家到队列
  - 核心方法：`removePlayer(Player player)` - 从队列移除玩家

### 1.2 战斗相关类

- **PKBattle**：处理PK战斗逻辑
  - 核心方法：`startBattle(Player p1, Player p2)` - 开始战斗
  - 核心方法：`calculateDamage(Skill skill, Player attacker, Player defender)` - 计算伤害

- **PKCalculator**：战斗计算器
  - 核心方法：`calculateCombo(int hits)` - 计算连击加成
  - 核心方法：`calculateHonor(int result, Player winner, Player loser)` - 计算荣誉值

### 1.3 数据管理类

- **PKRecordService**：管理PK记录
  - 核心方法：`saveRecord(PKRecord record)` - 保存PK记录
  - 核心方法：`getPlayerRecords(Player player)` - 获取玩家PK记录

- **HonorService**：管理荣誉值
  - 核心方法：`updateHonor(Player player, int change)` - 更新荣誉值
  - 核心方法：`getHonorLevel(int honor)` - 获取荣誉等级

### 1.4 排行榜类

- **PKRankingService**：管理PK排行榜
  - 核心方法：`updateRanking(Player player)` - 更新排行榜
  - 核心方法：`getTopPlayers(int limit)` - 获取排行榜前N名

## 2. 关键方法与实现

### 2.1 匹配算法

```java
public List<PKMatch> matchPlayers(List<Player> players) {
    // 1. 按等级分组
    Map<Integer, List<Player>> levelGroups = groupByLevel(players);
    
    // 2. 计算匹配值
    for (List<Player> group : levelGroups.values()) {
        for (Player player : group) {
            player.setMatchValue(calculateMatchValue(player));
        }
    }
    
    // 3. 匹配玩家
    List<PKMatch> matches = new ArrayList<>();
    for (List<Player> group : levelGroups.values()) {
        matches.addAll(matchInGroup(group));
    }
    
    return matches;
}
```

### 2.2 战斗计算

```java
public int calculateDamage(Skill skill, Player attacker, Player defender) {
    // 基础伤害
    int baseDamage = skill.getBaseDamage();
    
    // 攻击力加成
    int attackBonus = attacker.getAttackPower() * skill.getAttackRatio() / 100;
    
    // 防御力减免
    int defenseReduction = defender.getDefense() * skill.getDefenseRatio() / 100;
    
    // 连击加成
    int comboBonus = calculateComboBonus(attacker.getComboCount());
    
    // 最终伤害
    int finalDamage = baseDamage + attackBonus - defenseReduction + comboBonus;
    return Math.max(finalDamage, 1); // 确保至少造成1点伤害
}
```

### 2.3 荣誉值计算

```java
public int calculateHonor(int result, Player winner, Player loser) {
    // 基础荣誉值
    int baseHonor = PKConfig.BASE_HONOR;
    
    // 等级差距修正
    int levelDiff = loser.getLevel() - winner.getLevel();
    int levelBonus = levelDiff * PKConfig.LEVEL_BONUS;
    
    // 连击加成
    int comboBonus = winner.getMaxCombo() * PKConfig.COMBO_BONUS;
    
    // 最终荣誉值
    int finalHonor = baseHonor + levelBonus + comboBonus;
    
    // 胜利方获得荣誉值
    if (result == PKResult.WIN) {
        return finalHonor;
    }
    // 失败方扣除荣誉值
    else if (result == PKResult.LOSE) {
        return -finalHonor / 2;
    }
    // 平局不加减荣誉值
    else {
        return 0;
    }
}
```

## 3. 数据结构

### 3.1 PK记录

```java
public class PKRecord {
    private long id;
    private long winnerId;
    private long loserId;
    private int winnerHonorChange;
    private int loserHonorChange;
    private int duration; // 战斗持续时间（秒）
    private int winnerMaxCombo;
    private int loserMaxCombo;
    private Date battleTime;
    private String battleResult; // WIN, LOSE, DRAW
    
    // getters and setters
}
```

### 3.2 玩家PK数据

```java
public class PlayerPKData {
    private long playerId;
    private int honor;
    private int honorLevel;
    private int totalBattles;
    private int wins;
    private int losses;
    private int draws;
    private int ranking;
    
    // getters and setters
}
```

### 3.3 PK配置

```java
public class PKConfig {
    public static final int BASE_HONOR = 10;
    public static final int LEVEL_BONUS = 2;
    public static final int COMBO_BONUS = 1;
    public static final int MAX_QUEUE_TIME = 300; // 最大排队时间（秒）
    public static final int BATTLE_TIMEOUT = 600; // 战斗超时时间（秒）
    
    // 荣誉等级配置
    public static final Map<Integer, Integer> HONOR_LEVELS = new HashMap<>();
    static {
        HONOR_LEVELS.put(0, 1);      // 新手
        HONOR_LEVELS.put(100, 2);     // 勇士
        HONOR_LEVELS.put(500, 3);     // 专家
        HONOR_LEVELS.put(1000, 4);    // 大师
        HONOR_LEVELS.put(2000, 5);    // 王者
    }
}
```

## 4. 数据库设计

### 4.1 PK记录表 (`t_pk_record`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| id | BIGINT | PRIMARY KEY | 记录ID |
| winner_id | BIGINT | NOT NULL | 胜利者角色ID |
| loser_id | BIGINT | NOT NULL | 失败者角色ID |
| winner_honor_change | INT | NOT NULL | 胜利者荣誉值变化 |
| loser_honor_change | INT | NOT NULL | 失败者荣誉值变化 |
| duration | INT | NOT NULL | 战斗持续时间（秒） |
| winner_max_combo | INT | NOT NULL | 胜利者最大连击数 |
| loser_max_combo | INT | NOT NULL | 失败者最大连击数 |
| battle_time | DATETIME | NOT NULL | 战斗时间 |
| battle_result | VARCHAR(10) | NOT NULL | 战斗结果 |
| create_time | DATETIME | NOT NULL | 创建时间 |

### 4.2 玩家PK数据表 (`t_player_pk_data`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| player_id | BIGINT | PRIMARY KEY | 角色ID |
| honor | INT | NOT NULL | 荣誉值 |
| honor_level | INT | NOT NULL | 荣誉等级 |
| total_battles | INT | NOT NULL | 总战斗场次 |
| wins | INT | NOT NULL | 胜利场次 |
| losses | INT | NOT NULL | 失败场次 |
| draws | INT | NOT NULL | 平局场次 |
| ranking | INT | NOT NULL | 排名 |
| update_time | DATETIME | NOT NULL | 更新时间 |

## 5. 代码优化建议

1. **匹配算法优化**：
   - 当前实现使用简单的等级分组和匹配值计算，可以考虑使用更复杂的匹配算法，如ELO等级分系统
   - 增加匹配池的大小，提高匹配效率

2. **战斗计算优化**：
   - 优化伤害计算公式，考虑更多因素如装备特效、 buff/debuff 等
   - 使用缓存存储常用计算结果，减少重复计算

3. **数据存储优化**：
   - 对PK记录表进行分区，按时间或等级段分区
   - 为玩家PK数据表的排名字段建立索引，提高查询效率

4. **并发处理优化**：
   - 增加并发控制，避免高并发情况下的数据竞争
   - 使用消息队列处理PK结果，提高系统响应速度

5. **代码结构优化**：
   - 提取重复代码到公共方法，减少代码冗余
   - 增加单元测试，提高代码质量

## 6. 总结

Java版本的PK系统实现了完整的PK功能，包括匹配、战斗计算、荣誉值管理和排行榜等核心功能。但在性能优化、代码结构和可扩展性方面仍有改进空间。Go语言实现时，可以借鉴其核心业务逻辑，同时利用Go的并发特性和性能优势，构建更高效、可扩展的PK系统。