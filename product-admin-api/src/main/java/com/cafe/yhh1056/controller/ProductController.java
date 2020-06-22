package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

       return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product detail(@PathVariable Long id) {

        return productService.getProductInfo(id);
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@Valid @RequestBody Product resource) throws URISyntaxException {
        Product product = productService.addProduct(
                Product.builder()
                        .name(resource.getName())
                        .memo(resource.getMemo())
                        .date(resource.getDate())
                        .price(resource.getPrice())
                        .quantity(resource.getQuantity())
                        .build());

        String url = "/products/" + product.getId();

        return ResponseEntity.created(new URI(url)).body("{추가 완}");
    }

    @PatchMapping("product/{id}")
    public String update(@PathVariable Long id,
                         @Valid @RequestBody Product resource) {
        String name = resource.getName();
        String memo = resource.getMemo();
        Long price = resource.getPrice();
        Long quantity = resource.getQuantity();
        productService.updateProduct(id, name, memo, price, quantity);
        return "{수정 완료}";
    }

    /**
     * 직원 기
     */
    @PatchMapping("product/{id}/quantity")
    public String updateQuantity(@PathVariable Long id,
                         @Valid @RequestBody Product resource) {
        Long quantity = resource.getQuantity();
        productService.updateProduct(id, quantity);
        return "{수정 완료}";
    }
}
