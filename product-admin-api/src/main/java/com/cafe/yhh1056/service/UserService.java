package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.domain.exception.EmailExistedException;
import com.cafe.yhh1056.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserInfo(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User memberRegister(String name, String email, String password) {
        Optional<User> overlap = userRepository.findByEmail(email);
        if(overlap.isPresent()) {
            throw new EmailExistedException(email);
        }

        String encodePassword = passwordEncoder().encode(password);

        User user = User.builder().name(name).email(email).password(encodePassword).build();

        return userRepository.save(user);
    }
}
