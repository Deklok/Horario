-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: horario
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `ee`
--

DROP TABLE IF EXISTS `ee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ee` (
  `idEE` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) DEFAULT NULL,
  `maestro` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEE`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ee`
--

LOCK TABLES `ee` WRITE;
/*!40000 ALTER TABLE `ee` DISABLE KEYS */;
INSERT INTO `ee` VALUES (1,'Principios de Construccion','Freddy'),(2,'Principios de Diseño','Dra Karen');
/*!40000 ALTER TABLE `ee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ee_salon`
--

DROP TABLE IF EXISTS `ee_salon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ee_salon` (
  `idEE_Salon` int(11) NOT NULL AUTO_INCREMENT,
  `idEE` int(11) NOT NULL,
  `idSalon` int(11) NOT NULL,
  PRIMARY KEY (`idEE_Salon`),
  KEY `idEE` (`idEE`),
  KEY `idSalon` (`idSalon`),
  CONSTRAINT `ee_salon_ibfk_1` FOREIGN KEY (`idEE`) REFERENCES `ee` (`idEE`),
  CONSTRAINT `ee_salon_ibfk_2` FOREIGN KEY (`idSalon`) REFERENCES `salon` (`idSalon`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ee_salon`
--

LOCK TABLES `ee_salon` WRITE;
/*!40000 ALTER TABLE `ee_salon` DISABLE KEYS */;
INSERT INTO `ee_salon` VALUES (1,1,2),(2,1,3),(3,2,2),(4,2,4);
/*!40000 ALTER TABLE `ee_salon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salon`
--

DROP TABLE IF EXISTS `salon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salon` (
  `idSalon` int(11) NOT NULL AUTO_INCREMENT,
  `salon` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`idSalon`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salon`
--

LOCK TABLES `salon` WRITE;
/*!40000 ALTER TABLE `salon` DISABLE KEYS */;
INSERT INTO `salon` VALUES (1,'102'),(2,'108'),(3,'CC3'),(4,'104'),(5,'CC2'),(6,'105');
/*!40000 ALTER TABLE `salon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semana`
--

DROP TABLE IF EXISTS `semana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semana` (
  `idSemana` int(11) NOT NULL AUTO_INCREMENT,
  `Dia` int(11) NOT NULL,
  `Hora` int(11) NOT NULL,
  `idEE` int(11) NOT NULL,
  `idSalon` int(11) NOT NULL,
  PRIMARY KEY (`idSemana`),
  KEY `idEE` (`idEE`),
  KEY `idSalon` (`idSalon`),
  CONSTRAINT `semana_ibfk_1` FOREIGN KEY (`idEE`) REFERENCES `ee` (`idEE`),
  CONSTRAINT `semana_ibfk_2` FOREIGN KEY (`idSalon`) REFERENCES `salon` (`idSalon`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semana`
--

LOCK TABLES `semana` WRITE;
/*!40000 ALTER TABLE `semana` DISABLE KEYS */;
INSERT INTO `semana` VALUES (3,0,2,2,4),(4,4,2,2,2),(36,2,0,1,2),(37,1,2,1,2),(66,2,3,1,2),(67,3,2,1,2);
/*!40000 ALTER TABLE `semana` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-10 10:52:35
