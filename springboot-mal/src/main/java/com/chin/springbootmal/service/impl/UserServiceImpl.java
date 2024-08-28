package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.UserDao;
import com.chin.springbootmal.dto.user.UserLoginRequest;
import com.chin.springbootmal.dto.user.UserRegisterRequest;
import com.chin.springbootmal.model.User;
import com.chin.springbootmal.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private static final  Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * 創建使用者
     * @param userRequest 創建使用者請求
     * @return Integer 使用者Id
     */
    @Override
    public Integer createUser(UserRegisterRequest userRequest) {
        //檢查email是否有人使用
        User user = userDao.getUserByEmail(userRequest.getEmail());
        if (user != null) {
            LOG.warn("email:{} 已經被userId:{} 使用", userRequest.getEmail(), user.getUserId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //將請求中的註冊密碼加密
        //Base64 ecyption Data code
//        try {
//            userRequest.setPassword(setBase64EncyptionPassword(userRequest.getPassword()));
//        }catch (UnsupportedEncodingException uex){
//            LOG.error("加密失敗:{}",uex.getMessage());
//        }
        //MD5 ecyption Data code
        userRequest.setPassword(setMd5EncyptionPassword(userRequest.getPassword()));
        LOG.info("執行UserService.createUser");
        return userDao.createUser(userRequest);
    }

    /**
     * 透過使用者Id取得使用者
     * @param userId 使用者Id
     * @return 使用者data
     */
    @Override
    public User getUserByUserId(Integer userId) {
        LOG.info("執行UserService.getUser");
        return userDao.getUserByUserId(userId);
    }

    /**
     * 登入使用者
     * @param userRequest 查詢使用者請求
     * @return Uder 使用者data
     */
    @Override
    public User login(UserLoginRequest userRequest) {
        String email = userRequest.getEmail();
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            LOG.warn("eamil:{}尚未註冊", email);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else {

        //Base64 decyption Data code
//            String dPassword = null;
//            try {
//                dPassword = setBase64DecyptionPassword(user.getPassword());
//                if (dPassword.equals(userRequest.getPassword().trim())){
//                    return user;
//                }else {
//                    LOG.warn("登入email:{}密碼不正確",email);
//                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//                }
//            }catch (UnsupportedEncodingException uex){
//                LOG.error("解密失敗:{}",uex.getMessage());
//            }
//            return user;

            //將使用者登入密碼加密後與DB password Data做核對
            String hashPassword = setMd5EncyptionPassword(userRequest.getPassword());
            if (user.getPassword().equals(hashPassword)){

                return user;
            }else {
                LOG.warn("登入email:{}密碼不正確",email);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    /**
     * 使用MD5雜湊值進行加密
     */
    private String setMd5EncyptionPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    /**
     * 使用Base64進行加密
     */
    private String setBase64EncyptionPassword(String password) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
    }

    /**
     * 使用Base64進行解密
     */
    private String setBase64DecyptionPassword(String password) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(password),"UTF-8");
    }
}
