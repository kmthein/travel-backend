package com.travelbackend.services;

import com.travelbackend.dto.FindHotelDTO;
import com.travelbackend.dto.HotelDTO;
import com.travelbackend.dto.HotelListDTO;

import java.util.List;

public interface HotelService {

    List<HotelListDTO> getHotelsFromUser();
    void save(HotelDTO hotelDTO) throws Exception;

    List<HotelDTO> getAll();

    HotelDTO get(int id) throws Exception;

    void delete(int id) throws Exception;

    void update(HotelDTO hotelDTO, int id) throws Exception;

    List<HotelDTO> getAllAvailableHotels(FindHotelDTO findHotelDTO);
}
