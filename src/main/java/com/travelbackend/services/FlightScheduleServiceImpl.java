package com.travelbackend.services;

import com.travelbackend.dao.AirLineDAO;
import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dao.FlightScheduleDAO;
import com.travelbackend.dto.TransportScheduleDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.Destination;
import com.travelbackend.entity.FlightSchedule;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService{
    @Autowired
    private DestinationDAO destinationDAO;

    @Autowired
    private FlightScheduleDAO flightScheduleDAO;

    @Autowired
    private AirLineDAO airLineDAO;

    @Override
    public void create(FlightSchedule flightSchedule,int airlineId, int departureId, int arrivalId) {
        if(departureId == arrivalId){
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        AirLine airLine = airLineDAO.findAirLineById(airlineId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        flightSchedule.setAirLine(airLine);
        flightSchedule.setDeparturePlace(departurePlace);
        flightSchedule.setArrivalPlace(arrivalPlace);
        flightScheduleDAO.save(flightSchedule);
    }

    @Override
    public List<FlightSchedule> getAllFlightSchedule() {
        return flightScheduleDAO.findAll();
    }

    @Override
    public FlightSchedule getFlightScheduleById(int id) {
        return flightScheduleDAO.findFlightScheduleById(id);
    }

    @Override
    public void updateFlightSchedule(int id, FlightSchedule flightSchedule, int airlineId, int departureId, int arrivalId) {
        FlightSchedule currentFlightSchedule = flightScheduleDAO.findFlightScheduleById(id);
        if(departureId == arrivalId){
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        AirLine airLine = airLineDAO.findAirLineById(airlineId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        currentFlightSchedule.setDepartureTime(flightSchedule.getDepartureTime());
        currentFlightSchedule.setArrivalTime(flightSchedule.getArrivalTime());
        currentFlightSchedule.setDate(flightSchedule.getDate());
        currentFlightSchedule.setDistance(flightSchedule.getDistance());
        currentFlightSchedule.setAirLine(airLine);
        currentFlightSchedule.setDeparturePlace(departurePlace);
        currentFlightSchedule.setArrivalPlace(arrivalPlace);
        flightScheduleDAO.update(currentFlightSchedule);
    }

    @Override
    public List<TransportScheduleDTO> getAvailableFlight() {

        List<FlightSchedule> flightScheduleList = flightScheduleDAO.availableFlight();
        List<TransportScheduleDTO> transportScheduleDTOList = new ArrayList<>();
        for(FlightSchedule fs : flightScheduleList){
            TransportScheduleDTO fst = new TransportScheduleDTO();
            fst.setId(fs.getId());
            fst.setAirlineId(fs.getAirLine().getId());
            fst.setName(fs.getAirLine().getName());
            fst.setDate(fs.getDate());
            fst.setArrivalTime(fs.getArrivalTime());
            fst.setDepartureTime(fs.getDepartureTime());
            fst.setArrivalPlace(fs.getArrivalPlace().getName());
            fst.setDeparturePlace(fs.getDeparturePlace().getName());
            fst.setAriLineImg(fs.getAirLine().getImage());
            transportScheduleDTOList.add(fst);
        }
        return transportScheduleDTOList;
    }
}
