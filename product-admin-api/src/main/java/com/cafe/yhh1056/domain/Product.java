package com.cafe.yhh1056.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 클레스 설명
 * <p>
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String date;           //todo 현재 시간으로 변경하기

    private Long price;

    private String memo;
}
