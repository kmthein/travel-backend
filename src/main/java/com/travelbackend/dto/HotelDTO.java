package com.travelbackend.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HotelDTO {

    private String name;

    private String description;

    private Double rating;

    private Integer destinationId;

    private List<String> imgUrlList;

}
