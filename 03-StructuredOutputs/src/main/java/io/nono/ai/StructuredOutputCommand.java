package io.nono.ai;


import io.micronaut.configuration.picocli.PicocliRunner;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "StructuredOutput", description = "Identify Countries",
        mixinStandardHelpOptions = true)
public class StructuredOutputCommand implements Runnable {

    @Inject 
    StructuredOutputService countryService;

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names = {"-q", "--question"}, description = "question   ")
    String question;


    public static void main(String[] args) throws Exception {
        PicocliRunner.run(StructuredOutputCommand.class, args);
    }

    public void run() {

        Country country = countryService.getCountry(question);
        System.out.println(country.toString());
        
    }




}
