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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void overlapEmail() {
        User testUser = User.builder().name("testUser").email("tester@test.com").password("1234").build();
        User yhh1056 = User.builder().name("yhh1056").email("tester@test.com").password("1234").build();

//        userRepository.save(testUser);
//        when(userRepository.findByEmail(yhh1056.getEmail())).thenReturn(Optional.of(testUser));
//
//        userService.memberRegister(yhh1056.getName(), yhh1056.getEmail(), yhh1056.getPassword());

//        assertThrows(EmailExistedException.class, () -> userRepository.findByEmail(yhh1056.getEmail()));
        Throwable exception = assertThrows(
                EmailExistedException.class, () -> {
                    throw new EmailExistedException(yhh1056.getEmail());
                });
        assertEquals(exception.getMessage(),"tester@test.com already existed email. change your email");

    }
}