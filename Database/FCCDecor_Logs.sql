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
-- Table structure for table `Logs`
--

DROP TABLE IF EXISTS `Logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Logs` (
  `LogID` int NOT NULL AUTO_INCREMENT,
  `id` int NOT NULL,
  `Type` varchar(45) NOT NULL,
  `Activity` varchar(500) NOT NULL,
  `Target` varchar(45) NOT NULL,
  PRIMARY KEY (`LogID`),
  KEY `id_idx` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Logs`
--

LOCK TABLES `Logs` WRITE;
/*!40000 ALTER TABLE `Logs` DISABLE KEYS */;
INSERT INTO `Logs` VALUES (1,1,'account','training','abc'),(2,2,'added category','chairs','category table'),(3,2,'added event','graduation','event table'),(4,1,'Event','Added New Event Name:Pizza, on Date: 2021-04-03','2021-54-13 06:54'),(5,1,'Event','Added New Event Name:Pizza, on Date: 2021-04-03','Apr 13/2021 06:55'),(7,1,'Item','AdministratorAdded New Item :Glue Sticks','Apr 13/2021 10:48'),(8,24,'Event','MarkAdded New Event Name:Pizza again, on Date: 2021-04-07','Apr 14/2021 08:43'),(9,24,'Event','Edited Event Name:Pizza not again, on Date: 2021-04-07','Apr 14/2021 08:43'),(10,24,'Event','MarkAdded New Event Name:Pizza not again, on Date: 2021-04-07','Apr 14/2021 08:43'),(11,24,'Supplier','Mark Added New Supplier Name:test','Apr 14/2021 08:45'),(12,24,'Supplier','Mark Added New Supplier Name:editttttttt','Apr 14/2021 08:45'),(13,24,'Supplier','Deleted Name:editttttttt','Apr 14/2021 08:45'),(14,24,'Item Loaded','Mark Loaded Overlay-Satin, into Event:Test','Apr 14/2021 08:49'),(15,24,'Item Loaded','Mark Loaded Overlay-Satin, into Event:Test','Apr 14/2021 08:52'),(16,24,'Item Loaded','Mark Loaded Overlay-Satin, into Event:Test Dated on: 2021-03-08','Apr 14/2021 08:54'),(17,24,'Item Returned','Mark returned Overlay-Satin, into Event:Test Dated on: 2021-03-08','Apr 14/2021 08:54'),(18,24,'Event','Mark Deleted Event Name:Pizza, on Date: 2021-04-03','Apr 15/2021 02:29'),(19,2,'Item Loaded','Noar reLoaded Overlay-Satin, into Event:Test Dated on: 2021-03-08','Apr 15/2021 04:57'),(20,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 04:57'),(21,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:09'),(22,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:12'),(23,2,'Item Loaded','Noar reLoaded pot, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:15'),(24,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:18'),(25,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:19'),(26,2,'Item Loaded','Noar reLoaded pot, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:21'),(27,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:25'),(28,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:26'),(29,2,'Item Returned','Noar returned flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:26'),(30,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:27'),(31,2,'Item Returned','Noar returned flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:27'),(32,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:28'),(33,2,'Item Returned','Noar returned flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:28'),(34,2,'Items Loaded','Noar Loaded 0 Cherry Blossom Branches, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:29'),(35,2,'Items Returned','Noar returned 2 Cherry Blossom Branches, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:29'),(36,2,'Item Returned','Noar returned Cherry Blossom Branches, into Event:Test Dated on: 2021-03-08','Apr 15/2021 05:29'),(37,1,'Item Loaded','Administrator Loaded flowers, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 05:42'),(38,1,'Item Loaded','Administrator Loaded Cherry Blossom Branches, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 05:42'),(39,1,'Items Loaded','Administrator Loaded 0 Cherry Blossom Branches, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 05:52'),(40,2,'Item Loaded','Noar reLoaded flowers, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 06:11'),(41,2,'Item Loaded','Noar Loaded Overlay-Satin, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 06:11'),(42,2,'Item Loaded','Noar Loaded Overlay-Satin, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 06:11'),(43,2,'Items Loaded','Noar Loaded 0 Cherry Blossom Branches, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 06:11'),(44,2,'Item Returned','Noar returned flowers, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 06:12'),(45,2,'Item Returned','Noar returned Overlay-Satin, into Event:Marriage Dated on: 2020-01-01','Apr 15/2021 06:12'),(46,2,'Item Loaded','Noar reLoaded Overlay-Satin, into Event:Test Dated on: 2021-03-08','Apr 15/2021 06:27'),(47,2,'Item Loaded','Noar reLoaded flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 06:27'),(48,2,'Items Loaded','Noar Loaded 0 Cherry Blossom Branches, into Event:Test Dated on: 2021-03-08','Apr 15/2021 06:27'),(49,2,'Item Returned','Noar returned Cherry Blossom Branches, into Event:Test Dated on: 2021-03-08','Apr 15/2021 06:28'),(50,2,'Item Returned','Noar returned Overlay-Satin, into Event:Test Dated on: 2021-03-08','Apr 15/2021 06:28'),(51,2,'Item Returned','Noar returned flowers, into Event:Test Dated on: 2021-03-08','Apr 15/2021 06:28');
/*!40000 ALTER TABLE `Logs` ENABLE KEYS */;
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

-- Dump completed on 2021-05-20 16:42:54
