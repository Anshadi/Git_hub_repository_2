console.dir(document.body); //prints the body of the document
console.dir(document.head); //prints the head of the document

//we accessed here html through our js.


document.body.childNodes[3].innerText = "Hello World";

//used to access heading text from here we can see the child Node no.
// containing heading from the child Node no. or guess it by seeing our code.

let heading = document.getElementById("heading1");
console.dir(heading);

//used to access heading text from here we can see the child Node no.

let Class_heading = document.getElementsByClassName("heading2");
console.dir(Class_heading);

