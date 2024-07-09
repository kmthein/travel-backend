package com.travelbackend.dao;

import com.travelbackend.entity.BusService;

import java.util.List;

public interface BusServiceDAO {

    void save(BusService busService);

    BusService findBusServiceById(int busServiceId);

    List<BusService> findAll();

    void update(BusService busService);

    void delete(int busServiceId);
}
