package com.travelbackend.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BusClassDTO {
    private int busClassId;
    private String busClassName;
    private int price;
    private int availableSeat;

    private int busServiceId;
    private String busServiceName;
    private List<String> busServiceImgUrl;
}
