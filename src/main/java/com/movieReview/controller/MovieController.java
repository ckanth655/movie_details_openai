
package com.movieReview.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movieReview.openai.service.OpenAIService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController

@RequestMapping("/movies")

@Tag(name = "Movie Service", description = "Endpoints for movie details retrieval using ChatGPT")
public class MovieController {

	private OpenAIService openAIService;

	public MovieController(OpenAIService openAIService) {
		this.openAIService = openAIService;
	}

	@GetMapping("/details")

	@Operation(summary = "Get Movie Details", description = "Fetches movie details like plot, year, cast, and rating using ChatGPT.")
	public ResponseEntity<?> getMovieDetails(@RequestParam String moviewName) {
		String response = openAIService.getChatResponse(moviewName);
		return ResponseEntity.ok(Map.of("movieInfo", response));
	}
}
