package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.RoleModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.web.request.AuthLoginRequest;
import com.project.projectbackculture.web.request.EmailRequest;
import com.project.projectbackculture.web.request.NewUserRequest;
import com.project.projectbackculture.web.response.AuthLoginResponse;
import com.project.projectbackculture.web.response.UserResponse;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface UserService extends ServiceGeneral<UserResponse, NewUserRequest, Integer> {

    Authentication aunthenticateUser(String username, String password);
    AuthLoginResponse loginUser(AuthLoginRequest authLoginRequest);
    Set<RoleModel> setDefaultRoles();
    void setUserWithDefaultValues(UserModel userModel);
    void validateUniqueConstraint(NewUserRequest newUserRequest);
    EmailRequest buildEmail(UserModel userModel);


}
