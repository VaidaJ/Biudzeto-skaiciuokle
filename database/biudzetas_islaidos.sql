CREATE DATABASE  IF NOT EXISTS `biudzetas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `biudzetas`;

DROP TABLE IF EXISTS `islaidos`;
CREATE TABLE `islaidos` (
  `islaidu_ID` int(11) NOT NULL AUTO_INCREMENT,
  `suma` double NOT NULL,
  `kategorija` varchar(30) NOT NULL,
  `islaidu_data` date NOT NULL,
  `komentaras` mediumtext,
  PRIMARY KEY (`islaidu_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

LOCK TABLES `islaidos` WRITE;
INSERT INTO `islaidos` VALUES (25,5.49,'Mokesčiai','2017-12-01',''),(26,5.55,'Ryšiai ','2017-12-05','Sąskaita už telefona '),(28,60.89,'Apranga','2017-11-17',''),(29,90.99,'Laisvalaikis ','2017-11-24','Kelionė'),(30,10.6,'Maistas, gėrimai ','2017-12-11',''),(32,5.8,'Transportas','2017-12-05','mėnesinis bilietas'),(33,36,'Grožis','2017-12-01',''),(34,20.36,'Mokesčiai','2017-12-01',''),(35,5.36,'Sveikata','2017-12-02','vaistai'),(38,25.26,'Mokesčiai','2017-12-06','');
UNLOCK TABLES;

