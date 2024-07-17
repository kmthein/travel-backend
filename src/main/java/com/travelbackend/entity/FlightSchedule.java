package com.travelbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_schedule")
public class FlightSchedule extends BaseEntity {
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "departure_place")
    private Destination departurePlace;

    @JsonBackReference
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "arrival_place")
    private Destination arrivalPlace;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "airline_id")
    private AirLine airLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Destination getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(Destination departurePlace) {
        this.departurePlace = departurePlace;
    }

    public Destination getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(Destination arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public AirLine getAirLine() {
        return airLine;
    }

    public void setAirLine(AirLine airLine) {
        this.airLine = airLine;
    }
}
