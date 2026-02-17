package com.email.replier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequest {
    @NotBlank(message = "emailContent must not be blank")
    private String emailContent;

    // optional tone (e.g. "formal", "casual")
    private String tone;
}