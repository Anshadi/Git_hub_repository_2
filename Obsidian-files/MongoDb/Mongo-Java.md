When we create a java application that connects to mongo db , it req. a set of libraries interacting with the mongodb instance . These libraries are referred to as drivers .

Mongodb maintains official drivers for both synchronous and asynchronous java application .
![[Pasted image 20250225160256.png]]

![[Pasted image 20250225202405.png]]

The mongo connection string contains the dns service or the srv records . It contains the info about the port and the target host in the replica set .

Here the mongo client settings allows us to control the behavior of the mongo client instance .
Server Api sets the server api to use when sending commands to the server . And also sets the stable api version .
![[Pasted image 20250225203011.png]]

An application should use a single Mongo Client instance for all database req .
 Instantiating Mongo Client is resource intensive .
 ![[Pasted image 20250225203219.png]]
```
The dependency used is -
```
```
<dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongodb-driver-sync</artifactId>
        <version>4.7.1</version>
    </dependency>
</dependencies>
```

Make a connection.java file and paste this code in it 
```

package com.mongodb.quickstart;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Connection {

    public static void main(String[] args) {
        String connectionString = System.getProperty("mongodb.uri");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }
    }
}
```


### Troubleshooting a Mongo Db Connection 

![[Pasted image 20250225203838.png]]

Incorrect password or username - Authentication error 
Unencoded special charachters
Users not defined in the default admin database 
If users are defined in different database then authSource parameter must be defined in the database string .


Firewall restrictions can be there too .
![[Pasted image 20250225204119.png]]

![[Pasted image 20250225204128.png]]

Remember that our java code calls for MongoClient.close() function .

### Mongo compass 
Here we can create query with just words , see the statics  and create a index on suggestion of the compass with a single click , can see the time taken to execute the query and in which part of the query it took most time .

We can also create the aggregation in the compass using the aggregation tab . We can also save this aggregation using save pipeline button .

Now if the aggregation query is written in any other lang. , we can choose the button export and then convert it in the language that we want and paste .


### Mongo VS Extension -
Can use mongodb extension in vs code and connect through the connection string through that .

It will help to navigate the mongo db through vs code .
Though the document in mongodb is polymorphic , but if some field is consistent in all the document , we can know about it by seeing the schema field in it , it will be represented by the pound sign icon if it is integer . like this 
![[Pasted image 20250225211143.png]]


We can also see the indexes that are made in that database .
We can also install mongodb shell in our system and then can access it using the terminal of our VS Code Using this Extension .

![[Pasted image 20250225235711.png]]
![[Pasted image 20250225235733.png]]

We can create code in the playground , run it and see the output and will see its effect in the database .

we can also save this code for  later execution again .
Ex- this code![[Pasted image 20250226002141.png]]

I can select here what code i have to run and can run only those code only . I can use node js build in modules here also like this 
![[Pasted image 20250226002516.png]]

it can also be used to run Aggregation Pipeline and in the language we want .


### Spring Mongo 
![[Pasted image 20250226003506.png]]

![[Pasted image 20250226003600.png]]

![[Pasted image 20250226004139.png]]


### Mongo Db Crud Operations In Java 

Bson - Binary Json
It is the data format that mongo db uses to organize and store data .

- Optimized for storage retrieval and transmission across the wire .
- More secure than json as it was more vulnerable to json attack Ajax .
- Supports more data types than json .

The `document class` is preferred for representing the Bson data , though provided many by the java driver class .

The Document class implements the Bson Interface .

Now if we have to generate a document in the java for the mongo db equivalent doc , it can be done through the following way , 
![[Pasted image 20250226153423.png]]
![[Pasted image 20250226153231.png]]
Here for date , we have used `Java.util.date class` .
Here to join each field , we use `.append()` function .

As we have to make the address as the #Embedded_Document
 , we used `.document()` function .


#### Inserting Doc in Java Application -
We can insert one doc. using the #InsertOne() in the Java Application .
The Insert One function returns the `InsertOneResult` object .
![[Pasted image 20250226154649.png]]
Now if we want to insert many documents , then we will first create the documents and then we will create an array of lists for them and then using the `InsertMany()` function , we will insert this into our collection .
It will return an ` InsertManyResult ` .

We can get the Id of the inserted doc by using the method `.getInsertedIds()` .
![[Pasted image 20250226154949.png]]


#### Retrieving Mongo Db Docs in a Java Collection 

