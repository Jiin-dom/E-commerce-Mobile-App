package com.jumpstartbackone.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.Orders;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.OrderRepository;
import com.jumpstartbackone.repository.UserRepository;


@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService; // Assuming you have a ProductService

	@Autowired
	private UserRepository userRepository; // Assuming you have a UserRepository
	
	public Orders placeOrder(Orders order) {
		return orderRepository.save(order);
	}
	
//	 public Orders placeOrder(Orders order) {
//	        // Fetch the user by userId
//	        User user = userRepository.findById(order.getUser().getId())
//	                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//	        // Fetch the product by productId
//	        Product product = productService.getProductById(order.getProduct().getProductId());
//	                
//
//	        // Set the user and product in the order
//	        order.setUser(user);
//	        order.setProduct(product);
//
//	        // Calculate the total price, set order date, and order status as needed
//	        order.setOrderDate(LocalDateTime.now());
//	        order.setOrderStatus("Pending");
//	        // Calculate total price based on product price and quantity
//	        int productPrice = product.getProductPrice();
//	        int quantity = order.getQuantity();
//	        int totalPrice = productPrice * quantity;
//	        order.setTotalPrice(BigDecimal.valueOf(totalPrice));
//
//	        // Save the order to the database
//	        return orderRepository.save(order);
//	    }
}
