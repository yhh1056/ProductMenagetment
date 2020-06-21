package com.cafe.yhh1056.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void list() throws Exception {
        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("coffee beans")));

    }

}