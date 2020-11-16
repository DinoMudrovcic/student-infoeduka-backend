INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_STUDENT');
INSERT INTO roles(name) VALUES('ROLE_PROFESSOR');


INSERT INTO users(username, password) VALUES ('admin', '$2a$10$jjGR4q0V7uCWX9F/1c2Sw.DULEwTpFec8ub8/tlOp3bsQkj1fHJp.');
INSERT INTO users(username, password) VALUES ('user', '$2a$10$oR2ESLPT5ann4bTj75mH9uZ614/IFYkKcttm6NWefZv3oXUP5vZ3i');

INSERT INTO user_roles(user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO user_roles(user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));
