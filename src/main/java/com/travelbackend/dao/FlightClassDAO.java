package com.travelbackend.dao;

import com.travelbackend.entity.FlightClass;
import com.travelbackend.entity.FlightSchedule;

import java.util.List;

public interface FlightClassDAO {

    void save(FlightClass flightClass);

    FlightClass findFlightClassById(int flightClassId);

    List<FlightClass> findAll();

    void update(FlightClass flightClass);

    void delete(int flightClassId);


}
