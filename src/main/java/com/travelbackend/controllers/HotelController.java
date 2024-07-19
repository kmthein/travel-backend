package com.travelbackend.controllers;

import com.travelbackend.dto.FindHotelDTO;
import com.travelbackend.dto.HotelDTO;
import com.travelbackend.dto.HotelListDTO;
import com.travelbackend.dto.RoomDTO;
import com.travelbackend.entity.Hotel;
import com.travelbackend.services.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@ModelAttribute HotelDTO hotelDTO) throws Exception {

        hotelService.save(hotelDTO);
        return ResponseEntity.ok("Hotel created");
    }

    @GetMapping("all")
    public ResponseEntity<List<HotelListDTO>> getHotelsFromUser(){
        return ResponseEntity.ok(hotelService.getHotelsFromUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute HotelDTO hotelDTO, @PathVariable int id) throws Exception {

        hotelService.update(hotelDTO, id);
        return ResponseEntity.ok("Hotel updated");
    }

    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> getAll(){
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> get(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(hotelService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        hotelService.delete(id);
        return ResponseEntity.ok("Hotel deleted");
    }

    @PostMapping("all")
    public ResponseEntity<List<HotelDTO>> getAllAvailableHotels(FindHotelDTO findHotelDTO){
        System.out.println(findHotelDTO);
        return ResponseEntity.ok(hotelService.getAllAvailableHotels(findHotelDTO));
    }

    @GetMapping("/{id}/room")
    public ResponseEntity<List<RoomDTO>> getRoomByHotelId(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(hotelService.getRoomByHotelId(id));
    }

}
