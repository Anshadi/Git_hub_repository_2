It can be used if the read and write is being done more in the permanent or persistent database  .
It is used for cache and is a In-Memory Database  .

Redis  - Remote Directory server
- In-Memory Data Structure Store Used as a database , cache and Message broker .
- Used Mostly in case of Real time data read or write 
- Provides for the option of permanent storage .

![[Pasted image 20241105141057.png]]

Here the data Bytes are stored in the In-Memory rather than in the disk as it happens with the case of Mysql or other databases  . So it fastens the speed of read and write  .

Ex- So like i can use the Redis for the real time tracking , and when we get the final source and destination location , we can store it in our mysql database , so we can later on show it if required .
As it provides the option to finally dump the data in the disk also  .


![[Pasted image 20241105141851.png]]


All the operations are done on the Radis Server , But the user can't access the Radis Server .
So we have client which queries the Radis server

We can access the Radis through this client .
Here the GUI of the Radis can act as the client as well as our java or spring boot application 


Now how to run this in docker --
```
docker pull redis
docker run --name my-redis -d redis

docker rm my-redis
docker start my-redis

docker inspect my-redis
docker stop my-redis 

docker exec -it my-redis redis-cli
docker run --name my-redis -d -p 6379:6379 redis

```

Inside client now when i will type `Ping ` , I should get the result  pong .
![[Pasted image 20241106121345.png]]

![[Pasted image 20241106122010.png]]


##### Redis Data Types --


###### **String**

Here are the key Redis string commands, each with a brief description:

1. **`SET key value`** – Set a string value for a key.
2. **`GET key`** – Retrieve the value of a key.
3. **`APPEND key value`** – Append a value to an existing key's value.
4. **`STRLEN key`** – Get the length of the value stored at a key.
5. **`SETEX key seconds value`** – Set a key with a value and an expiration time (in seconds).
6. **`SETNX key value`** – Set a value only if the key does not already exist.
7. **`GETSET key value`** – Set a new value and return the old value.
8. **`MSET key value [key value ...]`** – Set multiple keys to multiple values.
9. **`MGET key [key ...]`** – Get the values of multiple keys.
10. **`INCR key`** – Increment the integer value of a key by one.
11. **`INCRBY key increment`** – Increment the integer value by a specified amount.
12. **`DECR key`** – Decrement the integer value of a key by one.
13. **`DECRBY key decrement`** – Decrement the integer value by a specified amount.
14. **`INCRBYFLOAT key increment`** – Increment the float value of a key by a specified amount.
15. **`GETRANGE key start end`** – Get a substring of the value at a key.
16. **`SETRANGE key offset value`** – Overwrite part of a string starting at a specified offset.
17. **`BITCOUNT key [start end]`** – Count the number of set bits (1s) in a string.
18. **`BITOP operation destkey key [key ...]`** – Perform bitwise operations (AND, OR, XOR, NOT) on strings.

**Redis strings** are versatile and allow for setting, getting, modifying, and incrementing/decrementing string and numeric values, making them ideal for a wide range of caching and simple data storage tasks.\


###### Hashes -
It is best to store the objects  .

Here are the main Redis hash commands, each with a brief description:

1. **`HSET key field value`** – Set a field in a hash to a specific value.
2. **`HGET key field`** – Retrieve the value of a field in a hash.
3. **`HMSET key field value [field value ...]`** – Set multiple fields in a hash (deprecated in favor of `HSET`).
4. **`HMGET key field [field ...]`** – Get values of multiple fields in a hash.
5. **`HGETALL key`** – Retrieve all fields and values in a hash.
6. **`HDEL key field [field ...]`** – Delete one or more fields in a hash.
7. **`HEXISTS key field`** – Check if a field exists in a hash.
8. **`HLEN key`** – Get the number of fields in a hash.
9. **`HINCRBY key field increment`** – Increment the integer value of a field by a specified amount.
10. **`HINCRBYFLOAT key field increment`** – Increment the float value of a field by a specified amount.
11. **`HKEYS key`** – Get all field names in a hash.
12. **`HVALS key`** – Get all values in a hash.
13. **`HSTRLEN key field`** – Get the length of the value of a field in a hash.

**Redis hashes** are ideal for storing and retrieving key-value pairs where each entry has multiple fields (like a small record or document), making them efficient for managing semi-structured data like user profiles or settings.

![[Pasted image 20241106175935.png]]

![[Pasted image 20241106181918.png]]
###### List-
For making list 
Here are the key Redis list commands, each with a brief description:

