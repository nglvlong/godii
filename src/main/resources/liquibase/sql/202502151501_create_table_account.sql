DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255),
    email        VARCHAR(255),
    password     VARCHAR(255),
    status       INTEGER,
    type         VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
