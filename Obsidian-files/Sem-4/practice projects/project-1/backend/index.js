import express from 'express';
import mongoose from 'mongoose';
import bodyParser from 'body-parser'; // Import body-parser
import BookRoute from "./route/book.route.js";
import UserRoute from "./route/user.route.js";
import cors from "cors";

const app = express();

app.use(cors());

app.use(bodyParser.urlencoded({ extended: true })); // Use body-parser to parse URL-encoded data
app.use(bodyParser.json());

const PORT = process.env.PORT || 2000;

const URI = "mongodb+srv://Asthana:Asthana@cluster1.dkwkepc.mongodb.net/db2";

mongoose.connect(URI)
.then(() => {
    console.log("MongoDB connected successfully");
})
.catch(error => {
    console.log("Error connecting to MongoDB:", error);
});

app.use("/books", BookRoute);
app.use("/users", UserRoute);

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
