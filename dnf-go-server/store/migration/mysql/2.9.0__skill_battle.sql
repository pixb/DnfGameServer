-- 2026-09-07 第六十八轮: 技能效果接入战斗(攻击力加成/冷却秒数)
ALTER TABLE skills ADD COLUMN attack INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '每级攻击力加成' AFTER type;
ALTER TABLE skills ADD COLUMN cooldown INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '冷却秒数' AFTER attack;
ALTER TABLE role_skills ADD COLUMN last_cast_at BIGINT NOT NULL DEFAULT 0 COMMENT '技能最后施放时间戳' AFTER is_learned;
UPDATE skills SET attack=5,  cooldown=5  WHERE skill_id=1001; -- 冲刺
UPDATE skills SET attack=0,  cooldown=8  WHERE skill_id=1002; -- 格挡(防御技)
UPDATE skills SET attack=10, cooldown=10 WHERE skill_id=1101; -- 上挑
UPDATE skills SET attack=15, cooldown=15 WHERE skill_id=1102; -- 崩山击
UPDATE skills SET attack=12, cooldown=12 WHERE skill_id=1201; -- 银光落刃
UPDATE skills SET attack=8,  cooldown=6  WHERE skill_id=1202; -- 三段斩
UPDATE skills SET attack=10, cooldown=8  WHERE skill_id=1301; -- 念气波
UPDATE skills SET attack=20, cooldown=20 WHERE skill_id=1302; -- 雷浑背摔
