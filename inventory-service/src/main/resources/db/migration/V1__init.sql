CREATE TABLE t_inventory (
  id BIGINT AUTO_INCREMENT NOT NULL,
   sku_code VARCHAR(255) NULL,
   quantity INT NULL,
   CONSTRAINT pk_t_inventory PRIMARY KEY (id)
);