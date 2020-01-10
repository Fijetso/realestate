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
INSERT INTO `address` VALUES (1,'Số nhà 53',760,79,26749),(2,'Số nhà 201',760,79,26746),(3,'Số nhà 94',762,79,26827),(4,'Số nhà 94',762,79,27301),(5,'Số nhà 94',774,79,27313),(6,'Số nhà 201',774,79,27328),(28,'34 Tân Hưng',774,79,27313),(7,'X.Bà Điểm, H.Hóc Môn',784,79,27592),(8,'P.14, Q.8',776,79,27421),(9,'P.Thạnh Xuân, Q.12',761,79,26764),(10,'P.Bình Trị Đông, Q.Bình Tân',777,79,27445),(11,'P.16, Q.Gò Vấp',764,79,26878),(12,'P.Phú Trung, Q.Tân Phú',767,79,27031),(13,'P.Bình Hưng Hòa A, Q.Bình Tân',777,79,27436),(14,'P.12, Q.3',770,79,27130),(15,'P.Tam Bình, Q.Thủ Đức',762,79,26803),(16,'P.Tân Tạo A, Q.Bình Tân',777,79,27457),(17,'X.Tân Xuân, H.Hóc Môn',784,79,27580),(18,'P.Phước Long A, Q.9',763,79,26851),(19,'P.Phú Thọ Hòa, Q.Tân Phú',767,79,27025),(20,'P.14, Q.8',776,79,27421);
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
INSERT INTO `category` VALUES (1,'360 Bất động sản'),(2,'Tin thị trường'),(3,'Truyền thông'),(4,'Phong thuỷ'),(5,'Kiến trúc'),(6,'Kinh nghiệm');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confirmationtoken`
--

DROP TABLE IF EXISTS `confirmationtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `confirmationtoken` (
  `tokenid` bigint(20) NOT NULL,
  `confirmationToken` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`tokenid`),
  KEY `FKp74ll2607keu3i6lwsnst5iwy` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmationtoken`
--

LOCK TABLES `confirmationtoken` WRITE;
/*!40000 ALTER TABLE `confirmationtoken` DISABLE KEYS */;
INSERT INTO `confirmationtoken` VALUES (10,'81ffaceb-cfaf-4f9d-9fd2-012f619210e6','2019-10-04 08:21:13',9),(11,'dd861e6a-2d88-49ea-9b68-3ff3ac4ba9a6','2019-10-04 08:38:17',9),(13,'57fc2fbb-991f-49fa-9c62-bec02df2c19a','2019-10-04 17:06:23',12),(36,'217cbae0-3fe6-465c-afe5-2b1419912e21','2019-12-03 10:51:21',34),(39,'b0c50660-3b34-466d-9fb9-0b8684799180','2019-12-03 15:34:48',37),(42,'4223205b-5b95-4ad0-9bec-86775f75983b','2019-12-03 16:11:45',40),(45,'48cc8e51-89f4-41a6-9c17-ec9fee750c2a','2019-12-03 16:57:26',43),(46,'d13f927d-1c26-48f1-8980-ee1ea0e82179','2019-12-03 17:10:42',43),(49,'4374966e-f0ed-44fc-9cac-fb2e995fb8b4','2019-12-04 12:07:55',47),(87,'976b9a90-669f-4d4e-ba22-e30bbe095561','2019-12-07 16:33:58',86),(93,'02afdd0c-9ccf-4c66-8fc3-94a637239b8a','2019-12-07 16:55:27',91),(96,'6ec410a6-e032-424a-bc37-418fdf235a38','2019-12-07 16:56:08',94),(103,'d0933c4d-ae2f-4989-9555-612f79b24091','2019-12-07 23:14:11',102),(105,'d5b8e678-81b7-4a4a-9c9c-c76f37233ccb','2019-12-07 23:14:58',104),(109,'59396bd1-b704-4c69-888c-70c3a52a99cc','2019-12-07 23:16:39',107),(118,'fae25df2-671f-4cc2-8947-40338b2654ed','2019-12-08 11:55:58',117),(121,'56f6c28d-1c13-425f-b8cd-f4cdb70f610c','2019-12-08 11:56:44',120),(131,'8980d850-c75d-4534-9e53-ccb76bd58d71','2019-12-16 14:43:57',130),(133,'4f47a930-f377-4c9e-b0ff-1725ac094a52','2019-12-16 16:41:17',132),(139,'8c466118-13dc-48e0-88f4-e72dc2bab47a','2019-12-18 12:10:28',138),(141,'3339f607-a588-4a99-9ff9-7b0c5dfcb8e9','2019-12-18 12:43:27',140);
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
INSERT INTO `coordinate` VALUES (1,10.7683,106.687),(2,10.7684,106.699),(3,10.8403,106.757),(4,10.8577,106.734),(5,10.7614,106.681),(6,10.7538,106.662),(20,106.651,10.7409),(19,106.624,10.7859),(18,106.768,10.8214),(17,106.595,10.8673),(16,106.585,10.7503),(15,106.73,10.8679),(14,106.673,10.7878),(13,106.605,10.7847),(12,106.636,10.7711),(11,106.668,10.8427),(10,106.618,10.7619),(9,106.672,10.8628),(8,106.644,10.7377),(7,106.602,10.8504);
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
  `length` float NOT NULL,
  `others` varchar(255) DEFAULT NULL,
  `square` float NOT NULL,
  `utilities` varchar(255) DEFAULT NULL,
  `width` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details` DISABLE KEYS */;
