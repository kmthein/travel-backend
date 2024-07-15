package com.travelbackend.services;

import com.travelbackend.entity.TravelPlan;

import java.util.List;

public interface TravelPlanService {

    void saveTravelPlan(TravelPlan travelPlan,int busClassId,int busScheduleId, int flightClassId, int flightScheduleId, int accommodationId, int userId);

    List<TravelPlan> findAllTravelPlan();

    TravelPlan findTravelPlanById(int travelPlanId);

    void updateTravelPlan(TravelPlan travelPlan,int travelPlanId,int busClassId,int busScheduleId, int flightClassId, int flightScheduleId, int accommodationId, int userId);

    void deleteTravelPlan(int travelPlanId);
}