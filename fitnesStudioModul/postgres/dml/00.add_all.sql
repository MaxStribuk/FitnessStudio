INSERT INTO app.user_role(role) VALUES ('USER');
INSERT INTO app.user_role(role) VALUES ('ADMIN');

INSERT INTO app.user_status(status) VALUES ('ACTIVATED');
INSERT INTO app.user_status(status) VALUES ('DEACTIVATED');
INSERT INTO app.user_status(status) VALUES ('WAITING_ACTIVATION');

INSERT INTO app.mail_status(status) VALUES ('WAITING');
INSERT INTO app.mail_status(status) VALUES ('SENT');
INSERT INTO app.mail_status(status) VALUES ('ERROR');
INSERT INTO app.mail_status(status) VALUES ('SUCCESS');

INSERT INTO app.users
VALUES ('00000000-0000-4000-8000-000000000000',
        '2023-01-01 00:00:00.000',
        '2023-01-01 00:00:00.000',
        'maxstr@mail.ru',
        'Max',
        2,
        1,
        '$2a$10$WzPoFH6QF0lO8LEpefoAmeoycv9F7PEXB6g.Z2qgb8djavHWKDmTS');

INSERT INTO app.essence_type(type) VALUES ('PRODUCT');
INSERT INTO app.essence_type(type) VALUES ('RECIPE');
INSERT INTO app.essence_type(type) VALUES ('USER');
INSERT INTO app.essence_type(type) VALUES ('REPORT');

INSERT INTO app.report_status(status) VALUES ('LOADED');
INSERT INTO app.report_status(status) VALUES ('PROGRESS');
INSERT INTO app.report_status(status) VALUES ('ERROR');
INSERT INTO app.report_status(status) VALUES ('DONE');

INSERT INTO app.report_type(type) VALUES ('JOURNAL_AUDIT');