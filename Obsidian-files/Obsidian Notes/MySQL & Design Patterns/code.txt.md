
-- -- create
-- CREATE TABLE EMPLOYEE (
--   phone INTEGER PRIMARY KEY,
--   name TEXT NOT NULL
-- );

-- CREATE TABLE EMPLY (
--   phone INTEGER PRIMARY KEY,
--   name TEXT NOT NULL,
--   number INTEGER
-- );


-- -- insert
-- INSERT INTO EMPLOYEE VALUES (0001, 'Clark' );
-- INSERT INTO EMPLOYEE VALUES (0002, 'Dave');

-- -- Select name as myname , name as "your name" from EMPLOYEE ;

-- -- desc EMPLOYEE;

-- -- Select * from EMPLOYEE ;

-- -- select name , ifnull(phone , "no phone no. given") as phone_num from EMPLOYEE ;

-- CREATE TABLE EMPL (
--   phone INTEGER PRIMARY KEY,
--   name TEXT NOT NULL,
--   number INTEGER
-- );

-- INSERT INTO EMPL VALUES (0002, 'Dave',NULL);
-- INSERT INTO EMPL VALUES (0001, 'Raj',NULL);

-- -- select name , ifnull(number , "no number given") as num from EMPL ;

-- INSERT INTO EMPLY VALUES (0001, 'Clark' , 9834);
-- INSERT INTO EMPLY VALUES (0002, 'Dave', NUll);


-- CREATE table tbl1 as select * from EMPLOYEE ;

-- -- desc tbl1 ;

-- CREATE table tbl2 as select * from tbl1 where 1=2 ;
-- -- desc tbl2 
-- -- Select * from EMPL ;

-- -- select * from EMPL union select * from EMPLY ;-- both should have same column no .

-- -- select * from EMPL union all select * from EMPLY ;



-- select 1 as col1 , 2 as col2 , 3 as col3  from dual ;



-- select 1 as col1 from dual 
-- union 
-- select 2 as col1 from dual 
-- union
-- select 3 as col1 from dual ;


-- select 1 as col1 , 2 as col2 , 3 as col3 from dual
-- union
-- select 4 as col1 , 5 as col2 , 6 as col3 from dual
-- union
-- select 7 as col1 , 8 as col2 , 9 as col3 from dual; 


-- select 4 as col1 , 5 as col2 , 6 as col3 from dual
-- union all 
-- select 7 as col1 , 8 as col2 , 9 as col3 from dual; 



-- create table tbl4 as
-- select * from tbl1;

-- select * from tbl4;


-- -- emptying the table - 
-- delete from tbl4 ; 


-- create table tbl5 as
-- select * from tbl1;


-- -- //alternate way of emptying the data - better performance than delete --> drop + create -- part of DDL
-- truncate table tbl5 ;

-- -- to remove the whole table strrucute and metadata both goes away 
-- drop table tbl4;



-- -- variations of creating a  table
-- create table tbl8 as (

-- select * from tbl1 );

-- select * from tbl8 ;



-- -- only to copy structure
-- create table tbl9 as (

-- select * from tbl1 where 1=2 );

-- desc tbl9 ;
-- desc tbl1 ;


-- select now() as curr_date from dual ;



-- SELECT * from tbl1 ;


-- SET @marks = 5;
-- SELECT 
--   @marks AS marks,
--   IF(@marks BETWEEN 90 AND 100, 'A', 
--     IF(@marks BETWEEN 10 AND 89, 'B', 'F')) AS grade 
-- FROM dual;
-- -- Query for phone numbers starting with '9'

-- SELECT * 
-- FROM tbl1 
-- WHERE phone LIKE '9%';

-- -- Concatenation using CONCAT
-- SELECT CONCAT('Hello', 'World') AS name FROM dual;

-- -- Concatenation with space
-- SELECT CONCAT('HELLO', ' ', 'WORLD') AS name FROM dual;

-- -- Concatenation with CONCAT_WS
-- SELECT CONCAT_WS(' ', 'HELLO', 'WORLD') AS name FROM dual; 
-- -- //seperator first


-- SELECT LENGTH('HELLO') AS Length FROM dual;


-- create database org




CREATE TABLE Worker (

	WORKER_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,

	FIRST_NAME CHAR(25),

	LAST_NAME CHAR(25),

	SALARY INT(15),

	JOINING_DATE DATETIME,

	DEPARTMENT CHAR(25)

);


INSERT INTO Worker

  (WORKER_ID, FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT)

VALUES

  (001, 'Monika', 'Arora', 100000, '14-02-20 09.00.00', 'HR'),

  (002, 'Niharika', 'Verma', 80000, '14-06-11 09.00.00', 'Admin'),

  (003, 'Vishal', 'Singhal', 300000, '14-02-20 09.00.00', 'HR'),

  (004, 'Amitabh', 'Singh', 500000, '14-02-20 09.00.00', 'Admin'),

  (005, 'Vivek', 'Bhati', 500000, '14-06-11 09.00.00', 'Admin'),

  (006, 'Vipul', 'Diwan', 200000, '14-06-11 09.00.00', 'Account'),

  (007, 'Satish', 'Kumar', 75000, '14-01-20 09.00.00', 'Account'),

  (008, 'Geetika', 'Chauhan', 90000, '14-04-11 09.00.00', 'Admin');



-- COMMIT

SELECT * from Worker ;


-- SELECT CONCAT_WS(' ', FIRST_NAME, LAST_NAME) AS COMPLETE_NAME
-- FROM Worker;


-- SELECT substr("PSIT" , 1,2) as extract from dual ;
-- SELECT substr("PSIT" , -1,2) as extract from dual ;
-- SELECT left("PSIT" ,2) as extract from dual ;
-- SELECT right("PSIT" ,2) as extract from dual ;



-- SELECT DISTINCT DEPARTMENT, LENGTH(DEPARTMENT) AS len
-- FROM Worker;


SELECT INSTR("dbac","a") as pos from dual 

-- SELECT FIRST_NAME, INSTR(UPPER(FIRST_NAME), 'A') AS pos
-- FROM Worker;


SELECT *
FROM Worker
WHERE FIRST_NAME LIKE 'a%';

SELECT *
FROM Worker
WHERE INSTR(FIRST_NAME, 'A') = 1;

SELECT *
FROM Worker
WHERE LEFT(FIRST_NAME, 1) = 'A';

SELECT *
FROM Worker
WHERE substr(FIRST_NAME,1,1) = 'A';

SELECT *
FROM Worker
WHERE 'A' in FIRST_NAME;

SELECT *
FROM Worker
WHERE FIRST_NAME LIKE '%A%';

SELECT *
FROM Worker
WHERE INSTR(FIRST_NAME, 'A') > 0;


SELECT *
FROM Worker
WHERE RIGHT(FIRST_NAME, 1) = 'h' AND LENGTH(FIRST_NAME) = 6;


SELECT *
FROM Worker
WHERE FIRST_NAME LIKE '_____h';




