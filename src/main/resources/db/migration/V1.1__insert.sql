
insert into roles(name) values ('ADMIN');

INSERT INTO `users` ( `first_name`, `last_name`, `email`, `password`) VALUES
( 'Miljana', 'Stamenkovic', 'miljana@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW'),
( 'Petar', 'Petrovic', 'petar@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW'),
( 'Mladen', 'Petrovic', 'mladen@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW');
INSERT INTO `users_roles`(`role_id`, `user_id`) VALUES (1,1), (1,2),(1,3);

INSERT INTO `restaurants` ( `name`, `address`, `phone_number`, `menu_url`,`email`,`delivery_time`, `additional_charges`, `delivery_info`) VALUES
('Mesecev konak', 'Rentgenova', '018/411232', 'munchies.pdf', 'miljanass@gmail.com', '30 min', '300 din', '....'),
('Cardak', 'Ustanicka', '018/231215', 'deletefile.png','miljanass@gmail.com', '40 min', '400 din', '....'),
('Kafana princ', 'Sremska', '018/564632', 'munchies(1).pdf','miljanass@gmail.com', '50 min', '400 din', '...');



