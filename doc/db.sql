CREATE DATABASE  IF NOT EXISTS `oath_db_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `oath_db_test`;
-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: 81.68.79.128    Database: oath_db_test
-- ------------------------------------------------------
-- Server version	5.6.50-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_auth`
--

DROP TABLE IF EXISTS `sys_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_auth` (
  `auth_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `auth_name` varchar(255) NOT NULL COMMENT '权限名称',
  `permission` varchar(500) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`auth_id`) USING BTREE,
  UNIQUE KEY `auth_name_UNIQUE` (`auth_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_auth`
--

LOCK TABLES `sys_auth` WRITE;
/*!40000 ALTER TABLE `sys_auth` DISABLE KEYS */;
INSERT INTO `sys_auth` VALUES (1,'查看用户信息','sys:user:info'),(2,'查看所有权限','sys:auth:info'),(3,'查看所有角色','sys:role:info');
/*!40000 ALTER TABLE `sys_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ADMIN'),(2,'USER'),(3,'YICHUAN');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_auth`
--

DROP TABLE IF EXISTS `sys_role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_auth` (
  `role_auth_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `auth_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_auth_id`) USING BTREE,
  UNIQUE KEY `idx_sys_role_auth_role_id_auth_id` (`role_id`,`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_auth`
--

LOCK TABLES `sys_role_auth` WRITE;
/*!40000 ALTER TABLE `sys_role_auth` DISABLE KEYS */;
INSERT INTO `sys_role_auth` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1);
/*!40000 ALTER TABLE `sys_role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `token` varchar(500) DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '状态（1-正常，0-禁用）',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `u_username` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$eSNvIpR92jtOA5GX.UPwWe67hAAIoWAivMonz5v.8HfiX8XPc2RhO',NULL,'0'),(2,'user','$2a$10$szHoqQ64g66PymVJkip98.Fap21Csy8w.RD8v5Dhq08BMEZ9KaSmS',NULL,'0'),(3,'C3Stones','$2a$10$Z6a7DSehk58ypqyWzfFAbOR0gaqpwVzY9aNXKqf4UhDCSJxsbDqDK',NULL,'2'),(4,'yichuan','$2a$10$ZZplIvs./ymp9cEJ/niRT.I8LAsSpnY4KmqCPTM/MsZ5oPXpXLBNC',NULL,'0'),(8,'xiaoming','$2a$10$5DGDGlsVNmCOPV/VgPzlBub4hgDmdjHbNgr06usWfgOBI7oBFE/Xa',NULL,'1'),(9,'test','$2a$10$61gue0JB3fJXlNp91o6a.eDOdBwSbqsvJRDiDNOZZTSTRr7y31vj6',NULL,'1');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  UNIQUE KEY `idx_sys_user_role_user_id_role_id` (`user_id`,`role_id`) COMMENT '用户角色唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1),(2,2,2),(3,3,2),(4,8,2),(5,9,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'oath_db_test'
--

--
-- Dumping routines for database 'oath_db_test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-11 15:51:22
