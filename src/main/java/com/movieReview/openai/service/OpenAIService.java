
package com.movieReview.openai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

@Service
public class OpenAIService {

	@Autowired
	private OpenAiService openAiService;

	public String getChatResponse(String movieName) {
  
  // Default system prompt to guide the assistant 
		ChatMessage systemMessage =  new ChatMessage("system",
  "You are a movie expert. Provide detailed, engaging information about any movie the user asks for."
  );
  
   //Dynamic user prompt 
   ChatMessage userMessage = new ChatMessage("user",
  movieName);
  
  ChatCompletionRequest request = ChatCompletionRequest .builder()
  .model("gpt-4o-mini") .messages(List.of(systemMessage, userMessage))
  .maxTokens(200) .build();
  
  ChatCompletionResult response = openAiService.createChatCompletion(request);
  return response.getChoices().get(0).getMessage().getContent(); }
}
