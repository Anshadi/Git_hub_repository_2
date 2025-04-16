Entity -object
Fields of entity -Attribute
SDLC  - Software Development Life Cycle
Functionalities --defining functions
Relationship -- Which function calls which function .How functions are related to each other. 

Entities are interdependent to each other. but some are independent.

Different Paradigms
![[Screenshot 2023-12-14 180653.png]]

In procedural oriented data and behaviour are seperate that is
there is struct and there are diff. functions 

Example--- of Procedural Thinking
![[Screenshot 2023-12-14 184045.png]]

But 
In OOP -- the data includes its behaviour .
We can seperately access the behaviour of the data.

Example of -- object oriented code
![[Screenshot 2023-12-14 185531 1.png]]

Class acts as a blueprint.
Class has two components 
Attribute--Data associated with that entity
Methods--Behaviour that entity can perform.

Objects are instances/example of class.
![[Screenshot 2023-12-21 183349.png]]
here f is formatted string .if there is no f string it would not replace the bracket with its value.
here r is the raw string.

when we add the keyword __init__ in a method it becomes a constructor.

In java
![[Screenshot 2023-12-21 184457.png]]
![[Screenshot 2023-12-21 184558.png]]

In java
In case of public we can access  
object.behaviour
ex--raj.age
i.e we can access the detail of object

But in case of private we can't access the details of object i.e its behaviour .
we can only apply thee method in defined in it and get its output.
If we have to access the private var we can use getter and setter method.

instance variable are var inside  the brackets in method of a class.
instance method if its using instance variable.

class methods are those method which uses var defined in class i.e globally.

In python before using class method we have to write @classmethod --it is called as decorater .

In java 
![[Screenshot 2023-12-21 191557.png]]
![[Screenshot 2023-12-21 191512.png]]

when we don't have constructor in python in a class it automatically creates a constructor and it calls the parent constructor but we have to call the parent class once.

but if we have to access the parent class method we don't need to call the parent class.

when child class inherit parent class both constructor needs to be called in order of child then parent.

![[Screenshot 2023-12-28 180451.png]]![[Screenshot 2023-12-28 181108.png]]![[Screenshot 2023-12-28 181517.png]][[Screenshot 2023-12-28 181958.png]][[Screenshot 2023-12-28 184620.png]][[Screenshot 2023-12-28 185100.png]]

In case of single child multiple parent if we call super.init then it will call only the first class . So we can do class1.init() ,class2.init().

It is called multilevel inheritance
when init self is called first in a constructor then first the child is called then its parent is called when super.init then its grandparent called.

[[Screenshot 2024-02-08 183838.png]]
[[Pasted image 20240208184605.png]]

![[Screenshot 2024-02-08 190322.png]]

any class which has one extract method becomes extract class
![[Screenshot 2024-02-08 192109.png]]