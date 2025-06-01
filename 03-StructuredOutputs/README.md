## Tex Summarizer

- Install the latest Micronaut command line 
```sh
❯ mn --version
Micronaut Version: 4.8.2
```

- Run Java 24 
```sh
❯ java -version
java version "24" 2025-03-18
Java(TM) SE Runtime Environment Oracle GraalVM 24+36.1 (build 24+36-jvmci-b01)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 24+36.1 (build 24+36-jvmci-b01, mixed mode, sharing)
```


- Create the project from [micronaut.io/launch](https://micronaut.io/launch) using  command 

```sh
❯ mn create-cli-app --build=maven --jdk=21 --lang=java --test=junit --features=graalvm,logback,langchain4j-ollama,langchain4j-openai io.nono.ai.summarizer
```

- Build the project 

```sh 
cd ai-demos-lab/summarizer
mvn clean install
```

- Make a try

```sh
java -jar target/summarizer-0.1.jar -v -u https://www.graalvm.org/


15:31:57.340 [main] INFO  i.m.c.DefaultApplicationContext$RuntimeConfiguredEnvironment - Established active environments: [cli]
# Summary
GraalVM is a high-performance runtime that provides significant improvements in performance and efficiency for running applications. It supports multiple languages and can be used for various types of workloads.

# Top 10 Points
1. GraalVM is a high-performance polyglot virtual machine that is compatible with multiple languages including Java, JavaScript, Python, and Ruby.
2. It offers improved efficiency and reduced memory consumption compared to traditional virtual machines.
3. GraalVM allows for seamless interoperability between different programming languages, enabling developers to build polyglot applications.
4. This runtime environment is designed for running applications both in the cloud and on-premises.
5. It provides tools for compiling Java applications ahead of time, resulting in faster startup times and lower resource usage.
6. GraalVM can be used for running serverless functions, microservices, and large monolithic applications.
7. It supports native images that can be statically linked to create standalone executables with reduced startup times.
8. GraalVM enhances Java performance by using its Just-In-Time (JIT) compiler, which can optimize code execution dynamically.
9. Developers can leverage GraalVM for building efficient and scalable applications with support for garbage collection and profiling tools.
10. The GraalVM ecosystem includes additional components such as GraalVM Enterprise, GraalVM Native Image, and GraalWasm, offering a comprehensive platform for various use cases.

*Duration: 15 minutes*
```







