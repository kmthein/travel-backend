package com.travelbackend.dao;

import com.travelbackend.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO{

    private EntityManager entityManager;

    @Autowired
    public ReviewDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Review review) {
        entityManager.persist(review);
    }

    @Override
    public Review findReviewById(int reviewId) {
        return entityManager.find(Review.class,reviewId);
    }

    @Override
    public List<Review> findAll() {
        TypedQuery<Review> query = entityManager.createQuery("from Review ",Review.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Review review) {
        entityManager.merge(review);
    }

    @Override
    @Transactional
    public void delete(int reviewId) {
        Review review = entityManager.find(Review.class,reviewId);
        entityManager.remove(review);
    }
}
