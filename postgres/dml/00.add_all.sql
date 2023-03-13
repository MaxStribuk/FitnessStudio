INSERT INTO app.user_role(role) VALUES ('USER');
INSERT INTO app.user_role(role) VALUES ('ADMIN');

INSERT INTO app.user_status(status) VALUES ('ACTIVATED');
INSERT INTO app.user_status(status) VALUES ('DEACTIVATED');
INSERT INTO app.user_status(status) VALUES ('WAITING_ACTIVATION');

INSERT INTO app.mail_status(status) VALUES ('WAITING');
INSERT INTO app.mail_status(status) VALUES ('SENT');
INSERT INTO app.mail_status(status) VALUES ('ERROR');
INSERT INTO app.mail_status(status) VALUES ('SUCCESS');