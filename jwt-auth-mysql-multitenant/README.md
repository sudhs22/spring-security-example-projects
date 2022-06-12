# Database Scripts

```
use test;


CREATE TABLE `user` (
`user_id` bigint NOT NULL AUTO_INCREMENT,
`enabled` bit(1) NOT NULL,
`password` varchar(255) DEFAULT NULL,
`username` varchar(255) DEFAULT NULL,
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
`role_id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) DEFAULT NULL,
PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
`user_id` bigint NOT NULL,
`role_id` int NOT NULL,
PRIMARY KEY (`user_id`,`role_id`),
KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Insert Users
insert into user(user_id, username, password, enabled) values(1, "user", "abcd", true);
insert into user(user_id, username, password, enabled) values(2, "admin", "abcd", true);
-- select * from user;


-- Insert Roles
insert into role (role_id, name) values (1, 'ROLE_USER');
insert into role (role_id, name) values (2, 'ROLE_ADMIN');
-- select * from role;

-- User Role Mapping
insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
```
