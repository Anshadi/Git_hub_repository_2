![[Pasted image 20240924174502.png]]![[Pasted image 20240924174103.png]]![[Pasted image 20240924174256.png]]![[Pasted image 20240924174603.png]]
In alias we put the alias name inside the double inverted comma otherwise after space it will consider that as other statement and will mostly give error .![[Pasted image 20240924175550.png]]

Describe gives us the metadata about the table  .

![[Pasted image 20240924175940.png]]

Rollback is used to undo all the partial transactions .
Commit to make all transactions permanently stored .

In Acid Principles - It refers to durability .
![[Pasted image 20240924180254.png]]

Update statement is used to modify the table values .

![[Pasted image 20240924180925.png]]

Here either full transaction will occur or no - refering to Atomicity in Acid .

We use save Point as a restore point to which we can rollback![[Pasted image 20240924181036.png]]

Ex-
```
SavePoint Here
Rollback To Here
```
IF Function --
![[Pasted image 20240924181220.png]]

IFNUll function - it tells whether the column is null and if it is then what to return

IFNULL(NULL , "PSIT")
![[Pasted image 20240924181330.png]]

Create table as select -CTAS -- Used to copy the whole structure of a table to another table while creating that table .
 If we want to copy only structure - we can use where to search the data which doesn't exist . 

Comparison -- ![[Pasted image 20240924182843.png]]

Union gives us the unique records .
where as union all gives us all the data so if contains duplicate , it will concatenate it too .


![[Pasted image 20240924183550.png]]

Creating data on the fly without table dual -
```
select 1 as col1 , 2 as col2 , 3 as col3  from dual ;
```

If we have to grow the col - we use select 
if rows then union -

Patterns Making --
![[Pasted image 20240924185632.png]]



Multiline Comment --  /*  


For Sorting -- ORDER BY
![[Pasted image 20240924190606.png]]

By default , it has Ascending order --

![[Pasted image 20240924191121.png]]


Here first ascending order then on matching value , the second sorting will be applied .
![[Pasted image 20241001190049.png]]


SELECT * 
FROM forbes_global_2010_2014 
WHERE sector = 'Financials' 
ORDER BY profits DESC 
LIMIT 1;


To limit the data , now if we want to select some specific data from middle , we use offset . it skips record from start .


**Pagination can be achieved by using limit by and offset .**

Set Of rows - Table - A set never have order that's why use order by .

###### If- Else -- 
If we use here where , it will remove all the rows that are not selected that is no operations will be performed on them , but if we suppose we have to fill some value in them i.e. 0  , we should if -else . 
Ex-  SELECT IF( country = 'India' , 10 , 0)  ---  ( condition , condition value , else value ) as discount ;
Here we don't have elseif . So we can use nested if - else .


We can declare a variable with the keyword SET .

SET @marks = 0 ;


However Here , We can also use

`SELECT Case WHEN  ----condition --  THEN---value ---`
	` WHEN --condtion-- THEN ---value--`
	` ELSE -- value --`
	` END as Column Name`
` FROM dual ;`


For pattern matching in sql - Like operator is used 

There are two wildcards - % - 0 or more char matchings 
If exactly  one - "__ "- single char matching 


```
SELECT * 
FROM customers 
WHERE customerName LIKE 'A%' OR customerName LIKE '%L%';
```


Concatenation can be done in two ways] --

`STR1 || STR2 || STR3` Used not in mysql but in oracle and PostgreSQL .

`CONCAT(STR1,STR2,STR3)`


LIKE can be used where there is no % or __  .

```
Select * from country where country like 'INDIA'
```




we can use <> for not in .









We use SUBSTR (string , start , length ) to get the substring of a string .
Just Like python it works with negative indexng too .
If i write left or right it already knows from where it is starting now it just need the length and the string .
***The selection is always from left to Right*** 
```
SELECT substr("PSIT" , 1,2) as extract from dual ;
SELECT substr("PSIT" , -1,2) as extract from dual ;
SELECT left("PSIT" ,2) as extract from dual ;
SELECT right("PSIT" ,2) as extract from dual ;
```


If we have to search for a specific string in another string , we can do 
select instr(string , string that we want to be searched) , it is case Insensitive .
It gives the position of its first occurrence . 

It doesn't returns index but it returns the position .If it is not present  it will return zero .


![[Pasted image 20241022180857.png]]


![[Pasted image 20241022181117.png]]

![[Pasted image 20241022181316.png]]

![[Pasted image 20241105174642.png]]
.

REPLACE(String Expressing , String Pattern , Replacing Pattern ) .
In replace , if have given a number , there would be a implicit conversion of it into string  .

TRIM() , LTRIM() , RTRIM() is used to trim the spaces in that direction .
![[Pasted image 20241022182327.png]]


![[Pasted image 20241022182442.png]]

Same way ceil() or ceiling() can be used .
Just opposite of Floor .

![[Pasted image 20241022182947.png]]

Same way as the truncate , Round() is used .

All the operations we can do with the Count() ---
![[Pasted image 20241022185439.png]]


Aggregation Function is also known as Squishing Function .

Count( * ) - counts all the values 
Count(Name)  - it won't count the null values .

So when we have to get distinct , will use distinct , if have to remove the Null values , will use the count on that particular name .



SUBQUERIES --
![[Pasted image 20241105183218.png]]


![[Pasted image 20241105183704.png]]![[Pasted image 20241105184057.png]]


However  it is better to use CTE ( Common Table Expressions ) than SubQueries .
Conversion of it to the CTE is
![[Pasted image 20241105184711.png]]

Here is the format for the CTE -- 
![[Pasted image 20241111174931.png]]

Here in query 2 , it is not necessary that we can only refer to only the previous table but we can refer to any table .


Grouping --
Here we try to filter at group level .
So i write Group_by column then in that all the values of the same column name will be grouped together .

![[Pasted image 20241111181953.png]]


We do group by in place of distinct , when we have to put condition on it .

In Group Concat , we can add the value of the different field having the same value . they provide the support for order by in it .
![[Pasted image 20241111190006.png]]

![[Pasted image 20241111191235.png]]


![[Pasted image 20241112175055.png]]

![[Pasted image 20241112183538.png]]
Now after disabling only full group by , we can select iata code .
![[Pasted image 20241112183803.png]]

![[Pasted image 20241112184529.png]]



Count(*) used for blind counting , but can use if else for conditional counting .
![[Pasted image 20250103114536.png]]

![[Pasted image 20250103114548.png]]![[Pasted image 20250103114556.png]]