Here we will use the `find() `and` find.first()` Methods .

Query Conditions also known as predicates are also used to filter the query .
![[Pasted image 20250226155549.png]]

Here the overloaded find method accepts the #filter class that is used to filter the query and we can iterate it using `.forEach()` .

If we want  to process each document returned from the find method , we can use the cursor .
![[Pasted image 20250226155815.png]]

In some cases we want to return a single  document from the result then we can use `find().first()` method chain .

![[Pasted image 20250226160240.png]]


Filter query helps us optimize system resources such as 
- RAM
- Network
- Disk I/o
- CPU


#### Updation of Mongo Docs in the Java Application 

It can be done in two ways -
- Updating the value of an existing Field 
- Define a new field and value 

Here we use the 
`UpdateOne()` when we want to update a Single Document .
- It accepts a query filter and an Update document .
![[Pasted image 20250226161751.png]]
The Update One method finds the first document that matches the query perform the operations on it .
It returns the `UpdateResult()` object that includes the updates no. of documents which will be either be 1 or 0 .
To Update fields with an array values , we can use ![[Pasted image 20250226230347.png]]


- `UpdateMany()` Modifies multiple document in a single operation .
- ![[Pasted image 20250226230451.png]]
- ![[Pasted image 20250226230554.png]]

Here for query , we perform the function on the Filters class , and for performing updation operation , we perform on the class `Updates` class .
(I think maybe they are static class of document class that's why we can use it like this .)


#### Deletion of Mongo Docs in the Java Application 
Here , we use the delete One method , it accepts the query filter and it returns an instance of `DeleteResult`  which has the records of how many documents have been deleted .

![[Pasted image 20250226231621.png]]

If it is deleted with the empty query filter , it deletes the first document that matches it But not All .

DeleteMany() -
To delete multiple documents in a single operation , we can use the method - `deleteMany()` Method .
- It also returns  the instance of delete result .
- It also accepts the query filter and it deletes all the documents that matches the query .
- ![[Pasted image 20250226231900.png]]
- Through this instance we can also get the info such as the no. of docs that were deleted.
- ![[Pasted image 20250226232001.png]]
Delete Many with an empty query will delete all documents .


#### Creating MongoDb Transactions in java 
Multi-Document Transactions - It  is an operation that requires atomicity of reads and/or writes to multiple documents .
A transaction is a seq. of database operations that represents a single unit of work .
If a transactions is failed , all write operations performed in that transactions are discarded
It also is  ACID Compliant .

To perform Transactions inside the Java Application -
- ![[Pasted image 20250227110009.png]]

By-Default - There's a 60-second time limit from once the transaction performs the first write transaction .
![[Pasted image 20250227110651.png]]

Here #WithTransaction Method is used to start a transaction , execute the callback and commit .

### MongoDb Aggregation Pipeline in Java Application 

An Aggregation Pipeline is composed of different stages and expression operators .
It is a #Query_Pipeline .
The stages contains -
- Finding
- Sorting 
- Grouping
- Projecting

This helps in 
- Debug and maintain individual stages 
- Rewrite and optimize the query 

A stage can use expression such as
- System Variables 
- Field Paths
- Expression Operators 
- Literals 
- Expression Objects 


Expression Operators are similar to the functions as they take the field value as arguments to compute new field values .
![[Pasted image 20250227113324.png]]


![[Pasted image 20250227113339.png]]


Once this pipeline is assembled it can be tested through mongo shell to see before it can be implemented in our language .


#match Stage - Passes only those docs to the next stage that matches the conditions , 
and the 
#group stage  separates them into groups according to the  group key .
The output of this stage  is one document for each unique group key .

Here , we use the keyword , `Aggregates.match()` function .
It is the part of ***Aggregate Builder class .***

The #Aggregate_Method returns an Aggregate Iterable document .
![[Pasted image 20250227114713.png]]


The group Stage uses operators known as accumulators . These includes operators sum and avg . and etc .

![[Pasted image 20250227201351.png]]



![[Pasted image 20250227201807.png]]


If we have to compute some field value before we have to store it , we will use the method 
`projections.computed()` ,  Which means these are derived fields and not the stored fields .
Now when we create the Aggregation pipeline using method `collection_name.aggregate()` , we have to be careful regarding to the seq. of stages names that we put in , as that seq. will be considered .
![[Pasted image 20250227202340.png]]




