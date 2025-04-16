Questions to be asked before starting

1-what basic objects will my application will be using?
2-what is the relationship between the different object types that is 
	-one to one
	-one to many
	-many to many
3-How often will new objects  be added in the database?
4-how often will objects be deleted from the database
5-how often will objects be changed?
6-how often objects will be accessed?
7-How will objects be accessed -by ID , property values , comparisons or other.
8-How will group of object types be accessed - Common Id , Common Property Value or other.

Should avoid document relocation due to Document growth -- Causes Performance Hit on system

Large no. of collections -- Not that Much Performance Hit
Large Collection --Performance Hit

Data Life Cycle --
Always Determine the TTL(Time To Live) For the stored Document. One Way Is Capped Collection

For data -First Accuracy -Then performance



three most important lines to remember --

import mongoose from "mongoose";

const UserSchema = new mongoose. Schema({},{timestamps: true});

export const User =  mongoose.model("User", UserSchema);


