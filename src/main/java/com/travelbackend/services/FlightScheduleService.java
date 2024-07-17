package com.travelbackend.services;

import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.dto.FlightScheduleDTO;
import com.travelbackend.entity.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {
    void create(FlightSchedule flightSchedule,int airlineId, int departureId,int  arrivalId);
    List<FlightSchedule> getAllFlightSchedule();
    FlightSchedule getFlightScheduleById(int id);

    void updateFlightSchedule(int id, FlightSchedule flightSchedule, int airlineId, int departureId, int arrivalId);

    List<AirlineDTO> getAvailableFlightSchedule(FlightScheduleDTO flightScheduleDTO);
}
