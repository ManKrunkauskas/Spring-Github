package com.example.movieapp.controller;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Screening;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepo;

    public MovieController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
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

    @GetMapping
    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }
}
