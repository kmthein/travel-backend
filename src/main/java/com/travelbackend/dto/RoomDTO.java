package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RoomDTO {

    private int id;
    private String roomType;
    private int validRoom;
    private int availableRoom;
    private int roomPrice;
    private int hotelId;
    private List<String> imgUrlList;
}
