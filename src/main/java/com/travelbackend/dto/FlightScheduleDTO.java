package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class FlightScheduleDTO {
    private int flightScheduleId;

    private String ticketOption;
    private int departurePlaceId;
    private String departurePlaceName;
    private int arrivalPlaceId;
    private String arrivalPlaceName;
    private int distance;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int numberOfPassenger;

    private int airlineId;
}
