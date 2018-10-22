


drop table users_role;

CREATE TABLE if not exists `users_role` (
  `roles_id` int(200) DEFAULT '1',
  `users_id` bigint(200),
  foreign key(roles_id) references roles(roles_id),
  foreign key(users_id) references users(users_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into users_role( roles_id, users_id) values(1,2);

INSERT INTO `restaurant` ( `name`, `address`, `phone_number`, `menu_url`, `delivery_time`, `additional_charges`, `delivery_info`) VALUES
('Mesecev konak', 'Rentgenova', '6465411232', 'meniurl.com', '30 min', '300 din', '....'),
('Cardak', 'Ustanicka', '231215', 'meniurl', '40 min', '400 din', '....'),
('Kafana princ', 'Sremska', '564632', 'urlmenija.com', '50 min', '400 din', '...');

