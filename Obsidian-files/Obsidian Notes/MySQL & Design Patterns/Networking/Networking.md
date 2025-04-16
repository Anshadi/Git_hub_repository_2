
Started as a response to Russia's sputnik satellite by america through AARPA Net. By using TCP .


#### Network Protocols --
 These are set of values and regulations setup to communicate and share . Ex-  HTTP , UDP ,TCP ,SMTP  etc. . For communication between different type of files and others .


#### Packets -- 
In order to share data , we cant send big chunk of data over the  network . So we divide the data into smaller chunks , these smaller chunks are called as packets . As now if some data is lost , we can retrieve it easily as they are small .

#### Address -
Sending message over the network req. the destination details . This detail uniquely identifies the end system is called as address .

#### Ports --
Any machine could be running many network apps . In order to distinguish the apps for receiving messages , we use ports .

Ports helps us to get the process to the specific process on the host .

```
IP ADDRESS + PORTS  == SOCKETS
```

Ports are basically 16 bits number i.e. we can have total ports are `2^16`  i.e.  around `65000` .

---------------------------------------------------------------------


WWW - world wide web - It allows us all the access to the documents and contains link .
It is a information system where documents and other web resources are identified by Uniform Resource Locators , which may be interlinked by hyperlink and  are accessible over the internet  . The resources on it are published by web servers .


When we search google.com , our computer act as a server and google as a client , which then sends web pages and other things . If we are working on localhost , we will act as a server .


#### TCP - 
It is a protocol which ensures that the data will reach to the destination completely without loss  

#### UDP -
It is a protocol where it is not nescessary that the data will reach the  destination compeletly without loss . Ex- Video Conferencing where frame rate drops and other things .

#### HTTP - 
It defines the format of the data which is transferred between client and servers and how the data will be sent .


Our Computer and servers are identified by Ip Address . Just like name and phone numbers .
Its format is -- ` X.X.X.X`  and every single X can have value between ` 0-255 ` . 

To find own computer Ip Address , we can use the command 
` curl ifconfig.me -- ` 

***How All Of It Actually Works -***
There is our ISP - Internet Service Provider - Which gives us a Router or Modem - which has a Global ( Public ) Ip Address .
All devices around the world will have same Ip Address . The router will also assigns Ip addresses to these devices using #DHCP_Protocol known as Local Ip Address . 


Every device **connected to the internet directly** (or every router/modem from every home) gets a **different global IP address** from its ISP.



The Isp is connected to larger Isp's which provide service to them . They are called tier-1 .
Ex- In India - tier-1 - tata and tier-2 airtel or else . There is large cable say of tata from Chennai to Singapore .

DHCP Protocol - Dynamic Host Configuration Protocol 
![[Pasted image 20241007204836.png]]
If we made a req. to google.com , the google will see the global Ip Address that is provided by our Router . And when the req. comes back , the router will decide on basis of Ip Address which device made the request using #NAT and gives the response to it . 

#NAT - Network Access Translator 

Inside the device , to decide which application made the request of data is decided by the help of ports .
For Ex - If i am talking to another person using chat app , we both will have an Ip Address and a Port Number . The Ip address will determine where our computers are located and port number will be deciding which application we are using . 


We know that web pages uses HTTP . So there should be a port defined for that . And all the operation that is done related to it happens on ***port 80*** . 




| From Ports   | Type of Ports                             | Work                         |
| ------------ | ----------------------------------------- | ---------------------------- |
| 0 -1023      | Reserved Ports                            | For Protocol Functionalities |
| 1024 - 49512 | Registered For Some Specific Applications | Ex- Mongo Db - 27017         |
|              |                                           |                              |


***What does it means*** -
1mbps - means that we can transfer 10^6 bits per second .
 when we send the data - upload 
 when we receive the data - download 


The Internet works through the underground cables in the ocean between countries .

They are connected through 
Physically --  Optical Fibers Cables , Coaxial Cables
Wireless: Bluetooth , Wifi , 3G ,4G , LTE , 5G 


Lan - Local Area Network - Network In an Area . ex- Wifi , Ethernet .
Man - Metropolitican Area Network -Across a city
Wan - Wide Area Network  - Across the Country ex- Optical Fibre Cables
	-  SONET - Synchronous Optical Network - It carries the data using optical fibres and hence can transfer the data over large distance .
	- Frame Relay - It is the way to connect our local Area Network to a wider Network that is Internet .

Internet is the collection all of the above three . (LAN , WAN ,MAN)

Modem - It is used to convert digital Signals to Analog Signal and vice-versa . Ex- So through a modem we can send the data by conv. the digital data into electrical signal where the another modem will receive it and conv. again to digital data .

Router - A router is a device which routes the data packets based on their Ip Addresses .


#### Topologies -
- Bus Topology  -
- Every System in bus  topology is connected like a backbone . If the link gets broken , entire network will be disturbed and only one person can  send a data at a time .
- ![[Pasted image 20241009155917.png]]

