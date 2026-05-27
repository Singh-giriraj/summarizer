package com.summarizer.service;

import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GetBeanConfigurationService {

    @Bean
    public PDFTextStripper pdfTextStripper(){
        return new PDFTextStripper();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
