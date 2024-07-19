package com.travelbackend.dao;

import com.travelbackend.dto.HotelVisitCountDTO;
import com.travelbackend.dto.TravelTypeDTO;
import com.travelbackend.dto.UserTravelCountDTO;
import com.travelbackend.entity.TravelPlan;
import com.travelbackend.entity.User;

import java.util.List;

public interface TravelPlanDAO {

    TravelPlan save(TravelPlan travelPlan);

    TravelPlan findTravelPlanById(int travelPlanId);

    List<TravelPlan> findAll();

    void update(TravelPlan travelPlan);

    void delete(int travelPlanId);

    List<User> sendMessageUser();

    List<UserTravelCountDTO> countTravelsByUser();

    TravelTypeDTO getTravelByPercent();

    List<HotelVisitCountDTO> getTopHotelVisit();

    List<Long> totalIncomeByMonth(int year);

    List<Long> newMemberByMonth(int year);

    List<Long> getTotalTravelCountByMonth(int year);
}
