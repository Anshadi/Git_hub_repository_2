![[Pasted image 20241110105812.png]]

![[Pasted image 20241110105825.png]]


The First Key Component in `Spring Batch Architechture` is `Job Launcher` .It is a Interface which has a method `run`  which starts the job , we can consider it as the starting point of the job .

Immidiately it will create another component named as `job` . It is the work to be done by spring batch .
Then job repository maintains the success and failure of job .

![[Pasted image 20241110110340.png]]



We have to paste the Our csv file into the resources folder .

Here ,we go backwards , that is 
So here basically create Item Reader , Item Processor and Item Writer and then give it to the step and then there could be many steps and then give these steps to the Job Using Job method in that using `Job Builder Factory` .

Now we will give this job to the Job Launcher , So that it can trigger the job .
Which Will be Done in Controller   class .

Now to trigger a job using job launcher , we need the job object as well as the job Parameter .
Where we will give something as a key and as value , we can give the timestamp and will pass it to the job parameters .

Now in the end we will use `jobLaauncher.run()` method where we will pass the job object and the job parameter .
And can use here the exception Handling too .

When ran there will be multiple table created in the database .,

But the problem is by default , the Spring Batch is synchronously and Not Asynchronously . To make it fast , we have to do it Asynchronously .

So i have to create this task Executor method and ![[Pasted image 20241110115956.png]]

And now i just have to pass it in the step Builder Factor
![[Pasted image 20241110120108.png]]


Now it will process the job concurrently .
Here now the sequence depends upon the Thread Schedular .

But if i want to take the control of the thread , i can do it using `Spring Batch Partioning .`

Now to filter out something particular while processing the data -
We can do it in the `CutomerProcessor` .
![[Pasted image 20241110131436.png]]



**How it is done here  ---**
![[Pasted image 20241110131746.png]]

![[Pasted image 20241110131759.png]]

![[Pasted image 20241110131808.png]]


![[Pasted image 20241110131851.png]]

![[Pasted image 20241110131907.png]]

![[Pasted image 20241110131912.png]]

![[Pasted image 20241110131924.png]]

![[Pasted image 20241110180037.png]]
**How I have done it ---** Batch Job 1-


---- 

![[Pasted image 20241111162525.png]]


--------


***PARTIONING ----*** MULTIPLE THREADS -

![[Pasted image 20241110132137.png]]

Now for this , we will create a class that will implement a interface provided by spring batch `Partioner` . We will implement its abstract method `partion` .  Where we will write the logic we want .

We will create a bean of this class in our spring Batch Config .Now we will create a `Partion Handler` which will take the info of how much the threads , the no. of steps and the Grid Size .
The Grid Size is the no. of splits i want of the data .

Then we  will create the object of thread Pool task executor .

