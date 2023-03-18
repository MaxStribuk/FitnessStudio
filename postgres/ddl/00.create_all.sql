CREATE DATABASE fitness;

\c fitness;

CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE IF NOT EXISTS app.user_role
(
    id smallserial NOT NULL,
    role character(20) NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_role_key UNIQUE (role)
);

CREATE TABLE IF NOT EXISTS app.user_status
(
    id smallserial NOT NULL,
    status character(20) NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app.users
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    mail text NOT NULL,
    fio text NOT NULL,
    role smallint NOT NULL,
    status smallint NOT NULL,
    password text NOT NULL,
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
);

CREATE TABLE IF NOT EXISTS app.mail_status
(
    id smallserial NOT NULL,
    status character(20) NOT NULL,
    CONSTRAINT mail_status_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app.mails
(
    uuid uuid NOT NULL,
    user_id uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    subject text NOT NULL,
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
);

CREATE TABLE IF NOT EXISTS app.products
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text NOT NULL,
    weight integer NOT NULL,
    calories integer NOT NULL,
    proteins numeric NOT NULL,
    fats numeric NOT NULL,
    carbohydrates numeric NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (uuid),
    CONSTRAINT products_title_key UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS app.recipes
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text NOT NULL,
    CONSTRAINT recipes_pkey PRIMARY KEY (uuid),
    CONSTRAINT recipes_title_key UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS app.recipes_products
(
    recipe_id uuid NOT NULL,
    product_id uuid NOT NULL,
    weight integer NOT NULL,
    CONSTRAINT recipes_products_pkey PRIMARY KEY (recipe_id, product_id)
);

CREATE TABLE IF NOT EXISTS app.essence_type
(
    id smallint NOT NULL,
    type text NOT NULL,
    CONSTRAINT essence_type_pkey PRIMARY KEY (id),
    CONSTRAINT essence_type_type_key UNIQUE (type)
);

CREATE TABLE IF NOT EXISTS app.audit
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    user_uuid uuid NOT NULL,
    mail text NOT NULL,
    fio text NOT NULL,
    role smallint NOT NULL,
    text text NOT NULL,
    essence_type smallint NOT NULL,
    CONSTRAINT audit_pkey PRIMARY KEY (uuid),
    CONSTRAINT audit_essence_type_id_fkey FOREIGN KEY (essence_type)
        REFERENCES app.essence_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT audit_user_role_id_fkey FOREIGN KEY (role)
        REFERENCES app.user_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);