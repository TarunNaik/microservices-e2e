package com.tsoft.ecomm.product.repo;

import com.tsoft.ecomm.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
