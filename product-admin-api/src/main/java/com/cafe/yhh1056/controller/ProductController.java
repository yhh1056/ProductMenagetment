package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * product controller
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> list() {
       return productService.getProducts();
    }


}
