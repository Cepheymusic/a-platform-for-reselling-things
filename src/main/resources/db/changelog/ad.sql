-- liquibase formatted sql

-- changeset laserova:2

CREATE TABLE ad_entity (
	id serial4 NOT NULL primary key,
	description varchar(255) NULL,
	image varchar(255) NOT NULL,
	price int4 NOT NULL,
	title varchar(255) NOT NULL,
	author_id int4 NOT NULL
--	id int4 NOT NULL
);


-- public.ad_entity foreign keys

ALTER TABLE public.ad_entity ADD CONSTRAINT ad_user FOREIGN KEY (author_id) REFERENCES public.user_entity(id);