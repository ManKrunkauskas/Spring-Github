package com.example.movieapp.controller;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Actor;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ActorRepository;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepo;
    private final ActorRepository actorRepo;

    public MovieController(MovieRepository movieRepo, ActorRepository actorRepo) {
        this.movieRepo = movieRepo;
        this.actorRepo = actorRepo;
    }

    @PostMapping
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepo.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movieDetails) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setTitle(movieDetails.getTitle());
        movie.setDirector(movieDetails.getDirector());
        movie.setScreenings(movieDetails.getScreenings());
        return movieRepo.save(movie);
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public Movie addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Actor actor = actorRepo.findById(actorId).orElseThrow(() -> new RuntimeException("Actor not found"));
        movie.getActors().add(actor);
        return movieRepo.save(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }
}
