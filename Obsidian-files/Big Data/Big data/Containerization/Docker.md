Docker is an open platform for developing , shipping and running applications .
Docker is a platform which packages an application and all its dependencies together in the form of container .

Docker example -- 
if a particular project is working in a computer which requires a specific dependency , when this project shifted to another where this dependency is not present , it won't work .However if we shift this project in form of container wich is containing both the dependency and the project then there will no problem .

**Docker Architecture  --- 

It has Docker engine .
It helps to run multiple versions of same application which won't be possible otherwise .
It manages our containers .

> **Docker File -- Text document which contains all the commands that a user can call on the command line to assemble an image . Ex- if making a image then what our parent image will be -- php , ubuntu , linux etc . what is our directory etc .


> Docker Image -- Template to create docker Container .
> (Interface of softwares we need to run in Container . just like constructor of a class which tells its attrribute and behaviours )

>Docker Container -- Running instance of Docker Image . 
>It holds entire package to run application .

so Docker File  runs -> Docker Image  runs -> Docker Container 

Example -- 
![[Pasted image 20240513104441.png]]


Docker images -- to see all the images .
Docker hub - it contains all the images .

To pull any image from docker hub
docker pull image_name

Ex- if we give 
docker pull openjdk 
docker pull openjdk:18

it will download 2 different images .

if we want to search any image from docker hub using cmd
docker search image_name
or use 
docker hub directly



Now to turn images to container --
docker run Image_name Or Image-Id

now the container would have been ran and then closed immediately too .


to check our which container is running
docker ps

to see all the container we ran 
docker ps -a



if we want the container to run in background 
we have to pass different parameters or use different flags according to use --
--detach (-d )or --env(-e ) etc. 

--detach - means it will run in background


We can also give name to our container using  --name

Ex- docker run --name pythonContainer -d python ( Image_name or Id) 
it will give us a Id . 
Though on docker ps it still won't show it as the container would have ended .

for Interactive mode use --it (it is a flag )

Ex- docker run --name pythonContainer  -it -d python ( Image_name or Id) 
Now on docker ps , our container will be shown running .

Now to go inside docker use exec
docker exec -it Container_id  Command  ( can know by docker ps )

Here what exactly command we want to execute in that image , command depends upon the image we using .

Ex- docker exec -it ( Python_Container_Id or Container_name ) python3

now we can run python on our command shell which will be running on a separate system from our host operating system .

**to exit this ctrl-D or exit()**

If we want any information about our container
docker inspect Container_Id

How to work on these images can be learnt through docker hub documentation .

To run mysql
Ex- docker run --name mysqlDB -e MYSQL_ROOT_PASSWORD=root -d mysql

To go inside MySQL container

docker exec -it mysqlDB bash
mysql -p (to give password )
password

Inside it we can use all the command  of our database that is
show databases;
create database mydb;
use mydb ;
create table test(id int);
show tables ;


To Run Nginx
docker run --name nginxServer -d -p 8080:80 nginx  ( Image_name )

here -p --> port no. 
now when on our host system we will run port 8080 then the port 80 of our container will run .
i.e on browser when run localhost:8080 will show nginx on it .
As our host port number and our container number will be different 


**To  stop any container
docker stop Container_name Or Container_Id
or even we can pass the starting digit of our id too 
docker stop 8ed**



To remove from here too 
docker rm container_id  container_id  container_id
and can remove many in one go

**Can remove images in the same way
docker rmi Image_name
To remove all the unused images in one go
docker image prune

To remove all the images in one go
docker rmi $(docker images -q)
the `-q` option is used to display only the image IDs, rather than the full details of each image.

To restart container
docker restart Container_name Or Container_Id

To login on docker hub
docker login

It is  used to create and save the image of a edited container on the local system
docker commit

we can push our own image on docker hub using
docker push

Copy files from docker to a local system
docker copy

To check logs of any container
docker logs (container_name)




Create a volume for container to store the data
docker create volume Volume_Name

Use that Volume
docker create volume myvol
Ex- docker run -d --name devtest -v myvol:/app nginx

If our container crashes , In most cases our data will be safe in volume

List all volumes
docker volume ls

Display the volume info
docker volume inspect volume_name

Remove a volume
docker volume rm volume_name

Deletes all volume not mounted
docker volume prune

Or We Can Store the Data in the local Folder 
docker run -d --name Container_name   -v   Local_Folder_Path    Image_name

Ex- docker run -d --name devtest -v d:/test:/app nginx




To logout from our docker hub
docker logout

[[Pasted image 20240515225046.png]]

We can set the limit for the container on the CPU and ram used by it .
[[Pasted image 20240515225207.png]]
[[Pasted image 20240515230055.png]]
[[Pasted image 20240515225340.png]]

We can Attach a shell to our terminals and can run commands directly into our containers.
[[Pasted image 20240515225549.png]] 
Once we have attached shell to our docker , we can install software, configure settings, start or stop services, or perform any other actions necessary to manage the software running inside the container.
Once we are attached to the  running container , we can see its bin
ls bin
and also see its logs .
[[Pasted image 20240515231140.png]]
Tag used to give name to an image and tag can be used to give version No. 


### To Create own Image

In case to make any image , we will make a folder in the docker file and open that folder in vs code .
Here Ex- ubuntu-image folder

Now in Vs code
```
FROM ubuntu
# here we tell the base image

LABEL author="Aditya"
# Run update command

RUN apt-get update
```

Now from cmd
docker build --tag( - t ) myubuntuimage .

here . signifies from the current working directory .

To execute another file using docker .
Here we have made a Test.java file
```
FROM openjdk

# Define the working directory inside the Docker container
WORKDIR /usr/src/app


# it copies files from our local machine to the working directory in the Docker container
COPY . /usr/src/app/    
It needs two things src and dest

# Compile the Java source code inside the Docker container
RUN javac Test.java

# Define the command to run the Java program when the container starts
CMD ["java", "Test"]
```

Now if we have to run any project on docker 
We make the package of that project that is make their jar file and then that jar file 
which is the executable file is used in our docker

So open vs code 
-make maven project 
-run it on port to check whether it is working or not
-then build it and create a jar file
-copy that jar file in another folder and open vs code
-create Docker file and write these command
![[Pasted image 20240514234917.png]]
once done then in cmd
- docker build -t bootImage .
- docker run --name bootproject -d -p 9595:9595-it bootImage 
- so mapped the host port to the container port


To run nodeJs App
```
From alpine (Base image)

RUN apk add -update nodejs nodejs-npm
# We run the package manager inside container to install nodejs

COPY . /src
# We copy all the local files into the folder named src inside the container

WORKDIR /src

RUN npm install

EXPOSE 8080
# add the metadata , here to listen on port 8080

ENTRYPOINT ["node" , "./app.js"]
# what the container to run when starting
```

To add 
docker add 
and to build the image
docker build 
then run 
docker run express js




