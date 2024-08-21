package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.UserDao;
import com.chin.springbootmal.dto.UserRequest;
import com.chin.springbootmal.model.User;
import com.chin.springbootmal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private static final  Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer createUser(UserRequest userRequest) {
        //檢查email是否有人使用
        User user = userDao.getUserByEmail(userRequest.getEmail());
        if (user != null) {
            LOG.warn("email:{} 已經被userId:{} 使用", userRequest.getEmail(),user.getUserId() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LOG.info("執行UserService.createUser");
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUser(Integer userId) {
        LOG.info("執行UserService.getUser");
        return userDao.getUserByUserId(userId);
    }

}
