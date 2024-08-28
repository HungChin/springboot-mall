package com.chin.springbootmal.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

    /** 使用者信箱 */
    @NotBlank
    private String email;

    /** 使用者密碼 */
    @NotBlank
    private String password;

}
