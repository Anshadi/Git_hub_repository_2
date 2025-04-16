
If we use annotation @EnableScheduling in the spring boot class and make  a class and mark it as 
@Component  and in that before the method which we have to schedule , we mark it as the @Scheduled(fixedRate = 3000 ) , So then that method will be executed after every 3 seconds .

![[Pasted image 20241014161425.png]]


To be Noted there are others methods too like fixed Rate . Like Cron  .


#### Cron Expressions - 
A **cron job** is a time-based job scheduler in Unix-like operating systems, used to execute tasks at specified intervals or times. It is commonly used for automating system maintenance or administration, and you can set up cron jobs to run scripts or commands at specific times, dates, or intervals.

In the context of a Spring Boot application, particularly when implementing task scheduling, the `@Scheduled` annotation can use cron expressions to define when a method should run. This allows you to schedule tasks in a way similar to how cron jobs are set up in a Unix-like environment. 

##### Cron Expressions
A cron expression is a string representing a schedule in a specific format. Here's a breakdown of a typical cron expression format:

```
┌───────────── second (0-59)
│ ┌───────────── minute (0 - 59)
│ │ ┌───────────── hour (0 - 23)
│ │ │ ┌───────────── day of the month (1 - 31)
│ │ │ │ ┌───────────── month (1 - 12)
│ │ │ │ │ ┌───────────── day of the week (0 - 7) (Sunday to Saturday; both 0 and 7 * * * * * *                                                       represent Sunday)


```

##### Example
For example, if you wanted to run a task every day at 10 AM, you could use the following cron expression:
```
0 10 * * *
```
In your Spring Boot application, it would look something like this:

```java
@Scheduled(cron = "0 10 * * *")
public void scheduledTask() {
    // Your task logic here
}
```


In general, the `*/n` pattern means:

- **Start from 0** in the specified field and repeat every **n** units of that field (e.g., seconds, minutes, etc.).
- In our Cron expression `*/3 * * * * *`, the first `*` (with `/3`) means the job will run **every 3 seconds**. 

##### Resources
For more detailed information, you can refer to the following resources:
- [Spring Scheduling - Cron Expressions](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#scheduled-tasks-cron) - Official Spring documentation.
- [Cron Jobs - How to Use Cron in Linux](https://www.baeldung.com/cron-expressions) - A tutorial on cron expressions and examples of their usage.

These resources will give you a good understanding of how to effectively use cron expressions in your Spring Boot application for scheduling tasks.


If we are using only annotation Scheduled , Spring Boot only creates a Single Task Executer .
So if we have made many task i.e. multiple calls to the method , it will complete the task  one by one .

So if we want to complete these tasks simultaneously , then we have to configure the Task Schedular .
