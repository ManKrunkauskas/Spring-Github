package com.example.RunningClub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record UserRequestDTO(
        @NotBlank
        @Pattern(regexp = "^[a-z0-9]{4,}$", message = "Username must be at least 4 characters, lowercase and numbers only")
        String username,

        @NotBlank
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        Set<Long> roles
) {}
