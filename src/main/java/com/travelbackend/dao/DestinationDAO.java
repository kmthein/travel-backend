package com.travelbackend.dao;

import com.travelbackend.entity.Destination;

import java.util.List;

public interface DestinationDAO {

    void save(Destination destination);
    List<Destination> searchByKeyword(String keyword);
    Destination findDestinationById(int destinationId);

    List<Destination> findAll();

    void update(Destination destination);

    void delete(int destinationId);
}
