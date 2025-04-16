import { User } from "../models/user.model.js";
import bcryptjs from "bcryptjs";

export const Signup = async (req,res) => {
    try {
        const {name,email,password} = req.body ;
        console.log("Request Body",req.body);

        const user = await User.findOne({email});
        if (user) {
            console.log("User already exists:", user); 
            return res.status(400).json({ message: "User already exists!" });
        }

        const hashedPassword = await bcryptjs.hash(await req.body.password, 5);
        console.log("Hashed Password:", hashedPassword); // Log hashed password

        const createdUser = new User({
            name: req.body.name,
            email: req.body.email,
            password: hashedPassword,
        });

        await createdUser.save();
        console.log("User saved:", createdUser); // Log saved user
        res.status(201).json({ message: "User Created Successfully" });
    } catch (error) {
        console.error("Error Occurred:", error.message);
        res.status(500).json({ message: "Internal Server Error Occurred!" });
    }
}

export const Login = async (req,res) => {
    try {
        const {email,password} =req.body ;
        const user =await User.findOne({email});
        const isMatch = await bcryptjs.compare(password,user.password);
        if(!user || !isMatch){
        return res.status(400).json({message:"Invalid Username or Password"});
        }
        else{
            res.status(200).json({message:"Login Succesfully",user:{
                _id:user._id,
                name:user.name,
                email:user.email,
            }});
        }
    } catch (error) {
        console.error("Error Occurred:", error.message);
        res.status(500).json({ message: "Internal Server Error Occurred!" });
    }
}



