package com.example.RunningClub.repository;

import com.example.RunningClub.model.RunningEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.time.LocalDate;

public interface RunningEventRepository extends JpaRepository<RunningEvent, Long> {
    List<RunningEvent> findByCalendarDateAfter(LocalDate date);
}
