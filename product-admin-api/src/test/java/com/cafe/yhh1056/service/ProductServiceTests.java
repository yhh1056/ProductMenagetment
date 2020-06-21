package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.Product;
import com.cafe.yhh1056.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * ProductServiceTests
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void getAllProducts() {
        List<Product> mockProducts = new ArrayList<>();

        mockProducts.add(Product.builder().id(1000L).name("beans").memo("memo").date("2020/6/12").price(12000L).build());
        mockProducts.add(Product.builder().id(2000L).name("powder").memo("memo").date("2020/6/12").price(6000L).build());

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.getAllProducts();

        assertThat(products.get(0).getName()).isEqualTo("beans");
        assertThat(products.get(1).getName()).isEqualTo("powder");
    }

    @Test
    void getProductInfo() {

        Product mockProduct = Product.builder().id(1000L).name("beans").memo("memo").date("2020/6/12").price(12000L).build();
        when(productRepository.findById(1000L)).thenReturn(Optional.of(mockProduct));

        Product product = productService.getProductInfo(1000L);

        assertAll(
                () -> assertEquals("beans", product.getName()),
                () -> assertEquals("memo", product.getMemo()),
                () -> assertEquals("2020/6/12", product.getDate()),
                () -> assertEquals(12000L, product.getPrice())
        );
    }

    @Test
    void createProduct() {
        Product mockProduct = Product.builder().id(1000L).name("beans").memo("memo").date("2020/6/12").price(12000L).build();

        when(productRepository.save(any())).thenReturn(mockProduct);

        Product result = productService.addProduct(mockProduct);

        assertAll(
                () -> assertThat(result.getId()).isEqualTo(1000L),
                () -> assertThat(result.getName()).isEqualTo("beans"),
                () -> assertThat(result.getMemo()).isEqualTo("memo"),
                () -> assertThat(result.getDate()).isEqualTo("2020/6/12"),
                () -> assertThat(result.getPrice()).isEqualTo(12000L)
        );
    }
}