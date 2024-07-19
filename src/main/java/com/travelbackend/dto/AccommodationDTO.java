package com.travelbackend.dto;

import com.travelbackend.entity.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccommodationDTO {
    private int id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int totalPerson;

    private int totalPrice;

    private String roomName;

    private String hotelName;

    private String hotelImgUrl;
}
