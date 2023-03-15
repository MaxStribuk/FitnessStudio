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
VALUES ('00000000-0000-0000-0000-000000000000',
        '2023-01-01 00:00:00.000',
        '2023-01-01 00:00:00.000',
        'maximus747@mail.ru',
        'Max',
        2,
        1,
        '$2a$10$WzPoFH6QF0lO8LEpefoAmeoycv9F7PEXB6g.Z2qgb8djavHWKDmTS');