package com.jumpstartbackone.restController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.DTO.OrderItemDTO;
import com.jumpstartbackone.DTO.OrdersDTO;
//import com.jumpstartbackone.entity.OrderItem;
import com.jumpstartbackone.entity.Orders;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.ProductRepository;
import com.jumpstartbackone.repository.UserRepository;
import com.jumpstartbackone.service.CartService;
import com.jumpstartbackone.service.OrderService;
import com.jumpstartbackone.service.ProductService;
import com.jumpstartbackone.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
//    @PostMapping("/order")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Orders> placeOrder(@RequestParam Long productId, @RequestBody Orders order) {
//    	
//        // Get the logged-in user's username (email) from the SecurityContextHolder
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Use the username to retrieve the user's ID from your database
//        User user = userRepository.findUserByEmail(username);
//
//        // Set the user in the cartItem
//        order.setUser(user);
//
//        // Retrieve the product using the productId from the request parameter
//        Product product = productService.getProductById(productId);
//
//        // Set the product in the cartItem
//        order.setProduct(product);
//        
//        // Validate the order and perform necessary business logic
//        Orders placedOrder = orderService.placeOrder(order);
//        return ResponseEntity.ok(placedOrder);
//    }
	
//    @PostMapping("/order")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Orders> placeOrder(@RequestBody Orders order) {
//    	// Get the logged-in user's username (email) from the SecurityContextHolder
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Use the username to retrieve the user's ID from your database
//        User user = userRepository.findUserByEmail(username);
//
//        // Set the user in the cartItem
//        order.setUser(user);
//        
//        List<OrderItem> updatedOrderItems = new ArrayList<>(); // Create a list to store updated order items
//
//        for (OrderItem orderItem : order.getOrderItems()) {
//            Product product = productService.getProductById(orderItem.getProduct().getProductId());
//            if (product != null) {
//                // Update the order item's product details
//            	orderItem.getProduct().setProductId(product.getProductId());
//
//                // Calculate the total price for this item
//                double itemTotalPrice = product.getProductPrice() * orderItem.getQuantity();
//                orderItem.setTotalPrice(itemTotalPrice);
//            	
//            	
//            	// Create a new OrderItem with the fetched product
////                OrderItem updatedOrderItem = new OrderItem();
////                updatedOrderItem.setProduct(product);
////                updatedOrderItem.setQuantity(orderItem.getQuantity());
////
////                // Calculate the total price for this item
////                double itemTotalPrice = product.getProductPrice() * orderItem.getQuantity();
////                updatedOrderItem.setTotalPrice(itemTotalPrice);
//
//                updatedOrderItems.add(orderItem); // Add the updated order item to the list
//            } else {
//                // Handle the case where the product doesn't exist
//                System.out.print("Product is Null");
//            }
//        }
//        
//        
//        order.setOrderItems(updatedOrderItems);
//
//        // Calculate the total quantity and total price for the order
//        int totalQuantity = order.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum();
//        double totalPrice = order.getOrderItems().stream()
//                .mapToDouble(orderItem -> orderItem.getTotalPrice())
//                .sum();
//
//        // Set the total quantity and total price in the order
//        order.setQuantity(totalQuantity);
//        order.setTotalPrice(totalPrice);
//
//        // Save the order to the database
//        Orders placedOrder = orderService.placeOrder(order);
//
//        // Return the order as the response with populated product_id and quantity
//        return ResponseEntity.ok(placedOrder);
//    }
	
	
//	@PostMapping("/order")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> orderData) {
//	    // Get the logged-in user's username (email) from the SecurityContextHolder
//	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//	    // Use the username to retrieve the user's ID from your database
//	    User user = userRepository.findUserByEmail(username);
//
//	    // Create a new Orders instance
//	    Orders order = new Orders();
//	    order.setUser(user);
//
//	    // Set the order status, order date, and shipping address from the request data
//	    order.setOrderStatus((String) orderData.get("orderStatus"));
//	    order.setOrderDate(LocalDateTime.now());
//	    order.setShippingAddress((String) orderData.get("shippingAddress"));
//
//	    // Create a list to store order items
//	    List<OrderItem> orderItems = new ArrayList<>();
//
//	    List<Map<String, Object>> orderItemsData = (List<Map<String, Object>>) orderData.get("orderItems");
//
//	    for (Map<String, Object> orderItemData : orderItemsData) {
//	        String productIdStr = String.valueOf(orderItemData.get("productId"));
//	        String quantityStr = String.valueOf(orderItemData.get("quantity"));
//
//	        // Check if productIdStr and quantityStr are not null and are valid numbers
//	        if (productIdStr != null && quantityStr != null) {
//	            try {
//	                Long productId = Long.parseLong(productIdStr);
//	                int quantity = Integer.parseInt(quantityStr);
//
//	                // Retrieve the product by product ID
//	                Product product = productService.getProductById(productId);
//
//	                if (product != null) {
//	                    // Create a new OrderItem instance for this order item
//	                    OrderItem orderItem = new OrderItem();
//	                    orderItem.setProduct(product);
//	                    orderItem.setQuantity(quantity);
//
//	                    // Calculate the total price for this item
//	                    double itemTotalPrice = product.getProductPrice() * quantity;
//	                    orderItem.setTotalPrice(itemTotalPrice);
//
//	                    // Set the order in the order item
//	                    orderItem.setOrder(order);
//
//	                    // Add the order item to the list
//	                    orderItems.add(orderItem);
//	                } else {
//	                    // Handle the case where the product doesn't exist
//	                    return ResponseEntity.badRequest().body("Invalid product ID");
//	                }
//	            } catch (NumberFormatException e) {
//	                // Handle the case where productIdStr or quantityStr is not a valid number
//	                return ResponseEntity.badRequest().body("Invalid number format for productId or quantity");
//	            }
//	        } else {
//	            // Handle the case where productIdStr or quantityStr is null
//	            return ResponseEntity.badRequest().body("productId or quantity is null");
//	        }
//	    }
//
//	    // Set the list of order items in the order
//	    order.setOrderItems(orderItems);
//
//	    // Calculate the total quantity and total price for the order
//	    int totalQuantity = orderItems.stream().mapToInt(OrderItem::getQuantity).sum();
//	    double totalPrice = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();
//
//	    // Set the total quantity and total price in the order
//	    order.setQuantity(totalQuantity);
//	    order.setTotalPrice(totalPrice);
//
//	    // Save the order to the database
//	    Orders placedOrder = orderService.placeOrder(order);
//
//	    // Return the order as the response with populated product_id and quantity
//	    return ResponseEntity.ok(placedOrder);
//	}


	
	
