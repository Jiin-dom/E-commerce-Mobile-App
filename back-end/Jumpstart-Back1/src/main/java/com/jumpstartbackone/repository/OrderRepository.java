package com.jumpstartbackone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpstartbackone.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
