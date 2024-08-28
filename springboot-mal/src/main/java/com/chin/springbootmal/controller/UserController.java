package com.chin.springbootmal.controller;

import com.chin.springbootmal.dto.user.UserLoginRequest;
import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;
import com.chin.springbootmal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 使用者註冊
     * @param userRequest 註冊使用者請求
     * @return ResponseEntity<> User 使用者data
     */
    @PostMapping(value = "/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRequest) {
        Integer userId = userService.createUser(userRequest);
        User user = userService.getUserByUserId(userId);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     *
     * @param userRequest 登入使用者請求
     * @return ResponseEntity<> User 使用者data
     */
    @PostMapping(value="/user/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userRequest){
        User user = userService.login(userRequest);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