- Ring Topology - every system communicates with  one another . So if A has to communicate with D , has to go through B,C .![[Pasted image 20241009160056.png]]

Limitations - If any cable breaks wont be able to make communication and there is a lot of unnecessary calls being made .

- Star Topology - There is one central device that is connected to every device . a centralized device . Every device can communicate through this device .
![[Pasted image 20241013104026.png]]
If this device fails then whole communication gets stopped .
- Tree Topology - This is a combination of bus and star topology . As in bus every single one is  connected , here every single one can have star topology .
![[Pasted image 20241013104343.png]]
Has more fault tolerance .
- Mesh Topology - Every Single computer is connected to every single computer .
![[Pasted image 20241013104602.png]]
Expensive , Scalability issues .


#### Structure Of the Network --


So if we req. something lets say a video from youtube , then the youtube will find all the data and transport it from its server , then it will reach the server in our country and we will get that from there .


The Layer from which we are interacting that is req. and receiving is known as ***Application Layer .***  Ex - Its a application  like WhatsApp .

Now How This Transportation Happens - #OSI_Model .

***OSI Model - Open System Interconnection Model -***
It was developed as a standard way as how 2 or more computers communicate .

There are 7 Layers in OSI model -
- Application Layer 
- Presentation Layer
- Session Layer 
- Transport Layer
- Network Layer
- Data Link 
- Physical Layer 


Every Layer has different protocols and devices .

#### Application Layer -
It is implemented in the  Software . It contains the application .Where the user Interacts .

We send our data from application layer to presentation layer .
Protocols - HTTP , FTP , Telnet etc.  .

#### Presentation Layer -
It will accept the data which would be in form of like charachters and numbers and etc. and will convert it into the machine representation format that is the binary format .
From ASCII to EBCDIC[Extended Binary Coded Decimal Interchange Code] known as translation .

It is responsible for 
- Translation
- Encryption - changing the data so only readable to the person send to
- Compression - To reduce Traffic , can be with loss or lossless 
- Other Forms of data formatting between applications

Protocols - Used SSL for encryption and decryption .

Then it is send to Session Layer .

#### Session Layer -
It helps in setting up and managing the connections and it enables the sending and receiving of data followed by the termination of the terminated session . 

So It just manages a session .

So Before a session , it asks  for Authentication and Authorization .

Now the data is transferred to the transport layer .


#### Transport Layer -

It helps in transporting of the data .

It does it in 3 ways -
- Segmentation -Here data is divided into small units called segments . Every segment will contain the source and destination port number along with sequence number .
- Flow Control -  It Controls the  amount of data that is being transferred  .
	- Ex- If the client is sending data at 40 MBPS and the server is accepting the data at 20 MBPS then it will make the client data transfer rate slow down .
	-  It also works on Error Control . i.e. if the  data packets got lost or corrupted then how to work with it .
	- It also adds Checksums to every data segment . To ensure the data Integrity .
	


Sequence Number - It helps to reorganize the segments into the correct order .


Protocols - UDP  , TCP etc.  .

So there is ***Connection Oriented Transmission like TCP and  Connectionless Oriented Transmission like UDP*** . 

Udp is faster because it doesn't provide the feedback that some data packets gets lost .
Ex - Video Conferencing Or Gaming.

However in tcp , whenever the receiver receives the data , it sends a acknowledgement back . Ex - Email or Ftp .

So now the data is transferred to the network layer .

***Till now all was happening within same network .***


#### Network Layer -

It works for the transmission of the received data segment from one computer to another ***that is located on a different network*** . 

It is where the Router Comes into the picture .

It does 
- Logical Addressing - i.e. Ip addressing - It assigns the sender and receivers Ip address to every segment and forms a #Ip_packet. So that the every data packet can reach the correct destination .
- It also performs Routing - i.e. moving one data packet from source to destination . 

It also determines the best path to take when transferring the data from one computer to another .
Load Balancing also happens here . To make sure it is not overloading .

Lets say we have a computer A is connected to our Wifi , we are sending a data through a Facebook page and there is a receiver computer B . 
![[Pasted image 20241013171418.png]]Now every data packet that will be sent to the Facebook will contain 3 things :
- Receivers Ip Address 
- Senders Ip Address\
- Subnet Mask 
These 3 things are called Packets .


Now the data is transported to the data link layer .


#### Data Link Layer -
It allows to directly communicate with the computers and host .

It receives the data packet from the network containing Ip of both the sender and receiver .

Now here Physical Addressing happens - i.e. Mac Addresses .
So here the mac addresses of the sender and receiver are assigned to the data packets here to form a frame . 
Frame is a unit of data link layer .

#Mac_Address -  It is 12 digit alphanumeric number of the network Interface of our computer . Our computer have more than one mac addresses . 
Ex- Our Computer may have some other mac address and our wifi may have some other mac address .

