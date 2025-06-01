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


- Run llava:7b model from Ollama 

```sh
❯ ❯ ollama run llava:7b
❯ 
```

- Build the project 

```sh 
cd ai-demos-lab/02-ocr
mvn clean install
```

- Make a try

```sh
 java -jar target/ocr-0.1.jar  -f src/main/resources/images/ocr-demo-text.png -v
```


```sh
 java -jar target/ocr-0.1.jar -f  02-ocr/src/main/resources/images/Sample-handwritten-text-input-for-OCR.png -v
```




