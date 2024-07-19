package com.travelbackend.services;

import com.travelbackend.dao.AccommodationDAO;
import com.travelbackend.dao.RoomDAO;
import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.dto.*;
import com.travelbackend.entity.Accommodation;
import com.travelbackend.entity.Hotel;
import com.travelbackend.entity.TravelPlan;
import com.travelbackend.entity.User;
import com.travelbackend.dao.*;
import com.travelbackend.entity.*;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccommodationDAO accommodationDAO;

    @Autowired
    private TravelPlanDAO travelPlanDAO;

    @Autowired
    private RoomDAO roomDAO;

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

    @Override
    public List<PlanDTO> getAllTravelPlan() {
        List<TravelPlan> travelPlans = travelPlanDAO.findAll();
        List<PlanDTO> plans = new ArrayList<>();
        for(TravelPlan travelPlan : travelPlans){
            PlanDTO planDTO = new PlanDTO();
            planDTO.setId(travelPlan.getId());
            planDTO.setUsername(travelPlan.getUser().getUsername());
            planDTO.setStatus(travelPlan.getStatus());
            planDTO.setStartDate(travelPlan.getStartDate());
            planDTO.setTotalPrice(travelPlan.getTotalPrice());
            if(travelPlan.getFlightSchedule() != null && travelPlan.getFlightClass() != null){
                planDTO.setArrivalPlace(travelPlan.getFlightSchedule().getArrivalPlace().getName());
                planDTO.setDeparturePlace(travelPlan.getFlightSchedule().getDeparturePlace().getName());
                planDTO.setAirlineClass(travelPlan.getFlightClass().getName());
                planDTO.setAirlineName(travelPlan.getFlightClass().getAirline().getName());
            }
            if(travelPlan.getBusClass() != null && travelPlan.getBusSchedule() != null){
                planDTO.setArrivalPlace(travelPlan.getBusSchedule().getArrivalPlace().getName());
                planDTO.setDeparturePlace(travelPlan.getBusSchedule().getDeparturePlace().getName());
                planDTO.setBusClass(travelPlan.getBusClass().getName());
                planDTO.setBusName(travelPlan.getBusClass().getBusService().getName());
            }
            if(travelPlan.getAccommodation() != null){
                int roomId = travelPlan.getAccommodation().getRoom().getId();
                Hotel hotel = roomDAO.findHotelNameByRoomId(roomId);
                if(hotel != null){
                    planDTO.setHotelName(hotel.getName());
                    planDTO.setArrivalPlace(hotel.getDestination().getName());
                }
            }
            plans.add(planDTO);
        }
        return plans;
    }

    @Override
    public List<UserTravelCountDTO> countTravelsByUser() {
        return travelPlanDAO.countTravelsByUser();
    }

    @Override
    public TravelTypeDTO getTravelByPercent() {
        return travelPlanDAO.getTravelByPercent();
    }

    @Override
    public List<HotelVisitCountDTO> getTopHotelVisit() {
        return travelPlanDAO.getTopHotelVisit();
    }

    @Override
    public List<Long> totalIncomeByMonth(int year) {
        return travelPlanDAO.totalIncomeByMonth(year);
    }

    @Override
    public List<Long> newMemberByMonth(int year) {
        return travelPlanDAO.newMemberByMonth(year);
    }

    @Override
    public List<Long> getTotalTravelCountByMonth(int year) {
        return travelPlanDAO.getTotalTravelCountByMonth(year);
    }
}
