package com.example.movieapp.model;

import com.example.movieapp.validation.ValidTitle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title can't be null")
    @ValidTitle
    private String title;

    @NotNull(message = "Director is missing")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]*$", message = "Director must start Uppercase, no numbers")
    private String director;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "Movie needs at least 1 screening")
    @JsonManagedReference
    private List<Screening> screenings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;
}
