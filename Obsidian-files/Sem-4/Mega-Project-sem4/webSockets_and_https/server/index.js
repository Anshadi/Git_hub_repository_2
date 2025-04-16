//type node index.js to start the server on the designated port
//to make all these files like node_modules and pakage-lock.json and package.json .
//we run npm install express and ws to install the required modules and then npm init -y to create a package.json file


// Import the necessary modules using ES module syntax
import express from "express";
import { WebSocketServer } from "ws";

// Create an Express app
const app = express();
const port = 8080;

// Start the Express server
const server = app.listen(port, () => {
    console.log("Server is listening on port " + port);
});

// Create a WebSocket server using the existing HTTP server created by Express

const wss = new WebSocketServer({ server });     

//here the web socket server created will run on the same port as the https server created named server 

wss.on("connection", (ws) => {          //here ws means - for a particular client web socket connection
    console.log("Client connected");
    
    // Define a callback function to handle incoming messages
    ws.on("message", (data) => {                                //used to recieve data from client
        console.log("data came from client: " + data);
        ws.send("Thanks brother: " );                    //used to send response to client
});

    // Define a callback function to handle the client disconnecting
});









//additional info 
/* just like http we use http for url , in web socket we use ws for url */
//here we are using hoppscotch to test the web socket server

//just like to establish connection with http we have fetch api which is natively available in frontend ,for web socket we have WebSocket browser API ,which can we use from client end