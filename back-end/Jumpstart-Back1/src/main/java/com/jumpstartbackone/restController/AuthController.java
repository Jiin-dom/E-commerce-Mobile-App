package com.jumpstartbackone.restController;

import java.net.URI;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jumpstartbackone.DTO.ApiResponse;
import com.jumpstartbackone.DTO.JwtAuthenticationResponse;
import com.jumpstartbackone.DTO.LoginRequest;
import com.jumpstartbackone.DTO.UserRegistrationDTO;
import com.jumpstartbackone.Exception.AppException;
import com.jumpstartbackone.Security.JwtTokenProvider;
import com.jumpstartbackone.entity.Points;
import com.jumpstartbackone.entity.Role;
import com.jumpstartbackone.entity.User;
import com.jumpstartbackone.repository.PointsRepository;
import com.jumpstartbackone.repository.RoleRepository;
import com.jumpstartbackone.repository.UserRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PointsRepository pointsRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        // Validate input data...
//
//        // Check if the selected role is valid
//        Role userRole = roleRepository.findByName(userRegistrationDTO.getRole())
//                .orElseThrow(() -> new AppException("Invalid role."));

        // Check if the email address is already taken
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email Address has already been taken"));
        }

//        if (userRole == null) {
//            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid Role"));
//        }
        
        // Encode the password
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());

        // Create a new User entity and save it to the database
        User user = new User(
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getPhoneNumber(),
                userRegistrationDTO.getAddress(),
                userRegistrationDTO.getEmail(),
                encodedPassword // Use the encoded password
        );

        // Create a new Points entity for the user with an initial points balance of 0
        Points points = new Points();
        points.setUser(user);
        points.setPoints(0.0); // Initial points balance
        pointsRepository.save(points);
        
		Role userRole = roleRepository.findById(2L).orElse(null);
		user.setRoles(Collections.singleton(userRole));
//        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully."));
    }

    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String roleName = userDetails.getAuthorities().iterator().next().getAuthority(); // Retrieve the role name

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AppException("User not found."));
        String firstName = user.getFirstName(); // Retrieve the first name
        Long userId = user.getId(); // Retrieve the user ID
        String email = username;
        String phoneNumber = user.getPhoneNumber();
        String message = "User '" + username + "' logged in successfully.";

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt, message, roleName, firstName, userId, email, phoneNumber);
        System.out.println("User ID: " + response.getUserId());
        System.out.println("User ID: " + response.getRoleName());

        return ResponseEntity.ok(response);
    }


}