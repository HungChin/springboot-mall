package com.chin.springbootmal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
