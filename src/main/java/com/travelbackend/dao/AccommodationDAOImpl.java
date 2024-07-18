package com.travelbackend.dao;

import com.travelbackend.entity.Accommodation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccommodationDAOImpl implements  AccommodationDAO{

    private EntityManager entityManager;

    @Autowired
    public AccommodationDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Accommodation save(Accommodation accommodation) {
        entityManager.persist(accommodation);
        return accommodation;
    }

    @Override
    public Accommodation findAccommodationById(int accommodationId) {
        return entityManager.find(Accommodation.class,accommodationId);
    }

    @Override
    public List<Accommodation> findAll() {
        TypedQuery<Accommodation> query = entityManager.createQuery("from Accommodation",Accommodation.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Accommodation accommodation) {
        entityManager.merge(accommodation);
    }

    @Override
    @Transactional
    public void delete(int AccommodationId) {
        Accommodation accommodation = entityManager.find(Accommodation.class,AccommodationId);
        entityManager.remove(accommodation);
    }
}
