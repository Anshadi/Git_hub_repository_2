package com.asthana.jwt_authentication.Controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.security.auth.message.AuthException;

import javax.naming.AuthenticationException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticationUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication ;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            
        } catch (AuthenticationException e) {

        }
    }
    
}
