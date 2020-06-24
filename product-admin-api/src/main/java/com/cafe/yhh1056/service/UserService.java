package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.domain.exception.EmailExistedException;
import com.cafe.yhh1056.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public User memberRegister(String name, String email, String password) {
        /**
         * 가입부분 이미 존재하는 이메일이 있을경우 예외처
         */
        Optional<User> overlap = userRepository.findByEmail(email);
        if(overlap.isPresent()) {
            throw new EmailExistedException(email);
        }

        User user = User.builder().name(name).email(email).password(password).build();

        return userRepository.save(user);
    }
}
