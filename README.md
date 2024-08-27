![header](https://capsule-render.vercel.app/api?type=waving&height=300&color=gradient&text=Spring%20Scheuler%20Version2)

# 🚀 STACK

Environment

![인텔리제이](   https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![깃허브](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![깃](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![POSTMAN](https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

Development

![자바](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SPRING BOOT](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![SQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

# ⚒️ API Table & Script
## [🖇️ Postman API Script](https://documenter.getpostman.com/view/37620136/2sA3s7hobk)

## 1 단계
### Schedule
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 일정 작성      | Post  |/api/schedule            |Body  | 등록정보
| 선택한 일정 조회 | Get   |/api/schedule/{schedule_id}|Query |단건 응답 정보
| 일정 목록 조회  | Get   |/api/schedule             |Body   |다건 응답 정보
| 선택한 일정 수정 | Put   |/api/schedule/{schedule_id}|Body  |수정 정보

## 2 단계
### Comment 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 댓글 작성      | Post  |/api/comment/{schedule_id}|Body  | 등록정보
| 선택한 댓글 조회 | Get   |/api/comment/{comment_id} |Query |단건 응답 정보
| 댓글 목록 조회  | Get   |/api/comment              |-   |다건 응답 정보
| 선택한 댓글 수정 | Put   |/api/comment/{comment_id}|Body  |수정 정보
| 선택한 댓글 삭제 | Put   |/api/comment/{comment_id} |-  |삭제된 comment_id

## 3 단계
### Schedule 변경사항 (일정 목록 조회 수정)
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 일정 목록 조회  | Get   |/api/schedule/?page=?|Param |다건 응답 정보

## 4 단계
### Schedule 변경사항 (일정 삭제 추가)
| 기능          | Method | URL               | Request| Response|
| ----         |:----:  |:----:             |:----:|:----:|
| 일정 삭제  |Delete   |/api/schedule/{id}|- |삭제된 schedule_id

## 5 단계
### Schedule 변경 사항 (담당 유저 배치 추가)
| 기능          | Method | URL                               | Request| Response|
| ----         |:----:  |:----:                             |:----:|:----:|
| 담당 유저 배치  |Put   |/api/schedule/{schedule_id}/{user_id}|Param|등록정보

### User 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 유저 생성      | Post  |/api/user                  |Body  | 등록정보
| 선택한 유저 조회 | Get   |/api/user/{user_id} |Query |단건 응답 정보
| 유저 목록 조회  | Get   |/api/user                   |-   |다건 응답 정보
| 선택한 유저 수정 | Put   |/api/user/{user_id}|Body  |수정 정보
| 선택한 유저 삭제 | Put   |/api/user/{user_id} |-  |삭제된 user_id

## 6 단계 & 7 단계
### 변경 사항 없음

## 8 단계 
### User (유저 생성 -> 회원가입 변경)
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 유저 회원 가입  | Post  |/api/user/signup          |Body  | 등록정보

### Login 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 로그인        | Post  |/api/login               |Body  | JwtToken반환

## 최종 
### Schedule
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 일정 작성      | Post  |/api/schedule            |Body  | 등록정보
| 선택한 일정 조회 | Get   |/api/schedule/{schedule_id}|Query |단건 응답 정보
| 일정 목록 조회  | Get   |/api/schedule/?page=?|Param |다건 응답 정보
| 선택한 일정 수정 | Put   |/api/schedule/{schedule_id}|Body  |수정 정보
| 일정 삭제  |Delete   |/api/schedule/{id}|- |삭제된 schedule_id
| 담당 유저 배치  |Put   |/api/schedule/{schedule_id}/{user_id}|Param|등록정보


### Comment 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 댓글 작성      | Post  |/api/comment/{schedule_id}|Body  | 등록정보
| 선택한 댓글 조회 | Get   |/api/comment/{comment_id} |Query |단건 응답 정보
| 댓글 목록 조회  | Get   |/api/comment              |-   |다건 응답 정보
| 선택한 댓글 수정 | Put   |/api/comment/{comment_id}|Body  |수정 정보
| 선택한 댓글 삭제 | Put   |/api/comment/{comment_id} |-  |삭제된 comment_id

### User 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 유저 회원 가입  | Post  |/api/user/signup          |Body  | 등록정보
| 선택한 유저 조회 | Get   |/api/user/{user_id} |Query |단건 응답 정보
| 유저 목록 조회  | Get   |/api/user                   |-   |다건 응답 정보
| 선택한 유저 수정 | Put   |/api/user/{user_id}|Body  |수정 정보
| 선택한 유저 삭제 | Put   |/api/user/{user_id} |-  |삭제된 user_id

### Login 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 로그인        | Post  |/api/login               |Body  | JwtToken반환


# 📚 ERD 
## 1 ~ 4단계
<img width="539" alt="Screenshot 2024-08-23 at 5 29 04 PM" src="https://github.com/user-attachments/assets/aa35271c-81e7-48d9-b830-178f62f01bae">

## 5단계 ~ 최종
<img width="424" alt="Screenshot 2024-08-27 at 3 16 47 PM" src="https://github.com/user-attachments/assets/511f44d2-143a-4cec-abc2-e3c6cd23d405">

# 📊 SQL 

    create table schedules
    (
        id          bigint auto_increment constraint `PRIMARY`
    			primary key,
        created_at  datetime(6)  null,
        modified_at datetime(6)  null,
        contents    varchar(500) not null,
        title       varchar(255) not null,
        user_id     bigint       not null
    );
    
    create table comment
    (
        comment_id  bigint auto_increment constraint `PRIMARY`
    			primary key,
        created_at  datetime(6)  null,
        modified_at datetime(6)  null,
        comment     varchar(255) not null,
        username    varchar(255) not null,
        schedule_id bigint       null,
        constraint schedule_id
            foreign key (schedule_id) references schedules (id)
    );
    
    create table users
    (
        user_id     bigint auto_increment constraint `PRIMARY`
    			primary key,
        created_at  datetime(6)  null,
        modified_at datetime(6)  null,
        email       varchar(255) null,
        username    varchar(255) null
    );
    
    create table storages
    (
        storage_id  bigint auto_increment constraint `PRIMARY`
    			primary key,
        schedule_id bigint null,
        user_id     bigint null,
        constraint schedule_id
            foreign key (schedule_id) references schedules (id),
        constraint user_id
            foreign key (user_id) references users (user_id)
    );







# 🌲 Repository 
    ├── HELP.md
    ├── build
    │   ├── classes
    │   │   └── java
    │   │       └── main
    │   │           └── com
    │   │               └── sparta
    │   │                   └── springwebscheduler
    │   │                       ├── AuthFilter
    │   │                       │   └── AuthFilter.class
    │   │                       ├── SpringWebSchedulerApplication.class
    │   │                       ├── config
    │   │                       │   └── PasswordEncoder.class
    │   │                       ├── controller
    │   │                       │   ├── CommentController.class
    │   │                       │   ├── LoginController.class
    │   │                       │   ├── SchedulerController.class
    │   │                       │   └── UserController.class
    │   │                       ├── dto
    │   │                       │   ├── ConsumerDto
    │   │                       │   │   ├── CommentRequestDto.class
    │   │                       │   │   └── CommentResponseDto.class
    │   │                       │   ├── LoginRequestDto.class
    │   │                       │   ├── LonginResponseDto.class
    │   │                       │   ├── PageResponseDto.class
    │   │                       │   ├── ScheduleDetailResponseDto.class
    │   │                       │   ├── ScheduleDto
    │   │                       │   │   ├── ScheduleRequestDto.class
    │   │                       │   │   └── ScheduleResponseDto.class
    │   │                       │   └── UserDto
    │   │                       │       ├── UserRequestDto.class
    │   │                       │       └── UserResponseDto.class
    │   │                       ├── entity
    │   │                       │   ├── Comment.class
    │   │                       │   ├── Schedule.class
    │   │                       │   ├── Storage.class
    │   │                       │   ├── Timestamped.class
    │   │                       │   ├── User.class
    │   │                       │   ├── UserRoleEnum$Authority.class
    │   │                       │   └── UserRoleEnum.class
    │   │                       ├── jwt
    │   │                       │   └── JwtUtil.class
    │   │                       ├── repository
    │   │                       │   ├── CommentRepository.class
    │   │                       │   ├── ScheduleRepository.class
    │   │                       │   ├── StorageRepository.class
    │   │                       │   └── UserRepository.class
    │   │                       └── service
    │   │                           ├── CommentService.class
    │   │                           ├── ScheduleService.class
    │   │                           └── UserService.class
    │   ├── generated
    │   │   └── sources
    │   │       ├── annotationProcessor
    │   │       │   └── java
    │   │       │       └── main
    │   │       └── headers
    │   │           └── java
    │   │               └── main
    │   ├── resources
    │   │   └── main
    │   │       ├── application.properties
    │   │       ├── static
    │   │       └── templates
    │   └── tmp
    │       └── compileJava
    │           ├── compileTransaction
    │           │   ├── backup-dir
    │           │   └── stash-dir
    │           └── previous-compilation-data.bin
    ├── build.gradle
    ├── gradle
    │   └── wrapper
    │       ├── gradle-wrapper.jar
    │       └── gradle-wrapper.properties
    ├── gradlew
    ├── gradlew.bat
    ├── settings.gradle
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── sparta
        │   │           └── springwebscheduler
        │   │               ├── AuthFilter
        │   │               │   └── AuthFilter.java
        │   │               ├── SpringWebSchedulerApplication.java
        │   │               ├── config
        │   │               │   └── PasswordEncoder.java
        │   │               ├── controller
        │   │               │   ├── CommentController.java
        │   │               │   ├── LoginController.java
        │   │               │   ├── SchedulerController.java
        │   │               │   └── UserController.java
        │   │               ├── dto
        │   │               │   ├── ConsumerDto
        │   │               │   │   ├── CommentRequestDto.java
        │   │               │   │   └── CommentResponseDto.java
        │   │               │   ├── LoginRequestDto.java
        │   │               │   ├── LonginResponseDto.java
        │   │               │   ├── PageResponseDto.java
        │   │               │   ├── ScheduleDetailResponseDto.java
        │   │               │   ├── ScheduleDto
        │   │               │   │   ├── ScheduleRequestDto.java
        │   │               │   │   └── ScheduleResponseDto.java
        │   │               │   └── UserDto
        │   │               │       ├── UserRequestDto.java
        │   │               │       └── UserResponseDto.java
        │   │               ├── entity
        │   │               │   ├── Comment.java
        │   │               │   ├── Schedule.java
        │   │               │   ├── Storage.java
        │   │               │   ├── Timestamped.java
        │   │               │   ├── User.java
        │   │               │   └── UserRoleEnum.java
        │   │               ├── jwt
        │   │               │   └── JwtUtil.java
        │   │               ├── repository
        │   │               │   ├── CommentRepository.java
        │   │               │   ├── ScheduleRepository.java
        │   │               │   ├── StorageRepository.java
        │   │               │   └── UserRepository.java
        │   │               └── service
        │   │                   ├── CommentService.java
        │   │                   ├── ScheduleService.java
        │   │                   └── UserService.java
        │   └── resources
        │       ├── application.properties
        │       ├── sql.mysql
        │       ├── static
        │       └── templates
        └── test
            └── java
                └── com
                    └── sparta
                        └── springwebscheduler
                            └── SpringWebSchedulerApplicationTests.java
    
    63 directories, 75 files





