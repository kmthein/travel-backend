package com.travelbackend.services;

import com.travelbackend.dto.BusScheduleDTO;
import com.travelbackend.dto.BusServiceDTO;
import com.travelbackend.entity.BusSchedule;

import java.util.List;

public interface BusScheduleService {
    void create(BusSchedule busSchedule, int busId, int departureId, int arrivalId);

    List<BusSchedule> getAllBusSchedule();

    BusSchedule getBusScheduleById(int id);

    void updateBusSchedule(int id, BusSchedule busSchedule, int busId, int departureId, int arrivalId);

    List<BusServiceDTO> getAvailableBusSchedule(BusScheduleDTO findBusDTO);
}
