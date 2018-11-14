-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: hotel_db
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `reservation_id` int(15) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `payed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (137),(137);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `payed` tinyint(4) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (77,NULL,0,NULL),(79,NULL,0,NULL),(81,NULL,0,NULL),(83,NULL,0,NULL),(85,NULL,0,NULL),(87,NULL,0,NULL),(88,'2018-03-29 02:00:00',1,'2018-03-29 02:00:00'),(90,NULL,0,NULL),(92,NULL,0,NULL),(95,NULL,0,NULL),(96,'2018-03-29 02:00:00',1,'2018-03-29 02:00:00'),(98,NULL,0,NULL),(100,NULL,0,NULL),(105,'2018-03-29 02:00:00',1,'2018-03-29 02:00:00'),(107,NULL,0,NULL),(111,'2018-03-29 02:00:00',1,'2018-03-29 02:00:00'),(121,NULL,0,NULL),(123,NULL,0,NULL),(124,'2018-03-29 02:00:00',1,'2018-03-29 02:00:00'),(126,NULL,0,NULL),(128,NULL,0,NULL),(130,NULL,0,NULL),(132,NULL,0,NULL),(135,NULL,0,NULL),(136,'2018-03-29 02:00:00',1,'2018-03-29 02:00:00');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_id`
--

DROP TABLE IF EXISTS `reservation_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_id` (
  `reservation_reservation_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8hihkopet2nqedlwmbatw96c7` (`reservation_reservation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_id`
--

LOCK TABLES `reservation_id` WRITE;
/*!40000 ALTER TABLE `reservation_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `beds` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `reservation_id` int(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbqjc1we17tbqewgyxup8i010w` (`reservation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (25,1,'anastazja',32,0),(26,1,'kunegunda',34,0),(59,3,'dewarfe',34,0),(40,3,'rewa',35,0),(57,4,'fdgfdsgv',34,0),(43,3,'hhhhh',56,0),(44,2,'gfdxgf',44,0),(45,4,'fdzx',67,0),(46,3,'fdszfds',56,0),(52,4,'fdzv',23,0),(49,1,'fdszv',34,0),(50,4,'rfes',34,0),(51,3,'rewa',34,0),(76,1,'anastazja1',32,0),(78,1,'anastazja34',62,0),(80,1,'anastazja34',62,0),(82,4,'KAJA',56,0),(84,1,'anastazja334',62,0),(86,1,'KAJA',62,0),(89,1,'KAJA',62,0),(91,1,'KAJA',62,92),(94,1,'KAJA',62,95),(97,1,'KAJA',62,98),(99,1,'KAJA',62,100),(106,1,'KAJA',62,107),(120,1,'KAJA',62,121),(122,1,'ANIA',62,123),(125,1,'ANIA',62,126),(127,1,'ANIA',62,128),(129,1,'ANIA',62,130),(131,1,'ANIA',62,132),(134,1,'ANIA',62,135);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `employee` tinyint(1) DEFAULT '0',
  `amount_of_reservations` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (56,'magda','magda','magda','magda',1,NULL),(9,'test1','test1','test1','test1',1,NULL),(11,'alek','alek','alek','alek',0,NULL),(12,'alek1','alek1','alek1','alek1',1,NULL),(13,'admin@email.com','admin','pass','admin',0,NULL),(14,'employee1','employee1','employee1','employee1',1,NULL),(61,'employee2','employee2','employee2','employee2',1,NULL),(62,'ania5','ania5','ania5','ania5',1,NULL),(63,'ania5','ania6','ania5','ania6',1,NULL),(64,'ania5','ania77','ania77','ania6',1,NULL),(65,'ania5','ania777','ania77','ania6',1,NULL),(66,'ania5','ania7775','ania77','ania6',1,2),(67,'ada1','ada1','ada1','ada1',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-14 21:32:53
