CREATE TABLE IF NOT EXISTS app.report_type
(
    id smallserial NOT NULL,
    type text NOT NULL,
    CONSTRAINT report_type_pkey PRIMARY KEY (id),
    CONSTRAINT report_type_type_key UNIQUE (type)
);