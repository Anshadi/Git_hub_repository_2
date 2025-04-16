import express from "express"
import { getBook } from "../controllers/book.controller.js";

const router = express.Router();

router.get("/", getBook);       //whenever we request get on this "/" path we will get the getBook function

export default router ;

