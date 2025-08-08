package io.nono.ia;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.nono.ia.service.DocumentProcessorService;
import io.nono.ia.service.EmbeddingService;
import io.nono.ia.service.OracleRepositoryStoreService;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Command(name = "document-ingestor", description = "PDF to Oracle Vector Store Ingestor",
        mixinStandardHelpOptions = true)
public class DocumentIngestorCommand implements Runnable {

    Logger logger = Logger.getLogger(DocumentIngestorCommand.class.getName());

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names = "--ingest", description = "Ingest mode")
    boolean ingest;

    @Option(names = "--search", description = "Search mode")
    boolean search;

    @Option(names = "--query", description = "Query to Search")
    String query;

    @Option(names = "--path", description = "File or folder to ingest")
    File inputPath;



    @Inject
    DocumentProcessorService processor;

    @Inject
    EmbeddingService embeddingService;

    @Inject
    OracleRepositoryStoreService repository;


    public static void main(String[] args) throws Exception {
        PicocliRunner.run(DocumentIngestorCommand.class, args);
    }

    public void run() {

        if (ingest == search) { // both true or both false is invalid
            System.err.println("Error: You must specify exactly one of --ingest or --search");
            return;
        }

        if (verbose) {
            System.out.println("Verbose mode ON");
        }


        if (ingest) {
            File file = new File(inputPath.toURI());
            logger.info("Ingesting file: " + file.getAbsolutePath());
            try {
                handleIngestion(inputPath);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else { // search
            logger.info(String.format("Searching for \"%s\" (limit %d results)%n", query, limit));
            handleSearch(query);
        }





    }

    private void handleSearch(String query) {

        String matches = repository.findSimilarParagraph(query);
        logger.info(String.format("Matches found : %s ",matches));
    }


    private void handleIngestion(File inputPath) throws Exception {

        if (inputPath.isDirectory()) {
            for (File file : inputPath.listFiles((d, name) -> name.endsWith(".pdf"))) {
                processFile(file);
            }
        } else {
            processFile(inputPath);
        }

    }


    private void processFile(File file) throws Exception {
        logger.info("Processing: " + file.getName());
        List<TextSegment> segments = processor.parseAndSplit(file);
        int idx = 0;
        for (TextSegment segment : segments) {
            Embedding embedding = embeddingService.embed(segment);
            String savedId = repository.save(new Paragraph(file.getName(), segment, embedding));
            if (verbose) {
                logger.info(String.format(
                        "\n📄 File      : %s\n🔢 Chunk ID  : %d\n📝 Content   :\n%s\n📊 Embedding : %s\n %s\n", file.getName(),
                        idx,
                        segment.text(),
                        Arrays.toString(embedding.vector()),
                        savedId)
                );
            }
            idx++;
        }
        logger.info("✅ " + file.getName() + "  Size:" + Files.size(file.toPath()) + "B ingested successfully: + " + idx + " Chunks");
    }

}
