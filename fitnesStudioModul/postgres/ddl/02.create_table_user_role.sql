CREATE TABLE IF NOT EXISTS app.user_role
(
    id smallserial NOT NULL,
    role character(20) NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_role_key UNIQUE (role)
);