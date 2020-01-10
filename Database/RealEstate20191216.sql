-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: realestate
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `detail` varchar(255) NOT NULL,
  `district` bigint(20) NOT NULL,
  `province` bigint(20) NOT NULL,
  `ward` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc62cqat2nk6t5uclbx98shdai` (`ward`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Số nhà 53',760,79,26749),(2,'Số nhà 201',760,79,26746),(3,'Số nhà 94',762,79,26827),(4,'Số nhà 94',762,79,27301),(5,'Số nhà 94',774,79,27313),(6,'Số nhà 201',774,79,27328),(28,'34 Tân Hưng',774,79,27313);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blueprint`
--

DROP TABLE IF EXISTS `blueprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blueprint` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageLink` varchar(255) DEFAULT NULL,
  `tradeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt5mo4achhvj4teh2mpqktwkqh` (`tradeId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blueprint`
--

LOCK TABLES `blueprint` WRITE;
/*!40000 ALTER TABLE `blueprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `blueprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `timeEnd` datetime DEFAULT NULL,
  `timeStart` datetime DEFAULT NULL,
  `tradeId` bigint(20) DEFAULT NULL,
  `bookingId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdsmxp5msd4emhv6567419wlee` (`tradeId`),
  KEY `FKqdqpgy0273mgf1g0wqse4ekw7` (`bookingId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confirmationtoken`
--

DROP TABLE IF EXISTS `confirmationtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `confirmationtoken` (
  `tokenId` bigint(20) NOT NULL,
  `confirmationToken` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`tokenId`),
  KEY `FKsjwvpslamugnwqhxsp09noep2` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmationtoken`
--

LOCK TABLES `confirmationtoken` WRITE;
/*!40000 ALTER TABLE `confirmationtoken` DISABLE KEYS */;
INSERT INTO `confirmationtoken` VALUES (10,'81ffaceb-cfaf-4f9d-9fd2-012f619210e6','2019-10-04 08:21:13',9),(11,'dd861e6a-2d88-49ea-9b68-3ff3ac4ba9a6','2019-10-04 08:38:17',9),(13,'57fc2fbb-991f-49fa-9c62-bec02df2c19a','2019-10-04 17:06:23',12),(36,'217cbae0-3fe6-465c-afe5-2b1419912e21','2019-12-03 10:51:21',34),(39,'b0c50660-3b34-466d-9fb9-0b8684799180','2019-12-03 15:34:48',37),(42,'4223205b-5b95-4ad0-9bec-86775f75983b','2019-12-03 16:11:45',40),(45,'48cc8e51-89f4-41a6-9c17-ec9fee750c2a','2019-12-03 16:57:26',43),(46,'d13f927d-1c26-48f1-8980-ee1ea0e82179','2019-12-03 17:10:42',43),(49,'4374966e-f0ed-44fc-9cac-fb2e995fb8b4','2019-12-04 12:07:55',47),(87,'976b9a90-669f-4d4e-ba22-e30bbe095561','2019-12-07 16:33:58',86),(93,'02afdd0c-9ccf-4c66-8fc3-94a637239b8a','2019-12-07 16:55:27',91),(96,'6ec410a6-e032-424a-bc37-418fdf235a38','2019-12-07 16:56:08',94),(103,'d0933c4d-ae2f-4989-9555-612f79b24091','2019-12-07 23:14:11',102),(105,'d5b8e678-81b7-4a4a-9c9c-c76f37233ccb','2019-12-07 23:14:58',104),(109,'59396bd1-b704-4c69-888c-70c3a52a99cc','2019-12-07 23:16:39',107),(118,'fae25df2-671f-4cc2-8947-40338b2654ed','2019-12-08 11:55:58',117),(121,'56f6c28d-1c13-425f-b8cd-f4cdb70f610c','2019-12-08 11:56:44',120),(131,'8980d850-c75d-4534-9e53-ccb76bd58d71','2019-12-16 14:43:57',130);
/*!40000 ALTER TABLE `confirmationtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinate`
--

DROP TABLE IF EXISTS `coordinate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coordinate` (
  `id` bigint(20) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinate`
--

LOCK TABLES `coordinate` WRITE;
/*!40000 ALTER TABLE `coordinate` DISABLE KEYS */;
INSERT INTO `coordinate` VALUES (1,10.7683,106.687),(2,10.7684,106.699),(3,10.8403,106.757),(4,10.8577,106.734),(5,10.7614,106.681),(6,10.7538,106.662);
/*!40000 ALTER TABLE `coordinate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `details` (
  `id` bigint(20) NOT NULL,
  `bathrooms` int(11) NOT NULL,
  `bedrooms` int(11) NOT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `floors` varchar(255) DEFAULT NULL,
  `legalDocuments` varchar(255) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  `square` bigint(20) DEFAULT NULL,
  `utilities` varchar(255) DEFAULT NULL,
  `width` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details` DISABLE KEYS */;
