package com.project.projectbackculture.config;

import com.project.projectbackculture.utility.JwtUtils;
import com.project.projectbackculture.utility.JwtValidator;
import com.project.projectbackculture.service.implement.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(JwtUtils jwtUtils, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtUtils = jwtUtils;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)

                .httpBasic(Customizer.withDefaults())

                .exceptionHandling( except ->
                        except.authenticationEntryPoint(customAuthenticationEntryPoint))

                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                    session.maximumSessions(1);
                })
                .authorizeHttpRequests(request -> {

                    //Endpoint publicos
                    request.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    request.requestMatchers("/place/**").permitAll();
                    request.requestMatchers("/comment/place").permitAll();

                    //Endpoints privados
                    request.requestMatchers("/category/**").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST,"/comment/create").hasRole("USER");
                    request.requestMatchers("/favority/**").hasRole("USER");
                    request.requestMatchers("/image/**").hasRole("ADMIN");
                    request.requestMatchers("/qualification/**").hasRole("USER");

                    //Endpoints no configurados
                    request.anyRequest().denyAll();

                })
                .addFilterBefore(new JwtValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserServiceImpl userServiceImpl) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userServiceImpl);
        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
