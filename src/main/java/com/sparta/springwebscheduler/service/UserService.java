package com.sparta.springwebscheduler.service;

import com.sparta.springwebscheduler.config.PasswordEncoder;
import com.sparta.springwebscheduler.dto.LoginRequestDto;
import com.sparta.springwebscheduler.dto.UserDto.UserRequestDto;
import com.sparta.springwebscheduler.dto.UserDto.UserResponseDto;
import com.sparta.springwebscheduler.entity.User;
import com.sparta.springwebscheduler.jwt.JwtUtil;
import com.sparta.springwebscheduler.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponseDto createUser(UserRequestDto userRequest, HttpServletResponse res) {
        String userName = userRequest.getUsername();
        String password = passwordEncoder.encode(userRequest.getPassword());

        // email 중복확인
        String email = userRequest.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // RequestDto -> Entity
        User user = new User(userRequest,password);
        //DB 저장
        User saveUser = userRepository.save(user);
        //token생성 및 쿠키
        String token = jwtUtil.createToken(saveUser.getId());
        jwtUtil.addJwtToCookie(token,res);
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

    public void login(LoginRequestDto loginRequest, HttpServletResponse res) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //사용자 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        //JWT생성 및 쿠키에 저장후 response 객체의 추가
        String token = jwtUtil.createToken(user.getId());
        jwtUtil.addJwtToCookie(token, res);
    }
}
