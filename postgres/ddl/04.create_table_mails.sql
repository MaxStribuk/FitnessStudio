CREATE TABLE IF NOT EXISTS app.mails
(
    uuid uuid NOT NULL,
    user_id uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    subject text COLLATE pg_catalog."default" NOT NULL,
    departures integer NOT NULL,
    status text COLLATE pg_catalog."default" NOT NULL,
    code uuid,
    CONSTRAINT mails_pkey PRIMARY KEY (uuid),
    CONSTRAINT mails_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES app.users (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.mails
    OWNER to postgres;