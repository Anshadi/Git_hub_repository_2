
![[Pasted image 20250303143348.png]]


Data Modelling in MongoDb involves 3 design phases :
- Workload
- Relationships
- Patterns

Relationship Types -
- 1-1
- 1-M
- M-M

Embedding & Referencing -
In Embedding both doc is in the same collection and in referencing both the doc are in separate documents .
![[Pasted image 20250222132338.png]]

These are the points that has to be keep in points while deciding whether we have to embed documents or to make them referencing .

![[Pasted image 20250222132525.png]]
![[Pasted image 20250222132547.png]]


![[Pasted image 20250222132603.png]]

![[Pasted image 20250222132659.png]]
![[Pasted image 20250222132728.png]]

![[Pasted image 20250222132803.png]]

![[Pasted image 20250222132839.png]]

![[Pasted image 20250222132902.png]]

![[Pasted image 20250222132915.png]]
![[Pasted image 20250222133014.png]]

![[Pasted image 20250222133029.png]]

SO As here we took the example of the author and the book so the total score was more for the embedding hence we embed the documents .
Though there were only 2 in favor of referencing here , while designing we should also see the priority of each guidelines in relation to our application .

In One to Many , we embed the many side as an array of documents in the one side .
![[Pasted image 20250222135259.png]]
Another way is to create a one subdocument within the parent document .![[Pasted image 20250222135440.png]]

The advantages of it is -
There is No duplication of the information .
Also good when the info at the many side can not exist by themselves . Like a review without the book .

Now using references , 
- The first way is to use an array of the references within the parent document .![[Pasted image 20250222135750.png]]
- The second way is to reference the parent in each of the many child document .
![[Pasted image 20250222135959.png]]
- We can also have bi-directional references .
![[Pasted image 20250222140016.png]]

When to decide whether we embed or reference the document , embedding the array of sub-documents is preferred .

By Using arrays to hold related data , Our application can hold retrieve information for a document without `$Lookup` operations or `indexes` on other collections .

But we should also remember the cardinality guideline (here it becomes the priority) , that for ex- if the reviews in a book becomes an unbounded array , it may degrade the performance , *** so in this case referencing would be better*** .



In case of Many to Many - 
In  Embedding -
- Here we embed the document from the child side using an array in parent document .
However unlike 1-M , we have to embed the children in Each Parent Document .
So here data duplications happens , however not all data duplication is bad , it allows for faster query processing but we should decide to what level data duplication we want .
 - Another way is single sub-doc with multiple key-value pairs . here each child in sub-doc has a key which can be used to access the sub-doc .
![[Pasted image 20250222213713.png]]

In Referencing -
![[Pasted image 20250222213810.png]]
here we see data duplication , as the more than one time the author id is stored in the books
![[Pasted image 20250222213915.png]]
We can also reference in the opposite direction as shown above .

Here bi-directional can be created but not encouraged .


### Schema --

#### 1) The Polymorphic Pattern -
***Inheritance -*** It is based on the polymorphism . where single documents can have different forms .![[Pasted image 20250222230226.png]]
We should use this when we have more similarities than differences . and we keep them in the same collection so they can be read together .

We can use the aggregation framework to apply the inheritance pattern to large numbers of document . or an entire collection .

For Ex- If we have these books ![[Pasted image 20250223100112.png]]
![[Pasted image 20250223100145.png]]
![[Pasted image 20250223100946.png]]


Now on applying inheritance pipeline  on them - 
![[Pasted image 20250223101257.png]]
![[Pasted image 20250223101342.png]]
![[Pasted image 20250223101358.png]]

Now here we left the product type field with an unspecified value . So Now we will apply the second pipeline to finish applying the pattern .
![[Pasted image 20250223204250.png]]
Same way we will create the pipeline for the printed books and the ebooks .

Now we can see the result --![[Pasted image 20250223204328.png]]



#### 2) Computed Pattern -

Now if we have to compute the average of the rating , we have to compute the same operations frequently . So we can solve this problem using the #Computed_Pattern .
It allows us to run expensive operations when the data changes And Store the result for Quick Access .

Common Computations -
- Mathematical 
- Roll-Up

In Mathematical - It is used to precompute the result when the data  is written , instead of running the calculation each time .

In this case , when we have to calculate the review we have to calculate everything 
![[Pasted image 20250228155924.png]]

