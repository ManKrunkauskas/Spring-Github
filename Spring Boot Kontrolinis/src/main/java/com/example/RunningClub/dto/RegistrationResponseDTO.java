package com.example.RunningClub.dto;

import java.time.LocalDateTime;

public record RegistrationResponseDTO(
        Long id,
        Long userId,
        String eventName,
        LocalDateTime registrationDate
) {}
