package com.project.projectbackculture;

import com.project.projectbackculture.persistence.model.PermissionModel;
import com.project.projectbackculture.persistence.model.RoleEnum;
import com.project.projectbackculture.persistence.model.RoleModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class ProjectBackCultureApplication {

    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;
    @Value("${admin.email}")
    private String email;


    public static void main(String[] args) {
        SpringApplication.run(ProjectBackCultureApplication.class, args);
    }

    @Bean
    CommandLineRunner init (UserRepository userRepository, PasswordEncoder encoder){

        return args -> {

            //Permisos por defecto para un ADMIN
            final PermissionModel CREATE = PermissionModel.builder().name("CREATE").build();
            final PermissionModel REPORT = PermissionModel.builder().name("REPORT").build();

            //Rol de ADMIN
            final RoleModel ADMIN = RoleModel.builder().role(RoleEnum.ADMIN)
                    .permissions(Set.of(CREATE, REPORT)).build();

            final UserModel USER_ADMIN = UserModel.builder().username("admin")
                    .username(username)
                    .fullName("Brayan Cardenas Muñoz")
                    .email(email)
                    .password(encoder.encode(password))
                    .registrationDate(LocalDate.now())
                    .isEnable(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(ADMIN)).build();

            //userRepository.save(USER_ADMIN);
        };
    }

}
