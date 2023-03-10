CREATE TABLE IF NOT EXISTS app.recipes_products
(
    recipe_id uuid NOT NULL,
    product_id uuid NOT NULL,
    weight integer NOT NULL,
    CONSTRAINT recipes_products_pkey PRIMARY KEY (recipe_id, product_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.recipes_products
    OWNER to postgres;