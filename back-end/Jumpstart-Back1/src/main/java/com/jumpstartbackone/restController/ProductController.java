package com.jumpstartbackone.restController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.entity.Cart;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.CartRepository;
import com.jumpstartbackone.repository.ProductRepository;
import com.jumpstartbackone.repository.UserRepository;
import com.jumpstartbackone.service.CartService;
import com.jumpstartbackone.service.ProductService;
import com.jumpstartbackone.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	
	
	@GetMapping("/images/{productId}/{productPhoto:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String productId, @PathVariable String productPhoto) {
		try {
			Path imagePath = Paths.get("src/main/resources/static/images/meals/" + productId + "/" + productPhoto);
			Resource imageResource = new UrlResource(imagePath.toUri());

			if (imageResource.exists() && imageResource.isReadable()) {
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageResource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/showProducts") // member
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')")
	public List<Product> showProducts() {
		List<Product> productList = productService.showProducts();
		return productList;
	}
	
	@GetMapping("/showProducts/{productId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_USER')")
	public Optional<Product> viewProductDetails(@PathVariable Long productId) {
		return productRepository.findById(productId);
	}
	

	
	@PostMapping("/addToCart")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> addToCart(
	    @RequestParam Long productId, // Get the productId from the request parameter
	    @RequestBody Cart cartItem) {
	    try {
	        // Get the logged-in user's username (email) from the SecurityContextHolder
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();

	        // Use the username to retrieve the user's ID from your database
	        User user = userRepository.findUserByEmail(username);

	        // Set the user in the cartItem
	        cartItem.setUser(user);

	        // Retrieve the product using the productId from the request parameter
	        Product product = productService.getProductById(productId);

	        // Set the product in the cartItem
	        cartItem.setProduct(product);

	        // Save the cart item to your database
	        cartService.saveCartItem(cartItem);

	        // Return a success response
	        return ResponseEntity.ok("Item added to the cart successfully");
	    } catch (Exception e) {
	        // Handle errors and return an error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to add item to the cart: " + e.getMessage());
	    }
	}
	
	@PutMapping("/products/{productId}/updateStock")
    public ResponseEntity<?> updateProductStock(
        @PathVariable Long productId,
        @RequestParam("quantity") Integer quantity
    ) {
        try {
            Product product = productService.getProductById(productId);
            
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            
            int updatedStock = product.getProductStock() - quantity;
            
            if (updatedStock < 0) {
                return ResponseEntity.badRequest().body("Insufficient stock.");
            }
            
            product.setProductStock(updatedStock);
            productService.saveProduct(product);
            
            return ResponseEntity.ok("Product stock updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product stock.");
        }
    }



	
	







}
