
const validateFormData = (req, res, next) => {
    const {name, email, password} = req.body;
    if(!name ||  !email || !password) return res.status(400).send({message: 'Form data is required'});
    else next();
}

export default validateFormData;