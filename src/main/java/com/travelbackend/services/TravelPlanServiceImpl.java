package com.travelbackend.services;

import com.travelbackend.dao.*;
import com.travelbackend.entity.*;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelPlanServiceImpl implements TravelPlanService{

    private TravelPlanDAO travelPlanDAO;
    private BusClassDAO busClassDAO;
    private BusScheduleDAO busScheduleDAO;
    private FlightClassDAO flightClassDAO;
    private FlightScheduleDAO flightScheduleDAO;
    private AccommodationDAO accommodationDAO;
    private UserDAO userDAO;

    @Autowired
    public  TravelPlanServiceImpl(TravelPlanDAO travelPlanDAO,BusClassDAO busClassDAO,BusScheduleDAO busScheduleDAO, FlightClassDAO flightClassDAO,FlightScheduleDAO flightScheduleDAO,AccommodationDAO accommodationDAO,UserDAO userDAO){
        this.travelPlanDAO = travelPlanDAO;
        this.busClassDAO = busClassDAO;
        this.busScheduleDAO = busScheduleDAO;
        this.flightClassDAO = flightClassDAO;
        this.flightScheduleDAO = flightScheduleDAO;
        this.accommodationDAO = accommodationDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void saveTravelPlan(TravelPlan travelPlan,int busClassId,int busScheduleId, int flightClassId, int flightScheduleId, int accommodationId, int userId) {
        BusClass busClass = busClassDAO.findBusClassById(busClassId);
        travelPlan.setBusClass(busClass);

        BusSchedule busSchedule = busScheduleDAO.findBusScheduleById(busScheduleId);
        travelPlan.setBusSchedule(busSchedule);

        FlightClass flightClass = flightClassDAO.findFlightClassById(flightClassId);
        travelPlan.setFlightClass(flightClass);

        FlightSchedule flightSchedule = flightScheduleDAO.findFlightScheduleById(flightScheduleId);
        travelPlan.setFlightSchedule(flightSchedule);

        Accommodation accommodation = accommodationDAO.findAccommodationById(accommodationId);
        travelPlan.setAccommodation(accommodation);

        User user = userDAO.findUserById(userId);
        travelPlan.setUser(user);
        travelPlanDAO.save(travelPlan);
    }

    @Override
    public List<TravelPlan> findAllTravelPlan() {
        return travelPlanDAO.findAll();
    }

    @Override
    public TravelPlan findTravelPlanById(int travelPlanId) {
        return travelPlanDAO.findTravelPlanById(travelPlanId);
    }

    @Override
    public void updateTravelPlan(TravelPlan travelPlan, int travelPlanId, int busClassId, int busScheduleId, int flightClassId, int flightScheduleId, int accommodationId, int userId) {
        TravelPlan tp = travelPlanDAO.findTravelPlanById(travelPlanId);
        if(tp == null){
            throw new ResourceNotFoundException("Travel Plan class not found!");
        }
        tp.setStartDate(travelPlan.getStartDate());
        tp.setEndDate(travelPlan.getEndDate());
        tp.setTotalAmount(travelPlan.getTotalAmount());

        BusClass busClass = busClassDAO.findBusClassById(busClassId);
        tp.setBusClass(busClass);

        BusSchedule busSchedule = busScheduleDAO.findBusScheduleById(busScheduleId);
        tp.setBusSchedule(busSchedule);

        FlightClass flightClass = flightClassDAO.findFlightClassById(flightClassId);
        tp.setFlightClass(flightClass);

        FlightSchedule flightSchedule = flightScheduleDAO.findFlightScheduleById(flightScheduleId);
        tp.setFlightSchedule(flightSchedule);

        Accommodation accommodation = accommodationDAO.findAccommodationById(accommodationId);
        tp.setAccommodation(accommodation);

        User user = userDAO.findUserById(userId);
        tp.setUser(user);
        travelPlanDAO.update(tp);
    }

    @Override
    public void deleteTravelPlan(int travelPlanId) {
        TravelPlan travelPlan = travelPlanDAO.findTravelPlanById(travelPlanId);
        if(travelPlan != null){
            travelPlan.setDelete(true);
        }
        travelPlanDAO.update(travelPlan);
    }
}
