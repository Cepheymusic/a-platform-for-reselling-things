-- liquibase formatted sql

-- changeset laserova:3

CREATE TABLE comment_entity (
	id serial4 NOT NULL primary key,
	author_first_name varchar(16) NULL,
	author_image varchar(255) NULL,
	created_at timestamp NULL,
	comment_text varchar(64) NULL,
	ad_id int4 NOT NULL,
	author_id int4 NOT NULL,
	created_at_inst timestamp NULL,
--	id int4 NOT NULL,
	image varchar(255) NOT NULL
);
--
--
-- public.comment_entity foreign keys

ALTER TABLE public.comment_entity ADD CONSTRAINT comment_user FOREIGN KEY (author_id) REFERENCES public.user_entity(id);
ALTER TABLE public.comment_entity ADD CONSTRAINT comment_ad FOREIGN KEY (ad_id) REFERENCES public.ad_entity(id);