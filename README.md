# VirtuaExpress: CodeCool WebShop

## Introduction

The goal was to build an Online Shop, an online eCommerce web-application with
Java, where users can browse products, add them into a shopping cart, checkout
items, make payments, log-in, browse shopping cart and order history.

## Technologies

- Java servlets (JDK 11)
- PostgreSQL
- Maven
- Jetty
- JavaScript
- jQuery 3.3.1
- Bootstrap 4.2.1
- [Notiflix 2.1.2](https://www.notiflix.com)
- [Popper.js 1.14.3)](https://popper.js.org)

## Setup

### IntelliJ

- Import `pom.xml` to load the whole Maven project
- Create a new configuration to run the app with Jetty webserver:
  - Select a new Maven configuration
  - Name it to something (eg. `VirtuaExpress`)
  - Set the command line arguments to `jetty:run -f pom.xml`
  - Set the JDK to 11

### Environment

- Navigate to the `src/main/resources` folder
- Rename `.env.example` to `.env`
- Complete the key value pairs

### PostgreSQL

- Create a user and database according to the previously edited `.env` file
- Execute the `schame.sql` than the `data.sql` to migrate and seed the database

## Status

### Sprint #5 (2020 May 04-08) ([diff](https://github.com/CodecoolGlobal/webshop-java-jawashop/compare/1d4a4eef6ce01dad9058a54cae3c4fab2fca958f...3766b37355dc1da0ee205e6900c4e349c0d90ed8))

- [x] **_Test database_**: Tests doesn't run against the production database
- [x] **_Product / DB / Testing_**: JDBC DAO classes tested
- [x] **_User / Order history_**: List user's previous orders
- [x] **_User / Save billing and shipping info_**: Prefill form with previous details
- [Product & Spring Backlog](https://docs.google.com/spreadsheets/d/1jIK7Ynltlv7Kt2NBbTOA73VulsxTj1h8fm7C7wQfajY)
- [Presentation](https://docs.google.com/presentation/d/1eUg5vUmJp82tuYJFSXZvOm70BRVX_qL8fuuSzIaa-QQ)

### Sprint #4 (2020 April 20-24) ([diff](https://github.com/CodecoolGlobal/webshop-java-jawashop/compare/0be56778907482f4fe9650ee05a51bed262a7915...1535043b9e67f3783cc28bd7509d2e8b7833e6e4))

- [x] **_Shopping Cart / Payment_**: Fake PayPal or Credit card payment option
- [x] **_Shopping Cart / Safe Chechout_**: Save order details to database
- [x] **_Shopping Cart / Confirmation_**: Result of the transaction: success / failed. ~~Email about the payment.~~
- [x] **_Database / Config_**: Read database connection details from `src/main/resources/.env` file
- [Product & Spring Backlog](https://docs.google.com/spreadsheets/d/1jIK7Ynltlv7Kt2NBbTOA73VulsxTj1h8fm7C7wQfajY)
- [Presentation](https://docs.google.com/presentation/d/1npcQXKGYZxneB8XXSWo6MJVmYx1VYeJ2jYCV2V3zfjs)

### Sprint #3 (2020 Apr 6-10) ([diff](https://github.com/CodecoolGlobal/webshop-java-jawashop/compare/2df8b4e4d1b15a9fce5d8e901338ccde31c141f3...f7fd191339a5ef19fc6b4bd7ba518f03f295dcfd))

- [x] **_Shopping Cart / Checkout_**: Checkout items from the Shopping Cart to start the order process
- [x] **_User / Registration_**: Password hashed with BCrypt and the salt can be set in the `.env` file by specifying `APP_SALT`
- [x] **_User / Login - Logout_**
- [Product & Spring Backlog](https://docs.google.com/spreadsheets/d/1jIK7Ynltlv7Kt2NBbTOA73VulsxTj1h8fm7C7wQfajY)

### Sprint #2 (2020 March 23-27) ([diff](https://github.com/CodecoolGlobal/webshop-java-jawashop/compare/276d7533029361f0cd060ab58c86365ddf2c6fc7...073aa551a7bb84bfc1efb92de671f92b57659b33))

- [x] **_Shopping Cart / Review_**: View items in the shopping cart
- [x] **_Products DB_**: Store data in a database
- [x] **_Shopping Cart / Edit_**: Change the quantity of the Product
- [ ] **_Shopping Cart / Checkout_**: Form to get contact information for the shipment (only UI done)
- [Product & Sprint Backlog](https://docs.google.com/spreadsheets/d/1MtD0igVGZPdnc7V0v3aLZ-7QzUH1vOoj28_qmqr60ZQ)
- [Presentation](https://docs.google.com/presentation/d/1sBUoi_njtDe7iNkryv-sNkbRHdcHQ7UBOSeDQk7jAgs)

### Sprint #1 (2020 March 9-13) ([diff](https://github.com/CodecoolGlobal/webshop-java-jawashop/compare/0ce3a04993286b2c3b42c1208a1695a1e0f9f859...57aff7020b16715af17dc0b90389de55f44661ca))

- [x] **_Development / Create an environment_**: Setup the project for IntelliJ
- [x] **_Products / List_**: List the Product(s) on the home page from the default Category
- [x] **_Products / by Product Category_**: Filter Products by Product Categories
- [x] **_Products / by Supplier_**: Filter Products by Product Suppliers
- [x] **_Shopping Cart / Add_**: Add product(s) to the Shopping Cart
- [Product & Sprint Backlog](https://docs.google.com/spreadsheets/d/1MtD0igVGZPdnc7V0v3aLZ-7QzUH1vOoj28_qmqr60ZQ)
- [Presentation](https://docs.google.com/presentation/d/1u75cYMWwHpUllYgS-VuaxQfdLLj9IU008pD5UnllxWM)
