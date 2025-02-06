package com.example.movieapp.controller;

import com.example.movieapp.dto.ActorDTO;
import com.example.movieapp.mapper.ActorMapper;
import com.example.movieapp.model.Actor;
import com.example.movieapp.repository.ActorRepository;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorRepository actorRepo;

    public ActorController(ActorRepository actorRepo) {
        this.actorRepo = actorRepo;
    }

    @PostMapping
    public ActorDTO createActor(@Valid @RequestBody ActorDTO actorDTO) {
        Actor actor = ActorMapper.toEntity(actorDTO);
        actor = actorRepo.save(actor);
        return ActorMapper.toDTO(actor);
    }

    @GetMapping
    public List<ActorDTO> getAllActors() {
        return actorRepo.findAll().stream()
                .map(ActorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ActorDTO getActorById(@PathVariable Long id) {
        Actor actor = actorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));
        return ActorMapper.toDTO(actor);
    }

    @PutMapping("/{id}")
    public ActorDTO updateActor(@PathVariable Long id, @Valid @RequestBody ActorDTO actorDTO) {
        Actor actor = actorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));

        actor.setName(actorDTO.getName());
        actor.setBirthYear(actorDTO.getBirthYear());

        actor = actorRepo.save(actor);
        return ActorMapper.toDTO(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id) {
        actorRepo.deleteById(id);
    }
}
