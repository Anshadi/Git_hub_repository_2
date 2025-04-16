## Elk Stack - E , L , K  , X-pack , Beats 

[https://youtu.be/ifr0uxteQNM?list=PLA3GkZPtsafYd5m2BXmkL9pjsBKy0FQ2X]



#Elastic_Search - Search Engine , For typos , autocomplete etc.

#Kibana - UI  - Dashboard For visualization of Elastic Search 

#Logstash - processes logs from app and send to elastic search . Now it is also a server side data processing pipeline . It ingests data and works on it and then  sends to the stash whether Kafka or Elastic Search .


#X-pack - Adds Security , Machine Learning , used for recommendation  , provides sql Api

#Beats - Collection of data shippers . sends data from thousands of machine to logstash or elastic Search . Different beats do different tasks .

we have to start the batch file of elastic search and copy the token and then start batch file of kibana and paste it there .

Node - It is elastic search  instance i.e. elastic search server .
Cluster - Collection of nodes

Can create diff cluster based on diff tasks . Ex - one cluster for searching , another for monitoring .


- Data is saved in form of json in elastic search with some metadata attached 
- And all the  keys in the json starts with underscore ,
- Documents are grouped together by indexes .
- Ex- " _index " ,  "_source" . 
- _ source - It contains the actual data .

		Equvivalent to 
		Document in Elastic Search == Rows in Sql     
		Fields  == Columns in Sql
		Index == Table in Sql  --- Identifier  Ex- In whole doc let there can be two types of shard either student or teacher that is only two types of data present .


***


When Query Runs --> gets to Elastic Search --> gets converted to Rest Api.

There is 2 ways to take data from elastic search -
1) Directly run query 
2) Through Rest Api --  Method_Name _Api_Name / Its Command

![[Pasted image 20240630191559.png]]


 Now to do the same through command line 

we have to use this format :

Curl -X Method Name  -k -u username:password  portNumber /  Api Name / Its Command .

Used -k here to bypass the certification of elastic which is not trusted by the cmd .
Used -u here to enter the username and password .


Ex- Curl -X GET -k -u elastic : password https://localhost:9200/_cluster/health 

#Sharding -- It is the way of dividing the data into smaller parts known as shards .
It is done at index level .
It improves query performance by parallelization of queries .
Every Index - One Shard - can hold 2 billion Documents .

We use shrink Api and Split Api for the above purposes .

#Replication - copying the original shard i.e. primary shard to make replica shards 
all the replication of same shard comes under replication group . assigned on different nodes . If only one node then the replica shard remains unassigned .



Ex -- 
![[Pasted image 20240701114408.png]]

Now to see Column Header we can use " ?v " with some commands .
![[Pasted image 20240701114730.png]]

Here can add comments in the request body .

Now to create a index , we do
```
PUT /fruits 

The Output will be --
{
  "acknowledged": true,
  "shards_acknowledged": true,
  "index": "fruits"
}
```

Now to chk it 
![[Pasted image 20240701115251.png]]
Its  health is showing yellow , which means unhealthy . As we have created a shard and its replica is also generated but it is not assigned to any node .
\
Can chk the shard health using
`Get /_cat/shards?v`

Can chk cluster health using
`GET /_cluster/health`

To chk about nodes
`get /_cat/nodes?v`


***

**Now to create a cluster --**

If we haven't  started the elastic search then we can create its copy  and make changes in node name in elastic.yaml file . 

we have also have to enable the replication from the elastic .yaml file if not already enabled .

But if have started then have to extract again in those nodes i.e nodes2 and nodes3 .



Now for the 1st time we generated enrollement token for kibana , now to create a new node we have to generate enrollement token for it also , using this inside bin of node-1
` elasticsearch-create-enrollment-token.bat -s node `
We will generate the enrollement token from here and then inside cmd of bin of node2 , we will put this token .
` `

Now our shard health will be green as it got a node for the replicated shard .

Now our two nodes cluster have been created , now if we start 3rd node then we have to every time start two nodes out of 3  to start the cluster as according to the elastic search because in case of 1 node running out of 3 , es is not able to select the master node .


***

**Nodes Role -**


There are many nodes roles --

- Master Node -- 
1) cluster wide actions
2) creating or deleting index 
3) track which nodes part of cluster
4) decides which shard allocate to which node 
5) to make any master node in config file - `node.master =true`
6) As making it on , it becomes master eligible 
7) But can become master only through voting

