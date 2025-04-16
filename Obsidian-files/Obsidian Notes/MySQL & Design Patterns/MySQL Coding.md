We use Coalesce as a fall back mechanism , where we provide multiple value , so if first is null then it takes second value and likewise ,and we can also give a default value like 0 in case of all null values . 

We can use windows function LAG()  and LEAD() with OVER (partition by , order by ) to find the next value and the previous value .

to create multiple  table that u can use later on down the query is 
WITH TABLE1 AS (  ) , TABLE2 As ( ) ;


We can use SUM() function and Count also , if we have to count something and also to give the default value .
like 

SUM( if(YEAR(order_date)=2019),1,0) as something 
OR
Count ( CASE WHEN YEAR(order_date)=2019 THEN 1 END) as something .


A thing to remember is there we don't assign anything like prev = prev+1 , it will return true or false , Here we just put the value .

We use Case When Then Else End  in the select phase . 

We use Group By Above Order By .


if we have to use subQuery then what we can do is we can start writting it after from of the first query like 
Select name , age from (  Select name , age , production )


If there is no column and we have to make ours then we can write it as 1 AS Column_Name .

To Join mainly we use ON to match the condition after that we can use Having .


To match a date  then we can use this -  YEAR(date) =2019 , MONTH(date)=07 , DAY(date)=24 DATE(date) = '2019-07-24 '   ----    ------- to match the entire date.


- You **cannot use `WHERE` with aggregate functions** like `COUNT()` — use `HAVING` instead.
- `ORDER BY id` doesn't make sense if `id` isn't in `SELECT` or `GROUP BY`.


Aggregate functions like `COUNT()` must be used with `HAVING`, **after** a `GROUP BY`.


---------- 
----------------


Great question, Aditya! Let's clear up the differences between `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`, and `NTILE()` — these are all **window functions** that assign ranks or numbers to rows **within a partition** of data. They're often used in analytics queries.

---

### 🧮 1. `ROW_NUMBER()`

- Assigns a **unique number** to each row.
    
- Even if two rows have the same value, their row numbers will differ.
    
- **No gaps** — but no duplicates either.
    

**Example:**

|score|`ROW_NUMBER()`|
|---|---|
|100|1|
|95|2|
|95|3|
|90|4|

---

### 🥇 2. `RANK()`

- Gives the **same rank** to rows with the **same value**.
    
- But **skips ranks** afterward — meaning it leaves gaps.
    

**Example:**

|score|`RANK()`|
|---|---|
|100|1|
|95|2|
|95|2|
|90|4|

---

### 🧮 3. `DENSE_RANK()`

- Like `RANK()`, but **does not skip ranks**.
    
- No gaps between ranks, even for duplicates.
    

**Example:**

|score|`DENSE_RANK()`|
|---|---|
|100|1|
|95|2|
|95|2|
|90|3|

---

### 📦 4. `NTILE(n)`

- Divides rows **into `n` equal buckets**.
    
- Useful for percentiles/quartiles.
    

**Example:** If you do `NTILE(4)` on 8 rows, you’ll get:

|row|`NTILE(4)`|
|---|---|
|1|1|
|2|1|
|3|2|
|4|2|
|5|3|
|6|3|
|7|4|
|8|4|

---

### 🧠 Summary Table:

| Function       | Ties get same rank? | Skips ranks? | Unique numbers?           |
| -------------- | ------------------- | ------------ | ------------------------- |
| `ROW_NUMBER()` | ❌ No                | ❌ No         | ✅ Yes                     |
| `RANK()`       | ✅ Yes               | ✅ Yes        | ❌ No                      |
| `DENSE_RANK()` | ✅ Yes               | ❌ No         | ❌ No                      |
| `NTILE(n)`     | ❌ No                | ❌ No         | ❌ No (equal-sized groups) |

---


-------------
---------------


-----------------------

**Analytic/Statistical Functions** and **Conversion Functions** — because they’re super useful when your queries need either **data analysis** or **data type adjustments**.

---

## 🔄 4. **Analytic / Statistical Functions**

These are a **subset of window functions**, used when you want to compute **ranking, percentiles, cumulative distributions**, etc., **without collapsing rows**. You’ll find them especially helpful in data science or reporting tasks.

Here are the core ones:

---

### ▶️ `PERCENT_RANK() OVER(...)`

Returns the **relative rank** of a row as a **percentile** in the partition.

📌 Formula it follows:  
`(rank - 1) / (total_rows - 1)`

```sql
SELECT 
  student_id,
  score,
  PERCENT_RANK() OVER (ORDER BY score DESC) AS percentile
FROM Scores;
```

✅ Output:

|student_id|score|percentile|
|---|---|---|
|1|98|0.00|
|2|95|0.33|
|3|92|0.66|
|4|90|1.00|

