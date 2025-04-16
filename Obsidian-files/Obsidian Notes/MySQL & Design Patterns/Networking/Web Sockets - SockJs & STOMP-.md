**STOMP -- (Simple Text Oriented Messaging Protocol)**
#### **1. Queues, Topics, and Endpoints**[](https://www.baeldung.com/spring-websockets-send-message-to-user#queues-topics-and-endpoints)

There are **three main ways to say where messages are sent and how they are subscribed to** using Spring WebSockets and STOMP:

1. **Topics** – common conversations or chat topics open to any client or user
2. **Queues** – reserved for specific users and their current sessions
3. **Endpoints** – generic endpoints

Now, let’s take a quick look at an example context path for each:

- “/topic/movies”
- “/user/queue/specific-user”
- “/secured/chat”

It’s important to note that **we must use queues to send messages to specific users, as topics and endpoints don’t support this functionality**.




In Config Folder - Creating a Web Socket Config class , which has two methods -
- One for determining the  path of the Websocket and Routes that are allowed to access it . - `Register Stomp Endpoints .`

- Another for Determining where the message will  be transferred when received .
	`configure Message Broker`

```
@Configuration

@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  

    @Override

    public void registerStompEndpoints(@SuppressWarnings("null") StompEndpointRegistry registry) {

        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();  
        
        // addEndpoint matlab kya path hoga and setAllowedOrigins matlab kon kon isse access kar sakta hai and i am using the browser version of the web socket , so sockjs .


    }

  

    @Override

    public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry registry) {      
    
    // it is used to configure the message broker as the web socket is a bi-dir communication protocol so we need to configure the message broker to handle the messages.

        registry.setApplicationDestinationPrefixes("/app");

        registry.enableSimpleBroker("/topic");

    }

}
```


  

So  Now If i am a user and i send a  message then it will be send to` /app/send-messaage` using `/app` route where only those our method annotated with message mapping will tell what to do and this route is fixed by using method `setAapplicationDestinationPrefixes` 
and 
what `enable simple broker` does is it will indicate to what route all the messages have to be broadcasted that is to which topic our users should be subscribed to . It does this broadcasting by using an in-memory broker .

  
  

When we define a method annotated with @MessageMapping, it automatically considers the prefix set by `setApplicationDestinationPrefixes()`. So even though there we have only written `sendMessage` , it will consider it as `app/sendMessage` .

  
  
In Frontend , we will mention app/sendMessage , when we will connect .

  
  The register Stomps Endpoints method is used to enable the STOMP support .  And they are also enables sockjs fallback option .

  
There could be a case in which the web socket is not available , we can use other non-websockets that is https methods .Because as web socket which are connected through the upgrade request to decide on a protocol to use , which can be restricted by proxies or firewalls , 
Or
In some case where the web socket connection is seen as idle , and is terminated  by the proxies as when they are implemented on a idle time expiry basis or allowing only a set number of connections open . 
-- Called the Sockjs Fallback .


  Generally our spring uses the Jackson library to convert the objects to json and vice versa .


#### Frontend - web socket  and Stomp - html and js

```
function connect() {
    var socket = new SockJS('/chat'); // Create a SockJS connection to the server
    
    stompClient = Stomp.over(socket);  // Wrap the socket in a STOMP client



    stompClient.connect({}, function(frame) {
        setConnected(true); // Set connection state
        console.log('Connected: ' + frame);



        stompClient.subscribe('/topic/messages', function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body)); // Handle incoming messages
            
        });
    });
}

```

- As we can see that the stomp works over the web socket , 
- Because Web socket is there to open a bi-dir full duplex communication where as the http methods like to send and receive messages are handled by the stomp .

- Now the things to remember is to create a SockJs instance connected to the path on which communication is going to happen . .
- Here that path will be send that is written in our Register Stomp Endpoint . Ex -  \ws in our case .

- Then wrap it with stomp .
- then use .connect() method .
- then subscribe the topic on which  communication is going to happen that is on which the user will send the messages .
- The path used to subscribe by the stomp client should be the same as used as annotation `@Send To` above the service method .


```
function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect(); // Disconnect the STOMP client
    }
    setConnected(false); // Update the connection state
    console.log("Disconnected");
}

```
Now to disconnect , if the stomp client is not null which means there is a user then use `.disconnect()` method to disconnect .



```
function sendMessage() {



    var from = document.getElementById('from').value; // Get the nickname
    var text = document.getElementById('text').value; // Get the message text


    stompClient.send("/app/chat", {}, 
      JSON.stringify({'from':from, 'text':text})); // Send the message to the server
      
}

```

- Here the path used is same as what we wrote in the set Application Destination Prefixs + the path where we wrote the message mapping annotation .


Then to display the message , we can write something like this -
```
function showMessageOutput(messageOutput) {

    var response = document.getElementById('response');
    var p = document.createElement('p');
    
    p.style.wordWrap = 'break-word'; 
    // Prevent text overflow
    
    
    
    p.appendChild(document.createTextNode(messageOutput.from + ": " 
      + messageOutput.text + " (" + messageOutput.time + ")"));
    response.appendChild(p); 


    // Append the message to the response area


}

```



#### General Web Socket Terms Vs Specific - i.e. - @stomp/stompjs 

Send --> Publish 
Connect --> Activate 
Disconnect  --> Deactivate 



Now we can also create a private chat also in the group chat but  for that we just have to add another method in the `Configure Message Broker` i.e. ` setUserDestinationPrefix` whose path will be used for a private chat .  
And 
In `Register Stomp Endpoints` , we have to add its endpoint too .  


##### ***Using Spring's Messaging Framework ---***
In the spring inbuilt messaging framework , for sending to the group chat , we use its Message Templates `convert and send ` Method , where as for sending to user for private conversation , we use `convert and send to user` method .As only this method is there for handling the private messages .
```
messagingTemplate.convertAndSendToUser(
    recipient, 
    "/queue/messages", 
    privateMessage
);

```
However in the Kafka , we use `KafkaTemplate.send` method to send the message for the group chat as well as private chat. Just have to subscribe the user to different topic .
So if have to use this for private , we can use the recipient id as the topic .



***But it is better to use the web socket and stomp and spring messaging for private and kafka for group chat as kafka used for high volume and has a bit of latency .Where as web sockets used for low latency , reduced bandwidth header overhead because now everytime like http we dont have to send the header .*** 


- **Latency for Kafka**: Kafka is generally low-latency, but compared to WebSockets, it could introduce more delay, especially when a large volume of messages is processed.

- **Real-time nature of WebSocket**: You can emphasize the efficiency of WebSocket in real-time applications a bit more. WebSocket connections stay open, eliminating the need for constant reconnection, unlike HTTP.



##### What the `@SendTo` Annotation Does:

- The `@SendTo("/topic/messages")` annotation is used to specify where the return value of the method should be sent. In this case, the returned `message` will be broadcast to all WebSocket clients that are subscribed to the `/topic/messages` topic.
- Spring uses the return value of the `sendMessage()` method to populate the message that gets sent to the `/topic/messages` destination.












