package com.example.RunningClub.service;

import com.example.RunningClub.dto.RunningEventRequestDTO;
import com.example.RunningClub.dto.RunningEventResponseDTO;
import com.example.RunningClub.model.RunningEvent;
import com.example.RunningClub.repository.RunningEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunningEventService {

    private final RunningEventRepository runningEventRepository;

    public RunningEventService(RunningEventRepository runningEventRepository) {
        this.runningEventRepository = runningEventRepository;
    }

    public RunningEventResponseDTO createEvent(RunningEventRequestDTO dto) {
        RunningEvent event = new RunningEvent(dto.name(), dto.calendarDate(), dto.location(), dto.maxParticipants());
        RunningEvent savedEvent = runningEventRepository.save(event);
        return new RunningEventResponseDTO(
                savedEvent.getId(),
                savedEvent.getName(),
                savedEvent.getCalendarDate(),
                savedEvent.getLocation(),
                savedEvent.getMaxParticipants()
        );
    }

    public List<RunningEventResponseDTO> getUpcomingEvents() {
        return runningEventRepository.findByCalendarDateAfter(java.time.LocalDate.now()).stream()
                .map(event -> new RunningEventResponseDTO(event.getId(), event.getName(), event.getCalendarDate(), event.getLocation(), event.getMaxParticipants()))
                .collect(Collectors.toList());
    }

    public void deleteEvent(Long eventId) {
        if (!runningEventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }
        runningEventRepository.deleteById(eventId);
    }
}
