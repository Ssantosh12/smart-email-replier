package com.email.replier.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GenerateEmailRequestDTO {
    @NotBlank(message = "emailContent must not be blank")
    @Size(min = 1, max = 10000, message = "emailContent must be between 1 and 10000 characters")
    private String emailContent;

    // optional tone (e.g. "formal", "casual")
    @Size(max = 50, message = "tone must not exceed 50 characters")
    private String tone;
}