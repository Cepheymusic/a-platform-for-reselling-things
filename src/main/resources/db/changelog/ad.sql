-- public.ad_entity definition

-- Drop table

-- DROP TABLE public.ad_entity;

CREATE TABLE public.ad_entity (
	pk serial4 NOT NULL,
	description varchar(255) NULL,
	image varchar(255) NOT NULL,
	price int4 NOT NULL,
	title varchar(255) NOT NULL,
	author_id int4 NOT NULL,
	id int4 NOT NULL,
	CONSTRAINT ad_entity_pkey PRIMARY KEY (pk)
);


-- public.ad_entity foreign keys

ALTER TABLE public.ad_entity ADD CONSTRAINT fklqa1jebb58188r8k2o6gd9mks FOREIGN KEY (author_id) REFERENCES public.user_entity(pk);