package com.travelbackend.services;
import com.travelbackend.dao.AirLineDAO;
import com.travelbackend.dao.FlightClassDAO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.BusClass;
import com.travelbackend.entity.FlightClass;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightClassServiceImpl implements FlightClassService{

    private FlightClassDAO flightClassDAO;

    private AirLineDAO airLineDAO;

    @Autowired
    public FlightClassServiceImpl(FlightClassDAO flightClassDAO,AirLineDAO airLineDAO){
        this.flightClassDAO = flightClassDAO;
        this.airLineDAO = airLineDAO;
    }

    @Override
    public void saveFlightClass(FlightClass flightClass, int airlineId) {
        AirLine a = airLineDAO.findAirLineById(airlineId);
        if(a == null){
            throw new ResourceNotFoundException("Class not found");
        }
        flightClass.setAirline(a);
        flightClassDAO.save(flightClass);
    }

    @Override
    public List<FlightClass> findAllFlightClass() {
        return flightClassDAO.findAll();
    }

    @Override
    public FlightClass findFlightClassById(int flightClassId) {
        return flightClassDAO.findFlightClassById(flightClassId);
    }

    @Override
    public void updateFlightClass(FlightClass flightClass,int id,int airlineId) {
        FlightClass fc = flightClassDAO.findFlightClassById(id);
        if(fc == null){
            throw new ResourceNotFoundException("Flight class not found");
        }
        AirLine airLine = airLineDAO.findAirLineById(airlineId);
        if(airLine == null){
            throw new ResourceNotFoundException("AirLine Class not found");
        }
        fc.setName(flightClass.getName());
        fc.setPrice(flightClass.getPrice());
        fc.setValidSeat(flightClass.getValidSeat());
        fc.setAirline(airLine);
        flightClassDAO.update(fc);
    }

    @Override
    public void deleteFlightClass(int flightClassId) {
        FlightClass flightClass =flightClassDAO.findFlightClassById(flightClassId);
        if(flightClass == null){
            throw new ResourceNotFoundException("No data in Flight Class Table");
        }
        flightClass.setDelete(true);
        flightClassDAO.update(flightClass);
    }

    @Override
    public List<FlightClass> getFlightClassByAirline(int airlineId) {
        return flightClassDAO.getFlightClassByAirline(airlineId);
    }

    @Override
    public List<FlightClass> findFlightClassByAirlineId(int airlineId) {
        List<FlightClass> flightClassList = flightClassDAO.findAll();
        return flightClassList.stream().filter(c -> c.getAirline().getId() == airlineId).toList();
    }
}
