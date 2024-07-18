package com.travelbackend.controllers;

import com.travelbackend.entity.Review;
import com.travelbackend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<?> createReview(@ModelAttribute Review review,@RequestParam int userId){
        reviewService.saveReview(review, userId);
        return ResponseEntity.ok("Inserted Review!");
    }

    @GetMapping("/review")
    public List<Review> findAllReview(){
        return reviewService.findAllReview();
    }

    @GetMapping("/review/{id}")
    public Review findReviewById(@PathVariable int id){
        return reviewService.findReviewById(id);
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<?> updateReview(@ModelAttribute Review review,@PathVariable int id,@RequestParam int userId){
        reviewService.updateReview(review,id,userId);
        return ResponseEntity.ok("Review Updated! ");
    }

    @PostMapping("/review/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review Deleted!");
    }
}
