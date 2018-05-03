CREATE DATABASE  IF NOT EXISTS `biudzetas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `biudzetas`;

DROP TABLE IF EXISTS `pajamos`;
CREATE TABLE `pajamos` (
  `pajamu_ID` int(11) NOT NULL AUTO_INCREMENT,
  `suma` double NOT NULL,
  `kategorija` varchar(30) NOT NULL,
  `pajamu_data` date NOT NULL,
  `komentaras` mediumtext,
  PRIMARY KEY (`pajamu_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

LOCK TABLES `pajamos` WRITE;
INSERT INTO `pajamos` VALUES (15,224.2,'Atlyginimas','2017-12-03',''),(16,25,'Kitos pajamos','2017-12-01','loterijos laimėjimas'),(17,222,'Atlyginimas','2017-11-05','');
UNLOCK TABLES;

