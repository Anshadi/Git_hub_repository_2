//static website

const url =require('url');
const http =require('http');
const fs = require('fs');

http.createServer((req,res)=>{

    const path=req.url

    if(path=='/')
    {
        console.log("Home Page");

        res.writeHead( 200,{
            "content-type" : "text/html",
        });

        const filecontent =fs.readFileSync("./views/home.html")         //created a object named filecontent in which all the data of specified path will be read and stored
        res.write(filecontent);
        res.end();
    }
    else if(path=='/about')
    {
        console.log("About Page");
        
        res.writeHead( 200,{                            //takes two input succesful or not and content type
            "content-type" : "text/html",               //200 says true or successfull
        });

        const filecontent =fs.readFileSync("./views/about.html")

        res.write(filecontent);
        res.end();
    }
    else if(path=='/services')
    {
        console.log("Service Page");
        
        res.writeHead( 200,{
            "content-type" : "text/html",
        });

        const filecontent =fs.readFileSync("./views/services.html")

        res.write(filecontent);
        res.end();
    }
}).listen(8002);