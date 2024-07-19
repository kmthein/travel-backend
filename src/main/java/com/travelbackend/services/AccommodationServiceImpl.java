package com.travelbackend.services;

import com.travelbackend.dao.AccommodationDAO;
import com.travelbackend.dao.RoomDAO;
import com.travelbackend.entity.Accommodation;
import com.travelbackend.entity.Room;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService{

    private AccommodationDAO accommodationDAO;

    private RoomDAO roomDAO;

    @Autowired
    public AccommodationServiceImpl(AccommodationDAO accommodationDAO,RoomDAO roomDAO){
        this.accommodationDAO = accommodationDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    public Accommodation saveAccommodation(Accommodation accommodation, int roomId) {
        Room room = roomDAO.findRoomById(roomId);
        if(room == null){
            throw new ResourceNotFoundException("There are no available rooms in a hotel!");
        }
        accommodation.setRoom(room);
        Accommodation temp = accommodationDAO.save(accommodation);
        return temp;
    }

    @Override
    public List<Accommodation> findAllAccommodation() {
        return accommodationDAO.findAll();
    }

    @Override
    public Accommodation findAccommodationById(int accommodationId) {
        return accommodationDAO.findAccommodationById(accommodationId);
    }

    @Override
    public void updateAccommodation(Accommodation accommodation, int accommodationId, int roomId) {
        Accommodation accommo = accommodationDAO.findAccommodationById(accommodationId);
        if(accommo == null){
            throw new ResourceNotFoundException("No data in Accommodation Table");
        }
        accommo.setCheckIn(accommodation.getCheckIn());
        accommo.setCheckOut(accommodation.getCheckOut());
        accommo.setTotalPerson(accommodation.getTotalPerson());
        accommo.setTotalPrice(accommodation.getTotalPrice());
        Room room = roomDAO.findRoomById(roomId);
        if(room == null){
            throw new ResourceNotFoundException("No free Room! Please kindly take another!");
        }
        accommo.setRoom(room);
        accommodationDAO.update(accommo);
    }

    @Override
    public void deleteAccommodation(int accommodationId) {
        Accommodation accommodation = accommodationDAO.findAccommodationById(accommodationId);
        if(accommodation != null){
           accommodation.setDelete(true);
        }
        accommodationDAO.update(accommodation);
    }
}
