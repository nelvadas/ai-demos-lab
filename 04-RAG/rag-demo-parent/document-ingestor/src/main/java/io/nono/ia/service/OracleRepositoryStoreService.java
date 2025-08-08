package io.nono.ia.service;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.oracle.EmbeddingTable;
import dev.langchain4j.store.embedding.oracle.OracleEmbeddingStore;
import io.micronaut.context.env.Environment;
import io.nono.ia.Paragraph;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import static dev.langchain4j.store.embedding.oracle.CreateOption.CREATE_IF_NOT_EXISTS;

@Singleton
public class OracleRepositoryStoreService implements IOracleRepositoryStoreService {


    @Inject
    Environment environment;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OracleRepositoryStoreService.class);
    Logger logger = Logger.getLogger(OracleRepositoryStoreService.class.getName());
    public static final EmbeddingModel embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();
    PoolDataSource dataSource= null ;
    EmbeddingStore<TextSegment> embeddingStore;
    ContentRetriever retriever ;



    @Inject
    public OracleRepositoryStoreService (Environment environment) {

        try {
            dataSource = PoolDataSourceFactory.getPoolDataSource();
            dataSource.setConnectionFactoryClassName("oracle.jdbc.datasource.impl.OracleDataSource");
            dataSource.setURL(environment.get("oracle.jdbc.url", String.class).orElse("N/A"));
            dataSource.setUser(environment.get("oracle.jdbc.user", String.class).orElse("N/A"));
            dataSource.setPassword(environment.get("oracle.jdbc.password", String.class).orElse("N/A"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


         embeddingStore =
                OracleEmbeddingStore.builder()
                        .dataSource(dataSource)
                        .embeddingTable(EmbeddingTable.builder()
                                .createOption(CREATE_IF_NOT_EXISTS) // Maintain table states accross
                                .name("ORA_DOCS")
                                .idColumn("ID")
                                .embeddingColumn("EMBEDDING")
                                .textColumn("TEXT")
                                .metadataColumn("METADATA")
                                .build())
                        .build();

        // RAG Retreiver.
        retriever= EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2)
                .minScore(0.5)
                .build();

    }

    @Override
    public String save(Paragraph paragraph){

     String  id= embeddingStore.add(paragraph.embedding(),paragraph.text());
     logger.info(" Insert Paragraph "+ paragraph + "with ID:"+id );
    return id;
    }

    @Override
    public String findSimilarParagraph(String query) {

        List<Content> matches = retriever.retrieve(Query.from(query));
        StringBuilder stringBuilder = new StringBuilder();
        for (Content c : matches) {
            logger.info("Match Found: "+c.textSegment().text());
            stringBuilder.append(c.toString()).append("\n");
        }

        return stringBuilder.toString();
    }


}
