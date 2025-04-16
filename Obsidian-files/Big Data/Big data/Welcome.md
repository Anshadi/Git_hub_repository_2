[https://youtu.be/KcecJfxbd-4]()





![[Pasted image 20240428220951.png]]
Hadoops components 

Apache kafka is a data integration tool .

![[Pasted image 20240428221509.png]]how big files data gets stored in hdfs

![[Pasted image 20240428222555.png]]same things just different terminologies in different things

![[Pasted image 20240428223058.png]]
we will always want a high availability type of setup in organiztions because we don't want slave and master to work on same machines .. like setup

we can also have a single node machine system where we have all things on single node.(also known as psuedo nodes.)
setup -1,2,4 are prefferable

![[Pasted image 20240428231815.png]]It shows how hdfs works and how files are stored in it.


lets say there is my hdfs running and M1,2,3,4,5 are my nodes ,now it would have been pre-defined which processes will run on which and what will its path.
M3,4,5 will have data nodes running so lets say its path is abc/d where it will  store hadoop related data.
and on M1 where namenode is running lets say its path is abc/n.
so any client on an app which wants to write data to cluster needs to interact with master to begin with .

As soon as cluster comes up the first thing that happens is the datanodes are sending their heartbeats to namenodes in every 3 seconds to let it know that they are available.
namenode builds its metadata in ram with this info , it already has metadata in disk created due to the result of formatting of hdfs.

So initially it has info about which DN is available,when an app wants to write data to cluster , it gets in touch with NN inquiring about DN where data can be written , which refers to metadata and  responses with DN o.r slave machines which are free , based on which client takes the file to hdfs intending to write it to cluster .

in hadoop we have two things which are default block size which is 128mb and default replication which is 3 .
Blocks are logical entity . 
Lets say there is a file of 256 mb then it will be broken down into two parts B1 and B2 which will then be randomly distributed to the slave machines that is data nodes .
the rule of replication is we will never have two identical blocks sitting on the same node  and there is no differntiation between the orignal and replica for cluster its same .

after the blocks are written our data nodes are repeatedly sending their block report to NM every 10 seconds which is then updated in NN metadata and NN will have info what  are the files and files are broken down in what blocks and blocks are residing into which data nodes .And this metadata gets build in NN as  DN responds with there heartbeats and also every 10 seconds with their block report.
Here NN is master and DN are slaves .
Hadoop as a framework has been coded in java so every directory and every file and file related block is considered as an object which is getting stored and for every object that NN is tracking 150 bytes of NN ram is utilized which also tells us t
hat if block size  is smaller than 128mb then for the same file we would have more no. of blocks which would mean NN ram would be over utilized and over load NN , so block size should be chosen very careful as acc. to masters ram and  evident size of data .
This is how data is stored among blocks and distributed over dataNodes .
Hdfs is very fault tolerant and because of its replicas available .

the read and write process which is controlled by our methoda such as open and create which internally playing a role when a read or write operation happens .
Also replication is a sequential process and write activity is a parallel process.
Block contains the data , which means even if we have file of 1Kb it will stored in a block of 128 Mb which doesn't means that there is disk space getting wasted because when block resides on the datanode disk it will only occupy only 128kb of space for the data it has . because block is a logical entity which takes our data to disk and stores our data in disk .
there is three way to write data to hdfs--using commands, or using Ide through writting codes and 3rd is through graphical interface that is hue.


MapReduce WorkFlow ---![[Pasted image 20240502030916.jpg]]

MapReduce Architecture --

![[Pasted image 20240502030904.jpg]]

Yarn components --  Resource Manager , Node Manager , Container , App Master 

Resource Manager --- 
![[Pasted image 20240504111626.png]]

Node Manager ---
![[Pasted image 20240504111452.png]]

How Applications run on Yarn ---
![[Screenshot 2024-05-04 111213.png]]

Resource Allocation Example --
![[Pasted image 20240504112029.png]]


##### SQOOP --
![[Pasted image 20240504120414.png]]

Sqoop Architect --
![[Pasted image 20240504123016.png]]
![[Pasted image 20240504123125.png]]

How sqoop Imports works
![[Pasted image 20240504132907.png]]

How Sqoop exports works
![[Pasted image 20240504132948.png]]

Sqoop import codes--
![[Pasted image 20240504133526.png]]




