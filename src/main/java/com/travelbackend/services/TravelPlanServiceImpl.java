package com.travelbackend.services;

import com.travelbackend.dao.*;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.*;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccommodationDAO accommodationDAO;

    @Autowired
    private TravelPlanDAO travelPlanDAO;

    @Autowired
    private BusClassDAO busClassDAO;

    @Autowired
    private BusScheduleDAO busScheduleDAO;

    @Autowired
    private FlightClassDAO flightClassDAO;

    @Autowired
    private FlightScheduleDAO flightScheduleDAO;

    @Override
    public ResponseDTO save(TravelPlan travelPlan, int accommodationId, int busClassId, int busScheduleId, int flightClassId, int flightScheduleId, int userId) {
        User user = userDAO.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found.");
        }
        travelPlan.setUser(user);
        if (accommodationId != 0) {
            Accommodation accommodation = accommodationDAO.findAccommodationById(accommodationId);
            if (accommodation == null) {
                throw new ResourceNotFoundException("Accommodation not found.");
            }
            travelPlan.setAccommodation(accommodation);
        }
        if (busClassId != 0) {
            BusClass busClass = busClassDAO.findBusClassById(busClassId);
            if (busClass != null) {
                travelPlan.setBusClass(busClass);
            }
        }
        if (busScheduleId != 0) {
            BusSchedule busSchedule = busScheduleDAO.findBusScheduleById(busScheduleId);
            if (busSchedule != null) {
                travelPlan.setBusSchedule(busSchedule);
            }
        }
        if (flightClassId != 0) {
            FlightClass flightClass = flightClassDAO.findFlightClassById(flightClassId);
            if (flightClass != null) {
                travelPlan.setFlightClass(flightClass);
            }
        }
        if (flightScheduleId != 0) {
            FlightSchedule flightSchedule = flightScheduleDAO.findFlightScheduleById(flightScheduleId);
            if (flightSchedule != null) {
                travelPlan.setFlightSchedule(flightSchedule);
            }
        }
        travelPlan.setStatus("active");
        TravelPlan tempTravelPlan = travelPlanDAO.save(travelPlan);
        ResponseDTO res = new ResponseDTO();
        if (tempTravelPlan == null) {
            res.setMessage("You can't access to make travel plan");
            res.setStatus("403");
            return res;
        }
        res.setMessage("Successfully made a travel plan");
        res.setStatus("201");
        return res;
    }
}
