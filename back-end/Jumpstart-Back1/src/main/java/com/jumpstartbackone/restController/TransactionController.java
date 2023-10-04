package com.jumpstartbackone.restController;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.DTO.TransactionsDTO;
import com.jumpstartbackone.entity.OrderRequest;
import com.jumpstartbackone.entity.Transactions;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.OrderRequestRepository;
import com.jumpstartbackone.repository.UserRepository;
import com.jumpstartbackone.service.OrderRequestService;
import com.jumpstartbackone.service.TransactionService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private OrderRequestService orderRequestService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRequestRepository orderRequestRepository;
	
//	@PostMapping("/transactions/save")
//	public ResponseEntity<?> saveTransaction(@RequestBody TransactionsDTO transactionDTO) {
//	    try {
//	    	
//
//	        // Convert TransactionDTO to the entity class (Transactions) if needed
//	        Transactions transaction = new Transactions();
//	        transaction.setEmail(transactionDTO.getEmail());
//	        transaction.setPayment(transactionDTO.getPayment());
//	        transaction.setTransactionDate(transactionDTO.getTransactionDate());
//	        
//	    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
//	 	    User user = userRepository.findUserByEmail(username);
//	 	    transaction.setUser(user);
//	 	    
//	 	   OrderRequest order = orderRequestRepository.findById(orderId);
//
//	        // Save the transaction to the database
//	        Transactions savedTransaction = transactionService.saveTransaction(transaction);
//
//	        // You can return a response or DTO if necessary
//	        TransactionsDTO savedTransactionDTO = new TransactionsDTO(
//	            savedTransaction.getEmail(),
//	            savedTransaction.getPayment(),
//	            savedTransaction.getTransactionDate()
//	        );
//
//	        return ResponseEntity.ok(savedTransactionDTO);
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving transaction.");
//	    }
//	}
    
    //deletes the order request as ait creates a new one
//    @PostMapping("/transactions/save")
//    public ResponseEntity<?> saveTransaction(@RequestBody TransactionsDTO transactionDTO) {
//        try {
//            // Convert TransactionDTO to the entity class (Transactions) if needed
//            Transactions transaction = new Transactions();
//            transaction.setEmail(transactionDTO.getEmail());
//            transaction.setPayment(transactionDTO.getPayment());
//            transaction.setTransactionDate(transactionDTO.getTransactionDate());
//
//            // Set OrderRequest
//            OrderRequest orderRequest = transactionDTO.getOrder();
//            transaction.setOrder(orderRequest);
//
//            String username = SecurityContextHolder.getContext().getAuthentication().getName();
//            User user = userRepository.findUserByEmail(username);
//            transaction.setUser(user);
//
//            // Save the transaction to the database
//            Transactions savedTransaction = transactionService.saveTransaction(transaction);
//
//            // Update the payment_status of the associated OrderRequest
//            orderRequest.setPaymentStatus("Paid"); // Set payment_status to "Paid"
//            orderRequestService.saveOrderRequest(orderRequest);
//
//            // You can return a response or DTO if necessary
//            TransactionsDTO savedTransactionDTO = new TransactionsDTO(
//                savedTransaction.getEmail(),
//                savedTransaction.getPayment(),
//                savedTransaction.getTransactionDate(),
//                savedTransaction.getOrder() // Include OrderRequest in the response
//            );
//
//            return ResponseEntity.ok(savedTransactionDTO);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving transaction.");
//        }
//    }
    
    
    @PostMapping("/transactions/save")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionsDTO transactionDTO) {
        try {
            // Convert TransactionDTO to the entity class (Transactions) if needed
            Transactions transaction = new Transactions();
            transaction.setEmail(transactionDTO.getEmail());
            transaction.setPayment(transactionDTO.getPayment());
            transaction.setTransactionDate(transactionDTO.getTransactionDate());

            // Set OrderRequest
            OrderRequest orderRequest = transactionDTO.getOrder();
            transaction.setOrder(orderRequest);

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findUserByEmail(username);
            transaction.setUser(user);

            // Save the transaction to the database
            Transactions savedTransaction = transactionService.saveTransaction(transaction);

            // Update the payment_status of the associated OrderRequest
            Long orderId = orderRequest.getOrderId(); // Get the order ID from the OrderRequest
            Optional<OrderRequest> optionalOrderRequest = orderRequestService.findOrderRequestById(orderId);

            if (optionalOrderRequest.isPresent()) {
                OrderRequest existingOrderRequest = optionalOrderRequest.get();
                existingOrderRequest.setPaymentStatus("Paid"); // Set payment_status to "Paid"
                orderRequestService.saveOrderRequest(existingOrderRequest);
            } else {
                throw new NoSuchElementException("OrderRequest not found.");
            }

            // You can return a response or DTO if necessary
            TransactionsDTO savedTransactionDTO = new TransactionsDTO(
                savedTransaction.getEmail(),
                savedTransaction.getPayment(),
                savedTransaction.getTransactionDate(),
                savedTransaction.getOrder() // Include OrderRequest in the response
            );

            return ResponseEntity.ok(savedTransactionDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving transaction.");
        }
    }



}
