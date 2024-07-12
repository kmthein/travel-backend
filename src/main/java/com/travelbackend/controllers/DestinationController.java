package com.travelbackend.controllers;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Destination;
import com.travelbackend.exception.ResourceNotFoundException;
import com.travelbackend.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    public DestinationService getDestinationService() {
        return destinationService;
    }

    @GetMapping("/destination-all")
    public ResponseEntity<?> getDestinationWithAll() {
        return null;
    }

    @GetMapping("/destination")
    public ResponseEntity<?> getAllDestinations() {
        List<Destination> destinationList = destinationService.getAllDestinations();
        if (destinationList == null) {
            ResponseDTO res = new ResponseDTO();
            res.setMessage("Destination Not Found.");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(destinationList, HttpStatus.OK);
    }

    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getDestinationById(@PathVariable int id) {
        Destination destination = destinationService.getDestinationById(id);
        if (destination == null) {
            throw new ResourceNotFoundException("Destination id: " + id + " is not found.");
        }
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @PostMapping("/destination")
    public ResponseEntity<?> createNewDestination(@ModelAttribute Destination destination, @RequestParam(value = "img_urls", required = false) List<String> imgUrls) {
        ResponseDTO res = destinationService.addNewDestination(destination, imgUrls);
        return new ResponseEntity<>(res.getMessage(), HttpStatus.CREATED);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDestination(@ModelAttribute Destination destination, @PathVariable int id, @RequestParam(value = "img_urls", required = false) List<String> imgUrls, @RequestParam(value = "delete_ids", required = false) List<Integer> deleteImgIds) {
        destinationService.updateDestination(destination, id, imgUrls, deleteImgIds);
        return new ResponseEntity<>("Destination Updated", HttpStatus.OK);
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<?> deleteDestinationById(@PathVariable int id) {
        ResponseDTO res = destinationService.deleteById(id);
        return new ResponseEntity<>(res.getMessage(), HttpStatus.NO_CONTENT);
    }
}
