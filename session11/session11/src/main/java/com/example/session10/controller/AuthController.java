package com.example.session10.controller;

import com.example.session10.model.dto.LoginRequest;
import com.example.session10.model.dto.RegisterRequest;
import com.example.session10.service.AuthService;
import com.example.session10.utility.JwtProvider;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          JwtProvider jwtProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    //-------REGISTER--------------
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("Register success");
    }

    //-------------LOGIN---------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {
            // 1. tạo token login
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    );
            //System.out.println("username: " + token.getName());
            //System.out.println("password: " + token.getCredentials());

            // 2. authenticate
            Authentication authentication =
                    authenticationManager.authenticate(token);
            //System.out.println("NAME: " + authentication.getName());
            //System.out.println("PRINCIPAL: " + authentication.getPrincipal());
            //System.out.println("CREDENTIALS: " + authentication.getCredentials());

            // 4. tạo JWT
            String accessToken =
                    jwtProvider.generateToken(authentication);

            // 5. trả JWT cho client
            return ResponseEntity.ok(
                    Map.of(
                            "accessToken", accessToken,
                            "type", "Bearer",
                            "username", request.getUsername()
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body("username or password incorrect");
        }
    }

}
