### ALL DBMS COMMANDS

![[Pasted image 20250217163029.png]]

### THEIR DESCRIPTION
![[Pasted image 20250217163122.png]]

![[Pasted image 20250217163148.png]]
![[Pasted image 20250217163311.png]]
![[Pasted image 20250217163337.png]]
_id 
it is a required field in every MongoDB document, but it's not a data type. If an inserted document does not include an _id field, MongoDB will automatically create the _id field and populate it with a value of type ObjectId.

when we first add the field we put in double inverted comma but when search, we don't use that.
## Using insertOne()---
db.collectionName.insertOne({data to be entered})

it returns the object_id of the inserted value. 
if the collection name doesn't exist so it automatically creates one


## using insertMany()---
```
db.collectionName.insertMany([
<document1>,
<document2>,
<document3>
])
```

it will return  the object id of every document inserted
while in InsertMany() Argument "docs" must be an array of documents and that's why we use [ ]

## using find()---
db.Collection.find()
returns all documents

if specific then
db.Collection.find({"state":mumbai})
will return all datas regarding this field value
it will return all the docs having field value as individual value or value in array

## $in: operator returns all the datas regarding to it if it matches any one field value
the field values here is passed as array.

db.Collection.find({"state":{$in:["mumbai","pune"]}})

We can also do 
![[Screenshot 2023-12-16 235650.png]]

## using findOne()--
if we specify the query inside it ,it will return the first doc only that matches the value.
otherwise if no query specified, it will return the first document


field: { operator : value}---Syntax

$gt --greater than   -- returns documents having value greater than specified value
$lt --less than
$gte --greater than or equal to
$lte --less than or equal to

can access sub Documents using dot notation
db.sales.find({"items.price":{ $gt : 50} })

can do
db.collection.find({
  "items": { $in: ["cup", "mug", "glass"] },		//will give all cup mud and glass items data having price greater than 500
  "price": { $gt: 500 }
})


## using $elemMatch Operator we will only return those docs in which the field value or values are prsent as a value in array
here we use $elemmatch with $eq.

we can also use it to find  docs which matches all the given different queries in field value.
db.sales.find({
  items: { $elemMatch: { name: "laptop", price: { $gt: 800 }, quantity: { $gte: 1 } }, }, 
})
it will return only those docs which matches all the conditions.


## Logical Operations
$and :-- it can also be used as other operators . we use it when we use comma known as Implicit And.it acts like and operator as it returns the documents if all the conditions are met. Takes Array
$or :-- when it is used it gives documents that matches any query given in them. Takes Array.
can use or and and operator together. $and: { $or: , $or:}
we can't use Implicit And here because it will be overwritten by next or operation .Because we can't store two fields with same name in the same Json Object.

As a general rule : When we use same operator more than once in our query we should use Implicit And.
If using Implicit and then all the expressions will be in the same single braces.

db.routes.find({
  $and: [
    { $or: [{ dst_airport: "SEA" }, { src_airport: "SEA" }] },
    { $or: [{ "airline.name": "American Airlines" }, { airplane: 320 }] },
  ]
 })
Above Meant that
![[Pasted image 20250217155822.png]]
## Using findOne()--
returns single document on basis of given criteria.
![[Pasted image 20250217160600.png]]
![[Pasted image 20250217163602.png]]
![[Pasted image 20250217163543.png]]
## using ReplaceOne(filter,replacement,option) --
we can replace a single document by using it
To tell which document to replace we have to specify the replacement object id in place of filter.
In place of replacement we have to enter the the whole new data excluding its object id

But if we have to update the old one then we will use options.

## Update Operator
$set-- It adds a new field and value to the doc or replaces the old field value with new value.
$push--Adds a value to an array . if absent , it adds the array field with the value as its element.
## $each-- Use the $each modifier to add multiple elements to the array.
upsert() --it updates or insert document.

## Using UpdateOne() Method
This Updates a single Object .
It it takes three options--
filter , update , options
we can use $set and $update operators in it..
It returns a array when successful.

if it doesn't matches any doc , it doesn't update.
Here we can use upsert() so if doesn't matches any doc it will insert the info as new doc. 
so {upsert : true}
But upsert doesn't insert new Field into existing documents .
But $push does.

