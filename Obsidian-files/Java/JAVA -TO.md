

In java by default , it is double value rather than  float

So for float 
float num = 5.6f

Boolean - Used as True or False not 1 or 0\

Long No. - 
long l = 5434l

can get the binary 
using int num=0b101 //equals 5

		int num = 100_000_00   //same as  10000000

Can assign int value to double - implicit conv.

	double num = 12e10      //will give 1E11 automatically

can increment the char too as well as num (post and pre - inc. both)
char c = 'a'
	c++                                       // c= b
int num =3
num++                                        //on sout 4

Smaller range data can be assigned to larger range implicitly 
For explicitly 

		byte b = (byte) a;   //returns a % b-range 
		
But all above in same data type .


Double + Int = Double

When nothing assigned to 
integer -> 0
String ->Null

***2 X 3 +"AB"  --->  "6AB"***

Type - Promotion 

if we have two bytes a and b and their operation result extends their range , can promote them to int
int result = a * b

Can Use ? :   and switch cases too  

Source file(  .java file ) -> JDK -> complied ->  .Class file  - Byte Code ->JVM -> Object

Compile Code -> JDK
Executes code -> JVM

Inbuilt Libraries  -> JRE  <- Verifies byte Code and classes 
![[Pasted image 20240518154946.png]]


Every Time we compile using javac , we have a class file .

##### JVM --
JVM contains a memory stack and a memory heap .

Local var are stored in stack .

**when we use new keyword , it creates a new object in heap memory which has a address .** which is pointed by the reference variable stored in the memory stack .
![[Pasted image 20240727234533.png]]
Object will have two section , one will be storing instance variables 
and 
will have method definitions and its code is stored in stack .

Instance variable - that belong to a class and not to a method .


Ex--- 
if    compute obj = new compute()    
then  here the obj is a reference variable  which is stored in stack along with the address of object created in heap with the help of new keyword .

Now if we created a var named num in our class outside method .
if now we created two obj for it though they will have diff. address in heap as two stacks in heap but will store same address for num .

But change in num through one obj won't reflect on another obj .


Array Creation -- 

 int num [] = {1,2,3}
 int num[] = new int[];
 int num[ ] = new int[3]

2-D Array
int num[][] = new int []  []
int num[][] = new int [3]  [4]
 
By default it contain 0 . 


We can even define the size of internal array as different -->Jagged Array .
But have to specify for each individually  .
![[Pasted image 20240518164051.png]]

In Java String is a class .
we can create its var using new keyword to 

String name = "Its "  
= = = 
String name = new String("Adi")

We can print its hash-code using 
sout(name.hashCode()) ;

To get any char in that 
name.charAt(0);

To concat two strings
name.concat("Ok") ;

could also do 
name = name + "Ok"


In JVM , We have area named String Constant Pool 
objects created in it .
Now if we make two string equal to that object .
Those two will point to the same address .

So var created in stack having address of the object . 
![[Pasted image 20240728000000.png]]
![[Pasted image 20240728000041.png]]
![[Pasted image 20240728000223.png]]
![[Pasted image 20240728000253.png]]
Mutable String -> String Buffer  -> Thread Safe
Immutable String -> String Builder  -> Not Thread safe

String Buffer gives us the buffer size .
We can get its capacity 

StringBuffer sb = new StringBuffer("Navin") ;
sout(sb.capacity()) ;                                     //By default 16 characters

Now when we added string navin to it , the size became 21 characters .

But sb.length()  will give me 5 .


**Used To - Minimize data relocation
Why it is used because suppose we used a string  and then added  some data to it and there is no contiguous memory left , we have to relocate . but in this case we already have a buffer .**



In string Buffer , we can use 
data.append()

To convert string buffer to string
String st = sb.toString()

Can delete a char at any index too 
sb.deleteCharAt(2) ;       //    Navin ->nain

Can insert a string to using index number 
sb.insert(3, " NOPE ");      //nain -> na NOPE in


can get the substring too
sb.substring( starting Index , ending Index)

Can  set length of the string too
sb.setLength(30)                    // empty place will contain null



##### Static  --

Static var --  Called by class name  -- saves memory

If we make a instance variable as static then that variable will be common for all the objects .
So if changed made through one object  , will be changed for all the others .
If common then means we can call it by the class name too .
Can use static var in non-static methods .

