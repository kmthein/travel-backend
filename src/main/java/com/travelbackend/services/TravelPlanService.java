package com.travelbackend.services;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.TravelPlan;

public interface TravelPlanService {
    ResponseDTO save(TravelPlan travelPlan, int accommodationId, int busClassId, int busScheduleId, int flightClassId, int flightScheduleId, int userId);
}
