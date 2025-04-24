package io.nono.ai;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "summarizer", description = "...",
        mixinStandardHelpOptions = true)
public class SummarizerCommand implements Runnable {

    @Inject 
    SummarizerService summarizerService;

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names= {"-u","--url"}, description =" Url of the page to summarize")
    String url;


    public static void main(String[] args) throws Exception {
        PicocliRunner.run(SummarizerCommand.class, args);
    }

    public void run() {

         String summary=summarizerService.summarize(url);
         System.out.println(summary);
        
    }


    private String extract(String url) throws IOException, URISyntaxException {
        return Jsoup.parse(new URI(url).toURL(), 10000).text();
    }

}
