package com.example.session10.service;

import com.example.session10.Repository.UserRepository;
import com.example.session10.model.dto.RegisterRequest;
import com.example.session10.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {

        // 1. check trùng username
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // 2. encode password (QUAN TRỌNG NHẤT)
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // 3. tạo user entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(hashedPassword);
        user.setRole("USER");
        user.setEnabled(true);

        // 4. lưu DB
        userRepository.save(user);
    }
}
