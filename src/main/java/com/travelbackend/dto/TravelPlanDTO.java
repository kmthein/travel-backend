package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class TravelPlanDTO {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int totalPrice;

    private String status;

    private BusClassDTO busClassDTO;

    private BusScheduleDTO busScheduleDTO;

    private FlightClassDTO flightClassDTO;

    private FlightScheduleDTO flightScheduleDTO;

    private AccommodationDTO accommodationDTO;

    private UserDTO userDTO;
}
