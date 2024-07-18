package com.travelbackend.dao;

import com.travelbackend.entity.Hotel;
import com.travelbackend.entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO{

    private EntityManager entityManager;

    @Autowired
    public RoomDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Room room) {
        entityManager.persist(room);
    }

    @Override
    public Room findRoomById(int roomId) {
        return entityManager.find(Room.class,roomId);
    }

    @Override
    public List<Room> findAll() {
        TypedQuery<Room> query = entityManager.createQuery("from Room ",Room.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Room room) {
        entityManager.merge(room);
    }

    @Override
    @Transactional
    public void delete(int roomId) {
        Room room = entityManager.find(Room.class,roomId);
        entityManager.remove(room);
    }

    @Override
    public Hotel findHotelNameByRoomId(int roomId) {
        TypedQuery<Hotel> query = entityManager.createQuery(
                "select r.hotel from Room r where r.id = :roomId",
                Hotel.class
        );
        query.setParameter("roomId", roomId);
        return query.getSingleResult();
    }
}
