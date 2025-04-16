Instead of using data type name you can use dynamic while declaring a variable (should not do always) but it helps in case you have stored a string in var and later on you stored a number it won't give error.

while declaring a list if you use don't specify data type it won't give error (but its not a good practice)

`list listname = [];`  	
`list<string> listname = [];``
`
`listname.add("") or .remove("")`` function -
to add something or delete something from the list 

#### Instantiation
Here we are creating a class named user and instantiating a object named userOne .(called object instantiation)
inside user class we are using a constructor (the constructor should always have the same name as the class)
it is used so that we can dynamically pass the value to the function which will be used in it.
inside the definition of constructor the var need not to be called as same the var in  function (they don't relate to each other, they are just there to capture value).
![[Pasted image 20240225100642.png]]
here this. is referring to the instance we have created that is userOne and going to its class finding the class var named username and assigning it the value username that we have captured using constructor.

now we can use inheritance (it is used when we want some user to have access to special feature additional to this in this case we use inheritance) here we used extends keyword.

#### Inheritance
>**now if  we create a function named superuser that has all the functions of function user then we also have to create a constructor as created in the class we are extending to (user) . Here we will use the keyword super.**
![[Pasted image 20240225101936.png]]
**As now when the user will be passing the value it will be going to the user function but now we want it in the extended function superuser so the super keyword will inherit the the whole constructor from its extended class and then pass the value to the constructor of superuser .**

![[Pasted image 20240225102304.png]]
*here we have created userthree which is an object of superuser so it can access the function publish but usertwo can't access the publish function so it will  give error . but user three can access the functions of function user like login i.e 
userthree.login() won't give error.


#### scaffold 
Without using the Scaffold widget in a Flutter app, you would need to manually implement various components and layouts that Scaffold provides by default. This includes things like app bars, body content, floating action buttons, and other common UI elements.

For example, instead of using Scaffold's appBar property to define an app bar, you would need to create a separate AppBar widget and manage its placement within your UI hierarchy. Similarly, for body content, you would need to manage the layout and structure of your main content area without the convenience of Scaffold's body property.
without scaffold it would look like this
![[Pasted image 20240225122532.png]]
and with scafold it would look like this
![[Pasted image 20240225123438.png]]



widgets are just classes
widgets are just classes they start with capital letter.
we can't just directly write anything we should always have properties like child or children.
child is used to nest widgets inside it.

while choosing options or words we can press ctrl+Q to see its further option and other things we can do with it.

ex- we can't do ``fontweight :bold`` but can do  ``''fontweight:FontWeight.bold'' ``
because all fontwieght is built in sdk flutter library .

The 'child' argument should be last in widget constructor invocations.

Inside the `pubspec.yaml` we can change the properties of our file .
here the subproperties are exactly two spaces under the properties name.

the directory you make(for ex -- here we make fonts directory where i will put my google font file ) should be in the same directory you have pubspec.yaml 

stless -- shortcut for creating stateless widget
in stateless widget - the state of the widget can not change with time (i.e. color or data or layout . for ex - a clock it has  to change data with time so we use stateful widget here ) it has to be  final . it can contain data and that data can change after the widget is initialized .


Hot reload only works on stateless widget .
comma in the end means we are going to add a widget after that.

#### @override - 
is written because  it tells that the function defined in it will overlap the functions with same name in its ancestor classes .
![[Pasted image 20240225204053.png]]
so the build function written in the home will work instead of what it inherited from the stateless widget (as it would have built function of its own.)

we can set the image in two ways 
1) network image -- Directly from the internet 
		``child: Image(  image: NetworkImage("ImageUrl"),  ),``
		or we can simply do
		`` image.network()``
1) Asset.Image  -- from our root location
		``child: Image( image : AssetImage("assets/svg-animation-main.jpg")``
		or we can simply do
		`` image.asset()``


Instead of writing every image location in asset dependency in pubspec.yaml . we can do this- 
assets:  
  - assets/
what it will do that it will give access anything inside it .it will not work if it is inside any folder inside assets directory.

For creating Icons

```

