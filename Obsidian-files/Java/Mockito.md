This is used to create dummy objects by mocking real classes and then use it with junit for the unit testing .
![[Pasted image 20241128200725.png]]
so it instantiates class under test and executes method under test and verifies that it works as expected .
![[Pasted image 20241128201013.png]]

![[Pasted image 20241128201210.png]]

**Test Doubles ---**
It is a generic term where we replace the production object with dummy for testing purpose .
Becoz of sometime there is unavailability of the external dependencies .or if it is network dependable .
![[Pasted image 20241128201622.png]]

**There  are five types of Test Doubles --**
1)  Mock
2) Spy
3) Stub
4) Fake
5) Dummy


These all provide replacement from the external services .
**Fake --**
![[Pasted image 20241128203326.png]]

![[Pasted image 20241128203418.png]]


Example of Fake Test Double - ![[Pasted image 20241128234151.png]]
Here we have created a repo. but while testing created a `fake repo that implements that repo` and used` hashmap` as the inner Database and then using `assert Equals` checked whether they are correct or not .



**Dummy Testing --**
When we are testing the class under test , it may be dependent on many dependency and when we are testing a method , maybe there are dependencies that doesn't play any role .
So for those dependency, we create dummy .
![[Pasted image 20241128234730.png]]

Here We will seeing its implementation , where i have used the same fakebook repository but now for functioning the service , it also needs a email service which has no use in it . So we created a dummy of it .
![[Pasted image 20241130135147.png]]
![[Pasted image 20241130134920.png]]


**Stub Test Double --**
- It provides predefined answers to method executions made during the test.
- It is a hardcoded behavior for a particular test .
- So instead of calling the external service , the stub is called and it returns the expected response .
- It acts as a substitute of the external dependency .

Now for its implementation , lets say that we are going to take all the books and then going to implement some discount percentage on it based on the day when book is published .
![[Pasted image 20241130202051.png]]
![[Pasted image 20241130202008.png]]


**Spy Test Double --**

- It keeps an eye on external dependency and keeps records what interactions are done with the external dependency .
- It is very similar to stubs but it just records info about how they ( Class Under Test ) are executed .
- It is for the behavior verification of the class under test .

![[Pasted image 20241130151334.png]]
![[Pasted image 20241130151327.png]]

It spies so that we come to know that particular method was actually called and what interactions were done .
Here also external dependency is replaced by it .
Because if a method  is called internally in our code but it doesn't return something , there is very less possibilities that we can do the assert .
So we can't even know whether that method is called or not .
Here all the asserts are made at the test level .
![[Pasted image 20241130201740.png]]
![[Pasted image 20241130201837.png]]


**Mock Test Double --**
It works very similar to spy .
It also has the behavior verification capabilities as well as the mock capabilities .
![[Pasted image 20241130183353.png]]

![[Pasted image 20241130183400.png]]

Here our test will call the book Repository mock , Only After it performs some actions . 
- So the only difference between the spy and the mock is that it works exactly the same as the spy -but the behavior verification is done at the mock level here in the Mock Object .

- I.E instead of doing the behavior verification at the test class , we give this responsibility to the repository mock object .
- So it makes our test cases very clean .
- ![[Pasted image 20241130201655.png]]
![[Pasted image 20241130201616.png]]




##### Mocking --

***Now Without doing these things manually , i can do these things with Mockito --***
Now to work with mockito with junit , we have to use that dependency that supports it .

![[Pasted image 20241130220027.png]]
![[Pasted image 20241130220100.png]]

![[Pasted image 20241130220137.png]]


` Mockito Uses Java Reflection to create Mock Objects .`
![[Pasted image 20241130222434.png]]


***Benefits --***
- It saves developers from writing test doubles and mock objects on their own .
- It provides support for the exception handling .
- It has support for stubbing methods . It help us in returning configured response .
- It can check order of method calls and no. of times method being called .
- It provides Annotation support .


***Implementations --***
It provides a static method to create a mock of any class .
so now instead of creating a Fake Repository manually , i can do this 
`BookRepository bookRepository = Mockito.mock(bookRepository.class)`
So Now when this mock class is called we have to tell it what response to give on what action .
using  `.when()` and `.then()`  .

***Fake Test Double Using Mockito--***
![[Pasted image 20241204174621.png]]


***Dummy Test Double Using Mockito--***
![[Pasted image 20241204174707.png]]
***Stub Test Double Using Mockito--***
![[Pasted image 20241204174909.png]]
***Spy Test Double Using Mockito--***
Here instead of using the `mock` method , we call the `Mockito.spy` method  on the class .

There is a difference between `Mockito.mock` and `Mockito.spy` in Mockito that is , when we call the mock method , we create the mock of the external dependency , but when we call the spy method , we create a spy of the real object .
![[Pasted image 20241201224854.png]]
![[Pasted image 20241201225219.png]]

