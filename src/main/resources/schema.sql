CREATE DATABASE catalog;

CREATE TABLE items (
    id varchar(10) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL
);