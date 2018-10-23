

insert into roles(name) values ('ADMIN');


INSERT INTO `users` ( `first_name`, `last_name`, `email`, `password`, `role_id`) VALUES
( 'Miljana', 'Stamenkovic', 'miljana@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW', 1),
( 'Petar', 'Petrovic', 'petar@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW', 1),
( 'Mladen', 'Petrovic', 'mladen@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW', 1);

INSERT INTO `restaurants` ( `name`, `address`, `phone_number`, `menu_url`, `delivery_time`, `additional_charges`, `delivery_info`) VALUES
('Mesecev konak', 'Rentgenova', '018/411232', 'mesecevkonak.com/meni', '30 min', '300 din', '....'),
('Cardak', 'Ustanicka', '018/231215', 'cardak.com/meni', '40 min', '400 din', '....'),
('Kafana princ', 'Sremska', '018/564632', 'kafanaprinc.com/meni', '50 min', '400 din', '...');



