package com.product_service.controller;

import com.product_service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

public interface ProductController {
    @PostMapping(value = "/products")
    ResponseEntity<?> add(Product product);

    @GetMapping(value = "/products")
    ResponseEntity<List<Product>> readAll();

    @GetMapping(value = "/products/{id}")
    ResponseEntity<Product> readById(Long id);

    @PutMapping(value = "/products/{id}")
    ResponseEntity<?> updateById(Long id, Product product);

    @DeleteMapping(value = "/products/{id}")
    ResponseEntity<?> removeById(Long id);

    @GetMapping(value = "/products/getTop3List")
    ResponseEntity<List<Product>> getTop3List();

    @GetMapping(value = "/products/filter")
    ResponseEntity<List<Product>> getFilter(@RequestParam(value = "word", required = false) String word,
                                            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                            @RequestParam(value = "page", required = false) Integer page);
}
