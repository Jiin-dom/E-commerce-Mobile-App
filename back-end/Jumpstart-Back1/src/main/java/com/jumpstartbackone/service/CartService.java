package com.jumpstartbackone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.Cart;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	
	public Cart saveCartItem(Cart cartItem) {
        return cartRepository.save(cartItem);
    }
	
    public List<Cart> getCartItemsByUser(User user) {
        return cartRepository.findByUser(user);
    }
    
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }
    
    public List<Cart> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
