package com.cafe.yhh1056.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * User domain
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 */

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    /**
     * 암호화
     */
    private String password;

    /**
     * 입일 만들기
     */
    private String date;
}
