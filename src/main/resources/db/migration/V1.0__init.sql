


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


CREATE TABLE if not exists `restaurant` (
  `restaurant_id` bigint(20) primary key auto_increment NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `menu_url` varchar(500) NOT NULL,
  `delivery_time` varchar(50) DEFAULT NULL,
  `additional_charges` varchar(100) DEFAULT NULL,
  `delivery_info` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE if not exists `group_order` (
  `group_order_id` bigint(20) primary key auto_increment NOT NULL,
  `creator` varchar(50) NOT NULL,
  `order_timeout` int(20) DEFAULT '10',
  `order_url` varchar(500),
  order_id bigint,
  foreign key (order_id) references orders(order_id),
  `restaurant_id` bigint(20),
  foreign key (restaurant_id) references restaurant(restaurant_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;





