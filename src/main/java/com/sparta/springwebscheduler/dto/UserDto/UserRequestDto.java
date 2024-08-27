package com.sparta.springwebscheduler.dto.UserDto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    @Email
    private String email;
    private String password;
    private boolean admin = false;
    private String adminToken;
}
