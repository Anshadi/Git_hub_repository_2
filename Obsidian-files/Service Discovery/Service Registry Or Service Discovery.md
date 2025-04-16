
A service registry is a central repository where microservices register themselves and discover other services. It facilitates service-to-service communication by maintaining an updated list of available services and their instances. This is particularly useful in microservice architectures where services are dynamic and can scale up or down.


So it will tell what services are up and down .

#### **Key Responsibilities**

1. **Service Registration:** Microservices register themselves with the service registry upon startup. This includes information like service name, instance IP address, port, and health check endpoints.
    
2. **Service Discovery:** Services use the registry to discover and communicate with other services. Instead of hardcoding service addresses, services query the registry to find instances of other services.
    
3. **Health Checks:** The service registry often performs health checks on registered services to ensure that only healthy instances are available for communication.
    
4. **Load Balancing:** The registry provides a list of available service instances, which can be used for load balancing. This helps distribute the load among multiple instances of a service.
    
5. **Dynamic Scaling:** When services scale up or down, they update their status in the registry. This ensures that the registry always has an accurate view of available service instances.

Ex- Consul , Eureka , Zookeper

With this, we can use the services using its name  .
#### **Benefits of Using a Service Registry**

- **Simplifies Service Discovery:** Automatically locates services by their names, reducing the need for hardcoded service endpoints.
- **Supports Dynamic Scaling:** Handles changes in the number of service instances automatically.
- **Facilitates Load Balancing:** Helps distribute requests across multiple instances of a service.
- **Improves Fault Tolerance:** Provides mechanisms to detect and handle service failures



#### **Implementation of server in Service Registry -**

![[Pasted image 20240911221303.png]]
Here We will use annotation @EnableEurekaServer
![[Pasted image 20240911221515.png]]This Our whole project is a Service Registry or Discovery Server .
In this project , Our all other services will get registered  .


Now we will make application.yml under the folder application.properties .

we will give eureka hostname using eureka.instance.hostname: localhost 

Now we will use this property on pausing which it will prevent this current service from being registered , as it is already a server then why we will need to make it a client . also will ensure that it doesn't fetch the registry . that is it won't be able to fetch the registry from the eureka server .

![[Pasted image 20240911222412.png]]

By Default these properties are true . 

will change its port number using server.port = 9999 .

Now when we will implement client on all other services , it will automatically will get registered to our eureka server which we can access through localhost:9999 .



#### **Implementing Service Discovery Client -

Now to implement client in a Service -  we need dependencies 
![[Pasted image 20240911224042.png]]
Now we will select these two dependencies from the spring and put it in the pom.xml file of our service . i.e.   spring-cloud-starter  and  spring-cloud-starter-netflix-eureka  . 
![[Pasted image 20240911224116.png]]


We will need both above for it to work , but still it will give error as it will show 
``` cloud-starter dependency as unknown ```  so we will comment it or remove it from our service and will import this dependency management there .![[Pasted image 20240911224521.png]]

Here we will replace the spring cloud version as specified in the spring initializer file .However we will mention it inside the properties file and remove that line from dependency management .![[Pasted image 20240911224655.png]]


Now above our service we will use the annotation @EnableEurekaClient  .

Now have to configure somethings . like prefer ip-address to true so when it will guess the hostname , the ip-address will be preferred  .

Now by default fetch-registry and register-with- eureka is true but we can write it too .
Now we will mention the eureka server url with the help of Default Zone .

So it will use this url in order to register with the server . 

we will also put 
``` spring.application.name = name``` otherwise it will register the service with the name "unknown" .
![[Pasted image 20240913092526.png]]
Now we can use this name for using this service or for communication 

And our all configurations are done . 
Now we can see our service on our eureka server .  Now it has become a discovery client .





#### **Microservices Communication-
whenever a service calls another service , it does it with the help of http client , through which communication can be done .
we can use any type of client like rest template or feign client etc .


**Using Rest Template**

So for this we have to autowire rest template 
``` 
@Autowired 
private RestTemplate restTemplate
```
But for this , we have to have its bean .
 So for this inside our spring application file -![[Pasted image 20240912175552.png]]

Or we can put it in the config class -![[Pasted image 20240912180005.png]]
So the  rest template contains many method with it , one is 
``` 
ArrayList forObject = restTemplate.getforObject(url,ArrayLiat.class)
```

If we needed a single result , we could use `.getforEntity` , then we could access its body that through  `EntityName.getBody()`

Now this url will be of the other service method url which has to be called through this method . 
And we will put the class of that of which we will get the data in return .
![[Pasted image 20240912181428.png]]

![[Pasted image 20240912182202.png]]
But 
however it is not dynamic as we had hardcoded id , so now we are using that method ![[Pasted image 20240912181659.png]]

Now the endpoints won't change untill we change the service but we can change the host port . 
So to make it dynamic ,rather than using that service path and its port number , we can use the name given to that service by us .
So dependency on the port will be removed .


**Now lets say we have to call two microservices together , we can**
![[Pasted image 20240912183656.png]]
Here we are getting the ratings of the user through the user service and have to show with it the hotel name and its detail then we are getting hotel of each rating  through Hotel Service then using map function and converting it into list and then
setting that list of ratings to user through 
`user.SetRating(ratingList) .`


However , a problem will arise as when we access the data above , we got it in the array form  as wrote array.class , However it is putting the data inside it in linkedHastSet , so when we will use mapping on it to convert it into the `List<Rating>` , it will give error .

![[Pasted image 20240912185254.png]]

So we will do
![[Pasted image 20240912185609.png]]


##### **Dynamic Host And Port of Microservice -

Here  we just have to replace the localhost with the service name , 
![[Pasted image 20240912190850.png]]
then we have to use `@loadBalanced ` above our spring Application service .


Its advantage is that even if we change the port number of our service , it won't affect us as we are accessing its using it's name .






