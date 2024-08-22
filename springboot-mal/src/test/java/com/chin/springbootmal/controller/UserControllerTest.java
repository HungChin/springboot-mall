package com.chin.springbootmal.controller;

import com.chin.springbootmal.dao.UserDao;
import com.chin.springbootmal.dao.impl.UserDaoImpl;
import com.chin.springbootmal.dto.user.UserLoginRequest;
import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.AbstractDocument;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserDao userDao;

    @Transactional
    @Test
    public void registerSuccess() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@test.com");
        userRegisterRequest.setPassword("a123456");

        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        User user  = userDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(userRegisterRequest.getPassword(), user.getPassword());

    }

    @Test
    public void registerEmailRepetRegister() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@test.com");
        userRegisterRequest.setPassword("a123456");

        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());

    }

    @Test
    public void loginSuccess() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@test.com");
        userRegisterRequest.setPassword("a123456");

        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("test@test.com");
        userLoginRequest.setPassword("a123456");
        String json2 = objectMapper.writeValueAsString(userLoginRequest);
        requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());

    }
}