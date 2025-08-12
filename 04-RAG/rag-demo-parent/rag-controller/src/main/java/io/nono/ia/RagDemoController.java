package io.nono.ia;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.QueryValue;
import io.nono.ia.service.OracleRepositoryStoreService;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/search")
class RagDemoController {
    private static final Logger logger = LoggerFactory.getLogger(RagDemoController.class);

    @Inject
    OracleRepositoryStoreService similaritySearchService;

    @Inject
    GeneratorService generatorService;


    @Get(uri = "/rag", produces = MediaType.TEXT_PLAIN)
    String query(@QueryValue String userQuery) {
        logger.info("\uD83D\uDFE2************** RAG Enabled");
        String context = similaritySearchService.findSimilarParagraph(userQuery);
        return generatorService.generate(userQuery , context);
    }


    @Get(uri = "/norag", produces = MediaType.TEXT_PLAIN)
    String queryNoRag(@QueryValue String userQuery) {

        logger.info("\uD83D\uDE21************** No RAG Enabled");
        //No RAG
        return generatorService.generate(userQuery);
    }


}
