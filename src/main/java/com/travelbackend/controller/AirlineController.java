package com.travelbackend.controller;

import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @PostMapping("airlines")
    private ResponseEntity<?> createAirline(@ModelAttribute AirlineDTO airlineDTO){
        airlineService.save(airlineDTO);
        return ResponseEntity.ok("Airline Created");
    }

    @GetMapping("airlines")
    private List<AirLine> getAllAirline(){
        return airlineService.findAll();
    }

    @PutMapping("airlines/{id}")
    private ResponseEntity<?> updateAirline(@PathVariable int id, @ModelAttribute AirlineDTO airlineDTO){

        return ResponseEntity.ok("Updated Airline Successful");
    }

}