Through Constructor , we can give by default values to both static as well as instance variable .

But if we gave static var inside constructor , it will be initialized every time the  object is created .
So we give default value to static var in static block .
It is called only once .

```
static
{
var_name=default value;
}
```


Every time we create a object two things happen -
Class Loads and Objects instantiated .

Class is loaded only once .
Every time we load a class , it calls the static block .
As Class loading happens first , Static block is called first .

And then calls constructor to create  object .

***In JVM , we have area called class loader which contains all the class loaded .***

Now if we don't create Object , Static block won't be executed .
However if we want to execute it , we can use 
Class name "Class".
Its Method forName is used to load the class .
It throws an exception named **ClassNotFoundException** . 


```**Class Loading**:

- **Class Loader**: In the JVM, the Class Loader is responsible for loading classes into memory. It loads classes when they are first referenced in the code.
- **Class Loading Process**: Each class is loaded by a specific class loader, which belongs to a hierarchy of class loaders (e.g., Bootstrap, Platform, and Application class loaders).
- **Class is Loaded Only Once**: A class is loaded into memory only once, regardless of how many objects are instantiated from it.

```


![[Pasted image 20240518185002.png]]


##### Static Method --
For static Method , we can use the class name to call it  .

For a non-static method we will always create a object . 


We can  use 
Static var in Static Method 
Can't use 
**non-static var in Static Method as they will be different for diff. objects .**

But can use it Indirectly  --- through object reference 
By passing the Object to the static method and then accessing it inside . i.e
We can access instance variable through static method by passing it the object reference . 
![[Pasted image 20240728001754.png]]



We create Main method as static because otherwise it would be deadlock .
That is --  if we didn't made it static , then we have to create a object of the class it is in , But now when main is not static It won't be executed first when code gets executed ,then we can't create any object . So here lies the deadlock .

![[Pasted image 20240728002321.png]]
![[Pasted image 20240728002401.png]]


##### Private -- 
Private var is accessible in the same class .
Now we can access the var through setter and getter methods . -- Encapsulation.


###### this - Keyword --- 
We can still assign values in setter and getter method without this keyword but problem happens when 
Both local and instance variable inside the class has the same name .
In that case ,  the local variable will assign itself the value and the local var passed through the setter method will still remain unassigned .
As preference will always be given to local var .

So we use this keyword to asses that it is a instance var and hence another one is local var .

Or 
To imply  same we have to create a object there in the  setter method and through it we have to call the var to tell the difference between them .
But then it will change the value of the new object created inside the setter method and not that of object that called that setter method .


So we can in place of creating a new object can pass the reference of the old object while calling the setter method and assign it the new reference variable created , so its value gets changed  .
![[Pasted image 20240518200912.png]]
So by default java to make the code redundant , provides the this keyword which works the same as above .
As this refers to the current calling object .


##### Constructors -- 
Every time object created Constructor is called .

Method Overloading -  Two Method with same name and different parameters .

Constructor types -
Default Constructor 
Parametrized Constructor 


If a class A is being extended by another class B then when the object of B is created then
first the constructor of the class that is being is extended will be called that is A and then B .
![[Pasted image 20240519102103.png]]
A is super class here and B is sub class .

Because Every Constructor in java has a method named super( )  even if it is not mentioned .
![[Pasted image 20240519102448.png]]
So what's happening is when constructor of B is called then the super gets executed first which takes to default Cons. of A . Now if A had extending any other class ,  we would have reached there .

If in super we explicitly passed an parameter then it will call the parameterized cons. if not prs. gives error .


**Every Class in java extends the Object Class even if not mentioned .**
But Not the primitive Ones - Int , Float etc .

Not Every frameworks work with non-object type .  Ex-  Collection Framework .

Here this() will execute the constructor for the same class .
![[Pasted image 20240519103113.png]]
Through this we can call both the constructor that is parameterized and non-parameterized through one call through main .

So 
Main ->  B Obj -> B param. Const. -> this() -> default Const. B -> Super() -> default Const. A 


##### Naming Convention -

class and interface -- Calc
variable and method -  marks
constants --  PIE



##### Oops Concept -- 

A obj ;  --> Reference Creation 

**We can create the reference of interface but can't create its object .**

