package com.jumpstartbackone.restController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jumpstartbackone.entity.OrderRequest;
import com.jumpstartbackone.entity.Product;
import com.jumpstartbackone.service.OrderRequestService;
import com.jumpstartbackone.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderRequestService orderRequestService;

	
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
	
	
//	@PostMapping("/add_product")
//	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
//	public ResponseEntity<String> addNewProduct(@ModelAttribute Product product) {
//		try {
//			productService.saveProduct(product);
//
//			return ResponseEntity.ok("Product successfully added");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product");
//		}
//	}
	
//	@PostMapping("/add_product")
//	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
//	public ResponseEntity<String> addNewProduct(@RequestParam("image") MultipartFile image, @ModelAttribute Product product) {
//		try {
//			String imageName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(image.getOriginalFilename());
//
//			// Set meal details
//			product.setProductPhoto(imageName);
//
//			// Save the meal
//			Product savedProduct = productService.saveProduct(product);
//
//			// Path of the image
//			String imagePath = "./src/main/resources/static/images/products/" + savedProduct.getProductId();
//
//			// Save the image file
//			Path uploadPath = Paths.get(imagePath);
//			if (!Files.exists(uploadPath)) {
//				Files.createDirectories(uploadPath);
//			}
//			try (InputStream inputStream = image.getInputStream()) {
//				Path filePath = uploadPath.resolve(imageName);
//				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//			} catch (IOException e) {
//				throw new IOException("Cannot save uploaded file: " + imageName);
//			}
//
//			// Set the image path for the meal
//			product.setProductImgPath("/images/products/" + savedProduct.getProductId() + "/" + savedProduct.getProductPhoto());
//			productService.saveProduct(product);
//
//			return ResponseEntity.ok("Product successfully added");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product");
//		}
//	}
	
	@PostMapping("/add_product")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<String> addNewMeal(@RequestParam("image") MultipartFile image, @ModelAttribute Product product) {
		try {
			String imageName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(image.getOriginalFilename());

			// Set meal details
			product.setProductPhoto(imageName);

			// Save the meal
			Product savedMeal = productService.saveProduct(product);

			// Path of the image
			String imagePath = "./src/main/resources/static/images/meals/" + savedMeal.getProductId();

			// Save the image file
			Path uploadPath = Paths.get(imagePath);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = image.getInputStream()) {
				Path filePath = uploadPath.resolve(imageName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Cannot save uploaded file: " + imageName);
			}

			// Set the image path for the meal
			product.setProductImgPath("/images/meals/" + savedMeal.getProductId() + "/" + savedMeal.getProductPhoto());
			productService.saveProduct(product);

			return ResponseEntity.ok("Meal successfully added");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding meal");
		}
	}
	
	
    @PutMapping("/approve/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> approveOrder(@PathVariable Long orderId) {
        try {
            OrderRequest updatedOrder = orderRequestService.approveOrder(orderId);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error approving order.");
        }
    }
	
//	@GetMapping("/orders/pending")
//	public ResponseEntity<List<OrderRequest>> getPendingOrders() {
//	    List<OrderRequest> pendingOrders = orderRequestService.findPendingOrders();
//	    return ResponseEntity.ok(pendingOrders);
//	}

	
//	@PostMapping("/add_product")
//	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
//	public ResponseEntity<String> addNewProduct(@RequestPart("productPhoto") MultipartFile productPhoto, @ModelAttribute Product product) {
//	    try {
//	        String imageName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(productPhoto.getOriginalFilename());
//
//	        // Save the image file
//	        Path imagePath = Paths.get("src/main/resources/static/images/products/" + imageName);
//	        try (InputStream inputStream = productPhoto.getInputStream()) {
//	            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
//	        } catch (IOException e) {
//	            throw new IOException("Cannot save uploaded file: " + imageName);
//	        }
//
//	        // Set meal details
//	        product.setProductPhoto(imageName);
//
//	        // Save the product
//	        Product savedProduct = productService.saveProduct(product);
//
//	        // Set the image path for the product
//	        savedProduct.setProductImgPath("/images/products/" + imageName);
//	        productService.saveProduct(savedProduct);
//
//	        return ResponseEntity.ok("Product successfully added");
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product");
//	    }
//	}

}
