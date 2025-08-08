package io.nono.ia.service;


import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import jakarta.inject.Singleton;
import java.util.logging.Logger;

@Singleton
public class EmbeddingService {

    Logger logger = Logger.getLogger(EmbeddingService.class.getName());
    public static final EmbeddingModel embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();

    public Embedding embed(TextSegment textSegment) {
         return   embeddingModel.embed(textSegment.text()).content();

    }

}
