package com.movieReview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movieReview.bo.UserBo;
import com.movieReview.model.User;
import com.movieReview.repository.UserRespository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository respository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createUser(UserBo userBo) {

		User user = new User();
		user.setFirstName(userBo.getFirstName());
		user.setLastName(userBo.getLastName());
		user.setEmail(userBo.getEmail());
		user.setPassword(passwordEncoder.encode(userBo.getPassword()));
		user.setPhoneNumber(userBo.getPhoneNumber());

		user = respository.save(user);
		if (user == null) {
			throw new RuntimeException("User creation failed");
		}
	}

	@Override
	public void updateUser(String email, String password) {
		// TODO Auto-generated method stub
		User user = respository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user = respository.save(user);
		if (user == null) {
			throw new RuntimeException("User update failed");
		}
	}

	@Override
	public void deleteUser(String email) {
		User user = respository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		respository.delete(user);
	}

	@Override
	public List<UserBo> getAllUsers() {
		// TODO Auto-generated method stub
		return respository.findAll().stream().map(user -> {
			UserBo userBo = new UserBo();
			userBo.setFirstName(user.getFirstName());
			userBo.setLastName(user.getLastName());
			userBo.setEmail(user.getEmail());
			userBo.setPhoneNumber(user.getPhoneNumber());
			return userBo;
		}).toList();
	}

}
