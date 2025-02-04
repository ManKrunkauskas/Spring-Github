package com.example.movieapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Actor name is required")
    @Pattern(regexp = "[A-Za-z ]+$", message = "Actor name must only include letters")
    private String name;

    @Min(value = 1900, message = "Birth year can't be older than 1900")
    @Max(value = 2100, message = "Birth year can't be more than 2100")
    private Integer birthYear;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();
}
