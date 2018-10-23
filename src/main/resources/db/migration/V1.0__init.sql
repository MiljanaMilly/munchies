
CREATE TABLE if not exists `roles` (
  `id` int(100) primary key auto_increment NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `users` (
  `id` bigint(200) primary key auto_increment NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text(100) NOT NULL,
   role_id int(100) NOT NULL,
   foreign key (role_id) references roles(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `users_roles` (
  `role_id` int(100) DEFAULT NULL,
  `user_id` bigint(200) DEFAULT NULL,
  foreign key(role_id) references roles(id),
  foreign key(user_id) references users(id)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `order_items` (
  `id` bigint(200) primary key auto_increment NOT NULL,
  `creator` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
   price double(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `restaurants` (
  `id` bigint(200) primary key auto_increment NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `menu_url` varchar(500) NOT NULL,
  `delivery_time` varchar(50) DEFAULT NULL,
  `additional_charges` varchar(100) DEFAULT NULL,
  `delivery_info` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE if not exists `orders` (
  `id` bigint(200) primary key auto_increment NOT NULL,
  `creator` varchar(50) NOT NULL,
  `order_timeout` datetime NOT NULL,
  `order_url` varchar(500),
  order_item_id bigint,
  foreign key (order_item_id) references order_items(id),
  `restaurant_id` bigint(200),
  foreign key (restaurant_id) references restaurants(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;