So This layer performs 2 functions -
- Allows Upper layers to access frames and other things 
- It also controls how the data is placed and received from the medias using things known as #Media_Access_Control .

It is the technique used to get network access to physical medium and permission to transmit data .
It does 
- Frame Delimiting 
- Addressing
- Error Detection 
- Collision Handling in shared Networks 


Means this layer pushes the data into frames which can transport then .

Then passed to Physical Layer .


#### Physical Layer -
It contains hardware , it is hardware section . Here the mediums are wires .
It transmits the bits through Electrical Signals or Radio Signal.
It doesn't have packets or frames like things .

At the receivers end when the data is received , the physical layer converts the data into bits and pass it to the  data link layer as a frame and then the frame will move to higher and higher layers .

Ex- So if i am sending a message to my friend then the message will pass from my Application Layer --- to ---> My Physical Router  --- which sends it to ----> My friend's Physical Router which will be passed up to its Application Layer .

![[Pasted image 20241013202815.png]]

******

#### TCP/IP Model --
There is another Model Known as #TCP/IP_Model -- 

It is known as Internet Protocol Suite .
It is similar to OSI Model but it has only 5 Layers .

They are :
- Application Layer
- Transport Layer
- Network Layer
- Data Link Layer
- Physical Layer 

It is the Implemented Model and the OSI Model is the Conceptual Model .
Here the rest of the layers have been Combined .


#### Application Layer -

- User Interacts with this . Ex - WhatsApp , Browsers .
- It lies on our devices .
- How data is transferred depends upon the protocols that is being followed .


##### How Applications talk to each other -

There are Many Ways --

1) Client - Server - Architecture  -

- Client sends the request and the server sends the response .
- Server is the system that controls the website that we host .
- These are known as processes and they communicate to each other .\
- If we wanna host our server then our server should have some reliable Ip Address which can be reached by the clients . We should also have high Availability .
- The collection of the servers in a big company is known as #Data_Centres . As if youtube  had only a single server , then it won' t be able to meet the demand of all of its client .
- So when we talk about data Centres , it means a large collection of computers having static Ip Addresses that is Ip Address that do not change  , having High Upload speed 
- So when we #ping a server ,
	- It measures the round trip time  for the messages send from the originating host to the destination of the computer and that are echoed back . This Entire time is known as ping .

2) Peer to Peer - P2P - Architecture --
 -  In this , application running on various devices gets connected to each other , there is no concept of a large data center or one server or anything else .
 - Ex- How computers connected in our college to one another .
 - There is no one dedicated server . 
 - Advantages - Can scale it very rapidly , is a bit like decentralized network .
 - Here any computer can work as a client or as a server .
 - Ex- Bit Torrent 

We can also have the hybrid of the above two .


***Now the Networking Device we use are --***

1) Repeater - 
    - It is at the Physical Layer . 
    - its job is to regenerate the signal over the same network before the signal becomes too weak or corrupted . So it can be transmitted over the same network .
	 - It Doesn't amplify the signal , when the signal becomes weak , it copies the signal bit by bit and then  regenerate it at the original strength .
	- It is a 2 Port Device .
2)  Hub -  
	 -  It is a multi-port repeater instead of just 2 port . It connects multiple wires coming from the different branches .
	 
	 - Ex- Connector in star topology which connects different stations .
	 
	 - It can't filter the data , so the data packets are sent to all the connected devices .
	 
	 - Here the #collision_domain of all the hosts connected through the hubs remains same , that is all the devices are connected through the same connection channel , So if two or more devices sends the data at the same time , both data will interfere with each other and devices will need to send the data again .
	 
	 - 2 Types - Active Hub and  Passive Hub	 

![[Pasted image 20241015133938.png]]


3)   Bridge - 
	-  It is a type of repeater .
	- It is at the  Data Link Layer .
	- It has the Additional Functionality of filtering out the content by reading the mac addresses of the sources and destination .
	- Also used for connecting the 2 LANs working on the same protocol.

![[Pasted image 20241015134027.png]]

4) Switch - It is a multi-port bridge that can boost the performance and the efficiency .
	 - It is at the data link layer .
	 - It can perform error checking before forwarding the data . Makes it efficient .
5) Gateway - 
	- It is a passage to connect 2 networks together that may work upon different networking models .
![[Pasted image 20241015134523.png]]

6) Brouter - Bridging Router - It Adds the functionality of both .

![[Pasted image 20241015134742.png]]




##### Protocols -\
 ###### Web Protocols -
-  TCP/IP  --
		-  HTTP  --  It defines how data is transferred . Like html pages and others .
		- DHCP --  Dynamic Host Control Protocol - 
			- It allocates IP Addresses to the devices that are connected to our network.
		 - FTP -- How files are transferred but now they are transferred  over http .
		 - SMTP --  How Emails are sent 
		 - POP3 & IMAC --  To Receive the Emails 
		 - SSH -- Secured Shell - 
			 - Use it for Ex- when we want to log to  a  terminal of someone else's computer
		- VNC -- Virtual Network Computer 
			-  For Graphical Control 
