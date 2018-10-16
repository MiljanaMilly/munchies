
ALTER TABLE group_order
DROP COLUMN order_url;
ALTER TABLE group_order
ADD order_url varchar(500);

insert into roles (role_name) values ("ADMIN");

insert into users(first_name,last_name,email,password, roles_id) 
values ("Miljana","Stamenkovic","miljana@gmail.com","$2a$10$NptDrc5dVIvHjLRL7Ja5Ne8WFeawbNtL4jvsjukpXKcPIX0DJ73yW", 1 );



