# 📧 Email Replier - AI-Powered Email Response Generator

An intelligent email reply generation service powered by **Google Gemini AI**. This Spring Boot application automatically generates context-aware, professional email responses based on incoming email content and user preferences.

## ✨ Features

- 🤖 **AI-Powered Responses** - Uses Google Gemini API for intelligent email generation
- 🎯 **Tone Customization** - Generate replies in formal, casual, or professional tones
- ⚡ **Fast Processing** - Optimized responses with execution time tracking
- 📚 **Interactive API Docs** - Swagger UI for easy API exploration and testing
- 🔄 **Reactive Architecture** - Built with Spring WebFlux for non-blocking operations
- ✅ **Input Validation** - Comprehensive validation for request data
- 🛡️ **Global Exception Handling** - Centralized error management
- 🚀 **Deployment Ready** - Containerized with Docker, deployed on Render

## 🚀 Quick Start

### Prerequisites

- Java 17+
- Maven 3.6+
- Google Gemini API Key

### Installation

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd email-replier
   ```

2. **Set environment variables**
   ```bash
   export GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent
   export GEMINI_API_KEY=your-api-key-here
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📖 API Documentation

### Interactive API Docs

Visit the **Swagger UI** for an interactive API playground:
```
http://localhost:8080/swagger-ui.html
```

### API Schema

OpenAPI JSON specification:
```
http://localhost:8080/api-docs
```

### Endpoints

#### Generate Email Reply

**Endpoint:** `POST /api/v1/email/generate`

**Request Body:**
```json
{
  "emailContent": "Thank you for reaching out. I'd like to discuss collaboration opportunities.",
  "tone": "professional"
}
```

**Parameters:**
- `emailContent` (required) - The email content to generate a reply for
- `tone` (optional) - Desired tone for the response: `formal`, `casual`, or `professional`

**Response:**
```json
{
  "generatedReply": "Thank you for your interest. I would be delighted to explore collaboration opportunities with you. Please let me know your availability for a discussion..."
}
```

**Example using cURL:**
```bash
curl -X POST http://localhost:8080/api/v1/email/generate \
  -H "Content-Type: application/json" \
  -d '{
    "emailContent": "Can you help me with this project?",
    "tone": "professional"
  }'
```

## 🏗️ Project Structure

```
email-replier/
├── src/
│   ├── main/
│   │   ├── java/com/email/replier/
│   │   │   ├── controller/
│   │   │   │   ├── EmailGeneratorController.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── service/
│   │   │   │   └── EmailGeneratorService.java
│   │   │   ├── client/
│   │   │   │   ├── AiClient.java
│   │   │   │   └── GeminiClient.java
│   │   │   ├── config/
│   │   │   │   ├── WebClientConfig.java
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── dto/
│   │   │   │   ├── EmailRequest.java
│   │   │   │   └── ApiErrorResponse.java
│   │   │   ├── exception/
│   │   │   │   ├── AiServiceException.java
│   │   │   │   ├── EmailGenerationException.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   └── EmailReplierApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/email/replier/
│           └── EmailReplierApplicationTests.java
├── pom.xml
├── Dockerfile
├── Procfile
└── README.md
```

## 🔧 Configuration

Create a `.env` file or set environment variables:

```env
# Gemini API Configuration
GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent
GEMINI_API_KEY=your-actual-api-key

# Spring Configuration (optional)
spring.application.name=email-replier
server.port=8080
```

## 📝 Technologies Used

- **Framework:** Spring Boot 3.5.3
- **Java Version:** Java 17
- **Build Tool:** Maven
- **Reactive:** Spring WebFlux
- **API Documentation:** SpringDoc OpenAPI (Swagger)
- **AI Integration:** Google Gemini API
- **HTTP Client:** WebClient (Spring)
- **Validation:** Jakarta Bean Validation
- **Lombok:** Simplified Java code

### Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
```

## 🐳 Docker Deployment

### Build Docker Image

```bash
docker build -t email-replier .
```

### Run with Docker

```bash
docker run -p 8080:8080 \
  -e GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent \
  -e GEMINI_API_KEY=your-api-key \
  email-replier
```

## ☁️ Cloud Deployment

### Deploy on Render

1. Connect your GitHub repository to Render
2. Create a **Web Service**
3. Set build command: `./mvnw clean install`
4. Set start command: `java -jar target/email-replier-0.0.1-SNAPSHOT.jar`
5. Add environment variables:
   - `GEMINI_API_URL`
   - `GEMINI_API_KEY`
6. Deploy!

Your app will be available at: `https://your-app-name.onrender.com`

### Access Deployed API Docs

```
https://your-render-app-url/swagger-ui.html
```

## 🧪 Testing

```bash
./mvnw test
```

## ❌ Error Handling

The API provides detailed error responses:

**Example Error Response:**
```json
{
  "timestamp": "2026-04-06T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "emailContent must not be blank",
  "path": "/api/v1/email/generate"
}
```

## 🤝 Contributing

Feel free to submit issues and enhancement requests!

## 📄 License

This project is open source.

## 📧 Contact

For questions or support, please reach out.

---

**Made with ❤️ using Spring Boot and Google Gemini AI**
