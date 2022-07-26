package com.product_service.service.aop;

import com.product_service.model.Product;
import com.product_service.service.ProductsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class viewsProduct {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Before("execution(* readById(..))")
    public void aopShowOneProduct(JoinPoint joinPoint) {
        Long id = (Long) joinPoint.getArgs()[0];
        Product product = productsService.getById(id);
        product.setViews(product.getViews() + 1);
        productsService.add(product);
    }
}
