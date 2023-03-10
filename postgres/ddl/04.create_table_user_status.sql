CREATE TABLE IF NOT EXISTS app.user_status
(
    id smallserial NOT NULL,
    status character(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.user_status
    OWNER to postgres;