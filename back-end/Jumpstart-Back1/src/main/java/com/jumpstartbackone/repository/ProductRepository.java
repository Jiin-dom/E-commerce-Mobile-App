package com.jumpstartbackone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jumpstartbackone.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT product FROM Product product WHERE product.productName LIKE %:keyword%" + " OR product.productDescription LIKE %:keyword%"
			+ " OR product.productCategory LIKE %:keyword%")
	
	
	public List<Product> search(@Param("keyword") String keyword);

//	public Optional<Product> findById(Long productId);
//	public Product findById(Long productId);

}
