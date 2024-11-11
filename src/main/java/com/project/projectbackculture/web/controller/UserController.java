package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.UserServiceImpl;
import com.project.projectbackculture.web.request.NewUserRequest;
import com.project.projectbackculture.web.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        UserResponse userResponse = userService.save(newUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }


}

