package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * product controller
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> list() {
        List<Product> products = new ArrayList<>();

        Product product = Product.builder().name("coffee beans").build();

        products.add(product);

        return products;
    }


}
