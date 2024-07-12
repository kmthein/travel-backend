package com.travelbackend.dao;

import com.travelbackend.entity.FlightSchedule;
import com.travelbackend.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class FlightScheduleDAOImpl implements FlightScheduleDAO {

    private EntityManager entityManager;

    @Autowired
    public FlightScheduleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(FlightSchedule flightSchedule) {
        entityManager.persist(flightSchedule);
    }

    @Override
    public FlightSchedule findFlightScheduleById(int flightScheduleId) {
        TypedQuery<FlightSchedule> query = entityManager.createQuery("from FlightSchedule f where f.id=:flightScheduleId and f.isDelete=false ", FlightSchedule.class);
        query.setParameter("flightScheduleId",flightScheduleId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new ResourceNotFoundException("The FlightSchedule with ID " + flightScheduleId + " was not found");
        }
    }

    @Override
    public List<FlightSchedule> findAll() {
        TypedQuery<FlightSchedule> query = entityManager.createQuery("from FlightSchedule f where f.isDelete=false ", FlightSchedule.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(FlightSchedule flightSchedule) {
        entityManager.merge(flightSchedule);
    }

    @Override
    @Transactional
    public void delete(int flightScheduleId) {
        FlightSchedule flightSchedule = entityManager.find(FlightSchedule.class, flightScheduleId);
        entityManager.remove(flightSchedule);
    }
}
