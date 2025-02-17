package com.example.RunningClub.service;

import com.example.RunningClub.dto.RegistrationRequestDTO;
import com.example.RunningClub.dto.RegistrationResponseDTO;
import com.example.RunningClub.model.Registration;
import com.example.RunningClub.model.RunningEvent;
import com.example.RunningClub.model.User;
import com.example.RunningClub.repository.RegistrationRepository;
import com.example.RunningClub.repository.RunningEventRepository;
import com.example.RunningClub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final RunningEventRepository runningEventRepository;

    public RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository, RunningEventRepository runningEventRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.runningEventRepository = runningEventRepository;
    }

    public RegistrationResponseDTO registerUserForEvent(Long eventId, RegistrationRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RunningEvent event = runningEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (registrationRepository.existsByUserIdAndRunningEventId(dto.userId(), eventId)) {
            throw new RuntimeException("User is already registered for this event");
        }

        Registration registration = new Registration(user, event, LocalDateTime.now());
        Registration savedRegistration = registrationRepository.save(registration);

        return new RegistrationResponseDTO(
                savedRegistration.getId(),
                savedRegistration.getUser().getId(),
                savedRegistration.getRunningEvent().getName(),
                savedRegistration.getRegistrationDate()
        );
    }
}
