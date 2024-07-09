package com.travelbackend.dao;

import com.travelbackend.entity.FlightClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class FlightClassDAOImpl implements FlightClassDAO{

    private EntityManager entityManager;

    @Autowired
    public FlightClassDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(FlightClass flightClass) {
        entityManager.persist(flightClass);
    }

    @Override
    public FlightClass findFlightClassById(int flightClassId) {
        return entityManager.find(FlightClass.class,flightClassId);
    }

    @Override
    public List<FlightClass> findAll() {
        TypedQuery<FlightClass> query = entityManager.createQuery("from FlightClass", FlightClass.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(FlightClass flightClass) {
        entityManager.merge(flightClass);
    }

    @Override
    @Transactional
    public void delete(int flightClassId) {
        FlightClass flightClass = entityManager.find(FlightClass.class,flightClassId);
        entityManager.remove(flightClass);
    }
}