obj = new A()   -->  Object Created . <-- reference Object
new A()  <-- stored in heap. <-- Anonymous Object <-- can't reuse 

Can use in which have to call a method of a class only once
new A().show ;


 To Inherit we use the keyword extend , it allows the class to have its properties along with the class being inherited .
Multi-level **Inheritance** Possible .

Inheritance -  Is , Has relationship .

No Multiple Inheritance - as if same name method in both inherited class , won't be able to determine which to call due to Ambiguity .


In multi-level Inheritance , if method of same name called from main then the method of that class will be called which will be first from going down to up .

even if above class have method of same name , it will get overriden by below class while extending .

Folders <= > packages 

Package part of another Package  < = > Folders inside Folder

Whichever class we use in java belongs to a package .

Now we can put our classes inside packages. Using
package tools.

Main Method is not inside any package .

If we are inside another package we can import that class using package name. class name .
i.e.
import tools.Calc 

System.out.println()
Now System class is also a part of package java.lang , but
***By default every java file java.lang is imported , even if not mentioned .***

In folder structure use / , in packages use .  

java.* is different from java.lang* as
*<-- means all the files that is class in that package is imported and not all the folders that is packages are imported .


Our package name should be unique because it can be used in freelancing so we can do that by reversing our domain name as domain name will always be unique .


##### Access Modifiers --

###### Public -
A variable which is not public is not visible in other packages . If in same package no problemo! .
Can't have two public class  in same file . 

###### Private--
Private var can be used in same class irrespective  of the package . setter and getter 

###### Default --
If not mentioned default , can be accessed in same package .

###### Protected --
Var can be used inside the package and outside can be used only after extending that class.


![[Pasted image 20240519113203.png]]


Try to keep method public
Instance var -> private 
method -> most time public but some cases protected when want to use in only in subclass in another package .



##### Polymorphism --

Overloading --> Compile time Polymorphism -> decides at comp. time which method exec.
Overriding  --> Run Time Polymorphism --> decides at run time --> case of extends with same method name .


##### Dynamic Method Dispatch -- uses RTP  <-- decides at Run Time -- Upcasting

If a class A is being extended by class B then we can create a reference var of class A and a object of class B and call the method in class A .![[Pasted image 20240719061712.png]]

A obj = new B() ;
type of parent = object of child 

Still Same method name -> down to up .

![[Pasted image 20240728010716.png]]

We can assign a old var to new obj , it will just change the address in stack to point to new obj in heap .

obj = new A() ;

But can't do 
obj = new D()
If D is not extending A .


##### Final --
Final var - Once assigned never changes .

Final class - If i don't want my class to be extended  that is inherited , make it final .  

Final Method - If i don't want to let Override my method through the extended class using same method name , use final .



##### Object Class -

Every Time we print object , it gives the class name and some code .
i.e. Laptop@9854f90

So What it does internally is 
It calls `object.toString()` Method which 
return object.getClass.getName() + @ + Integer.toHexString(hashcode())

What hashcode does is creates hash code based on values we have , it tries to create a single Number string of all the data we have 


But We can create our own toString Method in the object class to print something that we want when we print the object .

now we have method named `.equals` which checks whether they are equal or not .
If we create two object with same value and then check 
1.equals(2)   //false  as what it does is , it chks  this == 2 in object class .

Because in java , we are using equals for two object they should have same value and same hashcode .

![[Pasted image 20240719061404.png]]


If we want to chk values we  can create our own equals method .
![[Pasted image 20240519124958.png]]


OR We can generate equals and hashCode from Source Action through IDE.


##### Type casting In Oops- Down Casting & Up Casting --

If B extends A 

A obj = (A) new B();      // UpCasting
obj.show1()                              // method prs in class A 

It means we are creating a obj of B but refer to A i.e. superclass.

A obj = new B()
obj.show2()          //not possible as reference type A and A don't know that show2 exist.
![[Pasted image 20240728011620.png]]
So we can do

B obj1 = (B) obj;        // Downcasting 
obj1.show2() ;           //here we saying that whenever we assign we want it to be Obj of B                                            though reference type is A .


##### Wrapper class --
As Not all the framework works with primitive type , we use Wrapper class .

For Every Primitive type - We have a class .

if we are working with framework , which supports non-object , we can still use primitive .

###### Boxing -- 

