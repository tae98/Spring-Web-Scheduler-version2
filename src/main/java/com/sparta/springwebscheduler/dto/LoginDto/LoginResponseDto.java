package com.sparta.springwebscheduler.dto.LoginDto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String Token;

    public LoginResponseDto(String token){
        this.Token = token;
    }
}
