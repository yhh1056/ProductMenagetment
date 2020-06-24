package com.cafe.yhh1056.controller;

import com.cafe.yhh1056.domain.User;
import com.cafe.yhh1056.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * user controller test code
 *
 * author {yhh1056}
 * Create by {2020/06/23}
 */
@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUser() throws Exception {
        List<User> userList = new ArrayList<>();
        User testUser = User.builder().name("testUser").email("tester@test.com").build();
        User yhh1056 = User.builder().name("yhh1056").email("yhh1056@test.com").build();

        userList.add(testUser);
        userList.add(yhh1056);

        when(userService.getAllUsers()).thenReturn(userList);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("testUser")))
                .andExpect(content().string(containsString("tester@test.com")))
                .andExpect(content().string(containsString("yhh1056")))
                .andExpect(content().string(containsString("yhh1056@test.com")));
    }

    @Test
    void registerUser() throws Exception {
        User mockUser = User.builder().id(1000L).name("tester").email("tester@test.com").password("test123").build();

        when(userService.memberRegister(any(), any(), any())).thenReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isCreated())
                .andExpect(content().string("success register"))
                .andDo(print());
    }

    @Test
    void registerUserWithExistEmail() throws Exception {
        User mockUser = User.builder().id(1000L).name("tester").email("tester@test.com").password("test123").build();

        when(userService.memberRegister("yhh1056", "tester@test.com", "test123")).thenReturn(mockUser);

        mvc.perform(post("/users"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}