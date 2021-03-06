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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expression`
--

LOCK TABLES `expression` WRITE;
/*!40000 ALTER TABLE `expression` DISABLE KEYS */;
INSERT INTO `expression` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0),(11,0),(12,0),(13,0),(14,0),(15,0),(16,0),(17,0),(18,0),(19,0),(20,0),(21,0),(22,0),(23,0),(24,0),(25,0),(26,0),(27,0),(28,0),(29,0),(30,0),(31,0),(32,0);
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
INSERT INTO `exprstate` VALUES (1,2),(1,6),(1,8),(1,10),(1,12),(1,14),(1,16),(1,18),(2,4),(2,20),(2,22),(2,24),(2,26),(2,28),(2,30),(2,32),(3,2),(3,6),(3,8),(3,10),(3,20),(3,22),(3,24),(3,26),(4,4),(4,12),(4,14),(4,16),(4,18),(4,28),(4,30),(4,32),(5,2),(5,6),(5,12),(5,14),(5,20),(5,22),(5,28),(5,30),(6,4),(6,8),(6,10),(6,16),(6,18),(6,24),(6,26),(6,32),(7,2),(7,8),(7,12),(7,16),(7,20),(7,24),(7,28),(7,32),(8,4),(8,6),(8,10),(8,14),(8,18),(8,22),(8,26),(8,30),(9,3),(10,5),(10,7),(10,9),(10,11),(10,13),(10,15),(10,17),(10,19),(10,21),(10,23),(10,25),(10,27),(10,29),(10,31),(11,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `func`
--

LOCK TABLES `func` WRITE;
/*!40000 ALTER TABLE `func` DISABLE KEYS */;
INSERT INTO `func` VALUES (1,0,255,NULL,NULL,7),(2,0,255,NULL,NULL,8),(3,0,5,NULL,NULL,8),(4,121,133,144.5,NULL,1),(5,250,255,NULL,NULL,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuzzyset`
--

LOCK TABLES `fuzzyset` WRITE;
/*!40000 ALTER TABLE `fuzzyset` DISABLE KEYS */;
INSERT INTO `fuzzyset` VALUES (1,'white',1),(2,'black',2),(3,'black',3),(4,'edge',4),(5,'white',5);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` VALUES (1,1,2),(2,3,4),(3,5,6),(4,7,8),(5,9,10),(6,11,12),(7,13,14),(8,15,16),(9,17,18),(10,19,20),(11,21,22),(12,23,24),(13,25,26),(14,27,28),(15,29,30),(16,31,32);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statement`
--

LOCK TABLES `statement` WRITE;
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
INSERT INTO `statement` VALUES (1,NULL,1,NULL,1),(2,NULL,2,NULL,1),(3,NULL,1,NULL,2),(4,NULL,2,NULL,2),(5,NULL,1,NULL,3),(6,NULL,2,NULL,3),(7,NULL,1,NULL,4),(8,NULL,2,NULL,4),(9,1,3,NULL,5),(10,1,4,NULL,5),(11,1,5,NULL,5);
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
INSERT INTO `termvar` VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,2),(2,3),(2,4),(3,5),(4,5),(5,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variable`
--

LOCK TABLES `variable` WRITE;
/*!40000 ALTER TABLE `variable` DISABLE KEYS */;
INSERT INTO `variable` VALUES (1,255,0,'p1'),(2,255,0,'p2'),(3,255,0,'p3'),(4,255,0,'p4'),(5,255,0,'color');
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

-- Dump completed on 2013-04-14  1:29:10
