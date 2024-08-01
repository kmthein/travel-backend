package com.travelbackend.services;

import com.travelbackend.dto.FindRoomDTO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.dto.RoomDTO;
import com.travelbackend.entity.Room;

import java.util.List;

public interface RoomService {
    ResponseDTO addNewRoom (RoomDTO roomDTO) throws Exception;

    List<Room> getAllRooms();
    Room getRoombyId(int id);
    ResponseDTO updateRoom(Room room, int roomId,int hotelId,List <String> imgUrls,List<Integer> deleteImg);

    ResponseDTO deleteById(int id);

    RoomDTO getAvailableRoom(FindRoomDTO findRoomDTO);

    ResponseDTO editRoom(RoomDTO roomDTO, int id) throws Exception;
}
