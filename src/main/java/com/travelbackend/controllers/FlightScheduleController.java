package com.travelbackend.controllers;

import com.travelbackend.dto.TransportScheduleDTO;
import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.dto.FlightScheduleDTO;
import com.travelbackend.entity.FlightSchedule;
import com.travelbackend.services.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class FlightScheduleController {

    @Autowired
    private FlightScheduleService flightScheduleService;

    @PostMapping("flight-schedule")
    private ResponseEntity<?> createFlightSchedule(@ModelAttribute FlightSchedule flightSchedule,@RequestParam int airlineId, @RequestParam int departureId, @RequestParam int  arrivalId){
        flightScheduleService.create(flightSchedule,airlineId,departureId,arrivalId);
        return ResponseEntity.ok("Created FlightSchedule");
    }

    @GetMapping("flight-schedule")
    private List<FlightSchedule> getAll(){
        return flightScheduleService.getAllFlightSchedule();
    }

    @GetMapping("flight-schedule/{id}")
    private FlightSchedule getById(@PathVariable int id){
        return  flightScheduleService.getFlightScheduleById(id);
    }

    @PutMapping("flight-schedule/{id}")
    private ResponseEntity<?> update(@PathVariable int id,@ModelAttribute FlightSchedule flightSchedule,@RequestParam int airlineId, @RequestParam int departureId, @RequestParam int  arrivalId){
        flightScheduleService.updateFlightSchedule(id,flightSchedule,airlineId,departureId,arrivalId);
        return ResponseEntity.ok("Updated AirlineSchedule");
    }

    @GetMapping("flight-schedule/available-flight")
    private List<TransportScheduleDTO> getAvailableFlight() {
        return flightScheduleService.getAvailableFlight();
    }
    @PostMapping("flight-schedule/findFlight")
    private List<AirlineDTO> getAvailableBusSchedule(@ModelAttribute FlightScheduleDTO flightScheduleDTO) {
        return flightScheduleService.getAvailableFlightSchedule(flightScheduleDTO);
    }
}
