package com.cafe.yhh1056.repository;

import com.cafe.yhh1056.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