int num =7;
Integer num1 = Integer(num);   // Boxing -- Taking a primitive value and storing it in                                                                                     Primitive or a Wrapper Object  
Or
Integer num1 = num     //AutoBoxing  -- automatically converts to the object 

*int num2 = num1.intValue() ;  *  //Unboxing getting value from object type to primitive type.

Or
int num2 = num1   // AutoUnboxing   -- Happens Automatically  

Integer class has also --
parseInt() which converts string to Integer .

String str = "12";
int num1 = Integer.parseInt( str ) ;



##### Abstract Keyword --

We can instead of defining a method i.e.
public void drive(){  }

We can declare a method .
public void drive() ;

So later someone can extend it and define its feature .
So we can make it abstract i.e.
public abstract void drive () ;

**Abstract method can only be present in abstract class . 
But Abstract Class can also have Normal methods .
AND
We can't create the object of the abstract class but we can create the reference of the abstract Class.**  

So If we are extending the abstract class , it is must to define its abstract method .

If in case there is a class that is not able to define all the methods in the abstract class , it is extending ,then this class has also to be abstract class .

**Concrete Class** - Class which can implement all the abstract method . We can make obj. of Concrete Class .

So now what we can do is 

**reference var obj = new class extending abstract class Name ()**


##### Nested Class --
If class B is inside Class A , then To make object of B , we first have to make obj of A .

A obj = new A();

**A.B  obj1  = obj.new B()**   //needs to make the obj of upper class to make object of inner class .

To call B's method
obj1.showB() ;


But if the inner class is static then we can
**A.B  obj1  = new A.B()**
![[Pasted image 20240813190120.png]]
![[Pasted image 20240813190145.png]]
Can't make outer class static .




**Anonymous Inner Class** ---

Here we implement the Interface methods without using another class that implements that class  .
On Condition that we have to define every interface class method .

To change the implementation without making new class --

Show func. was already defined in A .
![[Pasted image 20240520182050.png]]
It is Anonymous class and a inner class as inside demo class .


![[Pasted image 20240520182747.png]]Though A is a abstract class but we can make obj of it here because here we are not making the obj of class A but the obj of Abstract Inner Class .

If there would be another abstract method , we  defhave to define it too here . 




##### Abstract Class & Interface ---


We can pass another class object as a parameter to other object but we have to first make object of that class .
![[Pasted image 20240520183716.png]]
![[Pasted image 20240520183736.png]]

But now the devApp() method only requires object of laptop and if we passed the object of Desktop (suppose ) then error . 
So what we can do is , we can create a abstract class Computer which is being extended by both  laptop and desktop having a abstract method code  .
Then on creating obj of sub-class , we can use ref. var. of parent class .
And change DevApp parameter to (Computer lap)


As We can create 
ref var. of parent class = new obj of child class


Now we can also make the  computer class to Interface from abstract . 
And in place of extend , we use Implement Keyword .

**Now the inside method won't be abstract . As by default every class inside interface is public abstract .**

**Interface just gives the design .
We can create its ref. var. but can't create its object . 

Although , we can create  the obj. of class implementing that class.

now when we use implements , it is must to define all the classes. 

**We can create var in the interface , but by default they are final and static .**
So we have to declare them on defining time only .
Now as they are static , we can directly use them using class name .
Ex-   A.age 

The var is final because when we use implement , we only access the method and 
Interfaces don't have memory in heap . So if we don't make them final , there is no use of it .
![[Pasted image 20240719063233.png]]

![[Pasted image 20240813200225.png]]




We can Implement Multiple interfaces .
And One interface extends another Interface .
![[Pasted image 20240520190713.png]]


##### Enumerations --

 Enum is a  class

enum Class_Name{
		obj1,obj2,obj3,obj4               //Named Constant
}



Here diff. constants have diff. numbers starting from 0.
We can get that number using function ordinal() .
![[Pasted image 20240521003311.png]]
will give 3 .

Now we can get all the value using values() .
Status[] s = Status.values() ;
It will return an array .
![[Pasted image 20240521003817.png]]



Can compare status using if -else 

s = Status.pending

if(s== Status.success)
	printf("Do Something");
	


Can use Switch-Case 
switch(s){
	case Running:
}

We can't extend enum with another class otherwise all other prop. is same . We can define Constructors and variables , etc. .

