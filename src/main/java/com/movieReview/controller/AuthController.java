package com.movieReview.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieReview.bo.AuthRequest;
import com.movieReview.bo.UserBo;
import com.movieReview.security.jwt.JWTUserDetailsService;
import com.movieReview.security.jwt.JwtUtils;
import com.movieReview.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Authentication", description = "Authentication Operations related to user management")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	@Operation(summary = "Authenticate", description = "Authenticate user credentials")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		if (!auth.isAuthenticated()) {
			return ResponseEntity.status(401).body("Authentication failed");
		}
		String token = jwtUtil.generateToken(request.getEmail());
		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/create")
	@Operation(summary = "Create a new user", description = "Creates a new user with the provided details")
	public ResponseEntity<String> createUser(@RequestBody UserBo userBo) {
		try {
			userService.createUser(userBo);
			return ResponseEntity.ok("User created successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error creating user: " + e.getMessage());
		}
	}
}
