package com.sparta.springwebscheduler.dto.UserDto;

import com.sparta.springwebscheduler.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
     Long id;
     String username;
     String email;
     LocalDateTime createAt;
     LocalDateTime modifiedAt;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
