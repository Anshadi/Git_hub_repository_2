import { Book } from "../models/book.model.js";

export const getBook = async (req,res) =>{      //here we are putting the info from body using request into var getBook 
    try{
        const book = await Book.find();
        res.status(200).json(book);     //giving response using res
    }
    catch(error){
        console.log("Error",error);
        res.status(500).json(error);
    }
}