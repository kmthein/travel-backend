package com.travelbackend.dao;

import com.travelbackend.entity.AirLine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class AirLineDAOImpl implements AirLineDAO{

    private EntityManager entityManager;

    @Autowired
    public  AirLineDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(AirLine airLine) {
        entityManager.persist(airLine);
    }

    @Override
    public AirLine findAirLineById(int airLineId) {
        return entityManager.find(AirLine.class,airLineId);
    }

    @Override
    public List<AirLine> findAll() {
        TypedQuery<AirLine> query = entityManager.createQuery("from AirLine", AirLine.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(AirLine airLine) {
        entityManager.merge(airLine);
    }

    @Override
    @Transactional
    public void delete(int airLineId) {
        AirLine airLine = entityManager.find(AirLine.class,airLineId);
        entityManager.remove(airLine);
    }
}
