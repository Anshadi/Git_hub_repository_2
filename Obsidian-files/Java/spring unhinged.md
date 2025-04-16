IOC Model is responsible for dependency Injection .


In case of Constructor Injection , the object is automatically created without me calling the constructor and the parameter it asks is automatically generated , And the object name is the same as of the class but 1st letter small .
Used Bean for this .\




![[Pasted image 20240611152642.png]]

Here what we do if we consider that i am making two class  implementing same class , so as we want to create its object , we write above the @component but here we will give it a name so that later where we want to create its object we will write @Qualifier("name") ,  So it will create the object for that .


![[Pasted image 20240611163624.png]]


![[Pasted image 20240611163640.png]]


Here not just with the component , we can use it with other likewise annotation like @Service , @Repository etc .

But other ways to do it includes --- 
We can write the autowire = default , it can do it , but inside the bean property if we write any as primary = true , it will override the autowire property .

Now we can do the same by @component and qualifier  Or 

With @Primary over the implemented class , we can also do the same .

We can do the same with the help of @ConditionalOnProperty annotation 
Or
With @Profile Annotation .

#### Field Injection --
It is done with the help of Annotation @Autowired , It directly injects the dependency needed into the  field , above which it is written .


#### Dependency Injection -

Method injection is a way of injecting dependencies into a class by calling a setter method or any other method on the class, which then receives the dependency as a parameter.


Here Autowired is a setter Injection --
![[Pasted image 20240611212352.png]]

The Annotations used here are --
- The `@Autowired` annotation is used for injecting dependencies into methods.
- The `@Qualifier` annotation is used to specify which bean should be injected when there are multiple candidates.
- The `@Primary` annotation is used to mark a bean as the primary candidate for injection when no `@Qualifier` is specified.



It is advised to use Constructor Injection in place  of setter Injection ..


#### ***Bean Scope*** --

There is 6 Scope --

1) Singleton -- Eagerly Initialized
2) Prototype -- New instance each time the bean is requested.
3) Request -- Lazily Initialized with Http request
4) Session -- Lazily Initialized -One instance per HTTP session, created when  session is accessed.
5) Application --One instance per ServletContext, shared across the entire application.
6) WebSocket -- One instance per WebSocket session, created when the session starts.


Sure, let's cover **Singleton** and **Prototype** bean scopes in a similar way:

### **Singleton Scope**

#### **Concept:**
- **Singleton Scope** is the default scope in Spring. It ensures that only **one instance** of the bean is created for the entire Spring container.
- This single instance is shared across the entire application, meaning every time the bean is needed, the same object is returned.
  
#### **Key Points:**
- **Single Instance:** Only one instance per Spring container.
- **Eagerly Initialized:** The bean is created when the Spring context is initialized.
- **Shared Object:** The same object is reused across multiple requests.

#### **Example:**
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MySingletonBean {
    public MySingletonBean() {
        System.out.println("Singleton Bean Instantiated");
    }

    public void doSomething() {
        System.out.println("Doing something in Singleton Bean");
    }
}
```
- **Explanation:** Here, the `MySingletonBean` is marked as a singleton, meaning only one instance of this bean will be created and shared across the application.

#### **Behavior in Practice:**
- When you inject this bean into different classes or request it multiple times, Spring will always provide the same instance. This can be useful for services that maintain state or are stateless and should not be recreated unnecessarily.

### **Prototype Scope**

#### **Concept:**
- **Prototype Scope** creates a new instance of the bean every time it is requested from the Spring container.
- Unlike the singleton scope, each injection or retrieval of the bean results in a new instance, meaning no shared state between instances.

#### **Key Points:**
- **Multiple Instances:** A new instance is created every time the bean is requested.
- **Lazily Initialized:** Beans are only created when requested.
- **Non-Shared Object:** Each object is unique and not reused.

#### **Example:**
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyPrototypeBean {
    public MyPrototypeBean() {
        System.out.println("Prototype Bean Instantiated");
    }

    public void doSomething() {
        System.out.println("Doing something in Prototype Bean");
    }
}
```
- **Explanation:** Here, the `MyPrototypeBean` is marked as prototype, meaning a new instance will be created every time it is requested.

