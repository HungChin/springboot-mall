package com.chin.springbootmal.controller;

import com.chin.springbootmal.dto.UserRequest;
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

    @PostMapping(value = "/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequest userRequest) {
        Integer userId = userService.createUser(userRequest);
        User user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
