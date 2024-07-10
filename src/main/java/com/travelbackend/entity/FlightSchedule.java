package com.travelbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_schedule")
public class FlightSchedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "distance")
    private int distance;

    @ManyToOne
    @JoinColumn(name = "departure_place")
    private Destination departurePlace;

    @ManyToOne
    @JoinColumn(name = "arrival_place")
    private Destination arrivalPlace;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private AirLine airLine;
}
