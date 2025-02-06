package com.example.movieapp.mapper;

import com.example.movieapp.dto.ActorDTO;
import com.example.movieapp.model.Actor;

public class ActorMapper {

    public static Actor toEntity(ActorDTO dto) {
        Actor actor = new Actor();
        actor.setName(dto.getName());
        actor.setBirthYear(dto.getBirthYear());
        return actor;
    }

    public static ActorDTO toDTO(Actor actor) {
        ActorDTO dto = new ActorDTO();
        dto.setName(actor.getName());
        dto.setBirthYear(actor.getBirthYear());
        return dto;
    }
}
