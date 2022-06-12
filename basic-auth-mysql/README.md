use test;

select * from user;

insert into user(username, password, enabled) values("sudeep", "abcd", true);

select * from role;

insert into role (name) values ('USER');
insert into role (name) values ('ADMIN');


insert into user_role (user_id, role_id) values (1, 1);