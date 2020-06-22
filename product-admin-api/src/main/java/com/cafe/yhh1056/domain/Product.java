package com.cafe.yhh1056.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * Product
 *
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

    @NotNull
    private String name;

    /**
     *  현재 시간으로 변경하기
     */
    private String date;

    private Long price;

    private String memo;

    @Min(0)
    @NotNull
    private Long quantity;

    public void updateInfo(String name, String memo, Long price) {
        this.name = name;
        this.memo = memo;
        this.price = price;
    }
}
