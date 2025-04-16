
When we use a laptop , we don't use the complete resources , so for it , there was the development for hyper Visor .
It uses the concept of virtualization . That is used to create VM servers on top of our physical servers , it is the logical separation , where we have OS for each of this Vms  on which we can run our application . 
So instead of running one application on one physical server , we can run multiple application using multiple vms and each having there own OS .

So this complete thing is known as Virtual Isolation .

So then what was the need for  the containers --

Suppose , we bought physical servers of 100Gb and then we installed hypervisor on it and divided the memory on it into 4 logical separations , VM1 , VM2 , VM3 , VM4 . each 25Gb space .

The same thing we do with EC2 instances , what AWS does is 
- it buys physical server from one of these vendors or they manufacture there own physical servers and on top of that physical servers , what they do is
- They install #Xen_Hypervisors .And using this Xen Hypervisors , they ask us to create the EC2 Instance .

![[Pasted image 20250304053102.png]]


But now we saw that the VM are not using the resources to their fullest capacity .
For Ex - 
VM1 ---> App1 ---> On best Day ---> using 10 Gb Ram and 6 Cpu --> So wasting most of the resources . So we can reduce the memory assignment to it But 
And when it is running on  low capacity , it is using much less than that . So then we are wasting more resources , it was the major drawback of the VMs .

Its same with the EC2 Instances .
So now containers will solve this problem . it will effectively use Vms to reduce this problem .



***So Problem of Physical Servers ----> Reduced Using Vms --> Problems of Vms ---> Reduced Using Containers (To Some Extent ).***

Today VMs are more secured than containers . As Vms have a full OS that means complete Isolation whereas the containers do not have a complete OS and they do have logical Isolation but this logical Isolation is not complete .
As one container interacts with another container or they interact with host OS to share some resources .


#### Architecture of Containers --


Containers can be created in two ways , we can use Containers over Vms or we can use containers over Physical Servers .


So the first way is the devops Engineer will use the IBM OR HP Servers  and 
- He will install a OS and over it will install a  #Containerization_Platform that is docker .
- As for running Vms , we need #Virtualization_Platform , like Hyper-V , for running containers we need docker .

Other way ,we can create VM or Ec2 over Physical Server . It could be AWS or cloud-provided Physical server or any other .
- And on top of this we create the docker .

Most companies uses this model because In maintenance of physical server there is lot of overhead . So in this case , we would need System Administrators for its maintenance .

The Main difference between them , is that the containers are very lightweight in nature . As they do not have a complete OS . They use the resources from the Base OS . 

The Containers is not like don't have OS , it is that they have minimal OS or a Base Image 

***So a container is nothing but a package or a bundle which is a combination of***
- Our Application 
- Application Libraries 
- System Dependencies req. by our application (ex- like python )

Any other shared libraries will be used from the Host OS , hence containers are very lightweight in nature .
![[Pasted image 20250304062120.png]]

To take a backup or Image  of our Vms , we will take a snapshot ,which minimum could be of 1Gb or 2Gb .
however with containers , it could be in MBs . There is a significant drop in the size .
So now they are easy to ship , that is they are easy to move . 


***Docker Lifecycle -***

Docker Implements containerization .

Docker is an containerization platform which have created command for interacting with the containers easily .

So to create a container , we write a docker file and we submit it to the docker engine using docker image  .
This docker engine will convert this docker file into image . Again using docker command , we can convert this container image or docker image to convert it to the container .


![[Pasted image 20250304140527.png]]

Here used the command - 
`docker build ` , `docker image ` 

The Problem here was that the docker was completely dependent on docker engine which was the ***single point of failure .***
It means that if our docker engine is down , our all docker containers will stop working .

Now to avoid this Spof and to solve various issues like when the docker engine creates the docker image from the docker file , it has various layers . And Each layer contains lots of space on our disk 
So now to solve all these problems -***Buildah*** came into the picture .
It works on commands instead like in previous like docker files .
It uses  the shell script and inside these shell script , we can write all the buildah commands
-  And Using these commands buildah will create a Image .
- And these image will be docker Image or any other OCI compliant Image .

![[Pasted image 20250304142232.png]]

On a virtual machine (VM), multiple containers can be created, each operating with minimal system dependencies. If the kernel is not running, containers do not consume resources from it, as the kernel is the core of the host operating system.

Each container includes only the essential system dependencies needed for its functionality, ensuring logical isolation. This prevents containers from interfering with one another, even when running on the same VM.
Otherwise the security of all other container will be affected .


***Files and Folder that the container have with in it .***
![[Pasted image 20250304143739.png]]

So these folders form a logical separation from one container to another .
Which means a container can't share these files and folders with another containers .
These are the containers base image .


***Files and Folders that the container uses from the host OS .***
![[Pasted image 20250304144248.png]]


We can run multiple containers as long as system resources are available and not fully utilized by other containers.

In a virtual machine (VM) environment, each VM operates with its own dedicated operating system. As a result, one VM cannot access or share kernel resources with another VM, ensuring complete isolation between them.



