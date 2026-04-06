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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
@Tag(name = "Email Generation", 
      description = "🤖 AI-Powered Email Reply Generation\n\n" +
              "Generate intelligent, context-aware email responses using Google Gemini AI.\n\n" +
              "**Supported Tones:** formal, casual, professional\n\n" +
              "**Use Cases:**\n" +
              "- Business correspondence\n" +
              "- Professional networking\n" +
              "- Customer service\n" +
              "- Project proposals")
@RestController
@RequestMapping("/api/v1/email")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class EmailGeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(EmailGeneratorController.class);

    private final EmailGeneratorService emailGeneratorService;

    @Operation(
        summary = "🎯 Generate AI Email Reply",
        description = "Generate a contextual, professional email reply based on the provided email content.\n\n" +
                "**How it works:**\n" +
                "1. Submit the email content you received\n" +
                "2. Optionally specify the desired tone\n" +
                "3. Get an AI-generated reply instantly\n\n" +
                "**Tone Options:**\n" +
                "- `formal` - Professional and formal language\n" +
                "- `casual` - Friendly and informal tone\n" +
                "- `professional` - Standard business tone (default)\n\n" +
                "**Performance:** Average response time < 2 seconds",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "✅ Email reply generated successfully",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Successful Response",
                        value = "\"Thank you for your interest. I appreciate the opportunity and would be delighted to explore this further. Please let me know your availability for a discussion at your earliest convenience.\""
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "❌ Bad Request - Invalid input (empty emailContent, validation failed)"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "❌ Server Error - AI service unavailable or API error"
            )
        }
    )
    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(
            @RequestBody @Valid EmailRequest emailRequest) {
        logger.info("Received email generation request");
        String response = emailGeneratorService.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}