- Telnet -- Terminal Emulation 
	- Allows a User to connect to a remote host or a device using a telnet client .
	- Default Port 23 .
	- It allows to manage a device or a account remotely .
	- It is a Low - Level Protocol 
	- In Http and Https , we get data that is encoded , i.e. utf-8 etc. However in telnet  , it is not encoded or encrypted .
- UDP -- User Datagram Protocol 
	- It is a connection less session , data may be lost during the lifetime of the connection .


Now how applications talk to each other  : -
	If we have a program : WhatsApp  - then it will have a lot of processes in it . like  sending a message or record a video .

A program is an application .  Process is the running instances of the Program . A Program can have many processes .
A Thread - It is a Lighter version of a process . Each Running Processes can have Multiple Threads . 
The difference between the thread and a process is a thread only does a single job .
Multiple threads works simultaneously .

Sockets -  
- Its a software process .
- It is the interface between the process  and the internet  .

Ports - 
	- It tells us which application we are working with .
	- So if many processes are running of an application , then the data which is incoming to identify that is to which instance of that application it has to go can be done by #Ephermal_Ports 
	- What it will do is that the application will internally assigns itself random ports numbers  if the multiple application instances are running . Once the process is done the ports will be freed .
	- Now these #Ephermal_Ports , can exist on the client side however on the server side , we have to know about the port number 

##### HTTP - 
- In client - server architecture , the req. and response is done using http . 
- It is a client - server protocol which tells us how we req. data from the server and tells us how the server will send back the data as response .
- These are Application Layer Protocols . And all the application layer protocol also require some Transport Layer Protocol .(TCP/IP  , UDP)
- Http uses TCP/IP Protocol Inside it as it makes sure that all the data packets are received 
- Http is a stateless protocol . So server won't store any info about the client by default .
- It is a #Connection-Oriented  i.e. the data is transferred once the connection is made .

- Now on the WWW , everything from the text to video to documents , everything have their own url .

HTTP Methods -
- Get  --   Requesting some data
- Post  -- Sending Some data
- Put  -- Puts data at a specific location
- Delete --  To delete data from the server 

One of the things is 
- when we see the connection - Keep Alive ( There are many like closed etc.  ), in the headers section of the request is it .  It means here that it allows the user to determine the timeout period or also allows the user to specify how many request be made before closing the connection .
- Status Codes --
	- 1XX - Informational Related Codes
	- 2XX - Success Codes
	- 3XX - Redirecting Purpose Codes 
	- 4XX - Client Error Codes 
	- 5XX - Server Error Codes 

So Now if the http is Stateless then how does the website remembers our movements and some cases doesn't even asks for the passwords , it is done with the help of 
***Cookies .***

###### Cookies -
- It is a unique String .
- It is stored in the client's browser  .
- When we visit a site for the first time , the cookies are set . Using tag #set-Cookie
- After that , each time whenever the request is made , the cookies are sent in the request made . 
- So then the server will find the state of that user from that cookies from its database .
- These Cookies also have a expiration date .
- Third Party Cookies -
	- These are the cookies that are set for the Urls that we don't visit .


***How Email Works -***
- Application Level Protocol - SMTP - Simple Mail Transfer Protocol - used to send emails
						- POP3 - Used to receive Emails
- Transport Layer Protocols Used - TCP - as want the entire data 

- The Email clients like gmail.com have their own servers .
- If i am sending the email from the yahoo.com to the gmail.com then 
- When email send from the sender it will go to the sender's SMTP server and 
- Then will make a connection with the receivers SMTP server . After the connection is established , the data is transferred .
- And then when the we login to the email client , it downloads the emails from the server  and it reaches to the sender .
- However if both the server and receiver are using the same email client then the connection thing doesn't happens . Ex - both using Gmail . It directly transfers it .
- There is also a error handling that is if the receiving server is offline then the sending server retries for some days .
- We can use #Name_Server_Lookup command to find the name and Ip Addresses of the SMTP Servers for various domains .
- `nslookup -type=mx gmail.com` , here mx means mails exchange which means smtp servers .

 - POP3 - Post Office Protocol -- To get the emails
- How it works is -
	- First the client connects to the  pop servers , does all the authorization and authentication .
	- Then the client asks the servers for all the emails .Then the session will be closed and the server will update on our command .(like if want to delete the email)
	- However other folders like draft and sent emails are not sync when we use this protocol .
	- If we download the email and delete the email from the server , the emails will be only  on the current client and not any new device we use .

- IMAP - Internet Message Access Protocol - It also used to get emails .
	- It allows to view our emails on multiple devices .
	- Emails are not deleted from servers unless manually done . Local emails present on the devices unless sync .( like draft )



