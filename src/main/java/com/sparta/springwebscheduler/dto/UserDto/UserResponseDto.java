package com.sparta.springwebscheduler.dto.UserDto;

import com.sparta.springwebscheduler.entity.User;
import com.sparta.springwebscheduler.entity.UserRoleEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
     private Long id;
     private String username;
     private String email;
     private LocalDateTime createAt;
     private LocalDateTime modifiedAt;
     private UserRoleEnum role;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
        this.role = user.getRole();
    }
}
