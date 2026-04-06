package com.email.replier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Email generation request containing the email content and optional tone preferences")
public class EmailRequest {
    @NotBlank(message = "emailContent must not be blank")
    @Schema(description = "The content of the email to generate a reply for", example = "Thank you for your email. I will look into this matter.")
    private String emailContent;

    @Schema(description = "Optional tone for the reply (e.g., 'formal', 'casual', 'professional')", example = "professional")
    private String tone;
}