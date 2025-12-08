package com.MyMemory.MyMemory.Services;

import com.MyMemory.MyMemory.Dto.Requset.LoginRequest;
import com.MyMemory.MyMemory.Dto.Requset.RegisterRequest;
import com.MyMemory.MyMemory.Dto.Response.AuthResponse;
import com.MyMemory.MyMemory.Enitity.User;
import com.MyMemory.MyMemory.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Register and return JWT + user info
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), token);
    }

    // Login and return JWT + user info
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), token);
    }
}
