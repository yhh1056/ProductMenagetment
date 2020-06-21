package com.cafe.yhh1056.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ProductTest
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */
class ProductTests {

    @Test
    void create() {
        List<Product> products = new ArrayList<>();

        Product product = Product.builder().name("원두").date("2020/6/21").memo("메모").price(120000L).build();
        products.add(product);

        assertAll(
                () -> assertEquals("원두", product.getName()),
                () -> assertEquals("2020/6/21", product.getDate()),
                () -> assertEquals("메모", product.getMemo()),
                () -> assertEquals(120000L, product.getPrice())
        );
    }

}