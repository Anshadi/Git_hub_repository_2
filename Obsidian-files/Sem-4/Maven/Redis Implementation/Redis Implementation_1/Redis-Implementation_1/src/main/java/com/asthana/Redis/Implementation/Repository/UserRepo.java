package com.asthana.Redis.Implementation.Repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.asthana.Redis.Implementation.Entity.Users;

@Repository
public class UserRepo {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "USER1";



//save user
public Users save(Users user){
    redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
    return user;
}

//find user
public Users findUser(String userId){
    return (Users) redisTemplate.opsForHash().get(KEY, userId);
}

//findAll
public Map<Object, Object> findAll(){
    return redisTemplate.opsForHash().entries(KEY);
}

//delete user
public void deleteUser(String userId){
    redisTemplate.opsForHash().delete(KEY, userId);
}

}


