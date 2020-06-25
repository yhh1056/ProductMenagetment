package com.cafe.yhh1056.service;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.domain.exception.EmailExistedException;
import com.cafe.yhh1056.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
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

    @Test
    void getUserInfo() {
        User mockUser = User.builder().id(1000L).name("test").email("test@test.com").build();

        when(userRepository.findById(1000L)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserInfo(1000L);

        assertThat(user.getName()).isEqualTo("test");
    }

    @Test
    void registerUser() {
        User mockUser = User.builder().name("tester").email("test@test.com").password("test123").build();

        when(userRepository.save(any())).thenReturn(mockUser);

        User user = userService.memberRegister("mock", "mock", "mock");

        assertThat(user.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void existedEmail() {
        User yhh1056 = User.builder().name("yhh1056").email("tester@test.com").password("1234").build();

        Throwable exception = assertThrows(
                EmailExistedException.class, () -> {
                    throw new EmailExistedException(yhh1056.getEmail());
                });
        assertEquals(exception.getMessage(), "tester@test.com already existed email. change your email");
    }


}