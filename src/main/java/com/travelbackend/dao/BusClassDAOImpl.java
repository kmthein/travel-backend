package com.travelbackend.dao;

import com.travelbackend.entity.BusClass;
import com.travelbackend.entity.FlightClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusClassDAOImpl implements BusClassDAO{

    private EntityManager entityManager;

    @Autowired
    public BusClassDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(BusClass busClass) {
        entityManager.persist(busClass);
    }

    @Override
    public BusClass findBusClassById(int busClassId) {
        return entityManager.find(BusClass.class,busClassId);
    }

    @Override
    public List<BusClass> findAll() {
        TypedQuery<BusClass> query = entityManager.createQuery("from BusClass ", BusClass.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(BusClass busClass) {
        entityManager.merge(busClass);
    }

    @Override
    @Transactional
    public void delete(int busClassId) {
        BusClass busClass = entityManager.find(BusClass.class,busClassId);
        entityManager.remove(busClass);
    }

    @Override
    public List<BusClass> getBusClassByBus(int busId) {
        TypedQuery<BusClass> query = entityManager.createQuery("from BusClass fc where fc.busService.id=:busId", BusClass.class);
        query.setParameter("busId",busId);
        return query.getResultList();
    }
}
