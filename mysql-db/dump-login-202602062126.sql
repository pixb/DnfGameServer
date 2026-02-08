-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: login
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_agent`
--

DROP TABLE IF EXISTS `sys_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_agent` (
  `agent_id` varchar(255) NOT NULL COMMENT 'agent_id',
  `code` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `agentId` varchar(128) DEFAULT NULL COMMENT 'agent_id',
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_agent`
--

LOCK TABLES `sys_agent` WRITE;
/*!40000 ALTER TABLE `sys_agent` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` varchar(255) NOT NULL COMMENT 'user_id',
  `login_name` varchar(255) DEFAULT NULL,
  `userId` varchar(128) DEFAULT NULL COMMENT 'user_id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_account`
--

DROP TABLE IF EXISTS `t_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_account` (
  `userID` varchar(64) NOT NULL,
  `passwd` varchar(64) NOT NULL,
  `isStop` tinyint(1) DEFAULT NULL,
  `privilege` tinyint(16) DEFAULT '0',
  `id` varchar(64) NOT NULL,
  `score` int(32) NOT NULL,
  `channelNo` varchar(16) DEFAULT NULL,
  `regDate` varchar(8) DEFAULT NULL,
  `agent_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `username_index` (`userID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_account`
--

LOCK TABLES `t_account` WRITE;
/*!40000 ALTER TABLE `t_account` DISABLE KEYS */;
INSERT INTO `t_account` VALUES ('hutue','123456',0,0,'1739683469377552384',0,'111','20231227','','2023-12-27 00:23:56');
/*!40000 ALTER TABLE `t_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_channel`
--

DROP TABLE IF EXISTS `t_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_channel` (
  `channelNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '渠道号',
  `parentChannel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父渠道',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `auth` tinyint(1) DEFAULT NULL COMMENT '是否总代',
  `point` int(11) DEFAULT NULL COMMENT '剩余点数',
  PRIMARY KEY (`channelNo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_channel`
--

LOCK TABLES `t_channel` WRITE;
/*!40000 ALTER TABLE `t_channel` DISABLE KEYS */;
INSERT INTO `t_channel` VALUES ('111','11','123456',1,100000);
/*!40000 ALTER TABLE `t_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_gm_log`
--

DROP TABLE IF EXISTS `t_gm_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gm_log` (
  `id` varchar(255) NOT NULL,
  `server_id` varchar(11) DEFAULT NULL COMMENT '服务器ID',
  `target_account` varchar(255) DEFAULT NULL COMMENT 'gm命令目标账户',
  `gm_command` varchar(255) DEFAULT NULL COMMENT 'gm命令类型',
  `gm_param` varchar(255) DEFAULT NULL COMMENT 'gm命令参数',
  `status` int(11) DEFAULT NULL COMMENT '操作状态',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `user_id` varchar(255) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gm_log`
--

LOCK TABLES `t_gm_log` WRITE;
/*!40000 ALTER TABLE `t_gm_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_gm_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_invite`
--

DROP TABLE IF EXISTS `t_invite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_invite` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `inviteCode` varchar(255) NOT NULL,
  `channelNo` varchar(255) NOT NULL,
  `state` smallint(6) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_invite`
--

LOCK TABLES `t_invite` WRITE;
/*!40000 ALTER TABLE `t_invite` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_invite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL COMMENT '内部订单ID',
  `tradeNo` varchar(255) DEFAULT NULL COMMENT '第三方订单ID',
  `serverId` int(11) DEFAULT NULL,
  `accountId` varchar(255) DEFAULT NULL,
  `commodityId` int(11) DEFAULT NULL,
  `commodityName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `money` double DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `agentId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`,`orderId`,`serverId`,`accountId`,`commodityId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_server`
--

DROP TABLE IF EXISTS `t_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务器ID',
  `server_name` varchar(255) DEFAULT NULL COMMENT '服务器名称',
  `ip` varchar(255) DEFAULT NULL COMMENT '服务器IP',
  `port` varchar(255) DEFAULT NULL COMMENT '服务器端口',
  `gm_port` varchar(255) DEFAULT NULL COMMENT 'gm端口',
  `open_status` int(1) DEFAULT NULL COMMENT '开启状态 0:关闭,1:开启',
  `congestion` int(11) DEFAULT NULL COMMENT '拥挤',
  `recommend` int(11) DEFAULT NULL COMMENT '推荐',
  `creatable` int(11) DEFAULT NULL COMMENT '可以创造',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_server`
--

LOCK TABLES `t_server` WRITE;
/*!40000 ALTER TABLE `t_server` DISABLE KEYS */;
INSERT INTO `t_server` VALUES (1,'糊涂鳄一区','66.66.66.66','10001',NULL,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'login'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-06 21:26:06
