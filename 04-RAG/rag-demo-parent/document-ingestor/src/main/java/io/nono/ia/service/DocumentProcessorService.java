package io.nono.ia.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentBySentenceSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.inject.Singleton;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@Singleton
public class DocumentProcessorService {
    Logger logger = Logger.getLogger(DocumentProcessorService.class.getName());
    public List<TextSegment> parseAndSplit(File file) {

        Document document= null;

        if(file.getName().toLowerCase().endsWith("pdf")){
            try (PDDocument pdfdoc = Loader.loadPDF(file)) {

                PDFTextStripper stripper = new PDFTextStripper();
                document = Document.from(stripper.getText(pdfdoc));

            } catch (IOException e) {
                System.out.println("Failed to load PDF: " + e.getMessage());
            }
        }
        else {
             document = FileSystemDocumentLoader.loadDocument(file.toPath());
        }

        DocumentBySentenceSplitter splitter = new DocumentBySentenceSplitter(512, 50);
        // chunk size 512 tokens, overlap 50
        //logger.log(Level.SEVERE, e.getMessage());

        return splitter.split(document);
    }

}
