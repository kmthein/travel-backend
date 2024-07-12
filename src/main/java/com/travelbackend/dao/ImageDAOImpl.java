package com.travelbackend.dao;

import com.travelbackend.entity.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ImageDAOImpl implements ImageDAO {

    private EntityManager entityManager;

    @Autowired
    public ImageDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Image image) {
        entityManager.persist(image);
    }

    @Override
    public Image findImageById(int imageId) {
        return entityManager.find(Image.class,imageId);
    }

    @Override
    public List<Image> findAll() {
        TypedQuery<Image> query = entityManager.createQuery("from Image ",Image.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Image image) {
        entityManager.merge(image);
    }

    @Override
    @Transactional
    public Image delete(int airlineId) {
        TypedQuery<Image> query = entityManager.createQuery("from Image i where i.airline.id=:airlineId and i.isDelete =false ",Image.class);
        query.setParameter("airlineId",airlineId);
        return query.getSingleResult();
    }

    @Override
    public Image findbyAirlineid(int airlineId) {
        TypedQuery<Image> query = entityManager.createQuery("from Image i where i.airline.id=:airlineId and i.isDelete =false ",Image.class);
        query.setParameter("airlineId",airlineId);
        return  query.getSingleResult();
    }

    @Override
    public List<Image> findImageByTypeId(String type, int id) {
        TypedQuery<Image> query = entityManager.createQuery("SELECT i FROM Image i WHERE i." + type + ".id = :id AND i.isDelete = false", Image.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