- Data Nodes -
8) holds shard - contains data we have indexed 
9) to not allow any data to store on that node - in config file ` node.data = false`
10) we make this false for the master node 

- Ingest - 
11) enable it for ingest pipeline -- i.e. -- indexing documents -- manipulate documents
12) `node.ingest =true/false `
13) did same work as elasic search
14) If we are running file beat , keep this off 

- Machine Learning Role -
15) On those node , we have to use ml algos 
16) `node.ml = true` -- used for running ml jobs
17) `xpack.ml.enabled = true` -- used for accessing ml Api

- Coordination Role -
18) routes queries to other nodes  - load balancer 
19) to make it on we have to do 
20) `node.master =false`
21) `node.data = false`
22)  `node.ingest = false`
23) `node.ml = false`
24) `xpack.ml.enabled = false`
25) Automatically , it will become coordination node

- Voting-Only Role -
26) Helps in deciding the master node 
27) can't become master node itself
28) `node.voting_only = true/false`


***

**Crud Operations -**

To create shards - 
1) With Default settings - Put /students  Or Put students
2) With Custom Settings - 
		PUT /students
		{
		  "settings": {
			"number_of_shards": 2,
			"number_of_replicas": 2
		  }
		}

3) To delete Shards - Delete students 
4) With Data --
		Put students/_ doc/ id_if want_to give
		{
				"name" : "Adi"
				"age" :21
		}
		 Id - assigned - unique - can be given or automatically 
		Ex- 
				PUT /students/_doc/1
				{
				  "name": "Adi",
				  "age": 21
				}

		 
5) if have to delete - Delete students/_ doc/ id_assigned
6) Now if want to fetch data -
			![[Pasted image 20240701223119.png]]	
7) Now if have to update a field and add a new field - 
		![[Pasted image 20240701223144.png]]
		In Elastic Search , when we update , it doesn't update rather it is replaced , as it is more easy .	
8) Now we will do the scripted update - i.e. the endpoint will be same but the we can write our custom logic .
		Using ctx , we can select the whole document 
		Using _ source , we can select the actual data 
			- now can use directly value or use params -- on these params we can write our logic too -- 
			- ![[Pasted image 20240701223225.png]]![
9) If have to run multiline script - Use thrice """   """  
10) Can use delete and **upsert** command here too - 
		When we use **upsert** , it means if the  id is found then do the above task or if not found then using **upsert** create the doc -
		![[Pasted image 20240701223343.png]]
11)  Now to replace the whole doc -  will use put with doc id have to replace .


***

Commands Execution --
![[Pasted image 20240702001828.png]]


***

#Routing -- It is the method through which we decide where we have to search the document  Or  to insert the document . As Elastic Search ,  directly goes to that shard and fetches the document .

The Shard Number where the doc is stored is gotten through the formula -
![[Pasted image 20240701224925.png]]
As through this formula , it is decided where the doc will be stored .

Here By default - _ _route means _id but in place of that we can give our own field .

That's the reason , if we have created the shards we can't increase its number , because the formula will be disturbed as no. of primary shards will be disturbed .


***

**How Elastic Search reads the data --**

Our Get request goes to Coordination Node ---> Which acts as a node Balancer  -->  which sends it to data node --> After that routing happens --> Through there it goes to replication group  --->   Then ARS happens  --->  That replica is selected which is free or search  optimized  ---> this replica goes back to coordination node  ---> which goes back to client .



***

**How Elastic Search writes data --**


Our Put request goes to the Coordination Node ---> Data Node ---> Routing ----> replication group ----> Then 

1) To put the data in the primary shard 
2) Validate that data
3) Save that data in primary shard 
4) Save in replica shard 


*** 


There were some problems to solve which some terms came that were 
- **Primary term** - It  is the counter of how many times our primary shard has been changed
- **Sequence Number** - It is the counter for each write operation in our replication no. . 

The problem was --
 If data is written in the primary shard and then  its replication started and done in R1 and before it could be done in R2 , the primary shard closes down and after voting the R2 becomes the primary shard in the replication group , now the problem is the data is present in the replica but not  in the primary shard .


Using these we can solve the problem of data consistency - chks whether the data is synced or not , chks till where the data is saved .

