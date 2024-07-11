package com.travelbackend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus_schedule")
public class BusSchedule extends BaseEntity{
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
    @JoinColumn(name = "bus_id")
    private BusService busService;

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

    public BusService getBusService() {
        return busService;
    }

    public void setBusService(BusService busService) {
        this.busService = busService;
    }
}
