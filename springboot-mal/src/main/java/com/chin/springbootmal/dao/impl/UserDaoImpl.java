package com.chin.springbootmal.dao.impl;

import com.chin.springbootmal.dao.UserDao;
import com.chin.springbootmal.dto.UserRequest;
import com.chin.springbootmal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRequest userRequest) {
        String sql = "INSERT INTO user(email,password,created_date,last_modified_date) "
                + "VALUES(:email,:password,:createdDate,:lastModifiedDate)";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", userRequest.getEmail());
        params.put("password", userRequest.getPassword());

        Date now = new Date();
        params.put("createdDate", now);
        params.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params),keyHolder);
        Integer userId = keyHolder.getKey().intValue();
        return userId;
    }

    @Override
    public User getUserByUserId(Integer userId) {
        String sql = "SELECT * FROM user WHERE user_id=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        User user = namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=:email";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
        return userList.stream().findFirst().orElse(null);
    }
}
