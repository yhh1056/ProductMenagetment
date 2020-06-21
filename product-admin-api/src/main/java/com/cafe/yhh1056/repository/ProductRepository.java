package com.cafe.yhh1056.repository;

import com.cafe.yhh1056.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrudRepository
 *
 * author {yhh1056}
 * Create by {2020/06/21}
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
