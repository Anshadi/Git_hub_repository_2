As of till now , when we have to call any microservices , we have to call that on its port , and each microservice has its own port , so if we have  50 microservice we have to call each on its port , but api gateway provides a gate to it , so now we have to call the microservices through its  port no.  i.e. the host port and have to use only its port . 

The api gateway will redirect us , so all the configurations should be in it in order for it to known which services are registered and when call comes which service to redirect .

So now there is no dependency , even if the service port no. is changed .
**This Api gateway is itself a microservice .**
![[Pasted image 20240916051720.png]]

We will use spring cloud gateway , it requires Netty runtime and Web Flux and it doesn't work with war files atleat according to its documentations [old ones]. 
We can't apply methods like spring security to it . as it is made upon web flux ,  project reactor and spring boot .
![[Pasted image 20240916052751.png]]
We are using these dependencies and we are using eureka client so that later on we can add it to the service registry .


Now we will use Enable_Eureka_Client annotation and then will configure these for service registry.
![[Pasted image 20240916054423.png]]
As one configuration is repeating many times , we can make a config server for that . 

If we read its doc. we can find out its components like Route , Predicates , Filter . Filter gives us the option to modify request or response before or after sending the downstream request .

How it works![[Pasted image 20240916054817.png]]

There is two way to configure these . 1st - shortcut way and another 2nd - Fully Expanded Argument Way .  
![[Pasted image 20240916055043.png]]

We mainly have just to pass 3 things - id , uri , predicates .
In Id -  Service Name 
In uri - we will mention the url , we don't have to mention the uri , here lb stands for load Balance .
In Predicate - We will mention the path which  it will use when we call that service .
![[Pasted image 20240916055502.png]]

Now for a service , if there are more than one path ,, then through api gateway , we can't specify that . so we can use this ![[Pasted image 20240916113307.png]] or if we can do `Paths = /** ` -- which is not a good practice .