#### **Behavior in Practice:**
- When injected into another bean or requested from the container, each call to `MyPrototypeBean` results in a new instance. This is ideal for scenarios where each client needs a fresh state.

---

### **Comparison Between Singleton and Prototype Scopes:**

- **Instantiation:**
  - **Singleton:** Instantiated once when the Spring context is initialized.
  - **Prototype:** Instantiated every time the bean is requested.

- **Usage:**
  - **Singleton:** Use when a shared, consistent state or stateless service is needed.
  - **Prototype:** Use when a new, independent instance is required for each request or client.

- **Lifecycle Management:**
  - **Singleton:** Spring manages the entire lifecycle (creation, initialization, destruction).
  - **Prototype:** Spring only manages the creation and initialization; destruction must be handled manually.

---

### **Example Scenario:**

Imagine you have a main class that requires an instance of two other classes, A and B:

- **Class A:** Scoped as `Singleton`.
- **Class B:** Scoped as `Prototype`.

```java
@Component
@Scope("singleton")
public class ClassA {
    public void performTask() {
        System.out.println("Task performed by ClassA");
    }
}

@Component
@Scope("prototype")
public class ClassB {
    
    @Autowired
    private ClassA classA;
    
    public void execute() {
        classA.performTask();
        System.out.println("Task executed by ClassB");
    }
}
```

#### **How it Works:**
1. **Initialization:**
   - When the application starts, an instance of `ClassA` is created (singleton).
   - No instance of `ClassB` is created yet (prototype).

2. **Usage:**
   - When `ClassB` is requested, a new instance is created. Since `ClassB` depends on `ClassA`, the existing `ClassA` instance (singleton) is injected into `ClassB`.
   - If another `ClassB` instance is requested, a new instance of `ClassB` is created, but it still shares the same `ClassA` instance.

3. **Subsequent Requests:**
   - Each time `ClassB` is requested, a fresh instance is created.
   - However, each `ClassB` instance uses the same `ClassA` singleton instance.

This pattern ensures that you get a fresh instance of `ClassB` every time, but the heavy or stateful `ClassA` instance is reused, avoiding unnecessary overhead.





##### Request -- 
Here , New Object is created for each http request i.e for all the classes marked as request and if a object req. 4 times , we will still use same object.

Lazily Initialized

For Ex- If i create a class which needs object of two class A And B , class A is marked as scope("Request") where as class B as prototype . And class B also needs object of class A then 
- Goes to Const. of main class -- runs it .
- Now solves dependencies
- sees that scope marked protype and request and moves on 
- Now when https request called then goes to A , creates object as request and then does its function 
- then goes to B creates the object and now it also needs object of A 
- but it will use the object of A already created and storing some info in it due to the function done in Class A .
- Procedure Done .
- Now again Http request called .
- It will again create new object of A and B.


Now But it will create error if Our Main class is Singleton , because then when spring container will run , it will create the object of Main Class and then will try to solve the dependency but the class A is mentioned as Request , So it can't be created at that time , Only at the time of request . So bean Creation fails . As at the time of its creation , there is no http req. to bound to . 

So To Resolve this we create a proxy object at the time of eager initialization , as a dummy node but when we create the real object when https req. , we bind it to the real object .

ProxyMode = ScopedProxyMode.TARGET_CLASS 
![[Pasted image 20240611222435.png]]


##### Session --
- Object created for each HTTP session 
- Lazily Initialized 
- When user accesses the endpoint Session is created .
- Remains active , till Http session ends 


We can configure expire time in application properties . Or if the session is running through default time , we can end it by 

HttpSession session = request.getSession();
session.invalidate() 

Now if api called , now will create new object .


##### Application --

- Shared across the entire web application.
- Single instance for the entire application lifecycle.
- Initialized when the web application context is created.
- Remains active until the application is shut down.


- **Use Case:** Ideal for global configurations, caches, or shared resources.
- **Initialization:** Eagerly initialized when the application context is created, unless specified otherwise.
- **Access:** Available to all components within the application, regardless of HTTP session or request.
- **Thread Safety:** Should be thread-safe as it can be accessed by multiple threads concurrently.\

