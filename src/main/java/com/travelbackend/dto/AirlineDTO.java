package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AirlineDTO {
    private int airlineId;
    private String airlineName;
    private FlightScheduleDTO flightScheduleDTO;
    private List<FlightClassDTO> flightClassDTOList;
    private List<String> imgUrlList;

    private boolean hasSeat;
}
