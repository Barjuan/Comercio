CREATE TABLE IF NOT EXISTS `brand` (
  `id` int PRIMARY KEY,
  `name` varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS `prices` (
  `price_list` int AUTO_INCREMENT PRIMARY KEY,
  `brand_id` int NOT NULL,
  `product_id` int NOT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP NOT NULL,
  `priority` int NOT NULL,
  `price` double NOT NULL,
  `curr` varchar(3) NOT NULL
);

ALTER TABLE `prices` ADD CONSTRAINT fk_prices_brand FOREIGN KEY (`brand_id`) REFERENCES `brand`(`id`) ON DELETE CASCADE;

INSERT INTO brand values (1,'ZARA');
INSERT INTO brand values (2,'OTHER');

INSERT INTO prices(price_list, brand_id, price, priority, product_id, end_date, start_date, curr) VALUES (1, 1, 35.50, 0, 35455, {ts '2020-12-31 23.59.59'}, {ts '2020-06-14 00.00.00'} ,'EUR');
INSERT INTO prices(price_list, brand_id, price, priority, product_id, end_date, start_date, curr) VALUES (2, 1, 25.45, 1, 35455, {ts '2020-06-14 18.30.00'}, {ts '2020-06-14 15.00.00'} ,'EUR');
INSERT INTO prices(price_list, brand_id, price, priority, product_id, end_date, start_date, curr) VALUES (3, 1, 30.50, 1, 35455, {ts '2020-06-15 11.00.00'}, {ts '2020-06-15 00.00.00'} ,'EUR');
INSERT INTO prices(price_list, brand_id, price, priority, product_id, end_date, start_date, curr) VALUES (4, 1, 38.95, 1, 35455, {ts '2020-12-31 23.59.59'}, {ts '2020-06-15 16.00.00'} ,'EUR');