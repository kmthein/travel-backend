package com.travelbackend.dao;

import com.travelbackend.entity.Accommodation;

import java.util.List;

public interface AccommodationDAO {

    void save(Accommodation accommodation);

    Accommodation findAccommodationById(int accommodationId);

    List<Accommodation> findAll();

    void update(Accommodation accommodation);

    void delete(int AccommodationId);
}