---

### ▶️ `CUME_DIST() OVER(...)`

Returns the **cumulative distribution** of a value within a group (i.e., fraction of rows with values less than or equal to the current).

```sql
SELECT 
  student_id,
  score,
  CUME_DIST() OVER (ORDER BY score) AS cume
FROM Scores;
```

✅ Output:

|student_id|score|cume|
|---|---|---|
|4|90|0.25|
|3|92|0.50|
|2|95|0.75|
|1|98|1.00|

---

### ▶️ `NTILE(n) OVER(...)`

Divides the result set into **n buckets** and gives each row a **bucket number**.

```sql
SELECT 
  student_id,
  score,
  NTILE(2) OVER (ORDER BY score DESC) AS bucket
FROM Scores;
```

✅ Output:

|student_id|score|bucket|
|---|---|---|
|1|98|1|
|2|95|1|
|3|92|2|
|4|90|2|

---

**Use Case Summary**:

- `PERCENT_RANK()` → know how a value ranks in percentage
    
- `CUME_DIST()` → know how many values are <= current value
    
- `NTILE()` → segment data into quantiles (quartiles, deciles, etc.)
    

---

## 🔧 5. **Conversion Functions**

Conversion functions let you **change data types** or formats. This is important when you're dealing with mixed types (like comparing numbers and strings) or formatting results for display.

### ▶️ `CAST(value AS TYPE)`

Changes the data type of a value.

```sql
SELECT CAST('123' AS UNSIGNED);
SELECT CAST(12.34 AS CHAR);
```

📝 Types include: `CHAR`, `UNSIGNED`, `DECIMAL(10,2)`, `DATE`, `DATETIME`, etc.

---

### ▶️ `CONVERT(value, TYPE)`

Same as `CAST()`, but uses different syntax.

```sql
SELECT CONVERT('2024-04-10', DATE);
```

---

### ▶️ `FORMAT(number, decimal_places)`

Formats a number **with commas** and rounds to the given decimals.

```sql
SELECT FORMAT(1234567.891, 2); -- Output: '1,234,567.89'
```

📌 Great for displaying prices, large numbers, etc.

---

### ▶️ `DATE_FORMAT(date, format_string)`

Formats date using a pattern (MySQL only).

```sql
SELECT DATE_FORMAT(NOW(), '%d-%b-%Y'); -- Output: '10-Apr-2025'
```

---

### ▶️ `STR_TO_DATE(str, format)`

Converts a string to a `DATE`.

```sql
SELECT STR_TO_DATE('10-04-2025', '%d-%m-%Y');
```

---

**Use Case Summary**:

- `CAST()` / `CONVERT()` → change column type for comparison or storage
    
- `FORMAT()` → beautify numerical output
    
- `DATE_FORMAT()` → readable timestamps
    
- `STR_TO_DATE()` → parsing string into a date
    

---

-------------


## 🔝 Value-Based Window Functions

|Function|Description|
|---|---|
|`FIRST_VALUE(col)`|Returns the **first** value in the window|
|`LAST_VALUE(col)`|Returns the **last** value in the window|
|`NTH_VALUE(col, n)`|Returns the **n-th** value in the window (e.g., 2nd score)|

---

## 🔢 Ranking Functions

| Function       | Description                                                         |
| -------------- | ------------------------------------------------------------------- |
| `ROW_NUMBER()` | Gives a unique row number **starting from 1** within each partition |
| `RANK()`       | Gives rank with **ties**, skipping numbers (e.g., 1,1,3)            |
| `DENSE_RANK()` | Like `RANK()`, but **no gaps** (e.g., 1,1,2)                        |

➡️ Great for identifying top performers, sorting by score, etc.

---

## 📊 Aggregation Functions (with `OVER()`)

|Function|Description|
|---|---|
|`SUM(col)`|Cumulative or partitioned sum|
|`AVG(col)`|Moving or partitioned average|
|`MIN(col)`|Minimum value in the window|
|`MAX(col)`|Maximum value in the window|
|`COUNT(col)`|Count of rows in the window|

➡️ Example:

```sql
SUM(score) OVER (PARTITION BY student_id ORDER BY exam_date)
```

Gives a **running total** of score for each student.

---

## ⏮ Navigation Functions

|Function|Description|
|---|---|
|`LAG(col, n)`|Gets the value **n rows before** the current row|
|`LEAD(col, n)`|Gets the value **n rows after** the current row|

---



---------------------------------

---

## 🧠 What is `ROWS BETWEEN ... AND ...`?

In a **window function**, you can define a _window frame_ — a subset of rows within the partition that the function should operate on.

It goes inside a `OVER (...)` clause and looks like:

```sql
ROWS BETWEEN <starting_point> AND <ending_point>
```

This tells SQL exactly **which rows to include** in the window function calculation **relative to the current row**.

