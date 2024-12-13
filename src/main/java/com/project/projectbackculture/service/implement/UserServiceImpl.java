package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.exception.DuplicateEmailException;
import com.project.projectbackculture.exception.DuplicateUsernameException;
import com.project.projectbackculture.web.request.AuthLoginRequest;
import com.project.projectbackculture.web.request.EmailRequest;
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
import com.project.projectbackculture.utility.JwtUtils;
import lombok.RequiredArgsConstructor;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;


    @Override
    @Transactional
    public UserResponse save(NewUserRequest request) {

        //Valida la duplicacion de email y username
        validateUniqueConstraint(request);

        try {

            UserModel userModel = UserMapper.toModel(request, passwordEncoder);
            log.debug("Mapped NewUserRequest to UserModel successfully");

            //Asigna valores por defecto al usuario
            setUserWithDefaultValues(userModel);
            log.debug("Enriched user with default values");

            //Envia un mensjae a bienvendida al usuario nuevo
            EmailRequest reques = buildEmail(userModel);
            emailService.sendEmail(reques);

            // Guardar y transformar la respuesta
            UserModel savedUser = userRepository.save(userModel);
            log.info("User created successfully with email: {}", savedUser.getEmail());

            return UserMapper.toResponse(savedUser);

        } catch (Exception e) {
            log.error("Error while saving user with email: {}", request.email(), e);
            throw new CustomException("Error while create user: " + e.getMessage());
        }
    }

    @Override
    public UserResponse update(NewUserRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {}

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
    @Transactional
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
    @Transactional
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
    @Transactional
    public AuthLoginResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        log.info("Attempting login for username: {}", username);

        Authentication authentication = aunthenticateUser(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthLoginResponse(username, "Inicio de session exitoso",
                accessToken, true);
    }

    @Override
    public Set<RoleModel> setDefaultRoles() {
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

    @Override
    public void setUserWithDefaultValues(UserModel userModel) {
        userModel.setRegistrationDate(LocalDate.now());
        userModel.setEnable(true);
        userModel.setAccountNoLocked(true);
        userModel.setAccountNoExpired(true);
        userModel.setCredentialNoExpired(true);
        userModel.setRoles(setDefaultRoles());
    }

    @Override
    public void validateUniqueConstraint(NewUserRequest newUserRequest) {

        //Validar email duplicado
        if(userRepository.existsByEmail(newUserRequest.email())){
            log.warn("Attempt to register duplicate email: {}", newUserRequest.email());
            throw new DuplicateEmailException(newUserRequest.email());
        }

        //Validar username duplicado
        if(userRepository.existsByUsername(newUserRequest.username())){
            log.warn("Attempt to register duplicate username: {}", newUserRequest.username());
            throw new DuplicateUsernameException(newUserRequest.username());
        }


    }

    @Override
    public EmailRequest buildEmail(UserModel userModel) {

        final String subject = userModel.getUsername()
                .concat(" ¡Bienvenido/a a Inti Ruta! \uD83C\uDF1F");

        final String message = String.format(
                """
                        Hola %s,
                        
                        ¡Bienvenido/a a **Inti Ruta**! 🎉
                        
                        Estamos encantados de tenerte con nosotros en esta experiencia única para explorar la riqueza cultural y la belleza natural de Ayacucho, Perú. Con nuestra aplicación, puedes:
                        
                        🌄 Descubrir paisajes impresionantes.
                        🎨 Explorar la riqueza del arte y la cultura ayacuchana.
                        🏛️ Visitar lugares turísticos llenos de historia.
                        
                        Tu aventura comienza aquí. Prepárate para sumergirte en las tradiciones y maravillas de Ayacucho.
                        
                        Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarnos. Estamos aquí para asegurarnos de que tu experiencia sea inolvidable.
                        
                        ¡Bienvenido/a a nuestra comunidad cultural!
                        
                        Atentamente,
                        El equipo de Inti Ruta
                        
                        Arte ASCII:
                        
                             _/\\_      \s
                            /    \\   🌞 \s
                           /______\\      \s
                            |  |      \s
                        """,
                userModel.getUsername()
        );

        return EmailRequest.builder()
                .toUser(userModel.getEmail())
                .subject(subject)
                .message(message)
                .build();
    }


}
