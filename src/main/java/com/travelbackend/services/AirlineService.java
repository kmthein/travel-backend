package com.travelbackend.services;

import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.entity.AirLine;

import java.util.List;

public interface AirlineService {
    void save(AirlineDTO airlineDTO);
    List<AirLine> findAll();
    void update(AirlineDTO air,int id);
}
