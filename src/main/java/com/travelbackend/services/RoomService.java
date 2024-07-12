package com.travelbackend.services;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Room;

import java.util.List;

public interface RoomService {
    ResponseDTO addNewRoom (Room room, List<String> imgUrls,int hotelId);

    List<Room> getAllRooms();
    Room getRoombyId(int id);
    ResponseDTO updateRoom(Room room, int roomId,int hotelId,List <String> imgUrls,List<Integer> deleteImg);

    ResponseDTO deleteById(int id);

}