In MongoDB, the push operator is used to add an element to an array field in a document. When using it, you can use the $each modifier to add multiple elements to the array

db.birds.updateOne(
...   { _id: ObjectId("6268471e613e55b82d7065d7") },
...   {
...     $push: {
...       diet: { $each: ["newts", "opossum", "skunks", "squirrels"] },
...     },
...   }
... )


1.   `{ $each: ["newts", "opossum", "skunks", "squirrels"] }`: This is a modifier that specifies the values to append to the "diet" array field. The `$each` modifier ensures that each value in the provided array is added individually to the array field.



Two operators can't be used consecutively.

## Using $inc operator

It is used to increment the value of a numeric field by a specified amount. 
Used when we want to update a numerical field in a document without fetching the document first, modifying it, and then saving it back. 
Instead, you can perform the increment operation directly in the update operation.

![[Screenshot 2023-12-14 224600.png]]

The `$set` operator replaces the value of a field with the specified value. This code example would replace the value of the `items` field. It would not add an element to the existing array.

The `$push` operator adds an element to an array field.

The `upsert` option can add a document to a collection if it does not already exist. `upsert` cannot be used to update the value of a field.

## Using findAndModify()---
Used to return the document that just been modified.

It directly updates and return data.
so that it doesn't happens that when we update using updateOne() and then find() to retrive that data someone uses in between updateOne() and we get wrong data.

takes three arguments--query , update, new accepts boolean value.
we can also use upsert : true with it.

![[Screenshot 2023-12-14 230218.png]]


here if new is false , then it will return the document before modification and if it is true then it will return the document after the modification .


## db.collection.findAndModify({
```
   query: <filter>,      // The query to match documents
   sort: <sort>,         // Optional. Sort the documents before modifying
   update: <update>,     // The update operations to perform
   new: <boolean>,       // Optional. If true, returns the modified document
   fields: <projection>, // Optional. Specifies which fields to return
   upsert: <boolean>,    // Optional. If true, performs an upsert
   remove: <boolean>,    // Optional. If true, removes the document
   bypassDocumentValidation: <boolean>, // Optional. If true, bypasses document validation
   writeConcern: <document> // Optional. Specifies the write concern
})
```
 
![[Pasted image 20250217180439.png]]
## Using updateMany()--
It is used to update multiple documents.
It takes three arguments -- Filter , Update , Options

UpdateMany() is not useful for some cases like Financial transactions because as soon it is updated it returns that doc. and it also fails sometimes so in that case only some doc may be updated so have to run updateMany() many times.

![[Screenshot 2023-12-14 232730.png]]

![[Screenshot 2023-12-14 233903.png]]

To delete documents in MongoDb we can use 
## deleteOne() --
	We use first find() method to find the id of object to be deleted then deleteOne() method.
	It accepts two arguments - filter ,options
![[Pasted image 20250217183243.png]]
## deleteMany() --
	Used to delete many document . it accepts only filter argument.It also accepts option
	It return no. of documents deleted
![[Screenshot 2023-12-14 234804.png]]

**Cursor**
Cursor points  to the result of the query to be returned.
***So when we use find() method , it returns a cursor which points to the result document.***

***Except Find , only aggregate() , findIndexes() and findCollections() returns the cursor and no other functions***

we can use Cursor Methods to perform operations on the result before it returns to the user.

We can return query in a specific order and also limit the no. of doc in  result using two methods
Cursor.sort()  -- it sorts  the result in a specified manner

For Ex--   .sort({name :1})  --will sort it in alphabetically ascending order 
			   .sort({name :-1})  --will sort it in alphabetically descending order 

In Mongodb -- First it sorts the Capital Letters and grouped together then lowercase letter as their appearance in ascii 
![[Pasted image 20250217184609.png]]

lets suppose there is a field name interest with array value .we can also sort the document on basis of its any value.like
![[Screenshot 2023-12-16 235929.png]]

Cursor.limit()--it limits the no. of results.
Limiting the no. of results can enhance performance.
Inside limit we can specify how many document we want to see.
![[Screenshot 2023-12-15 121638.png]]



