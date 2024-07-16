package com.travelbackend.services;

import com.travelbackend.dto.TransportDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.BusService;

import java.util.List;

public interface BusStationService {

    void save(TransportDTO transportDTO);

    List<BusService> findAll();

    BusService getById(int id);

    void updateBusService(int id,TransportDTO transportDTO);
}
