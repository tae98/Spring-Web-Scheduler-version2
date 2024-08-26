package com.sparta.springwebscheduler.controller;

import com.sparta.springwebscheduler.dto.UserDto.UserRequestDto;
import com.sparta.springwebscheduler.dto.UserDto.UserResponseDto;
import com.sparta.springwebscheduler.entity.User;
import com.sparta.springwebscheduler.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequest, HttpServletResponse res){
        return userService.createUser(userRequest,res);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequest){
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public Long deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }


}
