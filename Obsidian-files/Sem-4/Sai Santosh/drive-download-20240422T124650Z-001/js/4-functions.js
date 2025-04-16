// functions
function greet1() {
    console.log('hello');
}
function greet2(name) {
    console.log('hello ' + name);
}
greet1();
greet2();
greet2("PSIT");

// anonymous functions - unnamed functions
// python - lambda (x): print(x)

const anon1 = () => {
    console.log('hello from anon');
}
anon1()

const anon2 = (name) => {
    console.log('hello from anon '+ name);
}
anon2("PSIT")
const named1 = function namedfunc(name) {
    console.log('hello from anon '+ name);
}
named1("PSIT")

let listVar = [1,2,3,4,5]
function cube(x) {
    return x**3
}
outVar1 = listVar.map(cube)
console.log(outVar1)

outVar2 = listVar.map( (x) => { return x**2 })
console.log(outVar2)

function inner_function(){
    console.log('hello'); 
}

function greet3(inner_function) {
    console.log('calling from another function ');
    inner_function();
}

greet3(inner_function);