/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.0.22-community : Database - tasks
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`tasks` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tasks`;

/*Table structure for table `emailconfig` */

DROP TABLE IF EXISTS `emailconfig`;

CREATE TABLE `emailconfig` (
  `id` int(11) NOT NULL auto_increment,
  `dailyTime` varchar(200) default NULL,
  `noticeTime` varchar(200) default NULL,
  `host` varchar(50) default NULL,
  `port` int(11) default NULL,
  `userName` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `startTls` tinyint(1) default NULL,
  `auth` tinyint(1) default NULL,
  `debug` tinyint(1) default NULL,
  `dailyTo` varchar(500) default NULL,
  `dailyCc` varchar(500) default NULL,
  `dailyBcc` varchar(500) default NULL,
  `dailySubject` varchar(500) default NULL,
  `monthlyTo` varchar(500) default NULL,
  `monthlyCc` varchar(500) default NULL,
  `monthlyBcc` varchar(500) default NULL,
  `monthlySubject` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emailconfig` */

insert  into `emailconfig`(`id`,`dailyTime`,`noticeTime`,`host`,`port`,`userName`,`password`,`startTls`,`auth`,`debug`,`dailyTo`,`dailyCc`,`dailyBcc`,`dailySubject`,`monthlyTo`,`monthlyCc`,`monthlyBcc`,`monthlySubject`) values (1,'0 3 17 ? * MON-FRI','0 5 17 ? * MON-FRI','smtp.163.com',25,'xgtxxxx@163.com','xgtxxxx240720',0,1,0,'343445708@qq.com','','','Daily Report','343445708@qq.com','','','Monthly Report');

/*Table structure for table `emaillog` */

DROP TABLE IF EXISTS `emaillog`;