***Where the server and Ip list is stored***
- DNS - Domain Name System  - 
	- Domain Names are mapped to Ip Addresses . 
	- These are stored in a database or something .
	- We use a service to lookup in these databases . 
	- The Most famous one is DNS .
	- Ex- So when we enter google.com , it uses dns to find the Ip Address of google's Server.
	- So when we type a site name using domain name , the http takes it and finds and converts the url to Ip Address using dns . 
	- So Dns is directly a database service .
	- But we have too many domain names and what if the database service goes down and that's why this database is divided into various classes .

	- So when we search 
		- mail.google.com - here mail is sub-Domain i.e. it is a part of the bigger domain .
		- Here google is Second-Level Domain .
		- com -  Top-Level Domain 
		- So Instead of storing everything in one database there are multiple database for these 3 categories .
		
	- The Top-Level Domains are known as #Root_DNS_Servers .
	- These are the first point of contact . 
	- They have their Top Level Domain - 
		- .io , .org , .com  
	- The TLDs are Organization type specific . That is 
		- .com -- commercial Organization
		- .edu -- educational Institution
		- .uk , .eu  --  Country Specific 
	- The database for these TLDs are maintained by #Icann 
		- ***Internet_Corporation_For_Assigned_Names_And_Numbers***
		
	- Now Ex - student.io , google.com , here the student and google are #Second_Level_Domain .
	- Through Root-Servers.org , we can see who are maintaining the root servers .
	
	- So when we type Google.com , 
		- Then first it will check for its own computer , because when we visit a website for the first time , it will store the value of its Ip Address in the cache in our system .\
		- If we are not able to find it , then it will look for it in the Local DNS Server . 
			- It is our first point of contact . Ex - Our ISP - Internet Service Provider .
		- If we are not able to find it there , then it will check in the root server . that whether for Ex- .com is present in our root server or not .
		- If it is not present in the root server then , The ISP will check it in the TLDs .
		- And the TLDs have all the domains , so it will return the Ip Address .
		- Then the Isp will connect to the server of google.com 
		- We can't buy a domain name , we can only rent it .

	- We can see the messages received by the dns using `-dig command `
		- Ex- `-dig google.com`		
		- In windows - `nslookup google.com`
		- Here the Ip Address given is what stored in our Local Cache .
		- It performs dns lookup and displays the answers that are returned from the queried name servers .
		- ![[Pasted image 20241017031602.png]]


#### Transport Layer -

Suppose if we are sending a message using a messaging application , then the transfer of message from one computer to another is handled by the Network Layer .
But Within that computer , the transportation of the data from the network to the applications is done by Transport Layer .
i.e. it decides within that computer , to which application , we send the message like WhatsApp , Facebook is controlled by it . 

The Transport Layer is Located Within Our devices .

The Protocols It uses are - TCP , UDP .

Suppose if we are doing 3 things at the same time i.e. if we are sending a message , a file and video calling at the same time .
Now the way our computer will know how to send these 3 types of data to Network layer and the way through which the receiver's Transport Layer will know how to handle these 3 different types of data is through #MultiPlexing and #DeMultiplexing .

- Multiplexing -
	- It Refers to the ability of a system to send multiple types of data (from different applications or sources) over the same communication channel (one medium) at the same time. 
	- In networking, this is often achieved by using **ports** to differentiate between different streams of data (e.g., web traffic, email, file transfer).
	- Each application's data is assigned a port number, and the transport layer (like TCP or UDP) uses this information to direct the data to the correct destination.

- Demultiplexer - 
	- It is the reverse process, where the receiving end takes incoming data from a single channel and delivers it to the appropriate application based on the port number or other identifying information. 
	- The transport layer (like TCP or UDP) looks at the port number in the incoming data packets and ensures they are directed to the correct process or application.


Sockets are like Connection between two applications like a gateway . Between the application and the Network .
![[Pasted image 20241017035455.png]]
So if a message application wants to send something to another message application , it will give it to the socket .
These Sockets have Ports Numbers  .

We know that the data travels in packets . The transport Layer will attach these sockets with port numbers to them . 
So it will know from where this data is coming from and to which application it has to be send to .


Transport Layer Also takes cares of Congestion Control . i.e. traffic control .
Congestion happens in both transport layer and network Layer .

Here the Congestion Control Algorithms are built in the TCP Protocols .


#Checksums - When we are sending some data to another person , using this data , we calculate a particular string value or something that will be called checksum . When another person receives this data, the checksum will be associated with it that we calculated at the senders side. 
Then the receivers side too will calculate the checksum using the received data , if the value is different , then something is wrong , may be the data is corrupted or some data packets are lost .

#ReTransmission_Timers - It is used for the senders to know that the data packets sent to the receiver are received .
Ex- So If  I am sending data to my friend , then we know that the data are sent in the form of data packets .
So when i am sending the 1st data packet , we will start the timer , and when the receiver will receive the data packet and will send the acknowledgement , we will end this timer .
So if we send a data packet and started the timer , but never got the acknowledgement , then the timer will expire and We will know that the data packets are never sent .

