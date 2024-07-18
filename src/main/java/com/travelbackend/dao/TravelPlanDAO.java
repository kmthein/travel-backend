package com.travelbackend.dao;

import com.travelbackend.entity.TravelPlan;

import java.util.List;

public interface TravelPlanDAO {

    TravelPlan save(TravelPlan travelPlan);

    TravelPlan findTravelPlanById(int travelPlanId);

    List<TravelPlan> findAll();

    void update(TravelPlan travelPlan);

    void delete(int travelPlanId);

    List<String> sendMessageUser();
}