But Now as i have Added the rating document as the sub - document , Now whenever the new ratings comes in , we just have to multiply the old rating with the old review count and then add the new rating to the old rating and then divide it by (Review_Count+1) .

![[Pasted image 20250228155857.png]]


Now ***Roll-Up***
It Involves merging data in whole .
It involves grouping category from the smaller one to the larger ones .

![[Pasted image 20250228160730.png]]

Here we used the #size Operator to get the size of the author from the authors array , and then we will use #age operator to compute the average .


Hence the Rollup helps in 
![[Pasted image 20250228160813.png]]
![[Pasted image 20250228164521.png]]



#### 3) Approximation Pattern -

This pattern generates Statistically Valid number that is not exact .
- We use this pattern when data is either expensive or difficult to calculate .
- When getting the exact number is not critical for a use case .
- Also suitable for working with big data

It Is Helpful in 
- Reduces Write Operation 
- Reduces Contention on Heavily Updated Documents  .

Now likewise , if we have to calculate the ratings , the Computational Pattern will do it , but it will double the number of database writes .
But In case the ratings are less , we want to get the exact average , but if the no. of reviews are very large , we don't need to get the exact data .

***So what we can do is , if application sees that the product has received a significant amount of reviews then it can switch to calculating the review from the Immediate to the periodically .***

This can drastically reduce the database writes in a trade-off with a bit of accuracy .
So here we can use the random number generator , that is every time a review is generated , with it a random number is also generated between 1-10 , and when this no. is 10 then we can compute the new average . 
However there is an Exception - 
But now we add the count of no. of reviews by 10 and also we multiply the new review before adding them to the old review .
This Approximation Pattern reduces the no. of database writes by 90% .

Now this doesn't effect the document model , here all the processing happens at the Application Side .


#### 4) Extended Reference Pattern -

In Rdbms , if we have to get the data from the various documents based on the matching of the single key , we were using the `Join Operation` . However in MongoDb We use 
`$Lookup aggregation operation` .
But  this can resource intensive like join , but now we can apply #Extended_Reference_Pattern .

![[Pasted image 20250301150537.png]]

- It Helps avoiding joining too much data in a query .

Its Main Objective is -
- To Reduce the Latency of Read Operations 
- Avoid Round Trips to the Database
- Avoid Touching Too many pieces of the data .

So it will do 
- Faster reads
- Reduced Number of Joins And Lookups 

![[Pasted image 20250301151013.png]]

Here , we will embed the book product type and title in the review document .

![[Pasted image 20250301151027.png]]

Now as we will also need to know who wrote the review , we will include the user id and name in the review sub-document in the review sub document .
![[Pasted image 20250301151321.png]]


Now we get all the information in one place , However it also creates the issue of the data duplication . 

So the rule of the #Extended_Reference_Pattern is ![[Pasted image 20250301151512.png]]
And due to this , we will also have to manage the duplicated data when the source is updated .

- `So we have to decide what are the list of dependents External References .`

- `Next is whether the External references is needed to be Updated Immediately or they can be Updated Later time .`

In Most case , the answer is no , as changing the ranking of the best selling of our book doesn't need to update the ranking of all the books in our app .


If we have to transform the given data then -
![[Pasted image 20250301152128.png]]

![[Pasted image 20250301152303.png]]


![[Pasted image 20250301152321.png]]![[Pasted image 20250301152437.png]]



So Now our new document will be formed -
![[Pasted image 20250301152534.png]]

Now we will use the merge stage to merge the output to the existing review document .

![[Pasted image 20250301152638.png]]



Now our data will be like this ![[Pasted image 20250301153211.png]]



#### 5) Schema Versioning Pattern 

***MongoDb is a Schema-Flexible Documents .***

MongoDb helps changing the schema of the data with no downtime .

- Documents with different shapes and the schema version can exist within the same Collection 
- As they exist in the same collection , we have the flexibility on when and how we apply it so that we can avoid the downtime .
- The absence of a schema version is by default Schema 1 .

***MongoDb has the ability of coexist of Multiple Schema is in contrast with the Rdbms where only one schema exists .***

In MongoDb , we can increase the no. of schemas .
Every Document in MongoDb can have unique Schema Version . And Therefore unique shape .

In reality , we have more  than couple schema versions while our app is in transition .
![[Pasted image 20250301154921.png]]