INSERT INTO `details` VALUES (1,2,2,'Đông Bắc','1 trệt + 1 lầu','Sổ hồng riêng',20,'Nhà mặt tiền Quận 1, nhà đang cho thuê, sổ hồng chính chủ',665,'Gần chợ, siêu thị',4),(2,2,2,'Đông Bắc','1 trệt + 1 lầu','Sổ hồng riêng',20,'Nhà mặt tiền Quận 1, nhà đang cho thuê, sổ hồng chính chủ',665,'Gần chợ, siêu thị',50),(3,3,3,'Tây','1 trệt + 1 lầu','Sổ hồng riêng',17,'Bán nhà Quận Thủ Đức. Khu dân cư an ninh, yên tĩnh, không ngập nước, gần trường THPT Bách Việt',456,'Gần chợ, siêu thị, gần trường học, gần công viên, trung tâm',50),(4,2,1,'Tây','1 trệt + 1 lầu','Sổ hồng riêng',17,'Bán nhà Quận Thủ Đức. Khu dân cư an ninh, yên tĩnh, không ngập nước, gần trường THPT Bách Việt',434,'Gần chợ, siêu thị, gần trường học, gần công viên, trung tâm',50),(5,1,2,'Tây','1 trệt + 1 lầu','Sổ hồng riêng',9,'Bán nhà riêng quận 5, đang cho thuê 6tr/tháng sổ hồng chính chủ',37,'Gần chợ, siêu thị, gần trường học',4),(6,0,0,'Đông','1 trệt + 1 lầu','Sổ hồng riêng',7,'Bán nhà riêng quận 5, Sổ hồng chính chủ - Pháp lý minh bạch, rõ ràng',24,'Gần chợ, siêu thị, gần trường học',4),(29,2,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `district` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameWithType` varchar(255) DEFAULT NULL,
  `pathWithType` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `provinceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsljjgpiyi1a6uwjnlhauvc19p` (`provinceId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (770,'3','Quận 3','Quận 3, Thành phố Hồ Chí Minh','3',79),(760,'1','Quận 1','Quận 1, Thành phố Hồ Chí Minh','1',79),(771,'10','Quận 10','Quận 10, Thành phố Hồ Chí Minh','10',79),(761,'12','Quận 12','Quận 12, Thành phố Hồ Chí Minh','12',79),(772,'11','Quận 11','Quận 11, Thành phố Hồ Chí Minh','11',79),(783,'Củ Chi','Huyện Củ Chi','Huyện Củ Chi, Thành phố Hồ Chí Minh','cu-chi',79),(762,'Thủ Đức','Quận Thủ Đức','Quận Thủ Đức, Thành phố Hồ Chí Minh','thu-duc',79),(773,'4','Quận 4','Quận 4, Thành phố Hồ Chí Minh','4',79),(784,'Hóc Môn','Huyện Hóc Môn','Huyện Hóc Môn, Thành phố Hồ Chí Minh','hoc-mon',79),(763,'9','Quận 9','Quận 9, Thành phố Hồ Chí Minh','9',79),(774,'5','Quận 5','Quận 5, Thành phố Hồ Chí Minh','5',79),(785,'Bình Chánh','Huyện Bình Chánh','Huyện Bình Chánh, Thành phố Hồ Chí Minh','binh-chanh',79),(764,'Gò Vấp','Quận Gò Vấp','Quận Gò Vấp, Thành phố Hồ Chí Minh','go-vap',79),(775,'6','Quận 6','Quận 6, Thành phố Hồ Chí Minh','6',79),(786,'Nhà Bè','Huyện Nhà Bè','Huyện Nhà Bè, Thành phố Hồ Chí Minh','nha-be',79),(765,'Bình Thạnh','Quận Bình Thạnh','Quận Bình Thạnh, Thành phố Hồ Chí Minh','binh-thanh',79),(776,'8','Quận 8','Quận 8, Thành phố Hồ Chí Minh','8',79),(787,'Cần Giờ','Huyện Cần Giờ','Huyện Cần Giờ, Thành phố Hồ Chí Minh','can-gio',79),(766,'Tân Bình','Quận Tân Bình','Quận Tân Bình, Thành phố Hồ Chí Minh','tan-binh',79),(777,'Bình Tân','Quận Bình Tân','Quận Bình Tân, Thành phố Hồ Chí Minh','binh-tan',79),(767,'Tân Phú','Quận Tân Phú','Quận Tân Phú, Thành phố Hồ Chí Minh','tan-phu',79),(778,'7','Quận 7','Quận 7, Thành phố Hồ Chí Minh','7',79),(768,'Phú Nhuận','Quận Phú Nhuận','Quận Phú Nhuận, Thành phố Hồ Chí Minh','phu-nhuan',79),(769,'2','Quận 2','Quận 2, Thành phố Hồ Chí Minh','2',79);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritetrade`
--

DROP TABLE IF EXISTS `favoritetrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `favoritetrade` (
  `tradeId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`tradeId`,`userId`),
  KEY `FKqaqy8luhmipcwr3bwqha3arhm` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritetrade`
--

LOCK TABLES `favoritetrade` WRITE;
/*!40000 ALTER TABLE `favoritetrade` DISABLE KEYS */;
/*!40000 ALTER TABLE `favoritetrade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (132),(132),(132),(132),(132),(132),(132),(132),(132),(132),(132),(132),(132),(132);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageLink` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'Mặt trước','http://res.cloudinary.com/fijetso3671/image/upload/v1559860911/RealEstate/kql6ziprexdjjvcqmn1m.webp');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `information` (
  `id` int(11) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (31,'Giáo viên'),(119,'Kinh doanh');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `composeDate` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKply73hqjhpqghm97irph80ddc` (`categoryId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `province` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameWithType` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (79,'Hồ Chí Minh','Thành phố Hồ Chí Minh','ho-chi-minh');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realestatekind`
--

DROP TABLE IF EXISTS `realestatekind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `realestatekind` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realestatekind`
--

LOCK TABLES `realestatekind` WRITE;
/*!40000 ALTER TABLE `realestatekind` DISABLE KEYS */;
INSERT INTO `realestatekind` VALUES (1,'Chung cư'),(2,'Đất nền'),(3,'Nhà riêng');
/*!40000 ALTER TABLE `realestatekind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realimage`
--

DROP TABLE IF EXISTS `realimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `realimage` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageLink` varchar(255) DEFAULT NULL,
  `tradeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqlb2m6ek2r0w8yj1nlfv69mfu` (`tradeId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realimage`
--

LOCK TABLES `realimage` WRITE;
/*!40000 ALTER TABLE `realimage` DISABLE KEYS */;
INSERT INTO `realimage` VALUES (1,'Real Estate','https://res.cloudinary.com/fijetso3671/image/upload/v1559034773/RealEstate/maxresdefault_ropnun.jpg',1),(2,'Real Estate','https://res.cloudinary.com/fijetso3671/image/upload/v1559034774/RealEstate/uploads_2Fcard_2Fimage_2F829044_2Ff1a11a98-59ed-46a9-a2df-bf2a6997ee31.jpg_2F950x534__filters_3Aquality_2890_29_eiw5xu.jpg',1),(19,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1559860911/RealEstate/kql6ziprexdjjvcqmn1m.webp',2),(20,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559860911/RealEstate/kql6ziprexdjjvcqmn1m.webp',2),(3,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559872643/RealEstate/201810225152_20181025_115002_diaias.jpg',2),(4,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1559872755/RealEstate/20160420062223-31ae_okbvke.jpg',3),(5,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559872755/RealEstate/20160803161350-6c53_pjgujr.jpg',3),(6,'Nhà bếp','https://lacan.vn/wp-content/uploads/2016/06/10-mau-tu-bep-chu-l-dep-nhat-hien-nay-m5.jpg',3),(7,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1559873092/RealEstate/nha-rieng-pho-ban-to-chuc-quan-uy-cau-giay-dong-cua-im-im_ohou2l.jpg',4),(8,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559872756/RealEstate/20180620142809-644e_c4zz8e.jpg',4),(9,'Nhà bếp','https://gotrangtri.vn/wp-content/uploads/2018/05/thi%E1%BA%BFt-k%E1%BA%BF-n%E1%BB%99i-th%E1%BA%A5t-nh%C3%A0-b%E1%BA%BFp-%C4%91%E1%BA%B9p-c%E1%BA%A7n-c%C3%B3-g%C3%AC.jpg',4),(10,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1575531702/RealEstate/hinh-1-1544088631382690327544-crop-1544088723594514569302_j7lkio.jpg',5),(11,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559873204/RealEstate/cho-thu%C3%AA-nh%C3%A0-ri%C3%AAng-4-ph%C3%B2ng-ng%E1%BB%A7-t%E1%BA%A1i-%C4%91%E1%BA%B1ng-giang-qu%E1%BA%ADn-ng%C3%B4-quy%E1%BB%81n-h%E1%BA%A3i-ph%C3%B2ng_doolm2.jpg',5),(12,'Nhà bếp','https://nhadepsang.com.vn/images/2018/01/trang-tri-nha-bep-3.png',5),(13,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1575536762/RealEstate/20181001074725-ee3b_o6wkuc.jpg',6),(14,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559873204/RealEstate/uu-diem-cua-can-ho-chung-cu_taiqsx.jpg',6),(15,'Nhà bếp','https://www.giadinhnestle.com.vn/sites/default/files/articles/sliders/cach-trang-tri-nha-bep-theo-phong-thuy-de-dem-lai-may-man-630x350.jpg',6),(16,'Mặt trước','https://nld.mediacdn.vn/thumb_w/540/2018/4/20/123-15241955574791633741019.jpg',1),(17,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559873204/RealEstate/dsc7489_bnlp_kkk8za.jpg',1),(18,'Nhà bếp','https://res.cloudinary.com/fijetso3671/image/upload/v1575531641/RealEstate/noi-that-bep-dep-cho-nha-nho-1_iq3bh9.jpg',1);
/*!40000 ALTER TABLE `realimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `highestPrice` bigint(20) DEFAULT NULL,
  `lowestPrice` bigint(20) DEFAULT NULL,
  `districtId` bigint(20) DEFAULT NULL,
  `realEstateKindId` bigint(20) DEFAULT NULL,
  `tradeKindId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKonitw09sch7tesin4g47x7hlu` (`districtId`),
  KEY `FKe3akvr9ebtw8omgvoqgjyktnh` (`realEstateKindId`),
  KEY `FKo1il5as3qsqk0394q98k5xyet` (`tradeKindId`),
  KEY `FK8vyqdqj0l3t1y1ljxse0kb28s` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER'),(4,'MANAGER'),(8,'HR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trade` (
  `id` bigint(20) NOT NULL,
  `cost` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `viewCount` bigint(20) DEFAULT NULL,
  `addressId` bigint(20) DEFAULT NULL,
  `detailsId` bigint(20) DEFAULT NULL,
  `realEstateKindId` bigint(20) DEFAULT NULL,
  `tradeKindId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `tradeStatus` smallint(6) DEFAULT NULL,
  `coordinateId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq3anaqws3f17pky65jkx1il75` (`addressId`),
  KEY `FKlpws3d4em5htg1c42jejl2ium` (`detailsId`),
  KEY `FK2jy9tpeixc0vrvv7y68ynyuyp` (`realEstateKindId`),
  KEY `FKfbw184hpw3cco4kcqi5bmdfgo` (`tradeKindId`),
  KEY `FKd986y1i64s1k88weqamt2p6t5` (`userId`),
  KEY `FKpepp1nyu7djyad4ncuih8nep` (`coordinateId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (1,40000000000,'Nhà mặt tiền quận 1 thuận tiện kinh doanh',2,1,1,3,1,1,NULL,1),(2,53000000000,'Nhà quận 1 rộng rãi thoáng mát',0,2,2,3,1,2,NULL,2),(3,3800000000,'Bán nhà hẻm xe hơi phường Hiệp Bình Phước, Quận Thủ Đức, hướng Tây',0,3,3,1,2,2,NULL,3),(4,5100000000,'Bán nhà hẻm xe hơi phường Hiệp Bình Phước, Quận Thủ Đức, hướng Tây',0,4,4,2,2,1,NULL,4),(5,3300000000,'Bán nhà riêng quận 5 đang cho thuê 6tr/tháng',0,5,5,3,2,2,NULL,5),(6,3700000000,'Bán nhà riêng quận 5',0,6,6,3,2,1,3,6);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tradekind`
--

DROP TABLE IF EXISTS `tradekind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tradekind` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tradekind`
--

LOCK TABLES `tradekind` WRITE;
/*!40000 ALTER TABLE `tradekind` DISABLE KEYS */;
INSERT INTO `tradekind` VALUES (1,'Bán'),(2,'Thuê');
/*!40000 ALTER TABLE `tradekind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userKindId` bigint(20) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `jobId` bigint(20) DEFAULT NULL,
  `dateFormat` tinyblob,
  `imageUrl` varchar(255) DEFAULT NULL,
  `provider` varchar(255) NOT NULL,
  `providerId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr3ev5cp30260s7n0eni85a8q3` (`userKindId`),
  KEY `FK323s8f8mb3rgqx6p7tdbm1r0j` (`jobId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'1997-06-05 00:00:00','danhthanh0605@gmail.com',_binary '\0','Danh Thanh','','0123456789',2,_binary '\0',31,NULL,NULL,'facebook','1'),(3,'1991-11-30 11:57:24','152032545@gmail.com',_binary '\0','Lê Thị Thảo Trâm','dfgsfgsdfg','0123456789',NULL,_binary '\0',NULL,NULL,NULL,'local','0'),(1,NULL,'henrynguyen02081997@gmail.com',_binary '\0','Nguyễn Thị Ngọc Huyền',NULL,NULL,NULL,_binary '',NULL,NULL,NULL,'facebook','1'),(7,NULL,'15520325@gm.uit.edu.vn',_binary '\0','HUYỀN NGUYỄN THỊ NGỌC',NULL,NULL,NULL,_binary '',NULL,NULL,NULL,'google','2'),(12,NULL,'jessicakelny02081997@gmail.com',_binary '\0','Jessica','$2a$10$6JSBBfIfsZkf6V5ETitYROnvQz.Uz7z7YscjCB8IBnmqBWrc6XpUa','0123456789',NULL,_binary '',NULL,NULL,NULL,'local','0'),(130,NULL,'realestate.uit.edu@gmail.com',_binary '\0','nana','$2a$10$y.Gs7DYhunmVJXeSaX8w0uBnn1yv1Wocp7ulrGbNFqlnJOmFDKpci',NULL,NULL,_binary '\0',119,NULL,NULL,'local','0'),(128,NULL,'huyenntn02081997@gmail.com',_binary '\0','Huyen Nguyen',NULL,NULL,NULL,_binary '',NULL,NULL,'https://lh3.googleusercontent.com/a-/AAuE7mBBodqMK6inhYMbEAIN8NhxS2GAF_nwR_IFT4fz','google','116412851863114130546');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FKj1n131kbq3wgkdtpexsf3g00g` (`roleId`),
  KEY `FK2ysxi63jskrjbb8tclaetx2vu` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(7,1),(8,2),(9,2),(12,2),(34,2),(37,2),(40,2),(43,2),(47,2),(50,2),(86,2),(91,2),(94,2),(99,2),(102,2),(104,2),(107,2),(115,2),(116,2),(117,2),(120,2),(127,2),(128,2),(129,2),(130,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userkind`
--

DROP TABLE IF EXISTS `userkind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `userkind` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userkind`
--

LOCK TABLES `userkind` WRITE;
/*!40000 ALTER TABLE `userkind` DISABLE KEYS */;
INSERT INTO `userkind` VALUES (1,'Chủ nhà'),(2,'Môi giới');
/*!40000 ALTER TABLE `userkind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ward` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameWithType` varchar(255) DEFAULT NULL,
  `pathWithType` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `districtId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrp50c8pex0c5pk6f4lskt1n6` (`districtId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES (27139,'06','Phường 06','Phường 06, Quận 3, Thành phố Hồ Chí Minh','06',770),(27148,'04','Phường 04','Phường 04, Quận 3, Thành phố Hồ Chí Minh','04',770),(27127,'14','Phường 14','Phường 14, Quận 3, Thành phố Hồ Chí Minh','14',770),(27142,'09','Phường 09','Phường 09, Quận 3, Thành phố Hồ Chí Minh','09',770),(27121,'08','Phường 08','Phường 08, Quận 3, Thành phố Hồ Chí Minh','08',770),(27154,'03','Phường 03','Phường 03, Quận 3, Thành phố Hồ Chí Minh','03',770),(27151,'05','Phường 05','Phường 05, Quận 3, Thành phố Hồ Chí Minh','05',770),(27130,'12','Phường 12','Phường 12, Quận 3, Thành phố Hồ Chí Minh','12',770),(27124,'07','Phường 07','Phường 07, Quận 3, Thành phố Hồ Chí Minh','07',770),(27157,'02','Phường 02','Phường 02, Quận 3, Thành phố Hồ Chí Minh','02',770),(27136,'13','Phường 13','Phường 13, Quận 3, Thành phố Hồ Chí Minh','13',770),(27133,'11','Phường 11','Phường 11, Quận 3, Thành phố Hồ Chí Minh','11',770),(27145,'10','Phường 10','Phường 10, Quận 3, Thành phố Hồ Chí Minh','10',770),(27160,'01','Phường 01','Phường 01, Quận 3, Thành phố Hồ Chí Minh','01',770),(26749,'Phạm Ngũ Lão','Phường Phạm Ngũ Lão','Phường Phạm Ngũ Lão, Quận 1, Thành phố Hồ Chí Minh','pham-ngu-lao',760),(26740,'Bến Nghé','Phường Bến Nghé','Phường Bến Nghé, Quận 1, Thành phố Hồ Chí Minh','ben-nghe',760),(26761,'Cầu Kho','Phường Cầu Kho','Phường Cầu Kho, Quận 1, Thành phố Hồ Chí Minh','cau-kho',760),(26752,'Cầu Ông Lãnh','Phường Cầu Ông Lãnh','Phường Cầu Ông Lãnh, Quận 1, Thành phố Hồ Chí Minh','cau-ong-lanh',760),(26755,'Cô Giang','Phường Cô Giang','Phường Cô Giang, Quận 1, Thành phố Hồ Chí Minh','co-giang',760),(26743,'Bến Thành','Phường Bến Thành','Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh','ben-thanh',760),(26746,'Nguyễn Thái Bình','Phường Nguyễn Thái Bình','Phường Nguyễn Thái Bình, Quận 1, Thành phố Hồ Chí Minh','nguyen-thai-binh',760),(26734,'Tân Định','Phường Tân Định','Phường Tân Định, Quận 1, Thành phố Hồ Chí Minh','tan-dinh',760),(26737,'Đa Kao','Phường Đa Kao','Phường Đa Kao, Quận 1, Thành phố Hồ Chí Minh','da-kao',760),(26758,'Nguyễn Cư Trinh','Phường Nguyễn Cư Trinh','Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh','nguyen-cu-trinh',760),(27205,'03','Phường 03','Phường 03, Quận 10, Thành phố Hồ Chí Minh','03',771),(27175,'11','Phường 11','Phường 11, Quận 10, Thành phố Hồ Chí Minh','11',771),(27187,'08','Phường 08','Phường 08, Quận 10, Thành phố Hồ Chí Minh','08',771),(27184,'01','Phường 01','Phường 01, Quận 10, Thành phố Hồ Chí Minh','01',771),(27163,'15','Phường 15','Phường 15, Quận 10, Thành phố Hồ Chí Minh','15',771),(27196,'07','Phường 07','Phường 07, Quận 10, Thành phố Hồ Chí Minh','07',771),(27169,'14','Phường 14','Phường 14, Quận 10, Thành phố Hồ Chí Minh','14',771),(27202,'06','Phường 06','Phường 06, Quận 10, Thành phố Hồ Chí Minh','06',771),(27166,'13','Phường 13','Phường 13, Quận 10, Thành phố Hồ Chí Minh','13',771),(27199,'05','Phường 05','Phường 05, Quận 10, Thành phố Hồ Chí Minh','05',771),(27178,'10','Phường 10','Phường 10, Quận 10, Thành phố Hồ Chí Minh','10',771),(27190,'02','Phường 02','Phường 02, Quận 10, Thành phố Hồ Chí Minh','02',771),(27193,'04','Phường 04','Phường 04, Quận 10, Thành phố Hồ Chí Minh','04',771),(27172,'12','Phường 12','Phường 12, Quận 10, Thành phố Hồ Chí Minh','12',771),(27181,'09','Phường 09','Phường 09, Quận 10, Thành phố Hồ Chí Minh','09',771),(26791,'Tân Thới Nhất','Phường Tân Thới Nhất','Phường Tân Thới Nhất, Quận 12, Thành phố Hồ Chí Minh','tan-thoi-nhat',761),(26782,'Tân Thới Hiệp','Phường Tân Thới Hiệp','Phường Tân Thới Hiệp, Quận 12, Thành phố Hồ Chí Minh','tan-thoi-hiep',761),(26770,'Hiệp Thành','Phường Hiệp Thành','Phường Hiệp Thành, Quận 12, Thành phố Hồ Chí Minh','hiep-thanh',761),(26773,'Thới An','Phường Thới An','Phường Thới An, Quận 12, Thành phố Hồ Chí Minh','thoi-an',761),(26764,'Thạnh Xuân','Phường Thạnh Xuân','Phường Thạnh Xuân, Quận 12, Thành phố Hồ Chí Minh','thanh-xuan',761),(26785,'Trung Mỹ Tây','Phường Trung Mỹ Tây','Phường Trung Mỹ Tây, Quận 12, Thành phố Hồ Chí Minh','trung-my-tay',761),(26788,'Đông Hưng Thuận','Phường Đông Hưng Thuận','Phường Đông Hưng Thuận, Quận 12, Thành phố Hồ Chí Minh','dong-hung-thuan',761),(26776,'Tân Chánh Hiệp','Phường Tân Chánh Hiệp','Phường Tân Chánh Hiệp, Quận 12, Thành phố Hồ Chí Minh','tan-chanh-hiep',761),(26787,'Tân Hưng Thuận','Phường Tân Hưng Thuận','Phường Tân Hưng Thuận, Quận 12, Thành phố Hồ Chí Minh','tan-hung-thuan',761),(26779,'An Phú Đông','Phường An Phú Đông','Phường An Phú Đông, Quận 12, Thành phố Hồ Chí Minh','an-phu-dong',761),(26767,'Thạnh Lộc','Phường Thạnh Lộc','Phường Thạnh Lộc, Quận 12, Thành phố Hồ Chí Minh','thanh-loc',761),(27238,'07','Phường 07','Phường 07, Quận 11, Thành phố Hồ Chí Minh','07',772),(27217,'11','Phường 11','Phường 11, Quận 11, Thành phố Hồ Chí Minh','11',772),(27214,'14','Phường 14','Phường 14, Quận 11, Thành phố Hồ Chí Minh','14',772),(27247,'01','Phường 01','Phường 01, Quận 11, Thành phố Hồ Chí Minh','01',772),(27226,'13','Phường 13','Phường 13, Quận 11, Thành phố Hồ Chí Minh','13',772),(27229,'08','Phường 08','Phường 08, Quận 11, Thành phố Hồ Chí Minh','08',772),(27208,'15','Phường 15','Phường 15, Quận 11, Thành phố Hồ Chí Minh','15',772),(27241,'06','Phường 06','Phường 06, Quận 11, Thành phố Hồ Chí Minh','06',772),(27220,'03','Phường 03','Phường 03, Quận 11, Thành phố Hồ Chí Minh','03',772),(27253,'16','Phường 16','Phường 16, Quận 11, Thành phố Hồ Chí Minh','16',772),(27250,'02','Phường 02','Phường 02, Quận 11, Thành phố Hồ Chí Minh','02',772),(27223,'10','Phường 10','Phường 10, Quận 11, Thành phố Hồ Chí Minh','10',772),(27235,'12','Phường 12','Phường 12, Quận 11, Thành phố Hồ Chí Minh','12',772),(27232,'09','Phường 09','Phường 09, Quận 11, Thành phố Hồ Chí Minh','09',772),(27211,'05','Phường 05','Phường 05, Quận 11, Thành phố Hồ Chí Minh','05',772),(27244,'04','Phường 04','Phường 04, Quận 11, Thành phố Hồ Chí Minh','04',772),(27508,'An Nhơn Tây','Xã An Nhơn Tây','Xã An Nhơn Tây, Huyện Củ Chi, Thành phố Hồ Chí Minh','an-nhon-tay',783),(27502,'An Phú','Xã An Phú','Xã An Phú, Huyện Củ Chi, Thành phố Hồ Chí Minh','an-phu',783),(27535,'Phước Vĩnh An','Xã Phước Vĩnh An','Xã Phước Vĩnh An, Huyện Củ Chi, Thành phố Hồ Chí Minh','phuoc-vinh-an',783),(27514,'Phạm Văn Cội','Xã Phạm Văn Cội','Xã Phạm Văn Cội, Huyện Củ Chi, Thành phố Hồ Chí Minh','pham-van-coi',783),(27547,'Tân Thạnh Đông','Xã Tân Thạnh Đông','Xã Tân Thạnh Đông, Huyện Củ Chi, Thành phố Hồ Chí Minh','tan-thanh-dong',783),(27511,'Nhuận Đức','Xã Nhuận Đức','Xã Nhuận Đức, Huyện Củ Chi, Thành phố Hồ Chí Minh','nhuan-duc',783),(27544,'Hòa Phú','Xã Hòa Phú','Xã Hòa Phú, Huyện Củ Chi, Thành phố Hồ Chí Minh','hoa-phu',783),(27523,'Trung An','Xã Trung An','Xã Trung An, Huyện Củ Chi, Thành phố Hồ Chí Minh','trung-an',783),(27556,'Tân Thông Hội','Xã Tân Thông Hội','Xã Tân Thông Hội, Huyện Củ Chi, Thành phố Hồ Chí Minh','tan-thong-hoi',783),(27517,'Phú Hòa Đông','Xã Phú Hòa Đông','Xã Phú Hòa Đông, Huyện Củ Chi, Thành phố Hồ Chí Minh','phu-hoa-dong',783),(27529,'Phước Hiệp','Xã Phước Hiệp','Xã Phước Hiệp, Huyện Củ Chi, Thành phố Hồ Chí Minh','phuoc-hiep',783),(27526,'Phước Thạnh','Xã Phước Thạnh','Xã Phước Thạnh, Huyện Củ Chi, Thành phố Hồ Chí Minh','phuoc-thanh',783),(27505,'Trung Lập Thượng','Xã Trung Lập Thượng','Xã Trung Lập Thượng, Huyện Củ Chi, Thành phố Hồ Chí Minh','trung-lap-thuong',783),(27538,'Thái Mỹ','Xã Thái Mỹ','Xã Thái Mỹ, Huyện Củ Chi, Thành phố Hồ Chí Minh','thai-my',783),(27550,'Bình Mỹ','Xã Bình Mỹ','Xã Bình Mỹ, Huyện Củ Chi, Thành phố Hồ Chí Minh','binh-my',783),(27520,'Trung Lập Hạ','Xã Trung Lập Hạ','Xã Trung Lập Hạ, Huyện Củ Chi, Thành phố Hồ Chí Minh','trung-lap-ha',783),(27553,'Tân Phú Trung','Xã Tân Phú Trung','Xã Tân Phú Trung, Huyện Củ Chi, Thành phố Hồ Chí Minh','tan-phu-trung',783),(27499,'Phú Mỹ Hưng','Xã Phú Mỹ Hưng','Xã Phú Mỹ Hưng, Huyện Củ Chi, Thành phố Hồ Chí Minh','phu-my-hung',783),(27532,'Tân An Hội','Xã Tân An Hội','Xã Tân An Hội, Huyện Củ Chi, Thành phố Hồ Chí Minh','tan-an-hoi',783),(27496,'Củ Chi','Thị trấn Củ Chi','Thị trấn Củ Chi, Huyện Củ Chi, Thành phố Hồ Chí Minh','cu-chi',783),(27541,'Tân Thạnh Tây','Xã Tân Thạnh Tây','Xã Tân Thạnh Tây, Huyện Củ Chi, Thành phố Hồ Chí Minh','tan-thanh-tay',783),(26827,'Trường Thọ','Phường Trường Thọ','Phường Trường Thọ, Quận Thủ Đức, Thành phố Hồ Chí Minh','truong-tho',762),(26815,'Linh Chiểu','Phường Linh Chiểu','Phường Linh Chiểu, Quận Thủ Đức, Thành phố Hồ Chí Minh','linh-chieu',762),(26818,'Linh Tây','Phường Linh Tây','Phường Linh Tây, Quận Thủ Đức, Thành phố Hồ Chí Minh','linh-tay',762),(26806,'Tam Phú','Phường Tam Phú','Phường Tam Phú, Quận Thủ Đức, Thành phố Hồ Chí Minh','tam-phu',762),(26809,'Hiệp Bình Phước','Phường Hiệp Bình Phước','Phường Hiệp Bình Phước, Quận Thủ Đức, Thành phố Hồ Chí Minh','hiep-binh-phuoc',762),(26794,'Linh Xuân','Phường Linh Xuân','Phường Linh Xuân, Quận Thủ Đức, Thành phố Hồ Chí Minh','linh-xuan',762),(26797,'Bình Chiểu','Phường Bình Chiểu','Phường Bình Chiểu, Quận Thủ Đức, Thành phố Hồ Chí Minh','binh-chieu',762),(26821,'Linh Đông','Phường Linh Đông','Phường Linh Đông, Quận Thủ Đức, Thành phố Hồ Chí Minh','linh-dong',762),(26812,'Hiệp Bình Chánh','Phường Hiệp Bình Chánh','Phường Hiệp Bình Chánh, Quận Thủ Đức, Thành phố Hồ Chí Minh','hiep-binh-chanh',762),(26800,'Linh Trung','Phường Linh Trung','Phường Linh Trung, Quận Thủ Đức, Thành phố Hồ Chí Minh','linh-trung',762),(26803,'Tam Bình','Phường Tam Bình','Phường Tam Bình, Quận Thủ Đức, Thành phố Hồ Chí Minh','tam-binh',762),(26824,'Bình Thọ','Phường Bình Thọ','Phường Bình Thọ, Quận Thủ Đức, Thành phố Hồ Chí Minh','binh-tho',762),(27259,'13','Phường 13','Phường 13, Quận 4, Thành phố Hồ Chí Minh','13',773),(27274,'05','Phường 05','Phường 05, Quận 4, Thành phố Hồ Chí Minh','05',773),(27286,'03','Phường 03','Phường 03, Quận 4, Thành phố Hồ Chí Minh','03',773),(27283,'04','Phường 04','Phường 04, Quận 4, Thành phố Hồ Chí Minh','04',773),(27262,'09','Phường 09','Phường 09, Quận 4, Thành phố Hồ Chí Minh','09',773),(27295,'15','Phường 15','Phường 15, Quận 4, Thành phố Hồ Chí Minh','15',773),(27256,'12','Phường 12','Phường 12, Quận 4, Thành phố Hồ Chí Minh','12',773),(27289,'16','Phường 16','Phường 16, Quận 4, Thành phố Hồ Chí Minh','16',773),(27268,'08','Phường 08','Phường 08, Quận 4, Thành phố Hồ Chí Minh','08',773),(27265,'06','Phường 06','Phường 06, Quận 4, Thành phố Hồ Chí Minh','06',773),(27298,'01','Phường 01','Phường 01, Quận 4, Thành phố Hồ Chí Minh','01',773),(27277,'18','Phường 18','Phường 18, Quận 4, Thành phố Hồ Chí Minh','18',773),(27292,'02','Phường 02','Phường 02, Quận 4, Thành phố Hồ Chí Minh','02',773),(27271,'10','Phường 10','Phường 10, Quận 4, Thành phố Hồ Chí Minh','10',773),(27280,'14','Phường 14','Phường 14, Quận 4, Thành phố Hồ Chí Minh','14',773),(27571,'Tân Thới Nhì','Xã Tân Thới Nhì','Xã Tân Thới Nhì, Huyện Hóc Môn, Thành phố Hồ Chí Minh','tan-thoi-nhi',784),(27583,'Xuân Thới Đông','Xã Xuân Thới Đông','Xã Xuân Thới Đông, Huyện Hóc Môn, Thành phố Hồ Chí Minh','xuan-thoi-dong',784),(27580,'Tân Xuân','Xã Tân Xuân','Xã Tân Xuân, Huyện Hóc Môn, Thành phố Hồ Chí Minh','tan-xuan',784),(27592,'Bà Điểm','Xã Bà Điểm','Xã Bà Điểm, Huyện Hóc Môn, Thành phố Hồ Chí Minh','ba-diem',784),(27586,'Trung Chánh','Xã Trung Chánh','Xã Trung Chánh, Huyện Hóc Môn, Thành phố Hồ Chí Minh','trung-chanh',784),(27565,'Nhị Bình','Xã Nhị Bình','Xã Nhị Bình, Huyện Hóc Môn, Thành phố Hồ Chí Minh','nhi-binh',784),(27562,'Tân Hiệp','Xã Tân Hiệp','Xã Tân Hiệp, Huyện Hóc Môn, Thành phố Hồ Chí Minh','tan-hiep',784),(27574,'Thới Tam Thôn','Xã Thới Tam Thôn','Xã Thới Tam Thôn, Huyện Hóc Môn, Thành phố Hồ Chí Minh','thoi-tam-thon',784),(27568,'Đông Thạnh','Xã Đông Thạnh','Xã Đông Thạnh, Huyện Hóc Môn, Thành phố Hồ Chí Minh','dong-thanh',784),(27577,'Xuân Thới Sơn','Xã Xuân Thới Sơn','Xã Xuân Thới Sơn, Huyện Hóc Môn, Thành phố Hồ Chí Minh','xuan-thoi-son',784),(27589,'Xuân Thới Thượng','Xã Xuân Thới Thượng','Xã Xuân Thới Thượng, Huyện Hóc Môn, Thành phố Hồ Chí Minh','xuan-thoi-thuong',784),(27559,'Hóc Môn','Thị trấn Hóc Môn','Thị trấn Hóc Môn, Huyện Hóc Môn, Thành phố Hồ Chí Minh','hoc-mon',784),(26848,'Phước Long B','Phường Phước Long B','Phường Phước Long B, Quận 9, Thành phố Hồ Chí Minh','phuoc-long-b',763),(26839,'Hiệp Phú','Phường Hiệp Phú','Phường Hiệp Phú, Quận 9, Thành phố Hồ Chí Minh','hiep-phu',763),(26830,'Long Bình','Phường Long Bình','Phường Long Bình, Quận 9, Thành phố Hồ Chí Minh','long-binh',763),(26863,'Phước Bình','Phường Phước Bình','Phường Phước Bình, Quận 9, Thành phố Hồ Chí Minh','phuoc-binh',763),(26851,'Phước Long A','Phường Phước Long A','Phường Phước Long A, Quận 9, Thành phố Hồ Chí Minh','phuoc-long-a',763),(26854,'Trường Thạnh','Phường Trường Thạnh','Phường Trường Thạnh, Quận 9, Thành phố Hồ Chí Minh','truong-thanh',763),(26842,'Tăng Nhơn Phú A','Phường Tăng Nhơn Phú A','Phường Tăng Nhơn Phú A, Quận 9, Thành phố Hồ Chí Minh','tang-nhon-phu-a',763),(26845,'Tăng Nhơn Phú B','Phường Tăng Nhơn Phú B','Phường Tăng Nhơn Phú B, Quận 9, Thành phố Hồ Chí Minh','tang-nhon-phu-b',763),(26833,'Long Thạnh Mỹ','Phường Long Thạnh Mỹ','Phường Long Thạnh Mỹ, Quận 9, Thành phố Hồ Chí Minh','long-thanh-my',763),(26866,'Phú Hữu','Phường Phú Hữu','Phường Phú Hữu, Quận 9, Thành phố Hồ Chí Minh','phu-huu',763),(26836,'Tân Phú','Phường Tân Phú','Phường Tân Phú, Quận 9, Thành phố Hồ Chí Minh','tan-phu',763),(26857,'Long Phước','Phường Long Phước','Phường Long Phước, Quận 9, Thành phố Hồ Chí Minh','long-phuoc',763),(26860,'Long Trường','Phường Long Trường','Phường Long Trường, Quận 9, Thành phố Hồ Chí Minh','long-truong',763),(27304,'09','Phường 09','Phường 09, Quận 5, Thành phố Hồ Chí Minh','09',774),(27337,'06','Phường 06','Phường 06, Quận 5, Thành phố Hồ Chí Minh','06',774),(27316,'08','Phường 08','Phường 08, Quận 5, Thành phố Hồ Chí Minh','08',774),(27313,'02','Phường 02','Phường 02, Quận 5, Thành phố Hồ Chí Minh','02',774),(27325,'01','Phường 01','Phường 01, Quận 5, Thành phố Hồ Chí Minh','01',774),(27319,'15','Phường 15','Phường 15, Quận 5, Thành phố Hồ Chí Minh','15',774),(27328,'11','Phường 11','Phường 11, Quận 5, Thành phố Hồ Chí Minh','11',774),(27307,'03','Phường 03','Phường 03, Quận 5, Thành phố Hồ Chí Minh','03',774),(27340,'10','Phường 10','Phường 10, Quận 5, Thành phố Hồ Chí Minh','10',774),(27322,'07','Phường 07','Phường 07, Quận 5, Thành phố Hồ Chí Minh','07',774),(27301,'04','Phường 04','Phường 04, Quận 5, Thành phố Hồ Chí Minh','04',774),(27334,'05','Phường 05','Phường 05, Quận 5, Thành phố Hồ Chí Minh','05',774),(27331,'14','Phường 14','Phường 14, Quận 5, Thành phố Hồ Chí Minh','14',774),(27310,'12','Phường 12','Phường 12, Quận 5, Thành phố Hồ Chí Minh','12',774),(27343,'13','Phường 13','Phường 13, Quận 5, Thành phố Hồ Chí Minh','13',774),(27607,'Bình Lợi','Xã Bình Lợi','Xã Bình Lợi, Huyện Bình Chánh, Thành phố Hồ Chí Minh','binh-loi',785),(27619,'Bình Hưng','Xã Bình Hưng','Xã Bình Hưng, Huyện Bình Chánh, Thành phố Hồ Chí Minh','binh-hung',785),(27601,'Vĩnh Lộc A','Xã Vĩnh Lộc A','Xã Vĩnh Lộc A, Huyện Bình Chánh, Thành phố Hồ Chí Minh','vinh-loc-a',785),(27634,'Tân Quý Tây','Xã Tân Quý Tây','Xã Tân Quý Tây, Huyện Bình Chánh, Thành phố Hồ Chí Minh','tan-quy-tay',785),(27613,'Tân Nhựt','Xã Tân Nhựt','Xã Tân Nhựt, Huyện Bình Chánh, Thành phố Hồ Chí Minh','tan-nhut',785),(27610,'Lê Minh Xuân','Xã Lê Minh Xuân','Xã Lê Minh Xuân, Huyện Bình Chánh, Thành phố Hồ Chí Minh','le-minh-xuan',785),(27622,'Phong Phú','Xã Phong Phú','Xã Phong Phú, Huyện Bình Chánh, Thành phố Hồ Chí Minh','phong-phu',785),(27616,'Tân Kiên','Xã Tân Kiên','Xã Tân Kiên, Huyện Bình Chánh, Thành phố Hồ Chí Minh','tan-kien',785),(27628,'Hưng Long','Xã Hưng Long','Xã Hưng Long, Huyện Bình Chánh, Thành phố Hồ Chí Minh','hung-long',785),(27625,'An Phú Tây','Xã An Phú Tây','Xã An Phú Tây, Huyện Bình Chánh, Thành phố Hồ Chí Minh','an-phu-tay',785),(27604,'Vĩnh Lộc B','Xã Vĩnh Lộc B','Xã Vĩnh Lộc B, Huyện Bình Chánh, Thành phố Hồ Chí Minh','vinh-loc-b',785),(27637,'Bình Chánh','Xã Bình Chánh','Xã Bình Chánh, Huyện Bình Chánh, Thành phố Hồ Chí Minh','binh-chanh',785),(27598,'Phạm Văn Hai','Xã Phạm Văn Hai','Xã Phạm Văn Hai, Huyện Bình Chánh, Thành phố Hồ Chí Minh','pham-van-hai',785),(27631,'Đa Phước','Xã Đa Phước','Xã Đa Phước, Huyện Bình Chánh, Thành phố Hồ Chí Minh','da-phuoc',785),(27595,'Tân Túc','Thị trấn Tân Túc','Thị trấn Tân Túc, Huyện Bình Chánh, Thành phố Hồ Chí Minh','tan-tuc',785),(27640,'Quy Đức','Xã Quy Đức','Xã Quy Đức, Huyện Bình Chánh, Thành phố Hồ Chí Minh','quy-duc',785),(26896,'01','Phường 01','Phường 01, Quận Gò Vấp, Thành phố Hồ Chí Minh','01',764),(26884,'10','Phường 10','Phường 10, Quận Gò Vấp, Thành phố Hồ Chí Minh','10',764),(26876,'6','Phường 6','Phường 6, Quận Gò Vấp, Thành phố Hồ Chí Minh','6',764),(26887,'05','Phường 05','Phường 05, Quận Gò Vấp, Thành phố Hồ Chí Minh','05',764),(26898,'8','Phường 8','Phường 8, Quận Gò Vấp, Thành phố Hồ Chí Minh','8',764),(26875,'17','Phường 17','Phường 17, Quận Gò Vấp, Thành phố Hồ Chí Minh','17',764),(26897,'9','Phường 9','Phường 9, Quận Gò Vấp, Thành phố Hồ Chí Minh','9',764),(26878,'16','Phường 16','Phường 16, Quận Gò Vấp, Thành phố Hồ Chí Minh','16',764),(26899,'11','Phường 11','Phường 11, Quận Gò Vấp, Thành phố Hồ Chí Minh','11',764),(26869,'15','Phường 15','Phường 15, Quận Gò Vấp, Thành phố Hồ Chí Minh','15',764),(26902,'03','Phường 03','Phường 03, Quận Gò Vấp, Thành phố Hồ Chí Minh','03',764),(26890,'07','Phường 07','Phường 07, Quận Gò Vấp, Thành phố Hồ Chí Minh','07',764),(26881,'12','Phường 12','Phường 12, Quận Gò Vấp, Thành phố Hồ Chí Minh','12',764),(26872,'13','Phường 13','Phường 13, Quận Gò Vấp, Thành phố Hồ Chí Minh','13',764),(26882,'14','Phường 14','Phường 14, Quận Gò Vấp, Thành phố Hồ Chí Minh','14',764),(26893,'04','Phường 04','Phường 04, Quận Gò Vấp, Thành phố Hồ Chí Minh','04',764),(27349,'13','Phường 13','Phường 13, Quận 6, Thành phố Hồ Chí Minh','13',775),(27346,'14','Phường 14','Phường 14, Quận 6, Thành phố Hồ Chí Minh','14',775),(27379,'03','Phường 03','Phường 03, Quận 6, Thành phố Hồ Chí Minh','03',775),(27358,'12','Phường 12','Phường 12, Quận 6, Thành phố Hồ Chí Minh','12',775),(27373,'04','Phường 04','Phường 04, Quận 6, Thành phố Hồ Chí Minh','04',775),(27352,'09','Phường 09','Phường 09, Quận 6, Thành phố Hồ Chí Minh','09',775),(27385,'10','Phường 10','Phường 10, Quận 6, Thành phố Hồ Chí Minh','10',775),(27382,'07','Phường 07','Phường 07, Quận 6, Thành phố Hồ Chí Minh','07',775),(27361,'05','Phường 05','Phường 05, Quận 6, Thành phố Hồ Chí Minh','05',775),(27355,'06','Phường 06','Phường 06, Quận 6, Thành phố Hồ Chí Minh','06',775),(27367,'02','Phường 02','Phường 02, Quận 6, Thành phố Hồ Chí Minh','02',775),(27364,'11','Phường 11','Phường 11, Quận 6, Thành phố Hồ Chí Minh','11',775),(27376,'08','Phường 08','Phường 08, Quận 6, Thành phố Hồ Chí Minh','08',775),(27370,'01','Phường 01','Phường 01, Quận 6, Thành phố Hồ Chí Minh','01',775),(27652,'Nhơn Đức','Xã Nhơn Đức','Xã Nhơn Đức, Huyện Nhà Bè, Thành phố Hồ Chí Minh','nhon-duc',786),(27661,'Hiệp Phước','Xã Hiệp Phước','Xã Hiệp Phước, Huyện Nhà Bè, Thành phố Hồ Chí Minh','hiep-phuoc',786),(27646,'Phước Kiển','Xã Phước Kiển','Xã Phước Kiển, Huyện Nhà Bè, Thành phố Hồ Chí Minh','phuoc-kien',786),(27643,'Nhà Bè','Thị trấn Nhà Bè','Thị trấn Nhà Bè, Huyện Nhà Bè, Thành phố Hồ Chí Minh','nha-be',786),(27655,'Phú Xuân','Xã Phú Xuân','Xã Phú Xuân, Huyện Nhà Bè, Thành phố Hồ Chí Minh','phu-xuan',786),(27649,'Phước Lộc','Xã Phước Lộc','Xã Phước Lộc, Huyện Nhà Bè, Thành phố Hồ Chí Minh','phuoc-loc',786),(27658,'Long Thới','Xã Long Thới','Xã Long Thới, Huyện Nhà Bè, Thành phố Hồ Chí Minh','long-thoi',786),(26926,'07','Phường 07','Phường 07, Quận Bình Thạnh, Thành phố Hồ Chí Minh','07',765),(26959,'19','Phường 19','Phường 19, Quận Bình Thạnh, Thành phố Hồ Chí Minh','19',765),(26914,'26','Phường 26','Phường 26, Quận Bình Thạnh, Thành phố Hồ Chí Minh','26',765),(26947,'03','Phường 03','Phường 03, Quận Bình Thạnh, Thành phố Hồ Chí Minh','03',765),(26917,'12','Phường 12','Phường 12, Quận Bình Thạnh, Thành phố Hồ Chí Minh','12',765),(26905,'13','Phường 13','Phường 13, Quận Bình Thạnh, Thành phố Hồ Chí Minh','13',765),(26938,'15','Phường 15','Phường 15, Quận Bình Thạnh, Thành phố Hồ Chí Minh','15',765),(26908,'11','Phường 11','Phường 11, Quận Bình Thạnh, Thành phố Hồ Chí Minh','11',765),(26929,'24','Phường 24','Phường 24, Quận Bình Thạnh, Thành phố Hồ Chí Minh','24',765),(26962,'28','Phường 28','Phường 28, Quận Bình Thạnh, Thành phố Hồ Chí Minh','28',765),(26950,'17','Phường 17','Phường 17, Quận Bình Thạnh, Thành phố Hồ Chí Minh','17',765),(26920,'25','Phường 25','Phường 25, Quận Bình Thạnh, Thành phố Hồ Chí Minh','25',765),(26953,'21','Phường 21','Phường 21, Quận Bình Thạnh, Thành phố Hồ Chí Minh','21',765),(26941,'02','Phường 02','Phường 02, Quận Bình Thạnh, Thành phố Hồ Chí Minh','02',765),(26911,'27','Phường 27','Phường 27, Quận Bình Thạnh, Thành phố Hồ Chí Minh','27',765),(26944,'01','Phường 01','Phường 01, Quận Bình Thạnh, Thành phố Hồ Chí Minh','01',765),(26932,'06','Phường 06','Phường 06, Quận Bình Thạnh, Thành phố Hồ Chí Minh','06',765),(26935,'14','Phường 14','Phường 14, Quận Bình Thạnh, Thành phố Hồ Chí Minh','14',765),(26923,'05','Phường 05','Phường 05, Quận Bình Thạnh, Thành phố Hồ Chí Minh','05',765),(26956,'22','Phường 22','Phường 22, Quận Bình Thạnh, Thành phố Hồ Chí Minh','22',765),(27409,'04','Phường 04','Phường 04, Quận 8, Thành phố Hồ Chí Minh','04',776),(27403,'09','Phường 09','Phường 09, Quận 8, Thành phố Hồ Chí Minh','09',776),(27415,'12','Phường 12','Phường 12, Quận 8, Thành phố Hồ Chí Minh','12',776),(27412,'13','Phường 13','Phường 13, Quận 8, Thành phố Hồ Chí Minh','13',776),(27424,'06','Phường 06','Phường 06, Quận 8, Thành phố Hồ Chí Minh','06',776),(27418,'05','Phường 05','Phường 05, Quận 8, Thành phố Hồ Chí Minh','05',776),(27427,'15','Phường 15','Phường 15, Quận 8, Thành phố Hồ Chí Minh','15',776),(27406,'10','Phường 10','Phường 10, Quận 8, Thành phố Hồ Chí Minh','10',776),(27394,'01','Phường 01','Phường 01, Quận 8, Thành phố Hồ Chí Minh','01',776),(27388,'08','Phường 08','Phường 08, Quận 8, Thành phố Hồ Chí Minh','08',776),(27421,'14','Phường 14','Phường 14, Quận 8, Thành phố Hồ Chí Minh','14',776),(27400,'11','Phường 11','Phường 11, Quận 8, Thành phố Hồ Chí Minh','11',776),(27433,'07','Phường 07','Phường 07, Quận 8, Thành phố Hồ Chí Minh','07',776),(27397,'03','Phường 03','Phường 03, Quận 8, Thành phố Hồ Chí Minh','03',776),(27430,'16','Phường 16','Phường 16, Quận 8, Thành phố Hồ Chí Minh','16',776),(27391,'02','Phường 02','Phường 02, Quận 8, Thành phố Hồ Chí Minh','02',776),(27670,'Tam Thôn Hiệp','Xã Tam Thôn Hiệp','Xã Tam Thôn Hiệp, Huyện Cần Giờ, Thành phố Hồ Chí Minh','tam-thon-hiep',787),(27682,'Lý Nhơn','Xã Lý Nhơn','Xã Lý Nhơn, Huyện Cần Giờ, Thành phố Hồ Chí Minh','ly-nhon',787),(27664,'Cần Thạnh','Thị trấn Cần Thạnh','Thị trấn Cần Thạnh, Huyện Cần Giờ, Thành phố Hồ Chí Minh','can-thanh',787),(27673,'An Thới Đông','Xã An Thới Đông','Xã An Thới Đông, Huyện Cần Giờ, Thành phố Hồ Chí Minh','an-thoi-dong',787),(27667,'Bình Khánh','Xã Bình Khánh','Xã Bình Khánh, Huyện Cần Giờ, Thành phố Hồ Chí Minh','binh-khanh',787),(27679,'Long Hòa','Xã Long Hòa','Xã Long Hòa, Huyện Cần Giờ, Thành phố Hồ Chí Minh','long-hoa',787),(27676,'Thạnh An','Xã Thạnh An','Xã Thạnh An, Huyện Cần Giờ, Thành phố Hồ Chí Minh','thanh-an',787),(26974,'13','Phường 13','Phường 13, Quận Tân Bình, Thành phố Hồ Chí Minh','13',766),(27007,'15','Phường 15','Phường 15, Quận Tân Bình, Thành phố Hồ Chí Minh','15',766),(26986,'07','Phường 07','Phường 07, Quận Tân Bình, Thành phố Hồ Chí Minh','07',766),(26983,'11','Phường 11','Phường 11, Quận Tân Bình, Thành phố Hồ Chí Minh','11',766),(26995,'06','Phường 06','Phường 06, Quận Tân Bình, Thành phố Hồ Chí Minh','06',766),(26989,'05','Phường 05','Phường 05, Quận Tân Bình, Thành phố Hồ Chí Minh','05',766),(26965,'02','Phường 02','Phường 02, Quận Tân Bình, Thành phố Hồ Chí Minh','02',766),(26968,'04','Phường 04','Phường 04, Quận Tân Bình, Thành phố Hồ Chí Minh','04',766),(26998,'08','Phường 08','Phường 08, Quận Tân Bình, Thành phố Hồ Chí Minh','08',766),(26977,'01','Phường 01','Phường 01, Quận Tân Bình, Thành phố Hồ Chí Minh','01',766),(26992,'10','Phường 10','Phường 10, Quận Tân Bình, Thành phố Hồ Chí Minh','10',766),(26971,'12','Phường 12','Phường 12, Quận Tân Bình, Thành phố Hồ Chí Minh','12',766),(27004,'14','Phường 14','Phường 14, Quận Tân Bình, Thành phố Hồ Chí Minh','14',766),(27001,'09','Phường 09','Phường 09, Quận Tân Bình, Thành phố Hồ Chí Minh','09',766),(26980,'03','Phường 03','Phường 03, Quận Tân Bình, Thành phố Hồ Chí Minh','03',766),(27451,'Bình Trị Đông B','Phường Bình Trị Đông B','Phường Bình Trị Đông B, Quận Bình Tân, Thành phố Hồ Chí Minh','binh-tri-dong-b',777),(27460,'An Lạc','Phường  An Lạc','Phường  An Lạc, Quận Bình Tân, Thành phố Hồ Chí Minh','an-lac',777),(27454,'Tân Tạo','Phường Tân Tạo','Phường Tân Tạo, Quận Bình Tân, Thành phố Hồ Chí Minh','tan-tao',777),(27463,'An Lạc A','Phường An Lạc A','Phường An Lạc A, Quận Bình Tân, Thành phố Hồ Chí Minh','an-lac-a',777),(27442,'Bình Hưng Hoà B','Phường Bình Hưng Hoà B','Phường Bình Hưng Hoà B, Quận Bình Tân, Thành phố Hồ Chí Minh','binh-hung-hoa-b',777),(27436,'Bình Hưng Hòa','Phường Bình Hưng Hòa','Phường Bình Hưng Hòa, Quận Bình Tân, Thành phố Hồ Chí Minh','binh-hung-hoa',777),(27448,'Bình Trị Đông A','Phường Bình Trị Đông A','Phường Bình Trị Đông A, Quận Bình Tân, Thành phố Hồ Chí Minh','binh-tri-dong-a',777),(27445,'Bình Trị Đông','Phường Bình Trị Đông','Phường Bình Trị Đông, Quận Bình Tân, Thành phố Hồ Chí Minh','binh-tri-dong',777),(27457,'Tân Tạo A','Phường Tân Tạo A','Phường Tân Tạo A, Quận Bình Tân, Thành phố Hồ Chí Minh','tan-tao-a',777),(27439,'Bình Hưng Hoà A','Phường Bình Hưng Hoà A','Phường Bình Hưng Hoà A, Quận Bình Tân, Thành phố Hồ Chí Minh','binh-hung-hoa-a',777),(27010,'Tân Sơn Nhì','Phường Tân Sơn Nhì','Phường Tân Sơn Nhì, Quận Tân Phú, Thành phố Hồ Chí Minh','tan-son-nhi',767),(27022,'Tân Thành','Phường Tân Thành','Phường Tân Thành, Quận Tân Phú, Thành phố Hồ Chí Minh','tan-thanh',767),(27031,'Phú Trung','Phường Phú Trung','Phường Phú Trung, Quận Tân Phú, Thành phố Hồ Chí Minh','phu-trung',767),(27025,'Phú Thọ Hòa','Phường Phú Thọ Hòa','Phường Phú Thọ Hòa, Quận Tân Phú, Thành phố Hồ Chí Minh','phu-tho-hoa',767),(27037,'Hiệp Tân','Phường Hiệp Tân','Phường Hiệp Tân, Quận Tân Phú, Thành phố Hồ Chí Minh','hiep-tan',767),(27034,'Hòa Thạnh','Phường Hòa Thạnh','Phường Hòa Thạnh, Quận Tân Phú, Thành phố Hồ Chí Minh','hoa-thanh',767),(27013,'Tây Thạnh','Phường Tây Thạnh','Phường Tây Thạnh, Quận Tân Phú, Thành phố Hồ Chí Minh','tay-thanh',767),(27019,'Tân Quý','Phường Tân Quý','Phường Tân Quý, Quận Tân Phú, Thành phố Hồ Chí Minh','tan-quy',767),(27016,'Sơn Kỳ','Phường Sơn Kỳ','Phường Sơn Kỳ, Quận Tân Phú, Thành phố Hồ Chí Minh','son-ky',767),(27028,'Phú Thạnh','Phường Phú Thạnh','Phường Phú Thạnh, Quận Tân Phú, Thành phố Hồ Chí Minh','phu-thanh',767),(27040,'Tân Thới Hòa','Phường Tân Thới Hòa','Phường Tân Thới Hòa, Quận Tân Phú, Thành phố Hồ Chí Minh','tan-thoi-hoa',767),(27472,'Tân Kiểng','Phường Tân Kiểng','Phường Tân Kiểng, Quận 7, Thành phố Hồ Chí Minh','tan-kieng',778),(27484,'Phú Thuận','Phường Phú Thuận','Phường Phú Thuận, Quận 7, Thành phố Hồ Chí Minh','phu-thuan',778),(27481,'Tân Quy','Phường Tân Quy','Phường Tân Quy, Quận 7, Thành phố Hồ Chí Minh','tan-quy',778),(27493,'Phú Mỹ','Phường Phú Mỹ','Phường Phú Mỹ, Quận 7, Thành phố Hồ Chí Minh','phu-my',778),(27487,'Tân Phú','Phường Tân Phú','Phường Tân Phú, Quận 7, Thành phố Hồ Chí Minh','tan-phu',778),(27466,'Tân Thuận Đông','Phường Tân Thuận Đông','Phường Tân Thuận Đông, Quận 7, Thành phố Hồ Chí Minh','tan-thuan-dong',778),(27475,'Tân Hưng','Phường Tân Hưng','Phường Tân Hưng, Quận 7, Thành phố Hồ Chí Minh','tan-hung',778),(27469,'Tân Thuận Tây','Phường Tân Thuận Tây','Phường Tân Thuận Tây, Quận 7, Thành phố Hồ Chí Minh','tan-thuan-tay',778),(27478,'Bình Thuận','Phường Bình Thuận','Phường Bình Thuận, Quận 7, Thành phố Hồ Chí Minh','binh-thuan',778),(27490,'Tân Phong','Phường Tân Phong','Phường Tân Phong, Quận 7, Thành phố Hồ Chí Minh','tan-phong',778),(27049,'09','Phường 09','Phường 09, Quận Phú Nhuận, Thành phố Hồ Chí Minh','09',768),(27043,'04','Phường 04','Phường 04, Quận Phú Nhuận, Thành phố Hồ Chí Minh','04',768),(27076,'17','Phường 17','Phường 17, Quận Phú Nhuận, Thành phố Hồ Chí Minh','17',768),(27055,'03','Phường 03','Phường 03, Quận Phú Nhuận, Thành phố Hồ Chí Minh','03',768),(27052,'07','Phường 07','Phường 07, Quận Phú Nhuận, Thành phố Hồ Chí Minh','07',768),(27085,'13','Phường 13','Phường 13, Quận Phú Nhuận, Thành phố Hồ Chí Minh','13',768),(27064,'08','Phường 08','Phường 08, Quận Phú Nhuận, Thành phố Hồ Chí Minh','08',768),(27058,'01','Phường 01','Phường 01, Quận Phú Nhuận, Thành phố Hồ Chí Minh','01',768),(27067,'15','Phường 15','Phường 15, Quận Phú Nhuận, Thành phố Hồ Chí Minh','15',768),(27046,'05','Phường 05','Phường 05, Quận Phú Nhuận, Thành phố Hồ Chí Minh','05',768),(27079,'14','Phường 14','Phường 14, Quận Phú Nhuận, Thành phố Hồ Chí Minh','14',768),(27061,'02','Phường 02','Phường 02, Quận Phú Nhuận, Thành phố Hồ Chí Minh','02',768),(27073,'11','Phường 11','Phường 11, Quận Phú Nhuận, Thành phố Hồ Chí Minh','11',768),(27070,'10','Phường 10','Phường 10, Quận Phú Nhuận, Thành phố Hồ Chí Minh','10',768),(27082,'12','Phường 12','Phường 12, Quận Phú Nhuận, Thành phố Hồ Chí Minh','12',768),(27088,'Thảo Điền','Phường Thảo Điền','Phường Thảo Điền, Quận 2, Thành phố Hồ Chí Minh','thao-dien',769),(27097,'Bình Trưng Đông','Phường Bình Trưng Đông','Phường Bình Trưng Đông, Quận 2, Thành phố Hồ Chí Minh','binh-trung-dong',769),(27103,'Bình Khánh','Phường Bình Khánh','Phường Bình Khánh, Quận 2, Thành phố Hồ Chí Minh','binh-khanh',769),(27100,'Bình Trưng Tây','Phường Bình Trưng Tây','Phường Bình Trưng Tây, Quận 2, Thành phố Hồ Chí Minh','binh-trung-tay',769),(27112,'Thạnh Mỹ Lợi','Phường Thạnh Mỹ Lợi','Phường Thạnh Mỹ Lợi, Quận 2, Thành phố Hồ Chí Minh','thanh-my-loi',769),(27106,'An Khánh','Phường An Khánh','Phường An Khánh, Quận 2, Thành phố Hồ Chí Minh','an-khanh',769),(27091,'An Phú','Phường An Phú','Phường An Phú, Quận 2, Thành phố Hồ Chí Minh','an-phu',769),(27118,'Thủ Thiêm','Phường Thủ Thiêm','Phường Thủ Thiêm, Quận 2, Thành phố Hồ Chí Minh','thu-thiem',769),(27115,'An Lợi Đông','Phường An Lợi Đông','Phường An Lợi Đông, Quận 2, Thành phố Hồ Chí Minh','an-loi-dong',769),(27094,'Bình An','Phường Bình An','Phường Bình An, Quận 2, Thành phố Hồ Chí Minh','binh-an',769),(27109,'Cát Lái','Phường Cát Lái','Phường Cát Lái, Quận 2, Thành phố Hồ Chí Minh','cat-lai',769);
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-16 15:31:05
