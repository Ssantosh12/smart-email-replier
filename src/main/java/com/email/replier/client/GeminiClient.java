package com.email.replier.client;

import com.email.replier.exception.AiServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class GeminiClient {

    private static final Logger logger = LoggerFactory.getLogger(GeminiClient.class);

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateContent(String prompt) {
        long startTime = System.currentTimeMillis();
        logger.info("Incoming Gemini API request");

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
            logger.error("Missing configuration: gemini.api.url");
            throw new AiServiceException("Missing configuration: gemini.api.url");
        }
        if (geminiApiKey == null || geminiApiKey.isBlank()) {
            logger.error("Missing configuration: gemini.api.key");
            throw new AiServiceException("Missing configuration: gemini.api.key");
        }

        // Do request and get response. Gemini API requires key in URL query parameter.
        logger.info("Starting Gemini API call");
        String urlWithKey = geminiApiUrl + (geminiApiUrl.contains("?") ? "&" : "?") + "key=" + geminiApiKey;
        try {
            String response = webClient.post()
                    .uri(urlWithKey)
                    .header("Content-Type","application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (response == null) {
                logger.error("Empty response from Gemini API");
                throw new AiServiceException("Empty response from Gemini API");
            }

            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("Gemini API call completed successfully. Execution time: {} ms", executionTime);

            // Extract Response and Return
            return extractResponseContent(response);
        } catch (AiServiceException e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.error("AiServiceException during Gemini API call. Execution time: {} ms. Error: {}", executionTime, e.getMessage());
            throw e;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.error("Unexpected error during Gemini API call. Execution time: {} ms. Error: {}", executionTime, e.getMessage(), e);
            throw new AiServiceException("Error during API call: " + e.getMessage(), e);
        }
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode candidates = rootNode.path("candidates");
            if (!candidates.isArray() || candidates.size() == 0) {
                logger.warn("No candidates field in API response");
                throw new AiServiceException("No candidates field in API response");
            }
            JsonNode first = candidates.get(0);
            JsonNode textNode = first.path("content").path("parts").get(0).path("text");
            if (textNode.isMissingNode()) {
                logger.warn("No text part in API response");
                throw new AiServiceException("No text part in API response");
            }
            return textNode.asText();
        } catch (Exception e) {
            // Bubble up as runtime exception so controller can return 500 with clear message
            logger.error("Error processing API response: {}", e.getMessage(), e);
            throw new AiServiceException("Error processing API response: " + e.getMessage(), e);
        }
    }
}