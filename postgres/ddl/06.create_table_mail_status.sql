CREATE TABLE IF NOT EXISTS app.mail_status
(
    id smallserial NOT NULL,
    status character(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT mail_status_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.mail_status
    OWNER to postgres;