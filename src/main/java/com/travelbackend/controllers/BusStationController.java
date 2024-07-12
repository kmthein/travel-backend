package com.travelbackend.controllers;

import com.travelbackend.dto.TransportDTO;
import com.travelbackend.entity.BusService;
import com.travelbackend.services.BusStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class BusStationController {

    @Autowired
    private BusStationService busStationService;

    @PostMapping("/bus-services")
    private ResponseEntity<?> createBusServices(TransportDTO transportDTO){
        busStationService.save(transportDTO);
        return ResponseEntity.ok("Bus Service Saved");
    }

    @GetMapping("/bus-services")
    private List<BusService> getAllAirline(){
        return busStationService.findAll();
    }

    @GetMapping("/bus-services/{id}")
    private BusService getBusById(@PathVariable int id){
        return busStationService.getById(id);
    }

    @PutMapping("/bus-services/{id}")
    private ResponseEntity<?> updateBusService(@PathVariable int id,TransportDTO transportDTO){
        busStationService.updateBusService(id,transportDTO);
        return ResponseEntity.ok("Updated bus services");
    }
}
