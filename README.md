# Summarizer App

Spring Boot application to summarize PDF research papers using Google Gemini AI.

## Features
- **PDF Text Extraction**: Uses Apache PDFBox to read content from uploaded PDF files.
- **AI Summarization**: Leverages Google Gemini (v1.5 Flash) to generate comprehensive summaries highlighting problems, methodology, and conclusions.
- **REST API**: Simple endpoint for file uploads.

## Prerequisites
- **Google AI API Key**: Obtain one from [Google AI Studio](https://aistudio.google.com/).
- **Docker**: For containerized deployment.
- **Java 17+ / Maven**: For local development.

## Getting Started

### 1. Build and Run with Docker

Build the Docker image:
```bash
docker build -t summarizer .

### or If you are building on a Mac (ARM) but targeting a Linux (AMD64) server, Docker might struggle to pull images that don't explicitly support both architectures. you can force Docker to use a specific architecture (e.g., linux/amd64) by adding a platform flag to your build command:

docker build --platform linux/amd64 -t testspring .
```

Run the container (inject your API key):
```bash
docker run -p 8080:8080 -e GEMINI_API_KEY=your_google_api_key_here summarizer
```

### 2. Local Development

Set the environment variable:
```bash
export GEMINI_API_KEY=your_google_api_key_here
```

Run with Maven:
```bash
./mvnw spring-boot:run
```

## API Usage

### Summarize PDF
**Endpoint**: `POST /api/summarize`  
**Body**: `multipart/form-data` with `file` field.

**Example with cURL**:
```bash
curl -X POST -F "file=@/path/to/your/paper.pdf" http://localhost:8080/api/summarize
```

## Configuration
The app expects `GEMINI_API_KEY` environment variable. This is mapped in `src/main/resources/application.properties`:
```properties
gemini.api.key=${GEMINI_API_KEY}
```
