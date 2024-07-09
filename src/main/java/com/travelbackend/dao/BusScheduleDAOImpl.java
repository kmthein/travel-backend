package com.travelbackend.dao;

import com.travelbackend.entity.BusSchedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusScheduleDAOImpl implements BusScheduleDAO{

    private EntityManager entityManager;

    @Autowired
    public BusScheduleDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(BusSchedule busSchedule) {
        entityManager.persist(busSchedule);
    }

    @Override
    public BusSchedule findBusScheduleById(int busScheduleId) {
        return entityManager.find(BusSchedule.class,busScheduleId);
    }

    @Override
    public List<BusSchedule> findAll() {
        TypedQuery<BusSchedule> query = entityManager.createQuery("from BusSchedule", BusSchedule.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(BusSchedule busSchedule) {
        entityManager.merge(busSchedule);
    }

    @Override
    @Transactional
    public void delete(int busScheduleId) {
        BusSchedule busSchedule = entityManager.find(BusSchedule.class,busScheduleId);
        entityManager.remove(busSchedule);
    }
}
