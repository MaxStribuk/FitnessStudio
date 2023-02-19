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

CREATE TABLE IF NOT EXISTS app.users
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    fio text COLLATE pg_catalog."default" NOT NULL,
    role text COLLATE pg_catalog."default" NOT NULL,
    status text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT users_mail_key UNIQUE (mail)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;