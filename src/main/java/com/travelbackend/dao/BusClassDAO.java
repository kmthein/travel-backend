package com.travelbackend.dao;

import com.travelbackend.entity.BusClass;

import java.util.List;

public interface BusClassDAO {

    void save(BusClass busClass);

    BusClass findBusClassById(int busClassId);

    List<BusClass> findAll();

    void update(BusClass busClass);

    void delete(int busClassId);

    public List<BusClass> getBusClassByBus(int busId);
}