//	@PostMapping("/order")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public ResponseEntity<Orders> placeOrder(@RequestBody OrdersDTO orderDTO) {
//		
//    	// Get the logged-in user's username (email) from the SecurityContextHolder
//      String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//      // Use the username to retrieve the user's ID from your database
//      User user = userRepository.findUserByEmail(username);
//      
//	    // Retrieve userId, orderStatus, etc., from orderDTO
//	    Long userId = orderDTO.getUserId();
//	    String orderStatus = orderDTO.getOrderStatus();
//
//	    // You can access the list of product IDs from orderDTO.getProductIds()
//	    List<Long> productIds = orderDTO.getProductIds();
//
//	    // You can also access the list of OrderItemDTO objects from orderDTO.getOrderItems()
//	    List<OrderItemDTO> orderItemDTOs = orderDTO.getOrderItems();
//
//	    // Your logic to process the order using the new structure
//	    // Create Orders, set user, order status, and other properties
//
//	    // Loop through productIds and orderItemDTOs to create OrderItem objects
//	    List<OrderItem> orderItems = new ArrayList<>();
//	    for (int i = 0; i < productIds.size(); i++) {
//	        Long productId = productIds.get(i);
//	        int quantity = orderItemDTOs.get(i).getQuantity();
//
//	        // Use productId and quantity to fetch the corresponding Product
//	        Product product = productService.getProductById(productId);
//
//	        if (product != null) {
//	            // Create an OrderItem and set its properties
//	            OrderItem orderItem = new OrderItem();
//	            orderItem.setProduct(product);
//	            orderItem.setQuantity(quantity);
//	            // Calculate total price for this order item and set it
//	            double itemTotalPrice = product.getProductPrice() * quantity;
//	            orderItem.setTotalPrice(itemTotalPrice);
//
//	            // Add this OrderItem to the list
//	            orderItems.add(orderItem);
//	        } else {
//	            // Handle the case where the product doesn't exist
//	            // You can return an error response or handle it as needed
//	            return ResponseEntity.badRequest().build();
//	        }
//	    }
//
//	    // Calculate the total quantity and total price for the order
//	    int totalQuantity = orderItems.stream().mapToInt(OrderItem::getQuantity).sum();
//	    double totalPrice = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();
//
//	    // Create the Orders entity and set its properties
//	    Orders order = new Orders();
//	    order.setUser(user);
//	    order.setOrderStatus(orderStatus);
//	    order.setOrderDate(LocalDateTime.now()); // You can use the provided orderDate if needed
//	    order.setShippingAddress(orderDTO.getShippingAddress());
//	    order.setTotalPrice(totalPrice);
//	    order.setQuantity(totalQuantity);
//	    // Set the order items
//	    order.setOrderItems(orderItems);
//
//	    // Save the order to the database
//	    Orders placedOrder = orderService.placeOrder(order);
//
//	    // Return the order as the response
//	    return ResponseEntity.ok(placedOrder);
//	}

	

	
	
//	@PostMapping("/order")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Orders> placeOrder(@RequestBody OrderRequest orderRequest) {
//        // Fetch the user by userId
//        User user = userService.findById(orderRequest.getUserId())
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        List<Orders> placedOrders = new ArrayList<>();
//        
//        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
//            // Fetch the product by productId
//            Product product = productService.findById(itemRequest.getProductId())
//                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//
//            Orders order = new Orders();
//            order.setUser(user);
//            order.setProduct(product);
//            order.setQuantity(itemRequest.getQuantity());
//            order.setTotalPrice(BigDecimal.valueOf(product.getProductPrice() * itemRequest.getQuantity()));
//            order.setOrderDate(LocalDateTime.now());
//            order.setOrderStatus("Pending");
//            order.setShippingAddress(orderRequest.getShippingAddress());
//
//            // Save each order to the database
//            placedOrders.add(orderService.placeOrder(order));
//        }
//
//        return ResponseEntity.ok().body(placedOrders);
//    }






}
