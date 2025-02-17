package com.example.RunningClub.dto;

import java.time.LocalDate;

public record RunningEventResponseDTO(
        Long id,
        String name,
        LocalDate calendarDate,
        String location,
        int maxParticipants
) {}