However there could be a case where the receiver received the data packets however the acknowledgement signal didn't reached the sender , then it will consider the transmission as failed and again will sent that packet .
However now the receiver will have duplicate packets . But it won't be able to know .

So we will solve this problem using #Sequence_numbers . So a valid Unique Identification will be provided to every packet . 
So if there is another data packet with the same sequence number , then that data packet must be duplicate .

Transport Layer Protocols Used -- 
- UDP - 
	- Used same as the tcp to transmit data from the network layer to transport layer and vice-versa .
	- However here the data may or may not be delivered ,  data may  be changed or the sequence may be changed .
	- It is a connectionless protocol . Means No connection will be established between the computers and data will be still sent . 
	- **No connection establishment**: The sender does not need to perform any handshake or connection setup before sending data. It just sends the data.
	- UDP uses checksums . We will know that the data is corrupted or not , but  UDP will do nothing about it .


- UDP Packets -
	- Every Data packet will have a port number attached to it by the transport layer . - 2 Bytes
	- Every packet will have the source and destination port number . - 2 Bytes
	- Length Of Datagram i.e. the length of the packet is also added to it . -2Bytes
	- Checksum will be added to it . -2Bytes
	- The Above Parts of the data packets are known as Header . Total - 8 Bytes
	
	- Data will also be added to it .  
	- The size of the data will be `2^16 - 8` Bytes = 65 536 bytes .
	- The total size of the data packets is 2^16
![[Pasted image 20241017181022.png]]
- DNS Use Cases -
	- UDP is faster .
	- Used in Video Conferencing 
	- DNS uses UDP 
	- Gaming also uses UDP 


- TCP - Transmission Control Protocol
	- It also send the data from the transport layer to the network layer and vice - versa . However in a different way 
	- When we get data from application layer (i.e.  a lot of raw data )from transport layer .
	- TCP segments this data . i.e. divide in chunks , add headers . 
	- It may also collect the data from network layer . As suppose we have data and transport layer passes the data to network layer .
	- Network Layer may divide the data from more smaller chunks . So At the receiving end these more smaller chunks may be put into one .
	- It also provides Congestion Control .
	- It takes care of 2 things -
		- When data doesn't arrive 
		- It also maintains the sequence of data using #Sequence_numbers .
		
	- Email Layer Protocols like SMTP , IMAP , POP3  , these transport Layer Protocol uses TCP Transport Layer Protocol .

	- Features -
		- Connection Oriented - i.e. first the connection has to be established and then the data will be send .
		- It also provides Error Control .
		- Congestion Control 
		- Full Duplex - i.e. Bi-directional 
			- Both Computers can send data simultaneously and in both direction .
		- ***There could be 1 TCP Connections between only 2 computers i.e. 2 endpoints .***
		- If sending same things to 10 computers , then each of those will have a separate TCP connection to that sending computer .
		
	- As it is connection oriented , then a lot of things are required , some of them are 
		- Source Port , Destination Port , Data , #Sequence_Number , #Acknowledgement_Number  

It happens through #3_Way_Handshake  .

***How Connections Are Established -***
###### ***3 Way Handshake -***
- Here the client sends the connection request to the server by sending the #Synchronization_Flag and Sequence Number ( Random ).
- It means a new connection is being Established .
- Then the server also sends back the Acknowledgement flag with a Acknowledgement Number and with a sequence number  that is generated by taking the client sequence number and doing mathematical operations to it .
- Now the client on receiving will also send back a acknowledgement flag and the sequence number and the acknowledgement number  . 
- Here the Ack. and Seq. Number sent will be equal to one more than that number sent by it to the server .
- Then the connection will be Established .

![[Pasted image 20241017183006.png]]
Sequence Numbers - These are random numbers , because if it would not be random number then it would be easy to guess . For Security Purposes .



#### Network Layer -

We know that the data is transferred from the application Layer to the Transport Layer .


| Layer            | Data travels in form of |
| ---------------- | ----------------------- |
| Transport  Layer | Segments                |
| Network Layer    | Packets                 |
| Data Link Layer  | Frames                  |

In this layer , we work with Routers . Here the data travels from one computer to another .

Suppose Two computers A and B are connected to each other , then they will be connected through Routers and there can be a numbers of Routes in between them .
Every Single Router Here has a #Network_Address as Every device has their own Ip Address.

Network Address - It allows us to uniquely identify a network or a subnetwork in the larger architecture of the internet or within private networks

Suppose , if we sent the  data from the computer A to Network N1 router then it will check in its #Routing_Table or #Forwarding_Table which is a part of the Routing Table . Which Consists of every destination Address  .

A Packet Contains 
	- Network Layer Address of the destination ,
	- Network Layer Address of the sender 
	- Information that we want to send 

