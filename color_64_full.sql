-- MySQL dump 10.13  Distrib 5.5.30, for osx10.6 (i386)
--
-- Host: localhost    Database: fuzzy
-- ------------------------------------------------------
-- Server version	5.5.30

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
-- Table structure for table `expression`
--

DROP TABLE IF EXISTS `expression`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expression` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `op` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expression`
--

LOCK TABLES `expression` WRITE;
/*!40000 ALTER TABLE `expression` DISABLE KEYS */;
INSERT INTO `expression` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0),(11,0),(12,0),(13,0),(14,0),(15,0),(16,0),(17,0),(18,0),(19,0),(20,0),(21,0),(22,0),(23,0),(24,0),(25,0),(26,0),(27,0),(28,0),(29,0),(30,0),(31,0),(32,0),(33,0),(34,0),(35,0),(36,0),(37,0),(38,0),(39,0),(40,0),(41,0),(42,0),(43,0),(44,0),(45,0),(46,0),(47,0),(48,0),(49,0),(50,0),(51,0),(52,0),(53,0),(54,0),(55,0),(56,0),(57,0),(58,0),(59,0),(60,0),(61,0),(62,0),(63,0),(64,0),(65,0),(66,0),(67,0),(68,0),(69,0),(70,0),(71,0),(72,0),(73,0),(74,0),(75,0),(76,0),(77,0),(78,0),(79,0),(80,0),(81,0),(82,0),(83,0),(84,0),(85,0),(86,0),(87,0),(88,0),(89,0),(90,0),(91,0),(92,0),(93,0),(94,0),(95,0),(96,0),(97,0),(98,0),(99,0),(100,0),(101,0),(102,0),(103,0),(104,0),(105,0),(106,0),(107,0),(108,0),(109,0),(110,0),(111,0),(112,0),(113,0),(114,0),(115,0),(116,0),(117,0),(118,0),(119,0),(120,0),(121,0),(122,0),(123,0),(124,0),(125,0),(126,0),(127,0),(128,0);
/*!40000 ALTER TABLE `expression` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exprstate`
--

DROP TABLE IF EXISTS `exprstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exprstate` (
  `statement_id` int(11) NOT NULL,
  `expression_id` int(11) NOT NULL,
  PRIMARY KEY (`statement_id`,`expression_id`),
  KEY `FK1E59FBBCB559B915` (`statement_id`),
  KEY `FK1E59FBBC8A052255` (`expression_id`),
  CONSTRAINT `FK1E59FBBC8A052255` FOREIGN KEY (`expression_id`) REFERENCES `expression` (`id`),
  CONSTRAINT `FK1E59FBBCB559B915` FOREIGN KEY (`statement_id`) REFERENCES `statement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exprstate`
--

LOCK TABLES `exprstate` WRITE;
/*!40000 ALTER TABLE `exprstate` DISABLE KEYS */;
INSERT INTO `exprstate` VALUES (1,2),(1,4),(1,6),(1,8),(1,10),(1,12),(1,14),(1,16),(1,18),(1,20),(1,22),(1,24),(1,26),(1,28),(1,30),(1,32),(1,34),(1,36),(1,38),(1,40),(1,42),(1,44),(1,46),(1,48),(1,50),(1,52),(1,54),(1,56),(1,58),(1,60),(1,62),(1,64),(2,66),(2,68),(2,70),(2,72),(2,74),(2,76),(2,78),(2,80),(2,82),(2,84),(2,86),(2,88),(2,90),(2,92),(2,94),(2,96),(2,98),(2,100),(2,102),(2,104),(2,106),(2,108),(2,110),(2,112),(2,114),(2,116),(2,118),(2,120),(2,122),(2,124),(2,126),(2,128),(3,2),(3,4),(3,6),(3,8),(3,10),(3,12),(3,14),(3,16),(3,18),(3,20),(3,22),(3,24),(3,26),(3,28),(3,30),(3,32),(3,66),(3,68),(3,70),(3,72),(3,74),(3,76),(3,78),(3,80),(3,82),(3,84),(3,86),(3,88),(3,90),(3,92),(3,94),(3,96),(4,34),(4,36),(4,38),(4,40),(4,42),(4,44),(4,46),(4,48),(4,50),(4,52),(4,54),(4,56),(4,58),(4,60),(4,62),(4,64),(4,98),(4,100),(4,102),(4,104),(4,106),(4,108),(4,110),(4,112),(4,114),(4,116),(4,118),(4,120),(4,122),(4,124),(4,126),(4,128),(5,2),(5,4),(5,6),(5,8),(5,10),(5,12),(5,14),(5,16),(5,34),(5,36),(5,38),(5,40),(5,42),(5,44),(5,46),(5,48),(5,66),(5,68),(5,70),(5,72),(5,74),(5,76),(5,78),(5,80),(5,98),(5,100),(5,102),(5,104),(5,106),(5,108),(5,110),(5,112),(6,18),(6,20),(6,22),(6,24),(6,26),(6,28),(6,30),(6,32),(6,50),(6,52),(6,54),(6,56),(6,58),(6,60),(6,62),(6,64),(6,82),(6,84),(6,86),(6,88),(6,90),(6,92),(6,94),(6,96),(6,114),(6,116),(6,118),(6,120),(6,122),(6,124),(6,126),(6,128),(7,2),(7,4),(7,6),(7,8),(7,18),(7,20),(7,22),(7,24),(7,34),(7,36),(7,38),(7,40),(7,50),(7,52),(7,54),(7,56),(7,66),(7,68),(7,70),(7,72),(7,82),(7,84),(7,86),(7,88),(7,98),(7,100),(7,102),(7,104),(7,114),(7,116),(7,118),(7,120),(8,10),(8,12),(8,14),(8,16),(8,26),(8,28),(8,30),(8,32),(8,42),(8,44),(8,46),(8,48),(8,58),(8,60),(8,62),(8,64),(8,74),(8,76),(8,78),(8,80),(8,90),(8,92),(8,94),(8,96),(8,106),(8,108),(8,110),(8,112),(8,122),(8,124),(8,126),(8,128),(9,2),(9,4),(9,10),(9,12),(9,18),(9,20),(9,26),(9,28),(9,34),(9,36),(9,42),(9,44),(9,50),(9,52),(9,58),(9,60),(9,66),(9,68),(9,74),(9,76),(9,82),(9,84),(9,90),(9,92),(9,98),(9,100),(9,106),(9,108),(9,114),(9,116),(9,122),(9,124),(10,6),(10,8),(10,14),(10,16),(10,22),(10,24),(10,30),(10,32),(10,38),(10,40),(10,46),(10,48),(10,54),(10,56),(10,62),(10,64),(10,70),(10,72),(10,78),(10,80),(10,86),(10,88),(10,94),(10,96),(10,102),(10,104),(10,110),(10,112),(10,118),(10,120),(10,126),(10,128),(11,2),(11,6),(11,10),(11,14),(11,18),(11,22),(11,26),(11,30),(11,34),(11,38),(11,42),(11,46),(11,50),(11,54),(11,58),(11,62),(11,66),(11,70),(11,74),(11,78),(11,82),(11,86),(11,90),(11,94),(11,98),(11,102),(11,106),(11,110),(11,114),(11,118),(11,122),(11,126),(12,4),(12,8),(12,12),(12,16),(12,20),(12,24),(12,28),(12,32),(12,36),(12,40),(12,44),(12,48),(12,52),(12,56),(12,60),(12,64),(12,68),(12,72),(12,76),(12,80),(12,84),(12,88),(12,92),(12,96),(12,100),(12,104),(12,108),(12,112),(12,116),(12,120),(12,124),(12,128),(13,127),(14,1),(14,3),(14,5),(14,7),(14,9),(14,11),(14,13),(14,15),(14,17),(14,19),(14,21),(14,23),(14,25),(14,27),(14,29),(14,31),(14,33),(14,35),(14,37),(14,39),(14,41),(14,43),(14,45),(14,47),(14,49),(14,51),(14,53),(14,55),(14,57),(14,59),(14,61),(14,63),(14,65),(14,67),(14,69),(14,71),(14,73),(14,75),(14,77),(14,79),(14,81),(14,83),(14,85),(14,87),(14,89),(14,91),(14,93),(14,95),(14,97),(14,99),(14,101),(14,103),(14,105),(14,107),(14,109),(14,111),(14,113),(14,115),(14,117),(14,119),(14,121),(14,123),(14,125);
/*!40000 ALTER TABLE `exprstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `func`
--

DROP TABLE IF EXISTS `func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` double NOT NULL,
  `b` double NOT NULL,
  `c` double DEFAULT NULL,
  `d` double DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `func`
--

LOCK TABLES `func` WRITE;
/*!40000 ALTER TABLE `func` DISABLE KEYS */;
INSERT INTO `func` VALUES (1,0,1,NULL,NULL,7),(2,0,1,NULL,NULL,8),(3,0,0.8,NULL,NULL,8),(4,0.2,1,NULL,NULL,7);
/*!40000 ALTER TABLE `func` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuzzyset`
--

DROP TABLE IF EXISTS `fuzzyset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuzzyset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `func_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK681022183264D3D5` (`func_id`),
  CONSTRAINT `FK681022183264D3D5` FOREIGN KEY (`func_id`) REFERENCES `func` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuzzyset`
--

LOCK TABLES `fuzzyset` WRITE;
/*!40000 ALTER TABLE `fuzzyset` DISABLE KEYS */;
INSERT INTO `fuzzyset` VALUES (1,'high',1),(2,'low',2),(3,'solid',3),(4,'edge',4);
/*!40000 ALTER TABLE `fuzzyset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modificator`
--

DROP TABLE IF EXISTS `modificator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modificator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA3D851CF1FC202D5` (`variable_id`),
  CONSTRAINT `FKA3D851CF1FC202D5` FOREIGN KEY (`variable_id`) REFERENCES `variable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modificator`
--

LOCK TABLES `modificator` WRITE;
/*!40000 ALTER TABLE `modificator` DISABLE KEYS */;
/*!40000 ALTER TABLE `modificator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conc_id` int(11) DEFAULT NULL,
  `cond_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3596FC7AEB418B` (`cond_id`),
  KEY `FK3596FC7AEACD2C` (`conc_id`),
  CONSTRAINT `FK3596FC7AEACD2C` FOREIGN KEY (`conc_id`) REFERENCES `expression` (`id`),
  CONSTRAINT `FK3596FC7AEB418B` FOREIGN KEY (`cond_id`) REFERENCES `expression` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` VALUES (1,1,2),(2,3,4),(3,5,6),(4,7,8),(5,9,10),(6,11,12),(7,13,14),(8,15,16),(9,17,18),(10,19,20),(11,21,22),(12,23,24),(13,25,26),(14,27,28),(15,29,30),(16,31,32),(17,33,34),(18,35,36),(19,37,38),(20,39,40),(21,41,42),(22,43,44),(23,45,46),(24,47,48),(25,49,50),(26,51,52),(27,53,54),(28,55,56),(29,57,58),(30,59,60),(31,61,62),(32,63,64),(33,65,66),(34,67,68),(35,69,70),(36,71,72),(37,73,74),(38,75,76),(39,77,78),(40,79,80),(41,81,82),(42,83,84),(43,85,86),(44,87,88),(45,89,90),(46,91,92),(47,93,94),(48,95,96),(49,97,98),(50,99,100),(51,101,102),(52,103,104),(53,105,106),(54,107,108),(55,109,110),(56,111,112),(57,113,114),(58,115,116),(59,117,118),(60,119,120),(61,121,122),(62,123,124),(63,125,126),(64,127,128);
/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statement`
--

DROP TABLE IF EXISTS `statement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weight` double DEFAULT NULL,
  `fuzzyset_id` int(11) DEFAULT NULL,
  `modificator_id` int(11) DEFAULT NULL,
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK83B7296F9ECD4915` (`modificator_id`),
  KEY `FK83B7296FBCBA2635` (`fuzzyset_id`),
  KEY `FK83B7296F1FC202D5` (`variable_id`),
  CONSTRAINT `FK83B7296F1FC202D5` FOREIGN KEY (`variable_id`) REFERENCES `variable` (`id`),
  CONSTRAINT `FK83B7296F9ECD4915` FOREIGN KEY (`modificator_id`) REFERENCES `modificator` (`id`),
  CONSTRAINT `FK83B7296FBCBA2635` FOREIGN KEY (`fuzzyset_id`) REFERENCES `fuzzyset` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statement`
--

LOCK TABLES `statement` WRITE;
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
INSERT INTO `statement` VALUES (1,NULL,1,NULL,1),(2,NULL,2,NULL,1),(3,NULL,1,NULL,2),(4,NULL,2,NULL,2),(5,NULL,1,NULL,3),(6,NULL,2,NULL,3),(7,NULL,1,NULL,4),(8,NULL,2,NULL,4),(9,NULL,1,NULL,5),(10,NULL,2,NULL,5),(11,NULL,1,NULL,6),(12,NULL,2,NULL,6),(13,1,3,NULL,7),(14,1,4,NULL,7);
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termvar`
--

DROP TABLE IF EXISTS `termvar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `termvar` (
  `fuzzyset_id` int(11) NOT NULL,
  `variable_id` int(11) NOT NULL,
  PRIMARY KEY (`fuzzyset_id`,`variable_id`),
  KEY `FKAB261B3BBCBA2635` (`fuzzyset_id`),
  KEY `FKAB261B3B1FC202D5` (`variable_id`),
  CONSTRAINT `FKAB261B3B1FC202D5` FOREIGN KEY (`variable_id`) REFERENCES `variable` (`id`),
  CONSTRAINT `FKAB261B3BBCBA2635` FOREIGN KEY (`fuzzyset_id`) REFERENCES `fuzzyset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termvar`
--

LOCK TABLES `termvar` WRITE;
/*!40000 ALTER TABLE `termvar` DISABLE KEYS */;
INSERT INTO `termvar` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(3,7),(4,7);
/*!40000 ALTER TABLE `termvar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variable`
--

DROP TABLE IF EXISTS `variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `max` double NOT NULL,
  `min` double NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variable`
--

LOCK TABLES `variable` WRITE;
/*!40000 ALTER TABLE `variable` DISABLE KEYS */;
INSERT INTO `variable` VALUES (1,1,0,'d_p1p2'),(2,1,0,'d_p2p3'),(3,1,0,'d_p3p4'),(4,1,0,'d_p4p1'),(5,1,0,'d_p1p3'),(6,1,0,'d_p2p4'),(7,1,0,'mask');
/*!40000 ALTER TABLE `variable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-25 15:56:48
