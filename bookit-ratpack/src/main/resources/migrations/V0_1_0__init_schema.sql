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