On basis of its Forwarding Table , The Network N1 will the send the data packet to the next Router .
This is Known as #hop_By_hop_forwarding .  i.e. Hopping Routers to Routers until it reaches the correct router . 
These Routing Tables are inside our routers .
Routing Table may have many paths but the forwarding table will have only one path , i.e. the faster path .
These are Data Structures .

![[Pasted image 20241017205955.png]]So on basis of this Forwarding Table , the data packets will reach the destination .


The Network Address Tells us on which Network our device resides on . 


###### Control Plane --
In network layer , there is #Control_Plane .
It is used to build the Routing Tables .  There is a Link between every router .
It is like graph , where Routers are nodes and Links are edges .
![[Pasted image 20241017210531.png]]

There are 2 types of Routing --
- Static Routing - 
	- It is adding Address Manually . 
	- Time Consuming 
- Dynamic Routing -	
	- This Evolves , there is a change in the Network , it will evolve Accordingly .
	- Algorithm Used here are - Bellman Ford , Dijkstra's . 

Ip Address Defines a server , a node , a client or a router uniquely.

Network Layer Protocols Used Here -  IP 
- Internet Protocol (IP) - 
	- IPV4 -
		- These are 32 Bits Numbers with 4-Words . Ex- `5.8.6.7 ` Each no. Represented  in 8 bits .
	- IPV6 -
		- These are 128 bits Numbers .

- The Routing Table doesn't have every single router and every single addresses in the world but it has Blocks of Ip Addresses and not Individual Ip Address .
- 
- These Block of Ip Addresses are provided to the Internet Service Providers . These are known as Subnetting .

- A subnet is a range of IP addresses that can be assigned to devices in a network.

- **Subnetting** is the process of dividing a large network into smaller, manageable subnetworks, which helps in efficient routing.

- As Shown above , the network address as ` 192.168.2` . So all the devices Ip Address in the same network starts from this number .
	
- Here the network Address is the #Subnet_Id and the device Address is known as #Host_Id .

So what happens when Hopping is done is whenever the router is transferring the data like this , it must know the subnet of the destination . 

So the Internet Society Created classes for this . 

Classes Of Ip Addresses 


| Class Of Ip Addresses | Range from | Range to        | Default Subnet Mask  |
| --------------------- | ---------- | --------------- | -------------------- |
| A                     | 0.0.0.0    | 127.255.255.255 | 255.0.0.0            |
| B                     | 128.0.0.0  | 191.255.255.255 | 255.255.0.0          |
| C                     | 192.0.0.0  | 223.255.255.255 | 255.255.255.0        |
| D                     | 224.0.0.0  | 239.255.255.255 | Not Used For unicast |
| E                     | 240.0.0.0  | 255.255.255.255 | Reserved             |

Subnet Masking - It means masking the network part of the Ip Address . And will leave to us to use the Host Id .

For Ex - The subnet mask of class B is 255.255.0.0 means in the place of 0 , I can add any number . 
It means that first 16 bits are reserved for the Network Portion . But the other 16 bits are for host Id .


***Variable Length Subnets --*** We can set our own length of the subnet network .
Ex - `12.0.0.0 /31` it means that first 31 bits are for the network part .
Ex- `192.0.1.0/24` means first 24 bits reserved then we can have `192.0.1.1 to 192.0.1.255` but the starting 24 bits are reserved .


However the Internet Society (IETF) assigns the Ip Address based on the region and not the classes to minimize the numbers of hops .


***Reserved Addresses -***
127.0.0.0 /8 -- Local Host 
These are known as the loopback Addresses . Because the processes that are running on our computer , they will allow to connect to the same processes .

It can not be down , it will always be up as long as our computer is up . And we can have as many Loop Back Addresses  as we want .


***IPV6*** --
- It is 4 times larger than IPV4 .
- 2^128  bits
- However as this is a new technology .  Due to this it is not Not Backward Compatible .
- It means that the devices that are already configured over IPV4  can not access the website and server with IPV6 .
- Would Require a Lot of effort - ISPs have to shift , Lot of hardware work have to be done .

- Here we have 8 numbers , each representing a hexadecimal number representing 16 bit number .
- Ex-   `a.a.a.a.a.a.a.a ` Each a is a 16 bit number . Ex- `ABFE:F001:3210:9182:0:0:1:3`
- Here also like in IPV4 , we can fix the number of Ip Address reserved for the Network Address .  Ex- `ABFE:F001:3210:9182:0:0:1:3 /60 ` ,means first 60 bits are fixed .
- So if all the values are 0 , then Either we can write `:0000: or :0: or just ::` .How many 0s it having it will decide upon how many number in front or back . 


#### Packets --

Apart from the data , the header is of 20 bytes . 
Some of the things It contains -
- Ip Version 
- Total Length
- Identification Numbers
- Protocols , Flags
- checksums
- Addresses 
- TTL - Time To Live - To ensure that the data packets are not roaming within the network forever so if after that much hops its not reaching the destination , it will  be dropped .

