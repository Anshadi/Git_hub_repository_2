It is the process of keeping multiple copy of data and keeping them synchronized across different servers .
- prevent fault tolerance against a single server 

Group of servers known as Replica Set . Each database instance within a replica set is called Replica Set Member .

It provides 
- High Availability
- Fault Tolerance
- Data Durability


High Availability - It is the concept of making sure that our data can be continuously accessed even when there is a lack of availability in the system .

For ex-  if a server is down , we can send the data to its replica set which otherwise would be lost .

***A mongo Db replica Set consists of 3 , or 7 Mongod instances known as members .
We can have a max. of 50 members in replica set with a max of 7 voting members .***

Each Replica Set consists of primary and multiple secondary nodes .
The primary is the only member of the replica set that receives write operations .
![[Pasted image 20250403111733.png]]


By default all reads operations are handled by the primary . But this behavior can be changed so that the secondary can also handle reads .

The secondary replicates the contents of primary by duplicating the oplog entries and applies the operation to its own datasets .
So the secondary dataset reflects the primary datasets .


If the primary goes down or is not responding , the #election can be initiated .
An Election determines new primary to the database .

During the election , the voting secondary casts votes for the secondary that is most suitable to becomes the new primary .
The secondary with the most votes becomes the new primary for the replica set .


Once the Unavailable member becomes available , it then rejoins the replica set as secondary . It catches up by applying the operations that happened while it was unavailable . 

#Failover is the process of electing a new primary in case of failure of existing primary and resuming operations with the new primary .

Failover is Initiated automatically when the primary node of a replica set becomes unavailable .


#### Automatic Failover and Elections 

Automatic failover is crucial for a fault tolerant system .

An election may be Initiated by a variety of events such as 
- Adding a new node to the replica set 
- Initiating a replica set 
- Or while performing any form of replica set maintenances such as
	- `rs.stepDown()` 
	- `rs.reconfig()`


***An Election is also initiated if the secondary member loses connection to the primary for more than the configured timeout ( 10 Secs by default )  .***


Once the Election is Initiated , the Secondary member that started the election 
- Shares how recent the data is 
- Shares the Election term - A count to track the no. of the elections .
It then proceeds to vote for itself .


- Each Voting Member can cast one vote per election .
- A Max. of 7 members can have the voting privilages .

**It is important to have odd no. of voting members in our replica set . It Ensures that the primary will be elected in the event of Network Partition .**


This is why 3,5 or 7 members in replica set is preferred .
Initially all replica sets have a priority value which indicates the members relative eligibility to become a primary .

The default value of all members ( Primary and Secondary ) of RS is 1 .
To make a member more or less eligible we can assign a different priority value between ***0 and 1000 .***

The Higher value makes a member more eligible to become a primary than the lower value .

A member with a priority value ***0*** is ineligible to become a primary and it can't even initiate a Election .


Suppose , A replica is available at a preffered geographical area , we can assign it a higher priority value , so that it always wins the election , suppose if it goes down , then we can vote amongst the lower priority .
And when this member comes back , it initiates the election and becomes the primary node .


#### Op_Log ---

It is a special type of collection known as capped collection , which behaves similarly as the ring buffer .

The oldest entries are overwritten once it reaches capacity .

What it helps in are 
- Recovering from a specific timestamp in the op log .
- checking if secondaries are lagging behind the primary 
- Determining the op log window to avoid an initial sync when performing maintenance .

- When primary performs a write operation simultaneously records changes in its op log . Meanwhile the secondary pulls a continuous stream of op log from primary .
- Each Secondary updates their separate op log and performs the operations . Hence they become update with the primary .

To use the local database , we  use the command `use local ` . There the file `oplog.rs` will be present that actually stores the op log for replication .

The op log entry will look  like this ![[Pasted image 20250408004130.png]]

Each Op Log has a timestamp of when  that operation was performed on database .  Op Log Entries are Idempotent , that is any Op Log Entries can be applied once or several times with no difference in our final result .


**By Default when Op Log Is created , it is 5% of the disk space with the upper limit of 50 Gb.**  Size can be changed manually .

Command to get each secondary Op Log Info - 
`rs.printSecondaryReplicationInfo()`


There can be Replication Lag due to different reasons like 
- Network Latency
- Disk Throughput 
- Long-Running Operations 
- Not having the appropriate write concern 



If For Some Reason Secondary falls very behind the Primary , it reaches the Recovery Stage .
**When a member is recovering , it is eligible to vote , but it can't perform read operations .**

Now to bring the replica up to date , it has to start the #Initial_Sync .

***Initial Sync --*** It is the process of copying all data including the Op Log from a Replica Set Member .
These are exceptions .

These are Expensive in terms of Network , disk and CPU Usage .

The configuration information can be found in the mongod.conf file and on the
`rs.conf()`  object.


`rs.printReplicationInfo()`--------- returns the actual size and configured size of the Op log. It also gives the length of the op log in time, and the timestamps of the first and last entries.


#### Read Concerns And Write Concerns 
Write concern describes how many data bearing members need to acknowledge a write before it is considered complete .

Higher Level of Acknowledgement produces a stronger durability guarantee .
Durability ensures that the data that is stored in the database is not lost in case of failover .

**By Default Mongo - Write concern - Majority**
![[Pasted image 20250407185322.png]]


So if we have the write concern of majority on the 3 member Replica Set . Then 2 of the 3 member needs to successfully perform the write operation for it to be considered successfully .


***To Configure Write Concern - Ways -
- Directly on a Single Operation 
- ![[Pasted image 20250407185713.png]]
  The write Timeout ensures that our operation won't be blocked indefinitely .



Read Concerns -- 
It allows our application to specify a durability guarantee for the documents that are returned by a read operation .

**Through Appropriate Read And Write Concerns , we can adjust the level of consistency and available guarantees .**
Such as waiting for higher consistency guarantee or loose consistency to provide higher availability .

We can choose between returning the most recent data or the data that is committed by the majority of the members .

Though there are many read concerns level , but here are the main one -
- Local
- Available 
- Majority
- Linearizable 

![[Pasted image 20250407190324.png]]

![[Pasted image 20250407190513.png]]


![[Pasted image 20250407190544.png]]

Here the setDefaultRWConcern is the admin command , which is always set to 1 .



Read Preferences describes which part of the replica set , we want to send  read operations to .
There are few options for it too -
1) Primary - By Default - That is all reads go to the primary member of the replica set .
2) Primary Preferred - Reads go to primary , but in case it is unavailable , it goes to the secondary .
3) Secondaries - It reads from our secondaries
4) Secondary Preferred - Reads from Secondary , but if unavailable , reads from primary .
5) Nearest - It directs all reads to the member with the nearest network ping .

***Nearest Option is useful if we want to support geographical local reads which can improve our latency .***

There is a chance that when we are reading from a secondary , we would return stale data .
![[Pasted image 20250407191412.png]]

One way to add the read preference is to add it into our connection string , here we can also mention how stale our data can be . So here it won't be behind 120 secs .




#### Deploying a Replica Set -