***We have to keep in mind that we must first update the application before updating the schema so it is able to read both the old and the new schema .***


Schema Migration -
![[Pasted image 20250301155417.png]]

1) we update the app server 
2) we run the background task to update all the documents in a single pass 
3) while it is happening our app can handle both the versions .
4) We can also update all the document in the many passes . 
5) In the end , we can remove the code of the old versions .

It does the migration in Zero-Downtime . 
It helps us to choose when we have to migrate .

So To Implement this , we have to add a field tell the schema version  in each document . 

![[Pasted image 20250301160011.png]]




### Schema Design Anti-Pattern -

#### 1) Unbounded Array Anti-Pattern --

Unbounded Array is an common Anti-Pattern .
![[Pasted image 20250301161935.png]]


1) High Cardinality Arrays Should Not be Embedded .

There are two schema design that can help us with Unbounded Array -

- ***Extended Reference Pattern-*** Allows us to embed data from multiple docs and diff. collections into the main document .
- ***Subset Pattern -*** Reducing size by relocating data that isn't frequently accessed .

Both considers the size of the documents .

And their usage depends on the requirement .

So in subset pattern what we do is 
suppose we have initially books and their reviews into a single collection , then we can move the reviews into a separate collection . 
Then we then store few frequently accessed data with the book data .

Though it will create some data duplication ,
- it will eliminate the Unbounded array 
- helpful in avoiding using multiple queries or lookup operations
- Will keep frequently accessed data together 

This a good solution , however it may not be the best solution in every case .




#### 2) ***Bloated Documents Anti-Pattern --***

***Conclusion*** - So it is the document that groups together related data that is accessed separately . 

It occurs when a MongoDB document **keeps growing in size as new fields are added over time**, rather than Us restructuring the schema or normalizing the data. 
- This leads to **large, inefficient documents** that degrade performance, increase memory usage, and slow down queries .


***Though the moto is - Data that is accessed together must be stored together But it doesn't means that all the related data must be stored together .***


#Wired_Tiger (WT)- MongoDb Storage Engine 
To keep queries running quickly , keeps data that is accessed together frequently in memory .
This memory set is known as Wired Tiger Memory Set .

So the documents and indexes frequently used by the application should be fitted into the wired tiger cache set .
If the size of the working set increases than the cache set , then the database performance is impacted .

***WT Internal Cache Size is -
50% of the Ram - 1GB 
or 256 Mb
whichever is bigger 
***

To get this data , we can also use this commands in the mongosh 

```
db.collection.stats().count
db.collection.stats().avgSize

these doesn't includes indexes and other data 
```

To solve this , we can do two things - 
- We can increase our cluster size or
- We can update our data model , to use existing memory efficiently 

![[Pasted image 20250301182707.png]]
***This would be a bloated doc. if we are storing the data that is accessed seperataly in one document .***
that if the only first two fields are accessed by the homepage and the complete details are used by later on the Details page data .


Hence , we can store this data in two separate documents -
![[Pasted image 20250301182946.png]]


So now we have two docs , and majority of the doc that is the details is not accessed frequently , so the review doc is accessed frequently which is very less in size and fits in our Wired Tiger Internal Cache Set .


#### 3) Massive Numbers of Collection Anti-Pattern -

The Recommended Limit for the number of collections in a replica Set is ***10,000*** .
but Practically it depends on our workload and Database Resources .

Having too many collection slows down the performance , it is because the mongoDb default storage engine creates a separate file for each collection and each index .
And every collection in mongoDb has atleast one index file .

So in performance degradation happens when it cross 
In M10 cluster - >5,000 collections
In M20 or M30 -  > 10,000 collections

![[Pasted image 20250301233212.png]]

Shared Clusters on the other  hand handles 10,000 collections per shard .


One way to mitigate this anti-pattern is to drop or archive Unused Collection .
And if the problem still exists then we should remodel our data .

Sharding our database is also a solution for this problem .

For ex - if we created a collection per user view but it was a problem as it leads to this anti-pattern , so we put all the collection into a single views collection . 
Which will solve this problem .
![[Pasted image 20250301233704.png]]

![[Pasted image 20250301233730.png]]



#### 4) Unnecessary Indexes Anti-Pattern-

As we assume that , we have to create a index for every query , this can result into unnecessary indexes .

