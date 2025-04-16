const Eventemmiter = require('events')    //here importing EventEmiitter class from events (maybe library)
const celebrity = new Eventemmiter();       //here making the object of type Eventemmiter

celebrity.on('success',()=>{                  //here using function on of class 
    console.log("Congrats you are the best")
})

celebrity.emit("success")                   //here using function emit of class 
celebrity.emit("fail")

//get a whole collection of json object
app.get('/',(req,res)=>{
    return res.json(cards);
})

// Get a specific item in a collection by ID
app.get("/cards/:cardId", (req, res) => {
    const cardId = req.params.cardId;
    return res.json(cards[cardId]);
  });


  app.post("/cards", (req, res) => {
    // Get body from the request
    const card = req.body;
  
    // Validate the body
    if (!card.value || !card.suit) {                //ya card value empty ho ya card suit khatam ho gaya ho.
      return res.status(400).json({
        error: 'Missing required card property',
      });
    }
  
    // Update your collection
    cards.push(card);
  
    // Send saved object in the response to verify
    return res.json(card);
  });