package io.nono.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.micronaut.langchain4j.annotation.AiService;

@AiService

public interface StructuredOutputService {

            @SystemMessage("""
                You are an expert in World Geography contents.

                #OUTPUT SECTIONS 
                    - The country details in JSON format
                    - The country name
                    - The country capital
                    - The country region
                    - The country subregion
                    - The country population
                    - The country area
                    - The country languages
                    - The country currencies
                    - The country timezones
                    - The country flag
                    
                #OUTPUT INSTRUCTIONS 
                    - Use only json format
                    - Do not use any other format
                    - Do not use any other language
                    - Do not use any other content
                    - Do not use any other output
                    - Do not use any other sections

            
            """)
            Country getCountry(@UserMessage String userMessage);
            
}
