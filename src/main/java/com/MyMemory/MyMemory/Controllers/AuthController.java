package com.MyMemory.MyMemory.Controllers;

import com.MyMemory.MyMemory.Dto.Requset.LoginRequest;
import com.MyMemory.MyMemory.Dto.Requset.RegisterRequest;
import com.MyMemory.MyMemory.Dto.Response.AuthResponse;
import com.MyMemory.MyMemory.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request); // return AuthResponse instead of String
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request); // return AuthResponse instead of String
    }
}