Podman is a competitor of docker which addresses issues of docker like having single docker daemon and other issues .

Architecture of Docker -
![[Pasted image 20250304145605.png]]

Here in the docker Client , we execute some commands which is received by the docker daemon .
Docker daemon is the process that we install , when we say we are installing docker , actually we are installing docker .
These receives the commands from the Docker Cli and executes it to create containers and images .
***Docker Daemon is the heart of docker , If it stops , the docker goes down .***

Docker build -- creates images 
Docker Run -- creates containers .


![[Pasted image 20250304152640.png]]

Docker file is a set of instructions .
Docker container also helps in efficiency .  As for running docker container , we just need to pull the image and run it .. 

***Terminologies --***
- The **Docker Daemon** (`dockerd`) is the background service that manages Docker containers, images, networks, and volumes on a system. It acts as the **brain** of Docker, handling API requests and executing container-related operations.

- Docker Client --

- Docker Registry --

- Docker Hub - It iys a public registry . 
- **Registry** – A platform for storing and sharing Docker images. It can be either **public or private**. In a public registry, users can create **private repositories**, similar to how GitHub allows private repositories within a public platform. But  it is a Image sharing platform whereas the GitHub is a source code sharing platform .

The drawback of docker is , it always runs with the root user .


Docker Process 
![[Pasted image 20250304174515.png]]

So In order to containerize our application , 
Here we should have the knowledge of where our services gets their req. from and where  
these are interacting with other microservices .

![[Pasted image 20250304181955.png]]


 While containerizing , we also have to expose this port so that it also runs on our local machine and not in the container .
 If we are running this  in our EC2 instance  , we have to allow the inbound traffic rule to run it in our local machine .


#### Multi-Stage docker Build

Suppose we have written a calculator application in python then for it , 
First we will install ubuntu base Image i.e.
`from ubuntu`
then we will install the python runtime environment  .
then we have to install thousands of things like dependencies also 

Now to solve this issue , we use Docker Multi Stage Builds 

So we will see now what requires to run our application , that is Python Runtime . Other apt things are req. to build our application .

For Ex- If we are containerizing a java application - 
To build this java app , we require 100s of libraries 
But However to execute this java application - we only need Jre and java binary file that can be .jar file or .war or .ear file .

So for executing the java app , we don't need the base image , so for this issue , docker Introduced #Multi_stage_Build  .

So in this , what it does is , it divides our docker file into two parts (can divide it into multi-part ) as per our example .

So now in 1st stage , i will download all the dependencies required by our application , like as ubuntu has curl , wget etc. . Now will create a binary file of this and will use it in the second stage .
In second Stage , here we will install a minimalistic Image maybe python , java or etc. it doesn't have curl or wget but these req. for the dependency installation which we have already done .
Now here i will only execute . Here i will copy the artifact binary from above using the command ` Copy --FROM Ubuntu` . Here in this , we will have the Entry Point of CMD .

So now we have reduced our application image Significantly as Stage 1 Image won't be there in the final Image .
![[Pasted image 20250305021357.png]]

![[Pasted image 20250305020058.png]]


For Ex-  Suppose now we want to containerize the 3-tier Architecture . Which has Frontend for React , Backend for Java and Database for MySQL .

Here , we don't use java as base image , becoz then we have to download 100s of things , we choose a very rich base image , that is a base image that has all the dependencies due to Multi-Stage build , now in next stage choose the minimalistic image only which will be included as the final Image .

So now in 1st ubuntu Image , We install the java , react and mysql dependency then build the java and then the java and then Entry Point `/app.ear` .

As the final stage is the JRE , we will only include the binary from the last stage and execute the .ear file .![[Pasted image 20250305021023.png]]

So our app size from 1.5 Gb goes to 150 Mb only .

So in a MS Build , we can have countless no. of stages and only a final image which will be a minimalistic image . 
Here we can divide our frontend and backend in different stage and then bring them in our final stage to execute .
![[Pasted image 20250305021707.png]]




***Distroless Docker Images --***
A **Distroless image** is a **minimal container image** that includes only the necessary runtime dependencies for an application—**without a package manager, shell, or other unnecessary OS utilities**. This makes it **smaller, more secure, and more efficient** than traditional base images like `Ubuntu`, `Debian`, or `Alpine`.

For Ex -- 
Instead of using **`openjdk`** or **`alpine`**, you can use Google's **Distroless Java image** for a lightweight and secure deployment.

![[Pasted image 20250305022432.png]]

***A statically typed language doesn't require an runtime like golang doesn't require go runtime .*** So it will have more smaller size of the Image .

**statically typed** means that **the type of a variable is checked at compile time rather than runtime**

**Go compiles directly to machine code**, making it natively executable on the OS.
So Golang Distroless Image is even more secured , it don't even need runtime .


So to run Golang , we use `scratch` Image .
The `scratch` image is **empty**—it has **no OS, no shell, no libraries**.


### Docker Binds , Mounts & Volumes 
![[Pasted image 20250305041326.png]]

