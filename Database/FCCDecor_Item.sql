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
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `itemName` varchar(50) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `cndition` varchar(50) DEFAULT 'good',
  `itemGroupID` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `ConditionID_idx` (`cndition`),
  KEY `fk_Item_ItemGroup_idx` (`itemGroupID`),
  CONSTRAINT `fk_Item_ItemGroup` FOREIGN KEY (`itemGroupID`) REFERENCES `ItemGroup` (`itemGroupID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=123456904 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (123456830,'hjhj','test',NULL,NULL,NULL),(123456831,'jk','test2',NULL,NULL,NULL),(123456832,'jk','test2',NULL,NULL,NULL),(123456833,'velvet','velvet...',NULL,4,NULL),(123456834,'aaa','desc','Good',NULL,NULL),(123456835,'aaa','desc','Good',NULL,NULL),(123456836,'aaa','desc','Good',NULL,NULL),(123456837,'aaa','desc','Good',NULL,NULL),(123456838,'aaa','desc','Good',NULL,NULL),(123456839,'aaa','desc','Good',NULL,NULL),(123456840,'aaa','desc','Good',NULL,NULL),(123456841,'aaa','desc','Good',NULL,NULL),(123456842,'aaa','desc','Good',NULL,NULL),(123456843,'aaa','desc','Good',NULL,NULL),(123456844,'aaa','desc','Good',NULL,NULL),(123456845,'aaa','desc','Good',NULL,NULL),(123456846,'aaa','describe here','Good',NULL,NULL),(123456847,'aaa','describe here','Good',NULL,NULL),(123456849,'aaa','desc','Good',NULL,NULL),(123456867,'pot','Pots here','Good',54,NULL),(123456869,'test','te','Good',NULL,NULL),(123456871,'cherry blossom branches','branches',NULL,57,NULL),(123456874,'Cherry Blossom Branches','dgfhk...','good',57,NULL),(123456875,'Cherry Blossom Branches','hjv','good',60,NULL),(123456881,'Overlay-Satin','Overlay satin a tablecloth...','bad',3,NULL),(123456883,'flowers','velvet...','good',4,1),(123456887,'flowers','flowers','good',67,1),(123456888,'flowers','velvet...','good',4,1),(123456889,'flowers','velvet...','good',4,1),(123456890,'flowers','Flowers...','good',52,1),(123456892,'Overlay-Satin','Overlay satin a tablecloth...','good',3,1),(123456896,'Overlay-Satin','Overlay satin a tablecloth...','good',3,1),(123456899,'Overlay-Satin','Overlay satin a tablecloth...','good',3,1),(123456900,'hj','f2s','good',90,1),(123456901,'small square vase','vase golden','good',56,1),(123456902,'Statue','A big one','good',68,1),(123456903,'Overlay-Satin','Overlay satin a tablecloth...','good',3,1);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
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
