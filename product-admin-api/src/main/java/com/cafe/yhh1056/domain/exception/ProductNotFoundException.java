package com.cafe.yhh1056.domain.exception;

/**
 * ProductNotFoundException
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("not fount product >>> " + id);
    }
}
