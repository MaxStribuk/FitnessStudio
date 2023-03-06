CREATE TABLE IF NOT EXISTS app.ingredients
(
    uuid uuid NOT NULL,
    product_uuid uuid NOT NULL,
    weight integer NOT NULL,
    CONSTRAINT ingredients_pkey PRIMARY KEY (uuid)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.ingredients
    OWNER to postgres;