As singleton creates one object per IOC , Application creates one Object for multiple IOCs .
![[Pasted image 20240815150519.png]]![[Pasted image 20240815150549.png]]

##### WebSocket --
- Unique instance for each WebSocket session.
- Scoped to individual WebSocket connections.
- Initialized when a WebSocket connection is established.
- Remains active until the WebSocket session is closed.


- **Use Case:** Suitable for managing session-specific data for WebSocket communications, such as user-specific messages or session context.
- **Initialization:** Lazily initialized when a WebSocket connection is established.
- **Access:** Only accessible within the scope of the WebSocket session.
- **Thread Safety:** Typically does not require thread safety as each instance is only used by one WebSocket session at a time.

![[Pasted image 20240815150602.png]]![[Pasted image 20240815150631.png]]

##### Environment --
A Object of type environment is used to access the properties of the application .
![[Pasted image 20240612085712.png]]






##### ***@Lookup --***

Here , if we have used the prototype bean inside the singleton bean , so when the singleton bean will be called , the new instance of prototype bean will be made through Dependency Injection and as singleton bean will be called once , latter we have to work with same prototype bean . As it is the dependency of the singleton bean .

![[Pasted image 20240612154345.png]]

What @Lookup will do is that now whenever that method will be called each time , new instance of it will  be provided .


##### ***@Value --***

It is used to  inject values from the environment or the System properties file into the file or constructor or method or for injecting default value  .

![[Pasted image 20240612155114.png]]

Just when define its variable , write Ex-   @value("$(app.name)") 


##### ***@DependsOn --***

It is used to declare dependencies .

If used It is used to initialize the bean first on which the second bean is depending and then the second bean .
![[Pasted image 20240612160359.png]]


So if i have a bean of scope singleton which depends upon a bean of type session 
then still  the bean instantiation will be failed .
###### Why `@DependsOn` Won’t Work

The `@DependsOn` annotation ensures that certain beans are initialized before others, but it does not change the fundamental lifecycle management of the beans. Therefore, trying to initialize a singleton bean that depends on a session-scoped bean will likely result in a failure during the application startup.

In this scope ,we can either use 
proxyMode
Or
@lookup
Or
@Scope(WebApplicationContext.SCOPE_SESSION)



##### ***@Lazy --***


Can be used with class - level , method and Dependency injection .

![[Pasted image 20240612161200.png]]

By default all singleton beans are initialized when application context runs , but if we want it to be initialized when requested , we can use the @Lazy annotation .


##### ***@Profile***---

Here beans are created only when that profile is active otherwise its beans are not created and .


Can active the profile through 
- Configuration file   
- Command Line   
- System Property 
- Inside our code

Ex- Incase where Inactive profile bean is tried to initialized and give NoSuchBeanDefinitionException .
![[Pasted image 20240612162325.png]]
![[Pasted image 20240612162259.png]]




### Spring Boot Annotation --
Applied above the main class

#### @SpringBootApplication --

It contains 3 Annotations -
@EnableAutoConfiguration , @Configuration , @ComponentScan 

##### ***@Configuration***--

It indicates that class contains spring bean configurations . Can be used in the application class or another classes .


##### ***@EnableAutoConfiguration***--

Automatically configure the application based on the dependencies we have added to the project . 

##### ***@ComponentScan***--

- This annotation tells Spring to scan the current package (and all its sub-packages) for components (like `@Component`, `@Service`, `@Repository`, `@Controller`).

- Without `@SpringBootApplication`, you would typically use `@ComponentScan` to specify the base packages to scan for components. However, `@SpringBootApplication` implicitly includes this annotation and scans the package where the application class is located.


#### Spring Web Mvc Annotation --


- `@Controller`: Marks a class as a Spring MVC controller.
```
@Controller
public class MyController {
    // Controller methods here
}
```

- `@RequestMapping`: Maps web requests to handler methods.
```
@Controller
public class MyController {
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}

```