1. **`LPUSH key value [value ...]`** – Insert values at the head (left side) of the list.
2. **`RPUSH key value [value ...]`** – Insert values at the tail (right side) of the list.
3. **`LPOP key`** – Remove and return the first element of the list.
4. **`RPOP key`** – Remove and return the last element of the list.
5. **`LLEN key`** – Get the length of the list.
6. **`LRANGE key start stop`** – Get a range of elements from the list.
7. **`LINDEX key index`** – Get an element by its index in the list.
8. **`LSET key index value`** – Set the value of an element at a specific index.
9. **`LREM key count value`** – Remove elements from the list that match a value, up to `count` times.
10. **`LTRIM key start stop`** – Trim the list to only keep elements within a specified range.
11. **`RPOPLPUSH source destination`** – Remove the last element from one list and push it to the start of another list.
12. **`BLPOP key [key ...] timeout`** – Remove and get the first element in a list, or block until one is available.
13. **`BRPOP key [key ...] timeout`** – Remove and get the last element in a list, or block until one is available.
14. **`BRPOPLPUSH source destination timeout`** – Pop the last element from one list and push it to the start of another, or block until an element is available.

**Redis lists** are ordered collections of strings, providing versatile commands for queue-like (FIFO) or stack-like (LIFO) operations, making them well-suited for managing sequences or jobs in a queue.

![[Pasted image 20241106181940.png]]

Here lpush is used so , the first one will be on the last and the last one will be on the  first .


Here are the commands for **sets**, **sorted sets**, and **bitmaps** in Redis:

---

###### **Set Commands**
1. **`SADD key member [member ...]`** – Add one or more members to a set.
2. **`SREM key member [member ...]`** – Remove one or more members from a set.
3. **`SISMEMBER key member`** – Check if a member exists in the set.
4. **`SMEMBERS key`** – Retrieve all members in the set.
5. **`SCARD key`** – Get the number of members in the set.
6. **`SPOP key [count]`** – Remove and return one or multiple random members from the set.
7. **`SRANDMEMBER key [count]`** – Get one or multiple random members from the set without removing them.
8. **`SDIFF key [key ...]`** – Subtract multiple sets (returns members present in the first set but not in others).
9. **`SINTER key [key ...]`** – Intersect multiple sets (returns members present in all specified sets).
10. **`SUNION key [key ...]`** – Union multiple sets (returns all members present in any of the specified sets).
11. **`SMOVE source destination member`** – Move a member from one set to another.

**Redis sets** store unique, unordered elements and support operations like unions, intersections, and differences, making them ideal for managing distinct collections (e.g., tags, unique visitors).

---

###### **Sorted Set Commands**
1. **`ZADD key score member [score member ...]`** – Add one or more members with a score to a sorted set.
2. **`ZREM key member [member ...]`** – Remove one or more members from a sorted set.
3. **`ZSCORE key member`** – Get the score associated with a member.
4. **`ZRANGE key start stop [WITHSCORES]`** – Get a range of members by index, optionally including scores.
5. **`ZREVRANGE key start stop [WITHSCORES]`** – Get a range of members in reverse order, optionally with scores.
6. **`ZRANK key member`** – Get the rank (index) of a member in ascending order.
7. **`ZREVRANK key member`** – Get the rank of a member in descending order.
8. **`ZRANGEBYSCORE key min max [WITHSCORES]`** – Get members within a score range, optionally with scores.
9. **`ZREMRANGEBYRANK key start stop`** – Remove members within a rank range.
10. **`ZREMRANGEBYSCORE key min max`** – Remove members within a score range.
11. **`ZCARD key`** – Get the number of members in a sorted set.
12. **`ZINCRBY key increment member`** – Increment the score of a member by a given value.
13. **`ZUNIONSTORE destination numkeys key [key ...]`** – Store the union of multiple sorted sets into a new key.
14. **`ZINTERSTORE destination numkeys key [key ...]`** – Store the intersection of multiple sorted sets into a new key.

**Redis sorted sets** maintain members in a defined order by score, making them ideal for ranked data like leaderboards or priority queues.

---

###### **Bitmap Commands**
1. **`SETBIT key offset value`** – Set the bit at a specified offset to 0 or 1.
2. **`GETBIT key offset`** – Get the bit value at a specific offset.
3. **`BITCOUNT key [start end]`** – Count the number of bits set to 1 in a bitmap.
4. **`BITOP operation destkey key [key ...]`** – Perform bitwise operations (AND, OR, XOR, NOT) across multiple bitmaps.
5. **`BITPOS key bit [start end]`** – Find the position of the first bit set to the specified value (0 or 1).
6. **`BITFIELD key [GET type offset] [SET type offset value] ...`** – Perform complex operations on bit fields within a bitmap.

