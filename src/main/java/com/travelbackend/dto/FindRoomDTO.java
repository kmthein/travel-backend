package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FindRoomDTO {
    private int id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
