SDLC - Software Development Design Cycle  - Going from the idea to the product from creation to deployment and to maintenance .
![[Pasted image 20240923110638.png]]

High Level Diagram for Book My Show
![[Pasted image 20240916111656.png]]

![[Pasted image 20240916112115.png]]



Requirements - functional - Defines what system should do ,
nonfunctional - Defines how system must perform .

The system should be scalable . Highly available and low latency .

BlackBox View of the system .

Always before making any application , make the requirements for each used - asked in interview .

Identify Entities i.e. names , noun . 
In Interview must have minimalistic design - so only those things that need  .
.
Identify classes then identify there attributes .

Always create a ID  for class while creating it ,  whether it is used or not , it is useful . Like in  DBMS , it is like the primary key for any table .

Requirements  - 

Brainstorming and ideation - 

Iterative Design - UML Diagram (pictorial representation of system's structure and interactions ) Ex - class diagram , sequence diagram .

In Access Modifier - representation in uml
- - --> private 
- '#' --> protected
-  + --> public 

Follows SOLID - 
Single Responsibility Principle - SRP - any class should be focused on it and implement functionality related to itself , not related to any other .



***Creating Id and using Final --***
UUID - Universal   - It has 128 bits value with randomly generated value . Currently used UUID - 4 .

Can create random value using this -
UUID.randomUUID().toString() --from java.util.UUID  - can use this to create id .

We can use final in place of const because final can be given value later on and once it is given value and then changed will give error .  Final can be initialized once .

And that's why we can't give email or name as final .
![[Pasted image 20241009175944.png]]

In PostgreSQL and DBMS - there is also a function to generate the random id . 

We use UUID because of IDOR - Insecure Direct Object Reference 

Unix time or Epoch Time returns how much seconds passed since jan 1 1970.
ex- Date.now() in js or select now() in PostgreSQL . So It is stored in Long .

That's why for ex- we keep the var booking time as long .



