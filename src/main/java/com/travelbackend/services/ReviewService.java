package com.travelbackend.services;

import com.travelbackend.entity.Destination;
import com.travelbackend.entity.Review;

import java.util.List;

public interface ReviewService {

    void saveReview(Review review,int userId);

    List<Review> findAllReview();

    Review findReviewById(int reviewId);

    void updateReview(Review review,int ReviewId,int userId);

    void deleteReview(int reviewId);
}
