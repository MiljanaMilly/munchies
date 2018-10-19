

Alter table roles add column role_name varchar(150) default 'ADMIN';

drop table users_role;

CREATE TABLE if not exists `users_role` (
  `roles_id` int(200) DEFAULT '1',
  `users_id` bigint(200),
  foreign key(roles_id) references roles(roles_id),
  foreign key(users_id) references users(users_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

