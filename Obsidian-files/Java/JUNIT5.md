
![[Pasted image 20240523001651.png]]


Jupiter Testing Framework for unit testing

Testing - Done post the development by test Engineer ( TE )

Whereas
unit testing - Done by Software Developer while writing code .

SDLC - Software Development Life Cycle 
- design 
- devlop (write code)
- testing  -- Done by TE

unit - One or more than One class or Method 

Now if we are writing test for a particular class that  has only one method now , it should be written such way that when in future more methods add , works for them also .

We can perform unit testing manually without using the framework .
![[Pasted image 20240522181240.png]]
We had a class calc which had method divide .

In manual , steps ![[Pasted image 20240522181555.png]]
However with Junit , we just have to prepare the env . and give input and expected output . 

**Junit5 doesn't supports backward compatibilities .**

It provides Vintage  Api which helps for the test of Junit4 to work on junit5 .

Project File Should be Independent of test File  .

@ Test --- Used before each method of class which we are using as test in the test folder  
![[Pasted image
20240522230546.png]]

assertEquals( "result we are expecting" "result we are getting") ;

inside the test method -- 
assertEquals( "java" "Java") ;
![[Pasted image 20240522235448.png]]


![[Pasted image 20240523184227.png]]

![[Pasted image 20240523184249.png]]


#### Problems ---
![[Pasted image 20240523205837.png]]


Jupiter Api came after Junit 5 .  which responsible for annotation like @Test .

It only checks for failure of anything written inside it . If we start the test without anything written inside it , we will still get success .

Package name of the test file should be same as the class we are going to test .

Inside we can also write the message which only will be printed when test case fails but has to pass it as the first parameter .
        assertEquals( "TEST FAILED", expected3, result3 );
       
We can also set the precision value till which we want to be checked ---
		assertEquals("Not Came as expected ",125.00, Shape.ComputeCLArea(10), 0.001);

Whenever we create a java project , by default it takes java 1.5 but , maven supports JDK 8 or higher . 

from junit5 , the test class can be public or not . 

 Now the message we were giving , we can also give through Supplier Interface which is functional Interface .
Its benefit is that in case of direct passing message it was being evaluated in both case either passing or fail .
But In this interface it only chks if it fails .

Ex ---
```
@Test

public void ComputeCLAreaTest_Supplier() {

    Supplier<String> messageSupplier = () -> "Not Came as expected";

    assertEquals(, messageSupplier,125.00, Shape.ComputeCLArea(10), 0.001); // Adjust the delta value based on your precision requirements

}

DIDN,T WORK HOWEVER 
```

#WithAssertNotEquals  --  This method will give true when the expected not equal to actual result .

This method will print the result when the test cases fails , that is when the expected and actual is same .

assertrue( ) -- chks wether the given value in it is comming true .
assertfalse() -- does the opposite of above \

same way we can pass the message in above and can use the lambda expression .

![[Pasted image 20240523235704.png]]

assertArrayEquals ( ) --  It matches the whole array elements and their order with the another one and in case of false , it tells on which line it is false  .

For assertEquals ----  we can't pass the arrays to them as it chks the object reference in place of its value .


#### Testing the Exceptions --
To chk whether the particular unit is generating the particular exception or not .

![[Pasted image 20240524090909.png]]

To test the exception 
#assertThrows( )  - It req. two things - one is Exception class and another is executable , It requires a lambda expression which contains the code that throws the exception .

#### To test Performance --

We can use assert timeout , it requires two thing - One is Duration in which we want the code to be executed and another is the expression given through lambda expression as it is implementing functional Interface .

If it happens in the given duration then the test passes otherwise fails .
```
public class PerformanceTest {

    @Test

    public void test()

    {

        int[] unsorted ={5,3,6,1};

        Performance pf = new Performance();
        
        assertTimeout(Duration.ofMillis(28), ()->pf.sorted(unsorted) );

    }

}
```


We can use @beforeEach and @AfterEach whatever method has written below it will execute before and after each test .
Ex- here constructor is called for each test case .
![[Pasted image 20240524144026.png]]

Can also use @BeforeAll and @AfterAll to print something before any test and after all the test has been completed . These should be independent of object and any instances , so we make them static but we can also use it for non-static .
And static also so that those methods can be instantiated before any object creation .



But Now we wanted the test object to be created just once and not everytime the method is called so we can use 
@TestInstance which takes an Enum called TestInstance.LifeCycle.perMethod /.perClass , if not mentioned by default per method so object instance will be created for each method ..

And no need to use static with before and after all as it runs only once and we use static when it is common for many methods .

