package com.travelbackend.dao;

import com.travelbackend.entity.Alert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AlertDAOImpl implements AlertDAO{

    private EntityManager entityManager;

    @Autowired
    public AlertDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Alert alert) {
        entityManager.persist(alert);
    }

    @Override
    public Alert findAlertById(int alertId) {
        return entityManager.find(Alert.class,alertId);
    }

    @Override
    public List<Alert> findAll() {
        TypedQuery<Alert> query = entityManager.createQuery("from Alert ",Alert.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Alert alert) {
        entityManager.merge(alert);
    }

    @Override
    @Transactional
    public void delete(int alertId) {
        Alert alert = entityManager.find(Alert.class,alertId);
        entityManager.remove(alert);
    }
}
