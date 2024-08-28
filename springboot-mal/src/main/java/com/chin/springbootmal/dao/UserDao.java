package com.chin.springbootmal.dao;


import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;

public interface UserDao {

    /**
     * 創建使用者
     * @param userRequest 創建使用者請求
     * @return Integer 使用者Id
     */
    public Integer createUser(UserRegisterRequest userRequest);

    /**
     * 透過使用者Id取得使用者
     * @param userId 使用者Id
     * @return User 使用者data
     */
    public User getUserByUserId(Integer userId);

    /**
     * 透過Email取得使用者
     * @param email 使用者信箱
     * @return 使用者data
     */
    public  User getUserByEmail(String email);
}
