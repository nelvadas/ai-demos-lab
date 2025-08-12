package io.nono.ia;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import dev.langchain4j.community.model.oracle.oci.genai.OciGenAiCohereChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.env.Environment;
import jakarta.inject.Singleton;

import java.io.IOException;

@Factory
public class AiServiceFactory {

    //// OCI Generativie AI Config
    @Value("${oci.compartment-id}")
    private String OCI_COMPARTMENT_ID;
    @Value("${oci.model-name}")
    private String OCI_MODEL_NAME;
    @Value("${oci.auth-profile:DEFAULT}")
    private String OCI_AUTH_PROFILE;
    @Value("${oci.region}")
    private String OCI_REGION;



    // OpenAI Config
    @Value("${OPENAI_API_KEY}")
    private String OPENAI_API_KEY ;



    private OciGenAiCohereChatModel getOciCohereModel() throws IOException {
        return OciGenAiCohereChatModel.builder()
                .region(Region.fromRegionId(OCI_REGION))
                .modelName(OCI_MODEL_NAME)
                .compartmentId(OCI_COMPARTMENT_ID)
                .authProvider(new ConfigFileAuthenticationDetailsProvider(OCI_AUTH_PROFILE))
                .maxTokens(600)
                .temperature(0.2)
                .topP(0.75)
                .build();
    }

    private ChatModel getOllamaModel(String baseUrl, String modelName) {
        return OllamaChatModel.builder()
                .logRequests(true)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .build();

    }



    /* ---------- Service Builder ---------- */

    private GeneratorService buildGeneratorService(ChatModel model) {
        return AiServices.builder(GeneratorService.class)
                .chatModel(model)
                .tools()
                //No memory to avoid /norag  endpoint reusing the output of /rag endpoint
                //.chatMemory(MessageWindowChatMemory.withMaxMessages(0))
                .build();
    }





    @Singleton
    @Requires(property = "ai.provider", value = "ollama")
    public GeneratorService defaultOllamaService(
            @Value("${langchain4j.ollama.base-url}") String baseUrl,
        @Value("${langchain4j.ollama.model-name}") String modelName) throws IOException {
        return buildGeneratorService(getOllamaModel(baseUrl,modelName));
    }



    @Singleton
    @Requires(property = "ai.provider", value = "oci")
    public GeneratorService defaultOciCohereService() throws IOException {
        return buildGeneratorService(getOciCohereModel());
    }
}
