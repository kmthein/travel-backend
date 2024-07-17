package com.travelbackend.services;

import com.travelbackend.dao.AccommodationDAO;
import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Accommodation;
import com.travelbackend.entity.TravelPlan;
import com.travelbackend.entity.User;
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

    @Override
    public ResponseDTO save(TravelPlan travelPlan, int accommodationId, int busClassId, int busScheduleId, int flightClassId, int flightScheduleId, int userId) {
        User user = userDAO.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found.");
        }
        travelPlan.setUser(user);
        Accommodation accommodation = accommodationDAO.findAccommodationById(accommodationId);
        if (accommodation == null) {
            throw new ResourceNotFoundException("Accommodation not found.");
        }
        travelPlan.setAccommodation(accommodation);
        LocalDate start_date = LocalDate.now();
//        if (travelPlan.getStartDate() == null) {
//            travelPlan.setStartDate(start_date);
//        }
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
