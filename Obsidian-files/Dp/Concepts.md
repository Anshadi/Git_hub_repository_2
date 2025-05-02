It has two parts - memorization and tabulation .
![[Pasted image 20250418022246.png]]


![[Pasted image 20250418025341.png]]

![[Pasted image 20250418025451.png]]

As it would take same ways for us to reach the dest. from 2,1 grid as from 1,2 grid .

![[Pasted image 20250418030347.png]]

![[Pasted image 20250418043611.png]]

![[Pasted image 20250418043550.png]]

Here the parallel length is N that is for each , How many options i am getting and then the max length of the tree will be m that is target sum in case where in each level only 1 is substracted from it . So the final time complexity will be this .

![[Pasted image 20250419110115.png]]


Now it is` m*n` because  now for 1 value of m there will be n different values on substraction which will be stored and then reused , so for m values there will m*n different values 


![[Pasted image 20250419121319.png]]


![[Pasted image 20250419121652.png]]Unoptimized One -- 


![[Pasted image 20250419122206.png]]


here in brute force it is multiplied by m because it is the time taken to copy the older array into the new one .

In Optimized one it is given `m*m` space becoz each key has a value and each value at the worst going to be the array of m elements .


![[Pasted image 20250419122436.png]]



![[Pasted image 20250419132825.png]]


![[Pasted image 20250419133446.png]]


Its space and complexity of optimized and unoptimized will be same as above's optimized and unoptimized complexity .


![[Pasted image 20250419134653.png]]

![[Pasted image 20250419135031.png]]



![[Pasted image 20250419135136.png]]

![[Pasted image 20250419135705.png]]


![[Pasted image 20250419140042.png]]


![[Pasted image 20250419140520.png]]

however , we are also doing the suffix part so the final time complexity will be 
![[Pasted image 20250419140605.png]]



Now , we know the time complexity is -  `Branching Factor to the height as power `

and the space complexity will be the height of the tree that is m . but each of the m stack frame will have to store the suffix array which max. size can be m , so the final space complexity will be m^2
It can be seen as a vector where inside each vector there is a need to make a vector of size of  m .


![[Pasted image 20250419142203.png]]


![[Pasted image 20250419142235.png]]


![[Pasted image 20250419142347.png]]




![[Pasted image 20250419142521.png]]

![[Pasted image 20250419142555.png]]


![[Pasted image 20250419142632.png]]



![[Pasted image 20250419142802.png]]

![[Pasted image 20250419143031.png]]


A Important point to note is if we are sending a 2d array from all side and from invalid side , we are throwing a 1d array then when we concatenate the 2d array by concatenating their elements into one , we won't concatenate any thing from 1d array because we consider it as 2d array and inside there is 1d array but which isn't there that is , it is empty so nothing gets added and everything works alright .

Its Cpp version is 
![[Pasted image 20250419152823.png]]


![[Pasted image 20250419153050.png]]

![[Pasted image 20250419153234.png]]



![[Pasted image 20250419153436.png]]



#### Tabulation ---


![[Pasted image 20250419174734.png]]
![[Pasted image 20250419174759.png]]

A bit differently used dp here but it is great .
![[Pasted image 20250419174943.png]]


![[Pasted image 20250419175601.png]]




![[Pasted image 20250419180015.png]]




![[Pasted image 20250419180435.png]]


![[Pasted image 20250419180732.png]]



![[Pasted image 20250419180955.png]]


![[Pasted image 20250419181312.png]]


Last time we wanted to know whether we can generate that value or not but here we want to know the path if we can generate it , so last time , we put false everywhere , this time , we will put null everywhere 
![[Pasted image 20250419182412.png]]


where at the index 0 , we are going to put an empty array , as it is that's answer .
![[Pasted image 20250419182826.png]]


however , if we continue this , we can also generate all the paths , and one thing also , if we continue just exactly this , we will still get the answer as the next one will overwrite this .

![[Pasted image 20250419183058.png]]


so here just like previous time will be `m*n` but we still have to copy the array and hence the above time taken and likewise in the space , we will create a table of m but each can store at max of array of m , so as above .

![[Pasted image 20250419183349.png]]

![[Pasted image 20250419184310.png]]

so here in this question , we will let the algo to run to its completion , so that we can be sure that what we have stored in the last is the smallest one , and if we get two arrays , we compare and let the shortest one to be stored .

![[Pasted image 20250419184428.png]]


![[Pasted image 20250419184716.png]]


