package com.travelbackend.services;

import com.travelbackend.dto.*;
import com.travelbackend.entity.TravelPlan;

import java.util.List;

public interface TravelPlanService {
    ResponseDTO save(TravelPlan travelPlan, int accommodationId, int busClassId, int busScheduleId, int flightClassId, int flightScheduleId, int userId);
    List<PlanDTO> getAllTravelPlan();
    List<UserTravelCountDTO> countTravelsByUser();
    TravelTypeDTO getTravelByPercent();
    List<HotelVisitCountDTO> getTopHotelVisit();
    List<Long> totalIncomeByMonth(int year);
    List<Long> newMemberByMonth(int year);
    List<Long> getTotalTravelCountByMonth(int year);
}
