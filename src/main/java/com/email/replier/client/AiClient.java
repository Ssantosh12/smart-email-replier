package com.email.replier.client;

/**
 * Interface for AI service providers.
 * This abstraction allows for different AI implementations (e.g., Gemini, OpenAI, Claude)
 * to be used interchangeably without modifying the service layer.
 */
public interface AiClient {
    /**
     * Generate content based on the provided prompt.
     *
     * @param prompt the input prompt for the AI model
     * @return the generated content response
     * @throws com.email.replier.exception.AiServiceException if the API call fails or returns unexpected response
     */
    String generateContent(String prompt);
}
