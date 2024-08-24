package com.sparta.springwebscheduler.entity;

import com.sparta.springwebscheduler.dto.UserDto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;
    String username;
    String email;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Storage> storagesList = new ArrayList<>();

    public User(UserRequestDto userRequest){
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
    }

    public void updateUser(UserRequestDto userRequest){
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
    }
}
