package com.product_service.repository.specification;

import com.product_service.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> titleContains(String word) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal val) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), val);
    }

    public static Specification<Product> priceLessThanOrEq(BigDecimal val) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), val);
    }
}
