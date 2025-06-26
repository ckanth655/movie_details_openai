package com.movieReview.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@Configuration
public class EnvLoader {

	@PostConstruct
	public void loadEnv() {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		System.setProperty("openai.api.key", dotenv.get("OPENAI_API_KEY"));
	}
}
