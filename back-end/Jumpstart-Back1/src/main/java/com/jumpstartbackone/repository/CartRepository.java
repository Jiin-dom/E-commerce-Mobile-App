package com.jumpstartbackone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumpstartbackone.entity.Cart;
import com.jumpstartbackone.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUser(User user);
	List<Cart> findByUserId(Long userId);
}
