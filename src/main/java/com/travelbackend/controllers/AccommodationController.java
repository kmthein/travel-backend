package com.travelbackend.controllers;


import com.travelbackend.entity.Accommodation;
import com.travelbackend.services.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class AccommodationController {

    private AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService){
        this.accommodationService = accommodationService;
    }

    @PostMapping("/accommodation")
    public ResponseEntity<?> createAccommodation(@ModelAttribute Accommodation accommodation, @RequestParam int roomId){
        accommodationService.saveAccommodation(accommodation,roomId);
        return ResponseEntity.ok("Inserted Accommodation Data");
    }

    @GetMapping("/accommodation")
    public List<Accommodation> findAllAccommodation(){
        return accommodationService.findAllAccommodation();
    }

    @GetMapping("/accommodation/{id}")
    public Accommodation findAccommodationById(@PathVariable int id){
        return accommodationService.findAccommodationById(id);
    }

    @PutMapping("/accommodation/{id}")
    public ResponseEntity<?> updateAccommodation(@ModelAttribute Accommodation accommodation,@PathVariable int id,@RequestParam int roomId){
        accommodationService.updateAccommodation(accommodation,id,roomId);
        return ResponseEntity.ok("Accommodation Updated! ");
    }

    @PostMapping("/accommodation/{id}")
    public ResponseEntity<?> deleteAccommodation(@PathVariable int id){
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.ok("Accommodation Deleted! ");
    }
}
