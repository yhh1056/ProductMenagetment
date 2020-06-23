package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.domain.exception.ProductNotFoundException;
import com.cafe.yhh1056.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * product controller test
 * <p>
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void list() throws Exception {
        List<Product> products = new ArrayList<>();

        Product productByCoffee = Product.builder()
                .name("coffee beans")
                .date("2020/6/21")
                .memo("memo")
                .price(120000L)
                .quantity(12L)
                .build();
        products.add(productByCoffee);

        Product productByPowder = Product.builder()
                .name("vanilla powder")
                .date("2020/6/22")
                .memo("full")
                .price(18000L)
                .quantity(6L)
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

    @Test
    void detailWithNotExisted() throws Exception {
        given(productService.getProductInfo(404L)).willThrow(new ProductNotFoundException(404L));

        mvc.perform(get("/product/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not Found"));
    }

    @Test
    void create() throws Exception {
        Product mockProduct = Product.builder()
                .id(1000L)
                .name("coffee beans")
                .date("2020/6/21")
                .memo("memo")
                .quantity(6L)
                .price(120000L).build();
        given(productService.addProduct(any())).willReturn(mockProduct);

        mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockProduct)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/products/1000"));
    }

    @Test
    void update() throws Exception {
        Product updateProduct = Product.builder()
                .id(1000L)
                .name("deCoffee beans")
                .memo("x")
                .date("2020/6/21")
                .price(130000L).build();

        given(productService.updateProduct(1000L, "deCoffee beans", "x", 130000L, 12L))
                .willReturn(updateProduct);

        mvc.perform(patch("/product/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProduct)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).updateProduct(any(), any(), any(), any(), any());
    }

    @Test
    void updateQuantity() throws Exception {
        Product updateProduct = Product.builder()
                .id(1000L)
                .name("deCoffee beans")
                .date("2020/6/21")
                .memo("x")
                .quantity(12L)
                .price(130000L).build();

        given(productService.updateProduct(1000L,12L))
                .willReturn(updateProduct);

        mvc.perform(patch("/product/1000/quantity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProduct)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).updateProduct(any(), any());
    }
}