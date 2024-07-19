package com.travelbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelTypeDTO {
    private Double flightOnlyPercent;
    private Double busOnlyPercent;
    private Double hotelOnlyPercent;
    private Double flightHotelPercent;
    private Double busHotelPercent;
}
