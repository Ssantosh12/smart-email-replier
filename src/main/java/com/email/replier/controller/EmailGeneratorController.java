package com.email.replier.controller;

import com.email.replier.dto.EmailRequest;
import com.email.replier.service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
@OpenAPIDefinition(
    info = @Info(
        title = "Email Replier API",
        version = "1.0.0",
        description = "AI-powered email reply generation API powered by Google Gemini. Generate context-aware email responses instantly.",
        contact = @Contact(
            name = "Email Replier Project"
        )
    )
)
@Tag(name = "Email Generation", description = "Endpoints for generating AI-powered email replies")@RestController
@RequestMapping({"/api/v1/email", "/api/email"})
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class EmailGeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(EmailGeneratorController.class);

    private final EmailGeneratorService emailGeneratorService;

    @Operation(
        summary = "Generate an AI-powered email reply",
        description = "Generates a contextual email reply based on the provided email content, tone, and style preferences using Google Gemini AI.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Email reply generated successfully"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request body"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        }
    )
    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody @Valid EmailRequest emailRequest) {
        logger.info("Received email generation request");
        String response = emailGeneratorService.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}