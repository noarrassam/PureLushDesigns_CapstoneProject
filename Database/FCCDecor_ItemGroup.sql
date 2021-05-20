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
-- Table structure for table `ItemGroup`
--

DROP TABLE IF EXISTS `ItemGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ItemGroup` (
  `itemGroupID` int NOT NULL AUTO_INCREMENT,
  `itemName` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `description` varchar(80) DEFAULT NULL,
  `size` varchar(25) DEFAULT NULL,
  `colour` varchar(45) DEFAULT NULL,
  `initialCost` decimal(10,2) DEFAULT NULL,
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `multiBarcode` varchar(8) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `supplierID` int DEFAULT '0',
  `supplierName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`itemGroupID`),
  KEY `supplierID_idx` (`supplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemGroup`
--

LOCK TABLES `ItemGroup` WRITE;
/*!40000 ALTER TABLE `ItemGroup` DISABLE KEYS */;
INSERT INTO `ItemGroup` VALUES (3,'Overlay-Satin','tablecloth Master','Overlay satin a tablecloth...','6.00','white',5.00,'ijsk7','yes',1,0,NULL),(4,'flowers','Backdrop Fabrics Master Master','velvet...','20.00','white',12.30,'jjsk2','yes',1,2,NULL),(52,'flowers','florals','Flowers...','3.00','Red',7.00,'FLWS','yes',1,0,'2'),(53,'Plastic Tongs','serveware catering','Plastic tongs','8.00','no',5.50,'LLS','yes',1,0,'4'),(54,'pot','serveware catering','Pots here','3.00','brown',10.00,'KKS','yes',1,0,'Islington Flowers'),(55,'large floral arrangements','florals','Florals..','8.00','vary',150.00,'FLWS','yes',1,2,'Islington Flowers'),(56,'small square vase','Centerpiece & Glass Vase','vase golden','8.00','rose gold',4.95,'FLWS',NULL,1,0,'Ashpalt Now'),(57,'Cherry Blossom Branches','serveware catering','dgfhk...','8.00','white',7.00,'FLWS','no',20,0,'Ashpalt Now'),(59,'Cherry Blossom Branches','serveware catering','hjv','8.00','white',7.00,'FLWS','yes',1,0,'Islington Flowers'),(60,'Cherry Blossom Branches','serveware catering','hjv','8.00','white',7.00,'FLWS','yes',1,0,'Islington Flowers'),(61,'Cherry Blossom Branches','serveware catering','hjv','8.00','white',7.00,'FLWS','no',10,0,'Islington Flowers'),(62,'Kissing-Flower Ball','Kissing Balls','Balls...','8.00','Yellow',7.00,'FLWS','yes',1,0,'Sticks and Stones'),(63,'Kissing-Flower Ball','Kissing Balls','balls.','3.00','white',150.00,'FLWS','yes',1,0,'Ashpalt Now'),(64,'Kissing-Flower Ball','Kissing Balls','balls.','3.00','white',150.00,'FLWS','yes',1,0,'Ashpalt Now'),(65,'Kissing-Flower Ball','Kissing Balls','balls.','3.00','white',150.00,'FLWS','yes',1,0,'Ashpalt Now'),(66,'flowers','florals','flowers','8.00','white',150.00,'FLWS','yes',1,0,'Islington Flowers'),(67,'flowers','florals','flowers','8.00','white',150.00,'FLWS','yes',1,0,'Islington Flowers'),(68,'Statue','Furniture & Misc','A big one','10.00','Blue',100.00,'M8232','yes',1,0,'Islington Flowers'),(74,'Cherry Blossom Branches','serveware catering','sd','3.00','brown',7.00,'FLWS','no',5,0,'Islington Flowers'),(90,'hj','2','f2s','smlal','dfhj',3.00,'dfg',NULL,1,0,'2'),(93,'Glue Sticks','Maint $ Supply','It is aGlue Stick','bag','Transparent',9.95,'M&S','yes',10,0,'4');
/*!40000 ALTER TABLE `ItemGroup` ENABLE KEYS */;
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

-- Dump completed on 2021-05-20 16:42:50
