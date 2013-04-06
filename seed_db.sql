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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expression`
--

LOCK TABLES `expression` WRITE;
/*!40000 ALTER TABLE `expression` DISABLE KEYS */;
INSERT INTO `expression` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0),(11,0),(12,0),(13,0),(14,0),(15,0),(16,0),(17,0),(18,0),(19,0),(20,0),(21,0),(22,0),(23,0),(24,0),(25,0),(26,0),(27,0),(28,0),(29,0),(30,0),(31,0),(32,0),(33,0),(34,0),(35,0),(36,0),(37,0),(38,0),(39,0),(40,0);
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
INSERT INTO `exprstate` VALUES (31,32),(32,34),(33,36),(34,38),(35,40),(36,31),(37,33),(38,35),(39,37),(40,39);
/*!40000 ALTER TABLE `exprstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exprstatement`
--

DROP TABLE IF EXISTS `exprstatement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exprstatement` (
  `expression_id` int(11) NOT NULL DEFAULT '0',
  `statement_id` int(11) NOT NULL DEFAULT '0',
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`expression_id`,`statement_id`),
  KEY `FK2570D31AB559B915` (`statement_id`),
  KEY `FK2570D31A8A052255` (`expression_id`),
  CONSTRAINT `FK2570D31A8A052255` FOREIGN KEY (`expression_id`) REFERENCES `expression` (`id`),
  CONSTRAINT `FK2570D31AB559B915` FOREIGN KEY (`statement_id`) REFERENCES `statement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exprstatement`
--

LOCK TABLES `exprstatement` WRITE;
/*!40000 ALTER TABLE `exprstatement` DISABLE KEYS */;
/*!40000 ALTER TABLE `exprstatement` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `func`
--

LOCK TABLES `func` WRITE;
/*!40000 ALTER TABLE `func` DISABLE KEYS */;
INSERT INTO `func` VALUES (1,10,30,NULL,NULL,3),(2,20,35,50,NULL,1),(3,40,50,60,NULL,1),(4,50,60,70,NULL,1),(5,60,70,NULL,NULL,5),(6,-72,-36,NULL,NULL,3),(7,-54,-27,0,NULL,1),(8,-18,0,18,NULL,1),(9,0,27,54,NULL,1),(10,36,72,NULL,NULL,5);
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
  `variable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK681022183264D3D5` (`func_id`),
  KEY `FK681022181FC202D5` (`variable_id`),
  CONSTRAINT `FK681022181FC202D5` FOREIGN KEY (`variable_id`) REFERENCES `variable` (`id`),
  CONSTRAINT `FK681022183264D3D5` FOREIGN KEY (`func_id`) REFERENCES `func` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuzzyset`
--

LOCK TABLES `fuzzyset` WRITE;
/*!40000 ALTER TABLE `fuzzyset` DISABLE KEYS */;
INSERT INTO `fuzzyset` VALUES (1,'cold',1,1),(2,'cool',2,1),(3,'warm',3,1),(4,'not hot',4,1),(5,'hot',5,1),(6,'full left',6,2),(7,'left',7,2),(8,'center',8,2),(9,'right',9,2),(10,'full right',10,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` VALUES (1,31,32),(2,33,34),(3,35,36),(4,37,38),(5,39,40);
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
  KEY `FK83B7296F1FC202D5` (`variable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statement`
--

LOCK TABLES `statement` WRITE;
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
INSERT INTO `statement` VALUES (1,NULL,1,NULL,1),(2,NULL,2,NULL,1),(3,NULL,3,NULL,1),(4,NULL,4,NULL,1),(5,NULL,5,NULL,1),(6,1,6,NULL,2),(7,1,7,NULL,2),(8,1,8,NULL,2),(9,1,9,NULL,2),(10,1,10,NULL,2),(11,NULL,1,NULL,1),(12,NULL,2,NULL,1),(13,NULL,3,NULL,1),(14,NULL,4,NULL,1),(15,NULL,5,NULL,1),(16,1,6,NULL,2),(17,1,7,NULL,2),(18,1,8,NULL,2),(19,1,9,NULL,2),(20,1,10,NULL,2),(21,NULL,11,NULL,3),(22,NULL,12,NULL,3),(23,NULL,13,NULL,3),(24,NULL,14,NULL,3),(25,NULL,15,NULL,3),(26,1,16,NULL,4),(27,1,17,NULL,4),(28,1,18,NULL,4),(29,1,19,NULL,4),(30,1,20,NULL,4),(31,NULL,1,NULL,1),(32,NULL,2,NULL,1),(33,NULL,3,NULL,1),(34,NULL,4,NULL,1),(35,NULL,5,NULL,1),(36,1,6,NULL,2),(37,1,7,NULL,2),(38,1,8,NULL,2),(39,1,9,NULL,2),(40,1,10,NULL,2);
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variable`
--

LOCK TABLES `variable` WRITE;
/*!40000 ALTER TABLE `variable` DISABLE KEYS */;
INSERT INTO `variable` VALUES (1,100,0,'water temp'),(2,90,-90,'angle');
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

-- Dump completed on 2013-04-06 12:34:12
