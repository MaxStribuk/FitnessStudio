CREATE DATABASE fitness
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE IF NOT EXISTS app.user_role
(
    id smallserial NOT NULL,
    role character(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_role_key UNIQUE (role)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.user_role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS app.user_status
(
    id smallserial NOT NULL,
    status character(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.user_status
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS app.users
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    fio text COLLATE pg_catalog."default" NOT NULL,
    role smallint NOT NULL,
    status smallint NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT users_mail_key UNIQUE (mail),
    CONSTRAINT users_role_fkey FOREIGN KEY (role)
        REFERENCES app.user_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT users_status_fkey FOREIGN KEY (status)
        REFERENCES app.user_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS app.mail_status
(
    id smallserial NOT NULL,
    status character(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT mail_status_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.mail_status
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS app.mails
(
    uuid uuid NOT NULL,
    user_id uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    subject text COLLATE pg_catalog."default" NOT NULL,
    departures integer NOT NULL,
    status smallint NOT NULL,
    code uuid,
    CONSTRAINT mails_pkey PRIMARY KEY (uuid),
    CONSTRAINT mails_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES app.users (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT mails_status_fkey FOREIGN KEY (status)
        REFERENCES app.mail_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.mails
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS app.products
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text COLLATE pg_catalog."default" NOT NULL,
    weight integer NOT NULL,
    calories integer NOT NULL,
    proteins numeric NOT NULL,
    fats numeric NOT NULL,
    carbohydrates numeric NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (uuid),
    CONSTRAINT products_title_key UNIQUE (title)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.products
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS app.recipes
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT recipes_pkey PRIMARY KEY (uuid),
    CONSTRAINT recipes_title_key UNIQUE (title)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.recipes
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS app.recipes_products
(
    recipe_id uuid NOT NULL,
    product_id uuid NOT NULL,
    weight integer NOT NULL,
    CONSTRAINT recipes_products_pkey PRIMARY KEY (recipe_id, product_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.recipes_products
    OWNER to postgres;