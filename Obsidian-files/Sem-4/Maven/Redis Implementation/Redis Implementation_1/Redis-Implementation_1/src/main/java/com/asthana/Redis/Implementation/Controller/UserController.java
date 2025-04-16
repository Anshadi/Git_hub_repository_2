package com.asthana.Redis.Implementation.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asthana.Redis.Implementation.Entity.Users;
import com.asthana.Redis.Implementation.Repository.UserRepo;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    //get Single User
    @GetMapping("/{userId}")     //taking the user Id from the path
    public Users getUser(@PathVariable("userId") String userId){          //Injecting the UserId data from the path to this String
        return userRepo.findUser(userId);
    }

    //find all Users
    

    //1-   @GetMapping
    //     public Map<Object,Object> getAllUsers(){
    //         return userRepo.findAll();
    //     }

    @GetMapping
    public List<Users> getAllUsers(){
    
    Map<Object,Object> allUsers = userRepo.findAll();

    Collection<Object> values = allUsers.values();

    List<Users> collect = values.stream().map(value -> (Users) value).collect(Collectors.toList());

    return collect;
    
    }
    

    //delete User
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userRepo.deleteUser(userId);
    }
}
