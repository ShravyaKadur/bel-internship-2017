# Efficient real-time data querying w/ Kafka and Ignite

I. IgniteWorkspace and IgniteDocumentation.docx make up the first section of the project.
IgniteWorkspace contains all code and the IgniteDocumentation.docx contains the documentation.
IgniteWorkspace/src - Contains all main Java classes. 
                      PutMessageIgnite.java generates 1 million messages and puts into Ignite cache.
                      QueryMessages.class contains SQL queries performed using SqlQuery class on the cache data.
                      QueryMessages2.class contains queries with SqlFieldsQuery class.
