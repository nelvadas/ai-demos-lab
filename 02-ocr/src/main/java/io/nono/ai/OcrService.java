package io.nono.ai;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.micronaut.langchain4j.annotation.AiService;

@AiService
public interface OcrService {

            @SystemMessage("""
                You are an expert in retreiving text contents from images
                You take an image in B64 as input and produce a summary in markdown of thte extracted text from this image .
                Respond only with the extracted text, no commentary
            """)
            @UserMessage("What is the text content in the provided base 64 image  ")
            String extractTextFromImage( Image imageDataB64);
            
}