It is done by global and local checkpoint --  It is the sequence number .
Global Checkpoint  --- It means there a checkpoint for a complete replication group .
Ex-  It is the least seq no. common which is ensured that it will be present in all nodes of replication group .


Local Checkpoint  ---  It is the  checkpoint for each replica . 
Ex- can identify some shard data is not written if seq. no. of R1 is n+1 and of R2 is n .
\

***

If in any case elastic search is not working and want a fresh installation , we can delete all the files inside the data  directory .


***

**Optimistic Concurrency Control --**
\

The Problem was -- Suppose there was data which was accessed from two points and if the changes on the data is done from  both point concurrently , there may be wrong data stored in it . 

So to resolve it what we can do is , every time we run a query , we can give the primary term and sequence number , so when the first client have changed the data i.e write operation done , the primary term and sequence no. is changed , so when second client will run query with  the old seq no. and primary term , it would give error . So he have to fetch the data and then update his seq. no. and primary term while sending query  .

5:18  , 5:40 


***

**Update_by_query , Delete_by_query --**


It is used suppose there is some field in the doc where we have to make change , we can take use of it .
Lets say we are increasing the age of everyone or every client by one .
![[Pasted image 20240702153248.png]]



Here version conflict means in the case the primary term and seq. no. of query is not same as of doc . 
Here in some cases of heavy resource usage , some resources are stopped for some milliseconds which is mentioned there .  -- Throttled Millisecond 
The time for which the request was stopped is mentioned in the throttled unit millisecond.

Here the updation is not done one by one but using bulk Api .

![[Pasted image 20240702161149.png]]


Here retries are done in case of network glitch or in case the node shuts down .
![[Pasted image 20240702161149.png]]

Now to delete by delete by query --
![[Pasted image 20240702161917.png]]



***


**Bulk Api --** 
It allows to index , create , delete or update multiple docs in a single Api call .

Here there is a bit difference in index and  create i.e. in index , we put the doc inside a index whereas in create , we create a doc inside a index  .

When we index with the same doc with same id , it gets updated whereas in create it gives error .
Here the syntax is diff . --> Req then data .

![[Pasted image 20240702164133.png]]
Here in case of index and create , we can give index or it can generate automatically .
In case of update ,we have to give id .


Here the request is processed in batches which are automatically divided on basis of our ram and space .



***



**Analysis --**
It refers to the process of conv. text in terms or individual token which can be efficiently stored and processed . 
These token are used to build inverted index for fast and accurate search .

It uses a powerful text analysis engine .


Inverted Index - It is a data structure to store terms .

This analyzer  has 3 parts - divides in words 
1) Charachter Filters  -- Process input text before tokenization , performs tags removal like html tags , and replaces specific charachters .

2) Tokenization  -- Converts the text into words based on punctuation and whitespaces
3) Token Filtering -- converts all tokens to lowercase 

By default there is no charachter Filtering .

In documentation , it is written what possible values they can take .

When we do indexing , the tokenization happens on its own .
In Elastic  , there is a predefined analyzer .

But for custom tokenization , we have an Api " _ analyze ".\

![[Pasted image 20240702180847.png]]

There are many types of analyzer -
If we don't give the value of analyzer , it takes standard as value . Otherwise 
- whitespace  -- It removes whitespaces .
- stop - It removes stop words like - are , is  - which don't have meanings 

Ex-![[Pasted image 20240702181404.png]]


There are two things - text and keyword .

***



**Inverted Index --**
Data Structure designed to solve the problem of quickly finding the doc on basis of specific terms .

For Each Token , the inverted index maintains a list of doc ids , where the token appears . It creates  a mapping between each token and the corresponding documents where that token appears .

It also stores the freq. of appearance of that word in that particular documents . to give priority while showing the doc on basis of that term .


Ex--  ![[Pasted image 20240702182443.png]]


**Forward Index --**
There is a forward index - which simply stores the index to the location of each document . for the direct retrieval of that doc .


***


**doc values --**

For Numeric Values -  Elastic Search Uses doc values instead of inverted Index .
It is also used for date fields .

Ex- ![[Pasted image 20240702184127.png]]
![[Pasted image 20240702184156.png]]

We can store the numeric value in the inverted index , if want . And we can also disable indexing for a field .

If we set a word as keyword - then it will be stored as it is for ex- email in doc values and not in inverted index .

