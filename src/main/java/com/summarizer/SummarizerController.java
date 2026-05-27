package com.summarizer;


import com.summarizer.service.ExtractTextFromPDFService;
import com.summarizer.service.GeminiSummarizeTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SummarizerController {

    private static final Logger log = LoggerFactory.getLogger(SummarizerController.class);
    public final GeminiSummarizeTextService geminiSummarizeTextService;
    public final ExtractTextFromPDFService extractTextFromPDFService;

    public SummarizerController(GeminiSummarizeTextService geminiSummarizeTextService, ExtractTextFromPDFService extractTextFromPDFService){
        this.geminiSummarizeTextService = geminiSummarizeTextService;
        this.extractTextFromPDFService = extractTextFromPDFService;

    }


    @PostMapping("/summarize")
    public ResponseEntity<String> summarizePDF(@RequestParam("file") MultipartFile file) {

        String summary = "";
        try{
            String pdfText = extractTextFromPDFService.extractText(file);
            summary = geminiSummarizeTextService.summarizePaper(pdfText);
            log.info(summary);
        }catch(Exception e){
            log.error("giriloggingtest :", e);
        }

        return ResponseEntity.ok(summary);
    }

}
