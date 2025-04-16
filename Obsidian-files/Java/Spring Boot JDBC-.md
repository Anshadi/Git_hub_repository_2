- **Data Source** - It is an interface  which provides the connection to the database . it supports Connection pooling which enhances the performance of database operations .
- H2 - It is an in-memory database .

Now as we know , run returns the object of the application object , we are doing this to get the hold on spring container .

Now as above alien class , we have written the @component , so it will be maintained by the bean . Now in place of Alien alien = new Alien() ;
we can write 
Alien alien = context.getBean(Alien.class)  ;

Now By default its type will be singleton , we will write @Scope("Prototype") to make it of type prototype . So now spring will handle obj. creation ,management , deletion . (write above alien class . )



Now in repository , we have all the method which connects to the database .

Above the repo class we have to mention @Repository to indicate to the compiler that it is a repository .



Now for storing our data in database , we need to create object of AlienRepo .
Now to store data in Database , we use Jdbc Template .

`` Just created the ref . the object will be handeled by the spring .
` private JdbcTemplate template ;  `
`Just have to add the @Autowired method above its setter method .`



Now template has method 
.query() --- Mainly used to fetch data from the database  
Or
In case we have to save something , we have 
.update() -- It will takes two parameters  
-  SQL query
-  Parameters .

This update returns the no. of rows updated so we can print that if data is fetched or not .

Now although we have made the sql query but there is no table to insert as mentioned in query .
![[Pasted image 20240603183548.png]]


Now we have to create 2 folders - Schema - for structure( i.e table )  and in case we want the  object to have some default value , we will create a folder named Data.sql in resource folder  .

There is multiple implementation of query , here we are using 
`template.query( sql query ,RowMapper<T>)    --- It returns us the List<T> .`

Now we have to pass the object of the row Mapper . - It helps to give us one row at a time .


	#RowMapper<T> -- It is a  functional Interface .  So have to create a anonymous Inner Class .which has only method 
	mapRow(Resultset rs , Int Numrows) -- which takes two parameters .


So now we will make the object of the alien class which will be empty . So now will put data from the #Resultset rs to it .
![[Pasted image 20240604005116.png]]

Now we can set data to alien obj. through 
a.setId(rs.getInt( "Id" ) ) ;   Here though i have written name but we can also write the column no. too .

Now we can shorten the code using lambda expression
![[Pasted image 20240604010724.png]]


#### Spring MVC Theory ( Model View Theory)--


Before when client sends data to server , it was server resp. to give layout as well as data .
But now we divided the task - 

We have something that accepts the request and sends response - Controller (Before used Servlet )
We have something that will hold the data - Model ( Before used POJO )
We have something that will be view to the client - View ( Before used Jsp)

![[Pasted image 20240604011602.png]]
Now there could be multiple controller for different things so we made multiple servlet where each  servlet handles single request called **Single Action Controller** . 

Now we are provided front controller by spring Mvc which checks the request and sends it to the appropriate controller . It is done acc. to the file created by us .
This front controller is Dispatcher Servlet .




##### Spring Boot Mvc --

As we already have tomcat server dependency embedded i
 ***Without downloading the tomcat server , we can run the app as spring boot application by using command --    `./mvnw spring-boot:run`
To run it on diff. port 
Inside application properties -  ` server.port=8081`***


Request --> Dispatcher Servlet -> Controller

##### ***Creating Controller File -***

we have to write @Controller above the controller class , so we won't need to make the Configuration file for it as it will be handled by the spring , unless we have to add something specific .

Inside controller class , before every method , where we want to show something we have to mention the path , so that when we visit that path then we get that output .
Using - @RequestMapping( " " ) 
![[Pasted image 20240604032828.png]]
Now it will give me error on the local  host but will print the home page requested on the console . It is because it isn't able to call the jsp page . 
So we have to return the name of the jsp page we have to call --
`return "index.jsp " `
and make return type string .


Here the dispatcher Servlet calls the home method . So it is the job of dispatcher Servlet to call the jsp page .


Generally Jsp --->  converts to ---> Servlet -----> runs on -----> Tomcat 

So to convert the jsp to servlet we need the lib tomcat - jasper .

So Home Controller --> at given Path ---> requests the page returned  ---> displays the Jsp page .


##### Accepting User Input --

Now if we are adding two number , we have to make its controller .
We can make that method inside same class only .

Now as we are making the controller , so it should return a page too . so we will make a result.jsp page .

So 

by default ("/ ") --> calls the home() --> returns index.jsp ---> calls add method through action=add ---> goes to controller --> executes Request Mapping at add --> executes method present there ---> shows result.jsp  .
![[Screenshot (97).png]]


Action text Should be same as Request Mapping Text and session text inside " " should be same as the Text on the receiving page . 

Now to give result , we will use the HttpServletRequest  req , whose object will be made automatically by spring through object dependency injection .  Now will calculate the result here . Then we can send the result to result.jsp using two methods either by 
` "result.jsp?num3=" + num3`
or using Http Session .
![[Pasted image 20240604095558.png]]
As we are using jstl format in result.jsp we have to write isELIgnored = false ;

 
##### Spring Mvc Request Param --
Now can do the same thing using request param -

![[Pasted image 20240604100559.png]]Here it will implicitly catch the incoming data in the variable mentioned and spring will implicitly create the object of the Http Session .


##### Spring Mvc Model And View --
Now if we don't want to use session 
then we can use #ModelAndVeiw object . it will contain both data and the view .

```
@RequestMapping("add")

public ModelAndView add(@RequestParam("num1") int i,@RequestParam("num2") int j ){


  ModelAndView mv = new ModelAndView();

  mv.setViewName("result.jsp");

  int num3 = i+j;


  mv.addObject("num3", num3);

  return mv ;

}
```


##### Prefix And Suffix --
Now if we want to hide our path of the Jsp file and its extension or want to make public in another folder we can add prefix and suffix in the application properties .

```
  Inside application properties ---

spring.mvc.view.prefix= /views/

spring.mvc.view.suffix= .jsp
```

Or if we want to make this folder private , we can put those files in #WEB_INF , only way to call these files are through controller .



##### Made A New File Applying The Same Functionality -
![[Pasted image 20240604183545.png]]

#### Model And Model Map --

Model m ;
We  can create the object of Model too , it will be available for the view too .
It has #addattribute() method .
Its return type is String .

ModelMap m;
We have ModelMap too . when we add data too it , it will be added in the map format .
![[Pasted image 20240605100011.png]]

But here we have to take every parameter one-by-one but with the Model Attribute we can directly assign the object here .
We have to use @ModelAtrribute . As we have to give it a different name .

We can use it give name acc. to us too .

(  @ModelAttribute("Data")  Data  d  )  ---  So whatever will come now will be assigned to var d and can be accessed through var Data .
![[Pasted image 20240605101759.png]]
There is no need of Model m above .

#### Model Attribute At Method Level --

Now if we used @Model Attribute above any method , we can pass the data inside it . And before load of any @Request Mapping , it will load the Model Attribute . 
![[Pasted image 20240605103502.png]]


#### Spring Mvc Project ---
Now the same project using Spring Mvc and without Spring Boot . 

In spring boot - 
When request generated ----> automatically goes to --> Dispatcher Servlet  

But In Spring  ---> Have to do Configuration ---> In spring Xml 


