package com.travelbackend.dao;

import com.travelbackend.entity.Destination;
import com.travelbackend.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DestinationDAOImpl implements DestinationDAO {

    private EntityManager entityManager;

    @Override
    public List<Destination> searchByKeyword(String keyword) {
        TypedQuery<Destination> query = entityManager.createQuery("SELECT d FROM Destination d WHERE d.name LIKE :keyword AND d.isDelete = false    ", Destination.class);
        List<Destination> searchResult = query.setParameter("keyword", "%" + keyword + "%").getResultList();
        System.out.println(searchResult.size());
        return searchResult;
    }

    @Autowired
    public DestinationDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Destination destination) {
        entityManager.persist(destination);
    }

    @Override
    public Destination findDestinationById(int destinationId) {
        TypedQuery<Destination> query = entityManager.createQuery("from Destination d where d.id=:destinationId and d.isDelete = false ",Destination.class);
        query.setParameter("destinationId",destinationId);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new ResourceNotFoundException("The Destination with ID " + destinationId + " was not found");
        }

    }

    @Override
    public List<Destination> findAll() {
        TypedQuery<Destination> query = entityManager.createQuery("SELECT d from Destination d WHERE d.isDelete = false",Destination.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Destination destination) {
        entityManager.merge(destination);
    }

    @Override
    @Transactional
    public void delete(int destinationId) {
        Destination destination = entityManager.find(Destination.class,destinationId);
        entityManager.remove(destination);
    }
}
