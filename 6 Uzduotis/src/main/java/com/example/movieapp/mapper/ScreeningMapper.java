package com.example.movieapp.mapper;

import com.example.movieapp.dto.ScreeningDTO;
import com.example.movieapp.model.Screening;

public class ScreeningMapper {

    public static Screening toEntity(ScreeningDTO dto) {
        Screening screening = new Screening();
        screening.setTheater(dto.theater);
        screening.setTime(dto.time);
        return screening;
    }

    public static ScreeningDTO toDTO(Screening screening) {
        ScreeningDTO dto = new ScreeningDTO();
        dto.theater = screening.getTheater();
        dto.time = screening.getTime();
        return dto;
    }
}
