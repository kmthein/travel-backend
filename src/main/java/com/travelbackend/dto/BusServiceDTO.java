package com.travelbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BusServiceDTO {
    private int busServiceId;
    private String busServiceName;
    private BusScheduleDTO busScheduleDTO;
    private List<BusClassDTO> busClassDTOList;
    private List<String> imgUrlList;

    private boolean hasSeat;
}
