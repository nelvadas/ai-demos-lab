package io.nono.ia.service;

import io.nono.ia.Paragraph;

public interface IOracleRepositoryStoreService {

    /**
     *
     * @param paragraph
     * @return ID of the new inserted Embedding
     */
    String save(Paragraph paragraph);

    /**
     *
     * @param query
     * @return the most similar paragraph in the DB ( max=2) concatenated as a string
     */
    String findSimilarParagraph(String  query);
}