The limit for the number of Indexes per collection is ***64*** . So we should use as least no. of indexes as possible .

Becoz Indexes need 
- Requires Space
- When used they are loaded in memory
- Grows as docs are added 
- Negative impact on write performance as indexes must be updated every time the documents are created , updated or deleted .


![[Pasted image 20250302115204.png]]



One thing to remember is that the index on the _id_ is  required and can not be dropped .

we can use the method `hideIndex()` to see what the impact of it will happen on our database performance .

or we can also use `.dropIndex()` .



#### 5) Data Normalization Anti-Pattern-

When we are modelling data , it may seem good to normalize the data(i.e. split it ) but  accessing data that is stored seperataly requires multiple queries  or we use mongo's $lookup operation to retrieve it .

This is expensive and impact write performance negatively , when happens frequently , it is known as Data Normalization Anti-Pattern .

We can solve this problem by leveraging -
- Subset Pattern 
- Extended Reference Pattern 

For Ex- In our bookstore , we created separate docs for books and reviews , but since they are related , they are accessed together . or otherwise will need multiple operations .

So we can use 
Subset Pattern - Useful for the elements which could become very long like - reviews , comments etc.
So for this , we stored a subset of review document in the books document .
![[Pasted image 20250302153604.png]]

But if we are making a reviews page where we will need all the reviews with reference of the some of the fields of the book document , then we can use 
Extended Reference Pattern - To embed the book data that we need in each review document .

![[Pasted image 20250302153828.png]]

Now these require only two field so less duplication and the fields wont change in the future so they will be requiring no updation in the future . 
However we don't need multiple queries .


### 6) Case Sensitivity Anti-Pattern-

Conclusion - Case Insensitive queries must use Case Insensitivity Index 

Now days the search bar is case insensitive , but before to find something , we had to use the right capitalization .

Mongo Db queries are case sensitive by default .
![[Pasted image 20250302165246.png]]

Now to support Case insensitivity in search query , we can use collation .
Collations have also the comparison level or strength from 1-5 
![[Pasted image 20250302171047.png]]
The default level is 3 which makes comparison case sensitive .
If we want the search to be case insensitive , we want the  strength to be 1 or 2 .

The value of one compares base charachters only , ignoring diacritics , accents and case . Includes all 4 words voila .
The value of 2 includes the secondary differences and diacritics .

![[Pasted image 20250302180300.png]]

There are many ways in which we can ensure the query performs case insensitive search through collation .

- By ensuring that the index and query have the same case insensitive collation .
- By assigning a default collation when we create a collection . (Not Recommended)
	- Default collation applied to a collection , is applied to all the indexes of that collection . This collation can't be changed once the collection has been created .
	- Any Queries on the collection , will  also use the same collation .

We can override the collation at the query level only .
![[Pasted image 20250302180945.png]]

We can also use regex operator with the i option . However it is not recommended as they are not that much efficient when the search terms are case insensitive . 

**When we write our query it must use the same collation and the locale as our collection or otherwise it will face the case insensitivity anti - pattern .**

![[Pasted image 20250302181731.png]]

So one thing more that if we have not defined the strength in the index while its creation and the time , we are querying that index but with the collation strength , it won't use that index rather it will perform the whole collection scan .![[Pasted image 20250302182618.png]]
![[Pasted image 20250302182556.png]]


It is another form of Case InSensitivity(CI) Anti-Pattern .  where a CI query is missing a CI index to support it .
So as the solution ,we have to drop the index and create it with that strength .
![[Pasted image 20250302182943.png]]



### Advanced Schema Design -

#### 1) Single Collection Pattern (Scp) -
 
 - It groups related docs of different types into a single collection .
- It Avoids Multiple queries to read non-embedded related docs 
- It avoids expensive lookup operations

It Specifically useful for -
- Modelling Many-Many Relationships , where we are concerned about data duplication and embedding isn't a good option .
- Modelling 1-M Relationships 
- Real World Ex - Catalog of Product Items and shopping Cart 

The Scp has two variants , one that is applied on the 1-M Relationships and another that is implemented on the M-M Relationships .

![[Pasted image 20250302215239.png]]

![[Pasted image 20250302215813.png]]

![[Pasted image 20250302215832.png]]


The ***Variant 1*** Uses 
- Array of references 
- doc Type field
- can model both 1-m and m-m relationships

