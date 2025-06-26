package com.movieReview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieReview.bo.UserBo;
import com.movieReview.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping("/update")
	@Operation(summary = "Update an existing user", description = "Updates the details of an existing user")
	public ResponseEntity<String> updateUser(@RequestBody UserBo userBo) {
		try {
			userService.updateUser(userBo.getEmail(), userBo.getPassword());
			return ResponseEntity.ok("User updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating user: " + e.getMessage());
		}
	}
	
	@PutMapping("/delete")
	@Operation(summary = "Delete a user", description = "Deletes a user by their email address")
	public ResponseEntity<String> deleteUser(@RequestBody UserBo userBo) {
		try {
			userService.deleteUser(userBo.getEmail());
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting user: " + e.getMessage());
		}
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get all users", description = "Retrieves a list of all users in the system")
	public ResponseEntity<List<UserBo>> getAllUsers() {
		try {
			List<UserBo> users = userService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
}
