package com.travelbackend.dao;

import com.travelbackend.entity.BusService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusServiceDAOImpl implements BusServiceDAO {

    private EntityManager entityManager;

    @Autowired
    public BusServiceDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(BusService busService) {
        entityManager.persist(busService);
    }

    @Override
    public BusService findBusServiceById(int busServiceId) {
        return entityManager.find(BusService.class,busServiceId);
    }

    @Override
    public List<BusService> findAll() {
        TypedQuery<BusService> query = entityManager.createQuery("from BusService",BusService.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(BusService busService) {
        entityManager.merge(busService);
    }

    @Override
    @Transactional
    public void delete(int busServiceId) {
        BusService busService = entityManager.find(BusService.class,busServiceId);
        entityManager.remove(busService);
    }
}
