It is java file that processes the request and provide the response in format of  html page in form of response object to the client .

It is contained inside the web container  which is part of JEE framework .

Ex-Apache Tomcat , Jetty , Apache Geronimo

Whenever any file is requested from the server which is not available , then the request is handled by Web containers like  Tomcat which further sends it to Servlets . 


#Deployment_Descriptor -- It is the file in our server in which we mention for what request what file should be called . The name of the file is **Web.xml** .

We can do mapping using annotations .

Client --> Server --> Web Container --> Web.xml --> decides which  servlet --> Servlet  -- > Response -->  Client 

When simple class extends HttpServlet , it  behave like the servlet .

service method takes two object req and res .

We use res.getwriter().println( ) as servlet is sending the data in form of response object .

when we want data from the server use get req . and when want to send to it , use post request , so data doesn't appear on url .

or we can make its object .

![[Pasted image 20240526014848.png]]
![[Pasted image 20240526014923.png]]
![[Pasted image 20240526014941.png]]



Now we can say that we only want to work with get request or only post request , so we can use #doget or #dopost method inplace of #service method . Because user can change the request type using inspect which is not good .

#### Request Dispatcher calling a servlet from a servlet --

To share data between diff. servlets , we use Session Management --



To call a servlet we have 2 options -
- Request Dispatcher 
- Redirect

We make obj of Request dispatcher and forward the req and res .

Whatever we write in the rd works as a route -![[Pasted image 20240526022838.png]]
![[Pasted image 20240526022920.png]]

So the work flow is

Submit --> action=add --> check method --> goes to web.xml --> goes to the Add Servlet --> goes to Request Dispatcher -->  sees sq --> goes to web.xml --> Sq servlet --> prints the data 


Now to do same thing through sq servlet is 
As we are sending req and res through dispatcher to sq , we can send data through req object .

It will take an key and value .

```
In Add Servlet 

req.setAttribute("k",k);

In Sq servlet

int k = (int) req.getAttribute("k");  //as comming in form of object 
k=k*k ;

```




#### HttpServletRequest & Response --- These both are Interfaces

When client sends some data lets say num1 and num2 to add , it not only sends that data but also 
sends its client address and browser info through Object names Request object .

When server sends the response , it does through Response Object maybe sends html tags or data.



#### Request Dispatcher( rd ) and sendRedirect --


when the client send data to servlet using request object , servlet s1 will have req1 and res1 object which it forwards to another servlet  if needed and with that any data s1 has to send to s2 using req1 and now s2 generates data for response res1 and sends res1 directly to client .

But when both servlet are on another website then we will have to use redirect and not rd to inform client because he didn't know because in the end it is getting the res. object res1 to him .
So now req1 and res1 will go back to client from s1 and new req2 and res2 will be generated for s2 .


Now to get the same result -
```

Inside add Servlet
res.sendRedirect("sq?k="+k); //session management   // sending url here

Inside sq Servlet
int k = Integer.parseInt(req.getParameter("k")) ;

```

We use here req.getParameter because the data from s1 goes to client in form of req1 and res1 and now that gets conv. to req2 for servlet s2 .


#### Http Session & Cookies --
Now if i have to pass multiple values to that servlet which sends it to another servlet so we have to maintain the data over session .
Now if we go some other page over the same website , the session will be available for us .

```
Inside Add Servlet 
	HttpSession session = req.getSession() ;   //get hold of session 

session.setAttribute("k", k);        //set data for session

res.sendRedirect("sq");

Inside Sq servlet

HttpSession session = req.getSession();
int k = (int)session.getAttribute("k");

```

We can also remove data from the session using 
session.removeAttribute("k");


We can also use Cookies  ---
whenever we send req to server , it sends the res which contains a cookie or cookies which  we can then send as req to another servlet  too .

It also takes two parameters -- key and value .

Now on sq servlet , we will get all the cookies and then have to determine how to get the cookie we want .

While sending the cookie we have to add string to the value .
```
In Add Servlet
Cookie cookie = new Cookie("k", k+"");

res.addCookie(cookie);

res.sendRedirect("sq");

In Sq Servlet
int k=0;

Cookie[] cookies = req.getCookies();

for(Cookie c: cookies)

{

if(c.getName().equals("k"));

k= Integer.parseInt(c.getValue()) ;

}
```


#### Servlet Config and Server Context ---
Now we can add parameters and their values inside web.xml using context-param .



