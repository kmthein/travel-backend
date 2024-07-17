package com.travelbackend.services;

import com.travelbackend.entity.Accommodation;

import java.util.List;

public interface AccommodationService {

    Accommodation saveAccommodation(Accommodation accommodation,int roomId);

    List<Accommodation> findAllAccommodation();

    Accommodation findAccommodationById(int accommodationId);

    void updateAccommodation(Accommodation accommodation,int accommodationId,int roomId);

    void deleteAccommodation(int accommodationId);
}
