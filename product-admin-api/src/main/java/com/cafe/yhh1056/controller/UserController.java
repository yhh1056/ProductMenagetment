package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * UserController
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 * 유저 모두 가져오기 v
 * 유저 등록기능 v
 * 유저 정보 변경
 * 유저 삭제 (등급 낮춰서 활동 할 수없게)
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User detail(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        User user = User.builder()
                .name(resource.getName())
                .email(resource.getEmail())
                .password(resource.getPassword())
                .build();

        userService.memberRegister(user.getName(), user.getEmail(), user.getPassword());

        String uri = "user/" + user.getId();
        return ResponseEntity.created(new URI(uri)).body("success register");
    }


}
