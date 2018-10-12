
ALTER TABLE group_order
DROP COLUMN order_url;
ALTER TABLE group_order
ADD order_url varchar(500);

insert into roles (role_name) values ("ADMIN");

insert into users(first_name,last_name,email,password, roles_id) 
values ("Miljana","Stamenkovic","miljana@gmail.com","$2y$12$nZ0VRSaQYgb1aYfmoYMhxun/pOp/NYJengRY7QT.b2aEcThkG5O1K", 1 );



