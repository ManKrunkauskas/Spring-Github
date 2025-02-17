package com.example.RunningClub.repository;

import com.example.RunningClub.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByRunningEventId(Long eventId);

    boolean existsByUserIdAndRunningEventId(Long userId, Long eventId);
}
