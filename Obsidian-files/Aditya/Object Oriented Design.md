Entity -object
Fields of entity -Attribute
SDLC  - Software Development Life Cycle
Functionalities --defining functions
Relationship -- Which function calls which function .How functions are related to each other. 

Entities are interdependent to each other. but some are independent.

Different Paradigms
![[Screenshot 2023-12-14 180653.png]]

In procedural oriented data and behaviour are seperate that is
there is struct and there are diff. functions 

Example--- of Procedural Thinking
![[Screenshot 2023-12-14 184045.png]]

But 
In OOP -- the data includes its behaviour .
We can seperately access the behaviour of the data.

Example of -- object oriented code
![[Screenshot 2023-12-14 185531 1.png]]

Class acts as a blueprint.
Class has two components 
Attribute--Data associated with that entity
Methods--Behaviour that entity can perform.

Objects are instances/example of class.
![[Screenshot 2023-12-21 183349.png]]
here f is formatted string .if there is no f string it would not replace the bracket with its value.
here r is the raw string.

when we add the keyword __init__ in a method it becomes a constructor.

In java
![[Screenshot 2023-12-21 184457.png]]
![[Screenshot 2023-12-21 184558.png]]

In java
In case of public we can access  
object.behaviour
ex--raj.age
i.e we can access the detail of object

But in case of private we can't access the details of object i.e its behaviour .
we can only apply thee method in defined in it and get its output.
If we have to access the private var we can use getter and setter method.

instance variable are var inside  the brackets in method of a class.
instance method if its using instance variable.

class methods are those method which uses var defined in class i.e globally.

In python before using class method we have to write @classmethod --it is called as decorater .

In java 
![[Screenshot 2023-12-21 191557.png]]
![[Screenshot 2023-12-21 191512.png]]

when we don't have constructor in python in a class it automatically creates a constructor and it calls the parent constructor but we have to call the parent class once.

but if we have to access the parent class method we don't need to call the parent class.

when child class inherit parent class both constructor needs to be called in order of child then parent.

![[Screenshot 2023-12-28 180451.png]]![[Screenshot 2023-12-28 181108.png]]![[Screenshot 2023-12-28 181517.png]]![[Screenshot 2023-12-28 181958.png]]![[Screenshot 2023-12-28 184620.png]]![[Screenshot 2023-12-28 185100.png]]

In case of single child multiple parent if we call super.init then it will call only the first class . So we can do class1.init() ,class2.init().

It is called multilevel inheritance
when init self is called first in a constructor then first the child is called then its parent is called when super.init then its grandparent called.

![[Screenshot 2024-02-08 183838.png]]
![[Pasted image 20240208184605.png]]

![[Screenshot 2024-02-08 190322.png]]

any class which has one extract method becomes extract class
![[Screenshot 2024-02-08 192109.png]]





![[Pasted image 20240627191029.png]]![[Pasted image 20240627192608.png]]![[Pasted image 20240711180835.png]]![[Pasted image 20240711181215.png]]

Layered arch. can have single point of failure but entire architecture can't be a single point of failure .

![[Pasted image 20240711183419.png]]
Client Server - It is synchronous
Event Driven - It is asynchronous communication .

sync - tightly coupled
async comm. - loosely coupled

Event Broker acts like a temporary store where all the events are stored .
Here the event bus or broker is the single point of failure . 
![[Pasted image 20240711184332.png]]

Could be difficult to make our whole system event driven but parts of it can .

![[Pasted image 20240711185558.png]]
Scaling - adding more resources 

Scaling are two types - horizontal and vertical .
vertical - same machine but more resources 
horizontal - more machines 

Redundancy can be good or bad depending as while storing data in database not good but in case of having of backup like it happens in kafka system or in Elk stack.

RAID - Backup becomes more resilient .


**System Designs Steps --**
![[Pasted image 20240711190856.png]]![[Pasted image 20240711192605.png]]

