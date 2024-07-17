package com.travelbackend.controllers;

import com.travelbackend.dto.TransportScheduleDTO;
import com.travelbackend.dto.BusScheduleDTO;
import com.travelbackend.dto.BusServiceDTO;
import com.travelbackend.entity.BusSchedule;
import com.travelbackend.services.BusScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class BusScheduleController {

    @Autowired
    private BusScheduleService busScheduleService;

    @PostMapping("bus-schedule")
    private ResponseEntity<?> createFlightSchedule(@ModelAttribute BusSchedule busSchedule, @RequestParam int busId, @RequestParam int departureId, @RequestParam int  arrivalId){
        busScheduleService.create(busSchedule,busId,departureId,arrivalId);
        return ResponseEntity.ok("Created BusSchedule");
    }

    @GetMapping("bus-schedule")
    private List<BusSchedule> getAll(){
        return busScheduleService.getAllBusSchedule();
    }

    @GetMapping("bus-schedule/{id}")
    private BusSchedule getById(@PathVariable int id){
        return busScheduleService.getBusScheduleById(id);
    }

    @PutMapping("bus-schedule/{id}")
    private ResponseEntity<?> createFlightSchedule(@PathVariable int id, @ModelAttribute BusSchedule busSchedule, @RequestParam int busId, @RequestParam int departureId, @RequestParam int  arrivalId){
        busScheduleService.updateBusSchedule(id,busSchedule,busId,departureId,arrivalId);
        return ResponseEntity.ok("Updated BusSchedule");
    }

    @GetMapping("bus-schedule/available-bus")
    private List<TransportScheduleDTO> getAvailableFlight(){
        return busScheduleService.getAvailableFlight();
    }

    @PostMapping("bus-schedule/findBus")
    private List<BusServiceDTO> getAvailableBusSchedule(@ModelAttribute BusScheduleDTO busScheduleDTO) {
        return busScheduleService.getAvailableBusSchedule(busScheduleDTO);
    }
}