CREATE TABLE `emaillog` (
  `id` int(11) NOT NULL auto_increment,
  `sendTime` varchar(30) default NULL,
  `emailType` int(11) default NULL,
  `sendType` int(11) default NULL,
  `sender` varchar(200) default NULL,
  `email` varchar(200) default NULL,
  `groupType` int(11) default NULL,
  `groupId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emaillog` */

insert  into `emaillog`(`id`,`sendTime`,`emailType`,`sendType`,`sender`,`email`,`groupType`,`groupId`) values (1,'2015-01-05 13:48:11',1,2,'ShiHai','343445708@qq.com',2,1),(2,'2015-01-06 14:01:40',1,2,'XiGangTao','343445708@qq.com',2,1),(3,'2015-01-06 15:33:22',1,2,'XiGangTao','343445708@qq.com',2,1),(4,'2015-01-06 17:40:45',1,2,'XiGangTao','343445708@qq.com',2,1),(5,'2015-01-06 17:51:48',1,2,'XiGangTao','343445708@qq.com',2,1),(12,'2015-01-08 18:22:54',1,2,'XiGangTao','343445708@qq.com',2,1),(13,'2015-01-08 18:23:25',1,2,'XiGangTao','343445708@qq.com',1,1),(14,'2015-01-12 17:55:02',1,1,'SYSTEM','xgtxxxx@163.com',3,NULL),(17,'2015-01-13 17:19:50',1,2,'XiGangTao','343445708@qq.com',2,1),(18,'2015-01-13 17:39:27',1,2,'XiGangTao','343445708@qq.com',1,1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (1,'teamleader'),(2,'developer'),(3,'admin');

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `id` int(11) NOT NULL auto_increment,
  `description` varchar(50) NOT NULL,
  `font_color` varchar(200) default NULL,
  `bg_color` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `status` */

insert  into `status`(`id`,`description`,`font_color`,`bg_color`) values (1,'Resolved','#FFFFFF','green'),(2,'Pending on code review','black','pink'),(3,'In Progress','white','blue'),(4,'On Hold','#000000','#FFFF99');

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) NOT NULL auto_increment,
  `task_id` varchar(60) default '',
  `description` varchar(5000) default '',
  `priority` int(4) default NULL,
  `team` int(11) default NULL,
  `team_name` varchar(200) default NULL,
  `status` int(1) default NULL,
  `assign_date` varchar(30) default NULL,
  `fixed_date` varchar(30) default NULL,
  `owner` int(11) default NULL,
  `owner_name` varchar(100) default NULL,
  `comments` varchar(5000) default NULL,
  `questions` varchar(5000) default NULL,
  `submit_time` datetime default NULL,
  `last_update_time` datetime default NULL,
  `enabled` bit(1) default '',
  `releaseVersion` varchar(500) default NULL,
  `progress` decimal(11,2) default NULL,
  `comments_questions` varchar(2000) default NULL,
  `pending_issues` varchar(1000) default NULL,
  `feedback` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task` */

insert  into `task`(`id`,`task_id`,`description`,`priority`,`team`,`team_name`,`status`,`assign_date`,`fixed_date`,`owner`,`owner_name`,`comments`,`questions`,`submit_time`,`last_update_time`,`enabled`,`releaseVersion`,`progress`,`comments_questions`,`pending_issues`,`feedback`) values (1,'DTAD','dsaf',NULL,1,'team01',1,'2015-01-01','2015-01-02',1,'ShiHai',NULL,NULL,'2015-01-05 13:10:54','2015-01-05 13:10:54','','wred','43.00','fdsafaf','fdsafd',''),(2,'AI-1088','It\'s one world,It\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world',NULL,1,'team01',3,'2015-01-01','',1,'ShiHai',NULL,NULL,'2015-01-05 14:13:55','2015-01-05 14:13:55','','2015 V01','60.00','It\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nKishore','',''),(3,'FDSF','dsfsf',NULL,1,'team01',1,'2015-01-01','',1,'XiGangTao',NULL,NULL,'2015-01-06 14:39:53','2015-01-06 14:39:53','','refds','0.00','dfsfd','fdsf',''),(4,'TEST ID','test description',NULL,1,'team01',2,'2015-01-06','',11,'XiGangTao',NULL,NULL,'2015-01-06 16:08:14','2015-01-06 16:08:14','','test version','50.00','test comments and questions','test pending issues',''),(5,'DSFD','fdsafdasf',NULL,1,'team01',2,'2015-01-07','',1,'XiGangTao',NULL,NULL,'2015-01-08 10:41:39','2015-01-08 10:41:39','','dfsa','100.00','dafdsaf','dasfdsafd',''),(6,'FDFA','fdsafda',NULL,1,'team01',1,'2015-01-01','2015-01-08',11,'XiGangTao1',NULL,NULL,'2015-01-08 16:08:01','2015-01-08 16:08:01','','fdsafda','100.00','fdas','fdasf',''),(7,'FDSA','fdsafds',NULL,1,'team01',2,'2015-01-08','',11,'XiGangTao1',NULL,NULL,'2015-01-08 16:08:13','2015-01-08 16:08:13','','fdsafda','100.00','afdasfd','afdsafdsa',''),(8,'DFAFD','fdsafd',NULL,1,'team01',1,'2015-01-01','2015-01-08',12,'xxx',NULL,NULL,'2015-01-08 16:09:16','2015-01-08 16:09:16','','fdasfda','100.00','safdas','fdasfdas',''),(9,'FDSAFDA','fdsaf',NULL,1,'team01',2,'2015-01-01','',12,'xxx',NULL,NULL,'2015-01-08 16:09:28','2015-01-08 16:09:28','','fdsaf','100.00','dasfdas','fdasfda',''),(10,'AI-1088','It\'s one world,It\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world',NULL,1,'team01',3,'2015-01-01','',1,'XiGangTao',NULL,NULL,'2015-01-08 16:34:50','2015-01-08 16:34:50','\0','2015 V01','0.00','It\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nKishore','',''),(11,'AI-1088','It\'s one world,It\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world',NULL,1,'team01',3,'2015-01-01','',1,'XiGangTao',NULL,NULL,'2015-01-08 16:35:12','2015-01-08 16:35:27','','2015 V01','62.00','It\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nKishore','',''),(12,'DTAD','dsaf',NULL,1,'team01',1,'2015-01-01','2015-01-02',1,'XiGangTao',NULL,NULL,'2015-01-08 18:22:33','2015-01-08 18:22:33','','wred','43.00','fdsafaf','fdsafd',''),(13,'AI-1088','It\'s one world,It\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one worldIt\'s one worldIt\'s one world',NULL,1,'team01',3,'2015-01-01','',1,'XiGangTao',NULL,NULL,'2015-01-09 09:07:40','2015-01-09 09:07:40','','2015 V01','60.00','It\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nIt\'s one worldIt\'s one world\nKishore','',''),(14,'FDSAFD','fsadf',NULL,1,'team01',1,'2015-01-08','2015-01-09',1,'XiGangTao',NULL,NULL,'2015-01-09 15:15:51','2015-01-09 15:15:51','','test','100.00','safdasf','dsafdsaf',''),(15,'DFA','fdsaf',NULL,1,'team01',2,'2015-01-08','',1,'XiGangTao',NULL,NULL,'2015-01-09 15:17:43','2015-01-09 15:17:43','','test11','100.00','dsafdsa','fdasfd',''),(16,'TEST','ffdaf',NULL,1,'team01',2,'2015-01-01','',11,'XiGangTao1',NULL,NULL,'2015-01-12 11:21:37','2015-01-12 11:21:37','','test111','100.00','dfds','fsadfsaf',''),(17,'FDASFD','fdaf',NULL,1,'team01',2,'2015-01-01','',11,'XiGangTao1',NULL,NULL,'2015-01-12 13:32:58','2015-01-12 13:32:58','','test','100.00','dfsaf','dafdsaf',''),(18,'TEST','fdsafda',NULL,1,'team01',1,'2015-01-06','2015-01-13',1,'XiGangTao',NULL,NULL,'2015-01-13 16:06:33','2015-01-13 16:17:09','','Test111','100.00','fdsafdsa','fdsafda',''),(19,'TEST','fdsaf',NULL,1,'team01',1,'2015-01-07','2015-01-13',1,'XiGangTao',NULL,NULL,'2015-01-13 16:18:18','2015-01-13 16:18:18','','Test Test','100.00','fdsaf','dsafda',''),(20,'TEST1','fsafsdsadsda',NULL,1,'team01',2,'2015-01-08','2015-01-06',1,'XiGangTao',NULL,NULL,'2015-01-13 16:18:51','2015-01-13 16:23:55','','Test Test','100.00','dsafdsa','fdsafdas','');

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `teamleader` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `team` */

insert  into `team`(`id`,`name`,`teamleader`) values (1,'team01',1),(2,'team002',2),(3,'11112211',2),(12,'222',2);

/*Table structure for table `team_user` */

DROP TABLE IF EXISTS `team_user`;

CREATE TABLE `team_user` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `team_user` */

insert  into `team_user`(`id`,`user_id`,`team_id`) values (9,1,1),(10,1,2),(11,11,2),(12,11,1),(13,12,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `account` varchar(500) NOT NULL default '',
  `password` varchar(50) NOT NULL,
  `locked` bit(1) NOT NULL,
  `expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `username` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`account`,`password`,`locked`,`expired`,`enabled`,`username`) values (1,'343445708@qq.com','68cbdf67a444a5a100ebba7e685a124c','','','','XiGangTao'),(11,'gangtao.xi@symbio.com','574dd1be5fa6fb550c316976cbf9416d','','','','XiGangTao1'),(12,'2307251011@qq.com','1e8985274576332f1f7ae06446b6b848','','','','xxxww');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (2,1,1),(3,1,2),(9,1,3),(15,11,2),(16,12,2);

/*Table structure for table `version` */

DROP TABLE IF EXISTS `version`;

CREATE TABLE `version` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(500) default NULL,
  `active` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `version` */

insert  into `version`(`id`,`name`,`active`) values (5,'test',1),(6,'testeees3',1),(7,'test',1),(8,'test',1),(9,'test111',1),(10,'Test1111',1),(11,'Test Test',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
