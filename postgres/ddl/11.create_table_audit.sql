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