![[Pasted image 20241204174950.png]]


***Mock Test Double Using Mockito--***
Here we are using the inbuilt` Mockito.verify` method of Mockito class instead of making it manually .
In case of fail it will also tell that how many interactions with that method has happened . As it should because it has the behavior verification Capabilities .

We can also use the `Mockito.times()` to check that how many times that method is called on by that particular instance .
![[Pasted image 20241204175014.png]]


###### Mocking With Annotations --

In Junit-5 --

`@Mock` is written above the class of which we want the mock of instead of using the mock method .
But it will give error so to resolve that , we have to use the Mockito extension .

So here above the Test class ,we have to use `ExtendWith(MockitoExtension.class)` to extend the capabilities .

Now we can also use the Annotation `@InjectMock` above Book Service , so it will automatically inject the class that is marked with `@mock` i.e. Book Repository to the Book Service class . 

![[Pasted image 20241205033823.png]]

In Junit-4 --
 Here in place of `@ExtendWith` , we use `RunWith(MockitoJunitRunner.class)` .
 Though the problem with this is that , we can only use one runner per class , so if we are using spring boot that will be a problem .

So another way to do this - 
In Junit 4 - There is a annotation named `@before`  .below which in class , we can use the method `MockitoAnnotations.initMocks(this);` and pass the current instance of the class to do the same work .
![[Pasted image 20241205034151.png]]
There is another way to configure mockito with Junit 4 -
As in Junit 4 , we have been provided Annotation `Rule` which is used to define the rules .
```
@Rule
public MockitoRule mockitoRule = MockitoJUnit.rule();
```
However this Mockito rule have to public otherwise , it wont work .


![[Pasted image 20241205034302.png]]
##### Stubbing ---
The Process of how a given Mock Method should behave is called Stubbing. 
The benefit is the ability to return a  provided response when a specific method is called on the mocked dependency .

Usually 2 ways -
- Using with `when and thenReturn` which are static methods .
- Using with `doReturn and when` which are also static methods .  
	- It means that do return the pre-configured value , when a specific method is called .

Mockito uses the `equals()`  method while matching arguments during stubbing .
Incase the response is not stubbed for a method , default values are returned when called .

![[Pasted image 20241202192025.png]]


The Specialty with Mockito is even if we haven't done the implementations of the methods of the interface Repository but still i can tell what to do during testing  . 

Using When and then -
![[Pasted image 20241205034419.png]]
Using do Return -
![[Pasted image 20241205034453.png]]
![[Pasted image 20241205034956.png]]
There is a syntax difference between when of the do return and of the when i.e. in the do return when -  we only pass the class under test , however in the when and then , we pass the class under test as well as the method which we are going to call of that .

Both when are present in different classes as well as the return type of them are also different.

When method takes a method that we actually want to stub whereas the do-return when takes the mock object and then on that the method is called .


Here in Mockito , i can configure giving multiple value even when calling the same method with same parameters .

```
Mockito.when(bookRepository.findBookByBookId("787"))
				.thenReturn(book3);
				.thenReturns(book2);
				.thenReturns(book1);
```

So it will pass the value of the book sequentially on multiple invocation of the method with this parameter . But here can't give to call multiple parameters to give multiple answers in a single method .
![[Pasted image 20241205035639.png]]

`doNothing()` - This method of Mockito is used when we want to call a method of the mock Object but we want it to perform no operations . This method is used to stub out the void methods .


**In Mockito , we can't use when with then return if the method is void , because it won't return anything .** So can use doNothing() .
![[Pasted image 20241205072737.png]]
So here it compared all the books passed to it using `equals()` i.e. book1 and when got the match then did nothing . Here , it doesn't even chk whether the book id is null or not , what is the price etc .

It can also generate problems .

Now if i made a entity book Request which is similar to book and internally in the add Book method we are saving the book entity , so now if i have to tell Mockito to stub out of that something - we would have to create a book entity in the test itself .
![[Pasted image 20241205074417.png]]But this will create error , becoz when it chks  using equal method the id of the two objects instantiated at the different time will be different .

To resolve this  , we have to write the implementation of the equals method inside the Book Entity , becoz otherwise everytime it will give error as they both objects were instantiated at different time so will give different id .

**It is a Scenario where verifying the saved entity rather than the passed entity becomes critical:**

Here in the book request there is no book id field so while generating the equals method in the book entity, we wont use that field .

And now if i run this test , it will run successfully . And we could now stub out if we want a object of book .

**So to remember is when we are doing the Stubing on the class under test , whatever is passed there should match with the real object passed in the service class , and if have to match the content then we have to implement the equals method .**





##### Behavior Verification ---
Sometimes in unit testing, it becomes very crucial whether a method was called on the mock object or not .
![[Pasted image 20241205075831.png]]