---

## 🧩 Window Frame Options

Here are the main options you can use:

| Clause                | Meaning                                 |
| --------------------- | --------------------------------------- |
| `UNBOUNDED PRECEDING` | From the **first** row of the partition |
| `N PRECEDING`         | From **N rows before** the current row  |
| `CURRENT ROW`         | The **current row** only                |
| `N FOLLOWING`         | From **N rows after** the current row   |
| `UNBOUNDED FOLLOWING` | Until the **last** row of the partition |

---

### ✅ Common Examples:

1. **Full partition** (e.g., for `LAST_VALUE()` to work correctly):
    

```sql
LAST_VALUE(price) OVER (
  PARTITION BY product_id 
  ORDER BY change_date
  ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
)
```

2. **Running total**:
    

```sql
SUM(price) OVER (
  ORDER BY change_date
  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
)
```

3. **Moving average of last 3 rows**:
    

```sql
AVG(score) OVER (
  ORDER BY exam_date
  ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
)
```

---

## 🔎 Related Window Functions

These are commonly used with `OVER (...)`:

| Function         | Description                                          |
| ---------------- | ---------------------------------------------------- |
| `ROW_NUMBER()`   | Assigns a unique row number within the partition     |
| `RANK()`         | Rank with gaps                                       |
| `DENSE_RANK()`   | Rank without gaps                                    |
| `NTILE(N)`       | Splits rows into N roughly equal groups              |
| `LEAD(expr, N)`  | Gets value N rows _after_ current row                |
| `LAG(expr, N)`   | Gets value N rows _before_ current row               |
| `FIRST_VALUE()`  | First row’s value in the window                      |
| `LAST_VALUE()`   | Last row’s value in the window (⚠️ needs full frame) |
| `SUM()`, `AVG()` | Aggregates over window (running total, moving avg)   |

---

## ⚠️ Tip: Why `LAST_VALUE()` Needs Full Frame

By default, `LAST_VALUE()` only considers the window **up to the current row**. So you **must** use:

```sql
ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
```

…to ensure it includes **all** rows in the partition.

---

-----------------------
-------------
-----------------------


---

## 🔍 What do `CHAR_LENGTH` and `REPLACE` do?

### ✅ `CHAR_LENGTH(str)`

Returns the number of **characters** in the string `str`.

Example:

```sql
SELECT CHAR_LENGTH('ATATGGG') AS length;
-- Output: 7
```

> ⚠️ Note: `CHAR_LENGTH` counts **characters**, not bytes. For byte count, use `LENGTH()`.

---

### ✅ `REPLACE(str, from_str, to_str)`

Returns a copy of the string `str` with all occurrences of `from_str` replaced by `to_str`.

Example:

```sql
SELECT REPLACE('ATATGGG', 'AT', '') AS replaced;
-- Output: AGGG
```

So if you want to **count** how many times `'AT'` occurs:

```sql
(CHAR_LENGTH('ATATGGG') - CHAR_LENGTH(REPLACE('ATATGGG', 'AT', ''))) / CHAR_LENGTH('AT')
-- (7 - 5) / 2 = 1
```

---

## 🔧 Other Handy String Functions in MySQL

|Function|What it does|
|---|---|
|`LENGTH(str)`|Number of **bytes** in the string|
|`SUBSTRING(str, a, b)`|Extracts a substring starting at position `a` of length `b`|
|`LEFT(str, n)`|Returns first `n` characters|
|`RIGHT(str, n)`|Returns last `n` characters|
|`INSTR(str, substr)`|Returns position of first occurrence of `substr` in `str`|
|`LOCATE(substr, str)`|Same as `INSTR`, but args are reversed|
|`REVERSE(str)`|Reverses the string|
|`LOWER(str)`|Converts to lowercase|
|`UPPER(str)`|Converts to uppercase|
|`TRIM(str)`|Removes spaces from start and end|
|`CONCAT(str1, str2)`|Joins two strings together|
|`CONCAT_WS(sep, ...)`|Joins multiple strings using a separator|
|`REGEXP_LIKE(str, pattern)`|Returns true if `str` matches the regex pattern|

---
---------------
----------------


---

### ✅ `LENGTH(str)`

Returns the number of **bytes** in a string. Unlike `CHAR_LENGTH()`, this counts bytes — useful for multibyte character sets.

```sql
SELECT LENGTH('Hello'); -- Returns 5
SELECT CHAR_LENGTH('Hello'); -- Also 5 (same for plain ASCII)

SELECT LENGTH('你好'); -- Returns 6 (each Chinese character is 3 bytes in UTF-8)
SELECT CHAR_LENGTH('你好'); -- Returns 2 (2 characters)
```

---

### ✅ `SUBSTRING(str, start, length)`

