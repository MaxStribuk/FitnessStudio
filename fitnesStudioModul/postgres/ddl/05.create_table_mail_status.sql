CREATE TABLE IF NOT EXISTS app.mail_status
(
    id smallserial NOT NULL,
    status character(20) NOT NULL,
    CONSTRAINT mail_status_pkey PRIMARY KEY (id)
);