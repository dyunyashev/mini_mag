package com.product_service.controller;

import com.product_service.model.Product;
import com.product_service.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    @PostMapping(value = "/products")
    public ResponseEntity<?> add(@RequestBody Product product) {
        productsService.add(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> readAll() {
        final List<Product> products = productsService.getAllProducts();

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> readById(@PathVariable(name = "id") Long id) {
        final Product product = productsService.getById(id);

        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        product.setId(id);
        productsService.add(product);
        final boolean updated = true;

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> removeById(@PathVariable(name = "id") Long id) {
        productsService.removeById(id);
        final boolean deleted = true;

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    @GetMapping(value = "/products/getTop3List")
    public ResponseEntity<List<Product>> getTop3List() {
        final List<Product> products = productsService.getTop3List();

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping(value = "/products/filter")
    public ResponseEntity<List<Product>> getFilter(String word, BigDecimal minPrice, BigDecimal maxPrice, Integer page) {
        final List<Product> products = productsService.getAllProductsWithPagingAndFiltering(word,minPrice,maxPrice,page,2);

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
