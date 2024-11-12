package com.project.projectbackculture.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Maneja las excepciones de argumentos invalidos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex,
                                                           WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ApiError apiError = new ApiError(
                request.getDescription(false),
                "Error de validacion",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                errors
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    //Maneja una exception de email duplicada
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiError> handleDuplicateEmail(DuplicateEmailException ex,
                                                         WebRequest request) {
        ApiError apiError = new ApiError(
                request.getDescription(false),
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    // Maneja una exception de usuario duplicado
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ApiError> handleDuplicateUsername(DuplicateUsernameException ex,
                                                            WebRequest request) {
        ApiError apiError = new ApiError(
                request.getDescription(false),
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    // Maneja un exception en general
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleCustomException(CustomException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                request.getDescription(false),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Manejar exception de ejecucion
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                request.getDescription(false),
                "Ocurrió un error inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja excepciones relacionadas con la autenticación de Spring Security
     */
    @ExceptionHandler({
            BadCredentialsException.class,
            LockedException.class,
            DisabledException.class,
            AccountExpiredException.class,
            CredentialsExpiredException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex,
                                                                  WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Error de autenticación";

        // Personalizar mensaje segun tipo de exception
        if (ex instanceof BadCredentialsException) {
            message = "Contraseña incorrecta";
        } else if (ex instanceof LockedException) {
            message = "Cuenta bloqueada";
        } else if (ex instanceof DisabledException) {
            message = "Cuenta deshabilitada";
        } else if (ex instanceof AccountExpiredException) {
            message = "Cuenta expirada";
        } else if (ex instanceof CredentialsExpiredException) {
            message = "Credenciales expiradas";
        } else if (ex instanceof UsernameNotFoundException) {
            message = "El usuario no está registrado";
        }

        log.info("Auhthentication error: {} - {} ",message, ex.getMessage());

        ApiError apiError = new ApiError(
                request.getDescription(false),
                message,
                status.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );

        return new ResponseEntity<>(apiError, status);
    }

    //Maneja excepciones de acceso denegado
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex,
                                                                WebRequest request) {
        ApiError apiError = new ApiError(
                request.getDescription(false),
                "Accesso denegado",
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    // Maneja exceptiones de jwt
    @ExceptionHandler(JWTValidationException.class)
    public ResponseEntity<ApiError> handleJWTValidationException(JWTValidationException ex,
                                                                 WebRequest request) {

        log.error("JWT validation error: {}", ex.getMessage());

        ApiError apiError = new ApiError(
                request.getDescription(false),
                "Error de autenticación",
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFoundException(CategoryNotFoundException ex,
                                                                    WebRequest request) {
        log.error("Category not found: {}", ex.getMessage());

        ApiError apiError = new ApiError(
                request.getDescription(false),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
