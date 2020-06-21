package com.cafe.yhh1056.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 클레스 설명
 * <p>
 * author {yhh1056}
 * Create by {2020/06/21}
 */
class ProductTests {

    @Test
    void create() {
        List<Product> products = new ArrayList<>();

        Product product = Product.builder().name("원두").build();

        products.add(product);

        assertThat(products.get(0).getName(), is("원두"));
    }

}