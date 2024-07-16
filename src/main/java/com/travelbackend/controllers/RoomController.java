package com.travelbackend.controllers;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.dto.RoomDTO;
import com.travelbackend.entity.Room;
import com.travelbackend.exception.ResourceNotFoundException;
import com.travelbackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@ModelAttribute RoomDTO roomDTO) throws Exception {
        ResponseDTO res = roomService.addNewRoom(roomDTO);
        return new ResponseEntity<>(res.getMessage(), HttpStatus.CREATED);
    }
    @GetMapping("/room")
    public ResponseEntity<?> getAllRooms(){
        List<Room> roomList = roomService.getAllRooms();
        if (roomList == null){
            ResponseDTO res = new ResponseDTO();
            res.setMessage("Room not found");
            return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomList,HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable int id){
        Room room = roomService.getRoombyId(id);
        if (room == null){
            throw new ResourceNotFoundException("Room id" + id + "is not found!!");
        }
        return new ResponseEntity<>(room,HttpStatus.OK);
    }
    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@ModelAttribute Room room, @PathVariable int id, @RequestParam int hotelId, @RequestParam(value = "img_urls", required = false)List<String> imgUrls, @RequestParam(value = "delete_ids", required = false)List<Integer> deleteImg){
        roomService.updateRoom(room,id,hotelId,imgUrls,deleteImg);
        return new ResponseEntity<>("Room Updated",HttpStatus.OK);
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteroomById(@PathVariable int id){
        ResponseDTO responseDTO = roomService.deleteById(id);
        return new ResponseEntity<>("User Deleted",HttpStatus.OK);
    }


}
