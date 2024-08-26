package com.sparta.springwebscheduler.controller;

import com.sparta.springwebscheduler.dto.LoginRequestDto;
import com.sparta.springwebscheduler.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final UserService userService;
    @PostMapping
    public void logIn(@RequestBody LoginRequestDto loginRequest, HttpServletResponse res){
        userService.login(loginRequest,res);
    }
}
