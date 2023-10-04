package com.jumpstartbackone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpstartbackone.entity.OrderItems;


public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
