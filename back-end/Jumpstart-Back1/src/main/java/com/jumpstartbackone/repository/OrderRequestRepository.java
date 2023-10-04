package com.jumpstartbackone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jumpstartbackone.entity.OrderRequest;

public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long>{
//	List<OrderRequest> findByOrderStatus(String orderStatus);

	@Query("SELECT DISTINCT o FROM OrderRequest o JOIN FETCH o.orderItems oi JOIN FETCH oi.product p JOIN FETCH o.user u WHERE o.orderStatus = 'Pending'")
	List<OrderRequest> findPendingOrdersWithProductNamesAndUserDetails();
	
	@Query("SELECT DISTINCT o FROM OrderRequest o JOIN FETCH o.orderItems oi JOIN FETCH oi.product p JOIN FETCH o.user u WHERE o.orderStatus = 'Approved'")
	List<OrderRequest> findApprovedOrdersWithUserDetails();

	@Query("SELECT DISTINCT o FROM OrderRequest o JOIN FETCH o.orderItems oi JOIN FETCH oi.product p JOIN FETCH o.user u WHERE o.orderStatus = 'Received'")
	List<OrderRequest> findReceivedOrdersWithUserDetails();
	
	@Query("SELECT DISTINCT o FROM OrderRequest o JOIN FETCH o.orderItems oi JOIN FETCH oi.product p JOIN FETCH o.user u WHERE o.paymentMethod = 'Paypal'")
	List<OrderRequest> findToPayOrdersWithUserDetails();


}
