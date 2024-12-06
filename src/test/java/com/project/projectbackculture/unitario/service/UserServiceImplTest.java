package com.project.projectbackculture.unitario.service;

import com.project.projectbackculture.persistence.model.PermissionModel;
import com.project.projectbackculture.persistence.model.RoleEnum;
import com.project.projectbackculture.persistence.model.RoleModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.persistence.repository.UserRepository;
import com.project.projectbackculture.service.implement.UserServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testLoadUserByUsername_Success() {

        //Username para cargar
        String username = "bryacmy";

        //Creamos un usuario simulado con roles y permisos por defecto
        UserModel userModel = UserModel.builder()
                .username("pedro")
                .fullName("Brayan Cardenas Muñoz")
                .email("Brayan7br7@gmail.com")
                .password("password")
                .isEnable(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .build();

        //creamos permisos para el usuario
        PermissionModel read = PermissionModel.builder().name("READ").build();
        PermissionModel save = PermissionModel.builder().name("SAVE").build();

        //creamos un rol para el usuario
        RoleModel user = RoleModel.builder()
                .role(RoleEnum.USER)
                .permissions(Set.of(read, save))
                .build();

        //asignamos el rol al usuario
        userModel.setRoles(Set.of(user));

        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(userModel));

        //Metodo para cargar el usuario y probar
        UserDetails userDetails = userService.loadUserByUsername(username);

        //Asserts validar que los resultados sean correctos
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(3, userDetails.getAuthorities().size());

        //Valida si el rol ROLE_USER existen dentro de los authorities
        assertTrue(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) //Extrae el authority  roles y permisos
                .anyMatch("ROLE_USER"::equals));

        // Validad si el permiso READ existe dentro del los authorities
        assertTrue(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("READ"::equals));

        // Validad si el permiso SAVE existe dentro del los authorities
        assertTrue(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("SAVE"::equals));

        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String username = "usuarioNoExistente";
        Mockito.when(userRepository.findByUsername(username))
                        .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));

    }

}
