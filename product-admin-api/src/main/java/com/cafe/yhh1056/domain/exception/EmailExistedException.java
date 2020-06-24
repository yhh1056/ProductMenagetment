package com.cafe.yhh1056.domain.exception;

/**
 * email exception handler
 *
 * author {yhh1056}
 * Create by {2020/06/24}
 */
public class EmailExistedException extends RuntimeException {
    public EmailExistedException(String email) {
        super(email + " already existed email. change your email");
    }
}
