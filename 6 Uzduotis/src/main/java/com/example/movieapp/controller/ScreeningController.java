package com.example.movieapp.controller;

import com.example.movieapp.dto.ScreeningDTO;
import com.example.movieapp.mapper.ScreeningMapper;
import com.example.movieapp.model.Screening;
import com.example.movieapp.repository.ScreeningRepository;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ScreeningDTO addScreening(@PathVariable Long movieId, @Valid @RequestBody ScreeningDTO screeningDTO) {
        return movieRepo.findById(movieId).map(movie -> {
            Screening screening = ScreeningMapper.toEntity(screeningDTO);
            screening.setMovie(movie);
            screening = screeningRepo.save(screening);
            return ScreeningMapper.toDTO(screening);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @GetMapping
    public List<ScreeningDTO> getAllScreenings() {
        return screeningRepo.findAll().stream()
                .map(ScreeningMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ScreeningDTO getScreeningById(@PathVariable Long id) {
        return screeningRepo.findById(id)
                .map(ScreeningMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Screening not found"));
    }

    @PutMapping("/{id}")
    public ScreeningDTO updateScreening(@PathVariable Long id, @Valid @RequestBody ScreeningDTO screeningDTO) {
        return screeningRepo.findById(id).map(screening -> {
            screening.setTheater(screeningDTO.theater);
            screening.setTime(screeningDTO.time);
            screening = screeningRepo.save(screening);
            return ScreeningMapper.toDTO(screening);
        }).orElseThrow(() -> new RuntimeException("Screening not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteScreening(@PathVariable Long id) {
        screeningRepo.deleteById(id);
    }
}
