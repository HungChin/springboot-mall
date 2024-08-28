package com.chin.springbootmal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    /** 使用者Id */
    private int userId;

    /** 使用者信箱 */
    @JsonProperty("e_mail")
    private String email;

    /** 使用者密碼 */
    @JsonIgnore
    private String password;

    /** 使用者建立日期 */
    private Date createdDate;

    /** 使用者最後修改日期 */
    private Date lastModifiedDate;

}
