package com.example.movieapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public class MovieDTO {
    public Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 120, message = "Title must be between 2 and 120 characters")
    public String title;

    @NotNull(message = "Director is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]*$", message = "Director must start with uppercase and contain only letters")
    public String director;

    @Size(min = 1, message = "At least one screening is required")
    public List<ScreeningDTO> screenings;

    public List<ActorDTO> actors;
}
