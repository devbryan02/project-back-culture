package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.web.request.AuthLoginRequest;
import com.project.projectbackculture.web.request.NewUserRequest;
import com.project.projectbackculture.web.response.AuthLoginResponse;
import com.project.projectbackculture.web.response.UserResponse;
import com.project.projectbackculture.mapper.UserMapper;
import com.project.projectbackculture.persistence.model.PermissionModel;
import com.project.projectbackculture.persistence.model.RoleEnum;
import com.project.projectbackculture.persistence.model.RoleModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.persistence.repository.UserRepository;
import com.project.projectbackculture.service.interfaces.UserService;
import com.project.projectbackculture.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse save(NewUserRequest request) {

        UserModel userModel = UserMapper.toModel(request, passwordEncoder);
        log.info("mapping requestUser to userModel");

        //Setear valores por defecto
        final Set<RoleModel> ROLE_USER_DEFAULT = GET_DEFAULT_ROLES();
        userModel.setRegistrationDate(LocalDate.now());
        userModel.setEnable(true);
        userModel.setAccountNoLocked(true);
        userModel.setAccountNoExpired(true);
        userModel.setCredentialNoExpired(true);
        userModel.setRoles(ROLE_USER_DEFAULT);
        log.info("Saving default data user");

        UserModel savedUser = userRepository.save(userModel);
        log.info("saved user to database");

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse update(NewUserRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<UserResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserResponse> findById(Integer id) {
        Optional<UserModel> optionalUserById = userRepository.findById(id);
        log.info("find user by Id {}", optionalUserById);
        return optionalUserById.map(UserMapper::toResponse);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Starting loadUserByUsername {}", username);

        //Consultar a la base datos por username
        UserModel userModel =  userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Seteo de roles de usuario a SimpleGrantedAuthority
        userModel.getRoles().forEach(role -> authorities.add(
                new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

        // Seteo de permisos de usuarios a SimpleGrantedAuthority
        userModel.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(
                        new SimpleGrantedAuthority(permission.getName())));

        log.info("Authorities assigned for user {}: {}", userModel.getUsername(), authorities);

        return new User(
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.isEnable(),
                userModel.isAccountNoExpired(),
                userModel.isAccountNoLocked(),
                userModel.isCredentialNoExpired(),
                authorities);
    }

    @Override
    public Authentication aunthenticateUser(String username, String password) {

        log.info("Starting authentication for username: {}", username);

        UserDetails userDetails = loadUserByUsername(username);

        if (userDetails == null)
            throw new BadCredentialsException("Invalid username or password");

        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Invalid password");


        log.info("Authentication successful for username: {}", username);
        return new UsernamePasswordAuthenticationToken(
                username,
                password,
                userDetails.getAuthorities());
    }

    @Override
    public AuthLoginResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        log.info("Attempting login for username: {}", username);

        Authentication authentication = aunthenticateUser(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthLoginResponse(username, "User logged in successfully",
                accessToken, true);
    }

    public final Set<RoleModel> GET_DEFAULT_ROLES() {

        //Permisos por defecto para usuarios
        final PermissionModel READ = PermissionModel.builder().name("READ").build();
        final PermissionModel SAVE = PermissionModel.builder().name("SAVE").build();

        //Rol de usuario por defecto
        final RoleModel USER = RoleModel.builder()
                .role(RoleEnum.USER)
                .permissions(Set.of(READ, SAVE))
                .build();
        return Set.of(USER);
    }
}