**By default Enum in java extends java.lang.Enum class .**


We can give value to these objects rather than by default ,
By obj1(value) ,obj2(value) ;

But to this we have to make this constructor .
```
enum Laptop{
macBook(2000) , XPS(3000) , Lenovo(4000) ;

private int price;

private Laptop(int price){ //Constructors
		this.price = price ;
		sout(" in Laptop " + this.name() );
		
}       //enum.name returns name of the current constants


public int getPrice(){
return price ;
}

public void setPrice(int price){
this.price = price ;
}


}

Public class Demo{
Public static void main ( Sring a[]){

for (Laptop lap: Laptop.values())
	{
		sout( lap + " : " + lap.getPrice() );
	}
}
}

```
Output -->
![[Pasted image 20240521011126.png]]


**But now if we don't mention the value of any object , it will be error as before it was handelled by default constr. but now we have made parameterized const.**

So we also have to make a default constr. in there , if want can give min price too in that .
```
private laptop(){
	price = 500 ;
}
```

Using the private cons. here and not public as we are creating the obj in the same class itself .



##### Annotation -- (metadata) supplies extra info 

Bugs -- logical Problem -- 2+2 =5 
It helps us to know the problem .

For Ex - 
if we wrote a func. in class A and then B extends A and wrote same class in B but with error in func. name spelling , if we wrote 
@override before this class , we will see the error .


Some Annotations woks with variables , methods and class levels  ,

Ex- For use with class level 
@Depricated (indicating this class is deprecated )

For method level --
@Retention --  it tells the level of retention that is do we need it till compilation time or at runtime also .



##### Different Types of Interfaces ----

After Java 8 , we can also define methods in the interface .


![[Pasted image 20240813202459.png]]
![[Pasted image 20240813202531.png]]
- Normal -- Interface with two or more methods . So Now in an interface , we can only have default or static or a abstract method and it will still be called as an Interface .

- Functional (SAM)  -  Single Abstract Interface -- Only One Method

- Marker -- No Methods -- Blank Interface --  to update something to compiler
		- Ex - Serializable Class 
		
		- Used for Serialization -- It means storing object from the heap to the Hard drive .  Deserialization means retriving data from Hard Drive to the heap .
	
		- After Serialization , we can destroy the object .
	
		-  For Ex- we installed a game , played till a level and then deleted the game , now due to serialization our game status was saved on the Hard drive and now when we again installed the game , our data gets deserialized and  our progress was saved .




##### Functional (SAM)  -  Single Abstract Interface -- Only One Method
	- To specify that this interface is SAM , we can use @funtionalInterface to check for compile time error .

	- Now if there is a interface class with single method , we can use that either by implementing it by other class or by use of anonymous Inner class . 
	- In JDK 8 , we got Lambda Expression .


##### Lambda Expression --    ( -> ) <---- (Lambda)
	  - It is Only Applicable to functional Interfac . 

	As on using anonymous Inner class on A we already know that it only has one function that it will define and we know its method signature , there is no need to write it . So here we can use lambda expression .

![[Pasted image 20240521040235.png]]
If we have only one statement , we can do ![[Pasted image 20240521040035.png]]

If the method accepts parameter , we can send it by putting it in the round empty bracket .
And if we have only one parameter , we don't even need the brackets .
![[Pasted image 20240521040453.png]]



In the case the method returns something - Anonymous Inner version - 
![[Pasted image 20240521040713.png]]

In case of Lambda 
![[Pasted image 20240521040932.png]]


##### Exceptions  --

Error types
- Compile time Error - Comes at compile time 
- Runtime Error - when error happens at runtime . --> Called Exceptions .
- Logical Error - Error happens when result not same as expected

Ex - of Errors -- 
Thread Death error , IOError , Virtual machine error , Out of Memory error .

We can trace the exception using 
.printStackTrace() 

RunTime Exception -- Unchecked Exception .

If we intentionally want  to call the catch block when it matches the certain condition , we can use 


###### Throw --
Throw keyword  - here we have to make the object of the exception class , we want to throw .
![[Pasted image 20240521092642.png]]

And now if we want to send some message with it too , then we can pass it in the round bracket .
then inside the exception , we can print e or e.getmessage()  ;


we can create our own exception 
By extending it to the class Exception .



 .To print the  message being send to Our_Exception , 
