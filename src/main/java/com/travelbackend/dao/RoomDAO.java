package com.travelbackend.dao;

import com.travelbackend.entity.Room;

import java.util.List;

public interface RoomDAO {

    void save(Room room);

    Room findRoomById(int roomId);

    List<Room> findAll();

    void update(Room room);

    void delete(int roomId);
}
