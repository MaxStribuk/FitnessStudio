CREATE TABLE IF NOT EXISTS app.recipes_ingredients
(
    recipe_id uuid NOT NULL,
    ingredient_id uuid NOT NULL,
    CONSTRAINT recipes_ingredients_pkey PRIMARY KEY (recipe_id, ingredient_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.recipes_ingredients
    OWNER to postgres;