![[Pasted image 20241206174947.png]]


High level design  - blueprint 
Low level design - see on every components and how it works 

![[Pasted image 20241206175841.png]]

![[Pasted image 20241206180235.png]]


Which is better - microservices or monolithic - It depends . (maybe on the scale of the project .)
![[Pasted image 20241206181037.png]]

![[Pasted image 20241206181220.png]]
Here every box is a service and in LLD every box is a class .
![[Pasted image 20241206181431.png]]
![[Pasted image 20241206181554.png]]


SDLC - Software Development Design Cycle  - Going from the idea to the product from creation to deployment and to maintenance .

![[Pasted image 20241206182358.png]]


Architecture -
client asks and the server responses .

client is a process that makes request to another process or system, and the process the provides responses is the server
a process acts as a client that initiates the interaction, and vice versa, meaning a system is just hardware without any process running inside it

![[Pasted image 20241206183209.png]]

Most of the process is client server architecture .
P2P is not a client server architecture . Here there is no hierarchy as here both the peers are capable of doing things .
P2P - Torrents , 

Torrents - a protocol that lets transfer data between 2 computers without client and server architecture . Without a middlemen . 
ex- a file downloaded from 1 to another . If another comes then it can download from any one person or half from one and another half from another , making it very fast .
So No one person can affect the communication .

Bitcoin also uses P2P communication in the backend .

![[Pasted image 20241206184141.png]]
SPOF - Example - WhatsApp Server .
Another con - Network Latency and Bandwidth Limitations

![[Pasted image 20241206184637.png]]

![[Pasted image 20241206185219.png]]

Microservices are independent but it doesn't mean there are in total isolation that is they are low dependent that is loosely coupled , they do need to know the complete detail of the another service .

![[Pasted image 20241206190821.png]]


here it listens for some event and happens as a reaction to some thing .



Distributed system are the extension of the asyn event driven system .


**System Designs Topics --**
![[Pasted image 20250214185226.png]]
Requirements - Functionalities , constraints , and specification .

3 Types -
- functional - 
- Non-Functional - all the words that ends with ability - availability , scalability , testability , Secuirty
- Domain or system specific Requirments  (regulatory requirments and rules by the government or  other local bodies enforces us to do , we dont get any benifit from it .)

![[Pasted image 20241213184213.png]]

requirements drives the design (as also in sdlc)

![[Pasted image 20241213185048.png]]

While in design interview go for depth , choose 4 max points and then explain them in detail


Some function(core functionality , must have ) and some non -function above (that is good to have but is a extra functionality and not must have and ability and performance related)
At start some functionality can be non- functional and later on being must it becomes functional - like login in 500ms ![[Pasted image 20241213190143.png]]


Anything related to performance is non-functional


Availability - accessible anywhere and everywhere and anytime .
Like Copyright will be system requirement because it requires compilance with regulations . But it is also non-functional first and then functional in other normal cases .

Scaling Applications -
Like increasing no of servers in the most requested days - called scalling up and there is another called scaling down when there is not much request .
![[Pasted image 20241213191657.png]]

![[Pasted image 20241213192011.png]]

![[Pasted image 20241213192129.png]]



![[Pasted image 20241219180907.png]]
Combining of both is better than using a single one .

In vertical scaling , we cant go down once we go up but there is a advantage for horizontal scaling that we can scale down to easily .


Load Balancer -- 
![[Pasted image 20241219181541.png]]
It distributes the load between various servers present . It is done in horizontal Scaling .
Chances of failing of load balancers are very less as compared to the server .
![[Pasted image 20241219182210.png]]


However the time taken to schedule it and in routing , works as a disadvantage for it . 
Even if we did horizontal scaling , the load balancer can still act as a single point of failure .
As if load balancer failed, all the incoming requests will stop at that point, and wont get forwarded to servers .

