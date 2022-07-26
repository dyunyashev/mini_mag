package com.web_shop.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product {
    private Long id;

    private String title;

    private BigDecimal price;

    private Integer views;
}
