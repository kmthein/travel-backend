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

    @GetMapping("airlines/{id}")
    private AirLine getById(@PathVariable int id){
        return airlineService.getById(id);
    }

    @PutMapping("airlines/{id}")
    private ResponseEntity<?> updateAirline(@PathVariable int id, @ModelAttribute AirlineDTO airlineDTO){
        airlineService.update(airlineDTO,id);
        return ResponseEntity.ok("Updated Airline Successful");
    }

}