Single point of failure of the load balancer is most unlikely to happen as compared to the replicas .
As the server has many functonalities running so there could be error in any function however in load balancer there is only one function running , so its single point of failure is better than the servers single point of failure .

  
Earlier the system would fail due to the capacity of the users, which could happen more often
and now it depends more on the routing which can be less critical and error prone .

We can never get rid of the single point of failure in our system , there will always be a single point of failures . For Ex-  DNS can also be a single point of failure .

So we can reason it like Replacing the load balancer can be more convenient as compared to the single server in case of failure. 

sir , there are circuit breakers which are used for fault tolerance , so the load balancer can act as a single point of failure in horizontal scaling so can we use circuit breaker here which will switch it to the another load balancer and if that circuit breaker fails , it wont be a single point of failure .
But it is also done with the help of dns server so if our dns is working fine then it can work .


![[Pasted image 20241219185433.png]]

Databases  -
We never connect to database , we connect to DBMS .

![[Pasted image 20241219190043.png]]

**NewSQL** refers to a category of modern relational database management systems (RDBMS) designed to provide the **scalability and performance** of NoSQL databases while maintaining the **strong consistency, ACID properties, and SQL support** of traditional relational databases.

every dbms contains db in it like oracle has db inside it and same with mysql . using oracle dbms , we can connect to mysql database . We always contact with dbms and never db .
![[Pasted image 20241219191418.png]]

so using oracle and mysql is  just preference based , its just that oracle premium database provides safety of data . 
![[Pasted image 20241219192307.png]]



NoSql Database are easily to scale horizontly .
![[Pasted image 20250228184041.png]]


Column- Family Database - Cassandra 
Commonly as a bridge between as sql and nosql 
![[Pasted image 20250228184224.png]]

Each Column becomes as key-value pair for us .
![[Pasted image 20250228184406.png]]


[[Pasted image 20250228184612.png]]

If we want really strong consistency then sql but if we want it consistency at the end of the day then it is Nosql 

There are set of conditions in the database and when all these data checks passes , then the database is consistency .
So Sql database is always consistent but NoSql is eventually consistent .

So to choose which one depends on 
- Data type stored
- Sql Uses strong hardware
- Sql always Consistent 

[[Pasted image 20250228191042.png]]


[[Pasted image 20250228191619.png]]


##### Cache Storing Strategy 
- Time-Based Expiration
- Invalidation-Based Expiration
[[Pasted image 20250228191944.png]]



[[Pasted image 20250306182731.png]]


Here two types of server is present -
- Edge Server
- Origin Server


[[Pasted image 20250306185433.png]]




***Use The Interviewer --***



[[Pasted image 20250306191324.png]]


[[Pasted image 20250306192304.png]]

Url shouldn't be guessable as then our Digital Footprint will become traceable .

uniqueness means that no two long urls have same shor url and guessable means that it is easy to predict the short url hence making them risky in concern of security

Final Design --
[[Pasted image 20250306191600.png]]



[[Pasted image 20250321181735.png]]
[[Pasted image 20250321183422.png]]

The redirection happens completely on the browser , so when we redirect we use the http status 302 and inside it there is key `location` where we write what our real location url is .

[[Pasted image 20250321184948.png]]

[[Pasted image 20250321185512.png]]

Moving to higher base so that we  can encode more info in less bits but we can't go unlimited base because to be of some base , we need that many unique alphabets .

Here base 62 would be better than base 64 as it has " / " which can also be part of the url too .
So base 62 has url safe charachters . 

Will use mongo as database  as mongo provides storage of time capped data - Expiry of link and high read requirements .

[[Pasted image 20250321191307.png]]

[[Pasted image 20250321191924.png]]

[[Pasted image 20250321192119.png]]

[[Pasted image 20250403180354.png]]

[[Pasted image 20250403180653.png]]

[[Pasted image 20250403181333.png]]

