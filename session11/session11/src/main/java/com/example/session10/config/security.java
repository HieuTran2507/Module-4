package com.example.session10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class security{
    // FILTER CHAIN
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                // tắt csrf
                .csrf(csrf -> csrf.disable())

                // cấu hình phân quyền request
                .authorizeHttpRequests(auth -> auth

                        // cho phép public
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // các request còn lại phải login
                        .anyRequest().authenticated()
                );
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain sf(HttpSecurity http) throws Exception{
//        http
//                .csrf(csrf-> csrf.disable())
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AUTHENTICATION MANAGER
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
