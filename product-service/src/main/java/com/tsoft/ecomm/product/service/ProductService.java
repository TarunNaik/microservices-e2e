package com.tsoft.ecomm.product.service;

import com.tsoft.ecomm.product.dto.ProductRequest;
import com.tsoft.ecomm.product.dto.ProductResponse;
import com.tsoft.ecomm.product.model.Product;
import com.tsoft.ecomm.product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
            .name(productRequest.name())
            .description(productRequest.description())
            .price(productRequest.price())
            .build();
        Product savedProduct = productRepository.save(product);
        log.info("Product created: {}", savedProduct);
        return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products");
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
            .toList();
    }
}
