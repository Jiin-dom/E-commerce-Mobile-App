package com.jumpstartbackone.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.entity.OrderRequest;
import com.jumpstartbackone.service.OrderRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class OrderRequestController {
	
	@Autowired
	private OrderRequestService orderRequestService;
	
//    @PostMapping("/create")
//    public OrderRequest createOrderRequest(@RequestBody OrderRequest orderRequest) {
//        // Save the OrderRequest along with its associated OrderItems
//        return orderRequestService.createOrderRequest(orderRequest);
//    }
    
	@PostMapping("/create")
	public ResponseEntity<?> createOrderRequest(@RequestBody OrderRequest orderRequest) {
	    try {
	        // Call the service method to create the order and deduct points
	        OrderRequest savedOrderRequest = orderRequestService.createOrderRequest(orderRequest);

	        return ResponseEntity.ok(savedOrderRequest);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Insufficient points to make this purchase.");
	    }
	}

	
//	@GetMapping("/orders/pending")
//	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
//	public ResponseEntity<List<OrderRequest>> getPendingOrders() {
//	    List<OrderRequest> pendingOrders = orderRequestService.findPendingOrders();
//	    return ResponseEntity.ok(pendingOrders);
//	}
    
	@GetMapping("/orders/pending")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
    public List<OrderRequest> getPendingOrdersWithProductNames() {
	    List<OrderRequest> pendingData = orderRequestService.findPendingOrdersWithProductNamesAndUserDetails();
	    
	    // Log the pendingData to the console
	    for (OrderRequest order : pendingData) {
	        System.out.println("Order ID: " + order.getOrderId());
	        // Log other relevant order details here...
	    }

	    return pendingData;
    }
	

	@GetMapping("/orders/approved")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
	public ResponseEntity<List<OrderRequest>> getApprovedOrders() {
	    try {
	        List<OrderRequest> approvedOrders = orderRequestService.getApprovedOrdersWithUserDetails();
	        return ResponseEntity.ok(approvedOrders);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
	    }
	}
	
    @PutMapping("/confirm/{orderId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> confirmOrder(@PathVariable Long orderId) {
        try {
            OrderRequest updatedOrder = orderRequestService.confirmOrder(orderId);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error approving order.");
        }
    }
    
    
	@GetMapping("/orders/history")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
	public ResponseEntity<List<OrderRequest>> getOrderHistory() {
	    try {
	        List<OrderRequest> history = orderRequestService.getReceivedOrdersWithUserDetails();
	        return ResponseEntity.ok(history);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
	    }
	}
	
	@GetMapping("/orders/to-pay")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')") 
	public ResponseEntity<List<OrderRequest>> getOrdersToPay() {
	    try {
	        List<OrderRequest> history = orderRequestService.getToPayOrdersWithUserDetails();
	        return ResponseEntity.ok(history);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
	    }
	}

	
	
}