So the `verify()` method helps us to check whether a method is called on the mock class with a particular object .
![[Pasted image 20241205092838.png]]

So as here we have put up a condition on the book price , so no book will be saved so as when we verify , it will tell us that `there were zero interaction with the mock .`
Here the price is null but we can also put the price here something that is less than 1000.

**Verify method also uses the equal method for checking between the objects .**

In verify , we also get the times method which tells  how many times the method under test is called .
So in case we are not able to understand whether the method just returned or completed it execution and then returned , we can use this .

![[Pasted image 20241205213355.png]]

Incase where we don't write the times method inside the verify , by default it is 1.

Incase of times(0) , we can also use the method `never` ,which will ensure that the method is never called.
It returns times with 0 .
In Place of never , we can also use `atLeast()` , `atMost()` , `atMostOnce()` , `atLeastOnce()` .
These all methods are written inside the `verify` method and they ensures that they run that much times .


If we want to check that no interactions were done with the Book Repository after doing operations on the class under test .
So Mockito provides method for that also . We do it by using method `verifyNoInteractions`


![[Pasted image 20241206050800.png]]
Mockito also provides a method `NoMoreInteractions ` which tells us that after a particular interaction no more interactions are made .
As here below find book by id is called but the save method is not called becoz it has returned as its price was low .
It is very imp. in case we have to check whether any more interactions were made with the mock Object or not .
![[Pasted image 20241206052303.png]]

**Here the verify only checks whether it was called or not but it doesn't matter the order in which we have written the verify for the method and the call was made .**

However if we have to chk this in Order , we can use methods provided by mockito like this .
![[Pasted image 20241206053750.png]]
Test failed as save called after the find Book by Id . 


##### Throwing Exceptions---
Here We are going to use methods of Mockito like `when().thenThrows()` and `assertThrows()` .
***-have to post two photos***
However when we are dealing with the mock object , we can't use when and then throws method . So we use here method `doThrow() and .when()` .


##### Capturing Arguments---
Now before the main reason we were using equals method manually written because when we provided book in the service and then to chk in the test , there instance id were different .

But now it can be done easily with the help of the argument capturing .
Here we are going to use the method `ArgumentCaptor() and .forClass()`

**Thing to remember here is that we are not capturing the book Request but the book that is passed to the repository , so we are not calling it on the Book Repository class but on the Book Class .**

![[Pasted image 20241207102709.png]]
Mockito also provides annotation to capture the events .
`@Captor`
![[Pasted image 20241207102830.png]]



##### Spies In Mockito---
![[Pasted image 20241207103109.png]]

Here in addition to the mock methods , it also allows for the calling of the actual methods from the external dependency and it is one of the reason it is used very less in the real world .

![[Pasted image 20241207103329.png]]

***have to continue from around 4:30***




##### Behavior Driven Development ---
![[Pasted image 20241207104008.png]]

Here we can either write our test case in given , when and then style or we can use the annotation `DisplayName()` and can do the same in that .



Though we have asserts , we use `assertsj` provided by `BBD class`  . we use the method `then()` here .
With the above i can use methods such as `isNotNull` and `isEqualTo` .

To Use this , we have to import the dependency , `AssertJ Maven Dependency .`
Remember that we have to use the `then` method of AssertJ i.e. `BDD Assertions` and not of the Mockito  .
![[Pasted image 20241207203540.png]]



***With Behavior Verification BBD Mockito*** -

In place of verify , we can here use `then().should()` , it is present in the BBD Mockito Package .
![[Pasted image 20241207225109.png]]


Now in Exception Handling by BBD Mockito - Instead of `when` and `then Throw` , we have given and Will Throw .
![[Pasted image 20241207225605.png]]


##### Argument Matchers ---
![[Pasted image 20241207225836.png]]

One of the types of Argument Matchers is -
- Type Argument Matchers 

![[Pasted image 20241207230043.png]]

Here instead of the exact id , we can say that if that type of object is asked then send this . We can use `any()` or `any(String.class)` .
![[Pasted image 20241207231928.png]]


**One thing to remember is that , Argument Matchers must be provided for all the arguments and not for one or two .**
Like if a method needs 3 arguments , then for 2 we gave any() and for the 3rd we gave the real argument .
So for this , we can provide the other two arguments inside this method `eq()` .
![[Pasted image 20241207234413.png]]
I mean can do this but can't without eq .

- We can only use the argument Matchers , only when we are stubbing or we are doing behavior Verification .
- We can't use the method for actually calling the method .


**Specific Type Of Argument Matchers --**
![[Pasted image 20241207235235.png]]

![[Pasted image 20241207235245.png]]



**Collection Type Argument Matchers --**
![[Pasted image 20241207235507.png]]
Example of above types -
![[Pasted image 20241207235653.png]]

**String Type of Argument Matchers --**
![[Pasted image 20241207235803.png]]

