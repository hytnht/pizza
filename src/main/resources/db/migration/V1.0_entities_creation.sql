CREATE SCHEMA IF NOT EXISTS dev;

CREATE TABLE IF NOT EXISTS dev.address
(
    id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    unit     VARCHAR(255),
    street   VARCHAR(255) NOT NULL,
    ward     VARCHAR(30)  NOT NULL,
    district VARCHAR(30)  NOT NULL,
    city     VARCHAR(30)  NOT NULL
);

CREATE TABLE IF NOT EXISTS dev.customer
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name         VARCHAR(255),
    day_of_birth DATE,
    phone_number VARCHAR(15) UNIQUE
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
    id    BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER
);

CREATE TABLE IF NOT EXISTS dev.sauce
(
    id    BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER
);

CREATE TABLE IF NOT EXISTS dev.topping
(
    id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name     VARCHAR(255),
    price    FLOAT,
    quantity INTEGER,
    stock    INTEGER
);

CREATE TABLE IF NOT EXISTS dev.pizza
(
    id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    size     INTEGER,
    crust_id BIGINT NOT NULL,
    CONSTRAINT FK_PIZZA_ON_CRUST FOREIGN KEY (crust_id) REFERENCES dev.crust (id)
);

CREATE TABLE IF NOT EXISTS dev."order"
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    delivery_note VARCHAR(255),
    customer_id   BIGINT NOT NULL,
    address_id    BIGINT NOT NULL,
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
