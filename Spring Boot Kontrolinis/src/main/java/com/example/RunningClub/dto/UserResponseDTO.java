package com.example.RunningClub.dto;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String username,
        Set<String> roles
) {}
