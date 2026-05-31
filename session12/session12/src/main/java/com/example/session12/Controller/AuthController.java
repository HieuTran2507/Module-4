package com.example.session12.Controller;

import com.example.session12.Model.DTO.AuthResponse;
import com.example.session12.Model.DTO.LoginRequest;
import com.example.session12.Model.DTO.RegisterRequest;
import com.example.session12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterRequest request){

        authService.register(request);

        return ResponseEntity.ok(
                "Register success");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request){

        return ResponseEntity.ok(
                authService.login(request));
    }
}
