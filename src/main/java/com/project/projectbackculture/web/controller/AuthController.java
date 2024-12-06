package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.UserServiceImpl;
import com.project.projectbackculture.web.request.AuthLoginRequest;
import com.project.projectbackculture.web.request.NewUserRequest;
import com.project.projectbackculture.web.response.AuthLoginResponse;
import com.project.projectbackculture.web.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        UserResponse userResponse = userService.save(newUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthLoginResponse> loginUser(
            @RequestBody @Valid AuthLoginRequest authLoginRequest) {
        AuthLoginResponse authLoginResponse = userService.loginUser(authLoginRequest);
        return new ResponseEntity<>(authLoginResponse, HttpStatus.OK);
    }
}
