package com.travelbackend.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FlightClassDTO {
    private int flightClassId;
    private String flightClassName;
    private int price;
    private int availableSeat;

    private int airlineId;
    private String airlineName;
    private List<String> airlineImgUrl;
}
