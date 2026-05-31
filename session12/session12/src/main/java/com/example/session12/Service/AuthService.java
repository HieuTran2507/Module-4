package com.example.session12.Service;

import com.example.session12.Model.DTO.AuthResponse;
import com.example.session12.Model.DTO.LoginRequest;
import com.example.session12.Model.DTO.RegisterRequest;
import com.example.session12.Model.Entity.User;
import com.example.session12.Repository.UserRepository;
import com.example.session12.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request){

        if(userRepository.existsByEmail(
                request.getEmail())){
            throw new RuntimeException(
                    "Email already exists");
        }

        User user = User.builder()
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(
            LoginRequest request){

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email not found"));

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())){
            throw new RuntimeException(
                    "Wrong password");
        }

        String accessToken =
                jwtUtil.generateToken(user.getEmail());

        String refreshToken =
                jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                accessToken,
                refreshToken);
    }
}
