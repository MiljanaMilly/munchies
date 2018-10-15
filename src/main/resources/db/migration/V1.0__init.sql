-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 12, 2018 at 10:21 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


CREATE TABLE if not exists `roles` (
  `roles_id` int(100) primary key auto_increment NOT NULL,
  `role_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE if not exists `users` (
  `users_id` bigint(200) primary key auto_increment NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` binary(64) NOT NULL,
   roles_id int(100) NOT NULL,
   foreign key (roles_id) references roles(roles_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `users_role` (
  `id` int(11) primary key auto_increment NOT NULL,
  `roles_id` int(11) DEFAULT NULL,
  `users_id` bigint(200) DEFAULT NULL,
  foreign key(roles_id) references roles(roles_id),
  foreign key(users_id) references users(users_id)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `orders` (
  `order_id` bigint(20) primary key auto_increment NOT NULL,
  `order_creator` varchar(50) NOT NULL,
  `order_item` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE if not exists `group_order` (
  `group_order_id` bigint(20) primary key auto_increment NOT NULL,
  `creator` varchar(50) NOT NULL,
  `order_timeout` int(20) DEFAULT '10',
  `order_url` varchar(500),
  order_id bigint not null,
  foreign key (order_id) references orders(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE if not exists `restaurant` (
  `restaurant_id` bigint(20) primary key auto_increment NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `menu_url` varchar(500) NOT NULL,
  `group_order_id` bigint(20) NOT NULL,
  foreign key (group_order_id) references group_order(group_order_id),
  `delivery_time` varchar(50) DEFAULT NULL,
  `additional_charges` varchar(100) DEFAULT NULL,
  `delivery_info` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2018-10-11 14:14:13', 0, 1);



ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);


