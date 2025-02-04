package com.example.movieapp.controller;

import com.example.movieapp.model.Screening;
import com.example.movieapp.repository.ScreeningRepository;
import com.example.movieapp.repository.MovieRepository;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/screenings")
public class ScreeningController {

    private final ScreeningRepository screeningRepo;
    private final MovieRepository movieRepo;

    public ScreeningController(ScreeningRepository screeningRepo, MovieRepository movieRepo) {
        this.screeningRepo = screeningRepo;
        this.movieRepo = movieRepo;
    }

    @PostMapping("/{movieId}")
    public Screening addScreening(@PathVariable Long movieId, @Valid @RequestBody Screening screening) {
        return movieRepo.findById(movieId).map(movie -> {
            screening.setMovie(movie);
            return screeningRepo.save(screening);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
