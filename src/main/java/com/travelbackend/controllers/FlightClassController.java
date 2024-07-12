package com.travelbackend.controllers;
import com.travelbackend.dao.FlightClassDAOImpl;
import com.travelbackend.entity.FlightClass;
import com.travelbackend.exception.ErrorResponse;
import com.travelbackend.services.FlightClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class FlightClassController {

    private FlightClassService flightClassService;

    @Autowired
    public FlightClassController(FlightClassService flightClassService){
        this.flightClassService = flightClassService;
    }

    @PostMapping("/flight-class")
    public ResponseEntity<?> createFlightClass(@ModelAttribute FlightClass flightClass,@RequestParam int airlineId){
        flightClassService.saveFlightClass(flightClass,airlineId);
        return ResponseEntity.ok("Created Fligh-Class");
    }

    @GetMapping("/flight-class")
    public List<FlightClass> findAllFlightClasses(){
        return flightClassService.findAllFlightClass();
    }

    @GetMapping("/flight-class/{id}")
    public FlightClass findFlightClassById(@PathVariable int id){
        return flightClassService.findFlightClassById(id);
    }

    @PutMapping("/flight-class/{id}")
    public ResponseEntity<?> updateFlightClass(@ModelAttribute FlightClass flightClass,@PathVariable int id,@RequestParam int airlineId){
        flightClassService.updateFlightClass(flightClass,id,airlineId);
        return ResponseEntity.ok("Update FlightClass");
    }

    @PostMapping("/flight-class/{id}")
    public String deleteFlightClass(@PathVariable int id){
        flightClassService.deleteFlightClass(id);
        return "Delete flight-CLass";
    }
}
