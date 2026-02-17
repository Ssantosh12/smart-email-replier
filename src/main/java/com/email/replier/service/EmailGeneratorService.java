package com.email.replier.service;

import com.email.replier.client.GeminiClient;
import com.email.replier.dto.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailGeneratorService {

    private final GeminiClient geminiClient;

    public EmailGeneratorService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String generateEmailReply(EmailRequest emailRequest) {
        // Build the prompt
        String prompt = buildPrompt(emailRequest);

        // Call Gemini API
        return geminiClient.generateContent(prompt);
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