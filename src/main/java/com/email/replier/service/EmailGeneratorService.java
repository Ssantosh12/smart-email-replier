package com.email.replier.service;

import com.email.replier.client.GeminiClient;
import com.email.replier.dto.EmailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(EmailGeneratorService.class);

    private final GeminiClient geminiClient;

    public EmailGeneratorService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String generateEmailReply(EmailRequest emailRequest) {
        long startTime = System.currentTimeMillis();
        logger.info("Incoming request to generate email reply");

        // Build the prompt
        String prompt = buildPrompt(emailRequest);

        try {
            // Call Gemini API
            String response = geminiClient.generateContent(prompt);
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("Email reply generated successfully. Execution time: {} ms", executionTime);
            return response;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.error("Error generating email reply. Execution time: {} ms. Error: {}", executionTime, e.getMessage());
            throw e;
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line.");
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}