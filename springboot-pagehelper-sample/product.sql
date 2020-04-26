# Host: localhost  (Version 5.7.21-log)
# Date: 2020-04-24 00:00:40
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `discount` varchar(10) DEFAULT NULL,
  `price` double(10,0) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (1,'零食','卫龙辣条','8折',10),(2,'洗发水','霸王','8折',69),(3,'手机','小米10','',3499),(4,'手机','小米10 Pro','',4999),(5,'手机','华为P40','',4499),(6,'男装','T恤','',59),(7,'男装','休闲裤','',89),(8,'男装','牛仔裤','',99),(9,'数码','游戏主机','',5999),(10,'数码','数码相机','',5999),(11,'办公','打印机','',2999),(12,'办公','投影机','',1999),(13,'办公','会议白板','',999);
