package com.project.projectbackculture.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projectbackculture.web.response.ErrorAuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    // Inyectamos ObjectMapper para convertir el record a JSON
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        ErrorAuthenticationResponse errorAuthenticationResponse;
        // Si la excepción es BadCredentialsException (credenciales incorrectas)
        if (authException != null) {
            errorAuthenticationResponse = new ErrorAuthenticationResponse(
                    "No autorizado",
                    "Credenciales incorrectas. Por favor, verifica tu usuario y contraseña.",
                    false
            );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            errorAuthenticationResponse = new ErrorAuthenticationResponse(
                    "No autorizado",
                    "Acesso negado.",
                    false
            );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorAuthenticationResponse));

    }
}
