package com.travelbackend.services;

import com.travelbackend.dto.PlanDTO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.TravelPlan;

import java.util.List;

public interface TravelPlanService {
    ResponseDTO save(TravelPlan travelPlan, int accommodationId, int busClassId, int busScheduleId, int flightClassId, int flightScheduleId, int userId);

    List<PlanDTO> getAllTravelPlan();
}
