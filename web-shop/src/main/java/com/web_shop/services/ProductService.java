package com.web_shop.services;

import com.web_shop.entites.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("product-service-eureka-client")
public interface ProductService {
    @PostMapping(value = "/products")
    ResponseEntity<?> add(@RequestBody Product product);

    @GetMapping(value = "/products")
    List<Product> readAll();

    @GetMapping(value = "/products/{id}")
    Product readById(@PathVariable(name = "id") Long id);

    @PutMapping(value = "/products/{id}")
    ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody Product product);

    @DeleteMapping(value = "/products/{id}")
    ResponseEntity<?> removeById(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/products/getTop3List")
    List<Product> getTop3List();

    @GetMapping(value = "/products/filter")
    List<Product> getFilter(@RequestParam(value = "word", required = false) String word,
                                            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                            @RequestParam(value = "page", required = false) Integer page);
}
