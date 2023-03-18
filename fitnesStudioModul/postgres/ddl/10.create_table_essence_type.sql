CREATE TABLE IF NOT EXISTS app.essence_type
(
    id smallserial NOT NULL,
    type text NOT NULL,
    CONSTRAINT essence_type_pkey PRIMARY KEY (id),
    CONSTRAINT essence_type_type_key UNIQUE (type)
);