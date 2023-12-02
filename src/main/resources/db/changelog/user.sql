-- liquibase formatted sql

-- changeset laserova:1

CREATE TABLE user_entity (
	id serial4 NOT NULL primary key,
	first_name varchar(255) not NULL,
	last_name varchar(255) not NULL,
	email varchar(255) not NULL,
	phone varchar(255) not NULL,
	image varchar(255),
	password varchar(255),
	user_role int4 default 0
--	id int4 NOT NULL,
);

insert into user_entity values (1,'Имя','Фамилия','ads@mail.ru','+7(912)123-45-67','https://www.touristlink.com/nepal/overview.html','123');