Extracts part of a string starting from `start` position, for `length` characters.

```sql
SELECT SUBSTRING('DNA_SEQUENCE', 5, 3); -- Returns 'SEQ'
```

---

### ✅ `LEFT(str, n)`

Returns the **first n characters** of the string.

```sql
SELECT LEFT('GGGATATAT', 3); -- Returns 'GGG'
```

---

### ✅ `RIGHT(str, n)`

Returns the **last n characters** of the string.

```sql
SELECT RIGHT('GGGATATAT', 4); -- Returns 'ATAT'
```

---

### ✅ `INSTR(str, substr)`

Returns the **position** (1-based) of the **first occurrence** of `substr` in `str`.

```sql
SELECT INSTR('ATATGGGTAG', 'GGG'); -- Returns 5
SELECT INSTR('ATATGGGTAG', 'AAA'); -- Returns 0 (not found)
```

---

### ✅ `LOCATE(substr, str)`

Same as `INSTR()`, just parameter order is flipped.

```sql
SELECT LOCATE('GGG', 'ATATGGGTAG'); -- Returns 5
```

---

### ✅ `REVERSE(str)`

Returns the string reversed.

```sql
SELECT REVERSE('ATATGGG'); -- Returns 'GGGTATA'
```

---

### ✅ `LOWER(str)` and `UPPER(str)`

Converts a string to lowercase or uppercase.

```sql
SELECT LOWER('GgGaTaT'); -- Returns 'gggatat'
SELECT UPPER('GgGaTaT'); -- Returns 'GGGATAT'
```

---

### ✅ `TRIM(str)`

Removes spaces (or specified characters) from the **start and end** of the string.

```sql
SELECT TRIM('   ATAT   '); -- Returns 'ATAT'

-- You can also trim specific characters:
SELECT TRIM(BOTH 'A' FROM 'AATATAA'); -- Returns 'TAT'
```

---

### ✅ `CONCAT(str1, str2, ...)`

Joins strings together.

```sql
SELECT CONCAT('AT', 'GGG', 'TA'); -- Returns 'ATGGGTA'
```

---

### ✅ `CONCAT_WS(separator, str1, str2, ...)`

Same as `CONCAT`, but adds a **separator** between each string.

```sql
SELECT CONCAT_WS('-', 'AT', 'GGG', 'TA'); -- Returns 'AT-GGG-TA'
```

---

### ✅ `REGEXP_LIKE(str, pattern)`

Returns `1` (true) if the string matches the regular expression.

```sql
SELECT REGEXP_LIKE('ATGGGTAA', '^ATG'); -- Returns 1 (starts with ATG)
SELECT REGEXP_LIKE('ATGGGTAA', 'TAA$'); -- Returns 1 (ends with TAA)
SELECT REGEXP_LIKE('ATGGGTAA', 'GGG');  -- Returns 1 (contains GGG)
```

> Works only in MySQL 8.0+ (older versions use `str REGEXP 'pattern'` syntax).

---
-------------
------------
----------



---

### ✅ `TRIM([BOTH | LEADING | TRAILING] trim_char FROM str)`

This form of `TRIM()` allows you to **remove specific characters**, not just spaces — and control **where** you remove them from:

|Keyword|What it does|
|---|---|
|`BOTH`|Trims characters from **start and end**|
|`LEADING`|Trims characters from **start only**|
|`TRAILING`|Trims characters from **end only**|

---

### 📌 Examples

```sql
-- Trim spaces (default behavior)
SELECT TRIM('   ATAT   '); -- Output: 'ATAT'

-- Trim specific character from both ends
SELECT TRIM(BOTH 'A' FROM 'AATATAA'); -- Output: 'TAT'

-- Trim only leading 'A's
SELECT TRIM(LEADING 'A' FROM 'AATATAA'); -- Output: 'TATAA'

-- Trim only trailing 'A's
SELECT TRIM(TRAILING 'A' FROM 'AATATAA'); -- Output: 'AATAT'
```

---
--------------------
------------------



---

### ⚡ Why does this work?

In SQL (including MySQL), expressions like:

```sql
dna_sequence LIKE 'ATG%'
```

are **boolean expressions** — they return either:

- `TRUE` → which MySQL automatically **converts to `1`**
    
- `FALSE` → which MySQL automatically **converts to `0`**
    

So this:

```sql
SELECT dna_sequence LIKE 'ATG%' AS has_start
```

is actually doing this under the hood:

```sql
SELECT CASE WHEN dna_sequence LIKE 'ATG%' THEN 1 ELSE 0 END AS has_start
```

You don’t have to write the full `CASE` unless you need custom logic.

---

------------
-------------

-----

Important thing about where to use alias , and where not to - 
- `ORDER BY alias` ➜ **allowed** ✅
    
- `WHERE alias`, `HAVING alias` ➜ **not allowed** ❌
    
