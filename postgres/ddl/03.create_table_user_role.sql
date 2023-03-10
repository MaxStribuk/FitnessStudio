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