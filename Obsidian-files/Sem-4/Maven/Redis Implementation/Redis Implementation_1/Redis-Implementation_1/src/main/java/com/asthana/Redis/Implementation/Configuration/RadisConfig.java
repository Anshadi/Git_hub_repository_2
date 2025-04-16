package com.asthana.Redis.Implementation.Configuration ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RadisConfig {

@Bean
public RedisConnectionFactory ConnectionFactory(){
    return new LettuceConnectionFactory();                  //creates a new lettus connection to each call  to the get connection , not thread safe 
}



@Bean
public RedisTemplate<String, Object> redisTemplate() {               //<key ,value>

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    //Connection Factory
    redisTemplate.setConnectionFactory(ConnectionFactory()) ;          // The redis template needs some configurations like - connectionfactory , key serializer , value serializer etc .

    //Key Serializer
    redisTemplate.setKeySerializer(new StringRedisSerializer() ) ;

    //Value Serializer
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()) ;

    return redisTemplate ;
}


}
