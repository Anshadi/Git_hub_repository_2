console.log(`5 > 3 ${5 > 3}`);
console.log(`5 < 3 ${5 < 3}`);
console.log(`5 >= 3 ${5 >= 3}`);
console.log(`5 <= 3 ${5 <= 3}`);
console.log(`5 === 3 ${5 === 3}`);
console.log(`5 !== 3 ${5 !== 3}`);

// == vs ===
console.log(`2 == '2' ${2 == '2'}`); // == makes implicit conversion of datatypes
console.log(`2 === '2' ${2 === '2'}`); // we want this

// logical operators
console.log(`true && (5 > 3) ${true && (5 > 3)}`); 
console.log(`true || false ${true || false}`);
console.log(`!false ${!false}`);

color = "orange"
if (color === "red"){
    console.log("its red")
}
else if (color === "blue"){
    console.log("its blue")
}
else {
    console.log("its unknown")
}

// preferred 
if (color !== "blue"){
    console.log("its not blue")
}

let arrVar1 = [1,2,3,4,5];
for (idx in arrVar1){
    console.log(arrVar1[idx]);
}



