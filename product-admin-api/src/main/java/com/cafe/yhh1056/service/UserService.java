package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * UserService
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 */

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
