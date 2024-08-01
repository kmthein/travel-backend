package com.travelbackend.dto;

import com.travelbackend.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DestinationDTO {
    private int id;
    private String name;
    private String country;
    private String description;
    private String highlight;
    private String topPlace;
    private List<Hotel> hotelList;
    private List<BusSchedule> busArriveTo;
    private List<FlightSchedule> flightArriveTo;
    private List<Image> image;
}
