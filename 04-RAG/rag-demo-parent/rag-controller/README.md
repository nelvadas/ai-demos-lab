## RAG Controller


This module expose a REST Endpoint to chat an LLM
and custom ingested PDF data.


1. Build the application
```sh 
$ mvn clean install 
```

2. Test the query Endpoint 
```sh
$curl http://localhost:8080/search/rag\?userQuery\=JavaSE
JAvaSEDefaultContent { textSegment = TextSegment { text = "The Java SE
Universal Subscription from Oracle provides access to tools for consistently managing
updates, enabling enterprises to monitor their own Java runtimes, boosting the performance
of applications, and includes access to a specialized Java SE support team. PROTECTING YOUR INVESTMENT IN JAVA SE
Java is the most widely used professional development language and the #1 developer choice
for the cloud." metadata = {index=2} }, metadata = {EMBEDDING_ID=11f56dfe-474d-4b4e-86fd-25dc1d2c5e00, SCORE=0.8316499866646907} }
DefaultContent { textSegment = TextSegment { text = "2 DATA SHEET  |  Oracle Java SE Universal Subscription: Protect Your Investment in Java SE
 Copyright © 2024, Oracle and/or its affiliates
are planned to be available until one year after JDK 25 LTS releases (scheduled for September
2025). With a Java SE Universal Subscription, you will have access to tools, features, and
updates that help you manage your cloud, server, and desktop deployments." metadata = {index=9} }, metadata = {EMBEDDING_ID=6663f2f9-6306-47fc-a9a2-f509b57b79df, SCORE=0.8270349072146105} }
```

Check the output without the Vector DB 
```sh
$curl http://localhost:8080/search/norag\?userQuery\=JavaSE
``` 
