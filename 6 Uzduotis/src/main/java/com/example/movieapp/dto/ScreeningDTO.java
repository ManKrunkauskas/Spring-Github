package com.example.movieapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ScreeningDTO {
    public Long id;

    @NotNull(message = "Theater is required")
    @Size(min = 2, max = 100, message = "Theater name must be between 2 and 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Theater name must contain only letters")
    public String theater;

    @NotNull(message = "Time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be in HH:MM format")
    public String time;
}
