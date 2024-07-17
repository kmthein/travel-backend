package com.travelbackend.services;

import com.travelbackend.controllers.BusClassController;
import com.travelbackend.entity.BusClass;

import java.util.List;

public interface BusClassService {
    void saveBusClass(BusClass busClass,int busServiceId);

    List<BusClass> findAllBusclass();

    BusClass findBusclassById(int id);

    void updateBusClass(BusClass busClass,int busclassId, int busServiceId);

    void deleteBusClass(int id);

    List<BusClass> findBusClassByBusServiceId(int busServiceId);
}
