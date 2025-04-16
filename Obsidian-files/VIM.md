Here in the array , it is 0-based indexing 
and has no data types 
![[Screenshot 2025-02-10 143756.png]]
ls -ltr  - to get the all the file names and the permission related to it .

![[Pasted image 20250210153423.png]]

Here to delete a line  , go to the starting of the line and press two times d .

Here in case of if-else , we use  -- if [ ] -then -else -fi 
however , we will use `[[]]` after if , these are introduced in zsh and are the enhanced version of it , it allows more functionality . 
Inside there must be a space at the start and end inside the bracket .

Here `fi` marks the end of the file .

Incase of elif  -->  if[] then elif[] then else fi 

![[Pasted image 20250210163102.png]]

To execute any function use `;;` after the function  name like 
`date;; or ls;;`
but use it after whole statement of function i.e.  
```
echo ""
date
echo ""
;;
```

