package com.example.RunningClub.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RunningEventRequestDTO(
        @NotBlank
        @Size(min = 5, message = "Event name must be at least 5 characters long")
        String name,

        @Future(message = "Event date must be in the future")
        LocalDate calendarDate,

        @NotBlank
        String location,

        @Min(value = 1, message = "Max participants must be greater than 0")
        int maxParticipants
) {}
