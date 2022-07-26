package com.product_service.service;

import com.product_service.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsService {
    List<Product> getAllProducts();

    List<Product> getAllProductsWithPagingAndFiltering(String word, BigDecimal min, BigDecimal max, Integer page, Integer size);

    void add(Product product);

    Product getById(Long id);

    void removeById(Long id);

    List<Product> getTop3List();
}
