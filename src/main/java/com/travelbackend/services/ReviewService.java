package com.travelbackend.services;

import com.travelbackend.dto.ReviewDTO;
import com.travelbackend.entity.Review;

import java.util.List;

public interface ReviewService {

    void saveReview(Review review,int userId);

    List<ReviewDTO> findAllReview();

    Review findReviewById(int reviewId);

    void updateReview(Review review,int ReviewId,int userId);

    void deleteReview(int reviewId);
}
