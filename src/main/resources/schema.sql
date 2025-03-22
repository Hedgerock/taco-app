CREATE TABLE IF NOT EXISTS taco_order (
    id identity,
    delivery_name varchar(50) NOT NULL,
    delivery_street varchar(50) NOT NULL,
    delivery_city varchar(50) NOT NULL,
    delivery_state varchar(50) NOT NULL,
    delivery_zip varchar(10) NOT NULL,
    credit_card_number varchar(19) NOT NULL,
    credit_card_expiration_date varchar(5) NOT NULL,
    credit_card_cvv varchar(3) NOT NULL,
    placed_at timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos (
    id identity,
    name varchar(50) NOT NULL,
    taco_order bigint NOT NULL,
    taco_order_key bigint NOT NULL,
    created_at timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredient_ref (
    ingredient varchar(4) NOT NULL,
    taco bigint NOT NULL,
    taco_key bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredients (
    id varchar(4) NOT NULL PRIMARY KEY ,
    name varchar(25) NOT NULL,
    type varchar(25) NOT NULL
);

ALTER TABLE IF EXISTS tacos
    ADD FOREIGN KEY (taco_order) REFERENCES taco_order(id);
ALTER TABLE IF EXISTS ingredient_ref
    ADD FOREIGN KEY (ingredient) REFERENCES ingredients(id);