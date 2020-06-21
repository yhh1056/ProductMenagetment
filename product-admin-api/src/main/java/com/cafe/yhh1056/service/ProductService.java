package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ProductService
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@Service
@Transactional
public class ProductService {

   private ProductRepository productRepository;

   @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductInfo(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
