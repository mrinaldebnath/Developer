CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `developers`
--

DROP TABLE IF EXISTS `developers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `developers` (
  `email` char(255) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `developer` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developers`
--

LOCK TABLES `developers` WRITE;
/*!40000 ALTER TABLE `developers` DISABLE KEYS */;
INSERT INTO `developers` VALUES ('12'),('5stgwgw'),('6sgsg'),('6sygw'),('7swg'),('7zsn'),('8xw'),('hu'),('mrinaldebnath42@gmail.com'),('nw'),('s7u'),('t2ys'),('wj2t2yw'),('xj');
/*!40000 ALTER TABLE `developers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interviews`
--

DROP TABLE IF EXISTS `interviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `interviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interviews`
--

LOCK TABLES `interviews` WRITE;
/*!40000 ALTER TABLE `interviews` DISABLE KEYS */;
INSERT INTO `interviews` VALUES (23,234,'wwqq'),(26,2,'qw'),(27,2,'df');
/*!40000 ALTER TABLE `interviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(255) DEFAULT NULL,
  `developer` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `developer` (`developer`),
  CONSTRAINT `developer` FOREIGN KEY (`developer`) REFERENCES `developers` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'en','12'),(3,'bd','hu'),(4,'ja','mrinaldebnath42@gmail.com');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programminglanguages`
--

DROP TABLE IF EXISTS `programminglanguages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programminglanguages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `developer` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `developer` (`developer`),
  CONSTRAINT `f` FOREIGN KEY (`developer`) REFERENCES `developers` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programminglanguages`
--

LOCK TABLES `programminglanguages` WRITE;
/*!40000 ALTER TABLE `programminglanguages` DISABLE KEYS */;
INSERT INTO `programminglanguages` VALUES (1,'ruby','mrinaldebnath42@gmail.com'),(3,'php','12'),(4,'javascript','mrinaldebnath42@gmail.com'),(14,'scala','hu'),(19,'kotlin','hu'),(24,'python','nw'),(25,'python','12');
/*!40000 ALTER TABLE `programminglanguages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'test'
--

--
-- Dumping routines for database 'test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-14 20:33:32
