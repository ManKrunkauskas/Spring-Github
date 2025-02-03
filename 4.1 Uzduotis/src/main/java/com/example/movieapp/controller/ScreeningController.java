package com.example.movieapp.controller;

import com.example.movieapp.model.Screening;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ScreeningRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningRepository screeningRepo;
    private final MovieRepository movieRepository;

    public ScreeningController(ScreeningRepository screeningRepo, MovieRepository movieRepository) {
        this.screeningRepo = screeningRepo;
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public Screening createScreening(@RequestBody Screening screening) {
        return screeningRepo.save(screening);
    }

    @GetMapping
    public List<Screening> getScreenings() {
        return screeningRepo.findAll();
    }

    @PostMapping("/{movieId}")
    public Screening addScreening(@PathVariable Long movieId, @RequestBody Screening screening) {
        return movieRepository.findById(movieId).map(movie -> {
            screening.setMovie(movie);
            return screeningRepo.save(screening);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

}
