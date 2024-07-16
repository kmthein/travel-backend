package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FindHotelDTO {
    private String searchString;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int numberOfPerson;
}