- `GROUP BY alias` ➜ sometimes allowed in MySQL, not in other .

-----
---
----


`PARTITION BY rating` means you're grouping by the rating value itself ,

--------

---

## ✅ **Functions that support `OVER()` (Window Functions)**

These **aggregate-like functions** can be used with `OVER()` to become **window functions**:

|Function|Description|
|---|---|
|`AVG()`|Average value over a window|
|`SUM()`|Sum of values over a window|
|`COUNT()`|Count rows or non-null values|
|`MIN()`|Minimum value over a window|
|`MAX()`|Maximum value over a window|
|`ROW_NUMBER()`|Unique row number within a partition|
|`RANK()`|Rank of each row, with gaps|
|`DENSE_RANK()`|Rank of each row, no gaps|
|`NTILE(n)`|Splits rows into `n` buckets|
|`LAG()`|Value of a previous row in the window|
|`LEAD()`|Value of a following row in the window|
|`FIRST_VALUE()`|First value in the window|
|`LAST_VALUE()`|Last value in the window|
|`CUME_DIST()`|Cumulative distribution (0 to 1)|
|`PERCENT_RANK()`|Relative rank (0 to 1)|

You can use all of the above with:

```sql
FUNCTION(...) OVER ([PARTITION BY ...] [ORDER BY ...])
```

---

## ❌ **Functions that cannot be used with `OVER()`**

These functions **do not work** with `OVER()` — they are not window functions:

|Function|Why not valid with `OVER()`|
|---|---|
|`NOW()` / `CURRENT_TIMESTAMP`|Just returns current time, not over rows|
|`RAND()`|Generates a random value, not row-based|
|`GROUP_CONCAT()`|Aggregate for entire group, not row-aware|
|`JSON_ARRAYAGG()`|Also works only with `GROUP BY`|
|`ANY_VALUE()`|Doesn't relate to windowed rows|

> These are pure **aggregate functions**, or scalar functions, that do not support windowing.

---


## What **NOT** to combine directly with `OVER()`:

| Clause/Context             | Allowed with `OVER()`? | Fix (if not allowed)                     |
| -------------------------- | ---------------------- | ---------------------------------------- |
| `WHERE`                    | ❌ No                   | Use subquery or CTE                      |
| `HAVING`                   | ❌ No                   | Use subquery or CTE                      |
| `GROUP BY` + `OVER`        | ⚠️ Sometimes tricky    | Avoid mixing unless needed               |
| `SELECT`                   | ✅ Yes                  | Best place to use window functions       |
| `ORDER BY` (outside)       | ✅ Yes                  | Works fine                               |
| `ORDER BY` inside `OVER()` | ✅ Yes                  | For row-level ordering within partitions |
|                            |                        |                                          |

---

-------------

Yes, **It is absolutely true!** ✅
In SQL, when you use:

`SELECT MIN(name) FROM Users;`

It will return the **lexicographically smallest name** — in other words, the one that comes **first in dictionary order** (A to Z).



-----------------

------------

In case , if i have to give MIN(name) that is the ***lexicographically small name*** , here we can also use order by (name) Ascending , it will handle that .
##### `MIN(name)` there:
Because the `ORDER BY ... u.name ASC` **already handles** the lexicographic tie-breaker.

--------------

Now if somewhere , if we really don't need avg or count in the answer , we can directly use it in the condition and we don't use it in the select clause . Like here 
![[Pasted image 20250414160759.png]]



---------------

--------------


Now suppose , if we have ids with trial period and ids with paid period and we want the id that is included in both then we can also do this 
```
HAVING COUNT(CASE WHEN activity_type = 'free_trial' THEN 1 END) > 0 AND COUNT(CASE WHEN activity_type = 'paid' THEN 1 END) > 0
```


-----------------------
--------------------
-------------


---

## ✅ Creating Completely New **Rows** (Not Based on Existing Data)

### 1. **Using `UNION ALL` with `SELECT` constants**

```sql
SELECT 'Low Salary' AS salary_category, 0 AS count
UNION ALL
SELECT 'Average Salary', 0
UNION ALL
SELECT 'High Salary', 0;
```

This creates 3 **new rows** with artificial data, even if the original table has no matching data.

---

### 2. **Using `VALUES()` (in PostgreSQL, SQL Server, etc.)**

```sql
SELECT * FROM (
  VALUES 
    ('Low Salary', 0),
    ('Average Salary', 0),
    ('High Salary', 0)
) AS salary_template(category, default_value);
```

---

## ✅ Creating Completely New **Columns** (Not Present in Source Table)

You can **hardcode columns** like this:

```sql
SELECT 
  user_id,
  name,
  0 AS free_trial_flag,
  NULL AS subscription_date
FROM Users;
```

