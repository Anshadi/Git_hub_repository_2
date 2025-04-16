import express from "express";
import { Login, Signup } from "../controllers/user.controller.js";
import validateFormData from "../middlewares/validateformdata.js";

const router  = express.Router();
                                            //now we have created a route for signup but we  have to also tell our server to use it , we can do this in index.js by using app.use
router.post("/Signup",validateFormData, Signup);
router.post("/Login", Login);

export default router;