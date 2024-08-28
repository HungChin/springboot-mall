package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.user.UserLoginRequest;
import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;

public interface UserService {

    /**
     * 創建使用者
     * @param userRequest 創建使用者請求
     * @return Integer 使用者Id
     */
    public Integer createUser(UserRegisterRequest userRequest);

    /**
     * 透過使用者Id取得使用者
     * @param userId 使用者Id
     * @return 使用者data
     */
    public User getUserByUserId(Integer userId);

    /**
     * 登入使用者
     * @param userRequest 查詢使用者請求
     * @return Uder 使用者data
     */
    public User login(UserLoginRequest userRequest);
}
