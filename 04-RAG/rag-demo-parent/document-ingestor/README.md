## Micronaut 4.9.2 Documentation




## Index documents 

```sql
TRUNCATE TABLE ORA_DOCS;
```


```sh
 java -jar ./target/document-ingestor-0.1.jar --ingest  --path=./src/main/resources/javase-subscription-datasheet.pdf -v
 ```


## Search Similarities 

```sh
❯ java -jar ./target/document-ingestor-0.1.jar --search --query="What is Java Universal subscription"

INFO: Searching for "What is Java Universal subscription" (limit 2 results)

août 08, 2025 4:02:57 PM io.nono.ia.service.OracleRepositoryStoreService findSimilarParagraph
INFO: Match Found: Java SE Universal Subscription includes all 
patches and global support for the Java SE 
platform for all major releases. Java SE Universal 
Subscription, through its provision of Java 
patches for older versions, enables you to remain 
on those versions for longer because the latest 
performance, stability, and security 
improvements for the Java platform are applied.
août 08, 2025 4:02:57 PM io.nono.ia.service.OracleRepositoryStoreService findSimilarParagraph
INFO: Match Found: 3 DATA SHEET  |  Oracle Java SE Universal Subscription: Protect Your Investment in Java SE 
 Copyright © 2024, Oracle and/or its affiliates     
Use Java SE Universal Subscription’s term-based licensing and support to maintain your 
flexibility around the transition point between versions while at the same time making sure 
that your Java platform remains stable and up to date.
août 08, 2025 4:02:57 PM io.nono.ia.DocumentIngestorCommand handleSearch
INFO: Matches found : DefaultContent { textSegment = TextSegment { text = "Java SE Universal Subscription includes all 
patches and global support for the Java SE 
platform for all major releases. Java SE Universal 
Subscription, through its provision of Java 
patches for older versions, enables you to remain 
on those versions for longer because the latest 
performance, stability, and security 
improvements for the Java platform are applied." metadata = {index=38} }, metadata = {EMBEDDING_ID=5175d976-1b03-4c04-b11f-82219420ef4d, SCORE=0.9059996981662681} }
DefaultContent { textSegment = TextSegment { text = "3 DATA SHEET  |  Oracle Java SE Universal Subscription: Protect Your Investment in Java SE 
 Copyright © 2024, Oracle and/or its affiliates     
Use Java SE Universal Subscription’s term-based licensing and support to maintain your 
flexibility around the transition point between versions while at the same time making sure 
that your Java platform remains stable and up to date." metadata = {index=19} }, metadata = {EMBEDDING_ID=c35a5485-e78d-47c1-941f-76931d9d7228, SCORE=0.8830999561775812} }
 
16:02:57.743 [main] DEBUG io.micronaut.context.DefaultBeanContext -- Stopping BeanContext
16:02:57.743 [main] DEBUG io.micronaut.context.DefaultBeanContext -- Finding candidate beans for type: ApplicationEventPublisher<ShutdownEvent T>
16:02:57.743 [main] DEBUG io.micronaut.context.DefaultBeanContext --   interface io.micronaut.context.event.ApplicationEventPublisher null io.micronaut.context.event.ApplicationEventPublisherFactory@65eb1656
16:02:57.743 [main] DEBUG io.micronaut.context.DefaultBeanContext -- Found concrete candidate [io.micronaut.context.event.ApplicationEventPublisherFactory@65eb1656] for type: applicationEventPublisher
```



