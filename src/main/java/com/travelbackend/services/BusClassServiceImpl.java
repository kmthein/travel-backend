package com.travelbackend.services;

import com.travelbackend.dao.BusClassDAO;
import com.travelbackend.dao.BusServiceDAO;
import com.travelbackend.entity.BusClass;
import com.travelbackend.entity.BusService;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusClassServiceImpl implements BusClassService{
    private BusClassDAO busClassDAO;
    private BusServiceDAO busServiceDAO;

    @Autowired
    public BusClassServiceImpl(BusClassDAO busClassDAO, BusServiceDAO busServiceDAO){
        this.busClassDAO=busClassDAO;
        this.busServiceDAO=busServiceDAO;
    }

    @Override
    public void saveBusClass(BusClass busClass, int busServiceId) {
        BusService b = busServiceDAO.findBusServiceById(busServiceId);
        if (b == null){
            throw new ResourceNotFoundException("Class not found");

        }
        busClass.setBusService(b);
        busClassDAO.save(busClass);
    }

    @Override
    public List<BusClass> findAllBusclass() {
        return busClassDAO.findAll();
    }

    @Override
    public BusClass findBusclassById(int busclassId) {
        return busClassDAO.findBusClassById(busclassId);
    }

    @Override
    public void updateBusClass(BusClass busClass, int busclassId, int busServiceId) {
        BusClass bc = busClassDAO.findBusClassById(busclassId);
        if (bc == null){
            throw new ResourceNotFoundException("Bus Class not found");
        }
        BusService bs = busServiceDAO.findBusServiceById(busServiceId);
        if (bs == null){
            throw new ResourceNotFoundException("Bus Service not found");
        }
        bc.setName(busClass.getName());
        bc.setPrice(busClass.getPrice());
        bc.setValidSeat(busClass.getValidSeat());
        bc.setBusService(bs);
        busClassDAO.update(bc);
    }

    @Override
    public void deleteBusClass(int id) {
        BusClass bc1 = busClassDAO.findBusClassById(id);
        if (bc1 == null){
            throw new ResourceNotFoundException("BusClass not found");
        }
        bc1.setDelete(true);
        busClassDAO.update(bc1);
        }
    }



