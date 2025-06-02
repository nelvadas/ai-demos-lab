# Structure Output


## Introduction

Structured Outputs is a feature that ensures the model will always generate responses that adhere to your supplied JSON Schema. 
This example takes a prompt about countries and returns a JSON structure representing the country that matches the user's input.




- Build the project 

```sh 
cd ai-demos-lab/03-StructuredOutputs
mvn clean install
```

- Make a try

```sh

❯ java -jar target/StructuredOutput-0.1.jar  -v -q " What is the most populated country in the world ?"
23:25:15.292 [main] INFO  i.m.c.DefaultApplicationContext$RuntimeConfiguredEnvironment - Established active environments: [cli]
Country[name=China, capital=Beijing, region=Asia, subregion=Eastern Asia, population=1400280000, area=9596960 km2, languages=[Chinese], currencies=[Renminbi], flag=https://restcountries.com/data/chn.svg]


```







