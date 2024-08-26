package com.sparta.springwebscheduler.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LonginResponseDto {
    private String Token;

    public LonginResponseDto(String token){
        this.Token = token;
    }
}
