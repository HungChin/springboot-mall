package com.chin.springbootmal.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
