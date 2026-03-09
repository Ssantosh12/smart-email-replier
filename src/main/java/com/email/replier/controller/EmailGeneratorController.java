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

@RestController
@RequestMapping({"/api/v1/email", "/api/email"})
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class EmailGeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(EmailGeneratorController.class);

    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody @Valid EmailRequest emailRequest) {
        logger.info("Received email generation request");
        String response = emailGeneratorService.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}