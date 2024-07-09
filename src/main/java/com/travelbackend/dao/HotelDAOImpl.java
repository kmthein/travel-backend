package com.travelbackend.dao;

import com.travelbackend.entity.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HotelDAOImpl implements HotelDAO{

    private EntityManager entityManager;

    @Autowired
    public HotelDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Override
    public Hotel findHotelById(int hotelId) {
        return entityManager.find(Hotel.class,hotelId);
    }

    @Override
    public List<Hotel> findAll() {
        TypedQuery<Hotel> query = entityManager.createQuery("from Hotel ",Hotel.class);
            return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Hotel hotel) {
        entityManager.merge(hotel);
    }

    @Override
    @Transactional
    public void delete(int hotelId) {
        Hotel hotel = entityManager.find(Hotel.class,hotelId);
        entityManager.remove(hotel);
    }
}