Inside Our_Exception we use super keyword to send that message to the parent class that is exception class


 
 We have to catch Our_Exception and to throw it , we have to make object of Our_Exception .
![[Pasted image 20240521093324.png]]

But we have to send the message to the exception as we have made parameterized exception .


###### Throws --

The class on which it is used means that class won't handle that exception but it will throw it to the class calling it .
If its parent class is not handling it , it can send it to its parent class using throws .

For Ex - if class B and C is throwing same exception , and is not being handled by it can use throws exception keyword and then above in Class A , we can put that in the try and catch block .

**Never use throws Exception keyword with the #main() method as it is called by JVM and if it handles exception , then it will stop execution .**



##### Take Input From User --


**PrintStream  Class  ---->  Println Method**

	- Now if this println method has to be used , we have to create its object , but its already created with name of "out" of class Print Stream .

Now this #out is a static object hence it will be called with the help of its class #System and  hence 
System.out.Println() 


Now as there is out , there is #in object too used for method read of class **Input Stream** and this #in object is in class #System .

Now this  **System.in.read()**  throws **IOException** which we can handle there .

However it gives the **Ascii** value of the charachter entered . So we can minus it by 48 .

So Not Ideal Method .

So to read multiple charachters we use

###### BufferedReader --
	It is a class that is used by io .
	It belongs to the Java.io  package .
	
	So now when we have to read , we have to pass the object of the Input Stream Reader to the buffered Reader .
	And  Input Stream Reader requires the object of the Input Stream . It is the same one we were using with the System.in.read() case . 
		The IS object already created named " in " is static and belong to System Class . So we can pass System.in to it.
	And Now we can use the object of buffered Reader  to read .

![[Pasted image 20240521151552.png]]
As readLines gives us string , so we used ParseInt () .

bf.readLines throws Number Format Exception .

Buffered Reader is a resource like file or database connection , we need to close it manually to prevent data leak . 
So bf.close()  ;


Or we can use Scanner introduced in JDK 1.5 
Scanner sc = new Scanner(System.in) ;

It also takes System.in object .




##### Try-Catch-Finally --

Finally can be used to close resources .

One Method is #try_with_resources --

Here we define the resource inside the Try round brackets itself and as that try block gets executed , the resource automatically closes .

If there is any class or interface that is extending #closable , it can be automatically closed using try and resources .


Buffer Reader --> Extends Reader --> Implements Readable and Closable --> Closable extends --> AutoClosable  .![[Pasted image 20240521164451.png]]



##### Threads --

obj.setPriority(Thread.MAX_PRIORITY);
.getPriority() ;

Thread.sleep( time in ms ) --> goes into waiting state .

e.printStackTrace() ;

In runnable class , we don't have start method . It belongs to thread class .
So if we have to use the start method , we have to create Object of Thread class .
**Now thread here takes a Runnable object as its parameter , So we have to create the object of reference variable Runnable .**

i.e Reference of the Interface = Object of the class
![[Pasted image 20240521182434.png]]


We can also use Anonymous Inner Class here .
Now inside Runnable we can create the run method itself .
![[Pasted image 20240521182705.png]]

*We can use Lambda Expression , as Runnable is a Functional Interface . *


##### Mutations --
Change either in var or in anything else .

So if we are making mutations , we must work with thread safe methods .

Thread Safe - One Thread at a time .

t.join() -- Using it ,thread waits .

To ensure that only one object works with method at one time , we can use Keyword" Synchronized " with the method names .

**Thread States** -- 

- New - when we make object using new
- Runnable - when start method is called , goes into Runnable state or when thread is waiting for the schedular .
- Running - when run ( ) is called 
- Waiting - when wait() or sleep() , etc. calls . Now when notify() is called it returns to Runnable  state .
- Dead -  when stop ( ) is called or when the thread work is done .



##### Collection --

3 Types -- 
Collection Api  -- Concept  -- Framework
Collection -  Interface --> belong to java.util package
Collections -  class 



Iterable --->  Collection Interface ---> list , Queue ,Set ,Map

Ex -
Collection nums = new ArrayList( ) 

![[Pasted image 20240521223055.png]]
However , here it is returning the object , so on defining we have to mention the data type .
So , if have to access each using index , have to define its type .

	Collection<Integer> nums = new ArrayList<Integer>( ) ;

