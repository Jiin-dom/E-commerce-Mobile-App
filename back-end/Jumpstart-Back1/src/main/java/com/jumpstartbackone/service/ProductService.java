package com.jumpstartbackone.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.repository.ProductRepository;


@Service
@Transactional
public class ProductService {

	
	@Autowired
	ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> search(String keyword) {
		return productRepository.search(keyword);
	}

	public List<Product> showProducts() {
		return productRepository.findAll();
	}

	public Product saveProduct(Product product) {

		return productRepository.save(product);
	}
	
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
    }

	

}
