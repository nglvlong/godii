DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    firstname    VARCHAR(255),
    lastname     VARCHAR(255),
    address      VARCHAR(500),
    phone        varchar(255),
    age          integer,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