Now for ex- if we have 3 docs 
![[Pasted image 20250302220151.png]]

to make use of Scp here , we will add a docType field to it .
Then we add the related to field - this will help model the relationships between document and the  new collection using an array of references .

All the docs. in the example are related to the same book id and the book document points to itself .

![[Pasted image 20250302220543.png]]

Now to complete the implementation of the Scp , we have to create an index on the related To field to support our application queries .

![[Pasted image 20250302220706.png]]

Now , we can easily find all the documents related to a book or a specific doc type in it like reviews .



The ***Variant 2*** Uses -

It Uses the Overloaded Field - An field is overloaded when it is used for another purpose than its intended purpose .

This variant is used to model 1-M relationships only .

Now suppose we have this ![[Pasted image 20250302221239.png]]
Then  to apply second variant on it , we will add 
single collection id field , where in the book , it will have book id but in the review , it will have book id / review id  . Hence the overloaded field .

![[Pasted image 20250302221455.png]]
And now we will create an index on this sc_id field .


Now if we have to query for only the book id then 
![[Pasted image 20250302221619.png]]

Now for only the related review - 

![[Pasted image 20250302221659.png]]

![[Pasted image 20250302222237.png]]



![[Pasted image 20250302222738.png]]

So in case of M-M , Join Table method wins .


#### 2) Subset Schema Design Pattern -

It is used to Improve performance .

For Ex - if a book has 100s of reviews and we are showing only 3 of them then the rest is taking up the memory and causing performance degradation .
Becoz in such cases the working set exceeds the Internal cache size of the wired Tiger .

This pattern reduces doc sizes by relocating data that is not frequently accessed .
![[Pasted image 20250303013057.png]]

So in this example , where initially all the reviews were in the books doc , we relocated it to a separate collection and then we embedded 3 reviews in the book doc .( duplicating only 3 reviews  )

![[Pasted image 20250303013241.png]]

It helps in ![[Pasted image 20250303013325.png]] 

So now to implement we are going to apply two pipelines - Subset and cleanup pipeline 
![[Pasted image 20250303013727.png]]

Here we used the `unwind stage` to deconstruct the review array and then created a new doc for each review . 
However , each of this book doc will contain the product detail from the book doc .
Since we only need the product id for its reference in the review doc , we will add this field using the `set stage `.
![[Pasted image 20250303014249.png]]

Then we will use the `Replace Root stage ` to promote the embedded review doc to the top .![[Pasted image 20250303014413.png]]

now each of the review doc contains only product id . Now we will use out stage which will write our output to the collection we mention and overwrite anything if already written . So ensuring nothing is written .
![[Pasted image 20250303014615.png]]


Now inside book doc , we have to only include 3 docs , which we can do by this 
![[Pasted image 20250303014728.png]]

As previously it created a new collection review where it copied the output , but it didn't affected the original doc book , so we needed to do this .

Now to apply on all of them , we will run this on Update many method , {} here it means applied to all ![[Pasted image 20250303015013.png]]
![[Pasted image 20250303015054.png]]


#### 3) Bucket Design Pattern  --
If we want to create a 1-M relationship but the many side data could be very large and can result into an unbounded array , then we can't
- embed docs here and 
- referencing here will lead to poor performance due to large indexes and increased query complexity.

Then here , we can use Bucket pattern where none of the pure embed or reference model is a great choice .

It Groups pieces of info into the buckets , so that the doc size is more predictable and easier to work with .

Such in the case of the sensors which generate never ending stream data i.e. #Time_Series_data, in such cases we can use bucket pattern .


So if we can store each stream data separatly and then combine these data into bucket in a meaningful way , it can help us .

This pattern is mainly used with other computed pattern to store the pre-calculated statics in the bucket making the performance better .

To Implement this we should 
-  Consider how data will be queried 
- Choose a level of granularity 
-

![[Pasted image 20250303020312.png]]

Suppose if we want to get the views per book per month and store them in Views collection and each view has 3 things -
- Timestamp 
- User Id 
- Book Id 


Now for the the creation of the bucket , we have to have a field id in it where it will have 
- Book Id
- Month 

Now we will create a views array in it containing info about the views .

![[Pasted image 20250303143515.png]]


we should avoid unbounded array as we can get millions of views in each month .
For this , we can use a schema validation tool or we can also in the application logic specify a threshold on number of views per bucket .

