package com.travelbackend.controllers;

import com.travelbackend.dto.PlanDTO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.TravelPlan;
import com.travelbackend.services.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("api")
@CrossOrigin
public class TravelPlanController {
    @Autowired
    private TravelPlanService travelPlanService;

    @GetMapping("/travel-plan/top-member")
    public ResponseEntity<?> getTopMember() {
        return new ResponseEntity<>(travelPlanService.countTravelsByUser(), HttpStatus.OK);
    }

    @GetMapping("/travel-plan/percentage")
    public ResponseEntity<?> getTravelByPercentage() {
        return new ResponseEntity<>(travelPlanService.getTravelByPercent(), HttpStatus.OK);
    }

    @GetMapping("/travel-plan/top-hotels")
    public ResponseEntity<?> getTopVisitedHotels() {
        return new ResponseEntity<>(travelPlanService.getTopHotelVisit(), HttpStatus.OK);
    }

    @PostMapping("/travel-plan/income-month")
    public ResponseEntity<?> totalIncomeByMonth(@RequestParam int year) {
        return new ResponseEntity<>(travelPlanService.totalIncomeByMonth(year), HttpStatus.OK);
    }

    @PostMapping("/travel-plan/member-month")
    public ResponseEntity<?> newMemberByMonth(@RequestParam int year) {
        return new ResponseEntity<>(travelPlanService.newMemberByMonth(year), HttpStatus.OK);
    }

    @PostMapping("/travel-plan/travel-month")
    public ResponseEntity<?> travelsByMonth(@RequestParam int year) {
        return new ResponseEntity<>(travelPlanService.getTotalTravelCountByMonth(year), HttpStatus.OK);
    }

    @PostMapping("/travel-plan")
    public ResponseEntity<?> saveTravelPlan(@ModelAttribute TravelPlan travelPlan, @RequestParam(value = "accommodationId", required = false) Integer accommodationId,
                                            @RequestParam(value = "busClassId", required = false) Integer busClassId,
                                            @RequestParam(value = "busScheduleId", required = false) Integer busScheduleId,
                                            @RequestParam(value = "flightClassId", required = false) Integer flightClassId,
                                            @RequestParam(value = "flightScheduleId", required = false) Integer flightScheduleId,
                                            @RequestParam(value = "userId", required = false) Integer userId) {
        if (accommodationId == null) {
            accommodationId = 0;
        }
        if (busClassId == null) {
            busClassId = 0;
        }
        if (busScheduleId == null) {
            busScheduleId = 0;
        }
        if (flightClassId == null) {
            flightClassId = 0;
        }
        if (flightScheduleId == null) {
            flightScheduleId = 0;
        }
        if (userId == null) {
            userId = 0;
        }
        if (travelPlan.getStartDate() == null) {
            LocalDate start_date = LocalDate.now();
            travelPlan.setStartDate(start_date);
        }
        ResponseDTO res = travelPlanService.save(travelPlan, accommodationId, busClassId, busScheduleId, flightClassId, flightScheduleId, userId);
        if (res.getStatus().equals("403")) {
            return new ResponseEntity<>(res.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(res.getMessage(), HttpStatus.OK);
    }

    @GetMapping("/travel-plan")
    public ResponseEntity<?> getAllTravelPlan(){
        List<PlanDTO> travelPlans = travelPlanService.getAllTravelPlan();
        return new ResponseEntity<>(travelPlans,HttpStatus.OK);
    }
}
