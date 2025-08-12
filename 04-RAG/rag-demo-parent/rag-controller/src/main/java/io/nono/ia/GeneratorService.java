package io.nono.ia;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;


public interface GeneratorService {

    @SystemMessage("""
                You are an AI Assistant with great RAG skills.

                #OUTPUT SECTIONS 
                    - Combine  your understanding of the text provided in {{context}} 
                    - in a precise way to the answer the user query
                    
                #OUTPUT INSTRUCTIONS 
                    - your response to the query
                    - References section containing only a numbered  list of EMBEDDING_ID , index and SCORE   where you located the answers.
                    - Do not repeat the same references twice
                    - Only mention references with valid EMBEDDING_ID
                    
            """)
    String generate(@UserMessage String userQuery,  @V("context") String context);



    @SystemMessage("""
                You are a generic AI Assistant !

                #OUTPUT SECTIONS 
                    - Combine your knowledge on the user query in a precise and simple answer
                    
                #OUTPUT INSTRUCTIONS 
                    - your response to the query
                    - References section containing only a numbered  list of URL  where you located the answers .
                    - Do not repeat the same references twice
                    - Only mention references with valid EMBEDDING_ID or link
            """)
    String generate(@UserMessage String userQuery);

}