INSERT INTO `details` VALUES (1,2,2,'Đông Bắc','1 trệt + 1 lầu','Sổ hồng riêng',20,'Nhà mặt tiền Quận 1, nhà đang cho thuê, sổ hồng chính chủ',665,'Gần chợ, siêu thị',4),(2,2,2,'Đông Bắc','1 trệt + 1 lầu','Sổ hồng riêng',20,'Nhà mặt tiền Quận 1, nhà đang cho thuê, sổ hồng chính chủ',665,'Gần chợ, siêu thị',50),(3,3,3,'Tây','1 trệt + 1 lầu','Sổ hồng riêng',17,'Bán nhà Quận Thủ Đức. Khu dân cư an ninh, yên tĩnh, không ngập nước, gần trường THPT Bách Việt',456,'Gần chợ, siêu thị, gần trường học, gần công viên, trung tâm',50),(4,2,1,'Tây','1 trệt + 1 lầu','Sổ hồng riêng',17,'Bán nhà Quận Thủ Đức. Khu dân cư an ninh, yên tĩnh, không ngập nước, gần trường THPT Bách Việt',434,'Gần chợ, siêu thị, gần trường học, gần công viên, trung tâm',50),(5,1,2,'Tây','1 trệt + 1 lầu','Sổ hồng riêng',9,'Bán nhà riêng quận 5, đang cho thuê 6tr/tháng sổ hồng chính chủ',37,'Gần chợ, siêu thị, gần trường học',4),(6,0,0,'Đông','1 trệt + 1 lầu','Sổ hồng riêng',7,'Bán nhà riêng quận 5, Sổ hồng chính chủ - Pháp lý minh bạch, rõ ràng',24,'Gần chợ, siêu thị, gần trường học',4),(8,0,0,'Tây Nam','1 trệt, 1 lửng','Sổ hồng riêng',11,NULL,33,'Gần chợ, siêu thị;Gần trường học;Gần mặt tiền đường;Cần bán gấp;Di chuyển thuận tiện ra trung tâm;Thiết kế thông thoáng',3),(7,0,0,'Tây Nam','1 trệt, 1 lửng','Sổ hồng riêng',1246,NULL,557000,'Gần chợ, siêu thị;Gần trường học;Gần mặt tiền đường;Khu vực an ninh, yên tĩnh',448),(9,0,0,'Đông','1 trệt + 1 lầu','Sổ hồng riêng',13.25,NULL,59.3,'Gần mặt tiền đường;Di chuyển thuận tiện ra trung tâm;Khu vực an ninh, yên tĩnh;Sân để xe rộng rãi',4.5),(10,0,0,'Tây Bắc','1 trệt + 1 lầu','Sổ hồng riêng',6,NULL,14.2,'Gần chợ, siêu thị;Gần trường học;Gần mặt tiền đường;Di chuyển thuận tiện ra trung tâm;Thiết kế thông thoáng;Khu vực an ninh, yên tĩnh',3),(11,0,0,'Đông Nam','1 trệt','Sổ hồng riêng',15.5,NULL,50.2,'Gần trường học;Di chuyển thuận tiện ra trung tâm;Thiết kế thông thoáng;Khu vực an ninh, yên tĩnh',3.51),(12,0,0,'Nam','1 trệt + 1 lầu','Sổ hồng riêng',9.25,NULL,21.7,'Gần chợ, siêu thị;Gần trường học;Di chuyển thuận tiện ra trung tâm;Khu vực an ninh, yên tĩnh',2.35),(13,0,0,'Đông','1 trệt, 1 lửng','Sổ hồng riêng',9,NULL,35.8,'Gần chợ, siêu thị;Gần trường học;Gần mặt tiền đường;Thiết kế thông thoáng;Khu vực an ninh, yên tĩnh',4),(14,0,0,'Nam','1 trệt, 1 lửng + 1 lầu','Sổ hồng riêng',3.52,NULL,6.4,'Gần chợ, siêu thị;Gần trường học',2),(15,0,0,'Đông Nam','1 trệt, 1 lửng','Sổ hồng riêng',8.58,NULL,40.9,'Gần chợ, siêu thị;Gần công viên, trung tâm;Gần trường học;Gần mặt tiền đường;Di chuyển thuận tiện ra trung tâm;Khu vực an ninh, yên tĩnh;Sân để xe rộng rãi',4.73),(16,0,0,'Nam','1 trệt, 1 lửng','Sổ hồng riêng',9,NULL,36,'Gần chợ, siêu thị;Gần bệnh viện;Gần trường học;Hẻm thông;Gần mặt tiền đường;Thiết kế thông thoáng',4),(17,0,0,'Đông Nam','1 trệt, 1 lửng','Sổ hồng riêng',11.1,NULL,44.3,'Gần chợ, siêu thị;Gần trường học;Gần mặt tiền đường;Thiết kế thông thoáng;Khu vực an ninh, yên tĩnh',4),(18,0,0,'Tây Bắc','1 trệt','Sổ hồng riêng',12,NULL,50,'Gần chợ, siêu thị;Gần công viên, trung tâm;Gần trường học;Gần mặt tiền đường;Cần bán gấp;Di chuyển thuận tiện ra trung tâm;Thiết kế thông thoáng;Khu vực an ninh, yên tĩnh;Sân để xe rộng rãi',4.52),(19,0,0,'Đông Bắc','1 trệt + 1 lầu','Sổ hồng riêng',5.2,NULL,13.6,NULL,2.7),(20,0,0,'Nam','1 trệt','Sổ hồng riêng',12.05,NULL,58.1,'Gần chợ, siêu thị',4.87);
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
INSERT INTO `hibernate_sequence` VALUES (328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328),(328);
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
  `content` longtext,
  `title` varchar(255) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2yk322xd1afw7edtdvuk0smny` (`categoryId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2020-01-02 00:00:00','<html>↵<head>↵	<title></title>↵</head>↵<body>↵<p><em>L&agrave; một nh&agrave; đầu tư bất động sản th&agrave;nh c&ocirc;ng điều quan trọng nhất li&ecirc;n tục cập nhật c&aacute;c xu hướng thị trường bất động sản từ đ&oacute; ph&acirc;n t&iacute;ch v&agrave; đưa ra những nhận định để chuẩn bị cho bất kỳ sự thay đổi trong tương lai. C&ugrave;ng Interhome điểm lại thị trường BĐS Mỹ v&agrave; những thay đổi lớn trong năm 2018.&nbsp;</em></p>↵↵<p>&nbsp;</p>↵↵<p><strong>Bất động sản tồn kho c&oacute; xu hướng giảm mạnh</strong><br />↵Sản phẩm tồn kho trong bất động sản l&agrave; những ng&ocirc;i nh&agrave; bị ho&aacute; gi&aacute; tr&ecirc;n to&agrave;n nước Mỹ trong những năm khủng hoảng t&agrave;i ch&iacute;nh đang tr&ecirc;n đ&agrave; biến đổi giảm. Sản phẩm n&agrave;y đ&atilde; thực sự được cải thiện ở một số thị trường đắt nhất trong nước. Như tại 5 trong số 10 thị trường c&oacute; mức tăng lớn nhất h&agrave;ng năm l&agrave; ở California, nơi gi&aacute; bất động sản thuộc h&agrave;ng cao nhất trong thị trường bất động sản Mỹ.&nbsp;<br />↵&nbsp;</p>↵↵<p><img alt=\"thi-truong-bds-my-va-nhung-thay-doi-trong-nam-2018\" src=\"https://www.interhome.vn/upload/images/Blog/tin%2023.05/blog12-1.jpg\" /></p>↵↵<p>&nbsp;</p>↵↵<p>Hơn nữa, &nbsp;Seattle &nbsp;cũng đ&atilde; trải qua một sự thay đổi h&agrave;ng năm cải thiện h&agrave;ng tồn kho, tăng 45% so với một năm trước. C&aacute;c địa điểm ở California c&oacute; mức tăng BĐS tồn kho lớn nhất l&agrave;: San Jose (66,9%), San Diego (37,7%), Quận Ventura (31,6%), Oakland (25,9%), Quận Cam (20,7%).&nbsp;</p>↵↵<p><br />↵Đ&acirc;y l&agrave; tin tốt cho người mua v&agrave; nh&agrave; đầu tư bất động sản thất vọng v&igrave; thiếu nh&agrave; để b&aacute;n v&agrave; gi&aacute; bất động sản tăng nhanh. Khi c&oacute; nhiều nguồn cung t&agrave;i sản để đ&aacute;p ứng nhu cầu, điều n&agrave;y gi&uacute;p giảm nhiệt nhu cầu bất động sản v&agrave; l&agrave;m chậm qu&aacute; tr&igrave;nh tăng gi&aacute;.&nbsp;<br />↵&nbsp;</p>↵↵<p><strong>Gi&aacute; cả phải chăng</strong><br />↵ATTOM Data Solutions gần đ&acirc;y đ&atilde; c&ocirc;ng bố b&aacute;o c&aacute;o khả năng chi trả nh&agrave; ở Mỹ năm 2018, cho thấy gi&aacute; nh&agrave; ở Mỹ trong qu&yacute; 3 ở mức thấp nhất kể từ năm 2008, theo b&aacute;o c&aacute;o, chỉ số khả năng chi trả nh&agrave; ở thị trường bất động sản Mỹ trong qu&yacute; 3 năm 2018 l&agrave; 92 - giảm so với chỉ số 95 trong qu&yacute; trước v&agrave; 102 trong qu&yacute; 3/2017.</p>↵↵<p><img alt=\"thi-truong-bds-my-va-nhung-thay-doi-trong-nam-2018\" src=\"https://www.interhome.vn/upload/images/Blog/tin%2023.05/blog12-2.jpg\" /></p>↵↵<p>Hơn nữa, gi&aacute; nh&agrave; đ&atilde; tăng nhanh hơn nhiều so với thu nhập trong năm qua. Khả năng chi trả nh&agrave; ở đ&atilde; ảnh hưởng đến tất cả c&aacute;c ph&acirc;n kh&uacute;c nh&agrave; ở khi nh&agrave; đầu tư phải chi nhiều hơn thu nhập của họ để c&oacute; được một ng&ocirc;i nh&agrave; so với một năm trước.</p>↵↵<p><br />↵<strong>Xu hướng thị trường bất động sản Mỹ - Điểm mấu chốt</strong></p>↵↵<p>C&aacute;c nh&agrave; đầu tư bất động sản phải lu&ocirc;n lu&ocirc;n theo d&otilde;i c&aacute;c xu hướng kh&aacute;c nhau của thị trường nh&agrave; đất hiện tại để c&oacute; thể dự đo&aacute;n thị trường sẽ đi đến đ&acirc;u v&agrave; do đ&oacute;, đưa ra quyết định đầu tư th&ocirc;ng minh ph&ugrave; hợp. Dựa tr&ecirc;n qu&yacute; 3 năm 2018 Interhome cung cấp một xu hướng thị trường bất động sản Mỹ cho c&aacute;c &nbsp;nh&agrave; đầu tư bất động sản c&oacute; rất nhiều điều cần xem x&eacute;t trước khi quyết định b&aacute;n hoặc mua một t&agrave;i sản đầu tư.&nbsp;</p>↵↵<div id=\"eJOY__extension_root\" style=\"all:unset\">&nbsp;</div>↵</body>↵</html>↵','Thị trường Bất động sản Mỹ và những thay đổi trong năm 2019',2,'Nguyễn Thị Ngọc Huyền');
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
INSERT INTO `realimage` VALUES (1,'Real Estate','https://res.cloudinary.com/fijetso3671/image/upload/v1559034773/RealEstate/maxresdefault_ropnun.jpg',1),(2,'Real Estate','https://res.cloudinary.com/fijetso3671/image/upload/v1559034774/RealEstate/uploads_2Fcard_2Fimage_2F829044_2Ff1a11a98-59ed-46a9-a2df-bf2a6997ee31.jpg_2F950x534__filters_3Aquality_2890_29_eiw5xu.jpg',1),(19,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1559860911/RealEstate/kql6ziprexdjjvcqmn1m.webp',2),(20,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559860911/RealEstate/kql6ziprexdjjvcqmn1m.webp',2),(3,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559872643/RealEstate/201810225152_20181025_115002_diaias.jpg',2),(4,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1559872755/RealEstate/20160420062223-31ae_okbvke.jpg',3),(5,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559872755/RealEstate/20160803161350-6c53_pjgujr.jpg',3),(6,'Nhà bếp','https://lacan.vn/wp-content/uploads/2016/06/10-mau-tu-bep-chu-l-dep-nhat-hien-nay-m5.jpg',3),(7,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1559873092/RealEstate/nha-rieng-pho-ban-to-chuc-quan-uy-cau-giay-dong-cua-im-im_ohou2l.jpg',4),(8,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559872756/RealEstate/20180620142809-644e_c4zz8e.jpg',4),(9,'Nhà bếp','https://gotrangtri.vn/wp-content/uploads/2018/05/thi%E1%BA%BFt-k%E1%BA%BF-n%E1%BB%99i-th%E1%BA%A5t-nh%C3%A0-b%E1%BA%BFp-%C4%91%E1%BA%B9p-c%E1%BA%A7n-c%C3%B3-g%C3%AC.jpg',4),(10,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1575531702/RealEstate/hinh-1-1544088631382690327544-crop-1544088723594514569302_j7lkio.jpg',5),(11,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559873204/RealEstate/cho-thu%C3%AA-nh%C3%A0-ri%C3%AAng-4-ph%C3%B2ng-ng%E1%BB%A7-t%E1%BA%A1i-%C4%91%E1%BA%B1ng-giang-qu%E1%BA%ADn-ng%C3%B4-quy%E1%BB%81n-h%E1%BA%A3i-ph%C3%B2ng_doolm2.jpg',5),(12,'Nhà bếp','https://nhadepsang.com.vn/images/2018/01/trang-tri-nha-bep-3.png',5),(13,'Mặt trước','https://res.cloudinary.com/fijetso3671/image/upload/v1575536762/RealEstate/20181001074725-ee3b_o6wkuc.jpg',6),(14,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559873204/RealEstate/uu-diem-cua-can-ho-chung-cu_taiqsx.jpg',6),(15,'Nhà bếp','https://www.giadinhnestle.com.vn/sites/default/files/articles/sliders/cach-trang-tri-nha-bep-theo-phong-thuy-de-dem-lai-may-man-630x350.jpg',6),(16,'Mặt trước','https://nld.mediacdn.vn/thumb_w/540/2018/4/20/123-15241955574791633741019.jpg',1),(17,'Trong nhà','https://res.cloudinary.com/fijetso3671/image/upload/v1559873204/RealEstate/dsc7489_bnlp_kkk8za.jpg',1),(18,'Nhà bếp','https://res.cloudinary.com/fijetso3671/image/upload/v1575531641/RealEstate/noi-that-bep-dep-cho-nha-nho-1_iq3bh9.jpg',1),(144,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_6b92c66f585e552b9b465a507ac252d9ac41c8b58c411bf96227b8ffb4e24a1c.jpg',7),(143,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_0d6d5179a943eaf80e71bcda7235baa85530c07e9b52b26d305f94448bf63d04.jpg',7),(142,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_e29e5cb0744aa98e08999cd05fa6f06f5013978ee6846c336b1910b76489eaae.jpg',7),(141,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_c465c22c3d3123e21b085da163be85f758093edea443bc520b4711f01e0a35d5.jpg',7),(140,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_303c16767c1caeb22ff6ea7ebfe1db2010057fbcf44182659fe87b1b8e2f75d8.jpg',7),(139,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_6901d89641c0de4368f50679e46c6c662df2402047387916d1c1e726188ed6d1.jpg',7),(138,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_bd5394b29318ba60e5fc4c7cba872e2d90d8b97d3ecdb74953a02f5423ca9af6.jpg',7),(137,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_85fab2342d3c1498d0503dfbeaf62ac05c3bdd73716a25c27d29072af6876ba7.jpg',7),(136,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_a3bff4c5ae51ddb8ed3a0823b397136c7e9597a362d3c51fc7324c39b6615675.jpg',7),(135,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_0250c6c2ca64bf2a620dec15b0fbfb2fd29d16e29fc2d8dc068918d6e023f927.jpg',7),(134,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_06070fdef20e63f1db52e2857fa0fed946d1f5995b91867c7401a6be2d024c76.jpg',7),(133,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/13/listing_c545850786d02388c2a24630ab74483ecf2031a2e79530181e5003fb8b3021f6.jpg',7),(151,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/31/listing_20c278fc71ce65d363eee1e23b8cc8436775bafa0fb3b30a2e47008796d5474a.jpg',8),(152,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/31/listing_efea92065f7b3a2ab5f232d675fbf4a189703f5ca10161388ac169d35127d701.jpg',8),(153,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/31/listing_27bc83a29cd7a2fc7b4bbe71cd07f5f112fd27b38ccad207998e30977745321a.jpg',8),(154,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/31/listing_c422090590c994fa67bb1d53263a0ca6c8e6486d64db838521c888360b6bb0dc.jpg',8),(155,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/31/listing_6c8c7c186d6ab6a5268fc1a136783674f34b31a21775f1b21fc6946231af728f.jpg',8),(159,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/15/listing_2b1227a51e5bfd4bfcea0ba27d58928c5b368f41c05102f82d982fd008e4a13d.jpg',9),(160,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/09/listing_d75cb3e71cdd140f75a8424399740cd9c4bc07c15e6b9eb2a1bccf5faff09a39.jpg',9),(161,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/09/listing_298075b34c9a79e223bd4628915329f782779b6117787124bd0f9834ccc21ac5.jpg',9),(162,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/09/listing_07092503c9a131c425108b908bcc01e63a799b8ad19a4a8358ba2abc3522f537.jpg',9),(163,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/09/listing_8976bc0bfe1701337c45ec14ad1bd51049a1b49968f5dc1ebce3869f9f1f56f2.jpg',9),(164,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/09/listing_6226985a981f97d50f0bac2c10576781364d59b7858988ea054e3f45c7bef68a.jpg',9),(165,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/09/listing_901a5464d02b363a0cb0292dbd1b49fc863f02337e43cbbf24a30a94105d8f6b.jpg',9),(166,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/15/listing_2254da630d7fd0faeddac3ba51519864f6cc994706c04e83914a6b0e4d42a01a.jpg',9),(170,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/06/listing_d099c53a31cf9b68e18d11c2fe0b1732e7c58dcc7b780fd170dde3ccc68fb800.jpg',10),(171,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/06/listing_cbc5f2a27a99c3401746bb5af0e69f36de3da680582608354905e1cc12383418.jpg',10),(172,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/06/listing_f0842bf53a9b0faea7f29594dfbc52df570d13ae887c5d126c2851616db0ffaa.jpg',10),(173,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/06/listing_1bc0699029545dec7607eae8e8eb99c0f8ea4170b209715b58265b1fa738104f.jpg',10),(177,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_c1891be346321a15df1d59547633cbc05e8d673d7be7ae045b5436a0778d8713.jpg',11),(178,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_fd25a323f2a3863aae0362ac27d0db8652d083ccf7b973c5b032e09c9db0e1eb.jpg',11),(179,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_9792dda8f1f8f81cf157d73a105119410b657acd19b96be84b9e1c4b1e8b22a8.jpg',11),(180,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_c2cea9a05fcc5ff3762e3570d3d1a22500e875daa6c3d4a25f263c8c70c5b2fc.jpg',11),(181,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_6bd85ab27e209f77bc6c03feced3cb0f53e457bee1e44b67fe09e19f30b4a76c.jpg',11),(182,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_e1b0653d9d82b2fa0ffb0020b5df735b36c21136e0352db4b06c0dc803cb08be.jpg',11),(183,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_6cdfbaab7318c6334f634f709759a1bbd29e480ad4e5ab914508e6b5b51f68a1.jpg',11),(187,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/04/listing_c221513a6f4e1fef87611dfa7f7b70e1152f64ac392e6eae6b070ffe8d7ecb25.jpg',12),(188,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/04/listing_c2ba5be61f52420732361536ccf28ada8f7ff1a082170b62b437849a55cf5e1a.jpg',12),(189,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/04/listing_f6c177afcc445c0ebc26eb6515806c985e105c3c772e9b52bc7cd6e14f1e532f.jpg',12),(190,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/04/listing_2b4901eed54131e7c5318fbf8f76d6484ded13ebe649c03bb34e256f031332e2.jpg',12),(191,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/04/listing_863c1810178283a0ed7737ec0373ce10b5a5b0f03517b260ce0ac9a47de0c302.jpg',12),(192,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/04/listing_fb6c02694be8b5dc7d56b00975b1c5a13f13ae89b6a0b296f8d5c2b15e418ab4.jpg',12),(196,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_032efcaac1c3195296d85d816e3045ff645c07117291f83bb0c383b5c3a3d92d.jpg',13),(197,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_d08c1b412f388b29e0a1e6a3701936da8f20f35e01ff001b567c94763fa3d790.jpg',13),(198,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_f6132d5f624559bf7af691ae252c37cd9f114f58d4ab183b8e07b4f7c8102523.jpg',13),(199,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_fd4fe7f5e5ec9443d143e405d7a3a3eeee8c104fb107ff1b5e3e5d60909097ad.jpg',13),(200,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_d60fee7ce1186c1eccada322a904d52a29976ca5bdc19d6e27ebcc33b2c601bc.jpg',13),(201,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_4eb4e8b1a7328a8cbb30c40a344dd7439d4e7e89c58168be13ede5af97060683.jpg',13),(202,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_1007e60428e9e4e4e1f421cb2b49ed041a88db0510fc886da3cc1347defc06e7.jpg',13),(203,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_1a92c3265051724a2b2b3c02ce3675a5a8ed1dabb1913c50581c0e060a165f43.jpg',13),(204,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_4a679446b1240ef0d50297e9352615c696cdfcb56ebad3e2685feecc2fedcd22.jpg',13),(205,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_92463a33e6dc810653db79160da9e7eafd558a96b3b27dd4d113006c81d35341.jpg',13),(206,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_f45f3ed450890f5bd5cdd219c8c869bb6a78cd0bec21a73a7103ff13f1f51f58.jpg',13),(207,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_8bc33acadbf6831905f1b3ce5e7193d843c86a62fabea73ba384c68820c4bb5d.jpg',13),(208,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_ae53a89f85c074fe97527f67a0ba560e25458bdf4911e11f231f942e9be9e3de.jpg',13),(209,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_19c9bf4e04525d99a7c8576ab9a5a204c5050d52d888c7cbbcf5c43274d74994.jpg',13),(210,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/03/listing_6e19571f52411ba89619e8f68355dcbd903b96a8c4af351af964845c237f216f.jpg',13),(214,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_67bf280c7524535e9eff607c6b8f97b304863b3baad07d4a2bfcbc77a57c4a40.jpg',14),(215,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_de89b96d01545adfd40fdc91c42b71904962f25e6840fdf2462b3a918e361502.jpg',14),(216,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_e3d6b9cdef9b87ba1c3ac19e733e68a68bd56b2ae57d37641cd4ac183d184151.jpg',14),(217,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_a7c723b66e895b4a2730dd06a1ea3698fff69a84a02fb6cc1f80640057353797.jpg',14),(218,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_9a25fb1428e4b299007cbb754aadd15c4ce7c5e195e14ea27782240a34c9cc52.jpg',14),(219,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_4380322373c55dc8cc5f6e014cb9d91d56abfe1aef845f808b023802efe584fa.jpg',14),(220,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_36560dc8710e88f001d6df03fef06c2ad9123be1de9b28685aee20ff205e8fcf.jpg',14),(221,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_1d0e36d3612ab832b0566e56d910fdaefff8ac8ad8fcc3acf487f504e3ecab8b.jpg',14),(225,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_bd10af8490f32f0d132e86262d86d46f19a5af36bae4bf17f37cb33ead225362.jpg',15),(226,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_3dd01ec0e67ed56f0510f1102c1d8c20eb747dc48a1b2afd7f42cd07bbc5b825.jpg',15),(227,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_b9b514cc560f1108984299ca7a05e0d4b8e59d1d96647f33acb36882b5a34438.jpg',15),(228,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_f43bce8b2952e576afe1b90a1acdd113775ef5c9e1350b3ee615d4fb7633c5da.jpg',15),(229,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_bc5e699bc8778271256b5431cb72e57881c412449d22658f5767ce50ee9f5548.jpg',15),(230,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_4a79eaad688d98730aa7ba91053ca5d6f211b04a73bd291dfc9bd12cd8926ce3.jpg',15),(231,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_c002ccba0d2ba4e773a81c66c8db14d90c392969520d8f469f073cb829a349f7.jpg',15),(232,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_f76e90a610790dd6e4c5868d54c4a6c1c2804ccd00e383543ed476269757204e.jpg',15),(233,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_b0bc10e4ee99ca3fc95fab9cdd0619b2615f8ea663b3c49fc057825b970b6a62.jpg',15),(234,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_c53f4f94212e8c4f3ab3bf86cd0766a82085fbc82b770db0bc4deec0fce2c128.jpg',15),(235,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_267548781e889d24c0a5ed7b6a725be048b2e32a7c5ff7b822db7c6a7de70aba.jpg',15),(236,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_e171389d3d8723f4943aa1fcdf12c2900741940c76d71ce31c0c90ff27bb1339.jpg',15),(237,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_8872369178695c2aa940c4c6f589a0401bcd5f61b1a8aee68bac641e075618f5.jpg',15),(238,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_705945ad113a16dee5d412e6dc1dd6337649ce06049f7b9e79a5e58ba31dbdbb.jpg',15),(239,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/01/listing_c525a2312762438e90452fe49c77d1044e11cb3a95be63469eb219f18728f917.jpg',15),(243,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_f302621410623a53f345a4ac04be7988e7275c479f00060a420b4c47235bf5c3.jpg',16),(244,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_e498ecd45a4fc76907f72ab03818e6bee9b3f3952bee36971033be38ab77ce4d.jpg',16),(245,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_59e2ea5b1c3822fa226efe5dc008f2953cc73cdd2332e6a123f193748173e4f7.jpg',16),(246,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_e90d0ff5e22f80cf9531c88f41931ca2a3bfda2d41d65b2f7cc01ab35c2b83a9.jpg',16),(247,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_9949ae885853245c020195c50afd0538373b22caacc83592321fe0b60fa1deaf.jpg',16),(248,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_bfe1c81e656858adbb8857b363358698d86e78dc5821d1eca52c6ee1aeabdce7.jpg',16),(249,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_9314842d353a4d2daea7591a7a9136d67537eceff7b7a8f13116ef8df547c890.jpg',16),(250,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_3e6f40e03df6f3298dfed906835f8f8213162f74be5b962300220606534524c5.jpg',16),(251,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_1afb0b6931183d45309915613966c688ae3888de11d1f0dcdd12f6d732d29c78.jpg',16),(252,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_258a1fa8c4b88cb0e740e49af6f3c1092384cc1823dd571021edf4007ce9b659.jpg',16),(253,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_66be40230bf49dff168d11393e3785dfc6e0a9a63c217e632882c5d6fc1e88cb.jpg',16),(254,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_9d78b1220c07ef7a2436afc0202a07ed26ba4968aa58a510d3b54350ac65b4f7.jpg',16),(255,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_c07fc9f5c311b6029c659c39b6745959545a1e174fe8ac192fb971bf25ae70e5.jpg',16),(256,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_c4e3c8a50e44fc146e2297971eaca2ac0d3fab4476b9f69a1f276e7cbe2793d2.jpg',16),(257,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_bbd48d7ac489661b7739ba56f08d9bb1f387e4a79b70cfd2b35f0768944912ee.jpg',16),(258,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_ca7c4d5a16a79e94d4a4f8dcafcf43ee497eabdba9a47e51e6d26d900f5a652b.jpg',16),(259,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_4b1b6b160ba6477c1580b977e01a8efdb7511198fe68b9731b906b97b083e117.jpg',16),(260,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_37f5739b776d58833fd8a6e2e3e1d95bd2192fc8faedaec78bb08287397035c5.jpg',16),(261,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_48b35e5d4d508c6167dd055aa9eae36edc19549fa01c48b8be408520fb98225f.jpg',16),(262,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_3d13fe077aacf487377ceac7f7462e44914ab4e0338cc1dfc741694c3765fcf9.jpg',16),(263,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_0fa2874efaf2604e94dc0dc2a8da4f3d58a1a2b1946389cfcd2916d14d39b308.jpg',16),(264,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_bc0099034400b3699c632cc7d9c6137b73a8275b2f5f7bed58606d71fbed1157.jpg',16),(265,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_dba112455903a4c51886cab6e05013576b1de167bb1bb10906a772fd50d84a76.jpg',16),(266,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_7e01cea2b862154402190b04b33def2c1dab813727a387e2d7cd5db070106d7a.jpg',16),(267,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_7952f1ba2319d84fbe4b4887cbb6f486f9447a074d4d097c6ad285d58b935ef2.jpg',16),(268,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_9cf15fab1e915b35dca605756111e89ebc11bc72930df8636008f1de84186c87.jpg',16),(269,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_242b179cf9379f3cad5f4d8cc50bdbd8340c07314a0ac0300c2095bf83d967c2.jpg',16),(270,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/05/listing_ed411319a9a3d6441adce946598454f53803c99220208f02d04dd43bd1f6892c.jpg',16),(271,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/31/listing_d7eb5df0729d627bda4e30f0efa3c7f8e0c71ba4d762fdfb14fe0be62a861871.jpg',16),(288,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/30/listing_8c8a778377eac27c81dcff08e7a04ef925a547638bf244434d11e6e787c74fce.jpg',17),(287,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_0edaa0814fa051a5df6dcdc59491e0856b2d64eb2afc73fba699110b996ff77d.jpg',17),(286,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/30/listing_cb723d7b60e7098337f32b419afca87ee20025a8550f1d2f7c7a7051419cac6c.jpg',17),(285,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_29b2b9758bd2418a2a35da1fbc3670bba873a0a82e1f4a17f8182a8af60d38f1.jpg',17),(284,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_5f00409b682575f68a017c0857c0b4a1abc6e140698e163ff3041e3f85bd453e.jpg',17),(283,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_7c67e54af4e49a4521b4dc19875eebf3aa2828e24b98c98ff3daca4dd2c02998.jpg',17),(282,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_ff71f4d99b43634a10dbced3dd062e5477159093bcc08b493b26dbdeb69876e4.jpg',17),(289,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/30/listing_9706cf7017de139fc61f4547fa4f7bb39731130f68be13e0251f1b0e7a6675b6.jpg',17),(290,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/30/listing_6b56a1977dbd0c9eb2992dbf51ff6bd594cb12b8d1b1a83f70b2bd9479602ffb.jpg',17),(291,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/30/listing_c401235b57e0a7562ad1364916e740c36e9595760bee3c0f8c0adfed370f5672.jpg',17),(292,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/30/listing_688f96af59a057322492ddb68ee3b4cb9459ab0c407aebb9822a9448194d351c.jpg',17),(293,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_946ab1d45538a1e0562e1fc784619c27d6ef73ef2782bc28906054c9956ed187.jpg',17),(294,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_2a2a1ff5daca8fa298fa7f1ef27d93e0f12de55ecb3c7938d5171c3c125ef15f.jpg',17),(295,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_0eab639cd9061a5315af7e0b0c3dc7fc10e7b1bfcd3ccf6f1e4283ffe9abef74.jpg',17),(296,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_33153c0a4839021cc4ee69f5360708468aba7a44ebdd1111beb672f9b87c2d4a.jpg',17),(297,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_8694c1f0cb561c3aa21cae4dd760f94a73a6d721f175b465a98586e624a578e8.jpg',17),(298,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_542fb021bded3d77dcf0bf8b5bfd9c60ebb7ec1ae9d9b247193397b9f22f2bcc.jpg',17),(299,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/11/02/listing_f884db4eb9fdcf1222be0b21b5f5f1f827c75443cd5022ecc65caf544184397c.jpg',17),(303,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_dff2c2a4ca0f97eaf94ab338f1e01669509612f94c9e622e9200979d76159af4.jpg',18),(304,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_17374ad6b932e4fa40e0972a6557e55fd910a72f817c2824a8a19f9bb08d81b6.jpg',8),(305,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_72e4d3f4485a41562eca9ef056d041aec5cc76d5bd9aeb42d2893412161f77b3.jpg',18),(306,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_838b2e990b7770eeb4ec50ebd718a14a927384040019e80246f19d1069732c3b.jpg',18),(307,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_ac1c93c8533888edd6bcb83530696934294fd4e6c85cc498f50c33b5c1a7eac3.jpg',18),(308,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_8702015e7cd88824e9d855c7fbd69badafd2c0006dee79069d1700b7c44eb054.jpg',18),(309,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_cbb9e24cd3f6538b847dbe8af2e1aea2e672e3a58f81973960b97c912129005e.jpg',18),(310,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_24e7c796466cd46537cebeee3ff776ac697a00906ff5141d893ea512e3aa8277.jpg',18),(311,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_37007d7f8053ca25535bc73da10a79554c93684b75fa8b126039f813e9a6cd00.jpg',18),(312,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_62c499846ef437235cf1f525ddc133110fa4876b0e5aecc1f0ef267c60eb3687.jpg',18),(313,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_2c6e247483d51633fd977dcf04442967c1a3adb1456479123088bed70e848dbc.jpg',18),(314,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/29/listing_ed859b50d57efabf752735209e4ad28948425cf93a25430ce715ca43aa42dd16.jpg',18),(318,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/23/listing_957f799f1806fb42c59a897cbf27f57ab7f40a8e4ed581630ab41f85c6729da9.jpg',19),(319,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/23/listing_a654905cd9cfff9c1ec663f3a6f1a932bb2a8170966005d03027f85cc978ba99.jpg',19),(320,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/10/23/listing_f26acb4b67ada4ea1163cfcb0c37d6c3292cc7a12c006915497da58c5f33468f.jpg',19),(324,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/06/listing_a80b8afd7db2720a39f7842957934727e1bd8b8e07c471a0902d02d10037fb20.jpg',20),(325,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/06/listing_af2dd2a6195477876a44ea7e38f6f596fea345e81d8c3f66a7a0511e1a7458a5.jpg',20),(326,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/06/listing_98001c321a86806595c8e968a396464fb18fe3d392e0b63103d639a8550e5df6.jpg',20),(327,NULL,'https://cdn.propzy.vn/listing/thumbnail/grid/2019/12/06/listing_3ed46d6b78ea477d2bb5bdd7edaed001f721740a5209d51a4437d5a0743e6148.jpg',20);
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
  `tradeStatus` smallint(6) DEFAULT NULL,
  `viewCount` bigint(20) DEFAULT NULL,
  `addressId` bigint(20) DEFAULT NULL,
  `coordinateId` bigint(20) DEFAULT NULL,
  `detailsId` bigint(20) DEFAULT NULL,
  `realEstateKindId` bigint(20) DEFAULT NULL,
  `tradeKindId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq3anaqws3f17pky65jkx1il75` (`addressId`),
  KEY `FKpepp1nyu7djyad4ncuih8nep` (`coordinateId`),
  KEY `FKlpws3d4em5htg1c42jejl2ium` (`detailsId`),
  KEY `FK2jy9tpeixc0vrvv7y68ynyuyp` (`realEstateKindId`),
  KEY `FKfbw184hpw3cco4kcqi5bmdfgo` (`tradeKindId`),
  KEY `FKd986y1i64s1k88weqamt2p6t5` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (1,40000000000,'Nhà mặt tiền quận 1 thuận tiện kinh doanh',1,0,1,1,1,1,1,1),(2,53000000000,'Nhà quận 1 rộng rãi thoáng mát',1,0,2,2,2,2,1,2),(3,3800000000,'Bán nhà hẻm xe hơi phường Hiệp Bình Phước, Quận Thủ Đức, hướng Tây',1,0,3,3,3,2,2,1),(4,5100000000,'Bán nhà hẻm xe hơi phường Hiệp Bình Phước, Quận Thủ Đức, hướng Tây',1,0,4,4,4,1,2,2),(5,3300000000,'Bán nhà riêng quận 5 đang cho thuê 6tr/tháng',1,0,5,5,5,3,2,1),(6,3700000000,'Bán nhà riêng quận 5',1,0,6,6,6,3,1,2),(7,2400000000,'Bán nhà riêng huyện Hóc Môn- Hẻm bê tông rộng 2.5m',0,0,7,7,7,3,1,1),(8,2300000000,'Nhà hẻm phường 14 Quận 8  - Gần chợ Bình Đông',0,0,8,8,8,3,2,2),(9,2700000000,'Bán nhà riêng hẻm nhựa 6m, thông thoáng, cách mặt tiền khoảng 50m Quận 12',0,0,9,9,9,3,1,1),(10,2100000000,'Bán nhà hẻm an ninh P. Bình Trị Đông, Bình Tân - Đang cho thuê 4.5 triệu/tháng',0,0,10,10,10,3,1,2),(11,2700000000,'Nhà bán Quận Gò Vấp, - Thuận tiện di chuyển, khu dân cư an ninh.',0,0,11,11,11,3,1,1),(12,2600000000,'Bán nhà Quận Tân Phú - Khu dân cư an ninh.',0,0,12,12,12,3,1,2),(13,2700000000,'Nhà 1 trệt, 1 lửng, hẻm an ninh P. Bình Hưng Hòa A, Bình Tân - Gần Chợ Bình Long',0,0,13,13,13,3,2,2),(14,15500000000,'Nhà nhỏ xinh sổ hồng riêng, nội thất đẹp phường 12 quận 3.',0,0,14,14,14,3,2,1),(15,26500000000,'Chính chủ bán gấp nhà cấp 4 hẻm xe máy, đường số 12, phường Tam Bình, Quận Thủ Đức',0,0,15,15,15,3,2,2),(16,23500000000,'Nhà HXH gần 5m, P. Tân Tạo A, Bình Tân - Gần vòng xoay An Lạc',0,0,16,16,16,2,2,2),(17,2100000000,'Bán nhà riêng huyện Hóc Môn- Hẻm xe hơi',0,0,17,17,17,3,1,1),(18,300000000,'Bán gấp nhà hẻm phường Phước Long A, Quận 9 - Sổ hồng riêng chính chủ',0,0,18,18,18,3,1,2),(19,2400000000,'Bán nhà 2 mặt tiền Quận Tân Phú - Khu dân cư an ninh, yên tĩnh.',0,0,19,19,19,3,1,1),(20,300000000,'Nhà đẹp phường 14 Quận 8 - Nhà cũ tiện xây mới',0,0,20,20,20,3,2,1);
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
  `active` bit(1) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` bit(1) NOT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `provider` varchar(255) NOT NULL,
  `providerId` varchar(255) DEFAULT NULL,
  `jobId` bigint(20) DEFAULT NULL,
  `userKindId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK323s8f8mb3rgqx6p7tdbm1r0j` (`jobId`),
  KEY `FKr3ev5cp30260s7n0eni85a8q3` (`userKindId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,_binary '\0','1997-06-05 00:00:00','danhthanh0605@gmail.com',_binary '\0',NULL,'Danh Thanh','','0123456789','google','2',31,2),(1,_binary '\0',NULL,'henrynguyen02081997@gmail.com',_binary '\0',NULL,'Nguyễn Thị Ngọc Huyền',NULL,NULL,'facebook','1',NULL,1);
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
  KEY `FKj1n131kbq3wgkdtpexsf3g00g` (`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(7,1),(8,2),(9,2),(12,2),(34,2),(37,2),(40,2),(43,2),(47,2),(50,2),(86,2),(91,2),(94,2),(99,2),(102,2),(104,2),(107,2),(115,2),(116,2),(117,2),(120,2),(127,2),(128,2),(129,2),(130,2),(132,2),(134,2),(135,2),(136,2),(137,2),(138,2),(140,2);
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

-- Dump completed on 2020-01-06 14:38:58
