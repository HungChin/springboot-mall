package com.chin.springbootmal.dao;


import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;

public interface UserDao {

    public Integer createUser(UserRegisterRequest userRequest);

    public User getUserByUserId(Integer userId);

    public  User getUserByEmail(String email);
}
