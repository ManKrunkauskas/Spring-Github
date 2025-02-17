package com.example.RunningClub.dto;

import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDTO(
        @NotNull
        Long userId
) {}
