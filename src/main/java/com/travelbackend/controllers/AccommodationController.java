package com.travelbackend.controllers;


import com.travelbackend.dto.ResponseDTO;
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
        Accommodation temp = accommodationService.saveAccommodation(accommodation,roomId);
        ResponseDTO res = new ResponseDTO();
        res.setId(temp.getId());
        res.setMessage("Accommodation made successfully.");
        return ResponseEntity.ok(res);
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
