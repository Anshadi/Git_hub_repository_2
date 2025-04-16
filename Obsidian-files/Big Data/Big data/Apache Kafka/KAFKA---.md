##### APACHE KAFKA--    (OFFSET EXPLORER 2 see to download or not)
[https://youtu.be/c7LPlWvxZcQ](Full Crash Course)

Some basic Codes
![[Pasted image 20240508230104.png]]

![[Pasted image 20240508230206.png]]

[https://youtu.be/ei6fK9StzMM]( )

[[Pasted image 20240504153549.png]]
Apache kafka implementation in live user update 

If not using kafka , using only database in this case , it can cause large number of database hits , read and write operations and can lead to less Throughput and ultimately to database crash .
![Pasted image 20240504143346.png]]

For this problem , we can use apache kafka .
[[Pasted image 20240504144021.png]]
So here for efficient query , we can use database but for communication part we will use kafka due its high throughput .

So what it will do is the location will get published to it from time to time and the user  will be subscribed to that topic of kafka and it will get location updates to it and once the work is done that is the delivery is reached ,  the kafka will perform bulk batch operation to update the data  in database .
[[Pasted image 20240504144756.png]]

**Provides -**
High Throughput
Fault Tolerance (Replication)
Durable
Scalable


[[Pasted image 20240504150139.png]]

##### Kafka Architecture
[[Pasted image 20240504150303.png]]
Kafka Ecosystem consists of Kafka cluster and Zookeeper .The Kafka cluster consists of brokers which is our main kafka Server  which consists of various topics and within each topic , it consists of various partitions to categorize data .

#Zookeeper is a software which helps apache kafka in state management and broker management and in communication .

#Kafka_Cluster is a software which manages servers of apache servers called Brokers  .
As it is a distributed system , there can be multiple servers and multiple systems .

In broker , there can be multiple topics  to store,  categorize and organize data 
(As in database we use multiple tables to store various types of data like user table and client table). 
For ex- if messages are published continually to it , for ex - one is log based and another is user based so we can create different topics according to it and will store messages according to those topics .

Each Topic consists of various Partitions to store data  which is stored in it with the help of offsets .


We will open terminal where we have bin file of our kafka .

then will type following command to start zookeeper --
D:\kafka_2.13-3.7.0>bin\windows\zookeeper-server-start.bat config\zookeeper.properties

and following to start Kafka Server  -- 
D:\kafka_2.13-3.7.0>bin\windows\kafka-server-start.bat config\server.properties

Now to use topic , we use tool kafka-topic and for producer and consumer we have tool like kafka-console-consumer and kafka-console-producer.
[[Pasted image 20240505112939.png]]

Here we are using .bat in place of .sh as we are in windows

To see what we can  do and how we can do 
D:\kafka_2.13-3.7.0>bin\windows\kafka-topics.bat

also for help we can do
bin\windows\kafka-acls.bat --help

Now to create new topic
D:\kafka_2.13-3.7.0>bin\windows\kafka-topics.bat --create --topic user-topic --bootstrap-server localhost:9092

Here 1st we have to specify 
--create to tell it we are going to create a topic 
then --topic then topic name 
then --bootstrap server localhost: server number 

Writing Events (i.e. writing messages to the topic) --
bin\windows\kafka-console-producer.bat --bootstrap-server localhost:9092 --topic user-topic
![[Pasted image 20240505144325.png]]

Reading events through the consumer--

D:\kafka_2.13-3.7.0>bin\windows\kafka-console-consumer.bat --topic user-topic --from-beginning --bootstrap-server localhost:9092

![[Pasted image 20240505145103.png]]
This will first ask the topic and
then will ask from where i have to read i.e from beginning 

We can simultaneously send message from producer end and can  see it on the consumer end 
Using the same command on the other cmd we can make another consumer which will recieve the same message .


These are the some things we need to write to application properties
![[Pasted image 20240505182112.png]]

telling what we are using in our kafka Template as we are using the key and value as string so we have to specify that . 
And have to tell our kafka server address.

  

#Serialization is the process of converting data structures or objects into a format that can be transmitted over a network or stored persistently.

#Deserialization is the process of converting data structures or objects from a serialized format back into a usable form.

#Loggers are used to record information about the execution of a program, typically for debugging, monitoring, or auditing purposes.

#--from-beginning  ![[Pasted image 20240507121537.png]]

so if we start consumer not from begginning it will only read when the post request will be send otherwise it will contiously read message .


#KafkaRetentionPolicy
- Kafka retains messages in a topic based on its retention policy, which can be time-based or size-based.
- When the retention policy is reached (based on time or size), Kafka starts deleting old messages from the topic.
- If the Kafka console consumer reaches the end of the topic and there are no new messages, Kafka may delete old messages according to the retention policy.
- When old messages are deleted, the offset for the earliest available message in the topic advances, causing the consumer to start consuming from the beginning of the topic again.


#list_and_describe
To list all the topics of a Kafka Cluster do:
bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list

to get to know how many replicas has made and partitions use
bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic location-update-topic

#To_Send_Bulk_Messages--
![[Pasted image 20240508223221.png]]
to send the bulk message that is stored in a file use :
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic my-topic < C:\path\to\messages.txt

#Broker_List--
The `--broker-list` option is necessary to write when using Kafka producer and consumer tools like `kafka-console-producer` and `kafka-console-consumer`.

However, if we are using the Kafka client libraries within a custom Java application, you typically don't need to manually specify the broker list.

Instead, we would configure the Kafka client properties programmatically or through a configuration file, and the client library would handle connecting to the Kafka cluster based on those properties.

- A Kafka broker is a single instance of Kafka running on a server.


Ex-- ![[Pasted image 20240508224313.png]]
OR
![[Pasted image 20240508224443.png]]

#Kafka_template
If we want to talk to kafka Server then we have to use some class given by springboot i.e
Kafka_template.
Private KafkaTemplate<String,Object>Template 

To Send Message to kafka Server use -
Template.send();

![[Pasted image 20240509140333.png]]
Here Completable future class is used to handle async operations . Here particularly to handle the API operations i.e producing and consuming message async .

#_get
Future.get() used to wait for the above variable but it will slow down the kafka . 

So will use 
#-When_Complete
The `whenComplete()` function is a method provided by the `CompletableFuture` class in Java. It allows you to register a callback that will be executed when the `CompletableFuture` completes, either successfully or exceptionally.
Gives back result or exception .

![[Pasted image 20240509141834.png]]

## .getRecordMetadata()-
It is used for various purpose 
.offset() -- returns the offset it is reading .
.partition - returns the partition it is being stored
and other methods can be used to get the properties set about the application

## .getMessage() -- 
returns the message stored in the object.

#KafkaMessagePublisher-
>`KafkaMessagePublisher` is typically a component or class used to publish messages to Apache Kafka topics. In Kafka, publishers are also known as producers. They are responsible for sending data (messages) to Kafka topics.

### publisher.sendMessageToTopic(topic, message); ---
Used to send message to the specified topic from the publisher 

@PathVariable -- 
The `@PathVariable` annotation is used to extract a variable value from the request URL path and bind it to the method parameter `message`.
for example - if we have above written above the Requestmapping or GetMapping(\path) 
![[Pasted image 20240509145206.png]]

In kafka even though we have a single consumer , we still have to assign a group-id to it .

#Lag_No.
It is the amount of message produced by producer but not consumed by consumer that is , it is pending .
So in the case the message is being sent but the process somehow abruptly ends , then it will go to the lags no. 

Here In the consumer , the Data type(i.e String) of message will be the type it has been set in the producer while using template.send method.
![[Pasted image 20240509180843.png]]

Example of Multiple consumer is --
![[Pasted image 20240509181349.png]]


**Consumer Instances**:
- A consumer instance is an individual process or application that subscribes to Kafka topics and reads messages from them.
 - Each consumer instance within a Kafka consumer group reads a subset of the messages from the partitions it is assigned to
  - If one consumer instance fails or becomes unreachable, Kafka rebalances the partitions among the remaining instances in the consumer group, ensuring uninterrupted message consumption.
  - Kafka automatically assigns partitions to consumer instances within a group.


#### Question -(Consumer Instance Concept)
1---Does the consumer instances store their own seperate message or do they store repeated messages too .
![[Pasted image 20240509185056.png]]

##### 2- Does each consumer instance is related to only a single partition or not in same consumer group ?

	-However, It's important to note that each partition within a topic is assigned to only one consumer instance within the consumer group at any given time.

That if message gets two partitions then any two consumer instance will be able to recieve it .



##### 3- What is SendResult<> Data Type in kafka 

`SendResult` is a data type representing the result of a message send operation. It is returned by the Kafka Producer API when a message is successfully sent to a Kafka topic. The `SendResult` object contains information about the message, including its **metadata** and any associated **exceptions** if the send operation fails.

Ex---
```
import org.apache.kafka.clients.producer.*;

public class KafkaProducerExample {

    public static void main(String[] args) {
        // Configure Kafka producer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create Kafka producer instance
        Producer<String, String> producer = new KafkaProducer<>(props);

        // Create a Kafka record with key and value
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key", "Hello, Kafka!");

        // Send the record asynchronously and handle the result
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("Message sent successfully! Metadata: " + metadata);
                } else {
                    System.err.println("Failed to send message: " + exception.getMessage());
                }
            }
        });

        // Close the Kafka producer
        producer.close();
    }
}

```

When `send()` method is called on the Kafka producer, it returns a `Future<SendResult>` object representing the asynchronous send operation. The `SendResult` object contains metadata about the sent message,

The `Callback` interface is used to handle send result asynchronously, allowing the application to react to success or failure of the send operation.


#### #ProducerFactory- 
A producer factory class, if implemented by developers, would typically encapsulate the logic for creating and configuring Kafka producer instances. It might offer methods to create different types of producers with specific configurations, such as setting different serializers, specifying Kafka broker endpoints, or configuring authentication mechanisms.



#### #ERRORS
 1. This Error comes when we try to send our data as an object and use string serializer and deserializer .![[Pasted image 20240510212653.png]]Generally when we are sending message to the topic , it accepts byteArray as the input .
	We serialize the data when sending it from producer to broker and deserializing it when sending it to consumer.
	if we send string it can easily convert it into byte array but while working with object we have to specify that we are sending object so to convert it use object serializer that is *JSONSerializer* .
	Here we can send the value using string serializer but the value should be jsonSerializer .
	![[Pasted image 20240510213606.png]]
	And while resuming that is in application prop. of consumer
	![[Pasted image 20240510213700.png]]

2. NOT TRUSTED PACKAGE --(Error Coming in Consumer Package)![[Pasted image 20240510213903.png]]
	Its Solution --![[Pasted image 20240510214216.png]]
	Or Rather than defining there we can define it in the producer config file like this --
	![[Pasted image 20240510214652.png]]
	So here we created a function returning map in which we created a map object and put all our defined key and values inside it . 
	then we created a Producer Factory Based on that .
	then we send it to the kafka Template to use . In same way can write in consumer also .
	![[Pasted image 20240510215023.png]]
	Just write in case of consumer -- ![[Pasted image 20240511000242.png]]
4. 


#### Partition Control --
.send is a Overloaded function which meant that it has many constructors.
In Producer ,
We can specify the partition no. in the send method to which we have to send messaage only .
![[Pasted image 20240511110258.png]]

We can also specify the consumer to read from which partition by
Inside kafka Listener we can use @Topicpartition to specify the topic and the partition from which we want to listen .
![[Pasted image 20240511111055.png]]


Location-update-topic-1-1 means the consumer and producer is sending and listening to partition 1 of location-update-topic-1 topic


#### Testing with Containers --

@Slf4j is added for the log statement .

Dependencies to be added in pom.xml in both producer and consumer -
![[Screenshot 2024-05-11 134750.png]]
![[Pasted image 20240511134833.png]]

Common for both --

Used here SpringBootTest here to specify on which port we have to run this , to which have given random port.
1. Used @TestConatainers to specify that the below class has containers inside it .
	The `@TestContainer` annotation in Kafka integration testing is used to leverage Test containers for managing Kafka and ZooKeeper instances in Docker containers. This approach provides benefits such as isolation, consistency, ease of setup and teardown, portability, and repeatability, making it easier to write and maintain reliable integration tests for Kafka-dependent applications.
![[Pasted image 20240511113538.png]]

Now we have to  make a kafka container which will parse the Docker Image of the  kafka version we want . we will use @Container here .
Now we need to configure the bootstrap server .
  
In Apache Kafka, the #Dynamic_Property_Registry and #Dynamic_Property_Source annotations are used to provide a mechanism for dynamically configuring Kafka applications without the need to restart them. They allow the kafka properties to be modified dynamically .
![[Pasted image 20240511131854.png]]
.add method will take two parameters -
-key : which will be the the bootstrap server .
-value : which is the bootstrap server number which we will ask from the kafka container as it will change everytime .
**made the function to dynamically add the server number .**

Now we already have a application prop. folder in our producer where bootstrap server is mentioned but here dynamically allocating so make another directory resources in our test folder where specify same things except server numbers .

#ON_CMD we can write 'docker image ' and 'docker ps' to see whether docker image is there or not and whether the container is running or not .

##### Test case for producer --


A #KafkaMessagePublisher- , also known as a Kafka producer, is a component or application responsible for publishing messages to Kafka topics.

Here we created a class for the testing purpose of our producer where we specified data which want to send to check whether it works properly or not .Now we used await()
 for our thread for some min and max time . 
 Have to import this test from Jupiter .
![[Pasted image 20240511133743.png]]


##### Test case for Consumer -- 

to check whether the consumer is accepting the messages correctly or not .

As there was message publisher , here will be the Kafka Template  . 
here first log message will be published then , new customer will be build on the data given .
which will be then send to the  specified topic using .send method . now we already a method which is listening to this event , who will consume it .
which then will execute and then last log message will be printed .
![[Pasted image 20240511160242.png]]

Now it may take some time to make connection or to connect to the container . so we may keep some time buffer to sleep . That's why used await method .
![[Pasted image 20240511161208.png]]


#### Kafka Error Handling --
In Kafka , we can setup #kafka_Retry which will retry the the failed process based on our configuration , the no.  of times we have specified .(it will run n-1 times) And if retry count exceeded the messages will redirect it to #Dead_Letter_Topic -- It is a failure event to store all the failed topic or messages , if the consumer not able to process the message even after retry to ensure no data is lost . We can monitor and investigate them further . 
![[Pasted image 20240512111528.png]]



#### Extra --

IP Validation  ---
topic name is defined in app.properties file .
We are writting it in the consumer so still the postman when send this request will show message published succesfully but in consumer logs will show invalid IP address .
![[Pasted image 20240512111820.png]]now this try block will be usually connected to our DB but in case our db is down message will be lost so here we can use Kafka retry .

This can be done by writing it before Kafka listener
@Retriabletopic(attempts = by default 3) -  it asks for the no. of attempts .  -- it internally creates n-1 topics to do the attempts .

and for dead letter topic write this after the function that listens to that Kafka topic ends.
@DltHandler - the parameter in this function same as the listening function parameters .
![[Pasted image 20240512113430.png]]
It creates 1 dlt topic .
we can also specify time interval between  tries  in Retriable topic.
![[Pasted image 20240512115815.png]]

Now we can also tell the retriable topic for what kind of exception we don't  want to retry .
using exclude .
![[Pasted image 20240512120057.png]]



#### Schema Changes -- 
##### Avro Schema And Schema Registry --- 

Now if we have a schema defined for the producer and if in that we remove , change or remove some field , our consumer will not be able to work with that .
So we can either make a new producer and consumer or we can use Avro schema and to store it we have schema registry  .

Avro schema is the contract between our consumer and producer that we going to serialize and deserialize .
Equvivalent avro schema of a record ![[Pasted image 20240512142526.png]]
we can here tell which fields are optional or mandatory .
Then Schema -> Avro Schema -> Avro object using ->Avro Tool Maven Plugin
Then use this .java file to send to producer and consumer using Kafka Avro  Serializer and Deserializer  .
These convert the avro encoded message in the required object form .
For validation purpose and the object conversion , we use the Schema Registry .

##### Schema Registry - 
![[Pasted image 20240512153255.png]]
It store our schema so when avro serializer serializes the message , it first validates and stores the schema in the registry and when deserializer deserialize the message , it takes the schema from the registry and then validates with the messages and deserializes the message in the object.


- Needs 3 dependencies - web , Kafka , Lombok 
- will use here docker compose file and in that define all the container we need  in place of starting zookeeper and Kafka and schema registry .
- will need control center to see over all the dependencies .
- to start docker 1sr run docker , then type below command in terminal .![[Pasted image 20240512145622.png]]

we will create a folder schema directory in resources file where we define the schema and the file in it will be saved by the name employee.avsc ( avsc extension of avro schema) .
![[Pasted image 20240512150400.png]]
Here ,
	- namespace - the package where we want to store the generated class 
	-  type  - record 
	- name - the name we want to give our class
	then all the fields 
	the one we want to be optional -
		"default" : ""
	now in pom.xml add some dependencies and and plugin to define where my schema is
	![[Pasted image 20240512150949.png]]
	![[Pasted image 20240512150919.png]]


Now define producer , consumer and schema registry configuration .

#_@value - it takes the value of any object and gives it to the variable defined below
![[Pasted image 20240512151554.png]]in app.yaml file .
![[Pasted image 20240512151534.png]]
In kafka producer send a random key too --
![[Pasted image 20240512153032.png]]
![[Pasted image 20240512151905.png]]
key and value to be serialized and deserialized .

Now controller ![[Pasted image 20240512152040.png]]

to run on different port do in app.yaml file
server :
	port :9909
defining consumer and producer in same file 

![[Pasted image 20240512152421.png]] if used local server could used localhost:9092 but using docker so our ip_address:9092
Now in same file 
	consumer : also add the group-id in this![[Pasted image 20240512152727.png]]
	the common in both can be written under kafka 

now if we send the message from postman , the schema will be made in the confluent but if we change the schema model from our file and then again send , the schema will be updated in confluent but it will store the previous one too.
if during schema change , we are adding any new field we will add it as 
default:"  "  . 