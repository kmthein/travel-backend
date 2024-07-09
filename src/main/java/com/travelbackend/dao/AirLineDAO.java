package com.travelbackend.dao;

import com.travelbackend.entity.AirLine;

import java.util.List;

public interface AirLineDAO {

    void save(AirLine airLine);

    AirLine findAirLineById(int airLineId);

    List<AirLine> findAll();

    void update(AirLine airLine);

    void delete(int airLineId);
}
