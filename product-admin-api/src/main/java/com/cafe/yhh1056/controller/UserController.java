package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 *
 * 유저 가져오기, 유저 등록기능, 유저 삭제 (등급 낮춰서 활동 할 수없게)
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        return userService.getAllUsers();
    }
}
