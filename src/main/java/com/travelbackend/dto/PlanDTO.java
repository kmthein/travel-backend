package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlanDTO {
    private int id;
    private String username;
    private LocalDate startDate;
    private String status;
    private String arrivalPlace;
    private String departurePlace;
    private String hotelName;
    private String airlineClass;
    private String airlineName;
    private String busClass;
    private String busName;
    private double totalPrice;
}