So when a bucket doc reaches that threshold , a new bucket doc is created .

Both pattern ensures that we have a predictable data size .

![[Pasted image 20250303143950.png]]



#### 4) Outlier Design Pattern --

After our app has been public for a while , we find usage patterns that are not that well covered .
For Ex - For reviews we added a comment array in its doc , but some controversial review can have 1000s of comments which can cause unbounded array that is it exceeds 16MB doc size limit .

The Outlier Pattern allows us to solve this issue as it allows to treat a few docs with unusual characteristics differently . 

This Pattern is mainly used when - 
- Some docs in our database is sufficiently different that they req. special handling 
- But optimizing our entire app for this edge cases would degrade performance .
- Commonly seen its usage in eCommerce website or in Social Media 

Like in our case , instead of changing the complete model for all the reviews , we can Implement this pattern to identify the reviews that have 100s of comments and  
Once this pattern is implemented ,then  we can implement a solution that handles review outliers differently than the rest of the data .

So for this , we have to have a field threshold , which will tell when it is crossed it will be outliers .
Then we will have the outliers field which will become true when it crosses the threshold value .

Once our review doc is flagged as outlier , our application will store those review in another collection .
![[Pasted image 20250303150709.png]]
We can use Update Many to implement this outliers pattern . By Implementing this , we set the threshold  for the  review document and in those docs , we set the outliers as true .


Now review docs that contains more than 3 reviews will be considered as outliers .




Next time , the  review recieves a comment , we can move the comment to a separate place , we can also run a separate query on this . to move them to a different collection .

And finally , we can use the bucket pattern to handle outliers by storing any excess comments in the buckets .


![[Pasted image 20250303153308.png]]


#### 5) Archive Design Pattern -

It helps to move data that is no longer or rarely used outside of our database . To improve performance and primarily reduce storage requirements . 

We could need to store older data due to data compliances such as pharmaceuticals , Banking or old logs . Which could add up .

Active collections with a large no. of inactive documents can impact performance for a no. of reasons - 
- Write operations must update all indexes on the affected collections and large indexes can waste memory . 
- And Inactive docs takes up space in our primary storage which is very expensive .

Here this Archive Pattern is used to move these inactive docs out of our main database .

Now the steps to implement this pattern is 

For Ex-  if we want to move review that are more than a year old and haven't recieved any upvote , we can do this by 
- We can copy the source doc to the archive 
- But if the source doc contains references then it will be difficult . as we have to recreate complete document hierarchy .
- Here , we can use Extended Reference Pattern to enhance archive doc with the referenced data .

![[Pasted image 20250303162304.png]]

Though we can keep the field of the archive docs as same but we add some field for making the query work easier .
![[Pasted image 20250303162508.png]]

Now we need to find an appropriate storage service for this data , we can use here mongo Db online archive storage if our database is running on cluster M10 or Above .

Now the final step would be defining a schedule for archiving and deleting documents .
To stream Line our performance , it is recommended to archive and delete data frequently .


### Schema Lifecycle Management -- 

#### Schema Validation --

This helps in ensuring that only those documents that matches the desired shape and schema are added to the collection .

Its Components are - 
- Validation Rules 
- Validation Levels
- Validation Actions 


***Validation Rules*** --
In mongo , docs of different shapes can co-exist and their structure can be modified at any time .

Schema Validation ensures that there is no unintended Schema or improper data types and values .

For Ex- if we want the name field to be string and the user is entering it in another form , then through schema validation we can ensure that it doesn't enter the database . 

Schema acts as a contract between the database users because schema changes can not be made unless all stakeholders  agree . 

Mongo Schema Validation lets us create validation rules and gives us control over what is written to the database .
We can define these rules when we are creating a new collection , so then when inserted or updated in this new collection , mongo will update this .

If we added validation rules to an existing collection , those rules will be applicable to only to new documents that are inserted . Doc that are in the collection are only validated when they are updated .


To define Schema Validation rules , we can use 
- Query Operators 
- Json Schema Standard -- used to validate json schema using `$jsonSchema` by creating validation rules for it .


![[Pasted image 20250303204507.png]]

Here we are using both query operator and the json schema operator telling that the items field must be an array and the price of discounted price must be less than the price field .

Mongo offers validation level options - to control the enforcement .
Only two options -

![[Pasted image 20250303204809.png]]


