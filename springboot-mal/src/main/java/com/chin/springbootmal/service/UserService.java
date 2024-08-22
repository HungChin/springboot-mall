package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.user.UserLoginRequest;
import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;

public interface UserService {

    public Integer createUser(UserRegisterRequest userRequest);
    public User getUserByUserId(Integer userId);
    public User login(UserLoginRequest userRequest);
}
