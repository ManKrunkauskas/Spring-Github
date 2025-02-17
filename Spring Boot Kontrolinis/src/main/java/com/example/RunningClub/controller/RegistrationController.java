package com.example.RunningClub.controller;

import com.example.RunningClub.dto.RegistrationRequestDTO;
import com.example.RunningClub.dto.RegistrationResponseDTO;
import com.example.RunningClub.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<RegistrationResponseDTO> registerForEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody RegistrationRequestDTO request) {
        return ResponseEntity.status(201).body(registrationService.registerUserForEvent(eventId, request));
    }
}
