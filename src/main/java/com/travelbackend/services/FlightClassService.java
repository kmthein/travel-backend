package com.travelbackend.services;

import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.FlightClass;

import java.util.List;

public interface FlightClassService {

    void saveFlightClass(FlightClass flightClass, int airlineId);

    List<FlightClass> findAllFlightClass();

    FlightClass findFlightClassById(int flightClassId);

    void updateFlightClass(FlightClass flightClass,int flightClassId,int airlineId);

    void deleteFlightClass(int flightClassId);
}
