use newservlet112019;

CREATE TABLE public.mst_gender
(
    gender_id integer NOT NULL,
    gender_name character varying(10) COLLATE pg_catalog."default",
    create_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone NOT NULL,
    create_user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    update_user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT mst_gender_pkey PRIMARY KEY (gender_id)
);

CREATE TABLE public.mst_role
(
    authority_id integer NOT NULL,
    authority_name character varying(10) COLLATE pg_catalog."default",
    create_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone NOT NULL,
    create_user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    update_user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT mst_role_pkey PRIMARY KEY (authority_id)
);

CREATE TABLE public.mst_user
(
    user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    password character varying(8) COLLATE pg_catalog."default" NOT NULL,
    family_name character varying(10) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(10) COLLATE pg_catalog."default" NOT NULL,
    gender_id integer,
    age integer,
    authority_id integer,
    admin integer NOT NULL,
    create_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone NOT NULL,
    create_user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    update_user_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT mst_user_pkey PRIMARY KEY (user_id),
    CONSTRAINT fk_user_gender FOREIGN KEY (gender_id)
        REFERENCES public.mst_gender (gender_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_user_role FOREIGN KEY (authority_id)
        REFERENCES public.mst_role (authority_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

















