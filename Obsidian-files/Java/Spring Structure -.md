Can't have two path mapped whether one is  get and post or both , it will create ambiguity .


When sending data or receiving data from Controller to view , we use DTOs .

Entity - Represents table in database , when within service layer or repo. layer , we use entity As it represents database structure and managed by ORM ( Object Relation Mapping ) Tool .


Lombok dependency -- reduces boiler plate 

Mainly used for pojos -
That if we write this above any method

@Data - It is from Lombok dependency , it will automatically create the getter and setters methods for all the var. of our class .


![[Pasted image 20240612212215.png]]
And we can create by default constructor and argument constructor with the help og
- ***@NoArgsConstructor***
- ***@AllArgsConstructor***



***Jackson Library*** - Json gets converted to Java Object . And Java Object gets converted to Json .
Can also use ***Model Mapper Dependency*** (MMD) .


		JL -- It also converts the Boolean to string and string to Boolean ,  So when wrote isActive boolean , it will change it to Active string . So for it to instruct to not change the name , we can write above the var isActive .
```
JsonProperty("isActive)
```


MMD --It has map() - Its object asks what to map and in which class to map .


***@Table("  name ") -***
We can give the table name above the Entity Class using table annotation , otherwise it would have just been same as the class name .

***@ID -***
And whenever we make a Entity table , we have to make a primary key denoted by annotation Id .

![[Pasted image 20240613100734.png]]

***@GeneratedValue ( strategy = GenerationType.Auto) --***
Then we have to tell how to generate this id , using annotation Generated Value .

Here it will automatically generate the id .


******
***Repository --***

Whenever we make a repository class we make it a interface and extend it to JPA repository .

It deals with both persistence and Presentation and don't let another to know of each others existence .

Whenever used its class , used @Service Annotation .

******

***Controller uses service and service uses repository .***

So we make controller which contains service inside it , which takes data from the repository , on which we perform operations .
*******

We shouldn't do tight coupling as later on in the future , if the the method or the dependency gets deprecated then , that's why we always in industry , we make a interface and then its implementation .


***Follows Solid Principle*** .

To save any entity in the repository , we use *repository.save(entity)*  .

******

*If in any class , its all methods are going to a path through a common path , now we don't need to mention every time that path , we can mention it above the class and all the methods will understand that . So in method mapping , we will continue after that path .*

******

Stream does the same thing as the for each loop but just in one line . 

Using ` spring.h2.console.enabled = true ` ` In Application Properties` to view the h2 database on the browser .


-----------


In Spring Boot JPA , suppose there is a data that is in two separate classes but we want to store it in a single table 
So while writing the class that is mainly stored make the reference of that another class and here mark it with 
`@Embedded`       and there where that class is created mark it with 
`@Embeddable`.

but now if we have to see these embedded column field names as different column name in table , then normally we use the `@Column(name="")` Annotation but here we will use
```
@AttributeOverride( 
name="name of the field" ,
column = "name we want to show in table"
)
```
completely each time for each field name .

Now for first class , if we were using the builder pattern , then here we will pass the object of the embedded doc to the first class and we will create that object of the embedded class using builder .
Here , if we want the data using our query , we can mention the method below in the repository and above use the annotation `@Query()` . here will pass jpql query but if want to pass the sql query , just do `@Query(,nativeQuery=true)`


Now if in the query , instead of ?1 and ?2 , for the passed parameter , we can use the `@param` to named parameter .

Now if i want to update some data in the database through this query , we have to mark it with  `@Modifying` Annotation and as this data has to be updated , there should be a transaction that if successfully committed then the data in the database will be updated . Hence it should also be marked with `@Transactional` . 
Here one single transaction is created and all insert and delete etc. operations are done with in it and when method is completed , this transaction is committed to the database .If any error rolled back . Generally annotated in the service Layer .

-------

Generally if one class cant exist without other , we make the object of that class inside that other class and if now the relation among their table is 1-1 then we use 
```
@OneToOne(cascade = CascadeType.ALL)
```
Annotation  , we used here cascade becoz if we now try to save the 2nd class without first , as now first is not persistent , it will give error but by doing this we are giving it permission to allow this.

and if we have to use join operation then we use `@joinColumn` and then inside it we mention 2 things 
```
@JoinColumn(
name="name of  the column we want to keep in table"
referencedName = "Name of the column that we are referncing or using for join"
)
```

Now this other class will have extra column with the name we mentioned which will be connected to that first column .
So here we implemented primary key and foreign key relation among them .


----
Now there is eager and lazy fetching which what does is if we want the data of the embeddable doc. without the data of the inner class object we want untill mentioned, then we can use lazy fetching or otherwise we use eager fetching .
```
@OneToOne( 
fetch = "FetchType.LAZY "
)
```
For this , we may exclude this inner class from calling of to string method .

----

Now as we have mapped in upper class about the inner class , if we want to make the oneToOne bi-dir , we can use this in the innerclass 
```
@OneToOne( 
mappedby="field name of the upper class "
)
upper class object
```

 Now if inside one to one , we do optional=true then it would be for us to save the inner class with outer class and without it , it will give errors .

-----
In one to many relationship , when we use join , we mention the referenced name of the column of the same class becoz it will be only one .

Or inplace of this , we can use @many to one at another class which is more preffered where we will still use reference name of the field of the another class that is the class that is one .

_____

Now our repository as extends jpa repository , it implements pageable and sorting , the pageable just req. that page no. and the amount of content that is the size of the page .

so it also accepts the sorting where we can mention how we want to return the result in each page .

So now we can use it like 


----

Now in case of many to many , we have to create a table for the relationship , which we can do as shown and here have to mention the table name and the two column each containing the P.key of one table . 
```
@ManyToMany ( can here also define the cascade type i.e. cascadeType.All)
@joinTable(
name="name of the table"
joinColumns = @joinColum(
name = ""
regerencedColumnName = "to which field this field name is attached"
)
inverseJoinColummns = @joinColum(
name = ""
referencedcolumnname = "the primary key of this field name"
)
)
field name
```