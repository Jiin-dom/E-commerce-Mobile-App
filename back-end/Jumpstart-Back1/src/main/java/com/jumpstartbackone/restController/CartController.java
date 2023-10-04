package com.jumpstartbackone.restController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.entity.Cart;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.CartRepository;
import com.jumpstartbackone.repository.ProductRepository;
import com.jumpstartbackone.repository.UserRepository;
import com.jumpstartbackone.service.CartService;
import com.jumpstartbackone.service.ProductService;
import com.jumpstartbackone.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class CartController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping("/getAllCartItems")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Cart>> getAllCartItems() {
        List<Cart> cartItems = cartService.getAllCartItems();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }


	
//	@GetMapping("/getCartItems")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public ResponseEntity<List<Cart>> getCartItems() {
//	    try {
//	        // Get the logged-in user's username (email) from the SecurityContextHolder
//	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//	        
//	        // Use the username to retrieve the user's ID from your database
//	        User user = userRepository.findUserByEmail(username);
//	        
//	        // Use the user's ID to fetch the cart items associated with that user
//	        List<Cart> cartItems = cartService.getCartItemsByUser(user);
//
//	        // Extract the product information from cart items
//	        List<Product> productsInCart = cartItems.stream()
//	            .map(Cart::getProduct)
//	            .collect(Collectors.toList());
//
//	        // Return the list of products in the cart
//	        return ResponseEntity.ok(productsInCart);
//	    } catch (Exception e) {
//	        // Handle errors and return an error response
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body(Collections.emptyList()); // Return an empty list in case of an error
//	    }
//	}
	
	@GetMapping("/cart")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Cart>> getCartItemsForLoggedInUser() {
	    try {
	        // Get the logged-in user's username (email) from the SecurityContextHolder
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        
	        // Use the username to retrieve the user's ID from your database
	        User user = userRepository.findUserByEmail(username);

	        // Retrieve the cart items for the logged-in user
	        List<Cart> cartItems = cartService.getCartItemsByUser(user);

	        // Return the cart items as a response
	        return ResponseEntity.ok(cartItems);
	    } catch (Exception e) {
	        // Handle errors and return an error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Collections.emptyList());
	    }
	}
	
//	@GetMapping("/cart/")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public ResponseEntity<List<Cart>> getLoggedInUserCart() {
//	    try {
//	        // Get the logged-in user's userId from your authentication system
//	        Long userId = getUserIdFromAuthentication(); // Implement this method based on your authentication
//	        
//	        // Retrieve cart items for the logged-in user by userId
//	        List<Cart> cartItems = cartService.getCartItemsByUserId(userId);
//
//	        // Return the cart items in the response
//	        return ResponseEntity.ok(cartItems);
//	    } catch (Exception e) {
//	        // Handle errors and return an error response
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body(Collections.emptyList()); // Empty list if there's an error
//	    }
//	}
	
//	@GetMapping("/getLoggedInUserCart/{userId}")
//	public ResponseEntity<List<Cart>> getLoggedInUserCart(@PathVariable Long userId) {
//	    // Use userId to fetch the user's cart items
//	    List<Cart> cartItems = cartService.getCartItemsByUserId(userId);
//
//	    return ResponseEntity.ok(cartItems);
//	}





}
