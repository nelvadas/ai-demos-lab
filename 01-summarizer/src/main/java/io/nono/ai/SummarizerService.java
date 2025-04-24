package io.nono.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.micronaut.langchain4j.annotation.AiService;

@AiService

public interface SummarizerService {

            @SystemMessage("""
                You are an expert in summarizing contents.
                You take a string as input and produce a summary in markdown using the format below.

                #OUTPUT SECTIONS 
                    - Combine all of your understanding in a 2 sentences
                    - Output the 10 most important point of the content as a numbered list

                #OUTPUT INSTRUCTIONS 
                    - Use only output human readable markdown
                    - Use list and bullet points
                    - Do not repeat items
                    - Also print the duration it takes you to provide this summary.

            
            """)
            String summarize(@UserMessage String userMessage);
            
}