- So when we hop , it reduces the value of the TTL .


#### Middleboxes --
These  are extra devices which also interacts with Ip Packets .
They can be in Network Layer but can also be present in the Transport Layer .
These are used to apply filters on the packets that are coming .

Some of it types are -
- Firewalls -
	- There are two types of firewall - One that is connected to Global Network and another that is connected to our own network .
	- It Filters out the Ip Packets based on various Rules 
		- Based On Address - It blocks or allows some Ip packets
		- We can Modify the Packets i.e. we can change the destination or the header etc. .
		- We can set some rules on basis of port numbers .
		- We can set some flags . Like we can block all the syn flags so that we can't create new connections .
		- On Basis of Protocols .


	- There are 2 types of protocols -
	- Stateless Protocol and Stateful Protocol 
	- Stateless Protocol will not maintain the state whereas
	- Stateful Protocol will see the packet and maintain its state i.e. it stores it in its cache memory . So on basis of that it can be like that we have similar packet , we can allow this packet or reject this packet .
	- As it is using cache memory , so it is a bit more efficient .
	
	- There are two types of it -
		-  One that is hosted by the hosts and the system , i.e. in the Transport Layer
		- One that is in the network Layer 

#### Network Layer Translator (NAT) -

It is Method of mapping an Ip Address space into another by modifying network address information in the Ip Header  of packets  while they are in transit across a traffic routing device .

![[Pasted image 20241018101126.png]]

So it changed the Host Id for the Internet . So all the Ips in our network can be worked as a private Ip . 
This was done to slow down the consumptions of Ip Address .
So whenever we are getting the packet or something , NAT is going to save this in its memory and then it is going to pass it to the internet .

There are many types of NAT .

- It is used in  Routing , Load Balancing .
- Two way Communication 


The source address of the packet we are having that is replaced  by the public Address  and then check sum is also updated  then it is carried it out via tcp or udp .

When we receive the packet ,then we get it from the public Internet then NAT checks it in its table and is forwarded to our own Internal Network .



#### Data Link Layer --

So the data packets we receive from the Network Layer , the Data Link Layer is responsible to send these packets over a  Physical Link .

It transports our data to the connected devices .


So as we have a router and it has Ip Address provided by the ISP and some devices are connected to the router . These devices have their own Ip Addresses . 
So there is going to be a Subnet , to which new Ip Addresses are going to be allocated .

##### DHCP - Dynamic Host Configuration Protocol -
- So whenever a new device is added to our router or other things , The new device is going to be connected to the #DHCP_SERVER .
- Dhcp Server  will have some pool of Ip Addresses and it is going to assign Some Ip Address to our new device .

- There could be a case of many devices connected to the LAN . They may have same subnet or area Ip But at the data link layer they communicate to each other using #Data_Link_Layer_Address.
- So all the devices having the Ip Address will also have the Data Link Layer Address .
- So the data packets may know there Data Link Layer Address as well as the destination DLL Address .
- We can manually assign data Link Layer Address to the devices . But sometimes they are also assigned automatically .





Suppose here device 1 want to send something to device 4 then , it will check in its own cache  that whether  it contains the DLL Address of the device 4 .
If it does not have it then it is going to ask to the all the devices connected in the data Link Layer .
It is known as #ARP_Cache .
So all the  devices connected in this will get a message from this device . The message will contain a frame that contains the DDL Address of the sender  and Ip Address of the Destination .

Here using the Ip Address of the destination , it will get the DDL Address of the destination .
This is Known as #Address_Resolution_Protocol .

The **Address Resolution Protocol (ARP)** is a crucial protocol used in the Data Link Layer to map an IP address (Network Layer) to a physical address (MAC address, which operates in the Data Link Layer).

ARP is used to find the **MAC (DLL) address** associated with a given **IP address** on a local network. Since devices communicate over the Data Link Layer using MAC addresses, ARP helps translate an IP address into the corresponding MAC address, enabling communication between devices on the same network.



***The Data Link Layer of the devices are known as the mac Addresses of the devices .***
The Mac Address is not of our device but of  our  components that is our Wifi will have a different mac address and our Bluetooth may have different mac address . 


So when we make request and the data comes back to our Router , it will attach the correct private Ip Address to the packet ensuring that they go to the correct device . Because all the private Ip address corresponds to the  correct mac Address .

So the Ip Address will assign the correct Ip And Mac Address to it .

So the Public and Private Ip Address will remain the same but the Mac Address will keep on changing .as data will travel from one device to another .

MAC  -  Media Access Control  
- It is a 12 digit Alpha Numeric String  .
- It is a unique Identity for a Particular Network .

It is used by all IEEE Networks like  Ethernet , Wifi , Bluetooth  .
So we can block some devices too from accessing using their mac addresses .

So the data link layer is the one that  works  with the  mac address and it works at our router level and with the physical devices . So it converts the data into 0s and 1s and transfer it into  the physical devices .