In MongoDB, if you define **schema validation rules** on a collection **after** inserting documents, the existing documents are **not** checked for validity against the newly defined rules. This means that **invalid documents remain in the collection** unless explicitly updated.


Now to find the Invalid docs , we can use the find query with `$nor` operator . to find the docs that don't match the schema .
![[Pasted image 20250303205145.png]]



***Validation Action*** --
It tells what happens when the docs fails the validation .

It also has two options -

![[Pasted image 20250303205351.png]]

The default Action is to return an error .


![[Pasted image 20250303205504.png]]
For Ex - 
- Here in the validation , we have optional field bsonType , so either it shouldn't be set or should be set to Object type .
- Then we have mentioned some fields inside the required array , which means these fields are req. to be in the doc 
- Then we have `false` the Additional Properties , which means any other field than the required field will result in the  Validation Error .
- Then the properties doc , which includes the required bson type for each field , we want to validate . That is for each field in the req. array .
- We also used Keywords like `maximum` and `minimum` to define the range of the ratings .
- We used the max Item to limit the size of the comment array to 3 .
- This way we can avoid the unbounded array .

![[Pasted image 20250303210144.png]]

Since , we are modifying the existing collection , we will use the `collMod` command to enable schema validation . for the reviews collection .

We will give the Schema Validation as the validator document .
We have set the level of the validation to strict and validation Action to error .


#### Schema Evolution --

As application evolves database schema needs to be changed . The structure of schema will change with time , whether we are introducing new features , fixing bugs or applying new patterns . This process is known as ***Schema Evolution .***

Sometimes these changes are the ***part of the planned update*** , where all the stakeholders are known about this update .

Sometime , the changes could be ***Ad-hoc*** .  That is after the initial Schema design phase and during development . 
For ex- for fixing a bug , we might need to change the schema . Here may not be all the stakeholders involved .

Ad - hoc can be very challenging if , we are working with a rigid schema .

If we have to apply schema validation on the new docs - 
![[Pasted image 20250303213534.png]]
We used here the #enum field to specify only the allowed value in the field .

![[Pasted image 20250303213544.png]]

![[Pasted image 20250303213628.png]]
![[Pasted image 20250303214043.png]]

Now after this - 
Monitoring Schema Evolution -
- To Identify the Schema design Issues
- To Improving Data quality 
- For Optimizing Performance 

Monitoring logs is must if the validation action is set to warn , In Mongo , we have Atlas Schema Suggestion  (ASS) , which automatically monitors and gives suggestion , if we are working on the M10 or Above Cluster .

What ASS provides is -
![[Pasted image 20250303214507.png]]



#### Schema Migration - 

There are trade-offs and complexities associated with the schema migration .

***Transitioning from One Schema to the next is known as Schema Migration .***

Mongo's flexible doc model allows smooth schema migration in Zero Down-time .

- Versioning is the common example of the Enabling Controlled Migration .
- Maintaining Large no. of versions create complexities . So we should limit the no. of the schema versions to the min. required .

Levarging the Schema versioning pattern  and the Schema Validation while maintaining backward compatibility for migrations without downtime .


Once we are ready to migrate to our new Schema , we can do this in many ways - such as 
- Eager Migration - All At Once .
- Lazy Migration - Where changes are implemented as data is used 
- Incremental Migration - Where we take small steps to Implement the Migration 
- Predictive Migration - Update the Schema based on the prediction of how data will be used in the future .



To Enforce the Schema if there is more than One Version , we can use 
`OneOf` Keyword when we define the schema validation rules for the reviews collection .

![[Pasted image 20250304033719.png]]
This default Schema doesn't require any changes .
But had to implement Schema Versioning Pattern on this new Schema .![[Pasted image 20250304033823.png]]Or On any future version of Schema that we wish to add to the Reviews Collection .

Here we added the `Schema version ` to the required Array and to the properties field .
Used the Enum keyword to list the allowed values for the field  . Allows to specify how many schema version , we want to support .




Now one Of Keyword , it required the doc to be valid against exactly one of the included Schemas and not both .
And In each of the schema , we have done additional Properties to false , so that they must only use the field mentioned in them .

Now we can supply this doc to our validator and as we are in production , the  validation level is strict and the validation Action is error .![[Pasted image 20250304034400.png]]

So now our docs will be lazily migrated , that is changed as we will use it .
 
