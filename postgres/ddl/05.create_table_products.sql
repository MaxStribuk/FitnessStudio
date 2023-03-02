CREATE TABLE IF NOT EXISTS app.products
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
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