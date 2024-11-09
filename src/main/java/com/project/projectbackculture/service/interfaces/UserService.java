package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.web.request.AuthLoginRequest;
import com.project.projectbackculture.web.request.NewUserRequest;
import com.project.projectbackculture.web.response.AuthLoginResponse;
import com.project.projectbackculture.web.response.UserResponse;
import org.springframework.security.core.Authentication;

public interface UserService extends ServiceGeneral<UserResponse, NewUserRequest, Integer> {


    Authentication auntenticateUser(String username, String password);
    AuthLoginResponse loginUser(AuthLoginRequest authLoginRequest);

}
