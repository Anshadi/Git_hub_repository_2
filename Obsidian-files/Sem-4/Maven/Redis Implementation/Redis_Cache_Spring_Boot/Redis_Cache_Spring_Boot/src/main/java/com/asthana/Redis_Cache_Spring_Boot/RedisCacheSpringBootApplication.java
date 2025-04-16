package com.asthana.Redis_Cache_Spring_Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCacheSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheSpringBootApplication.class, args);
	}

}
