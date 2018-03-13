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

-- Some authors
INSERT INTO bookit.author (id, created, name) VALUES ('f75a73b6-23cb-11e8-b467-0ed5f89f718b', '3/09/2018', 'Donald Knuth');
INSERT INTO bookit.author (id, created, name) VALUES ('f75a77c6-23cb-11e8-b467-0ed5f89f718b', '3/09/2018', 'Siobhan Roberts');

-- Some books
INSERT INTO bookit.book
 (id, created, published, title, image_uri, short_description, author_id)
VALUES
 ('f75a7ca8-23cb-11e8-b467-0ed5f89f718b',
 '3/09/2018',
 '1/11/1974',
 'Surreal Numbers',
 'https://images-na.ssl-images-amazon.com/images/I/419WCKFCYEL._SX312_BO1,204,203,200_.jpg',
 'Shows how a young couple turned on to pure mathematics and found total happiness',
 'f75a73b6-23cb-11e8-b467-0ed5f89f718b');

INSERT INTO bookit.book
 (id, created, published, title, image_uri, short_description, author_id)
VALUES
 ('f75a7ea6-23cb-11e8-b467-0ed5f89f718b',
 '3/09/2018',
 '7/14/2015',
 'Genius At Play: The Curious Mind of John Horton Conway',
 'https://images-na.ssl-images-amazon.com/images/I/51wIzllKGNL._SX327_BO1,204,203,200_.jpg',
 'John Horton Conway is a singular mathematician with a lovely loopy brain',
 'f75a77c6-23cb-11e8-b467-0ed5f89f718b');
