package com.chin.springbootmal.dao;


import com.chin.springbootmal.dto.UserRequest;
import com.chin.springbootmal.model.User;

public interface UserDao {

    public Integer createUser(UserRequest userRequest);

    public User getUserByUserId(Integer userId);

    public  User getUserByEmail(String email);
}
