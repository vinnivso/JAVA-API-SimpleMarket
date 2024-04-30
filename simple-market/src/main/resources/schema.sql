CREATE TABLE product (
  id UUID NOT NULL,
   name VARCHAR(255),
   brand VARCHAR(255),
   quantity INT,
   price DOUBLE PRECISION,
   CONSTRAINT pk_product PRIMARY KEY (id)
);