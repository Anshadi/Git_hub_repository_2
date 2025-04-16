
Now here if we made the object of the class Alien , we  have to maintain it , but if we used Spring , it can give us the object . 

Using getBean(  the  class of which object we want.class ) ;

Infront of that class , we have to write 
##### @component - to indicate the compiler the spring is responsible to create its object .

So all classes whose object we aren't making should have this annotation .

getBean implements Interface **Application Context** ,So we need its object to use this method .

Now the run method ---> implements interface --> ConfigurableApplicationContext --> extends Application Context .

So run is giving us the object of it .


![[Pasted image 20240526072003.png]]

##### @Autowired - when we use this annotation , it checks whether we have object of this class , if yes it uses it .

When we ran spring application , it created two objects one of alien and another of Laptop as we used @Component .
![[Pasted image 20240526121933.png]]


###### Bean Factory -- 
In Video --![[Pasted image 20240526164044.png]]
![[Pasted image 20240526124358.png]]

As car is created from car factory , bean is created bean Factory .
so to use getBean method , we have to create Bean factory .
the class which implements the interface bean factory is xmlBeanFactory .

Now we have to tell which class we mean when say alien , so for that we created xml Configuration file , which we will mention inside the xml Bean Factory but we can't mention it in directly in it , so we will use FileSystemResource , 
but as the above two methods have been depriciated and removed , we use 
-   Class path xml Application Context . ----> extends classes which in the end extend --> Applicatioon Context .



Inside spring.xml file , we mention 
id="alien" -- that is when we say alien we meant
class = "com.asthana.Alien" ;




Inside get Bean , we mention the class we want to instantiate .



We can configure -
- java Configuration 
- Xml based Configuration  ( that we are using here )
- annotation



![[Pasted image 20240526165636.png]]

Bean - any class which has certain variables and every var has setters and getters , it is called bean factory . 

Even if we are making object of Alien class , its const. will still run , because 

All the processing in java is done in JVM and inside Jvm there is a container called Spring container which will have spring beans .

**Spring --> application Context --> makes its object ---> spring.xml file  ---> checks for all the classes as per the bean tag ---> creates their object even if not used .**


It sets the var value in the class to default . 


1. **ApplicationContext Initialization**:

- When an `ApplicationContext` (such as `ClassPathXmlApplicationContext`) is created, it immediately reads the configuration file (`spring.xml`).
2. **Bean Definition Loading**:

- The `ApplicationContext` loads all the bean definitions specified in the `spring.xml` file.
3. **Bean Instantiation**:

- All singleton beans (the default scope) are instantiated immediately. This means that as soon as the `ApplicationContext` is initialized, all beans defined as singletons are created, regardless of whether they are used or not.
4. **Dependency Injection**:

- Spring injects dependencies as specified in the `spring.xml` file. This means if a bean has dependencies on other beans, those dependencies are injected accordingly.
5. **Usage of Beans**:

- After the `ApplicationContext` has been initialized and all singleton beans have been instantiated, you can retrieve and use these beans as needed.


#### Singleton Vs Prototype --


All spring beans are singleton beans as object is created only once , so now if another ref var named obj2 tries to access the var through its object , it will be pointed to same obj as obj1 and hence will also retrieve the value set by obj1 for that var .

![[Pasted image 20240526221723.png]]

*By Default , Spring Follows Singleton Design . i.e spring gives one object , but if we want multiple object inside bean tag scope="prototype " .*

In case of prototype , if you didn't created object , it won't be created .


#### Setter Injection -

Every Variable in class if it is bean <==> Property .

```
Inside spring.xml
<property> name = "age"  , value=10< /property >
```
It will initially set the value of that var age to 10 .

*Runs Code --> Creates Spring Container --> Initializes Object -->  By default calls Setter property of the variables available in them  --> if mentioned in file , sets that value --> otherwise sets default value .*
But that setter method should be created in that class otherwise error .


#### Reference Attribute --

Now if it was variable , we would set a value but if it is another class reference created, then can't set value .
So ,  
```
Inside the class
<property> name = "laptop"  , ref="Laptop"< /property >

The Laptop class should also have created using bean .
```

If we didn't created the bean for the class and let it run it will give me Null Pointer Exception .

Here this property tag is called the **setter injection**.


In case , we don't want to use the default constructor of a class , we want to use the parameterized constructor to set the value of its variable .
we can use the constructor args method in the bean file inside that class.
![[Pasted image 20240601222500.png]]
It is called the **constructor Injection** .

 If the var. assignment is nesccesary , then use constructor injection , if it is optional then use setter injection . 

##### Autowired

- Spring will automatically find and insert the correct object into that  field of our class without us having to do it manually.
- ```
Optional Dependencies: You can make the dependency optional by setting required=false.

@Autowired(required = false) 
private Laptop laptop;



If we made an parent class computer and its object com and implemented that class by desktop and laptop . So inside spring.xml file in bean properties .

If we want 
```
Inside Alien Class --
private Computer com ;

Inside spring.xml file --- If want to execute laptop code
<properties name=com ref=laptop></properties>

if want to execute desktop code --
<properties name=com ref=desktop></properties>

```


![[Pasted image 20240603153709.png]]
Now as both the id and the reference is of the same name then in place of this we can use the autowire = default inside bean properties .
![[Pasted image 20240603153839.png]]

Now the autowire has the two options -

Byname - So as the prop. name of computer interface that is com.  As the class mentioned that is alien is dependent on com and the bean id name is also com . So it finds it . as by id it goes to the laptop class .and executes its code .-

Bytype - Now on using it , it won't be dependent on the prop. name com but would be on the object of the computer .
Now when we execute it , it will find the bean with id = desktop but that's not important what important is the class it mentions which is desktop which implements the Interface computer .  

![[Pasted image 20240603155135.png]]
But in case of this , both the class of id = com and id =desktop  implements computer and hence will give error .  As objects don't have sequence , gives error .
`available: expected single matching bean but found 2: laptop,desktop`


##### Primary -
So to solve this error we can either write primary=true in  bean of that id , which we want to execute .
Or we can write prop. inside bean of alien and in that pass the id name of that bean in the ref type .In this case the autowire = default will be ignored .


