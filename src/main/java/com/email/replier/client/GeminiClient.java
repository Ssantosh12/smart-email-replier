package com.email.replier.client;

import com.email.replier.exception.EmailGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class GeminiClient {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateContent(String prompt) {
        // Craft a request
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                       Map.of("parts", new Object[]{
                               Map.of("text", prompt)
                       })
                }
        );

        // Validate configuration
        if (geminiApiUrl == null || geminiApiUrl.isBlank()) {
            throw new EmailGenerationException("Missing configuration: gemini.api.url");
        }
        if (geminiApiKey == null || geminiApiKey.isBlank()) {
            throw new EmailGenerationException("Missing configuration: gemini.api.key");
        }

        // Do request and get response. Gemini API requires key in URL query parameter.
        String urlWithKey = geminiApiUrl + (geminiApiUrl.contains("?") ? "&" : "?") + "key=" + geminiApiKey;
        String response = webClient.post()
                .uri(urlWithKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response == null) {
            throw new EmailGenerationException("Empty response from Gemini API");
        }

        // Extract Response and Return
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode candidates = rootNode.path("candidates");
            if (!candidates.isArray() || candidates.size() == 0) {
                throw new EmailGenerationException("No candidates field in API response");
            }
            JsonNode first = candidates.get(0);
            JsonNode textNode = first.path("content").path("parts").get(0).path("text");
            if (textNode.isMissingNode()) {
                throw new EmailGenerationException("No text part in API response");
            }
            return textNode.asText();
        } catch (Exception e) {
            // Bubble up as runtime exception so controller can return 500 with clear message
            throw new EmailGenerationException("Error processing API response: " + e.getMessage(), e);
        }
    }
}