## Using projection method in find method we can specify the field names that we want to see from the search documents.

For Ex-- name is used here as projection
![[Screenshot 2023-12-15 121801.png]]
![[Screenshot 2023-12-15 121913.png]]

We are giving here the field value 1 so that we can the mongodb that we have to include that and if we don't want to show any field let's say Id field we can give the value of that field 0.
![[Screenshot 2023-12-16 003252.png]]

But inclusion and exclusion can't be combined that is we can either include a field or exclude in a projection but here id field is a Exception.

Projection is always used in Find Method. Other than find the other functions that can use the projection are ![[Pasted image 20250217184945.png]]

## Using countDocuments()--
Usin this we can count the documents which matches the query.
It takes two arguments -- query, options -- to tell how to count the document.
It returns a number .if no argument speecified in countDocument it returns the no. of documents in the document.

For Example ---
![[Screenshot 2023-12-16 010140.png]]

#h1 ## Aggregation Stage
![[Pasted image 20250217190633.png]]

![[Pasted image 20250217190755.png]]

--It is aggregation operation performed on data and do not alter source data.
Every thing inside the aggregate is in the array form. So its  all stages are inside the array.

Mongo db has aggregation pipeline where it is done in stages that  is output for one stage becomes input for another.
![[Screenshot 2023-12-16 145956.png]]

its syntax is -
![[Screenshot 2023-12-16 144015.png]]

It has many stages :
		some of the common one are :
			- $match -- filters the data that matches the criteria
						It should be placed in the early stages so we can lessen the no. of doc processing required as soon as possible.
						
			- $group -- groups the documents on the basis of the criteria. It groups all the value in the single array and returns that .
					We also have to specify the group key to it . In that we specify the name of field as a reference to the _id , we want to show.
					
			-![[Pasted image 20240422234041.png]]![[Pasted image 20240422234136.png]]
			
			
			- $sort --sorts the document in the specified manner

			-$limit --To limit the no. of documents

Each stage requires their syntax .for ex-- 
	In $match I can use find() syntax and in some i can use operators.
We can use number of times same operators in a diff stage.


	
Sometimes we use dollar sign infront of the field names to refer to the value in it .
For Ex--
![[Screenshot 2023-12-16 145909.png]]

We can concatenate two fields in MongoDB using the aggregation framework

## Using $concat ()-
This operator is used to concatenate strings. The `$concat` operator can take multiple arguments.

There is $concatArrays() too.

![[Screenshot 2023-12-16 191518.png]]
Ex--
![[Screenshot 2023-12-16 193031.png]]


We can assign a date to a field using two methods either By ISODate and new Date()
We can specify time also like this new Date(01-11-23T00:11:23)--
I used T to denote time here . the time here is 12:11:23 hr:min:sec.

We can Use Sort and Limit to get the top docs or bottom docs.
Ex- ![[Screenshot 2023-12-18 000513.png]]

Some More Stages Are 
-$project() -- determines the output doc shape. It should be last stage because it specifies what exact field will there be in output. In this both inclusion and exclusion can be done. 
Ex-  ![[Screenshot 2023-12-18 001918.png]]![[Screenshot 2023-12-18 002404.png]]

## -$set()- It adds or modifies existing field. Used When want to modify existing field or create new field which we want to use in upcoming stages.

Though we can add the new value to the new field using the projection but it doesn't store the values for the later use but in the case of set it modifies the existing data .
![[Pasted image 20250217201434.png]]



For Ex- adding a new field Pop_2022![[Screenshot 2023-12-18 002824.png]]

$multiply - used to multiply a single value to given array.
$round - Used to round off the no. to nearest whole no.

-$count() -- It counts the total no. of documents.
It returns single document with only one field which returns the count.
![[Screenshot 2023-12-18 011844.png]]

***$ out*** - It writes the data that are returned by the aggregation pipeline into a collection.
If that collection doesn't exist it creates One . If that already exist it replaces the data in it with this new data.
***It Must be last stage.***

Syntax--
{
$out :
{
db : db name,
coll : collection name
}
}

OR

{
$out :collectionname      //here it takes the aggregated database as its database
}
For Ex-- ![[Screenshot 2023-12-18 013033.png]]

