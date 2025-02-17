package com.example.RunningClub.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "running_events")
public class RunningEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate calendarDate;

    @Column(nullable = false)
    private String location;

    private int maxParticipants;

    @OneToMany(mappedBy = "runningEvent")
    private List<Registration> registrations;

    public RunningEvent() {}

    public RunningEvent(String name, LocalDate calendarDate, String location, int maxParticipants) {
        this.name = name;
        this.calendarDate = calendarDate;
        this.location = location;
        this.maxParticipants = maxParticipants;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getCalendarDate() { return calendarDate; }
    public void setCalendarDate(LocalDate calendarDate) { this.calendarDate = calendarDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
}
