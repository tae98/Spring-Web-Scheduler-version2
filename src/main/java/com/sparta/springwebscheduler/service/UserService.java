package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.dto.UserDto.UserRequestDto;
import com.sparta.springwebscheduler.dto.UserDto.UserResponseDto;
import com.sparta.springwebscheduler.entity.User;
import com.sparta.springwebscheduler.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto userRequest) {
        // RequestDto -> Entity
        User user = new User(userRequest);
        //DB 저장
        User saveUser = userRepository.save(user);
        //Entity -> ResponseDto
        UserResponseDto userResponse = new UserResponseDto(saveUser);
        return userResponse;
    }

    public List<User> getUserList() {
        return userRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public User updateUser(Long id, UserRequestDto userRequest) {
        User user = getUserById(id);
        user.updateUser(userRequest);
        return user;
    }

    public Long deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
        return id;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 유저는 존재하지 않습니다.")
        );
    }
}
