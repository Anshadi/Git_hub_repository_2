import { User } from "../models/user.models.js";
import bcryptjs from "bcryptjs";

export const signup = async (req, res) => {
    try {
        const { name, email, password } = req.body;     //D Structure
        const user = await User.findOne({ email });
        if (user)
            return res.status(400).json({ message: "User with email already exists!" });

        const hashedPassword = await bcryptjs.hash(password, 5);

        const createdUser = new User({
            name: name,
            email: email,
            password: hashedPassword,
        });

        await createdUser.save();
        console.log("User created successfully");
        res.status(200).json({ message: "User created successfully" });
    } catch (error) {
        console.error("Error occurred", error.message);
        res.status(500).json({ message: "Internal Server Error" });
    }
};

export const login = async (req,res) => {
    try{
        const {email,password} =req.body;
        const user = await User.find({email});
        const isMatch = bcryptjs.compare(password,user.password);
        
        
        if(!user || !isMatch)
        return res.status(400).json({message:"Invalid Username or passowrd"});
        else{
            res.status(200).json({message:"User created succesfully" , user:{
                _id:user._id,
                name:user.name,
                email:user.email,
            }});
        }

    }
    catch(error){
        console.error("Error occurred",error.message);
        res.status(500).json({message:"Internal Server Error"});
    }
}
