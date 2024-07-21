package com.travelbackend.controllers;

import com.travelbackend.entity.BusClass;
import com.travelbackend.services.BusClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BusClassController {
    private BusClassService busClassService;

    @Autowired
    public BusClassController (BusClassService busClassService){
        this.busClassService = busClassService;
    }


    @PostMapping("/bus-class")
    public ResponseEntity<?> createBusClass(@ModelAttribute BusClass busClass, @RequestParam int busServiceId){
        busClassService.saveBusClass(busClass, busServiceId);
        return ResponseEntity.ok("Busclass Created");
    }

    @GetMapping("/bus-class")
    public List<BusClass> findAllBusclass(){
      return busClassService.findAllBusclass();
    }

    @GetMapping("/bus-class/{id}")
    public BusClass findbyId (@PathVariable int id){
        return busClassService.findBusclassById(id);
    }

    @PutMapping("/bus-class/{id}")
    public ResponseEntity<?> updateBusClass(@ModelAttribute BusClass busClass,@PathVariable int id,@RequestParam int busServiceId ){
        busClassService.updateBusClass(busClass,id,busServiceId);
        return ResponseEntity.ok("Update Successful");
    }

    @DeleteMapping("/bus-class/{id}")
    public String deleteBusClass(@PathVariable int id){
        busClassService.deleteBusClass(id);
        return "Delete Successful";
    }

    @GetMapping("/bus-class/{id}/class")
    public List<BusClass> getFlightClassByAirline(@PathVariable int id){
        return busClassService.getBusClassByBus(id);
    }

}
