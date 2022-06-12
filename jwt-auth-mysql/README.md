use test;

-- Insert Users
insert into user(username, password, enabled) values("sudeep", "abcd", true);
insert into user(username, password, enabled) values("admin", "abcd", true);
select * from user;


-- Insert Roles
insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');
select * from role;

-- User Role Mapping
insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);