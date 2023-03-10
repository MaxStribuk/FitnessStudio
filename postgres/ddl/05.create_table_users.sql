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
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT users_status_fkey FOREIGN KEY (status)
        REFERENCES app.user_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;