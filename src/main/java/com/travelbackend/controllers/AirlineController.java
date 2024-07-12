package com.travelbackend.controllers;

import com.travelbackend.dto.TransportDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @PostMapping("airlines")
    private ResponseEntity<?> createAirline(@ModelAttribute TransportDTO transportDTO){
        airlineService.save(transportDTO);
        return ResponseEntity.ok("Airline Created");
    }

    @GetMapping("airlines")
    private List<AirLine> getAllAirline(){
        return airlineService.findAll();
    }

    @GetMapping("airlines/{id}")
    private AirLine getById(@PathVariable int id){
        return airlineService.getById(id);
    }

    @PutMapping("airlines/{id}")
    private ResponseEntity<?> updateAirline(@PathVariable int id, @ModelAttribute TransportDTO transportDTO){
        airlineService.update(id, transportDTO);
        return ResponseEntity.ok("Updated Airline Successful");
    }
    @DeleteMapping("airlines/{id}")
    private ResponseEntity<?> deleteAirline(@PathVariable int id){
        airlineService.deleteAirline(id);
        return ResponseEntity.ok("Deleted Airline");
    }

}
