INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_STUDENT');
INSERT INTO roles(name) VALUES('ROLE_PROFESSOR');


INSERT INTO users(username, password) VALUES ('admin', '$2a$10$jjGR4q0V7uCWX9F/1c2Sw.DULEwTpFec8ub8/tlOp3bsQkj1fHJp.');
INSERT INTO users(username, password) VALUES ('user', '$2a$10$oR2ESLPT5ann4bTj75mH9uZ614/IFYkKcttm6NWefZv3oXUP5vZ3i');
INSERT INTO users(username, password) VALUES ('student', '$2a$10$4v58WbYyZqcezcetxFw36.pudibpyIMBojJXtBaN8B/Kv/s3EWmta');

INSERT INTO user_roles(user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO user_roles(user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));
INSERT INTO user_roles(user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'student'), (SELECT id FROM roles WHERE name = 'ROLE_STUDENT'));

INSERT INTO library(amount, author, name) VALUES (1, 'author', 'name')

INSERT INTO user_library(borrow_date, library_id, user_id) VALUES ('2020-11-17', (SELECT id FROM library WHERE name = 'name'), (SELECT id from users WHERE username = 'user'))
