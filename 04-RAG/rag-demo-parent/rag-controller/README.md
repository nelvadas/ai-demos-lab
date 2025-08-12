## RAG Controller


This module expose a REST Endpoint to chat an LLM
and custom ingested PDF data.


1. Build the application
```sh 
$ mvn clean install 
```

2. Test the query Endpoint 
```sh
$curl http://localhost:8080/search/rag?userQuery=JavaSE%20Prices
According to the provided text, the Java SE Universal Subscription model offers simple per-employee pricing starting at $15/employee/month and lower depending on volume.

References:

1. **EMBEDDING_ID**: bae5dfe1-932b-4511-8735-2ee2848f8a31
**Index**: 24
**SCORE**: 0.7896159252764934
```

Check the output without the Vector DB 
```sh
$curl http://localhost:8080/search/norag?userQuery=JavaSE%20Prices
The prices for Java SE (Standard Edition) vary depending on the vendor, licensing model, and level of support. Here's a general breakdown:

**Oracle JDK**

* Oracle offers three types of licenses:
	+ Commercial license: This is the most common type, suitable for commercial use cases. The price depends on the number of processors and the level of support.
	+ Personal license: This is free for personal use, but not for commercial purposes.
	+ OpenJDK: This is a free, open-source implementation of Java SE, available under the GNU General Public License (GPL).

**Other Vendors**

* Azul Systems: Offers a commercial license for Zulu Enterprise, which includes support and additional features. Prices start at around $1,000 per year.
* BellSoft: Provides a commercial license for GraalVM CE, which includes support and additional features. Prices start at around $500 per year.
* Amazon Corretto: A free, open-source implementation of Java SE, available under the Apache License 2.0.

**Pricing Tiers**

* For Oracle JDK, the prices typically range from:
	+ Small-scale use (up to 1 CPU): Free
	+ Medium-scale use (1-4 CPUs): $20-$50 per year
	+ Large-scale use (5-16 CPUs): $100-$500 per year
	+ Enterprise-scale use (17+ CPUs): Custom pricing

Please note that these prices are estimates and may vary depending on the vendor, your location, and the specific licensing agreement.

References:

1. Oracle: Java SE Licensing [1]
2. Azul Systems: Zulu Enterprise Pricing [2]
3. BellSoft: GraalVM CE Pricing [3]

[1] https://www.oracle.com/java/technologies/javase-license.html
[2] https://www.azul.com/zulu-enterprise-pricing/
[3] https://bell-sw.com/products/graalvm-ce/pricing/

``` 
