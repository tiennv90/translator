-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: translator
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt-log

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `document_id` bigint(20) NOT NULL auto_increment,
  `created_date` datetime NOT NULL,
  `deleted_date` datetime default NULL,
  `pot_content` varchar(5000) NOT NULL,
  `slug` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`document_id`),
  KEY `FK_c7wu8new39m70ldf2i6vkeu9b` (`project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,'2015-05-02 03:09:51',NULL,'msgid \'Enter a comma separated list of user names.\' msgstr \'\'','updatedocument1','Update Test Document 1',1),(2,'2015-05-02 03:09:51','2015-05-02 08:25:03','msgid \'Enter a comma separated list of user names.\' msgstr \'\'','document2','Test Document 2',1),(3,'2015-05-02 03:13:20','2015-05-02 08:05:22','msgid \'Enter a comma separated list of user names.\' msgstr \'\'','document11','Test Document 11',1),(4,'2015-05-02 03:13:20','2015-05-02 08:07:58','msgid \'Enter a comma separated list of user names.\' msgstr \'\'','document22','Test Document 22',1),(5,'2015-05-02 03:48:16',NULL,'','document3','Test Document 3',2);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentstring`
--

DROP TABLE IF EXISTS `documentstring`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentstring` (
  `documentstring_id` bigint(20) NOT NULL auto_increment,
  `description` varchar(500) NOT NULL,
  `is_public` bit(1) NOT NULL,
  `plurals` int(11) NOT NULL,
  `string_key` varchar(100) NOT NULL,
  `string_value` varchar(500) NOT NULL,
  `document_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`documentstring_id`),
  KEY `FK_aemkif8iroruggythd6gug3e0` (`document_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentstring`
--

LOCK TABLES `documentstring` WRITE;
/*!40000 ALTER TABLE `documentstring` DISABLE KEYS */;
INSERT INTO `documentstring` VALUES (4,'content for login page','\0',0,'login.login.attempts','You have made 0 login attempts',2),(2,'content for login page','\0',0,'login.password.error','Please enter your password',2);
/*!40000 ALTER TABLE `documentstring` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exportformat`
--

DROP TABLE IF EXISTS `exportformat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exportformat` (
  `exportformat_id` bigint(20) NOT NULL auto_increment,
  `classname` varchar(45) default NULL,
  `title` varchar(100) default NULL,
  PRIMARY KEY  (`exportformat_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exportformat`
--

LOCK TABLES `exportformat` WRITE;
/*!40000 ALTER TABLE `exportformat` DISABLE KEYS */;
/*!40000 ALTER TABLE `exportformat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locale`
--

DROP TABLE IF EXISTS `locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locale` (
  `locale_id` bigint(20) NOT NULL auto_increment,
  `locale_code` varchar(5) NOT NULL,
  `plural_forms_expression` varchar(200) NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY  (`locale_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale`
--

LOCK TABLES `locale` WRITE;
/*!40000 ALTER TABLE `locale` DISABLE KEYS */;
INSERT INTO `locale` VALUES (1,'en_us','form','United States Of America');
/*!40000 ALTER TABLE `locale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL auto_increment,
  `created_date` datetime NOT NULL,
  `deleted_date` datetime default NULL,
  `slug` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `tenant_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`project_id`),
  KEY `FK_1epwuuudx98nljdc5xygw37mj` (`tenant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'2015-04-30 21:00:33','2015-05-02 02:23:23','project1','My First Project',4),(2,'2015-04-30 21:04:48',NULL,'project2','My Second Project',4),(3,'2015-05-02 02:54:51',NULL,'project3','My 3rd project',4);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant` (
  `tenant_id` bigint(20) NOT NULL auto_increment,
  `created_date` datetime NOT NULL,
  `deleted_date` datetime default NULL,
  `api_key` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY  (`tenant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (4,'2015-04-30 21:00:33',NULL,'aaa','Flip Mind','password','admin');
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translatedstring`
--

DROP TABLE IF EXISTS `translatedstring`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `translatedstring` (
  `translatedstring_id` bigint(20) NOT NULL auto_increment,
  `string_key` varchar(100) NOT NULL,
  `plurals` int(11) NOT NULL,
  `string_value` varchar(500) NOT NULL,
  `translation_id` bigint(20) default NULL,
  PRIMARY KEY  (`translatedstring_id`),
  KEY `FK_opsbs7ifstu0uwlevxdl8swva` (`translation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translatedstring`
--

LOCK TABLES `translatedstring` WRITE;
/*!40000 ALTER TABLE `translatedstring` DISABLE KEYS */;
INSERT INTO `translatedstring` VALUES (3,'login.username',0,'Please enter your username',1),(2,'login.password.error',0,'Please enter your password',2),(5,'login.username',0,'Please enter your username',23);
/*!40000 ALTER TABLE `translatedstring` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translation`
--

DROP TABLE IF EXISTS `translation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `translation` (
  `translation_id` bigint(20) NOT NULL auto_increment,
  `created_date` datetime NOT NULL,
  `deleted_date` datetime default NULL,
  `active_from_date` datetime default NULL,
  `published_date` datetime default NULL,
  `status` int(11) NOT NULL,
  `document_id` bigint(20) NOT NULL,
  `locale_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`translation_id`),
  KEY `FK_4pu166upvn855j7n0n88tf6ie` (`document_id`),
  KEY `FK_abld8kpx17c6ifqdhl0a3pp83` (`locale_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translation`
--

LOCK TABLES `translation` WRITE;
/*!40000 ALTER TABLE `translation` DISABLE KEYS */;
INSERT INTO `translation` VALUES (1,'2015-05-02 04:27:02',NULL,NULL,NULL,1,1,1),(2,'2015-05-02 04:30:14',NULL,NULL,NULL,0,2,1),(3,'2015-05-02 04:35:34',NULL,NULL,NULL,0,2,1),(24,'2015-05-02 11:38:13',NULL,NULL,NULL,0,1,1),(23,'2015-05-02 11:38:13',NULL,NULL,NULL,0,1,1),(13,'2015-05-02 09:23:39',NULL,'2015-01-03 12:12:00',NULL,1,1,1);
/*!40000 ALTER TABLE `translation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-02 11:53:28
