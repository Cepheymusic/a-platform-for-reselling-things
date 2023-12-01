-- public.user_entity definition

-- Drop table

-- DROP TABLE public.user_entity;

CREATE TABLE public.user_entity (
	id serial4 NOT NULL,
	email varchar(255) NULL,
	first_name varchar(255) NULL,
	image varchar(255) NULL,
	last_name varchar(255) NULL,
	phone varchar(255) NULL,
	user_role int4 NULL,
	id int4 NOT NULL,
	CONSTRAINT user_entity_pkey PRIMARY KEY (pk)
);