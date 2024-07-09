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
    public void delete(int imageId) {
        Image image = entityManager.find(Image.class,imageId);
        entityManager.remove(image);

    }
}
