function inside the classes  -- methods 
![[Pasted image 20240402052411.png]]

Inside any class , "this" keyword tells which object is trying to access that class .

### Declaring object --

` `Person person1 = new Person();
``
whenever we use new keyword then
Inside memory -- inside memory heap -- space allocated -- which stores our new object .
here after new keyword -- Person() --- we have made a special type of function known as constructors . --- used to construct objects .

Now if we don't make non parameterized type constructor in java , java makes it on its own by default . 
Here Person() -- non parameterized constructor , we haven't made it inside class .

constructor types 
1. Non parameterized constructor -- have no no parameters inside in it.
		``Person person1 = new Person();

2. Parameterized constructor - here we have to send parameters inside while     creating objects.
		`` Person person2 = new Person("aditya" , 34 ,56)``
		now here we have to make a constructor inside class i.e 
   ```
      Person(String name , int age , int height)
      { 
        this.name = name ;
        this.height=height ;
        this.age = age ;
       }
    ``` 
    
3. Copy constructors -- these are user defined. It just uses another object value to create its object value . 
		  ![[Screenshot 2024-04-02 060818.png]]
		here person2 is a copy constructor .
		
     Note -` if we are defining copy constructor then we have to also define the normal constructor we can't leave it on by default` . as above shown in code as default constructor.
   
constructor properties -
1. Have same name as class name
2. Can be called only once for any object 
3. they doesn't return anything like --  int, void  etc .

Now as there are constructors , there are destructors too , to delete the object created . 
In c++ we can  just create a function named as destructors just like constructors but in java , it is by default because it has GARBAGE COLLECTORS -- which automatically deletes unused variables.

### Oops has 4 prop .
#### 1. Abstraction -
It means showing only important info to the user and hiding other data .
Classes and functions which are only concept and whose function we don't have to use .
Ex-![[Pasted image 20240402164628.png]]Here we don't have to walk the animal ,we can walk chicken or horse .so no use of that class then it can be of abstract type .same with its function .
if its child is going to have its walk class then there is no means of using walk function of Animal class so can make it abstract type .
if we try  to initiate object of animal class [ means not creating class with using extend keyword ] with this type then it will give run time error - can not initiate the type Animal


[It can have final methods which will force the subclasses to not change the body of the methods.]


#### 2. Encapsulation -
Makes data hiding possible.  
making data and objects inside the class is known as encapsulation .

![[Pasted image 20240815141959.png]]![[Pasted image 20240815142023.png]]
#### 3. Inheritance 
 when one class( Child class ) inherits the properties and method of another class ( Parent  class ) .
 It enhances reusability .![[Pasted image 20240402064703.png]]
 Here we use extend class .
 Types -
	 1. Single level inheritance -![[Pasted image 20240402081645.png]]
	 2. Multi level inheritance -![[Pasted image 20240402081553.png]]
	 3. Hierarchical Inheritance - ![[Screenshot 2024-04-02 081815.png]]![[Pasted image 20240402082046.png]]
	 4. Hybrid Inheritance -![[Pasted image 20240402082117.png]]Here we are using  all above three . 
	 5. Multiple Inheritance - We use interfaces for its implementation . 
#### 4. Polymorphism
 Types-
	     -Compile Time Polymorphism (function Overloading) --- 
		      It means different functions with same name .
		      Here the compiler detects the polymorphism at the compile time only. 
		      So here on compiling time code error comes (which is more good).
		     Ex-![[Pasted image 20240402062634.png]]
		     now we will only print somefunction.printinfo() -- and according to the parameter passed it will use that function .
		     Rules- there should be a differentiating factors inside it i.e
			     1. Either there return type should be different i.e if one is void then another int type .
			     2. if it is same then its parameter type should be different if one is string then another is int type .
			     3. if it is also same then the no. of parameters should be different .
	     -Run Time Polymorphism (function Overriding ) ---


Packages -- Inside it there is relative code is there .
	Type -
		-Built in Packages
		 -User defined Packages 
		 
Access Modifiers -
      - public- can be accessed by any class in it and by any file or package.
      - private - can be accessed only within same class and not even by it's subclasses or same package or another package .
	      -now to access these we have to use setter and getter .![[Pasted image 20240402163134.png]] ***We can even make the setPassword function private so that our get password can set any random password then return it .***
	      like ex - [[Pasted image 20240402163526.png]]
      - protected - can be accessed by all classes in our package and if in another package then only accessed by sub classes .
      - (package access )default - if not writing anything while creation then it will be default type . It can be accessed by any class in our file but can't be accessed by any other package or file.


Constructor Chaining - Whenever we call an object of an derived class then first the parent class methods are called then the derived class methods are called .


### Interfaces --
We can't have constructors or non-abstract type functions inside interfaces .
Here we will implement it using keyword Implement .
here we will just give the name of function . when we derive class of it , we have to again implement it .![[Pasted image 20240402183942.png]]
Now we can use Multiple inheritance( i.e inheriting multiple class properties inside itself ) in java using interface .![[Pasted image 20240402184241.png]]

Static Keywords --  we use this keywords for that class ,method or variables which will be common for all classes . For these memory is allocated only once . However memory is allocated every time for objects .
it can be used for any properties or functions or blocks or with subclasses .

Here we can directly access these static variables of a class without defining its object as it is its class part.
**In static context-- there is no use of this keyword **
Ex- ![[Pasted image 20240402184759.png]]
We can  use this static keyword with the function inside the class too . 
![[Pasted image 20240402185046.png]]


## How Code Works --
![[Pasted image 20240402191840.png]]
Here if the system has Java runtime Environment then it can run any byte code converted by jdk .
![[Pasted image 20240402192121.png]]
native code are the code which are understood by machines .


There is object requirement to call non-static class .
instance variables are properties.

recursive constructor call -- compile time error
the this(constructor) should be the first line inside  constructor calling. 


(  JDK  (  JRE  (  JVM  )  )  )