Docker is lightweight  ,  so it doesn't have any file storage Options . So we create a log file for the container , now if the container is down , the log file will gets deleted , as these log files are short- lived .
As they use CPU , Memory , Storage of Host OS .

So it is a problem , as a person is not able to access the log files maybe of today or yesterday , or any other file maybe Dynamic HTML content generated by the Backend , not able to be fetched by the frontend as the backend container is gone down will create a huge problem .

now to solve this , Docker has provided 2 solutions - 
1) Bind Mounts -- ( specific use case )
	- It will Bind a host OS file with itself . So this file can be read or written by the container .
	- So even if our container goes down , the new container can bind itself to the same host OS file .
2) Volumes -- ( more preferred )
- These are the logical separation created on the host .
	- These do the same things , but provide a better life-cycle .
	- We can attach to a single or more than one container at the same time or remove from one container .
	- The Advantage of volumes is - it has lifecycles , i.e. we can create ,destroy etc. .
	- We can manage its lifecycle using docker commands , doesn't need the containers for running .
	- In Bind , we are depend. for the file on the host . But In Volumes , we can create it on the host , any external ec2 instance , or any external storage like S3 , NFS etc. .

- Volumes are much useful  , as suppose , we want a large amt. of space which is more than avail. at the host OS , which we have later on have to take a backup off . So we can use volume .
- These Volume can be of veery  high - Performance .


If Writing the commands , rather than < , , > , go for verbose option ( elaborated form ) .

```
To Mount a Voulme , after creating using 
docker create my_Volume
docker run -d -v my_volume:/app/data --name my_container my_image
```

Now to delete the volume , first stop the container it is attached to , then delete the volume .




### Docker Networking --

It allows containers to connect to each other as well as with the host system . 

So if we have two partitions in Vm , they have their own OS , so we can attach both with different sub-nets ( networking group ).

So there could be 2 things - 
- Complete isolation between two containers 
- Interaction between 2 containers 

Now if on one frontend is running and on another backend is running then we need communication between them .
If on one container payment is done and on another login is done , they should be completely isolated from each other .

So Networking provides both  .

#Eth0 - It is a network that is provided to our host , It is created by default  for any resource we are creating whether it is Vm , container or anything else .

So when we created a container , Between its eth0 network and of the Host , there will be a sub-net difference , so they can't communicate .

So what docker does is , it creates a virtual eth0 network . which basically is docker 0 . This is known as Bridge Networking .

The default networking in docker is ***Bridge Networking*** . It is virtual Ethernet , it is called veth called Docker0 .
If we try to delete this network , then our container will never able to communicate with our host and as the user is connected to host , hence it won't be able to reach to the container .


The Other Way of Networking is ***Host Networking*** --
Here what docker does is , when we create a container , docker directly binds it with the IP Address of the host i.e. the Eth0 of the host .

So now both are in the subnet-group , So now they can communicate with each other . 
So now we can ping from container to the host .
So now whoever has access to our host can have access to our container as well .
But this is problematic as we want the container to be secured .( That's why we were preferring Distroless Image. )


The Other Way of Networking is ***Overlay Networking*** --
It is useful when we have multiple hosts i.e. in orchestrating engine . So if we want to create a cluster over this multiple host , overlay network can create network that is common among this multiple host .


]Bridge Vs Host Vs Overlay
![[Pasted image 20250305053038.png]]

So now the First Problem , that is the backend and the frontend using same network is done by this .

So from one container , we can ping to the another container .


But if we use finance , there is only one veth that is docker0 , so to connect to the host it uses the same network that is used by the login container .![[Pasted image 20250305115133.png]]
So It is not secured .


So there should be a logical Network Isolation , It can be reached by the help of Bridge Networking .
What it does is it allows us to create custom bridges . So instead of following the same path that is through veth0 . It allows to connect to host through a custom bridge .
![[Pasted image 20250305115800.png]]

![[Pasted image 20250305120558.png]]



We can get all the network of all the container through -
` docker network ls` 

So if we want to remove any network , we can use the command 
`docker network rm network_name`

So Now to create a bridge network , we can use the command 
`docker network create secure-network` 

Now we have created a secure custom bridge network with the name secure-network .
Now to assign this network - 
`docker run -d --name finance --network=secure-network nginix:latest`




### Interview Questions -

![[Pasted image 20250305152828.png]]

![[Pasted image 20250305153450.png]]


![[Pasted image 20250305153756.png]]


***Difference between docker Add and Docker Copy --***
![[Pasted image 20250305153944.png]]


***Difference Between Docker CMD and Docker Entry Point --***
![[Pasted image 20250305154410.png]]

![[Pasted image 20250305154632.png]]


What does the MacvLan Network does -- 
![[Pasted image 20250305155003.png]]


![[Pasted image 20250305155250.png]]

![[Pasted image 20250305155733.png]]


![[Pasted image 20250305155858.png]]

![[Pasted image 20250305160307.png]]

Our Image can also contain issues , so for that use `Sync` Tool .
