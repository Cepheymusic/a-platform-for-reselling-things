-- public.comment_entity definition

-- Drop table

-- DROP TABLE public.comment_entity;

CREATE TABLE public.comment_entity (
	pk serial4 NOT NULL,
	author_first_name varchar(255) NULL,
	author_image varchar(255) NULL,
	created_at timestamp NULL,
	"text" varchar(255) NULL,
	ad_id int4 NOT NULL,
	author_id int4 NOT NULL,
	created_at_inst timestamp NULL,
	id int4 NOT NULL,
	image varchar(255) NOT NULL,
	CONSTRAINT comment_entity_pkey PRIMARY KEY (pk)
);


-- public.comment_entity foreign keys

ALTER TABLE public.comment_entity ADD CONSTRAINT fkexrg7rmory7ijwhf6ha5ke2rn FOREIGN KEY (author_id) REFERENCES public.user_entity(pk);
ALTER TABLE public.comment_entity ADD CONSTRAINT fkpxsyoxaa7yi6kcnny2j7necax FOREIGN KEY (ad_id) REFERENCES public.ad_entity(pk);