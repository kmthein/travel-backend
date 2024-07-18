package com.travelbackend.dao;

import com.travelbackend.entity.BusSchedule;
import com.travelbackend.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        TypedQuery<BusSchedule> query = entityManager.createQuery("from BusSchedule b where b.id=:busScheduleId and b.isDelete=false ", BusSchedule.class);
        query.setParameter("busScheduleId",busScheduleId);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new ResourceNotFoundException("The BusSchedule with ID " + busScheduleId + " was not found");
        }

    }

    @Override
    public List<BusSchedule> findAll() {
        TypedQuery<BusSchedule> query = entityManager.createQuery("from BusSchedule b where b.isDelete=false ", BusSchedule.class);
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

    @Override
    public List<BusSchedule> availableBus() {
        TypedQuery<BusSchedule> query = entityManager.createQuery("from BusSchedule f where f.isDelete=false and f.date >=:date", BusSchedule.class);
        query.setParameter("date", LocalDate.now());
        return query.getResultList();
    }

    @Override
    public List<BusSchedule> findbyDestinationId(int desId) {
        TypedQuery<BusSchedule> query = entityManager.createQuery("from BusSchedule b where b.arrivalPlace.id=:desId and b.isDelete=false ", BusSchedule.class);
        query.setParameter("desId",desId);
        try{
            return query.getResultList();
        }catch (NoResultException e){
            throw new ResourceNotFoundException("The BusSchedule with ID " + desId + " was not found");
        }
    }
}
