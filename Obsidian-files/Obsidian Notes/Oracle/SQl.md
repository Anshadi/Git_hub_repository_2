
Some Sql Functions --
```
create database mydb
use mydb;
show tables;
 create table student(
    -> studentId INTEGER );
create table newstudent( name varchar(10) , studentId integer , dob date , address varchar(20) );
show tables;
desc newstudent ;
 alter table newstudent  add email varchar(255), drop column studentId , modify dob timestamp ;
 desc newstudent;
 alter table newstudent  rename column dob to dobandtime ; 
 
```
 
When we have to put from some selected words we will use LIST operator -- IN 

If using keyword CONSTRIANT have to use naming Convention 

<> represents not equal to

NOTNULL -- 
always applicable on Column level Constraints and can be on  more than one .

Check Constraints - can be applied on both table level and column level constraints .

Unique Constraints - on column applied , can't have same values . Either one can have Null values . Can be more than one .

Default Constraint -- Datatype of our column and default expression must be same .
					can be applied on non-nullable .
					Used to give default value to a column  .

Composite Primary key -- On table level 


