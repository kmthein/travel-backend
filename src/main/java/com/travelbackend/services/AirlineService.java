package com.travelbackend.services;

import com.travelbackend.dto.TransportDTO;
import com.travelbackend.entity.AirLine;

import java.util.List;

public interface AirlineService {
    void save(TransportDTO transportDTO);

    List<AirLine> findAll();

    void update(int id, TransportDTO transportDTO);

    AirLine getById(int id);

    void deleteAirline(int id);
}