Here we use generics , as it provides data safety . Now we can't store any other type of data which is not suitable .

![[Pasted image 20240521224543.png]]

And now collection has list , which is more suitable for these type  of operations , so 

	List<Integer> nums = new ArrayList <Integer >() ;

To get index of any value
nums.indexOf(2) ;

To get value from index 
nums.get(Index) ;

	Set<Integer> nums = new HashSet<Integer>( ) ;
	Set<Integer> nums = new HashSet<Integer>( ) ;


Treeset extends AbstractSet and Implements NavigableSet , Cloneable ,  java.io.Serial 
where Navigable_Set extends Sorted_Set .
It gives Sorted Value .


Collection extends Iterable class which has method Iterator .

	- Iterator<Integer> values = nums.iterator ( ) ;    // nums.iterator returns a iterator itself 

		while(values.hasNext() )     // Till values can iterate
	sout(values.next )

We can do the same thing with for loop .

Map doesn't extend any collection class but it is part of collection framework .

Map<String , Integer > students = new HashMap<>();

students.put(key , value);
students.put(key , value);

sout(students) ;
Keys are unique . On repition , its prev. value gets overwritten .

To get all the keys in the set 
sout(  students.keySet() )

here each value can be getten using for-each loop on keySet 
to get the value from `student.get(key)`

We also have HashTable which is synchronized version of HashMap . 


##### Sorting --

For Sorting , Either We can use Comparator or we can implement Comparable .

If arraylist nums 
then 
		**Collections**.sort(nums , com )    // where com is the comparator 



Comparator  <--  Interface <-- Has method named compare <-- we can write our own logic of sorting .

Collection works with the Wrapper Classes 

	Comparator<Integer> com = new Comparator(){

		public int compare( Integer i, Integer j )     //if we return 1 it swaps
		{
				if( i%10 > j%10 )
					return 1;
				else
						return -1 ;    //if we return -1 it doesn't swap
		}                                //here if i return 0 then it will consider                                            them equal and hence no swap .
}


Example -- ![[Pasted image 20240521235016.png]]
		Inside Main Method 
		![[Pasted image 20240521235113.png]]

Now during sorting , if we don't want to send the comparator class to then we can implement Comparable . and it has interface named compareTo , So we have to define it .

```
Class Student implements Comparable<Students>
{
		public int compareTo( Student that)
		{
			if(this.age > that.age)
			return 1;
			else
			return -1 ;
		}
}
```


Comparator is a functional Interface , So we can use Lambda function 
So we can write 
![[Pasted image 20240522000210.png]]


Another way to add values to the list is 
`List<Interger> nums = Arrays.aslist(4,5,6,7)`

Now if we want to change the values to its double and add it to the sum for those who are even , we can do this using for loop
Or we can use stream Api.

Stream --> #java.util.stream package 
It provides methods like filter , double and reduced etc .

#For_Each
For printing each value of an array 
nums.forEach( n -> sout( n ) )

For Each takes the object of an Consumer .
Consumer  is an functional interface  ---> java.util.function and the only method it defines is accept .


```
Consumer<Integer> con = new Consumer<integer>()
{
	public void accept( Integer n)
	{
		Sout(n)
	}
}
nums.forEach(con);

```

**What for each does is it gives one val at a time and it goes to con of consumer which has only one method accept which decides what to do with that number .**

```
As it uses Lambda Expression 
Consumer <Integer> con = n-> Sout( n )

Now as for each accepts this con . we can directly put it there .
nums.forEach( n -> Sout( n )) ;

So now we don't need to create ref. for consumer .

```

Now do this with Stream Api .
Now with stream we can perform any op. and its not going to affect nums .
But once we used the stream , we can,t reuse it .

![[Pasted image 20240728144718.png]]
![[Pasted image 20240728144746.png]]
terminals gives output .  
```
List<Interger> nums = Arrays.aslist(4,5,6,7)

Stream<Integer> s1 = nums.stream( );   //s1 now has its every value
										//created stream object

s1.forEach( n -> Sout( n ) )
//  s1.forEach( n -> Sout( n ) )            //Error As only Once 


Now we can do 
Stream<Integer> s2 = s1.filter( n -> n%2 ==0 ); 

Stream<Integer> s3 = s2.map( n -> n*2  ); 


// filter returns a new stream with filtered value .

We can also use methods which don't return stream but a output .
int result = s3.reduce( 0 ,(c,e) -> c+e) ;

Sout( result ) ;

It will give  val of type mentioned with the Stream .
```


