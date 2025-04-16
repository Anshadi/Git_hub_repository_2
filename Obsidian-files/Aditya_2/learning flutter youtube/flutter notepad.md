
Ctrl+shift+P to start flutter project
ctrl+shift+B-- to build projects in different language

inside material View
debugShowCheckedModeBanner:false
to remove debug banner in side


Material App provides many features like to give title and theme and define the home page of our app
 
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Flutter Learn",
      theme: ThemeData(
        primarySwatch: Colors.orange,
      ),
      home: const HomeVeiw(),
    );
  }
}

here home gives us the path to the folder which will be shown as home page for our app.



_(underscore)  -- is used infront of variable names to tell them as private

const - to make the method un-changeable i.e constant variable
ctrl+. -- to find wrapping options 

if we use const we can't use methods inside in it

when we give path to cmd of flutter we have to give path of bin folder but in android studio we have to give path of flutter folder

for android we use gradle ,for ios we use cocoapod or swift package manager abnd for flutter or dart we use pubspec.yaml folder

ctrl. to import libraries after selecting the name

there is 2 types of widget in flutter
Stateless widget - in which there is no change in state
Stateful widget - if we click on a button and its state changes then its stateful widget
inside it there is 2 widget 
child widget- which can have only 1 widget
children widget

shortcut for-----stateless widget ----stle


expect(find.text("Hello World"), findsOneWidget);

it finds the text hello world in our file 
if it fails to find the text  it will fail the test.

if we giving input 20 and 10 and icon cupertino.minus
expect(find.text("10"),findsNWidgets(2));
as it gets two 10 widgets because our one input is also 10
otherwise it will give error test will fail


on using cupertinoicons.divide on 20 And 10
expect(find.text("2.0"),findsOneWidget);
it won,t be 2



we can use to keep items ------In colummn/In rows------ 
return const Column/Rows

to keep items upon each other /means inside each other can use stack
as colummn can have many items it can't have child only children

stack are also used to create multiple sliding pages
	
inplace of material-scaffold
and inplace of child---body


Scaffold Widget provides a basic app structure , including a top app bar,a body area and a bottom navigation bar.
It serves as a skeleton for your App's UI, making it easier to organize and build the different components of your application.

Appbar inside scaffold == header

scaffold converts the black screen on the phone to white screen

Scaffold is the ability to include a drawer, which is a sliding menu 
that can be revealed by swiping from the left or by tapping an icon 
in the app bar.
Inside onpressed 
Scaffold.of(context).openDrawer();

SAFE AREA
if the body is going out of the screen we can wrap the container(or any else) with widget and write safe area
safe area takes child as a parameter
The safe area widget is used to ensure that its child widgets are positiond in the safe area of the device's screen. This area excludes any notches , status bars or system overlays, ensuring that our app content is visible and not obscured by the hardware or system elements.
It helps in designing UIs that are compatible with various devices and screen sizes.


to make data down we can also use appbar inside scaffold
if we don't want to wrap it in bottom only top then give
bottom :false inside safearea
![[Pasted image 20240402092125.png]]



or inplace of bottom false we can set the backgtround colour of our scaffold.
for placeholder we can use hinttext
hintText: "Enter A Number",
      hintStyle:const TextStyle(color: Colors.white)



to chnage theme of our app i9n app.dart we can write

theme: ThemeData(
      primarySwatch: Colors.orange,
      ),

to change outline of border of a textfield and placeholder
TextField(
          decoration: InputDecoration(
            focusedBorder: OutlineInputBorder(		
              borderSide: BorderSide(color: Colors.pink),
            ),
             hintText: "Enter A Number",
	     hintStyle: TextStyle(color: Colors.white)
          
          ),
        ),

now on clicking inside the textarea it will show the border pink and the hint text will be shown in white

to give padding to anything we can wrap them with padding



focusedBorder: OutlineInputBorder(
                borderSide:const BorderSide(color: Colors.black, width: 3.0),
              borderRadius : BorderRadius.circular(10),
              ),
              
              border: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.black , width: 3.0),
              
              borderRadius : BorderRadius.circular(10),
               ),

only focus border would should show border okn clicking on it
but now with border without clicking on it it will still show its border
and now with width on clicking on it the border will show a mota border
border radius circular will show the edge of border circular in place of circular we can do all for every child

