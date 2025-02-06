package com.example.movieapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class ActorDTO {
    public Long id;

    @NotNull(message = "Actor name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Actor name must contain only letters")
    public String name;

    @NotNull(message = "Birth year cannot be null")
    @Min(value = 1900, message = "Birth year cannot be before 1900")
    @Max(value = 2025, message = "Birth year cannot be in the future")
    public Integer birthYear;

        public String getName() {
            return name;
        }

        public int getBirthYear() {
            return birthYear;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBirthYear(int birthYear) {
            this.birthYear = birthYear;
        }
    }
