package com.jumpstartbackone.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.OrderItems;
import com.jumpstartbackone.entity.OrderRequest;
import com.jumpstartbackone.entity.Points;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.OrderItemsRepository;
import com.jumpstartbackone.repository.OrderRepository;
import com.jumpstartbackone.repository.OrderRequestRepository;
import com.jumpstartbackone.repository.PointsRepository;
import com.jumpstartbackone.repository.UserRepository;

@Service
@Transactional
public class OrderRequestService {
    
    @Autowired
    OrderRequestRepository orderRequestRepository;
    
    @Autowired
    OrderItemsRepository orderItemsRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    PointsRepository pointsRepository;
    
	@Autowired
	private ProductService productService;
    
//    public OrderRequest createOrderRequest(OrderRequest orderRequest) {
//
//      String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//      // Use the username to retrieve the user's ID from your database
//      User user = userRepository.findUserByEmail(username);
//
//      // Set the user in the cartItem
//      orderRequest.setUser(user);
//      
//
//        // Save the OrderRequest entity to the database
//        OrderRequest savedOrderRequest = orderRequestRepository.save(orderRequest);
//
//        // Set the OrderRequest for each associated OrderItems
//        for (OrderItems orderItem : savedOrderRequest.getOrderItems()) {
//        	
//        	 Product product = productService.getProductById(orderItem.getProduct().getProductId());
//        	 orderItem.getProduct().setProductPrice(product.getProductPrice());
//          	
//          	
//            orderItem.setOrder(savedOrderRequest);
//        }
//
//        // Save the associated OrderItems entities to the database
//        orderItemsRepository.saveAll(savedOrderRequest.getOrderItems());
//
//        return savedOrderRequest;
//    }

	
	//original, without points implementation
//	public OrderRequest createOrderRequest(OrderRequest orderRequest) {
//
//	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//	    // Use the username to retrieve the user's ID from your database
//	    User user = userRepository.findUserByEmail(username);
//
//	    // Set the user in the cartItem
//	    orderRequest.setUser(user);
//
//	    // Set the OrderRequest for each associated OrderItems
//	    for (OrderItems orderItem : orderRequest.getOrderItems()) {
//	        Product product = productService.getProductById(orderItem.getProduct().getProductId());
//	        orderItem.getProduct().setProductPrice(product.getProductPrice());
//	        orderItem.setOrder(orderRequest);
//	    }
//
//	    // Save the associated OrderItems entities to the database
//	    orderItemsRepository.saveAll(orderRequest.getOrderItems());
//
//	    // Save the OrderRequest entity to the database
//	    OrderRequest savedOrderRequest = orderRequestRepository.save(orderRequest);
//
//	    return savedOrderRequest;
//	}
//		
	
	public OrderRequest createOrderRequest(OrderRequest orderRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(username);

        orderRequest.setUser(user);

        double totalPrice = orderRequest.getTotalPrice();

        // Retrieve the user's points
        Optional<Points> userPoints = pointsRepository.findByUser(user);

        if (userPoints.isPresent()) {
            Points points = userPoints.get();

            // Check if the user has enough points
            if (points.getPoints() >= totalPrice) {
                // Deduct points from the user
                points.setPoints(points.getPoints() - totalPrice);
                pointsRepository.save(points);

                // Save the associated OrderItems entities to the database
                orderItemsRepository.saveAll(orderRequest.getOrderItems());

                OrderRequest savedOrderRequest = orderRequestRepository.save(orderRequest);

                return savedOrderRequest;
            } else {
                throw new RuntimeException("Insufficient points to make this purchase.");
            }
        } else {
            throw new RuntimeException("User's points not found.");
        }
    }

	
	
	
    public OrderRequest saveOrderRequest(OrderRequest orderRequest) {
        // You can perform any additional logic or validation here if needed
        return orderRequestRepository.save(orderRequest);
    }
    
    public Optional<OrderRequest> findOrderRequestById(Long orderId) {
        return orderRequestRepository.findById(orderId);
    }
	
	
//    public List<OrderRequest> findPendingOrders() {
//        return orderRequestRepository.findByOrderStatus("Pending");
//    }
	

    public List<OrderRequest> findPendingOrdersWithProductNamesAndUserDetails() {
        return orderRequestRepository.findPendingOrdersWithProductNamesAndUserDetails();
    }

    public OrderRequest approveOrder(Long orderId) {
        // Find the order by orderId
        Optional<OrderRequest> optionalOrder = orderRequestRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            OrderRequest order = optionalOrder.get();

            // Check if the order is in a pending state
            if (order.getOrderStatus().equals("Pending")) {
                // Update the order status to "Approved"
                order.setOrderStatus("Approved");
                
                // Save the updated order
                orderRequestRepository.save(order);

                return order;
            } else {
                throw new IllegalStateException("Order is not in a pending state.");
            }
        } else {
            throw new NoSuchElementException("Order not found.");
        }
    }
    
    
    public OrderRequest confirmOrder(Long orderId) {
        // Find the order by orderId
        Optional<OrderRequest> optionalOrder = orderRequestRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            OrderRequest order = optionalOrder.get();

            // Check if the order is in a pending state
            if (order.getOrderStatus().equals("Approved")) {
                // Update the order status to "Approved"
                order.setOrderStatus("Received");
                
                // Save the updated order
                orderRequestRepository.save(order);

                return order;
            } else {
                throw new IllegalStateException("Order is not in a approval state.");
            }
        } else {
            throw new NoSuchElementException("Order not found.");
        }
    }
	
    
//    public OrderRequest orderHistory(Long orderId) {
//        // Find the order by orderId
//        Optional<OrderRequest> optionalOrder = orderRequestRepository.findById(orderId);
//
//        if (optionalOrder.isPresent()) {
//            OrderRequest order = optionalOrder.get();
//
//            // Check if the order is in a pending state
//            if (order.getOrderStatus().equals("Received")) {
//                // Update the order status to "Approved"
//                order.setOrderStatus("Received");
//                
//                // Save the updated order
//                orderRequestRepository.save(order);
//
//                return order;
//            } else {
//                throw new IllegalStateException("Order is not in a approval state.");
//            }
//        } else {
//            throw new NoSuchElementException("Order not found.");
//        }
//    }
    
    public List<OrderRequest> getApprovedOrdersWithUserDetails() {
        return orderRequestRepository.findApprovedOrdersWithUserDetails();
    }
    
    
    public List<OrderRequest> getReceivedOrdersWithUserDetails() {
        return orderRequestRepository.findReceivedOrdersWithUserDetails();
    }
    
    public List<OrderRequest> getToPayOrdersWithUserDetails() {
        return orderRequestRepository.findToPayOrdersWithUserDetails();
    }

}
