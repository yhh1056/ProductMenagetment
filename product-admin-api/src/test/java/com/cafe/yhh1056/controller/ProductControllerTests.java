package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * product controller test
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    void list() throws Exception {
        List<Product> products = new ArrayList<>();

        Product productByCoffee = Product.builder()
                .name("coffee beans")
                .date("2020/6/21")
                .memo("memo")
                .price(120000L)
                .build();
        products.add(productByCoffee);

        Product productByPowder = Product.builder()
                .name("vanilla powder")
                .date("2020/6/22")
                .memo("full")
                .price(18000L)
                .build();
        products.add(productByPowder);

        given(productService.getAllProducts()).willReturn(products);

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("coffee beans")))
                .andExpect(content().string(containsString("2020/6/21")))
                .andExpect(content().string(containsString("memo")))
                .andExpect(content().string(containsString("vanilla powder")))
                .andExpect(content().string(containsString("2020/6/22")))
                .andExpect(content().string(containsString("full")));
    }

    @Test
    void detail() throws Exception {
        Product productByCoffee = Product.builder()
                .id(1000L)
                .name("coffee beans")
                .date("2020/6/21")
                .memo("memo")
                .price(120000L)
                .build();

        given(productService.getProductInfo(1000L)).willReturn(productByCoffee);

        mvc.perform(get("/product/1000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("coffee beans")));
    }
}