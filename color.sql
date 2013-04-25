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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expression`
--

LOCK TABLES `expression` WRITE;
/*!40000 ALTER TABLE `expression` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
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

-- Dump completed on 2013-04-25 15:29:20
