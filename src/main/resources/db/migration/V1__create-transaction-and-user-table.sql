CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    login        VARCHAR(100) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone        VARCHAR(100) NOT NULL,
    street       VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    postal_code  VARCHAR(100) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(100) NOT NULL,
    number       VARCHAR(100) NOT NULL,
    balance      DECIMAL      NOT NULL,
    active       BOOLEAN      NOT NULL
);

CREATE TABLE transactions
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT       NOT NULL REFERENCES users (id),
    type        VARCHAR(100) NOT NULL,
    date        TIMESTAMP    NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount      DECIMAL(10, 2)
);