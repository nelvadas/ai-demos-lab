package io.nono.ia;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;
@MicronautTest
public class RagDemoControllerTest {


    /*static GenericContainer<?> ollamaContainer =
            new GenericContainer<>(
                    DockerImageName.parse("ollama/ollama:latest"))
                    .withExposedPorts(11434)
                    .withCommand("/bin/sh", "-c", "ollama serve & sleep 3 && ollama pull llama3 && tail -f /dev/null");
*/


    @Inject
    @Client("/")
    HttpClient client;


    @Test
    public void testQueryEndpointReturnsText() throws Exception {



    }

    @AfterAll
    public static void close(){
        //  ollamaContainer.stop();
    }
}