body:const Center(  
  child: Icon (  
    Icons.airport_shuttle ,  
    color: Colors.black,  
    size: 50.0 ,  
  )

```

**Raised Button is replaced by Elevated Button .
Flat Button is replaced by Text Button .
Outline Button by Outlined Button**

Here you can not conventionally use colors property for changing the color of button .
we have to use this-![[Pasted image 20240226093508.png]]
or
![[Pasted image 20240226094709.png]]

now we are going to use textbutton also known as flat button .  it doesn't have shadows around it like elevated button.
I am going to use print function so as when we click on the  button it will invoke the message in the console window .![[Pasted image 20240226095236.png]]

here i am putting an icon and writing beside it , 
```
body: Center(  
child:ElevatedButton.icon(onPressed: null , icon: const Icon (Icons.mail), label: const Text("mail me") , color: Colors.amber)
```
we can see here that the color is not working so we will use .stylerfrom 
![[Pasted image 20240226100610.png]]

here i am using icon button which can be pressed and i can use color here
![[Pasted image 20240226101833.png]]

#### Container Widget -
when we create a container it got no children widget inside it , it takes up the whole space of the body .
like this -![[Pasted image 20240226131800.png]]
if there is any child in it then the widget would have only changed that spaced that the child is occupying .for ex-
```
body: Container(  
  color:Colors.blueGrey,  
  child: const Text("hello"),  
),
```
it will only change the space the text hello is containing to bluegrey.

padding(inside space) and marging(outside space) can be added to the container.
can use many methods for them like
``padding: EdgeInsets.symmetric(vertical: 10.00 ,horizontal :30.0 ),
`padding: const EdgeInsets.fromLTRB(10.0,20.0,30.0,40.0),  
`margin: const EdgeInsets.all(30.0)

padding - it is the distance from the text and the container body that is inside the container .
margin - it is the distance of the container from the outside container .

if we only want padding and don't want margin and other things we can use padding widget instead of container .Inside it we can not use the margin and color property .
```
body: const Padding(  
  padding: EdgeInsets.fromLTRB(10.0,20.0,30.0,40.0),  
  child: Text("hello"),  
),
```





#### Row and Column Widget
there are two types of axis i.e.
For Row 
Main Axis - it goes along the row 
Cross Axis - It goes along the column

However in column 
Main Axis - It goes along the column
cross Axis - it goes along the row


Row widget --
it doesn't have the child properties rather it has a children properties of list type of comma separated widgets .
![[Pasted image 20240226135348.png]]

Now if we want all the widget  to be in the center of the row , we can write 
```
mainaxisalignment: MainAxisAalignment.center 
```
others are  .space between  ,   .start (by default) .

we can use cross axis alignment here too . for ex- if we use
```crossaxisalignment : CrossAxisAlignment.stretch ```
it will stretch the height of all the widgets to the space available .
For Ex- ![[Pasted image 20240226153824.png]]
Now if i use CrossAxisAlignment.end it won't  send it to the bottom but it woulld shift it to the end of that row (i.e. first row)

Now For Column Widget --
![[Pasted image 20240226155100.png]]
here .start will shift all the containers to the left side of that column and .end will shift the containers to the extreme right side of that column(here the full width).

Now the thing is that we can use column inside the row and row inside the column .

#### Expanded
if you wrap any container in the expanded widget , it will take the available space in the body. but the size of other container will remain same .
if all containers in different expanded widgets then they divide the space equally.


#### Flex
Now if we use flex widget inside the expanded widget , it will divide the space in ratios i.e if flex1: 3 , flex2 :2 , flex3: 1 , then the flex1 will occupy three times more space than flex3 . i.e. it will divide the space into 6(3+2+1) ratio and then assign it to them .

Now if we want to add any image to it , we can write it to the row widget but this can cause image overflow so we can wrap with the expanded widget . 
![[Pasted image 20240226181305.png]]
we can write flex widget for this image too 

#### Project 1 -- Ninjas Id Cards 
```
letter spacing : 2.0 is used to give spacing between letters inside text style.
```

we can use Circle Avatar Widget to put the small circular avatar . Inside we have to set background image and can decide the radius of the avatar using radius widget .
![[Pasted image 20240227015902.png]]
Now we can use a divider widget() which puts a horizontal line between the widgets used and can set its height and color .
Here the height provided is not the height of divider but the total distance between the two widgets the divider is being used .

### Stateful widget -
When we create a stateful widget , it creates two classes one is stateful class and another is state class .The stateful class (it is there so our current class can inherit all the widgets of the stateful class ) initiates the state class which stores the state of the different widgets  so that it can be used  to change states of diff. widgets .
Now in this second class we can define data and change their state .
```
class Pusher extends StatefulWidget {  
  const Pusher({super.key});  
  
  @override  
  State<Pusher> createState() => _PusherState();  
}  
  
class _PusherState extends State<Pusher> {  
  @override  
  Widget build(BuildContext context) {  
    return const Placeholder();  
  }  
}
```

Remember that inside the string , if we want to put a variable , we put a dollar sign before writing its name . and we can't use this inside something defined as const.

```
floatingActionButton: FloatingActionButton(  
  onPressed: () {  
    setState(() {  
      ninjaLevel += 1;  
    });  
  } ,  
  backgroundColor: Colors.grey ,  
  child: const Icon(Icons.add),  
),
```
 For state change , we can't directly write the change into OnPressed property because though it might the change the value but it won't show the change . So. we have to write it inside the setstate function because when it is executed , it calls for the build widget to build the whole context again.

Now if we have to print something from the list , we can use map method inside children .

```
where---
List<String> quotes = [  
  "Nothing is Easy in life , if you think it is easy your life is going to be hard",  
  "Work Today Enjoy Tomorrow or Enjoy today and In adverse of poverty DIE TOMORROW "  
] ;

use it like this but children expects a list but it is returning a iterable 
So, we have to use .tolist() method.

body: Column(  
  children: quotes.map((quote) {  
    return Text(quote) ;  
  }).toList() ,
```

We can also use arrow function if we are returning  a single value or line in place of return . i.e. 
```children: quotes.map((quote) => Text(quote) ).toList() , ```

### Class Creation  -
when we create a class during initialization we use keyword "late" if we are not giving it value then , to tell it that , though it is null now but by the time it will be accessed it will have non-null value .
Here we are using required keyword to tell it that it is necessary to give that field value .

```
Inside lib made a file named quotes.dart

class Quotes {  
  late String text ;  
  late String author ;  
  
  Quotes( String text ,  String author)  
  {  
    this.text =text ;  
    this.author = author ;  
  }  
}
```

Now we can assign this quotes using many methods -
1) Named Parameters -- The benefit in this is that now we can send the value to the class in any order . 
    Here inside the constructor we have to use required keyword . and curly braces 
    Now in the main class the list type won't be string but of type Quotes .
    Remember that now here the iterable "quote" is an object then when we have to access its properties we will write it in - '${ -- }' .
```
Inside the quotes.dart file 

import 'package:quotes/main.dart';  
  
  class Quotes {  
  late String text;  
  late String author;  
  
  
  Quotes({required this.text, required this.author});  
  
}

Inside main.dart 



body: Column(  
  
  children: quotes.map((quote) => Row(  
      mainAxisAlignment: MainAxisAlignment.center ,  
      children: [  
        Text(  
              '${quote.text} --- ${quote.author}',  
        style: const TextStyle(  
            color: Colors.white ,  
            fontWeight: FontWeight.bold,  
        ),  
        ),  
    ],  
    ) ,  
  ).toList() ,  
),

before Overload

List<Quotes> quotes = [  
Quotes(text:"Nothing is Easy in life , if you think it is easy your life is going to be hard", author:"Aditya"),  
Quotes(text:"Work Today Enjoy Tomorrow or Enjoy today and In adverse of poverty DIE TOMORROW ", author:"Ansh"),  
] ;

```

### Cards -

Now if we want to present some data in card form , we can use Card() widget , 

#### Type Inference -
Inside it we can make widget(function) in which we can pass quote as an object . here though we don't explicitly defined here that the quote is an object of quotes class but  it is clear like In fruits box there will be only different type of fruit only . It is called Type Inference . 

```
List<Quotes> quotes = [  
Quotes(text:"Nothing is Easy in life , if you think it is easy your life is going to be hard", author:"Aditya"),  
Quotes(text:"Work Today Enjoy Tomorrow or Enjoy today and In adverse of poverty DIE TOMORROW ", author:"Ansh"),  
] ;  
  
Widget quoteTemplate(quote)  
{  
  return Card(  
  margin : const EdgeInsets.fromLTRB(16.0, 16.0,16.0,16.0) ,  
  color: Colors.transparent,  
  child : Padding(  
    padding: const EdgeInsets.all(12.0),  
    child: Column(  
      crossAxisAlignment: CrossAxisAlignment.stretch ,  
      children: [  
        Text(  
          quote.text ,  
          style: const TextStyle(  
          fontSize: 18.0 ,  
          fontWeight: FontWeight.bold,  
          color: Colors.black ,  
        ),  
      ),  
        const SizedBox(height: 6.0),  
    Text(  
      quote.author ,  
      style: const TextStyle(  
      fontSize: 10.0 ,  
      color: Colors.black ,  
      ),  
      ),

```

Now another method to do it is by using creating a stateless widget of Card using Extract Widget Method .
By using this method we can later on just use the widget Quote card and pass inside the value without writing the whole code to do the same thing again .

Now we can dynamically give data to a stateless widget but it  shouldn't change with time . So for that we use the keyword final before declaring the variable . 


To create a Rounded Container  . we can write this inside Container 
```
decoration: BoxDecoration ( 
color: Colors.blue, 
borderRadius: BorderRadius.circular(75), 
),
```
can't provide both color and decoration separately . so provide the color inside the decoration .


The difference in the use of image.asset and AssetImage is its context of usage.![[Pasted image 20240404143603.png]]
