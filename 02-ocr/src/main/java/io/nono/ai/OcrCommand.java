package io.nono.ai;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import dev.langchain4j.data.image.Image;
import io.micronaut.configuration.picocli.PicocliRunner;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


/**
 * Command-line tool for extracting text from images using OCR.
 * 
 * Example usage:
 * <pre>
 *   java -jar app.jar ocr --file path/to/image.jpg [--verbose]
 * </pre>
 */


@Command(name = "ocr", description = "Extracts text from an image file using OCR", mixinStandardHelpOptions = true)
public class OcrCommand implements Runnable {

    @Inject 
    OcrService ocrService;

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names= {"-f","--file"}, description =" Image file")
    String filePath;


    public static void main(String[] args) throws Exception {
        PicocliRunner.run(OcrCommand.class, args);
    }

    public void run() {


       
        try {
            Path path = Path.of(filePath);

            if (verbose) {
                System.out.println("Reading file: " + path.toAbsolutePath());
            }
            if (!Files.exists(path)) {
               throw new FileNotFoundException("File not found: " + path);
            }

            byte[] fileContent = Files.readAllBytes(path);
            var  base64Image =  Base64.getEncoder().encodeToString(fileContent);
            if (verbose) {
                System.out.println("Base64-encoded image size: " + base64Image.length());
            }
             Image img = Image.builder().base64Data(base64Image).build();

         String output=ocrService.extractTextFromImage(img);
         System.out.println(output);

        } catch (IOException e) {
            System.err.println("Error: Unable to read the file '" + filePath + "'. " + e.getMessage());
            if (verbose) {
                e.printStackTrace(System.err);
            }
        }
        
        
    }


    

}
