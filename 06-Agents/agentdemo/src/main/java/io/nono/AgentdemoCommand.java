package io.nono;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import dev.langchain4j.community.model.oracle.oci.genai.OciGenAiCohereChatModel;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import io.micronaut.configuration.picocli.PicocliRunner;


import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.List;

@Command(name = "agentdemo", description = "AI Agent Demo",
        mixinStandardHelpOptions = true)


public class AgentdemoCommand implements Runnable {


    private static final Logger logger = LoggerFactory.getLogger(AgentdemoCommand.class);

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names = {"-j", "--job"}, description = "Job Position , eg: Software Engineer",defaultValue = "Software Engineer")
    String  job;

    @Option(names = {"-t", "--technology"}, description = "technologies : ", defaultValue = "java")
    String  technology;


    @Option(names = {"-c", "--country"}, description = " country where the job is located", defaultValue = "France")
    String country;

    //// OCI Generativie AI Config
    @Value("${oci.compartment-id}")
    private String OCI_COMPARTMENT_ID;
    @Value("${oci.model-name}")
    private String OCI_MODEL_NAME;
    @Value("${oci.auth-profile:DEFAULT}")
    private String OCI_AUTH_PROFILE;
    @Value("${oci.region}")
    private String OCI_REGION;

    @Value("${tavily.mcp.server.url}")
    private String TAVILY_API_URL;

    ChatModel model;


    public static void main(String[] args) throws Exception {
        PicocliRunner.run(AgentdemoCommand.class, args);
    }

    public void run() {

        try {
            model=OciGenAiCohereChatModel.builder()
                    .region(Region.fromRegionId(OCI_REGION))
                    .modelName(OCI_MODEL_NAME)
                    .compartmentId(OCI_COMPARTMENT_ID)
                    .authProvider(new ConfigFileAuthenticationDetailsProvider(OCI_AUTH_PROFILE))
                    .maxTokens(600)
                    .temperature(0.2)
                    .topP(0.75)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Configure connection to Tavilly MCP Server for web search
        McpTransport tavily_transport = new StreamableHttpMcpTransport.Builder()
                .url(TAVILY_API_URL)
                .logRequests(true)
                .logResponses(true)
                .build();




        McpClient tavilyMcpClient = new DefaultMcpClient.Builder()
                .transport(tavily_transport)
                .build();




        //Tool Provider : Tavily and Filesystem
        McpToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(tavilyMcpClient))
                .build();


        IDemoAgent agent= AiServices.builder(IDemoAgent.class)
                .chatModel(model)
                .toolProvider(toolProvider)
                .build();

        logger.info("using model + "+model);
        logger.info(" Searching with parameters job="+job +" Country="+country +" Technology="+technology);
        String response = agent.searchOpenPosition(country, job, technology,15);
        logger.info(response);


    }
}
