It is also known as Blocking Atomic Commit Protocol Or Anti Availability Protocol .  Becoz all nodes or members must be up to work . Here if the coordinator dies , all the services wait to hear from it untill it recovers , if the co-ordinator dies before making the final decision , then it sends the abort command to all the services .
![[Pasted image 20250417161619.png]]


The co-ord. writes the final decision in the disk before sending it to the other nodes .
![[Pasted image 20250417161752.png]]

Until the co-ord. revives , the other services which are waiting to commit will be prohibited to perform read and write operations .

Here Timeout could not help after a certain period of time , becoz they promised to follow the coordinators final decision .

If the co-ord. doesn't recover succesfully , a person has to manually intervene on the ongoing transactions on each participating nodes .

![[Pasted image 20250417162206.png]]


In google spanner , it mitigates the risk by using paxo group . ensuring each 2-pc member is highly available , even if its some of its pack members are down .


Sagas offers an alternatives to distributed transactions in microservices . which are loosely coupled otherwise and difficult to maintain .

These are more resilient than 2pc , It consists of Local transactions each updating a single service , if any local transaction fails , compensating transactions  made by the previous transactions .

Each Local transaction is responsible for the a single task , and they communicate through events .

Saga ensures consistency across multiple Services .

How it happens in flight Booking System - 
![[Pasted image 20250417173613.png]]

High Level System Design Of Saga - It Can be Implemented Using Choreography or Orchestration .

