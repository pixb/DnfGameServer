-- DnfGameServer 数据库初始化脚本

-- 创建主游戏数据库
CREATE DATABASE IF NOT EXISTS `game` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建登录数据库
CREATE DATABASE IF NOT EXISTS `login` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建跨服数据库
CREATE DATABASE IF NOT EXISTS `game_kuafu` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 显示所有数据库
SHOW DATABASES;
