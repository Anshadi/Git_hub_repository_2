import mongoose from 'mongoose';
const BookSchema = new mongoose.Schema({
name: {
    type: String,
    required:true,
},
price:{
    type:Number,
    default:0,
},
author:{
    type:String,
    required:true,
},
category:{
    type:String,
    required:true,
},
image:{
    type:String,
},
title: {
    type:String,
    required:true,
}
},{timestamps:true});

export const Book = mongoose.model("Book" , BookSchema) ;
