const loginfrom = document.getElementById(loginform_container);


loginfrom.submit(e){
    console.log("Hello from Event listner",e);
    e.preventDefault(); // this will prevent the page from reloading 
})