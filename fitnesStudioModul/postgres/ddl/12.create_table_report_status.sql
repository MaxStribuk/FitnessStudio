CREATE TABLE IF NOT EXISTS app.report_status
(
    id smallserial NOT NULL,
    status text NOT NULL,
    CONSTRAINT report_status_pkey PRIMARY KEY (id),
    CONSTRAINT report_status_status_key UNIQUE (status)
);