**Redis bitmaps** allow efficient storage and manipulation of binary data at the bit level, making them useful for tracking presence, flags, or boolean conditions across a large number of items (e.g., tracking user activity).

---

These commands offer powerful ways to work with unique data types and solve a variety of use cases, from basic collections to high-efficiency tracking and ranking systems.

![[Pasted image 20241106182427.png]]

Here are the key commands for **geospatial indices** and **streams** in Redis:

---

###### **Geospatial Commands**
1. **`GEOADD key longitude latitude member [longitude latitude member ...]`** – Add geospatial items (longitude, latitude, and name) to a geospatial index.
2. **`GEODIST key member1 member2 [unit]`** – Calculate the distance between two members in a specified unit (`m` for meters, `km` for kilometers, `mi` for miles, `ft` for feet).
3. **`GEOHASH key member [member ...]`** – Get the Geohash (encoded string) representation for one or more members.
4. **`GEOPOS key member [member ...]`** – Retrieve the longitude and latitude of one or more members.
5. **`GEORADIUS key longitude latitude radius unit [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC]`** – Find members within a specified radius of given coordinates. Optional flags allow you to retrieve additional information, like distance, coordinates, and ordering.
6. **`GEORADIUSBYMEMBER key member radius unit [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC]`** – Find members within a specified radius of a member. Supports additional options for distance, coordinates, and ordering.
7. **`GEOSEARCH key [FROMMEMBER member] [FROMLONLAT longitude latitude] BYRADIUS radius unit [ASC|DESC] [COUNT count] [WITHCOORD] [WITHDIST] [WITHHASH]`** – Advanced geospatial search allowing you to specify the search origin and search by radius.
8. **`GEOSEARCHSTORE destination key [FROMMEMBER member] [FROMLONLAT longitude latitude] BYRADIUS radius unit [ASC|DESC] [COUNT count] [WITHCOORD] [WITHDIST] [WITHHASH]`** – Store results of a geospatial search in a new sorted set.

**Redis geospatial indices** let you store, retrieve, and query location-based data efficiently, making them ideal for proximity searches, such as finding nearby locations or users within a radius.

---

###### **Stream Commands**
1. **`XADD key ID field value [field value ...]`** – Add a new entry to a stream with a specified ID (use `*` to auto-generate).
2. **`XLEN key`** – Get the number of entries in a stream.
3. **`XRANGE key start end [COUNT count]`** – Read entries in a stream within a specific range of IDs.
4. **`XREVRANGE key end start [COUNT count]`** – Read entries in a stream in reverse order, within a specific range.
5. **`XREAD [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] ID [ID ...]`** – Read new entries from one or more streams; can block until a new entry arrives.
6. **`XGROUP CREATE key groupname ID`** – Create a consumer group for a stream.
7. **`XREADGROUP GROUP groupname consumer [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] ID [ID ...]`** – Read entries from a stream as a specific consumer in a consumer group.
8. **`XACK key groupname ID [ID ...]`** – Acknowledge one or more entries as processed in a consumer group.
9. **`XCLAIM key groupname consumer ID [ID ...] [options]`** – Take ownership of pending entries in a consumer group.
10. **`XPENDING key groupname [start end count]`** – Get information on pending entries for a consumer group.
11. **`XDEL key ID [ID ...]`** – Delete entries from a stream by ID.
12. **`XTRIM key MAXLEN ~ count`** – Trim the stream to a specified maximum length (using the `~` modifier for approximate trimming).

**Redis streams** are designed for managing time-ordered data as append-only log entries, allowing for efficient handling of event logs, real-time feeds, and data pipelines, particularly when combined with consumer groups for multi-reader setups.




##### Redis Implementation with spring boot -

![[Pasted image 20241106183956.png]]



Now here in the  application properties , we will write localhost in front of the host as , though we are running it on the docker but we have  to expose it on our computer .

By default - 6379  .

###### Config class -

Now whenever we will perform the operations , we will be using redis template and for which we will be creating a configuration class . so we can autowire the redis template anywhere where we want in the project .

**Here the Redis Template will need some configurations like `connection Factory , key Serializer and Value Serializer` . which are inbuilt , we just have to create and pass them .
Because when we will store the value , we will serialize and when we retrieve it , we will deserialize it .** 

