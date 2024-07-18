package com.travelbackend.services;

import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dao.ReviewDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.entity.Destination;
import com.travelbackend.entity.Review;
import com.travelbackend.entity.User;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.RefreshFailedException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private DestinationDAO destinationDAO;

    @Autowired
    public ReviewServiceImpl(ReviewDAO reviewDAO,UserDAO userDAO,DestinationDAO destinationDAO){
        this.reviewDAO = reviewDAO;
        this.userDAO = userDAO;
        this.destinationDAO = destinationDAO;
    }

    @Override
    public void saveReview(Review review, int userId) {
        User user = userDAO.findUserById(userId);
        if(user == null){
            throw new ResourceNotFoundException("User Data not found");
        }
        review.setUser(user);
        reviewDAO.save(review);
    }

    @Override
    public List<Review> findAllReview() {
        return reviewDAO.findAll();
    }

    @Override
    public Review findReviewById(int reviewId) {
        return reviewDAO.findReviewById(reviewId);
    }

    @Override
    public void updateReview(Review review, int reviewId, int userId) {
        Review rv = reviewDAO.findReviewById(reviewId);
        if(rv == null){
            throw new ResourceNotFoundException("No Data in Review Table");
        }
        rv.setDescription(review.getDescription());
        rv.setRating(review.getRating());

        User user = userDAO.findUserById(userId);
        if(user == null){
            throw new ResourceNotFoundException("No data in User Table");
        }
        rv.setUser(user);

        reviewDAO.update(rv);
    }

    @Override
    public void deleteReview(int reviewId) {
        Review review = reviewDAO.findReviewById(reviewId);
        if(review != null){
            review.setDelete(true);
        }
        reviewDAO.update(review);
    }
}
