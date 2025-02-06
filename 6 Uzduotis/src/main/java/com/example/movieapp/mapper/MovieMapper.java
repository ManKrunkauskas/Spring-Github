package com.example.movieapp.mapper;

import com.example.movieapp.dto.MovieDTO;
import com.example.movieapp.model.Actor;
import com.example.movieapp.model.Movie;
import java.util.stream.Collectors;

public class MovieMapper {

    public static Movie toEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.title);
        movie.setDirector(dto.director);

        if (dto.screenings != null) {
            movie.setScreenings(dto.screenings.stream()
                    .map(ScreeningMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return movie;
    }

    public static MovieDTO toDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.id = movie.getId();
        dto.title = movie.getTitle();
        dto.director = movie.getDirector();
        dto.screenings = movie.getScreenings().stream()
                .map(ScreeningMapper::toDTO)
                .collect(Collectors.toList());
        dto.actors = movie.getActors().stream()
                .map(ActorMapper::toDTO)
                .collect(Collectors.toList());
        return dto;
    }
}
