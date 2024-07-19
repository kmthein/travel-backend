package com.travelbackend.dao;

import com.travelbackend.entity.FlightSchedule;

import java.util.List;

public interface FlightScheduleDAO {

    void save(FlightSchedule flightSchedule);

    FlightSchedule findFlightScheduleById(int flightScheduleId);

    List<FlightSchedule> findAll();

    void update(FlightSchedule flightSchedule);

    void delete(int flightScheduleId);

    List<FlightSchedule> availableFlight();

    List<FlightSchedule> findbyDestination(int desId);
}
