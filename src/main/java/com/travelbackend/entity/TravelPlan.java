package com.travelbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travel_plan")
public class TravelPlan extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_amount")
    private int totalAmount;

    @ManyToOne
    @JoinColumn(name = "bus_class")
    private BusClass busClass;

    @ManyToOne
    @JoinColumn(name = "bus_schedule")
    private BusSchedule busSchedule;

    @ManyToOne
    @JoinColumn(name = "flight_class")
    private FlightClass flightClass;

    @ManyToOne
    @JoinColumn(name = "flight_schedule")
    private FlightSchedule flightSchedule;

    @OneToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
