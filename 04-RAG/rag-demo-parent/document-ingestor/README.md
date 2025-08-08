## Micronaut 4.9.2 Documentation




## Index documents 

1. Create a DB23ai instance 



```sh
podman run --name oracle-db \
-p 1521:1521 \
-e ORACLE_PWD=Oracle01# \
-e ENABLE_ARCHIVELOG=true \
-e ENABLE_FORCE_LOGGING=true \
-v $(PWD)/oradata:/opt/oracle/oradata \
-v $(PWD)/onnx:/opt/oracle/onnx \
container-registry.oracle.com/database/free:latest
```

2. Initialize applicaiton user and table space .
Connect to DB and perform some initializations 

```sh
podman exec -it oracle-db sqlplus pdbadmin/Oracle01#@FREEPDB1
```


3. Run initialization script
```sql
-- Create a new tablespace 
CREATE TABLESPACE vector_ts
  DATAFILE '/opt/oracle/oradata/FREE/FREEPDB1/vector_ts01.dbf' SIZE 100M
  AUTOEXTEND ON
  SEGMENT SPACE MANAGEMENT AUTO;


-- Check the tablespace filename of the user
CREATE USER JAVA_AI_APPUSER IDENTIFIED BY Oracle01#
DEFAULT TABLESPACE vector_ts
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON vector_ts;


GRANT CREATE SESSION TO JAVA_AI_APPUSER;
GRANT CREATE TABLE TO  JAVA_AI_APPUSER;


SELECT username, default_tablespace
FROM dba_users
WHERE username = 'JAVA_AI_APPUSER';

TRUNCATE  TABLE ORA_DOCS ;
```

4. Build the application 
```sh
cd rag-parent
mvn clean install
```


5. Inject a document in database.
```sh
 java -jar ./target/document-ingestor-0.1.jar --ingest  --path=./src/main/resources/javase-subscription-datasheet.pdf -v
 ```


6. Check Text segment similar to user queries.

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
 

```



