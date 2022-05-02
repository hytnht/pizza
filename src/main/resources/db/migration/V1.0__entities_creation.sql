CREATE SCHEMA IF NOT EXISTS dev;

CREATE SEQUENCE IF NOT EXISTS dev.customer_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS dev.address_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS dev.crust_seq START 100 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS dev.topping_seq START 200 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS dev.sauce_seq START 300 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS dev.pizza_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS dev.order_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS dev.address
(
    id       BIGINT       NOT NULL DEFAULT nextval('dev.address_seq'),
    unit     VARCHAR(255),
    street   VARCHAR(255) NOT NULL,
    ward     VARCHAR(30)  NOT NULL,
    district VARCHAR(30)  NOT NULL,
    city     VARCHAR(30)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dev.customer
(
    id           BIGINT NOT NULL DEFAULT nextval('dev.customer_seq'),
    name         VARCHAR(255),
    day_of_birth DATE,
    phone_number VARCHAR(15) UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dev.customer_address
(
    customer_id BIGINT NOT NULL,
    address_id  BIGINT NOT NULL,
    CONSTRAINT pk_customer_address PRIMARY KEY (customer_id, address_id),
    CONSTRAINT FK_CUSTOMER_ADDRESS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES dev.address (id),
    CONSTRAINT FK_CUSTOMER_ADDRESS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES dev.customer (id)
);

CREATE TABLE IF NOT EXISTS dev.crust
(
    id    BIGINT NOT NULL DEFAULT nextval('dev.crust_seq'),
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dev.sauce
(
    id    BIGINT NOT NULL DEFAULT nextval('dev.sauce_seq'),
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dev.topping
(
    id    BIGINT NOT NULL DEFAULT nextval('dev.topping_seq'),
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dev.pizza
(
    id       BIGINT NOT NULL DEFAULT nextval('dev.pizza_seq'),
    size     INTEGER,
    crust_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_PIZZA_ON_CRUST FOREIGN KEY (crust_id) REFERENCES dev.crust (id)
);

CREATE TABLE IF NOT EXISTS dev."order"
(
    id            BIGINT NOT NULL DEFAULT nextval('dev.order_seq'),
    delivery_note VARCHAR(255),
    customer_id   BIGINT NOT NULL,
    address_id    BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES dev.customer (id),
    CONSTRAINT FK_ORDER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS dev.pizza_order
(
    pizza_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    quantity INTEGER,
    CONSTRAINT pk_pizza_order PRIMARY KEY (pizza_id, order_id),
    CONSTRAINT FK_PIZZA_ORDER_ON_ORDER FOREIGN KEY (order_id) REFERENCES dev."order" (id),
    CONSTRAINT FK_PIZZA_ORDER_ON_PIZZA FOREIGN KEY (pizza_id) REFERENCES dev.pizza (id)
);

CREATE TABLE IF NOT EXISTS pizza_sauce
(
    pizza_id BIGINT NOT NULL,
    sauce_id BIGINT NOT NULL,
    CONSTRAINT pk_pizza_sauce PRIMARY KEY (pizza_id, sauce_id),
    CONSTRAINT FK_PIZZA_SAUCE_ON_PIZZA FOREIGN KEY (pizza_id) REFERENCES dev.pizza (id),
    CONSTRAINT FK_PIZZA_SAUCE_ON_SAUCE FOREIGN KEY (sauce_id) REFERENCES dev.sauce (id)
);

CREATE TABLE IF NOT EXISTS pizza_topping
(
    pizza_id   BIGINT NOT NULL,
    topping_id BIGINT NOT NULL,
    CONSTRAINT pk_pizza_topping PRIMARY KEY (pizza_id, topping_id),
    CONSTRAINT FK_PIZZA_TOPPING_ON_PIZZA FOREIGN KEY (pizza_id) REFERENCES dev.pizza (id),
    CONSTRAINT FK_PIZZA_TOPPING_ON_TOPPING FOREIGN KEY (topping_id) REFERENCES dev.topping (id)
);
