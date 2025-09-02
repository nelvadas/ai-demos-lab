package io.nono.mcp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;


record JdkDetails(String jdkVersion,
                  String  docUrl,
                  String latestReleaseVersion,
                  String endOfSupportLifeDate,
                  boolean isLts) {}


record ReleaseItem(String releaseVersion,
                   String releaseDate,
                   String releaseNotesUrl,
                   JdkDetails jdkDetails,
                   String securityStatus,
                   List<String> artifactContentTypes,
                   Integer daysUnderSecurityBaseline,
                   Boolean isScriptDownloadAvailable) {}

record OracleJdkReleaseDetailsResponse(List<ReleaseItem> items) {}

@Service
public class OracleJdkReleaseService {

    private static final Logger logger = LoggerFactory.getLogger(OracleJdkReleaseService.class);

    private final WebClient webClient;

    public OracleJdkReleaseService() {

        this.webClient = WebClient.builder()
                .baseUrl("https://java.oraclecloud.com")
                .build();
    }


    @Tool(name = "getJavaReleaseDetails",
            description = """
        Returns the metadata associated with a specific Java release version
        - Release JDK Version
        - Release Date
        - Release Note URl
        - Latest Release Version
        - End of Support Life Date
        - LTS or STS
        - The security Status
        - Number of days Under SecurityBaseline
        - wheter a script Download is available or no
    """)

    public List<ReleaseItem> getReleaseDetails(String releaseVersion) {

        logger.info("calling getReleaseDetails with input= "+releaseVersion);
        OracleJdkReleaseDetailsResponse response= webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/javaReleases")
                        .queryParam("releaseVersion", releaseVersion)
                        .build())
                .retrieve()
                .bodyToMono(OracleJdkReleaseDetailsResponse.class)
                .block();
        logger.info(response.toString());
        return response != null && response.items() != null
                ?  response.items()
                :  Collections.emptyList();

    }


}
