/*
SQLyog - Free MySQL GUI v5.02
Host - 5.0.19-nt : Database - prac2
*********************************************************************
Server version : 5.0.19-nt
*/


create database if not exists `prac2`;

USE `prac2`;

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `rollno` int(2) default NULL,
  `name` varchar(20) default NULL,
  `class` varchar(2) default NULL,
  `marks` int(3) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
