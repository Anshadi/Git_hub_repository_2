import express from 'express';
import mongoose from 'mongoose';
import cors from 'cors';
import bodyParser from 'body-parser';
import UserRoute from  './routes/user.routes.js';

const app = express();


app.use(cors());

app.use(bodyParser.urlencoded({ extended:true}));       //to parse url encoded data
app.use(bodyParser.json());

const port = process.env.port || 4000;

const URI = "mongodb+srv://Asthana:Asthana@cluster1.dkwkepc.mongodb.net/db3";

mongoose.connect(URI)
.then(() => {
console.log("Mongodb is connected");
})
.catch((error) => {
    console.log("Databse connection error",error);
});

app.use("/user",UserRoute);

app.listen(port,() => {
    console.log(`Server is running on port:${port}`)
});
