package com.movieReview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieReview.model.User;


@Repository
public interface UserRespository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
