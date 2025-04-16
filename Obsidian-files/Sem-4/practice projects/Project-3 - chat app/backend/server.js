const express = require("express");
const { chats } = require("./data/data.js");
const dotenv = require("dotenv");


dotenv.config();

const app = express();

app.get("/", (req, res) => {
  res.send("Api is Running");
});

app.get("/api/chat", (req, res) => {
  res.send(chats);
});

app.get("/api/chat/:id", (req, res) => {
  //gave a single variiable name id
  //it means on giving id of an object on route from webpage we will get value in console
  const Singlechat = chats.find((c) => c._id === req.params.id);
  res.send(Singlechat);
});

const PORT = process.env.PORT || 5000 ;

app.listen(PORT, console.log(`server is running on port ${PORT}`));

