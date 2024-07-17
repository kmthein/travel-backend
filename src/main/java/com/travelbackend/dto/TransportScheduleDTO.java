package com.travelbackend.dto;

import com.travelbackend.entity.Image;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class TransportScheduleDTO {
    private int id;
    private int airlineId;
    private String name;
    private LocalDate date;
    private  String arrivalPlace;
    private String departurePlace;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private List<Image> ariLineImg;

}