Now All the above can be done in a single go

```
Int result = nums.stream( )
		    .filter ( n -> n%2==0)
			.map( n -> n*2)
			.reduce ( 0 , (c,e ) -> c+e  );
```

Filter needs an object of predicate which belongs to` java.util.function` and it has a function Boolean Test .
It is a Functional Interface .

That is from filter , if given cond, is true for given value , it will go beyond otherwise stop .
![[Pasted image 20240522055954.png]]
Then it code gets shortened in same way .


**Now map takes obj. of function which is func. Interface which takes two Data type and has method apply .**
![[Pasted image 20240522060313.png]]

Now reduce takes obj. of binaryOperation which extends BiFunction which takes method apply .

Now if we wanted a sorted stream of filtered value .

```
Stream<Integer> sortedValues = nums.stream( )
		    .filter ( n -> n%2==0)
			.sorted( ) ;
```

If we have to do it with multiple threads then use parallelStream
But can't use sort with it , as it will mix value .

```
Stream<Integer> sortedValues = nums.parallelStream( )
		    .filter ( n -> n%2==0)
```


*For Each Accept Karta Hai , Filter Test Karta Hai , Aur Map Apply Karta Hai .*  ---- SAM  --- Java.util. Functions package     --- void -- Boolean -- Integer

##### Local Variable Type Inference --  LVTI


***Used only for local var as instance var are meant to be stored in memory but local are not .***

now for local var , we don't need to specify the data type , we can use Var keyword .
Ex- var b= 8;

	Var is assigned at compile time .
	So when defining , have to assign value to it too.
	Otherwise Compile time error .
	Can't create var as global .
	
		var d= 2 ;
		int nums[] = new int[10] ;
		var nums = new int[10]  ;


##### Sealed Classes -
These are the classes in which it is mentioned , what are the subclasses and Interface that can be inherited from it .

We use the keyword `sealed` and `permits` here 
Ex - If there is a class A , B,C,D and we want only class B & C to inherit A then we can write -- 
```
sealed Class A permits B,C {
}
Final Class B extends A {
}
Final Class C extends A {
}
Class D {
}
```


**Now the class we are permitting must be either sealed , non-sealed or final .**
![[Pasted image 20240728105920.png]]

now we can also make B sealed and let it permit D , So now Class D has to be one of the above three .

We can also make B non-sealed , then there will be no condition on Class extending it to be any of the above .

**So basically it tells only which class can extend it .**

It can with permitting also can extend and implement another class or interface .

Ex -
 Class A extends thread implements Clonable permits B,C {   }
**permits should be the last arg .**


For Interface we have sealed and non-sealed 
```
sealed interface X permits Y{
}
sealed interface Y extends X {
}

```

##### Records --

Data carrier Class -- Class that only holds data  .

Variables which hold data are called *states* .
These data are not there for altering only for transfer , so final .

now if we are making a class Alien to which we send data , lets say name and Id  ,
**The class should have variable final and private . And to access it getter method** and if we want on sout( obj ) , we need its data and not classname and hashcode ,
We have to override hashcode() and toString() Method and and if we want to compare two object using equals on basis of its data , we have to override the equals () .

Using record , we can write it as just ,

record Alien (int id , String name ) {  }

So now it internally implements Constructor , equals and On Printing it we will get the value of the object rather than the object Itself 

In records , Instance variables are known as *Components .*
![[Pasted image 20240522163939.png]]

Here , we made parameterized Constructors . 
Now inside that record class , we can  make  a default constructor which can return a default value and empty string when called .

We can apply chk also in the constructor .
![[Pasted image 20240522165314.png]]



Canonical Constructor -- As the constructor has same parameters as defined in the class .
We can make it **Compact Canonical Constructor** .
![[Pasted image 20240522165256.png]]


All parameters in class are *private and final*  . And this class can't extend any other class because record class extends another class i.e.  JAVA.LANG.RECORD  , but can implement Interface .

Here as there won't be any getter , setter , we can get the name through   obj.name() ;

![[Pasted image 20240728175812.png]]





