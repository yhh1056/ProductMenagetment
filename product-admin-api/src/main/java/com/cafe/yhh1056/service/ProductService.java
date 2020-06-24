package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.domain.exception.ProductNotFoundException;
import com.cafe.yhh1056.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ProductService
 * <p>
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

        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String memo, Long price, Long quantity) {

        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.updateInfo(name, memo, price, quantity);
        }
        return product;
    }

    public Product updateProduct(Long id, Long quantity) {

        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.updateQuantity(quantity);
        }
        return product;
    }
}
