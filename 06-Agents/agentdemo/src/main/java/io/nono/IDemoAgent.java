package io.nono;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface IDemoAgent {

    @SystemMessage(
            """
                    You are a General Researcher.
                    Always use available tools to gather information before replying.
                    When searching with Tavily use  general category
                    Return the 10 most relevant items
            """
            )
    @UserMessage(
             """
             
             Find open positions related to  {{job}} with the following criteria:
                      general category
                      Country:  positions opened in {{country}}.
                      Technologies:  scope include  {{technology}}.
                      Publication date: item posted within the last {{publication_date_limit_in_days}} days first.
              Output requirements:
                      Format the results in Markdown 
                      For each job position, include:
                      * Reference/Identifier
                      * Title
                      * Posting Date : format it like dd/MM/yyyy
                      * any contact: name and email associated with the offer
                      * Job Posting Link
                      
            """
            )
    String searchOpenPosition(

                            @V("country") String country,
                            @V("job") String job,
                            @V("technology") String technology,
                            @V("publication_date_limit_in_days") int publication_date_limit_in_days
    );


}
