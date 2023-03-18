CREATE TABLE IF NOT EXISTS app.user_status
(
    id smallserial NOT NULL,
    status character(20) NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (id)
);