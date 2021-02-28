DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  startDate TIMESTAMP DEFAULT NULL,
  endDate TIMESTAMP DEFAULT NULL,
  priority INT DEFAULT NULL,
  price DECIMAL DEFAULT NULL,
  currency VARCHAR(20) DEFAULT NULL,
  brandId INT DEFAULT NULL,
  foreign key (brandId) references BRANDS(id),
  productId INT DEFAULT NULL
);

INSERT INTO PRICES (startDate, endDate, priority, price, currency, brandId, productId) VALUES
  (parsedatetime('2020-06-14-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'),
  0, 35.5, 'EUR', 1, 35455),
  (parsedatetime('2020-06-14-15.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-06-14-18.30.00', 'yyyy-MM-dd-hh.mm.ss'),
  1, 25.45, 'EUR', 1, 35455),
  (parsedatetime('2020-06-15-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-06-15-11.00.00', 'yyyy-MM-dd-hh.mm.ss'),
  1, 30.5, 'EUR', 1, 35455),
  (parsedatetime('2020-06-15-16.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'),
  1, 38.95, 'EUR', 1, 35455);
  (parsedatetime('2020-06-25-06.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'),
  0, 35.00, 'EUR', 2, 35456);


DROP TABLE IF EXISTS BRANDS;

CREATE TABLE BRANDS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(20) DEFAULT NULL,
  description VARCHAR(200) DEFAULT NULL
);

INSERT INTO BRANDS (id, name, description) VALUES
  (1, 'ZARA', 'Zara'),
  (2, 'Stradivarius', 'Stradivarius');


DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(20) DEFAULT NULL,
  description VARCHAR(200) DEFAULT NULL
);

INSERT INTO PRODUCTS (id, name, description) VALUES
  (35455, 'Short', 'Zara short'),
  (35456, 'Shoes', 'Stradivarius shoes');