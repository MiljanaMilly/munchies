alter table users drop column password;
alter table users add column password text(100);


INSERT INTO `users` ( `first_name`, `last_name`, `email`, `password`, `roles_id`) VALUES
( 'Miljana', 'Stamenkovic', 'miljana@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW', 1),
( 'Petar', 'Petrovic', 'petar@gmail.com', '$2a$10$.uXi8Cwa2woSVCSJaos5JeDvrZgbIh.I0vQX50pPS8rdPXXx2SN/C', 1),
( 'Mladen', 'Petrovic', 'mladen@gmail.com', '$2a$10$XHri7YQgrJuUeJx0By2N..Hg6L18xfMLTYjqACpju38V4OTH9OiM6', 1),
( 'Miljana', 'Stamenkovic', 'miljana@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW', 1),
( 'Miljana', 'Stamenkovic', 'miljana@gmail.com', '$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW', 1);


alter table group_order drop column order_timeout ;
alter table group_order add column order_timeout datetime;
