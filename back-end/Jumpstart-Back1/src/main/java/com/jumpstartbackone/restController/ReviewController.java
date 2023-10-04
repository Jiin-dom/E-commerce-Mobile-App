package com.jumpstartbackone.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.Review;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.UserRepository;
import com.jumpstartbackone.service.PointsService;
import com.jumpstartbackone.service.ProductService;
import com.jumpstartbackone.service.ReviewService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ReviewController {
	
	@Autowired
    private ReviewService reviewService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PointsService pointsService;
	
	@Autowired
	UserRepository userRepository;
	
    @PostMapping("/review")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
    public ResponseEntity<Review> createReview( @RequestParam Long productId, @RequestBody Review review) {
    	
    	// Get the logged-in user's username (email) from the SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Use the username to retrieve the user's ID from your database
        User user = userRepository.findUserByEmail(username);

        // Set the user in the cartItem
        review.setUser(user);

        // Retrieve the product using the productId from the request parameter
        Product product = productService.getProductById(productId);

        // Set the product in the cartItem
        review.setProduct(product);

        Review createdReview = reviewService.createReview(review);
        
       
        //Points
        Double pointsToAdd = 0.70;
        pointsService.addPointsToUser(user, pointsToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }
    
//    @PostMapping("/review")
//    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
//    public ResponseEntity<Review> createReview(@RequestBody Review review) {
//    	
//    	// Get the logged-in user's username (email) from the SecurityContextHolder
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Use the username to retrieve the user's ID from your database
//        User user = userRepository.findUserByEmail(username);
//
//        // Set the user in the cartItem
//        review.setUser(user);
//
////        // Retrieve the product using the productId from the request parameter
////        Product product = productService.getProductById(productId);
////
////        // Set the product in the cartItem
////        review.setProduct(product);
//
//        Review createdReview = reviewService.createReview(review);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
//    }
	
    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
    public List<Review> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }
    
    
    @GetMapping("/product/{productId}/reviews")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
    public List<Review> getReviewss(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }
    
//    @GetMapping("/user/{userId}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
//    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Long userId) {
//        List<Review> userReviews = reviewService.getUserReviews(userId);
//        return ResponseEntity.ok(userReviews);
//    }
}
