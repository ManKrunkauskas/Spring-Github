package com.example.movieapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Theater is missing")
    @Size(min = 2, max = 100, message = "Theater name 2-100 chars")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Theater name must only contain letters")
    private String theater;

    @NotNull(message = "Time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be HH:MM")
    private String time;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    private Movie movie;
}
