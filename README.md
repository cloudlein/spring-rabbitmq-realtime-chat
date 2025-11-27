## Architecture Desain

```tree
com.demo.chatApp/
├── adapter/
│   ├── controller/
│   │   ├── AuthController.java
│   │   └── UserController.java
│   ├── mapper/
│   │   ├── AuthMapper.java
│   │   └── UserMapper.java
│   ├── repository/
│   │   ├── JpaUserRepository.java
│   │   └── UserRepositoryAdapter.java
│   ├── seeder/
│   │   └── UserSeeder.java
│   └── value/
│       └── AuthResult.java
├── common/
│   ├── api/
│   │   ├── ApiError.java
│   │   ├── ApiErrorCode.java
│   │   ├── ApiResponse.java
│   │   ├── ApiResponseFactory.java
│   │   ├── GlobalExceptionHandle.java
│   │   ├── Meta.java
│   │   ├── PaginationFactory.java
│   │   └── PaginationMeta.java
│   ├── entity/
│   │   └── BaseEntity.java
│   ├── exception/
│   │   ├── ApiException.java
│   │   ├── BadRequestException.java
│   │   ├── ResourceNotFoundException.java
│   │   └── UnauthorizedException.java
│   └── logging/
│       ├── McdFilter.java
│       └── RequestLoggingFilter.java
├── config/
│   └── SecurityConfig.java
├── domain/
│   ├── entity/
│   │   └── User.java
│   ├── enums/
│   │   └── UserRole.java
│   ├── repository/
│   │   └── UserRepository.java
│   └── service/
│       ├── impl/
│       │   ├── AuthServiceImpl.java
│       │   └── UserServiceImpl.java
│       ├── AuthService.java
│       ├── BaseService.java
│       └── UserService.java
├── dto/
│   ├── auth/
│   │   ├── LoginRequestDto.java
│   │   ├── LoginResponseDto.java
│   │   ├── RegisterRequestDto.java
│   │   └── UserInfoDto.java
│   └── user/
│       ├── UserRequestDto.java
│       ├── UserResponseDto.java
│       └── UserUpdateRequestDto.java
├── security/
│   ├── JwtAuthenticationFilter.java
│   ├── JwtProvider.java
│   ├── JwtUserDetailService.java
│   ├── SecurityErrorHandler.java
│   └── UserPrincipal.java
└── chatApplication.java
```

### Diagram 
```yaml
Client POST /api/chat/send
        |
        v
   ChatService (save message)
        |
        v
   RabbitMQ Producer
        |
        v
   RabbitMQ Exchange ---> Queue ---> ChatConsumer
                                    |
                                    v
                               WebSocket Server
                                    |
                                    v
                             Client receives realtime
```