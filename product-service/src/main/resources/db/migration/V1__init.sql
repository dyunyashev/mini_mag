CREATE TABLE products (id serial, title varchar(100), price numeric(6,2), views int DEFAULT(0));

INSERT INTO products (title, price) VALUES
('Bread', 40.55),
('Milk', 80.66),
('Cheese', 200.11),
('Apple', 100.12),
('Meet', 210.1),
('Egg', 50.00),
('Cookie', 20.2),
('Porridge', 90.3),
('Cereal', 50.4),
('Fish', 150.5);
