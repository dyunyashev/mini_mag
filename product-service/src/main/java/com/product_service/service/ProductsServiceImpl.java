package com.product_service.service;

import com.product_service.model.Product;
import com.product_service.repository.ProductRepository;
import com.product_service.repository.specification.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getTop3List() {
        return productRepository.findTop3ByOrderByViewsDesc();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Product> getAllProductsWithPagingAndFiltering(String word, BigDecimal min, BigDecimal max, Integer page, Integer size) {
        if (page != null) {
            return productRepository.findAll(PageRequest.of(page,size)).getContent();
        }
        else {
            Specification<Product> filter = Specification.where(null);
            if (word != null) {
                filter = filter.and(ProductSpec.titleContains(word));
            }
            if (min != null) {
                filter = filter.and(ProductSpec.priceGreaterThanOrEq(min));
            }
            if (max != null) {
                filter = filter.and(ProductSpec.priceLessThanOrEq(max));
            }

            return productRepository.findAll(filter);
        }
    }

    @Override
    public void add(Product product) {
        if (product.getViews() == null) product.setViews(0);
        productRepository.save(product);
    }


}
