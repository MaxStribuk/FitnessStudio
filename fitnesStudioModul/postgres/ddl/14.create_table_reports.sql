CREATE TABLE IF NOT EXISTS app.reports
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    status smallint NOT NULL,
    type smallint NOT NULL,
    description text NOT NULL,
    user_id uuid NOT NULL,
    from_ date NOT NULL,
    to_ date NOT NULL,
    CONSTRAINT reports_pkey PRIMARY KEY (uuid),
    CONSTRAINT reports_status_fkey FOREIGN KEY (status)
        REFERENCES app.report_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT reports_type_fkey FOREIGN KEY (type)
        REFERENCES app.report_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);