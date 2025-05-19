package com.example.Souq.auth.controller;

import com.example.Souq.auth.dto.AuthResponse;
import com.example.Souq.auth.dto.LoginRequest;
import com.example.Souq.auth.dto.SignupRequest;
import com.example.Souq.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody SignupRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
}
