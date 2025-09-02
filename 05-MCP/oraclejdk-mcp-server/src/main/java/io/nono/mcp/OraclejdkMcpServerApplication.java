package io.nono.mcp;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OraclejdkMcpServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(OraclejdkMcpServerApplication.class, args);
	}


	@Bean
	public List<ToolCallback> tools(OracleJdkReleaseService oracleJdkReleaseService) {
		return List.of(ToolCallbacks.from(oracleJdkReleaseService));
	}

}
