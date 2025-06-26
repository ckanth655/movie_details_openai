package com.movieReview.service;

import java.util.List;

import com.movieReview.bo.UserBo;


public interface UserService {

	// Define methods that the UserService should implement
	void createUser(UserBo userBo);

	void updateUser(String email, String password);

	void deleteUser(String email);

	// Additional methods can be added as needed
	List<UserBo> getAllUsers();
}
