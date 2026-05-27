package com.summarizer.service;

import com.summarizer.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class GeminiSummarizeTextService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiSummarizeTextService(WebClient.Builder webClientBuilder) {
        // Configure the base URL for the Gemini API
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models")
                .build();
    }

    public String summarizePaper(String pdfText) {
        // 1. Construct the prompt
        String prompt = "Please provide a comprehensive summary of the following academic research paper. " +
                "Highlight the core problem, methodology, and key conclusions:\n\n" + pdfText;

        // 2. Build the request payload using our DTOs
        GeminiRequest requestPayload = new GeminiRequest(List.of(
                new Content(List.of(new Part(prompt)))
        ));

        // 3. Make the API call
        GeminiResponse response = webClient.post()
                .uri("/gemini-3.5-flash:generateContent?key=" + apiKey)
                .bodyValue(requestPayload)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block(); // Blocking is fine here if sticking to a standard synchronous MVC controller

        // 4. Parse and return the response
        if (response != null && !response.candidates().isEmpty()) {
            return response.candidates().get(0).content().parts().get(0).text();
        }

        throw new RuntimeException("Failed to generate summary from Gemini API.");
    }
}