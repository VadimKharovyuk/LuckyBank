CREATE TABLE card (
                      id BIGSERIAL NOT NULL,
                      balance DOUBLE PRECISION NOT NULL,
                      card_number VARCHAR(255),
                      cvv VARCHAR(255),
                      expiration_date DATE,
                      client_id BIGINT,
                      PRIMARY KEY (id)
);



CREATE TABLE client (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        last_name VARCHAR(255),
                        email VARCHAR(255),
                        address VARCHAR(255),
                        balance DOUBLE PRECISION,
                        UNIQUE (name)  -- Уникальный индекс на имя
);

--
--
--
--
--  create table client_food (client_id bigint not null, food_id bigint not null)
--  create table food (id bigserial not null, description varchar(255), name varchar(255), price float(53) not null, primary key (id))