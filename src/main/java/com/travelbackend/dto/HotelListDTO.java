package com.travelbackend.dto;

import com.travelbackend.entity.Destination;
import com.travelbackend.entity.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelListDTO {
    private Integer id;
    private String name;
    private String description;
    private Double rating;
    private Destination destination;
    private List<Room> roomList;
    private List<String> imgUrlList;
}
