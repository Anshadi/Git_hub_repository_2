// strings
let strVar1 = "hello";
console.log(typeof(strVar1));

let strVar2 = 'hello';
console.log(typeof(strVar2));

let val = 3;
let strVar3 = `hello val is ${val}`  // using `
console.log(typeof(strVar3));
console.log(strVar3);

// numbers
let numVar1 = 23;
console.log(typeof(numVar1));

// boolean
let boolVar = true; // false
console.log(typeof(boolVar));

// array
let arrVar1 = [1,2,3,4,5];
console.log(typeof(arrVar1));
console.log(arrVar1);

let arrVar2 = [1,"hello",true,[1,2],5];
console.log(typeof(arrVar2));
console.log(arrVar2);
console.log(arrVar2[2]);

// json
let jsonObjVar = {
    "username": "abc",
    "password": "123"
};
console.log(typeof(jsonObjVar));

// accesing elements of jsonObjVar
console.log(jsonObjVar.username);
console.log(jsonObjVar['password']);

const ch = 'a';
// ch = 1 // error cant change value of constant

// let if = 20;

// notation is camelCase 

// variable name cannot start with number 
// const 1name 

// variable name can start with _ 
const _name = 20;