## Using createIndex()--- 

Example -
![[Pasted image 20250217202508.png]]

--it returns Index name.
-- It supports equality matches and range based operation and returns sorted result.
***--There is one index created per collection including default _id***.
--Index comes with a #write-performance_cost.
--when we enter a new data into the document or update the index data in them we have to update the data in index structure. Otherwise we should delete redundant indexes.
--It reduces time as if you have given index and all the field provided . it doesn't need to read it . It will just return the document.

Its like , if we made the index about email and we asked only about index then it will fetch it from the index instead of complete scanning of database.


It means that  
![[Pasted image 20250217203234.png]]![[Pasted image 20250217203300.png]]

--Sometimes due to writing into database , the index may not work properly . So for that we have to recreate index using  the function ,but it takes time and resources so we should run it during less traffic time of our application. 

Though the index are automatically updated but when it becomes too large and slow downs process then we can
```db.collection.reIndex()```


--If we are not sure that index is needed ,we can also hide the index instead of deleting it . Mongo DB doesn't use hidden index but it continues to update its key. 


--It is used because unhiding an index is faster than recreating it.

--when we delete index it also affects the performance of query using that index. 


Of two types- 
***Simple field Index*** - Indexes on single field . 
***Compound Index*** - Indexes more than one field .
In these both we can use the first field name or Index prefix to support the query.
Both supports Multikey Index when operate on the array.
***Multikey***-- when we use an array as a index.
We can only use one array field per index.

Example of Multikey Index -- ![[Pasted image 20250217204421.png]]

Using Unique--
{Unique :1} is specified inside the createIndex() method to specify Uniqueness. So if duplicate data of key specified as index is tried to enter it will raise an error of dup key.
Example if a user tries to enter website with already available email , he will get a duplicate key error.

Using getIndexes() --
it will return the indexes being used.

Using Explain()-- To explain what mongo db is using
To determine if any index is being is used we  use the explain() method explain.
![[Screenshot 2023-12-20 112022.png]]

It returns a winning plan which tells the method it used to operate on query.
It will also tell whether there was any index with that name or not.

Compound index are used to sort or cover queries. These can be single key or multikey.
It support queries which matches the prefix of the index field.
Single key index means in it none is array and another means that at least one is array .

Sort Queries means that if appropriate it uses the order of index to return the result of the query instead of scanning the whole database .
.
For Ex-- ![[Screenshot 2023-12-20 115440.png]]
Here active is the prefix of the index. i.e. Queries **must use the first field(s) in order** to efficiently use the compound index.

![[Screenshot 2023-12-20 115759.png]]
so the order is Equality--Sort--Range

We can use projection function in it too.

it is recommended to place the equality method before sort and range function so to avoid any in-memory sort and filter.

Here this will take time as it has to perform every task by itself.
![[Screenshot 2023-12-20 121231.png]]
But If we create a compound index of it , then It basically have to fetch it.
![[Screenshot 2023-12-20 121251.png]]
![[Screenshot 2023-12-20 121307.png]]

This method leads to the exact document without fetching the document.
That is when you need info of some limited field so you put it in projection inside find . But those field are already available in the index field so it doesn't fetch the document but directly returns the result.

![[Screenshot 2023-12-20 122450.png]]
## Cover a query by the Index

An Index covers a query when MongoDB does not need to fetch the data from memory since all the required data is already returned by the index.

In most cases, we can use projections to return only the required fields and cover the query. Make sure those fields in the projection are in the index.


MongoDb Automatically creates an index on the _id field  by itself .

## using HideIndex function --
db.collectionnname.hideIndex(`<INDEX>`  or key name)
Here in place of index name we can also use the keys .
![[Screenshot 2024-01-10 114449.png]]

##Using dropIndex()--
we can delete the index using the drop index method.
its syntax is  -- db.collection.dropIndex(`<INDEX>` or key name)

if we have to drop multiple index we can use dropIndexs method
If we specify it with no parameter it will delete all the indexes except the _id index. 
However we can also specify single value or an array of values to it.
![[Screenshot 2024-01-10 114822.png]]
 It returns an ok response.

