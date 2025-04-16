package com.asthana.jwt_authentication.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@EnableMethodSecurity
public class HelloController {
    

    @GetMapping("/Hello")
    public String sayHello() {
        return "Hey";
    }
    @PreAuthorize("hasRole('USER')")            //blocking untill authorized to access 
    @GetMapping("/user")
    public String sayHelloUser() {
        return "Hey User";
    }
    
    @PreAuthorize("hasRole('ADMIN')")            //blocking untill authorized to access
    @GetMapping("/admin")
    public String sayHelloAdmin() {
        return "Hey Admin";
    }
    
}
