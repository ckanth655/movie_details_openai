package com.movieReview.security.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.movieReview.model.User;
import com.movieReview.repository.UserRespository;


@Component
public class JWTUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRespository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// Here you would typically fetch the user from the database using the email
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		
		
		return new org.springframework.security.core.userdetails
				.User(user.getEmail(), user.getPassword(),
				Collections.emptyList());
	}

}