We can use the hide index before deleting index so to check what affect it will have on the performance of the query.


## Relevant Based Search
It is used to return the relevant searches based on search the user has done.

Database indexes are different from search indexes.
![[Screenshot 2024-01-10 121524.png]]         ![[Screenshot 2024-01-10 121534.png]]


The components of search indexing includes
1- The type  of analyzer we are using  -- generally used lucene.standard
2- Whether the mapping is dynamic or not.
3- An option to store the full document in memory for faster post-aggregation performance.  
![[Screenshot 2024-01-10 121755.png]]
Its example is --
![[Screenshot 2024-01-10 122817.png]]
Here company and the employees are the column in the database and how the lucene.standard and lucene.whitespaces differs are ![[Pasted image 20250218180921.png]]
Search index with dynamic mapping--
![[Screenshot 2024-01-12 120720.png]]
when we search a query in a database after creating a search index then  it returns each search with a score which tells how much relevant it is to the data we searched.
we can change the score of searches by adding weights.

The default of mapping of Atlas Search is - Dynamic Mapping.
It indexes all the field present in the collection.
but if we have data of many fields and the user cares about only some field data then we can use relevant search by statically mapping some fields.
It makes search quick and efficent .
It is called Static Indexing.
Here the field being queried are always the same.
Here for this we will use
Field Mapping--
now if we searched something which we haven't put as a field during field mapping it will return no results.
![[Screenshot 2024-01-13 121458.png]]

Here the standard.lucene is an search algorithm made by mongodb .
How to do it through code editor in file aggregation.js
![[Screenshot 2024-01-13 124101.png]]
![[Screenshot 2024-01-13 124129.png]]



### Using $Search and Compound Operators to show most important searches First
![[Pasted image 20250218201719.png]]
Here we determine which index to use and
how much text to show , how many no. of documents should be shown and operations related to the compound operator .


Compound Operators are nested in the search stage and it contains different fields that the search ranking should have .
which are -
must - use only those that match the clause
must not - use only those that dont match to it
should - **give weight** to conditions that should be **considered** but are not mandatory for the document to match
filter - eliminate those search records that dont matches the clause but dont affect the score .
I.e. only must , must not and should contribute to the score and not filter
Ex- 
here the field is habitat and the query or the word is grassland 
Here we are add the score with the value of 5 to those which have the wingspan greater than the 75 and will rank the result higher .
![[Pasted image 20250218203157.png]]
we can see the score of each using below drop down project option .
![[Pasted image 20250218205504.png]]

### Grouping Search Results using Facets 
#Facets are the buckets that that we group our search results into . helps user to find the result faster by suggesting categories where the  user can find their result faster .

The datatypes that we can consider for facets are 
- Numbers
- Dates
- Strings 
Here we need to create field mapping which will be used to create those groupings .

Here instead of using the common search stage in the aggregation pipeline , we use the $searchMeta stage to see the facets and how many results are there in each bucket .
These buckets wont be the part of the search result but of the search metadata which tells how the search result is carried out .

There are 2 pieces of meta data -
The facets and the count .
While creating the facets , first we mention the 
- name of the facet
- type of the facet
- path of the facet
- boundaries that will be used to create the buckets 
- and the name of the bucket where all the data that dont match will go 

And above these we write the query Operator .
![[Pasted image 20250218210932.png]]

***We can't group search result using the $group aggregation stage . For this we have to use the facet operator with the $searchMeta aggregation stage .***

Here the facet contains both field that is the operator and the facet field .



## Data Modelling --

Questions that helps in data modelling -
- What does my application do 
- What data i will store 
- How will users access this data 
- What data will be most valuable to me
![[Pasted image 20250219000206.png]]

***A general Principal of MongoDb is Data that is accessed together . must be stored together*** Becoz otherwise it has both resource cost and the time cost .

MongoDb implements a flexible document data model .
Here #polmorphism means that each doc. in mongodb is different .
However it doesn't means it is schemeless , it is schema flexible that is it encourges to define a schema and perform a schema validation .


Here the key principle is , how our application will use the data rather than how its stored in the database .Rather than how it is stored in the database .
In RDBMS , we get the data req. , make the data model and then pass it to the developer .
In Mongo , we start with application req. and ask how our user will interact with the model . Then we model the data accordingly .

