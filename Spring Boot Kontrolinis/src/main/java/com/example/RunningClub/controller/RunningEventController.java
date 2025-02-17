package com.example.RunningClub.controller;

import com.example.RunningClub.dto.RunningEventRequestDTO;
import com.example.RunningClub.dto.RunningEventResponseDTO;
import com.example.RunningClub.service.RunningEventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class RunningEventController {

    private final RunningEventService runningEventService;

    public RunningEventController(RunningEventService runningEventService) {
        this.runningEventService = runningEventService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RunningEventResponseDTO> createEvent(@Valid @RequestBody RunningEventRequestDTO request) {
        return ResponseEntity.status(201).body(runningEventService.createEvent(request));
    }

    @GetMapping
    public ResponseEntity<List<RunningEventResponseDTO>> getEvents() {
        return ResponseEntity.ok(runningEventService.getUpcomingEvents());
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        runningEventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
