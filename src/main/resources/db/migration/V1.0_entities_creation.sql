CREATE SCHEMA IF NOT EXISTS dev;

CREATE TABLE IF NOT EXISTS dev.address
(
    id       BIGINT       NOT NULL PRIMARY KEY,
    unit     VARCHAR(255),
    street   VARCHAR(255) NOT NULL,
    ward     VARCHAR(30)  NOT NULL,
    district VARCHAR(30)  NOT NULL,
    city     VARCHAR(30)  NOT NULL
);

CREATE TABLE IF NOT EXISTS dev.customer
(
    id           BIGINT NOT NULL PRIMARY KEY,
    name         VARCHAR(255),
    day_of_birth TIMESTAMP WITHOUT TIME ZONE,
    phone_number VARCHAR(15) UNIQUE
);

CREATE TABLE IF NOT EXISTS dev.customer_address
(
    customer_id BIGINT NOT NULL,
    address_id  BIGINT NOT NULL,
    CONSTRAINT pk_customer_address PRIMARY KEY (customer_id, address_id),
    CONSTRAINT FK_CUSTOMER_ADDRESS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id),
    CONSTRAINT FK_CUSTOMER_ADDRESS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS dev.crust
(
    id    BIGINT NOT NULL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER
);

CREATE TABLE IF NOT EXISTS dev.sauce
(
    id    BIGINT NOT NULL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT,
    stock INTEGER
);

CREATE TABLE IF NOT EXISTS dev.topping
(
    id       BIGINT NOT NULL PRIMARY KEY,
    name     VARCHAR(255),
    price    FLOAT,
    quantity INTEGER,
    stock    INTEGER
);

CREATE TABLE IF NOT EXISTS dev.pizza
(
    id       BIGINT NOT NULL PRIMARY KEY,
    quantity INTEGER,
    size     INTEGER,
    crust_id BIGINT NOT NULL,
    CONSTRAINT FK_PIZZA_ON_CRUST FOREIGN KEY (crust_id) REFERENCES crust (id)
);

CREATE TABLE IF NOT EXISTS dev.pizza_addon
(
    pizza_id   BIGINT NOT NULL,
    topping_id BIGINT NOT NULL,
    sauce_id   BIGINT NOT NULL,
    CONSTRAINT pk_pizza_addon PRIMARY KEY (pizza_id, topping_id, sauce_id),
    CONSTRAINT FK_PIZZA_ADDON_ON_PIZZA FOREIGN KEY (pizza_id) REFERENCES pizza (id),
    CONSTRAINT FK_PIZZA_ADDON_ON_SAUCE FOREIGN KEY (sauce_id) REFERENCES sauce (id),
    CONSTRAINT FK_PIZZA_ADDON_ON_TOPPING FOREIGN KEY (topping_id) REFERENCES topping (id)
);

CREATE TABLE IF NOT EXISTS dev."order"
(
    id            BIGINT NOT NULL PRIMARY KEY,
    delivery_note VARCHAR(255),
    customer_id   BIGINT NOT NULL,
    CONSTRAINT FK_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS dev.pizza_order
(
    pizza_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT pk_pizza_order PRIMARY KEY (pizza_id, order_id),
    CONSTRAINT FK_PIZZA_ORDER_ON_ORDER FOREIGN KEY (order_id) REFERENCES "order" (id),
    CONSTRAINT FK_PIZZA_ORDER_ON_PIZZA FOREIGN KEY (pizza_id) REFERENCES pizza (id)
);






