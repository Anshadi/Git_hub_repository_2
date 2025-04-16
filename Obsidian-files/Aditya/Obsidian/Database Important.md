Specific Profile Photo Searching


![[Screenshot 2023-12-17 001107.png]]
![[Screenshot 2023-12-17 001131.png]]
![[Screenshot 2023-12-17 001144.png]]

Can be Used for
![[Screenshot 2023-12-17 002945.png]]

Where normalization of data should be done and where denormalization of data.
Normalization -- one to Many, more frequently data in it changes, write operation non-atomic
Denormalization -One to One , One to Few, less frequently.--full object one  place ,write operation atomic (preffered).
![[Screenshot 2023-12-17 013401.png]]
![[Screenshot 2023-12-17 013415.png]]
![[Screenshot 2023-12-17 013427.png]]
so Denormalization where we may want access the whole info and they can be changed often  -- Profile System , Data of places
we  can use the hybrid of both too.
So there is always a trade-off between data consistency and query  Performances.

Capped Collection --  (keeps Only the recent ones)
In MongoDB, a capped collection is a special type of collection with a fixed-size storage limit. Once a capped collection reaches its maximum size, it behaves in a circular or "capped" fashion, meaning that when the collection is full, the oldest documents are overwritten by the newest ones. Capped collections are commonly used for scenarios where a fixed-size, high-performance, and time-ordered data store is needed.

For Ex-![[Screenshot 2023-12-17 022031.png]]

Advantages -- ![[Screenshot 2023-12-17 022157.png]]
Disadvantages --![[Screenshot 2023-12-17 022215.png]]


Using Eval command Line
![[Screenshot 2023-12-17 115603.png]]

Connect(database name) --connects to a specific database and returns a object connected to it.

For Ex-
db = connect("localhost/words")        //now word database type object is stored in db
printjson(db.getCollectionNames())

It will print all the collections inside the database Words.


