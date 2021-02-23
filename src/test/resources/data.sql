DROP TABLE IF EXISTS item_details;
CREATE TABLE item_details
(
   id INT AUTO_INCREMENT PRIMARY KEY,
   item_no VARCHAR (500) NOT NULL,
   name VARCHAR (250) NOT NULL,
   description VARCHAR (500) NOT NULL,
   price DOUBLE NOT NULL,
   status VARCHAR (50) NOT NULL default 'Available',
   no_of_views INT NOT NULL default 0,
   no_of_items_in_stock INT NOT NULL
);
INSERT INTO item_details
(
   item_no,
   name,
   description,
   price,
   no_of_items_in_stock
)
VALUES
(
   'REUNI456',
   'Reebok Sports Shoes',
   'Reebok sports shoes XL size & Unisex',
   499,
   15
);
INSERT INTO item_details
(
   item_no,
   name,
   description,
   price,
   no_of_items_in_stock
)
VALUES
(
   'ADME80034',
   'Adidas Sports Jacket',
   'Adidas Sports Jacket S|M|L|XL for Men',
   199,
   2
);
INSERT INTO item_details
(
   item_no,
   name,
   description,
   price,
   no_of_items_in_stock
)
VALUES
(
   'ADWO96884',
   'Adidas Sports Jacket',
   'Adidas Sports Jacket S|M|L|XL for Women',
   199,
   44
);