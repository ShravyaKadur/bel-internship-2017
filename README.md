## Efficient real-time data querying w/ Kafka and Ignite

#### Part 1

Generating a million messages and performing queries on them through Ignite make up the first section of the project.

IgniteWorkspace contains all code and the IgniteDocumentation.docx contains the documentation.
* Contains all main Java classes. 
* PutMessageIgnite.java generates 1 million messages and puts into Ignite cache.
* QueryMessages.class contains SQL queries performed using SqlQuery class on the cache data.
* QueryMessages2.class contains queries with **SqlFieldsQuery** class.
                      
#### Part 2

This section focuses on Kafka-Ignite connect.

KafkaConnectDocumentation.docx contains the documentation for this part.
* **logs** contains all logs of Kafka, to debug errors faced while connecting. 
* **myconfig** contains the configured connector properties.                      
* **Dependencies** contains the classpath and the list of JARs used to connect without encountering errors.

#### Part 3

This section focuses on collating the above 2 parts.

More specifically, it loads the Messages generated in Part 1 into a CSV file, passes each Message from there to a UDP port. Kafka then reads from this port and stores it in its broker. This data is finally streamed to Ignite cache via KafkaConnect and queries are performed on it.
