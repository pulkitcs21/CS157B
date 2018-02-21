# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: cs157B_proj2
# Generation Time: 2018-02-19 05:57:42 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table brand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `brand_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table card_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `card_type`;

CREATE TABLE `card_type` (
  `card_type_id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `card_type_name` char(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`card_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `card_type` WRITE;
/*!40000 ALTER TABLE `card_type` DISABLE KEYS */;

INSERT INTO `card_type` (`card_type_id`, `card_type_name`)
VALUES
	(1,'Visa'),
	(2,'Master');

/*!40000 ALTER TABLE `card_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table credit_debit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `credit_debit`;

CREATE TABLE `credit_debit` (
  `payment_id` int(11) unsigned NOT NULL,
  `name_on_card` varchar(25) NOT NULL DEFAULT '',
  `card_number` varchar(20) NOT NULL DEFAULT '',
  `exp_date` char(5) NOT NULL DEFAULT '',
  `cvv` char(4) NOT NULL DEFAULT '',
  `card_type_id` int(1) unsigned NOT NULL,
  KEY `card_type_id` (`card_type_id`),
  CONSTRAINT `card_type_id` FOREIGN KEY (`card_type_id`) REFERENCES `card_type` (`card_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_status` varchar(20) NOT NULL DEFAULT '',
  `order_price` float NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id_order` (`user_id`),
  CONSTRAINT `user_id_order` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table payment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table paypal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `paypal`;

CREATE TABLE `paypal` (
  `paypal_acc` varchar(254) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` int(11) unsigned NOT NULL,
  `brand_id` int(11) unsigned NOT NULL,
  `product_name` varchar(100) NOT NULL DEFAULT '',
  `product_price` float NOT NULL,
  `product_description` text,
  `stock_amount` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`),
  KEY `type_id` (`type_id`),
  KEY `brand_id` (`brand_id`),
  CONSTRAINT `brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table product_images
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_images`;

CREATE TABLE `product_images` (
  `product_id` int(11) unsigned NOT NULL,
  `product_image_link` text NOT NULL,
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table shopping_cart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shopping_cart`;

CREATE TABLE `shopping_cart` (
  `order_id` int(11) unsigned NOT NULL,
  `product_id` int(11) unsigned NOT NULL,
  `amount` int(11) NOT NULL,
  KEY `order_id_shopping_cart` (`order_id`),
  KEY `product_id_shopping_cart` (`product_id`),
  CONSTRAINT `order_id_shopping_cart` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_id_shopping_cart` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(16) NOT NULL DEFAULT '',
  `password` char(16) NOT NULL DEFAULT '',
  `email` varchar(254) NOT NULL DEFAULT '',
  `DOB` date NOT NULL,
  `address` varchar(200) NOT NULL DEFAULT '',
  `deliveryAddress` varchar(200) DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
