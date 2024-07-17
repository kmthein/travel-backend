package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FindRoomDTO {
    private int id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
