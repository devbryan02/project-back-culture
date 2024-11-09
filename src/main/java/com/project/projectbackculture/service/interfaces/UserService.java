package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.controller.request.AuthLoginRequest;
import com.project.projectbackculture.controller.request.NewUserRequest;
import com.project.projectbackculture.controller.response.AuthLoginResponse;
import com.project.projectbackculture.controller.response.UserResponse;
import org.springframework.security.core.Authentication;

public interface UserService extends ServiceGeneral<UserResponse, NewUserRequest, Integer> {


    Authentication auntenticateUser(String username, String password);
    AuthLoginResponse loginUser(AuthLoginRequest authLoginRequest);

}
