package com.travelbackend.dao;

import com.travelbackend.entity.Hotel;

import java.util.List;

public interface HotelDAO {

    List<Hotel> findAllJoin();
    void save(Hotel hotel);

    Hotel findHotelById(int hotelId);

    List<Hotel> findAll();

    void update(Hotel hotel);

    void delete(int hotelId);

    List<Hotel> findByDestinationId(int desId);
}
