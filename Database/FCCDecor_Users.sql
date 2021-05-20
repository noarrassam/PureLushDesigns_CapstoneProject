-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: fccdecortest.cgecpjdjy8n1.ca-central-1.rds.amazonaws.com    Database: FCCDecor
-- ------------------------------------------------------
-- Server version	8.0.20

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `JoiningDate` datetime NOT NULL,
  `LeavingDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Administrator','admin@admin.com','200ceb26807d6bf99fd6f4f0d1ca54d4','Administrator','Active','2021-03-03 00:00:00',NULL),(2,'Noar','Noar@gmail.com','827ccb0eea8a706c4c34a16891f84e7b','General User','Active','2021-03-28 15:42:24',NULL),(21,'Sally','Sally@gmail.com','e10adc3949ba59abbe56e057f20f883e','General User','Active','2021-03-30 18:33:59',NULL),(22,'John','John@gmail.com','e10adc3949ba59abbe56e057f20f883e','General User','Active','2021-03-30 18:50:19',NULL),(23,'Rashed','rashed@gmail.com','fcea920f7412b5da7be0cf42b8c93759','General User','Inactive','2021-03-31 17:27:45','2021-03-31 17:28:10'),(24,'Mark','Mark@gmail.com','827ccb0eea8a706c4c34a16891f84e7b','Manager','Active','2021-04-02 20:35:13',NULL),(25,'Stephane','s@h.com','81dc9bdb52d04dc20036dbd8313ed055','General User','Active','2021-04-15 05:32:45',NULL),(26,'Mary','m@d.com','81dc9bdb52d04dc20036dbd8313ed055','General User','Active','2021-04-15 06:14:48',NULL),(27,'Peter','pet@aa.com','81dc9bdb52d04dc20036dbd8313ed055','Manager','Inactive','2021-05-09 03:35:23','2021-05-09 03:35:32');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-20 16:42:51
