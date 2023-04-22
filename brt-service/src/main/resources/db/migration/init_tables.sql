DROP TABLE IF EXISTS client_payload;
DROP TABLE IF EXISTS operation;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS tariff;

CREATE TABLE tariff(
    id                      BIGSERIAL PRIMARY KEY NOT NULL,
    tariff_id               VARCHAR(16) UNIQUE NOT NULL,
    name                    VARCHAR(32) NOT NULL,
    price                   DOUBLE PRECISION NOT NULL,
    default_minute_price    DOUBLE PRECISION NOT NULL,
    tariff_minute_price     DOUBLE PRECISION NOT NULL,
    minutes_left            INT NOT NULL,
    incoming_free           BOOLEAN NOT NULL
);

CREATE TABLE client(
    id                      BIGSERIAL PRIMARY KEY NOT NULL,
    number_phone            VARCHAR(16) UNIQUE NOT NULL,
    tariff_id               INT REFERENCES tariff NOT NULL,
    balance                 DOUBLE PRECISION NOT NULL,
    total_cost              FLOAT NOT NULL,
    monetary_unit           VARCHAR(16) NOT NULL
);

CREATE TABLE operation(
    id                      BIGSERIAL PRIMARY KEY,
    call_type               VARCHAR(16) NOT NULL,
    start_time              TIMESTAMP NOT NULL,
    end_time                TIMESTAMP NOT NULL,
    duration                BIGINT NOT NULL,
    cost                    DOUBLE PRECISION NOT NULL
);

CREATE TABLE client_payload(
    client_id               BIGINT NOT NULL REFERENCES client,
    payload_id              BIGINT NOT NULL REFERENCES operation
)

