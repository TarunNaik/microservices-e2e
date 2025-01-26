package com.tsoft.ecomm.product.controller;

import com.tsoft.ecomm.product.dto.ProductRequest;
import com.tsoft.ecomm.product.dto.ProductResponse;
import com.tsoft.ecomm.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