How many text fields , that many inverted indexes  .

Dynamic Mapping - It automatically maps the value to its data type .

Explicit Mapping - when we do it by overriding the dynamic mapping of elastic search .

****

When we enter the doc with the same id and changed value , this new one replaces the old one . And increments the version number .


***

**Mapping --**
Defines how documents and their data fields are stored and how they should be analyzed .

While dynamic mapping does this , but explicit mapping gives us data control , consistency , let us define the schema and ensures that elastic search behave as expected even when our data evolves over time .

- Preventing type conflicts 

To see maping of any doc , we can use _ mapping  api .

In elastic Search , if there is any text and elastic search is doing automatically mapping , then it will be stored in the inverted index as well as in doc values as a keyword .

Ex- ![[Pasted image 20240702201420.png]]

Now example if another doc came of student with extra field , lets say address then it will automatically stores its mapping .

However , if we want it not to be dynamically mapped , we have to write it to false at root level.
Now still  it will store the address field but  we can't call query operations on it as it hasn't been saved in the mapping .  Like we can't search using that field .
![[Pasted image 20240702202003.png]]
We can't give it at field levels  .
![[Pasted image 20240702202030.png]]

It saves space .
If we give `dynamic : strict ` , then the whole document will be rejected in case  of extra fields than mentioned .

Can't make mapping , if we already has put data into it .


***

**Dynamic Mapping --**

Through Mapping Api , we can get mapping of the doc and even mapping of a particular field .

We can add new field mapping to the doc .
![[Pasted image 20240702222525.png]]
We can't update the mapping of a field - i.e. - - we have to create a new index and copy all the docs .

It is because - In case we have saved too much data then we want to change the mapping of the id from text to integer , then what will happen to all the id of the previous documents .


This update is done by  **_ reindex Api**.


***
\
***_ Reindex Api -***  It Requires Source and Destination .

We can also provide queries and script to it .

In source - we give old index from where we have to copy and In destination - we give new index , to where we have to paste .

Source - can have index , query field .
Destination Tag - Index
Script Tag - Modifications 
![[Pasted image 20240702223725.png]]
If we have to copy some selected files , we can mention it in the source tag using "_ source" : [ "fields" ]

Ex-- 
![[Pasted image 20240702230236.png]]



***

**Coercion In Elastic Search --**

It is used for automatically converting data from one type to another during indexing or searching when the data type in the mapping doesn't precisely match the data provided i.e. expected data type mismatch. 

- It is basically type casting .

Now what happens is that  -
Let  in a doc if we have entered age:10 , it would have mapped it as long and now for another doc we insert 10.1 , it would have entered but converted to 10 in the doc and then stored . How when we will use query match all , it will be stored as 10.1 . But if in _ doc we will search the doc by the age:10 it will give me both element but when searched by 10.1 , it won't give me any doc .

However somehow elastic search using internal mechanism can return both result in case of 10.1 .

If give "10 years " however will give error , as it can convert years into long type .




We can also false the coercision .
![[Pasted image 20240703153620.png]]

Now we have to enter strictly the data type . or else the error will occur .
We can false the coercion either at the field level or at the upper level . But it will be written inside "settings" .

But it is also possible that we can false the coercion at the parent level and enable the coercion at the child level .
Ex- Incase of coerce false -
![[Pasted image 20240704033246.png]]
Different forms of coercion
![[Pasted image 20240704034007.png]]




***


**Data Types --**

1) Text -- used for full text search on that field - "type" : "text"
2) Keyword - for exact matching and filtering  - ex - id , categories - "type" : "keyword"  -- saved in doc values
3)  Date - "type" : "date" -- sort and range query 
4) Integer - -2 billion to +2 billion - 2^32
5) Long - 2^64
6) Float - 2^32 decimal
7) Decimal - 2^64 decimal
8) Object - it is used to represent nested field 
9) Nested - The field will be of nested type if it has array of objects as its values . Ex- In some case - "properties"

Ex-- ![[Pasted image 20240703161133.png]]
![[Pasted image 20240703161214.png]]


***


**Index Template --**
It is the way to define a set of configurations and mapping that will be automatically applied to new indices that match a specific pattern .

It means make a template that is pattern in which our indexes name will be .
![[Pasted image 20240703162730.png]]

