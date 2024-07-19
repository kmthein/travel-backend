package com.travelbackend.dao;

import com.travelbackend.entity.Review;

import java.util.List;

public interface ReviewDAO {

    void save(Review review);

    Review findReviewById(int reviewId);

    List<Review> findAll();

    void update(Review review);

    void delete(int reviewId);
}