- `@RestController`: Specialized version of `@Controller` for RESTful services.
```
@RestController
public class MyRestController {
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello, World!";
    }
}
```
- `@ResponseBody`: Binds method return value to HTTP response body.

	- In a Spring MVC application, when you annotate a method with `@ResponseBody`, it tells Spring that the return value of this method should be directly written to the HTTP response body. It's particularly useful when you want to return raw data such as JSON, XML, or even plain text directly to the client, bypassing any view rendering mechanism.
	
```
@RestController
public class ProductController {

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
```

In this example, when a GET request is made to `/products`, Spring serializes the list of products returned by `getAllProducts()` method directly into JSON format and sends it as the HTTP response body.

- `@RequestParam`: Extracts query parameters or form data from the request.

	- Needed when we need to  extract data sent via query parameters or form data in an HTTP request.
```
@Controller
public class MyController {
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name) {
        return "Hello, " + name + "!";
    }
}
```
- `@PathVariable`: Extracts values from the URI template. 

	- When we want to extract values directly from the URI itself, rather than as query parameters. `@PathVariable` allows you to capture these values from the URI template and use them in your controller logic.
```
@Controller
public class MyController {
    
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name) {
        return "Hello, " + name + "!";
    }
}
```




##### ***@ConditionalOnProperty***--

It is used suppose in case we have two or more methods with implementing same class , now to in which case , we have to use which one can be done with its help .

What we can do is in our application properties file , we can specify example -
```
project.mode = production
```

And in our laptop system Variable , we can define 
```
project.mode = development 
```

Now above these two methods we can use this annotation .
```
Ex- 
@ConditionalOnProperty( name="project.mode" , value ="production") 

@ConditionalOnProperty( name="project.mode" , value ="development ") 

```

Now what will happen is , as on our laptop , the system var. has more precedence over the application properties , so it will run development method .

But in our user Laptop or anyone else laptop , they won't have this var. in their env. var.  , so in that case the application prop. mode will run and production method will be used  .



![[Pasted image 20240612204040.png]]
![[Pasted image 20240612204022.png]]
![[Pasted image 20240612204113.png]]



##### ***@PathVariable , @PathParam , @RequestBody***--

**Two ways of passing data  - Path Variable And Path Param**

![[Pasted image 20240613093134.png]]

Here when we will go that path and will enter id in the url , then  the get employee function will fetch id from there using @PathVariable and will rename it as EmployeeID which we can use in our function and use it access some specific data and then show it to the User .

Now if we try to access only the employee path , it won't be accessible .


![[Pasted image 20240613094126.png]]Now in this case , 

We are accessing the parameter sortby and limit from the url , if the user doesn't pass it , by default it will contain null .


Request Body -- In place of bringing data one by one , we can bring whole data in form of object , using this .


******


If we make a object Autowired , it can be later on changed . so in place of that we can make the object final and use constructor for it .

![[Pasted image 20240613102002.png]]


##### ***ResponseEntity*** -

When we have not only have to send the specified data type but also the status code , we  use the response entity annotation . 

```
Inside Service ----

List<Questions> ----> Becomes ------> ResponseEntity<List<Questions>>

Ex-  return new ResponseEntity<>(questionService.getAllQuestions , Https.Ok)
```

We can also use try and catch , in case of failure .
![[Pasted image 20240618164645.png]]

So here we are returning an empty Array List .

******

Now if we made multiple table , then there could be need to connect them , then we can use 1-1 or 1-many or many-many relationship .
Known as Mapping Table .


So basically when we create a entity (Table) which needs another Entity as its variable , we have to use Annotations like --

***@ManytoMany***


******

If we have to write query to fetch something from the database , we can use 
***@Query*** . which comes from data-jpa .

It asks for the  query and whether it is native or not .
![[Pasted image 20240618175854.png]]

Here Used " : " when we want to use something which is given as parameter .


******

We have new option named Optional , it means the fetched data may come or not  .
![[Pasted image 20240619155809.png]]


Now we can also use  .get() method when not using the optional , it will provide us the data .
![[Pasted image 20240619180002.png]]


