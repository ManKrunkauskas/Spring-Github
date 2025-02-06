package com.example.movieapp.controller;

import com.example.movieapp.dto.MovieDTO;
import com.example.movieapp.mapper.MovieMapper;
import com.example.movieapp.mapper.ScreeningMapper;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Actor;
import com.example.movieapp.model.Screening;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ActorRepository;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

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
    public MovieDTO createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        Movie movie = MovieMapper.toEntity(movieDTO);

        if (movieDTO.screenings != null && !movieDTO.screenings.isEmpty()) {
            final Movie finalMovie = movie;  // ✅ Fix for lambda
            movie.setScreenings(movieDTO.screenings.stream()
                    .map(screeningDTO -> {
                        Screening screening = ScreeningMapper.toEntity(screeningDTO);
                        screening.setMovie(finalMovie);
                        return screening;
                    })
                    .collect(Collectors.toList()));
        }

        movie = movieRepo.save(movie);
        return MovieMapper.toDTO(movie);
    }

    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setTitle(movieDTO.title);
        movie.setDirector(movieDTO.director);

        movie.getScreenings().clear();

        if (movieDTO.screenings != null) {
            Movie finalMovie = movie;
            movieDTO.screenings.forEach(screeningDTO -> {
                Screening screening = new Screening();
                screening.setTheater(screeningDTO.theater);
                screening.setTime(screeningDTO.time);
                screening.setMovie(finalMovie);
                finalMovie.getScreenings().add(screening);
            });
        }

        movie = movieRepo.save(movie);
        return MovieMapper.toDTO(movie);
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public MovieDTO addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Actor actor = actorRepo.findById(actorId)
                .orElseThrow(() -> new RuntimeException("Actor not found"));

        movie.getActors().add(actor);
        movie = movieRepo.save(movie);

        return MovieMapper.toDTO(movie);
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return movies.stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable Long id) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return MovieMapper.toDTO(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieRepo.deleteById(id);
    }
}
