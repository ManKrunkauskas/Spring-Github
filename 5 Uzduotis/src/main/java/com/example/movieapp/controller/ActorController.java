package com.example.movieapp.controller;

import com.example.movieapp.model.Actor;
import com.example.movieapp.repository.ActorRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorRepository actorRepo;

    public ActorController(ActorRepository actorRepo) {
        this.actorRepo = actorRepo;
    }

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorRepo.save(actor);
    }

    @GetMapping
    public List<Actor> getAllActors() {
        return actorRepo.findAll();
    }
}