Now in case of insertion , if while inserting the values we used that indexing pattern , the mapping mentioned there will be applied .

We can get that index template and delete it too .
![[Pasted image 20240703163007.png]]


We can update the index template too . It always will be applied on the  new index , old index will remain same .



***


**Elastic Common Schema --**
It is Standardized data schema  designed to provide a common set of field names and data types for logs and metrics in Elastic Stack . For consistency . 
![[Pasted image 20240703163954.png]]
![[Pasted image 20240703164245.png]]



***


**Alias --**
It is the additional name or label assigned to one or more indexes .  Acts as a pointer to underlying index . 
Can be added , removed or changed without reindexing .
It is just a pointer , if it is pointing to one index , we can make it to point another index .


1) **Used for Abstraction and Decoupling --**  Provides layer , separating physical index names with the logical names used in application - beneficial when want to change or reorganize index structure without affecting your applications .
2) **Blue-green Deployment --** Ensures zero downtown when changing from old index to new one .
3) **Filtered Queries --** Enables filtered queries . That is we can define alias with a specific filter and all queries routed through that alias will be filtered accordingly .
4) **Cross-Cluster Search --** we can create cluster in  such a way that  our alias points to the data to both cluster , so when searched we can data from both the cluster .



**Creating - Updating - Retrieving - Removing --**

We use _ aliases Api here . Our alias can point to many shards .
We use add , remove tag inside actions tag .
Now we can also delete the doc , our alias is pointing to . In this case our  alias is not deleted , it is just not pointing to any indexes .

![[Pasted image 20240704140543.png]]
![[Pasted image 20240704140604.png]]
In case of getting aliases , it will return empty field if no aliases applied on that index otherwise alias name , and inside alias name it will give empty field if no filter i.e. configuration is applied otherwise , it will return the  filter .

Jab kuch indexed ko same alias se point kara diye hain , tab jab search karna hai tab alias ka naam use karenge , to search in all the indexes . We can put the doc using index and using alias too but have to match  the alias . 
![[Pasted image 20240704120633.png]]

We can use patterns i.e. wildcards inside it too . for ex - while adding index , we can write 
"index": "students_*"  --- It will be applied on all the indexes starting from the name students_  .

We have to enter filter while declaring the alias itself .

In case our alias point to more than one index and has filter , if entering doc through alias
	Using an alias to insert documents into multiple indices does not distribute the document across all the underlying indices. When you put a document using the alias, Elasticsearch routes the document to a single underlying index. If there is a filter applied to the alias, Elasticsearch does not enforce the filter at the time of indexing. Filters on aliases are used only for querying purposes.
In this case it will return error as it is pointing to multiple indexes and it is not mentioned in which index to insert doc.  .

For this while defining the index , we can mention `is_write_index = true` ;
	When you have multiple indices associated with the same alias and one of them is designated as the write index, all documents will be written to that write index, regardless of whether they match the filter criteria of other indices. The filter only affects the querying behavior and not where documents are written.



****


**Queries Types --**

Queries DSL (Domain Specific Language ) - Allows to write code in domain specific language .

***Uses match query --*** For full text Search 
***Disjunction Max Query --*** Combines multiple queries and returns that doc that matches any of the query with highest score .
***Multi-Match Query --*** If want to run same query on multiple fields .
***ID's Query --*** It returns doc that matches any of the multiple id provided as the query .
***Term Query --*** If you have to search exact term , we can use this query .
***Range Query--*** returns doc whose value falls into the specified range .
***Bool Query --*** Can combine multiple queries using and , or not by use of qool wuery .
***Exists Query --*** Can check whether the specified query exists or not .
***Wildcard Query --*** Used for retrieving doc based on patterns . 
***Prefix Query --*** if we know prefix , we can retrieve doc based on that 
***Nested Query --*** Used for fields that have nested fields in it .


