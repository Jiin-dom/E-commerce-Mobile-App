package com.jumpstartbackone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jumpstartbackone.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProduct_ProductId(Long productId);

}
