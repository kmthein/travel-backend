package com.travelbackend.services;

<<<<<<< HEAD
import com.travelbackend.dto.TransportScheduleDTO;
=======
import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.dto.FlightScheduleDTO;
>>>>>>> dfe6d88aa1ebb74fb415070e375c72d4a857f3ae
import com.travelbackend.entity.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {
    void create(FlightSchedule flightSchedule, int airlineId, int departureId, int arrivalId);

    List<FlightSchedule> getAllFlightSchedule();

    FlightSchedule getFlightScheduleById(int id);

    void updateFlightSchedule(int id, FlightSchedule flightSchedule, int airlineId, int departureId, int arrivalId);

<<<<<<< HEAD
    List<TransportScheduleDTO> getAvailableFlight();
=======
    List<AirlineDTO> getAvailableFlightSchedule(FlightScheduleDTO flightScheduleDTO);
>>>>>>> dfe6d88aa1ebb74fb415070e375c72d4a857f3ae
}
