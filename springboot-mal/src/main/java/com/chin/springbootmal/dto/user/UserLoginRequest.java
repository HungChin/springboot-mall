package com.chin.springbootmal.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {

    /** 使用者信箱 */
    @NotBlank
    @Email
    private String email;

    /** 使用者密碼 */
    @NotBlank
    private String password;

}
