package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.UserRequest;
import com.chin.springbootmal.model.User;

public interface UserService {

    public Integer createUser(UserRequest userRequest);
    public User getUser(Integer userId);

}
