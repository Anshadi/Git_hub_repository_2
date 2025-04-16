Java DataBase Connectivity 

For this - 4 drivers 
- Native Api
- ODBC Bridge
- Pure java drivers


Steps of Connectivity -
- Import package  (  java.sql *)
- Load and register the driver 
- Establish the connection 
- Create the statement 
- Execute  the Query
- Close


here the driver we use for mysql is - com.mysql.jdbc.Driver and from the library jar file named mysql-connector .


##### Steps -

	- In NetBeans , this connector is inbuilt .
	- Now we use method forName to load our driver 

		- Class.forName(" com.mysql.jdbc.Driver ") <-- it belongs to the class " Class " . & Driver belongs to package com.mysql.jdbc  .
		- it justs loads the class and not creates the object hence the static block inside it is executed and not the Instance block . 
		- can create the object using .newinstance() .
		- Ex-  Class.forName("xyz").newInstance()  .
		- And forName method also extends the register driver method which registers our driver specified .

		
	- For Connection , we have to instantiate the interface named connection .
	- Statement is of 3 types -- 
		- Statement - when we want to execute a query .
		- Prepared Statement - when we have pre-defined query but we have diff. values
		- callable Statement - When we want to execute Procedural language in sql


- getConnection() is a static method which gives us the object of interface Connection .
- It belongs to the Driver Manager Class .
	- It takes three parameter - URL( Database url ) , Username ,Password .

- We have to create a object of the statement too , as it is also Interface .
- To Execute Query , we use -  Statement_Object .executeQuery( " Database Query "); 
- After Getting query , Our pointer by default will be on starting 
- And in the end to close the connection 
		- statement and connection object . close() 

Ex -Theory![[Pasted image 20240525002923.png]]

Practical --
![[Pasted image 20240525004004.png]]

With the help of result set , we can store data in the tabular structure .
	Here alien dataset is made inside mysql Workbench .
	![[Pasted image 20240525004435.png]]

To fetch all the data from database .
![[Pasted image 20240525004717.png]]

To Insert Values in our database -
![[Pasted image 20240525005032.png]]

But in case we have to enter large data set then -
here ? - means we know the query but don't know what the value is yet
![[Pasted image 20240525005401.png]]



In Mysql through Command Line -
![[Pasted image 20240525010308.png]]

Using function to fetch data -- 
![[Pasted image 20240525011012.png]]

![[Pasted image 20240525011146.png]]

![[Pasted image 20240525011210.png]]

To Add the data to the database ---
![[Pasted image 20240525011610.png]]

![[Pasted image 20240525011748.png]]


Same way we can remove the data from the database .





