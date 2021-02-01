/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 10.4.14-MariaDB : Database - fawnder
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fawnder` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `fawnder`;

/*Table structure for table `fawn_pet` */

DROP TABLE IF EXISTS `fawn_pet`;

CREATE TABLE `fawn_pet` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_user` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `breeds` varchar(45) DEFAULT NULL,
  `photo` varchar(350) DEFAULT NULL,
  `visibility` tinyint(4) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `medicalHistory` varchar(2500) DEFAULT NULL,
  `familyTree` varchar(45) DEFAULT NULL,
  `certification` varchar(350) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fawnpet_to_fawnuser_idx` (`ID_user`),
  CONSTRAINT `fawnpet_to_fawnuser` FOREIGN KEY (`ID_user`) REFERENCES `fawn_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `fawn_pet` */

insert  into `fawn_pet`(`ID`,`ID_user`,`name`,`age`,`gender`,`breeds`,`photo`,`visibility`,`type`,`medicalHistory`,`familyTree`,`certification`) values 
(1,1,'nobu',19,'female','akita','17',1,'watchdog','kelainan sendi lutut','keturunan champion','-'),
(2,3,'shou',10,'male','chihuahua','18',1,'watchdog','pernah diabetes','keturunan champion','-'),
(19,2,'pim',15,'female','bulldog','19',1,'familydog','sangat sehat','-','-'),
(20,1,'jaiko',14,'male','bulldog','20',1,'housedog','tidak ada','keturunan biasa','belum ada'),
(21,3,'noi',17,'female','poodle','21',1,'familydog','pernah cacingan','-','-');

/*Table structure for table `fawn_pet_notification` */

DROP TABLE IF EXISTS `fawn_pet_notification`;

CREATE TABLE `fawn_pet_notification` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_petSender` int(11) DEFAULT NULL,
  `ID_petReceiver` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `petnotification_to_petsender_idx` (`ID_petSender`),
  KEY `petnotification_to_petreceiver_idx` (`ID_petReceiver`),
  CONSTRAINT `petnotification_to_petreceiver` FOREIGN KEY (`ID_petReceiver`) REFERENCES `fawn_pet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `petnotification_to_petsender` FOREIGN KEY (`ID_petSender`) REFERENCES `fawn_pet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `fawn_pet_notification` */

insert  into `fawn_pet_notification`(`ID`,`ID_petSender`,`ID_petReceiver`,`status`) values 
(38,1,2,'accepted'),
(40,2,1,'declined'),
(41,19,1,'accepted'),
(44,1,19,'declined'),
(45,1,21,'waitingApproval'),
(46,2,20,'accepted'),
(47,21,20,'waitingApproval');

/*Table structure for table `fawn_user` */

DROP TABLE IF EXISTS `fawn_user`;

CREATE TABLE `fawn_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `loginUsername` varchar(20) DEFAULT NULL,
  `loginPassword` varchar(12) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(75) DEFAULT NULL,
  `location` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `fawn_user` */

insert  into `fawn_user`(`ID`,`loginUsername`,`loginPassword`,`name`,`contact`,`location`) values 
(1,'iniharyo','1773031','Haryo','082127626977','Cimahi'),
(2,'glenpunya','asd','Glenn','087634372946','Bandung'),
(3,'vr','vr','Vira','123','bumi'),
(10,'soke','asd','Esok','087766543322','Denpasar'),
(13,'test','123','test','123','bumi');

/*Table structure for table `forum_posting` */

DROP TABLE IF EXISTS `forum_posting`;

CREATE TABLE `forum_posting` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_user` int(11) DEFAULT NULL,
  `ID_topic` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `content` varchar(2500) DEFAULT NULL,
  `hasEdit` tinyint(4) DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `forumposting_to_user_idx` (`ID_user`),
  KEY `forumposting_to_forumtopic_idx` (`ID_topic`),
  CONSTRAINT `forumposting_to_forumtopic` FOREIGN KEY (`ID_topic`) REFERENCES `forum_topic` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forumposting_to_user` FOREIGN KEY (`ID_user`) REFERENCES `fawn_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `forum_posting` */

insert  into `forum_posting`(`ID`,`ID_user`,`ID_topic`,`date`,`subject`,`content`,`hasEdit`,`editDate`) values 
(1,1,2,'2021-01-20 17:18:16','My dog has rabies','What should i do?',0,NULL),
(2,1,5,'2021-01-20 18:55:17','My dog is in breeding season','Please help me',0,NULL),
(3,2,1,'2021-01-20 18:57:35','He is lazy','i wanna kick my dog',1,'2021-01-22 16:48:56'),
(7,3,1,'2021-01-22 17:17:06','Cuma test','hai guys....',1,'2021-01-22 20:10:58'),
(8,3,3,'2021-01-22 20:12:43','hey','hila',0,NULL);

/*Table structure for table `forum_posting_comment` */

DROP TABLE IF EXISTS `forum_posting_comment`;

CREATE TABLE `forum_posting_comment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_user` int(11) DEFAULT NULL,
  `ID_posting` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `content` varchar(2500) DEFAULT NULL,
  `hasEdit` tinyint(4) DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `forumpostingcomment_to_user_idx` (`ID_user`),
  KEY `forumpostingcomment_to_posting_idx` (`ID_posting`),
  CONSTRAINT `forumpostingcomment_to_posting` FOREIGN KEY (`ID_posting`) REFERENCES `forum_posting` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forumpostingcomment_to_user` FOREIGN KEY (`ID_user`) REFERENCES `fawn_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `forum_posting_comment` */

insert  into `forum_posting_comment`(`ID`,`ID_user`,`ID_posting`,`date`,`content`,`hasEdit`,`editDate`) values 
(3,3,1,'2021-01-20 18:48:12','good',0,NULL),
(4,1,1,'2021-01-20 18:48:23','nice',0,NULL),
(5,2,1,'2021-01-20 18:48:52','wow',0,NULL),
(6,2,2,'2021-01-20 18:56:47','cool',0,NULL),
(7,1,2,'2021-01-20 18:57:04','hey',0,NULL),
(8,1,3,'2021-01-20 18:58:40','mantap',0,NULL),
(9,3,3,'2021-01-20 21:49:21','test',0,NULL),
(13,3,3,'2021-01-22 20:06:18','i can\'t',0,NULL),
(14,1,7,'2021-01-22 20:07:40','oh gitu',0,NULL);

/*Table structure for table `forum_topic` */

DROP TABLE IF EXISTS `forum_topic`;

CREATE TABLE `forum_topic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `forum_topic` */

insert  into `forum_topic`(`ID`,`name`) values 
(1,'Other'),
(2,'Dog Health and Nutrition'),
(3,'Dog Services'),
(4,'Dog Training and Behaviour'),
(5,'Dog Breeding'),
(6,'Dog Rescue and Adoption');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
