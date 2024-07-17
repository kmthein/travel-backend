package com.travelbackend.services;

import com.travelbackend.dao.BusScheduleDAO;
import com.travelbackend.dao.BusServiceDAO;
import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dto.TransportScheduleDTO;
import com.travelbackend.entity.*;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {
    @Autowired
    private BusServiceDAO busServiceDAO;

    @Autowired
    private DestinationDAO destinationDAO;

    @Autowired
    private BusScheduleDAO busScheduleDAO;

    @Override
    public void create(BusSchedule busSchedule, int busId, int departureId, int arrivalId) {
        if (departureId == arrivalId) {
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        BusService bus = busServiceDAO.findBusServiceById(busId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        busSchedule.setBusService(bus);
        busSchedule.setDeparturePlace(departurePlace);
        busSchedule.setArrivalPlace(arrivalPlace);
        busScheduleDAO.save(busSchedule);

    }

    @Override
    public List<BusSchedule> getAllBusSchedule() {
        return busScheduleDAO.findAll();
    }

    @Override
    public BusSchedule getBusScheduleById(int id) {
        return busScheduleDAO.findBusScheduleById(id);
    }

    @Override
    public void updateBusSchedule(int id, BusSchedule busSchedule, int busId, int departureId, int arrivalId) {
        BusSchedule currentBusSchedule = busScheduleDAO.findBusScheduleById(id);
        if(departureId == arrivalId){
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        BusService busService = busServiceDAO.findBusServiceById(busId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        currentBusSchedule.setDepartureTime(busSchedule.getDepartureTime());
        currentBusSchedule.setArrivalTime(busSchedule.getArrivalTime());
        currentBusSchedule.setDate(busSchedule.getDate());
        currentBusSchedule.setDistance(busSchedule.getDistance());
        currentBusSchedule.setBusService(busService);
        currentBusSchedule.setDeparturePlace(departurePlace);
        currentBusSchedule.setArrivalPlace(arrivalPlace);
        busScheduleDAO.update(currentBusSchedule);
    }

    @Override
    public List<TransportScheduleDTO> getAvailableFlight() {
        List<BusSchedule> busScheduleList = busScheduleDAO.availableBus();
        List<TransportScheduleDTO> transportScheduleDTOList = new ArrayList<>();
        for(BusSchedule bs : busScheduleList){
            TransportScheduleDTO bst = new TransportScheduleDTO();
            bst.setId(bs.getId());
            bst.setName(bs.getBusService().getName());
            bst.setDate(bs.getDate());
            bst.setArrivalTime(bs.getArrivalTime());
            bst.setDepartureTime(bs.getDepartureTime());
            bst.setArrivalPlace(bs.getArrivalPlace().getName());
            bst.setDeparturePlace(bs.getDeparturePlace().getName());
            bst.setAriLineImg(bs.getBusService().getImage());
            transportScheduleDTOList.add(bst);
        }
        return transportScheduleDTOList;
    }
}