Or **add calculated/placeholder columns**:

```sql
SELECT 
  user_id,
  name,
  CASE WHEN income < 20000 THEN 'Low'
       WHEN income BETWEEN 20000 AND 50000 THEN 'Average'
       ELSE 'High' END AS salary_category,
  1 AS new_flag_column,
  '2025-01-01'::date AS future_column
FROM Users;
```

---

## ✅ Mix: Build a Grid of Columns × Rows

Suppose you want to show a fixed grid of all combinations like:

```
Month     | Category
----------|----------
January   | Low
January   | Average
January   | High
February  | Low
...
```

Use `CROSS JOIN`:

```sql
WITH months AS (
  SELECT 'January' AS month UNION ALL
  SELECT 'February' UNION ALL
  SELECT 'March'
),
categories AS (
  SELECT 'Low' AS category UNION ALL
  SELECT 'Average' UNION ALL
  SELECT 'High'
)
SELECT *
FROM months
CROSS JOIN categories;
```

This gives you **new rows + new columns** with **zero reliance on existing data**.

---

## Summary: 🧠

| Goal                     | Technique                             |
| ------------------------ | ------------------------------------- |
| Create new rows manually | `UNION ALL`, `VALUES()`, `CROSS JOIN` |
| Create new columns       | Constants, `CASE`, default values     |
 
 -------------------------------
------------------------
-------------


---

## ✅ What does `FILTER` do in SQL?

The `FILTER (WHERE …)` clause is used **inside aggregate functions** like `SUM()`, `AVG()`, `COUNT()`, etc., to **apply a condition only to that aggregate**, **without affecting the rest of the row**.

### 🔍 Syntax:

```sql
AGG_FUNCTION(column) FILTER (WHERE condition)
```

### 🔧 Example:

```sql
SELECT 
  department,
  COUNT(*) AS total_employees,
  COUNT(*) FILTER (WHERE gender = 'Male') AS male_employees,
  COUNT(*) FILTER (WHERE gender = 'Female') AS female_employees
FROM employees
GROUP BY department;
```

Here:

- `COUNT(*)` counts everyone.
    
- The `FILTER` clause counts only rows that match the condition—**within that aggregate only**.
    

---

## 💡 Why use `FILTER` instead of `CASE WHEN …`?

You could write the same thing using `CASE`:

```sql
SUM(CASE WHEN condition THEN column ELSE 0 END)
```

But `FILTER`:

- Is **more readable**.
    
- Keeps **aggregates clean and focused**.
    
- Can be **more efficient** in some databases.
    

---

---

## ⚠️ Where is `FILTER` supported?

- ✅ PostgreSQL
    
- ✅ SQLite (version 3.30+)
    
- ✅ Apache Flink
    
- ❌ MySQL (not yet)
    
- ❌ SQL Server (not yet)
    
- 🔁 For unsupported databases, use `CASE WHEN` as a fallback.
    

---
-------
-------------


Shorthand way of writing the same thing in mysql ---

- So `GROUP BY 1` means: **Group by the first selected column** → which is `transaction_date`.
- Similarly, `ORDER BY 1` means: **Order by the first selected column** → again, `transaction_date`.

Here Selected column means the first column we write after Select clause .


------------------------
------------------------
------------


------------------


Now suppose there is two query --- 1st One ---
```
SELECT ROUND(COALESCE(COUNT(B.player_id),0) * 1.0 / tp.cnt, 2) AS fraction
FROM B, total_players tp;
```

Now here the answer could be null because if in the case the b.player_id is 0, that is no row is returned from there , then the coalesce doesn't even matter , it will result in null .

So to correct this , we have to use this count in a subquery way and then put it on a coalesce here , so now it returns 0 --
```
SELECT ROUND(COALESCE((
    SELECT COUNT(*) * 1.0 FROM B
), 0) / tp.cnt, 2) AS fraction
FROM total_players tp;

```

Ok so for clarity , what is happening is , when we run from B in the 1st query , the whole table is empty so it returns null and the inside select function doesn't even get the chance to run .

however in the second one the inner query is the independent one that is , it is not dependent on the context from the outside and hence it returns , there we even didn't need to put Coalesce .


-------------------------

--------------------
----------------

---------------------------------
---------

---

### 🔢 1. `COUNT(*)` vs `COUNT(column)`

|Function|What it does|
|---|---|
|`COUNT(*)`|Counts **all rows**, regardless of `NULL` values|
|`COUNT(column)`|Counts **only non-NULL values** in the specified column|

> BUT — here's the **key point** — **they behave the same way when the table is empty**:  
> both return `0`, **not `NULL`**, when there are no rows.

---

### 🤯 So Why Did You See `NULL` Earlier?

Because of this subtle situation:

#### ❌ Case where you got `NULL`:

