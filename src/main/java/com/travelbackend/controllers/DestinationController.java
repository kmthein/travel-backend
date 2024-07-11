package com.travelbackend.controllers;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Destination;
import com.travelbackend.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping("/destination")
    public ResponseEntity<?> createNewDestination(@ModelAttribute Destination destination, @RequestParam(value = "img_urls", required = false) List<String> imgUrls) {
        ResponseDTO res = destinationService.addNewDestination(destination, imgUrls);
        return new ResponseEntity<>(res.getMessage(), HttpStatus.CREATED);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDestination(@ModelAttribute Destination destination, @PathVariable int id, @RequestParam(value = "img_urls", required = false) List<String> imgUrls, @RequestParam(value = "delete_ids", required = false) List<Integer> deleteImgIds) {
        destinationService.updateDestination(destination, id, imgUrls, deleteImgIds);
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