***The purpose of the data modelling is to understand how our data is stored , queried use resource Optimally .***


![[Pasted image 20250219001546.png]]

and the ways to model the relationships are -
- Embedding - when we take related data and insert it into the document , mainly used when we have 1-many or many-many relationship among the stored data .
- Referencing - we refer to documents in another collection in documents.

![[Pasted image 20250219001845.png]]
in mongo , we can have the one-one in a single doc . .
![[Pasted image 20250219001924.png]]
Example - ![[Pasted image 20250219001940.png]]
cast is related to a number of data.
![[Pasted image 20250219002007.png]]



Ex- of Embedding and referencing 
![[Pasted image 20250219002512.png]]


![[Pasted image 20250220201506.png]]


### Embedding Document - 
Embedding avoids application joins that minimizes queries and provide better performance for the read operations .
It also allows dev to update related data in a single write operation .

However some issues related to it are  
- Embedding data into a single doc can create large documents .
- Large docs have to read into memory in full , which can result into a slow application performance for our end user .
- Unbounded Doc . -  we can accidently structure our doc. in such a way that the data is continuously added , without limit . .
- These unbounded doc can exceed the BSON doc threshold max. of 16 MB .
![[Pasted image 20250220202432.png]]



### Referencing Document - 
It saves the _id_ field of one document in another doc as a link between the two .
It is efficient .
Using references is also called Linking orr data Normalization .
![[Pasted image 20250220203115.png]]

![[Pasted image 20250220203227.png]]



#### Scaling a data model 
The mongo Db principle that the data that is accessed together should be stored together is becoz of query pattern that is how we access our data needs to be aligned with our data model .
So it can have optimum efficiency of 
![[Pasted image 20250220203739.png]]


So when we create a data model , we should avoid unbounded document creation i.e. that grows infinitely .
![[Pasted image 20250220204535.png]]
For ex - if we have a collection blog post and has an array comments , here comments can grow infinitely
![[Pasted image 20250220204507.png]]
Instead we can break our data into multiple collections and use references to keep frequently accessed data together .
![[Pasted image 20250220204740.png]]



### Schema --
Schema Design patterns are guidelines that help devs plan , organize  and model data .
Schema anti-patterns results into 
- Sub-Optimal Performance
- Non-Scalable Solutions


![[Pasted image 20250220205222.png]]


In Atlas , can be done through Data Explorer and Performance Advisor .



## Acid Transactions --

These are the group of database operations that will be completed as a unit or not at all Ensuring database safety and consistency .

`A-Atomicity` - All operations succeed or fail  together .
`C-Consistency` - All changes made by database op. are consistent with database constraints .
`I-Isolation`- Multiple trans. happen at the same time , without affecting the outcome of another transaction.
`D-Durability ` - Guarantees the changes made by operations in a transaction are persistent no matter what i.e. even in case of hardware or power failure .

### Single and Multi-Document Acid Transactions --

Single - Documents Operations are already atomic in Mongo DB . Like By default Update One is  and single doc acid transaction .
That if we used this operation , then all the fields mentioned in this will be updated or none .
That No extra operations are req. to ensure atomicity .

The Requirements for the Multi-doc arises from the fact that we can update data in two doc using update One but if we they are related to each other , we have to make sure if they should be updated together to maintain consistency .
Should be used in Special Circumstance .


![[Pasted image 20250221154645.png]]
![[Pasted image 20250221155206.png]]


### Using Transactions in 

Session - A session is similar to the transactions . It is used to group database operations that are related to each other and should be run together .

By default , a transaction has a max runtime of less than one minute after the first write .

Mostly error occurs while transactions when the  transaction time has completed causing the transactions to be aborted  .
![[Pasted image 20250222040014.png]]
Here we are making the object of the account collection , so it will be easy to iterate in this collection .

Using here update One , so has to pass both the query doc as well as the mutation document .

Now we will use `session.commitTransaction()` , to commit all the transactions at the same time .

However if we haven't committed and if we have to abort transaction , we can use `session.abortTransaction()` .

