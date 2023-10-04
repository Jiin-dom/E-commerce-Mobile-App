package com.jumpstartbackone.restController;

import java.security.Principal;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumpstartbackone.Exception.UserNotFoundException;
import com.jumpstartbackone.entity.Points;
import com.jumpstartbackone.entity.Role;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.PointsRepository;
import com.jumpstartbackone.repository.RoleRepository;
import com.jumpstartbackone.repository.UserRepository;
import com.jumpstartbackone.service.PointsService;
import com.jumpstartbackone.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PointsRepository pointsRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PointsService pointsService;
    
    @PostMapping("/{userId}/address")
//    @PreAuthorize("hasRole('MEMBER')")
    public void saveUserAddress(@PathVariable("userId") Long userId, @RequestBody String address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        user.setAddress(address);
        userRepository.save(user);
    }
    
//    @GetMapping("/user")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<User> getUserDetailsForLoggedInUser() {
//        try {
//            // Get the logged-in user's username (email) from the SecurityContextHolder
//            String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//            // Use the username to retrieve the user's details from your database
//            User user = userRepository.findUserByEmail(username);
//
//            // Return the user details as a response
//            return ResponseEntity.ok(user);
//        } catch (Exception e) {
//            // Handle errors and return an error response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null); // You can return a more meaningful error response here if needed
//        }
//    }
    
    
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> getUserDetailsForLoggedInUser() {
        try {
            // Get the logged-in user's username (email) from the SecurityContextHolder
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // Use the username to retrieve the user's details from your database
            User user = userRepository.findUserByEmail(username);

            // Fetch the user's points from the Points entity based on user ID
            Optional<Points> pointsOptional = pointsRepository.findPointsByUser(user);

            // Use .orElse() to provide a default value when the Optional is empty
            Double userPoints = pointsOptional.map(Points::getPoints).orElse(0.0);

            // Now you can use userPoints as needed
            // ...

            // Return the user details with points as a response
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            // Handle errors and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // You can return a more meaningful error response here if needed
        }
    }





}