```sql
SELECT ROUND(COALESCE(COUNT(B.player_id), 0) / tp.cnt, 2) 
FROM B, total_players tp;
```

Here’s what’s really happening:

- When **`B` is empty**, and you do `FROM B, total_players` → the entire `FROM` clause yields **zero rows**.
    
- Since the **whole row is missing**, the `SELECT` clause doesn't even get to run.
    
- The `COUNT(...)` is never evaluated — it’s not that `COUNT(B.player_id)` returned `NULL`; it **didn’t return anything at all**, because no row was produced from the `FROM` clause.
    

So it's not really `COUNT(B.player_id)` that's giving `NULL` — it's that **your query is returning zero rows** due to an empty join.

---

### ✅ Case that works correctly:

```sql
SELECT ROUND(COALESCE((SELECT COUNT(*) FROM B), 0) / tp.cnt, 2)
FROM total_players tp;
```

Here:

- The subquery `(SELECT COUNT(*) FROM B)` is **independent** — it runs even if `B` is empty.
    
- `COUNT(*)` sees no rows → returns `0`
    
- `COALESCE(0, 0)` → gives `0`, and you proceed with division.
    

---

### ✅ Key Learning:

|Case|What Happens When `B` is Empty|
|---|---|
|`SELECT COUNT(*) FROM B`|Returns `0`|
|`SELECT COUNT(B.col) FROM B`|Returns `0`|
|`SELECT COUNT(*) FROM B, total_players`|Returns **0 rows total**, because join fails|
|`SELECT COUNT(B.col) FROM B, total_players`|Same — **no row** → no expression to evaluate|
|`SELECT (SELECT COUNT(*) FROM B)`|Always returns a row, even if it's `0`|

---

### 🧠 Quick Visual:

Let’s say:

```sql
B = (empty set)
total_players = (1 row)
```

|Query Type|Output|
|---|---|
|`SELECT COUNT(*) FROM B`|`0`|
|`SELECT COUNT(col) FROM B`|`0`|
|`SELECT COUNT(*) FROM B, total_players`|❌ No rows|
|`SELECT COUNT(col) FROM B, total_players`|❌ No rows|
|`SELECT (SELECT COUNT(*) FROM B)`|✅ Returns `0`|

---
------------
-----------
------------------------------

---

## 🔹 `LIMIT` and `OFFSET` in MySQL

### ✅ Syntax

```sql
SELECT * FROM table_name
LIMIT count OFFSET offset;
```

- **`LIMIT`**: How many rows you want to fetch.
    
- **`OFFSET`**: How many rows to skip **before** starting to return rows.
    

### ✅ Example

```sql
SELECT name FROM students
ORDER BY marks DESC
LIMIT 1 OFFSET 1;
```

👉 This gives the **second highest mark**, because it skips the first (`OFFSET 1`) and takes the next one (`LIMIT 1`).

🧠 **Alternate shorthand:**

```sql
LIMIT offset, count;
-- Same as:
LIMIT 1, 1;
```

---

## 🔸 Use Case: Getting the _Nth_ Record

Want the 3rd top salary?

```sql
SELECT DISTINCT salary FROM Employee
ORDER BY salary DESC
LIMIT 1 OFFSET 2;  -- 3rd highest
```

---
 Pagination Example (Real-world Use)

```sql
-- Page 2, 10 results per page:
SELECT * FROM products
ORDER BY name
LIMIT 10 OFFSET 10;
```

---
-----------


Here an error came where i was writing in the template 


```
You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '- 1 ); END' at line 7 -
```

```
 CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
  BEGIN 
	  RETURN ( 
	  SELECT DISTINCT salary FROM Employee 
	  ORDER BY salary DESC 
	  LIMIT 1 OFFSET N - 1 
	  ); 
  END
```



The issue here is due to **`N - 1` inside the OFFSET clause** — MySQL does **not allow expressions like `N - 1` directly inside `LIMIT ... OFFSET`** unless you **wrap it properly or compute it beforehand**.

---

### ✅ Corrected version:

```sql
DELIMITER $$

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  DECLARE offset_val INT;
  SET offset_val = N - 1;

  RETURN (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    LIMIT 1 OFFSET offset_val
  );
END$$

DELIMITER ;
```

---

### 🔍 Explanation:

- ✅ `DECLARE offset_val INT;` creates a variable to hold `N - 1`
    
- ✅ `SET offset_val = N - 1;` calculates the value
    
- ✅ `OFFSET offset_val` uses the variable inside the SQL clause
    
- ✅ `DELIMITER $$` and `DELIMITER ;` are required when defining stored functions or procedures in MySQL to avoid confusion with `;` inside the body
    

---
-------------------------
----------------


Window function over a range -

```
<window_function>() OVER (
  PARTITION BY <column> 
  ORDER BY <column> 
  ROWS BETWEEN <range>
)
```

