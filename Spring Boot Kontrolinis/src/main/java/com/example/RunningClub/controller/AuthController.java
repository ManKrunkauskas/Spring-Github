package com.example.RunningClub.controller;

import com.example.RunningClub.dto.UserRequestDTO;
import com.example.RunningClub.dto.UserResponseDTO;
import com.example.RunningClub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.status(201).body(userService.registerUser(request));
    }
}
