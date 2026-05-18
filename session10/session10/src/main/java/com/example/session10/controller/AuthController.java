package com.example.session10.controller;

import com.example.session10.model.dto.LoginRequest;
import com.example.session10.model.dto.RegisterRequest;
import com.example.session10.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //-------REGISTER--------------
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("Register success");
    }

    //-------------LOGIN---------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request,
                                   HttpSession session) {

        try {
            // 1. tạo token login
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    );

            // 2. authenticate
            Authentication authentication =
                    authenticationManager.authenticate(token);

            // 3. lưu session (optional)
            session.setAttribute("user", request.getUsername());

            // 4. response success
            return ResponseEntity.ok(
                    "Login success for user: " + request.getUsername()
            );

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("username or password incorrect");
        }
    }
}
