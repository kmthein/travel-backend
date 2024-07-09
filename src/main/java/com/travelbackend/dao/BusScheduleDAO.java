package com.travelbackend.dao;

import com.travelbackend.entity.BusSchedule;

import java.util.List;

public interface BusScheduleDAO {

    void save(BusSchedule busSchedule);

    BusSchedule findBusScheduleById(int busScheduleId);

    List<BusSchedule> findAll();

    void update(BusSchedule busSchedule);

    void delete(int busScheduleId);
}
