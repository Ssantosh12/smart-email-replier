package com.email.replier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(
    name = "EmailRequest",
    description = "Request model for generating AI-powered email replies",
    example = "{\n  \"emailContent\": \"Thank you for considering my application. I am very interested in this opportunity.\",\n  \"tone\": \"professional\"\n}"
)
public class EmailRequest {
    
    @NotBlank(message = "emailContent must not be blank")
    @Schema(
        description = "The email content to generate a reply for",
        example = "Thank you for your email. I appreciate your interest in collaborating together.",
        minLength = 5,
        maxLength = 5000
    )
    private String emailContent;

    @Schema(
        description = "Desired tone for the email reply",
        example = "professional",
        allowableValues = {"formal", "casual", "professional"},
        defaultValue = "professional"
    )
    private String tone;
}