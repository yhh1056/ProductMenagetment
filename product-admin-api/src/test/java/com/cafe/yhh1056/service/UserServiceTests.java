package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

/**
 * UserService test code
 * <p>
 * author {yhh1056}
 * Create by {2020/06/23}
 */

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void getAllUsers() {
        List<User> mockUserList = new ArrayList<>();
        User testUser = User.builder().name("testUser").email("tester@test.com").date("2020/6/23").build();
        User yhh1056 = User.builder().name("yhh1056").email("yhh1056@test.com").date("2017/12/19").build();

        mockUserList.add(testUser);
        mockUserList.add(yhh1056);

        when(userRepository.findAll()).thenReturn(mockUserList);

        List<User> userList = userService.getAllUsers();

        assertAll(
                () -> assertThat(userList.get(0).getName()).isEqualTo("testUser"),
                () -> assertThat(userList.get(1).getName()).isEqualTo("yhh1056")
        );
    }
}