**** 



 #Match_Query -->
  It means want full search that is not exact data but related data . Best match on the top and below result has less relevant search .
	Here what happens is when we run match query on a text , that text would be already analyzed either by default analyzer or by custom analyzer . Here the match query uses the same analyzer . That is the text we will enter will be analyzed and will be matched with the analyzed text in the inverted text . But can be used to find boolean , number etc .
	Ex-  
	```
	{
	"query" : {
		"match": {
			"field_name": "search_term"
				}
			}
	}   
	
	
When we have to write only one operation , can write like this 
"field_name": "search_term" 
But when have multiple operations in it . Ex -
"field_name": 
{
"query": "search_term", 
"operator": "And" 
}

How well our doc matches our search depends upon **Relevance Score --** it gives a score field as a output . It decreases as we go down the result .
When we search more than one text , it uses the or operator behind .We can also mention operator explicitly like " and " , now it will give only those result which will have all the text .


We can use full text search on those even whose type is marked as Keyword .
The keyword is saved as it is , unlike text in which converted in lowercase .
So if we search cat which we are searching in the keyword type will return empty if in doc Cat is present .
As Normal type already gets conv. to lowercase and then searched in inverted index .


- #Disjunction_Max_Query --  "dis max"> Useful in case we have multiple queries on different fields ,  ensures that it returns doc which matches any query .
		Ex- In case we have two match query .
		It has two Components -
		- Query Clauses - comma seperated queries
		- Tie Breaker - It determines which query will determine how much in the final Relevance Score . As doc comes in descending order of relevance score .
		- Its value range from [0,1] 
		- Without tie Breaker - what it does is its takes the max of all thee individual queries and then return it . 

- Ex- 
- For Doc -1 --> query match-1 -->1.5 , match-2 -->2 , res-->2
- For Doc -2 --> match --> 0.5 ,2 , res --> 2

- now to decide which doc as both revelance score is same is problematic . Here , it takes tie_breaker = 0 by default .

	- If we use tie breaker , then 
	- max(score1 , 2 , 3) + tie_breaker*( score1+2+3 ) .
	- Now it will be easy to arrange doc on relevance score .
	- ![[Pasted image 20240704181349.png]]

> #Multi_Match_Query --  Uses "multi_match"
	> Same term - multiple field -- lets to prioritize certain fields over other .\
	> Takes two  field  - "query"  and  "fields" : [ array of fields] 
	> In fields can use wildcard and if not given by default " * . * ".
	> Individual fields can be boosted with ^ notation . It boosts the revelance score of that field .
	> The way MMQ executes depends upon type field . By default - best_fields
	> ![[Pasted image 20240704182312.png]]
	> We can use tie breaker here .

> #Id_query -- If we have an array of ids then it will return those doc which matches it .  
	> Syntax --> "query" --> "ids" --> "values "
	>It Does Coercion too . that if we give ids in string or in number will take it

 > #Term_query -- finds doc that contains specific exact term in a particular field . 
	>    Used for searching fields that are not analyzed . 
	>    Syntax --> "query" --> "terms" --> "field_name" --> " values " 
	>    Here Inside values can provide text or sentence , but it will search exact
	>    As this is not analyzed , it is keyword , So case Sensitive .
	>    But we can specify ` "case_insensitive" : "true" ` However then full text is better .

 >   #Range_Query -->  Used for searching range values .
 > 	  ![[Pasted image 20240704185854.png]]
 > 	  Here can define format of date too .

> #Bool_Query --  Here we can interconnect multiple sub queries .
	> Here We can use must , should , filter. 
	> Ex- In Must - In  which we can pass list of individual queries . All the conditions in it must match .
	> It is same as match query with operator and .
	> Should does the work of operator or .
	> Same is must_not , works as nand .
	> It does not depend on score . - must_not
	> ![[Pasted image 20240704191021.png]]
	> However if we used must , must_not and should , then it will be score dependent as there could be doc which includes should field and there could be doc which doesn't contains it .
	> Now but if we want that atleast one query from should match , then we can specify ` "minimum_should_match" : 1` Otherwise return empty .
	> We can use filter , it doesn't depend upon relevance score so it is faster as elastic search doesn't need to do extra work .So its data can be cached .
	> ![[Pasted image 20240704192051.png]]

> #Exists_Query  -- In case of exists , if the field value is null or [ ] (Empty List ), then it won't be seen in result . However if it is empty String then it will be seen (" "). 
> ![[Pasted image 20240704194648.png]]
> That field on which we are using it should be indexed . That is indexed shouldn't be false on that field .

 > #WildCard_Query -- Placeholders that allows to match a range of terms based on patterns .
	 > Here ? does the same thing that "_" does in sql . And then * does the same thing as sql .
	 > Here the twist is - if there is a data name : "Goli "
	 > And i searched name: "Go*" - it won't give me anything . Because what happens is **Wildcards are not indexed** . So it will search "Go" But as it will search in inverted index , where text are indexed so they are stored in lowercase . So it won't be able to find that . 
	 > So To find that we have to search "name" : " go* ".
	 >  ![[Pasted image 20240704195827.png]]
	 
 > #Prefix_Query - 
	 > Gives docs that contains terms with the specified prefix .
	 > It is also case sensitive . Same thing as wildcard about indexing.
	 > ![[Pasted image 20240704200156.png]]
	 
 > #Match_Phrase_Prefix_query :
	 > Useful incase when want to find doc with certain phrase and then continue with more charachters .
	 > Like in case of autocomplete suggestions .
	 > But the text entered phrase must be in same order .
	 > ![[Pasted image 20240704200652.png]]
	 > It is case Insensitive .
 
 > #Nested_query - 
 > In case of this ,  the nested field must be written inside the field path .![[Pasted image 20240704201206.png]]
 > If we want to see to which nested element , it matched , we can give specify 
 > ` "inner_hits" : {} ` . we can specify inside size too , that is how many inner hits , i want to see . `  "inner_hits" : { "size" : 2 }  `
 

Ex- Implementation -![[Pasted image 20240705143117.png]]

****


**Manipulate Results --**
Here we specify size to tell that that many doc we want to see .
Mentioned source here to tell which fields we want to see in the result .
By default It shows all  fields .
![[Pasted image 20240704201652.png]]
Source supports wildcards too .
` "_source" : [ author.* ]`
Below size , we can use from , which will get that much size of doc from the prev. result .


****

**Joins In Elastic Search --**
It allows to model parent-child relationships between documents within same index .
Useful in case we have doc that can be structured as the parent doc and one or more child doc .
It establishes relationships between doc within the same index .
The shard on which our child is , the parent should be on the same shard . So to do this we write ` ?writing=that-shard-no. id `
In this we can also insert those doc which have no relationship as parent or child .

Now when need to run search query , we can give parent id and along with it , the child type mentioned while mapping , as a parent can have multiple child .

Using `has parent` we can run operations like match query on it .


![[Pasted image 20240705143831.png]]


Here what we had to do is first give name to our join field and then inside it , mention type as join then inside relations tag - mention 
"parent" : "child " . Then whenever we have to make the doc as child , we can make its type:"child" .

Now we have to mention some index as parent then inside the join field tag put the parent type name .
![[Pasted image 20240705145655.png]]
And  In child while giving doc we also have to write the child name and the parent id in the parent tag also .

Now we can search specific for a parent by mentioning ` "parent_id" : 1 `, but inside it we have to mention the child name also . As a parent can have multiple child .

Now if we to use queries like match , we use ` has parent ` . 

***Now what is happening is we have multiple parent  and inside each we have multiple child and we are storing the data inside these childs .***

Same type of child can be present in multiple parents . Like the Hr and It parent both will have the empl child .


Now if we true the` "score:true" ` , the parent score will also be added in result .

Till now we did querying child by parent .

Now we do querying parent by child using `has child` .

Here the parent doesn't matter that is in context of this , only the employee matters and not the  department .
Now if we want to specify the dept too , we can do that too .

We can use `min_children : 10 ` will give me only those parent who have 10 child that fulfill this criteria . same way can use `max children` .
![[Pasted image 20240705162002.png]]



**Multi-Level Interface --**

Here we mention relations , Index Name inside it the same index name which accepts an array of its children , and below it the parent : child . 
![[Pasted image 20240705162450.png]]
![[Pasted image 20240705162637.png]]

Here we created a company , which child that is department is google and Apple , where child of apple is hr  and child of google is hr . Now we have put employee ram in HR in google . Here there is difference between routing and the parent is provided . The routing is of the grandparent .

Now if we have to find the parent of the parent of the employee , we can use this query .
![[Pasted image 20240705175410.png]]

Some Limitations here and there -
- Parents and child should be in the same index and same shard .
- only one join field .
- a document can have only one parent 



****


**Aggregation --** Uses "aggs" . parallel to query .
 1) Metric Aggregation -- calculate Statics - max ,min, avg , insights into numerical values .
 2) Bucket Aggregation --
 3) Pipeline Aggregation --


**Metric Aggregation --**
1) Single Valued - Single Output
2) Multi Valued - Multiple Output

Ex- ![[Pasted image 20240705180217.png]]

Here we have written match all to select all docs whose output will then go to aggr. pipeline .  It will give the result of avg on the field written . Given size 0 , as don't want the result of match all .  **Can use sum , min ,max ,distinct , filters etc.** in same way .

If we don't write match all , it will run by default . Now we can sum and avg parallel to each other inside aggs . **Before each operation we give name of the field with which it will appear .** 

Aggregation doesn't apply on text . applies on keyword ,long ,integer .
![[Pasted image 20240705181131.png]]
As here the director is both text and keyword . we have to mention it to apply on keyword  . To count total , we have to mention value_count .

>
	The result of aggregation is approx. and never accurate . Now if we want an accurate answer , we can use `"precision_threshold":100` . By default its value is 3000 .
	The value here means that ex- if we have 5000 things and we gave precision as 5000 . Till there the value will be accurate but if the things are greater than the precision , then value will be approx again .
	The max supported value of precision is 40000 .
	
	It doesn't provide accurate value because if there is thousands of memory to gather the correct intelligence , it will require a lot of memory and will have to communicate between hundred of nodes which is time taking and difficult .

There is an aggregation "stats" which automatically gets all the stats on the applied field .


f
***

**Bucket Aggregation --** Uses "terms"
- Basically does grouping . based on fields , ranges ,date intervals etc.
- Unlike metric aggregation , these can hold sub-aggregations , will be aggregated by their parent bucket aggregation .
- Ex- we want that genre , which has atleast two movies in it . 
- we can also use "missing" , which means when that field is not present on that doc , it comes under that field .
![[Pasted image 20240705184037.png]]

- Now we want more info about the result we got , we can create another aggs inside group by genre , that is nested aggregation . now we can apply stats on the genre field .
- Below missing genre , we can also define size , to see that no. of buckets  .
- Now if we have to create our own bucket 
- ![[Pasted image 20240705185414.png]]
- Inside this if we have to use source , to view limited fields , we have to use "includes" field inside "source" .

- Now we can apply range based searches also - we have to use here fields like "from " and "to" .
- ![[Pasted image 20240705190055.png]]

- We can also group by histogram . It distributes our data into equal interval given. and gives count . we can use "top_hits" and size in it , to see the top results till that size .
- ![[Pasted image 20240705190417.png]]

- can use extended bounds , it will search between the range given inside of it .
```
"extended_bounds":{
"min":1900,
"max":3000
}
```
- If we use `global:{}` inside the aggregation , it will have reach to all the docs and not only to those returned by above query . Now if we have to use nested aggregation .
- ![[Pasted image 20240705191331.png]]
- Now we can also use missing and in it give the field name , it will gather all the docs in which that field is missing and then we can use nested aggregation to operate on it .
- If any field is nested , we can't apply aggregation , we have to apply nested aggregation .
- We have to use `"nested"` here . inside it we have to mention path which will be the nested field name . then we have to use the aggregation aggs . and inside it we can mention the field that was nested . 
- ![[Pasted image 20240705194846.png]]




***


**Fuzziness** --
- Search technique used for approx matching rather than exact .
- Used in cases of typos or misspellings .
- Retrieve docs even when the term not exactly matches the term in indexed document .
- It is Controlled by non-negative integer fuzziness parameter . Representing allowable editable distance between the search term and the indexed term . 
- One change is equal to one fuzziness.![[Pasted image 20240705195735.png]]
- There is a auto parameter of fuzziness when applied , it can give right answer most times . As it has been set that if search term length is 4 , there may be 1 fuzziness  and likewise . More word - more auto fuzziness value high .
- There is a limit also , in case of explicitly mentioning it . That is if length is 5 then can give the fuzziness value from [0,2] .
- It is applicable individually .
- Suppose i have searched 2 terms and used and operator and both term contains a error . If fuzziness value is 1 , it will give right answer .
- We can use it match as well as in multi-match and prefix query too .
- It is case Insensitive . As it is in match query , it is analyzed .
![[Pasted image 20240705201129.png]]
- We also have a fuzzy query .Here we can also give the fuzziness or it is called by default .
- It is case sensitive . so it won't be analyzed . But it can come under fuzziness and if fuzziness is appropriate , it will search the term .
