package com.jumpstartbackone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.Review;
import com.jumpstartbackone.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public Review createReview(Review review) {
	        return reviewRepository.save(review);
	}
	
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProduct_ProductId(productId);
    }
	
//	public List<Review> getUserReviews(Long userId) {
//	    return reviewRepository.findByUserId(userId);
//	}
	
}
