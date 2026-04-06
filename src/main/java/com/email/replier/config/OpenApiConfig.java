package com.email.replier.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Email Replier API")
                        .version("1.0.0")
                        .description("🤖 AI-Powered Email Reply Generation API\n\n" +
                                "Generate intelligent, context-aware email responses in seconds using Google Gemini AI.\n\n" +
                                "**Features:**\n" +
                                "- 🎯 Tone Customization (formal, casual, professional)\n" +
                                "- ⚡ Real-time Response Generation\n" +
                                "- 📊 Execution Time Tracking\n" +
                                "- ✅ Input Validation\n" +
                                "- 🛡️ Error Handling\n\n" +
                                "**Quick Start:**\n" +
                                "```bash\n" +
                                "curl -X POST https://your-api.com/api/v1/email/generate \\\n" +
                                "  -H 'Content-Type: application/json' \\\n" +
                                "  -d '{\"emailContent\": \"Thank you for your offer\", \"tone\": \"professional\"}'\n" +
                                "```")
                        .contact(new Contact()
                                .name("Email Replier Project")
                                .url("https://github.com/yourusername/email-replier")
                                .email("support@emailreplier.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/yourusername/email-replier"))
                .servers(Arrays.asList(
                        new Server()
                                .url("https://email-replier.onrender.com")
                                .description("Production Server (Render)"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server")
                ));
    }
}