Now we will create a connection Factory - we will return here `lettuce Connection Factory` ,  Creates a new Lettus connection to each call  to the get connection , not thread   safe .

In key serializer , we will use `String Redis Key Serializer` . It converts string into bytes and vice-versa .

In Value Serializer , we will use `Generic Jackson 2 Json Redis Serializer` .  It will map our object to json and json to map through dynamic Typing .


Now we can use this Redis Template to put everything into our Redis Database .
![[Pasted image 20241107181547.png]]
###### Other Classes -
Now we will create a `Entity class` , where the function will implement the `Serializable Class` , as whatever data is saved there using it must be Serializable .
![[Pasted image 20241107181752.png]]


Now we will create a DAO Layer , which will have all the data access layer . ( i.e. data accessing, data retrieving and data ) , So basically it will be the repository .

Here , we will define the redis template and we will also define a key .
Key is important because all the objects that we are creating will come under a hash and this will be the key of that hash .
So  there will be 2 keys, one for all the object and then inside it , one key for each object .
![[Pasted image 20241107040549.png]]


Now , we will save the user using a function and  we can use `redisTemplate.OpsforHash` if we want to save it in key value pair , can use `OpsforList` if want to save it inform of list , likewise .

Now in hash , we have to `put` 3 things - main key , object hash  key and object value . No Returns

Now , if we have to get the user from user Id , can use `redisTemplate.OpsforHash().get()` , it will require 2 things - Key of the whole objects and the key of the object we are trying to find .
Returns a object which we will  cast into Users .


Now in returning all the objects , we will take a map and will get the all the key - value pairs using `get` and passing the key of all the objects . As inside the key , all the key and values are represented in the form of Map .
![[Pasted image 20241107181831.png]]
Now to delete `.delete(Key,hashKey)

Here while creating the key , we have taken User but there could be a case where it is already  present in the database , so we should change this to `User1`. in the User Repository .


**User Controller --**
![[Pasted image 20241107181725.png]]



Here when we are fetching all the Users , we are getting the User Id of each object as well as the Hashkey of each User Object , ![[Pasted image 20241107181326.png]] but if we do not want the Complete Object key , we can convert that Users List into the collection and then we can apply Stream Api To create a list of them .
![[Pasted image 20241107181517.png]]


##### Redis cache With Spring Boot and MySql --

When we are getting Millions of Requests , if it directly hits the database , it will cause the  database crash . So in place of this , we use the Redis as a  cache  database . So now first the request will be checked in the redis database then it will refer to the main database .
![[Pasted image 20241107183457.png]]


Now Implementation -- 
![[Pasted image 20241107183653.png]]



Now , we will use the Annotation `@EnableCaching` , it will enable the caching for the whole project .

Now , we will enter these command to connect our spring boot to the redis database.
```
spring.data.redis.host=localhost
spring.data.redis.port=6379
``` 



Now , we will go to the service layer and will use Annotation `@Cacheable` to enable caching of our data .

Wrote Above get Single Id--
`   @Cacheable(value = "user",key = "#noteId")`
It will check whether the data of the given key exist in redis or not .
Here the `users` is the cache name .
Here , key is the key of the cache , we can pass the expression into this .

If the data is present , it   will retrieve it from the redis otherwise fetch it from the main database and will put it in the redis .

```
// @Cacheable(value = "user",key = "#Id")
    @Cacheable(value = "notes",key = "#noteId")
    public NoteEntity getById(String noteId) {
```
Now in  key- notes , the Note Entity will be stored and its key will be what passed to that function .

Now for the key to store it , The notes Entity must be Serializable .

And now when we search for a data , 1st time won't be in redis , but second time , it will be there .


Before when we fetch it was like this - 
![[Pasted image 20241108191055.png]]
But Now , doesn't matter how many time i fetch the same data , it will be only fetched once .
![[Pasted image 20241108190929.png]]

Now we can verify this in my redis too -- 
By using - `GET key::noteId` --substitute the value there . we will get the serializable value there .

![[Pasted image 20241108191324.png]]


- **We use Cacheable , when we want to get , but if want to cache at the time of data creation then we can use Cacheput**
```
@CachePut(value = "notes", key = "#noteEntity.Id")
    public NoteEntity create(NoteEntity noteEntity) {
```
Here passing expression , then first  we will access the notes Entity then id of it .


-**And At the time of deletion , if we want to remove it from the cache then can use @CacheEvict**

```
    @CacheEvict(value = "notes", key = "#noteId")
    public void delete(String noteId) {
```

