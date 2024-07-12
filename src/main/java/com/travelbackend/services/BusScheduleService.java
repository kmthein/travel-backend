package com.travelbackend.services;

import com.travelbackend.entity.BusSchedule;
import com.travelbackend.entity.FlightSchedule;

import java.util.List;

public interface BusScheduleService {
    void create(BusSchedule busSchedule, int busId, int departureId, int arrivalId);

    List<BusSchedule> getAllBusSchedule();

    BusSchedule getBusScheduleById(int id);

    void updateBusSchedule(int id, BusSchedule busSchedule, int busId, int departureId, int arrivalId);
}
