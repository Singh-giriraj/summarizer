package com.summarizer.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExtractTextFromPDFService{

    public final PDFTextStripper pdfTextStripper;
    public ExtractTextFromPDFService(PDFTextStripper pdfTextStripper) {
    this.pdfTextStripper = pdfTextStripper;
    }

    public String extractText(MultipartFile file) throws IOException{
    try(PDDocument document = Loader.loadPDF(file.getBytes())){
        return pdfTextStripper.getText(document);
    }
   }
}
