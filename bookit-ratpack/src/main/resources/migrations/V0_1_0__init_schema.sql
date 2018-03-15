CREATE SCHEMA IF NOT EXISTS bookit;

CREATE TABLE bookit.author(
  id UUID PRIMARY KEY,
  created timestamp,
  name varchar(255)
);

CREATE TABLE bookit.book(
  id UUID PRIMARY KEY,
  created timestamp,
  published date,
  title varchar(255),
  image_uri varchar(255),
  short_description varchar(255),
  author_id UUID,
  FOREIGN KEY (author_id) REFERENCES bookit.author(id)
);

CREATE TABLE bookit.app_user(
  id UUID PRIMARY KEY,
  created timestamp,
  username varchar(255),
  password varchar(255)
);

CREATE TABLE bookit.app_role(
  id UUID PRIMARY KEY,
  created timestamp,
  rolename VARCHAR(255)
);

CREATE TABLE bookit.app_user_role(
  id UUID PRIMARY KEY,
  created timestamp,
  role_id UUID,
  user_id UUID,
  FOREIGN KEY (role_id) REFERENCES bookit.app_role(id),
  FOREIGN KEY (user_id) REFERENCES bookit.app_user(id)
);

-- Some users
INSERT INTO bookit.app_user
  (id, created, username, password)
VALUES
  ('03e35320-26a9-11e8-b467-0ed5f89f718b',
  '3/13/2018',
  'user@bookit.org',
  '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb');

INSERT INTO bookit.app_user
  (id, created, username, password)
VALUES
  ('03e3574e-26a9-11e8-b467-0ed5f89f718b',
  '3/13/2018',
  'admin@bookit.org',
  '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

-- Some roles and user_roles
INSERT INTO bookit.app_role (id, created, rolename) VALUES ('03e358ac-26a9-11e8-b467-0ed5f89f718b', '3/13/2018', 'USER');
INSERT INTO bookit.app_role (id, created, rolename) VALUES ('03e359ce-26a9-11e8-b467-0ed5f89f718b', '3/13/2018', 'ADMIN');

INSERT INTO bookit.app_user_role
  (id, created, role_id, user_id)
VALUES
  ('03e35af0-26a9-11e8-b467-0ed5f89f718b',
  '3/13/2018',
  '03e358ac-26a9-11e8-b467-0ed5f89f718b',
  '03e35320-26a9-11e8-b467-0ed5f89f718b');

INSERT INTO bookit.app_user_role
  (id, created, role_id, user_id)
VALUES
  ('03e35c12-26a9-11e8-b467-0ed5f89f718b',
  '3/13/2018',
  '03e359ce-26a9-11e8-b467-0ed5f89f718b',
  '03e3574e-26a9-11e8-b467-0ed5f89f718b');