If we lose the shard , we lose the data . 
Sharding allows parallel ( distributed ) read and write operations . 
Adding New Shard is hard , as if we want more shards than mentions then req. re-sharding completly which is very expensive . 

Spof is not completly removed .

So Now we use Sharded Replica Sets . However , not preferred commonly due to cost and complexity .

Even if we go with it then shards and only one replica of that .

[[Pasted image 20250403182618.png]]

Here , we can add cache , on the database or on the each server, but the database one is commonly preferred one generally .

Here the problem is shortening and replication are strongly coupled .
[[Pasted image 20250403183956.png]]


Redirection and shortening as redirection is more important and have diff. requirements and separation of concern .

As complexity increases , the server increases .
[[Pasted image 20250403185333.png]]


1 Design per Month --

-----

------
Networking -- 

------

[[Pasted image 20250403190758.png]]

2201641530197


Multiple mac addresses - one for each interface, different ip for different networks -- 

Don't always have a single unique ip and mac address . 
One device can have n no. of mac and ip addresses .

ISP - Company or Organ. that connects us to the internet  . 

We can also change the mac address without changing the NIC Card . as well as the Ip Address .


Before Switch , we used Hub 
Whenever we talk about network Switch  actually creates the network .
Now here , we can't use router , As it doesn't create a network , it connects the network . 

It is one to one communication , it operates in the data link layer . So It only forwards frame .
It uses mac Address to identify devices to send .

![[Pasted image 20250411180301.png]]


Forwarding Table is a mapping between Interface (Which port our device is connected to ) and mac Address . 



So for A to send the message to B through switch , A must know B mac Address 
![[Pasted image 20250411180804.png]]


Devices mainly works on L3 level , they care about Ip Address but Switch works on Mac , so here we need translation .

So Now A knows B Ip Address , but it need its mac Address . So to get Ip from Mac , we use ARP (Address Resolution Protocol ) . 


Whenever we have L3 header , we need to have L2 header - 
In l2 , we don't know the sender mac , so we use Broadcast Mac - all once , denoted by all f  . 
Now as L3 involved so now it becomes packet .  ![[Pasted image 20250411181759.png]]


Now sent to all other devices it chks the dest. Ip , it responds with its mac address .
So now B can make a ARP Table to keep track of source Mac . 
 ![[Pasted image 20250411182126.png]]
Here B sends the Mac to A , and  now it is not broadcast , it is specific , So that A can also populate it ARP Table . 

It is called an ARP Table . 

Done only if sender don't have the dest. mac .


How Ip Address is assigned - Using DHCP Server . It assigns the Ip to device .
![[Pasted image 20250411184037.png]]


DHCP Server Stages   ------ 

So in offer , dhcp sends an ip and asks if it ok , if not the offer rejected and then dhcp sents another Ip . [[Pasted image 20250411184421.png]]

DHCP when we connects give not only Ip , or Router address but also the address of DNS server . 

Without DHCP , we can't get the Ip assigned in a network  .

We can bypass the DHCP and assign an ip for ourself or we can let dhcp assign it .


Switch is a wired network . our laptop or computer connect through internet using wifi using access points . For wireless .

It connects Wireless device into the wired network , it brings them to switch network . 
So it acts as intermediatory .

[[Pasted image 20250411185314.png]]

[Pasted image 20250411185705.png]]
However , not all router may have modem built in them .


Router Acts as a gateway to join networks .
![[Pasted image 20250411190234.png]]


This Router is connected to multiple switches at  a time .
Router has both **ARP Table as well as Routing Table .** 


[[Pasted image 20250411191505.png]] , here the output is the network address (that we get on doing AND ) and 15 is the host address . 

As it is our network address , then it will be same for all the devices connected to it and hence we will have only the host id part , we can have at most 255 device in case of 255.255.255.0   ... 
![[Pasted image 20250411191804.png]]


Like we can do our subnet mask as 255.255.255.1110 , where it will allow only one device to connect and all other devices will be dropped .



