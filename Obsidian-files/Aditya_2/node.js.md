Here if we import fs module 
using const fs= require('fs')
then do the below code then we will be able to render the html file when using https request
![[Screenshot 2023-12-27 000600.png]]
As here we assigning a html named variable the data of file using function fs.readFileSync("html file path","encoding type").

We use response.end() to tell that no further data will be written to it.

Url --it is anything written after the route i.e whatever defined lets say "/"
so anything after that is url request.
![[Screenshot 2023-12-27 005349.png]]
![[Screenshot 2023-12-27 005310.png]]
we can get the object of url using parse method .
in that the path we are following will be in path key
and if we erite anything in url starting with ? then that will be taken as query and it will come in query key of the object.

we can also get the value of key we have put in the request using url.parse.query.key   to gets its value.
for EX-
![[Screenshot 2023-12-27 005705.png]]
![[Screenshot 2023-12-27 005650.png]]It generated a query object.
![[Screenshot 2023-12-27 005728.png]]we can enter multiple keys to it too using & too.
![[Screenshot 2023-12-27 010414.png]]
![[Screenshot 2023-12-27 010202.png]]and can access them using same way.

We will parse the url object using Parse() function it takes two input the url using
request.url and if we have to pass the query then true .
![[Screenshot 2023-12-27 110949.png]]
To Do these things we have to import  https,url and fs.