-----------------------

Now if i have to subtract a number from a date , then we can use this command -
`DATE_SUB(b.visited_on, INTERVAL 6 DAY)`

And suppose if we have to find something in a range , so we can use this query -
`BETWEEN DATE_SUB(b.visited_on, INTERVAL 6 DAY) AND b.visited_on`

----------------------------------

--------------

Now if i have to skip the first some rows without putting some limit then we can do this -
`LIMIT 100000 OFFSET 6 `
As in My Sql , We can't use OFFSET without Limit . So it will like asking for all the data, but may not work in real world scenarios . 


----------------
----
-------------


---

## ✅ 1. What is `JOIN LATERAL`?

`JOIN LATERAL` allows a **subquery to use columns from the outer query** row.  
It’s like saying:

> _"For each row in the outer table, run this subquery using the outer row's values."_

---

### 🎯 Example:

```sql
SELECT c.customer_id, c.name, top_order.amount
FROM Customer c
JOIN LATERAL (
    SELECT o.amount
    FROM Orders o
    WHERE o.customer_id = c.customer_id
    ORDER BY o.amount DESC
    LIMIT 1
) AS top_order ON true;
```

🔍 What's happening:

- For each customer, we look up their highest order.
    
- The subquery `top_order` is **run per customer**.
    
- It **can access `c.customer_id`** because of `LATERAL`.
    

Without `LATERAL`, the subquery can’t refer to `c`.

---

## ✅ 2. What is `CROSS APPLY`? (SQL Server & PostgreSQL)

`CROSS APPLY` in **SQL Server** (or **PostgreSQL**) works **exactly like** `JOIN LATERAL`. It's just a **different name**.

### 🎯 Equivalent Example in SQL Server:

```sql
SELECT c.customer_id, c.name, top_order.amount
FROM Customer c
CROSS APPLY (
    SELECT TOP 1 o.amount
    FROM Orders o
    WHERE o.customer_id = c.customer_id
    ORDER BY o.amount DESC
) AS top_order;
```

- Works **like an INNER JOIN**: If the subquery returns nothing for a row, that row is excluded.
    

---

## ✅ 3. What is `LEFT JOIN LATERAL` (aka `OUTER APPLY`)?

This is the **LEFT JOIN version** of `JOIN LATERAL`.  
It keeps **all rows from the left table**, even if the subquery returns nothing.

---

### 🎯 Example:

```sql
SELECT c.customer_id, c.name, top_order.amount
FROM Customer c
LEFT JOIN LATERAL (
    SELECT o.amount
    FROM Orders o
    WHERE o.customer_id = c.customer_id
    ORDER BY o.amount DESC
    LIMIT 1
) AS top_order ON true;
```

This ensures that **all customers are included**, even those **with no orders** — in which case `top_order.amount` will be `NULL`.

### 🆚 Equivalent in SQL Server:

```sql
SELECT c.customer_id, c.name, top_order.amount
FROM Customer c
OUTER APPLY (
    SELECT TOP 1 o.amount
    FROM Orders o
    WHERE o.customer_id = c.customer_id
    ORDER BY o.amount DESC
) AS top_order;
```

---

## 📊 Summary Table

|SQL Concept|Supported In|Keeps All Left Rows?|Can Access Outer Query?|
|---|---|---|---|
|`JOIN LATERAL`|PostgreSQL, MySQL 8+|❌ No (acts like INNER JOIN)|✅ Yes|
|`CROSS APPLY`|SQL Server, PostgreSQL|❌ No|✅ Yes|
|`LEFT JOIN LATERAL`|PostgreSQL, MySQL 8+|✅ Yes|✅ Yes|
|`OUTER APPLY`|SQL Server|✅ Yes|✅ Yes|

---

## 🧠 When to Use Them?

|Scenario|Use|
|---|---|
|You want a subquery to use columns from the outer row|`LATERAL` or `APPLY`|
|You want **only rows where subquery matches**|`JOIN LATERAL` / `CROSS APPLY`|
|You want **all left rows, even if subquery returns nothing**|`LEFT JOIN LATERAL` / `OUTER APPLY`|
|You’re doing **per-row aggregation, filtering, or lookups**|`LATERAL` magic ✨|

---

-------------
---------------

## 🤓 Why Use `LATERAL JOIN`?

|Use Case|Why LATERAL is Great|
|---|---|
|Top N items per group|Efficient per-row filtering|
|Rolling windows (custom)|When window functions aren’t enough|
|Dynamic filters|Filter logic changes per row|
|Reusable logic|Clean, readable subqueries|

---
---------------
------------


Now suppose , if we have to fetch all rows , before in join what we do is we write 
`on 1=1` but now we can write `on true` or if we have to fetch no rows , we can write `on false ` .

