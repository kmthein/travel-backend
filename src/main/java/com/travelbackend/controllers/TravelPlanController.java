package com.travelbackend.controllers;

import com.travelbackend.entity.TravelPlan;
import com.travelbackend.services.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class TravelPlanController {

    private TravelPlanService travelPlanService;

    @Autowired
    public TravelPlanController(TravelPlanService travelPlanService){
        this.travelPlanService = travelPlanService;
    }

    @PostMapping("/travel-plan")
    public ResponseEntity<?> createTravelPlan(@ModelAttribute TravelPlan travelPlan,@RequestParam int busClassId,@RequestParam int busScheduleId,@RequestParam int flightClassId,@RequestParam int flightScheduleId,@RequestParam int accommodationId,@RequestParam int userId){
        travelPlanService.saveTravelPlan(travelPlan,busClassId,busScheduleId,flightClassId,flightScheduleId,accommodationId,userId);
        return ResponseEntity.ok("Inserted Travel Plan");
    }

    @GetMapping("/travel-plan")
    public List<TravelPlan> findAllTravelPlan(){
        return travelPlanService.findAllTravelPlan();
    }

    @GetMapping("/travel-plan/{id}")
    public TravelPlan findTravelPlanById(@PathVariable int id){
        return travelPlanService.findTravelPlanById(id);
    }

    @PutMapping("/travel-plan/{id}")
    public ResponseEntity<?> updateTravelPlan(@ModelAttribute TravelPlan travelPlan,@PathVariable int id,@RequestParam int busClassId,@RequestParam int busScheduleId,@RequestParam int flightClassId,@RequestParam int flightScheduleId,@RequestParam int accommodationId,@RequestParam int userId){
        travelPlanService.updateTravelPlan(travelPlan,id,busClassId,busScheduleId,flightClassId,flightScheduleId,accommodationId,userId);
        return ResponseEntity.ok("Updated Travel Plan!");
    }

    @PostMapping("/travel-plan/{id}")
    public ResponseEntity<?> deleteTravelPlan(@PathVariable int id){
        travelPlanService.deleteTravelPlan(id);
        return ResponseEntity.ok("Delete Travel Plan");
    }
}