Now here in the can construct problem , We in place of seeing the index , we are seeing the charachters , that is the letter to which they can be reached and on basis of that keeping that Boolean value as true .
![[Pasted image 20250420094855.png]]

Now when we iterate again on only true indexes , we will check that whether from the char in the target letter any word starts in the word bank or not if yes add its length to the index and make it true .

Now in the end we will return the final index .
![[Pasted image 20250420095516.png]]


![[Pasted image 20250420095801.png]]


![[Pasted image 20250420100023.png]]

![[Pasted image 20250420100145.png]]

Its time and space complexity is similar to as the last one .

![[Pasted image 20250420100550.png]]

![[Pasted image 20250420101202.png]]

here for the first , we have the solution that is the empty array .
![[Pasted image 20250420101602.png]]

Here both the time complexity and the space is exponential because they are asking to generate every single way for the destination , so we have to iterate every letter with every possible ways .

Here the space is also exponential becoz we have m spaces as the target length , and in each we have a 2d array which can max store up to n as every element of the table it self is going to be a 2d array .


![[Pasted image 20250420101907.png]]


![[Pasted image 20250420102328.png]]


So in this case the brute forcce will be better , 
![[Pasted image 20250420102830.png]]





Sometimes in dp , when we want to update some things , like for each we want to update till a limit , but when we iterate the loop from forward , it updates somethings repeatedly that we don't want . Iterating the loop from back is the best idea , like doing the index 0 as true , maybe it at the start doesn't seem as  right but see it through once just for the sake .
More likely when something is working with bit set  and we want to convert it into dp .



![[Pasted image 20250421201744.png]]




![[Pasted image 20250421201821.png]]
![[Pasted image 20250421202141.png]]


![[Pasted image 20250421202246.png]]

![[Pasted image 20250421202445.png]]



![[Pasted image 20250421202507.png]]




![[Pasted image 20250421203128.png]]![[Pasted image 20250421203254.png]]

Slow recursive one --
![[Pasted image 20250421203447.png]]

Memo solution --
![[Pasted image 20250421203517.png]]

Memo in bottom up sol - having lower constants --


![[Pasted image 20250421215204.png]]

It is bottom up dynamic solution .
![[Pasted image 20250421222045.png]]

![[Pasted image 20250421222307.png]]


![[Pasted image 20250421222452.png]]


![[Pasted image 20250421222701.png]]


![[Pasted image 20250421223023.png]]



![[Pasted image 20250421224000.png]]



![[Pasted image 20250421224213.png]]


![[Pasted image 20250421224647.png]]


![[Pasted image 20250421224733.png]]



##### Maximum Profit In Job Scheduling - 

 we can make this question into take job or not take it and if we take then see from the end time for the next .
 ![[Pasted image 20250423014009.png]]

![[Pasted image 20250423014232.png]]Then give the max to the parent ,  here we can memoize so that we can use it efficiently so dp .

Now we have to also search the new start which is equal or more than this end time , so here we can use binary search  .
![[Pasted image 20250423014638.png]]

But for binary search ,  we need a sorted search space , so we have to sort the array of start time .


![[Pasted image 20250423015647.png]]
![[Pasted image 20250423015850.png]]


![[Pasted image 20250423015914.png]]

Bottom up dp . using lower bound function --
![[Pasted image 20250423023245.png]]
see here we are building the solutions as we go backward , in such cases we start from the last and then move back to the first where the answer is stored finally , here what happens is we start from the last and see after that but it will return base case , so it stores the base case , now when we move a index backward and again try to see after that , we either get the base case or the value stored in the dp next to it , which we can use to build the solution .

And see here also the dp is of the size of 1 that is the n+1 .



recusion --
![[Pasted image 20250423023331.png]]



	Nice Question and unique concept 

![[Pasted image 20250423183600.png]]

Ok here in the else part , the `*it` = num , here the chosen element is being replaced by the num , as now the pointer's value is replaced by the num value .





Dp On Interval Problems --- 

![[Pasted image 20250501182721.png]]


![[Pasted image 20250501182753.png]]


Break the problems of the intervals into the subproblems of the intervals itself and then think about how we can solve it easily and keep the both side indices in such way too .




These are some common patterns in dp  and how to think if that pattern is provided .
![[Pasted image 20250501183555.png]]


![[Pasted image 20250501183618.png]]
![[Pasted image 20250501183624.png]]




![[Pasted image 20250501184331.png]]


