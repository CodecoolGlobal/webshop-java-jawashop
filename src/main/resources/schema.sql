DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS order_payments;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS credit_cards;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id uuid PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    auth_token VARCHAR NULL UNIQUE
);

CREATE TABLE category (
    id uuid PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(200),
    department VARCHAR(20)
);

CREATE TABLE supplier (
    id uuid PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(200)
);

CREATE TABLE product (
    id uuid PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(200),
    default_price FLOAT,
    default_currency VARCHAR(3),
    category_id uuid REFERENCES category(id),
    supplier_id uuid REFERENCES  supplier(id)
);

CREATE TABLE cart (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    product_id uuid REFERENCES product(id),
    quantity INTEGER
);

CREATE TABLE addresses (
    id uuid PRIMARY KEY,
    country VARCHAR(30) NOT NULL,
    city VARCHAR(30) NOT NULL,
    zip_code VARCHAR(30) NOT NULL,
    address VARCHAR(50) NOT NULL
);

CREATE TABLE orders (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number BIGINT NOT NULL,
    billing_address_id  uuid REFERENCES addresses(id),
    shipping_address_id uuid REFERENCES addresses(id)
);

CREATE TABLE order_products (
    id uuid PRIMARY KEY,
    order_id uuid  REFERENCES orders(id),
    product_id uuid REFERENCES product(id),
    quantity INTEGER
);

CREATE TABLE credit_cards (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    number BIGINT NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    expire_date VARCHAR(5) NOT NULL,
    code INTEGER NOT NULL
);

CREATE TABLE order_payments (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    order_id uuid NOT NULL REFERENCES orders(id),
    credit_card_id uuid NOT NULL REFERENCES credit_cards(id),
    inserted_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX auth_token_idx ON users(auth_token);