inside textfield to limit the input type we can give 
keyboardType: const TextInputType.numberWithOptions(decimal: true),
autofocus: true,

it will only take numeric input and decimals
and autofocous true is given so that if we only hower our cursor on the textfield without tapping on the field the keyboard gets open.

now once the work is done inside a method we can make it clean by wrapping it by extract widget and giving it a name.
now we can duplicate the code inside the method by just copying the widget name.

If you have a column of widgets that should normally fit on the screen, but may overflow and would in such cases need to scroll, consider using a [ListView] as the body of the scaffold. This is also a good choice for the case where your body is a scrollable list.

what it does is add the padding between itself and its child of 32px from all sides
return const Padding(
      padding: EdgeInsets.all(32.0)
for ex if there is a parent container which is a button and it has a child container which is a icon inside then it will a difference of 32px between button and the icon


sized box what basically does is that it add some vertical height between two buttons here 30px but it can also be used for make sure a widget has some specific width and height.
	children: [	
	DisplayOne(),
          SizedBox(
            height:30,
            ),
          DisplayOne(), 
         
        ]  
and 

 SizedBox(
  width: 100,
  height: 50,
  child: Container(color: Colors.blue),
)

sometime the content in the container is small but we want the container to be of more width and height
SizedBox.expand(
  child: Container(color: Colors.red),
)

we can also write 
sizedbox(height:media.height*0.25)	//to give 25% height
width:media.width*0.6  to give 60% of the width


to make the border of any widget circular we can use 
borderRadius : BorderRadius.circular(10),
inside
decoration: InputDecoration()


[OutlineInputBorder], an [InputDecorator] border which draws a rounded rectangle around the input decorator's container.

decoration: InputDecoration(
        focusedBorder: OutlineInputBorder(
          borderSide:const BorderSide(color: Colors.black, width: 3.0),
        borderRadius : BorderRadius.circular(10),
        ) 

	to make buttons we can use this everytime for each button.
	
to make button fit in a box use
wrap the  button with the widget 
fitted box

	Row Creates a horizontal array of children.

 Row(		
	 mainAxisAlignment: MainAxisAlignment.spaceBetween,				
            children: [
              FloatingActionButton(onPressed: () {},
              child: const Icon(Icons.add),
              ),
              FloatingActionButton(onPressed: () {},
              child: const Icon(Icons.add),
              ),
              
              FloatingActionButton(onPressed: () {},
              child: const Icon(Icons.add),
              ),
              
              FloatingActionButton(onPressed: () {},
              child: const Icon(Icons.add),
              )
            ],
there are four buttons up there.
main Axis alignment is used to create space between the buttons.
in it at one place i have used their default add icon as they don't have minus icon so we can use cupertino icons.


to create space between widgets we can use 
const Spacer(),

Spacer(flex:2)
Column(
  children: [
    Text('Item 1'),
    Spacer(),       // Takes default flex: 1
    Text('Item 2'),
    Spacer(flex: 2), // Takes flex: 2, so it gets twice the space as Spacer() above
    Text('Item 3'),
  ],
)
In this example, the space between "Item 1" and "Item 2" is divided equally among any Spacer widgets with the default flex value of 1. 
The space between "Item 2" and "Item 3" is divided in a 1:2 

inside on tap
Navigator.pop(context); used to remove current screen
and when 
 Navigator.push(context,MaterialPageRoute(builder: (context)=> const  UserViewer()));
it is used to open new screen

now convert stateless widget into stateful wideget by clicking on it

whenever we have to show changes in our ui we have to call setstate

buildcontext tells the location of the widget in our woidget tree.

before callinng to build method our app comes to init state

There is two staes init and dispose.
in dispose we can tell which resources we can clean or clear or release.

init state---
Called when this object is inserted into the tree.
The framework will call this method exactly once for each [State] object it creates.

dispose state---
Called when this object is removed from the tree permanently.
The framework calls this method when this [State] object will never build again. After the framework calls [dispose], the [State] object is considered unmounted and the [mounted] property is false. It is an error to call [setState] at this point. This stage of the lifecycle is terminal: there is no way to remount a [State] object that has been disposed.



we have created a App life cycle listner in state class and we have defined it late that is we will define it later.
late final AppLifecycleListener _listener;
later on will define it in innit state
A listener that can be used to listen to changes in the application lifecycle.

inside initstate
 _listener = AppLifecycleListener(
      onShow: _onShow,
      onHide: _onHide,
      onDetach: _onDetach,
      onInactive: _onInactive,
      onPause:_onPause ,
      onResume: _onResume,
      onRestart: _onRestart,
      onStateChange: _onStateChange,
      // onExitRequested: _onExitRequested,
    );


On_Show
A callback that is called when the application is shown.
On mobile platforms, this is usually just before the application replaces another application in the foreground.
On desktop platforms, this is just before the application is shown after being minimized or otherwise made to show at least one view of the application.
On the web, this is just before a window (or tab) is shown.



OnHide
A callback that is called when the application is hidden.
On mobile platforms, this is usually just before the application is replaced by another application in the foreground.
On desktop platforms, this is just before the application is hidden by being minimized or otherwise hiding all views of the application.
On the web, this is just before a window (or tab) is hidden.

OnDetach
A callback that is called when an application has exited, and detached all host views from the engine.
This callback is only called on iOS and Android.

OnInactive
A callback that is called when the application loses input focus.
On mobile platforms, this can be during a phone call or when a system dialog is visible.
On desktop platforms, this is when all views in an application have lost input focus but at least one view of the application is still visible.
On the web, this is when the window (or tab) has lost input focus.

OnPause
A callback that is called when the application is paused.
On mobile platforms, this happens right before the application is replaced by another application.
On desktop platforms and the web, this function is not called.

onResume
A callback that is called when a view in the application gains input focus.
A call to this callback indicates that the application is entering a state where it is visible, active, and accepting user input.

OnRestart
A callback that is called when the application is resumed after being paused.
On mobile platforms, this happens just before this application takes over as the active application.
On desktop platforms and the web, this function is not called.

OnStateChange
Called anytime the state changes, passing the new state.



_onstatechanged function not like other functions takes a app life cycle state and then have to what have to do with it
  void _onStateChange (AppLifecycleState state) => print("On StateChange called with state: $state");
others like
void _onInactive () => print("On Inactive called");


Pump rebuilds the frame that is change in frame  and tester is used to test our programmes and its functins
await tester.pump()		


---------------------------------------------ICE CREAM APPLICATION STARTED---------------------------------------------------------------------------


scrollDirection:Axis.horizontal
so that it can be scrolled in horizontal direction


shrinkWrap:true
it shrinks the givrn object as much as it can
It is used when you want the ListView to occupy only the space required by its content,
 rather than taking up the entire available space.


listveiw is used to return a list of fixed length and now as we have added things to it 
it may not be able to render things properly so we have to 
wrap the listveiw with the container and inside it we can give the width too
return sizedbox(	//container
width:MediaQuery.sizeOf(context).width,



Expanded widget to make sure each button takes an equal portion of the available space.
It allows the buttons to share the available space evenly.
have to use expanded button each time on each button or whatever.\
child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          ElevatedButton(
            onPressed: () {
              // Handle Breakfast button press
            },
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.black, // Set the button color
              foregroundColor: Colors.white, // Set the text color
            ),
            child: const Text(
              "Breakfast",
              style: TextStyle(
                fontSize: 35,
                fontWeight: FontWeight.bold,
                fontStyle: FontStyle.italic,
              ),
            ),
          ),
          ElevatedButton(next button likewise.....)

Elevated Button 
To make text breakfast a button we have used child ElevatedButton and inside it 
has given onpressed to handle what happens when pressed and the styling of the button.

In place of expanded we can also use 
mainAxisAlignment: MainAxisAlignment.spaceEvenly,
using this the buttton will be centered also.

Image.asset expects images to be in the assets folder of your Flutter project. 
To load images from local file paths, you should use the Image.file widget.
import dart:io to handle local file paths.

In case of the file path is provided in same file locally we will use
children: images.map((image) => Image.file(File(image))).toList(),

when giving path 
'c:/Users/adity/OneDrive/Pictures/breakfast image 3.jpg',
we can use / in place of // or can use'//' as 
it considers // it as a escape charachters.



If you want to enable vertical scrolling for the column of images,
We can wrap the Column widget in a SingleChildScrollView and set the scrollDirection property
List<String> images = imagesCategories[selectedCategory] ?? [];
  return SingleChildScrollView(
    scrollDirection: Axis.vertical,
    child: Column(


Question:The "A RenderFlex overflowed by 117 pixels on the bottom" error typically occurs when 
the content within a Column is too large to fit within the available vertical space.

solution:
Wrap Column in a ListView:
Wrap your Column with a ListView to allow it to scroll. This way, 
if the content is too large to fit within the screen, users can scroll to see the rest.
List<String> images = imagesCategories[selectedCategory] ?? [];
  return SingleChildScrollView(
    scrollDirection: Axis.vertical,
    child: ListView(
      shrinkWrap: true,
      children: images
          .map(

child: Image.file(File(image), fit: BoxFit.cover),
Boxfit tells How a box should be inscribed into another box.


stateful or stateless on basis of wether we have to load data on its loading or not

for a widget we can either specify its height or can use expanded so that it can remaining available spaces

Expanded is usually used inside a Flex widget, such as Column or Row. However, when Expanded is 
directly inside a Column without any Flex parent, it might not work as intended.
Due to which scrolling is sometimes a issue.

 The NeverScrollableScrollPhysics() is added to the GridView to prevent it from trying to scroll independently.


Creates a scrollable, 2D array of widgets that are created on demand.
This constructor is appropriate for grid views with a large (or infinite) number of children 
because the builder is called only for those children that are actually visible.
itemBuilder: (context, index) {
                  return Image.file(
                    File(imagesCategories[selectedCategory]![index]),
                    fit: BoxFit.cover,
                  );





can set the height of the container.






GridView.builder is a widget in Flutter that creates a scrollable, 2D array of widgets based on a delegate.
 It's a more efficient way to create a grid of widgets compared to using a GridView with a fixed list of children.
 The GridView.builder constructor takes a gridDelegate and a itemBuilder as parameters.

Here's a brief explanation of the parameters:

gridDelegate: This parameter is responsible for defining the layout of the grid. 
It specifies the number of columns in the grid, the spacing(crossAxisSpacing and mainAxisSpacing) between the items, and how the items should be sized. 
The most commonly used delegate is SliverGridDelegateWithFixedCrossAxisCount, which creates a grid with a fixed number of columns.

itemBuilder: This parameter is a callback function that takes a BuildContext and an index as arguments and returns the widget at the specified index in the grid. The builder is called only for the widgets that are actually visible on the screen, which makes it efficient for large datasets.

Here's an example of how you might use GridView.builder:

GridView.builder(
  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
    crossAxisCount: 2, // Number of columns
    crossAxisSpacing: 8.0, // Spacing between columns
    mainAxisSpacing: 8.0, // Spacing between rows
  ),
  itemCount: 10, // Number of items in the grid
  itemBuilder: (context, index) {
    return YourWidget(index); // Your widget at the specified index
  },
);



Creates a delegate that makes grid layouts with a fixed number of tiles in the cross axis.
gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(


CROSS AXIS AND MAIN AXIS ALIGNMENT

mainAxisAlignment:
Defines how children are aligned along the main axis.
For a Row, it controls the horizontal alignment (e.g., left, center, right).
For a Column, it controls the vertical alignment (e.g., top, center, bottom).

crossAxisAlignment:
The cross axis is perpendicular to the main axis.
Defines how children are aligned along the cross axis.
For a Row, it controls the vertical alignment (e.g., top, center, bottom).
For a Column, it controls the horizontal alignment (e.g., left, center, right).


BoxFit.cover:

In BoxFit.cover, you want the picture to cover the entire frame without leaving any empty spaces.
If the picture is smaller than the frame, it will be scaled up to cover the entire frame, possibly cropping some parts of the picture to fit.
If the picture is larger than the frame, it will be scaled down to fit entirely within the frame, maintaining its aspect ratio.

BoxFit.contain:

In BoxFit.contain, you want the entire picture to fit inside the frame without cropping any part of it.
If the picture is smaller than the frame, it will be displayed as is, without scaling up.
If the picture is larger than the frame, it will be scaled down to fit entirely within the frame, maintaining its aspect ratio.




InkWell in Flutter:
Purpose:

Provides a visual splash or ripple effect in response to user interactions, especially tapping.
Visual Feedback:

Triggers an ink splash effect, creating a circular animation that expands from the point of contact, resembling a ripple.
Usage:

Wraps around interactive widgets (e.g., Container, Text, Image) to capture tap gestures.
Material Design Integration:
Allows customization of properties like splash color, splash shape, etc.


Shadow Around Images
return Container(
    decoration: BoxDecoration(
      boxShadow: [
        BoxShadow(
          color: Colors.grey.withOpacity(0.5),
          spreadRadius: 5,
          blurRadius: 7,
          offset: const Offset(0, 3),
        ),
      ],
    ),


OFFSET(hor. dir , ver. dir)
Creats a sense of depth and visual interest.
The offset essentially determines the direction and distance of the shadow, adding a realistic touch to the visual representation.

setter can't be used on final type variable.



It allows us to create responsive layouts or widgets that adapt to the size of the screen.
var media = MediaQuery.of(context).size;
Container(
  width: media.width * 0.8, // 80% of screen width
  height: media.height * 0.5, // 50% of screen height
  child: YourWidget(),
)


To allow on clicking  one page button to transfer to another page
where have written the text just write
Navigator.push(context , MediaPageRoute(builder : (context) =>const loginVeiw(),),);


Jitne me input utne baar
TextEditingController txt =TextEditingController();
Now when definig the textField give
controller : txt,


Navigation Slider

Inside scaffold -- Drawer ----
inside drawer---drawerheader ,list tile-----
drawer header---decoration , text to be displayed as navigation heading ex--user profile
List Tiles--The info as shown in slider like a stack ex--My profile, saved videos, edit profile etc.
inside List Tiles----leading(icon) ,title,on tap(){}

ListTile (
                leading: const  Icon(Icons.person),
                title: const Text("My Profile"),
                onTap: () {
                  Navigator.pop(context);
                },
               ),

Obscure Text
for password in flutter that is for hidden data
obscuretext : true

decoration:boxdecoration for Box
decoration:inputdecoration for input --inside it can use content padding


minWidth: double.infinity
commonly used in scenarios where you want a widget to take the maximum available width, adapting to the parent widget or screen width.


colorscheme:ColorScheme.light()
represents the color scheme used when the application is in a light mode.

style:Theme.of(context).textTheme.headline5
it is used inside style and themedata
and to modify it 
Theme.of(context).textTheme.headline5.copyWith(fontWeight: FontWeight.bold)

here for opacity of text
.withopacity(0.64)
can be given
color: Theme.of(context).textTheme.bodyText1.color.withOpacity(0.64),



to put icons from starting and last we can use 
leading:icon(icons.home)
trailingicon(icons.home)


Focus.of(context).requestFocus(FocusNode());
if we want to automatically focus on the second input field when the user submits an action on the first one in
multiple input field form , then we can use it .
it is like textediting controller.

// Create FocusNodes for each input field
  final FocusNode _firstFieldFocus = FocusNode();

 // First Input Field
            TextField(
              focusNode: _firstFieldFocus,
              decoration: InputDecoration(labelText: "First Field"),
              onSubmitted: (value) {
                // When the user submits on the first field, move focus to the second field
                _firstFieldFocus.unfocus(); // Remove focus from the first field
                _secondFieldFocus.requestFocus(); // Request focus for the second field
              },

so what we here do is first create a var then use focus node to focus on that and
after work done unfocus that then request for second one.

here also you can give /n for new line in text


Clipping in Flutter refers to the process of restricting the visible area of a widget to a certain shape or path.
1. **ClipRect:**
   - **Application:** Clips child to a rectangle.
   - **Example:** `ClipRect(child: Container())`

2. **ClipOval:**
   - **Application:** Clips child to an oval.
   - **Example:** `ClipOval(child: Container())`
	Example: Useful for creating circular avatars or images.

3. **ClipRRect:**
   - **Application:** Clips child to a rounded rectangle.
   - **Example:** `ClipRRect(borderRadius: BorderRadius.circular(10.0), child: Container())`
	Example: Ideal for creating containers with rounded corners.

4. **ClipPath:**
   - **Application:** Clips child using a custom path.
   - **Example:** `ClipPath(clipper: MyCustomClipper(), child: Container())`
	Example: Used when you need to clip the child based on a specific shape or path.

5. **ClipRRect with BorderRadius.circular:**
   - **Application:** Clips child to a circular shape.
   - **Example:** `ClipRRect(borderRadius: BorderRadius.circular(50.0), // half the size for a circle
	child: Container())`





