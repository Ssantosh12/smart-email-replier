package com.email.replier.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Email Replier API")
                        .version("1.0.0")
                        .description("AI-powered email reply generation API powered by Google Gemini. Generate context-aware email responses instantly.")
                        .contact(new Contact()
                                .name("Email Replier Project")));
    }
}
