package com.example.movieapp.controller;

import com.example.movieapp.model.Actor;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Screening;
import com.example.movieapp.repository.ActorRepository;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

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
    public Movie createMovie(@RequestBody Movie movie) {
        if (movie.getScreenings() != null) {
            for (Screening screening : movie.getScreenings()) {
                if (screening.getMovie() == null) {
                    screening.setMovie(movie);
                }
            }
        }
        return movieRepo.save(movie);
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public Movie addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Actor actor = actorRepo.findById(actorId).orElseThrow(() -> new RuntimeException("Actor not found"));

        movie.getActors().add(actor);
        return movieRepo.save(movie);
    }
}
