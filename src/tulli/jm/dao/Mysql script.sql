CREATE DATABASE `jsp1`;
USE jsp;

 CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `userfile` longtext,
  PRIMARY KEY (`userId`)
);

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

 CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` double NOT NULL,
  `categoryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category` (`categoryid`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`)
);
