package com.asthana.JDBCDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.asthana.JDBCDemo.Repo.AlienRepo;
import com.asthana.JDBCDemo.model.Alien;

@SpringBootApplication
public class JdbcDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JdbcDemoApplication.class, args);

		Alien alien1 = context.getBean(Alien.class);
		alien1.setId(101);
		alien1.setName("Alien");
		alien1.setTech("Java");


		AlienRepo repo = context.getBean(AlienRepo.class);
		repo.save(alien1);

			System.out.println(repo.findAll());			//To print all the obj. of repo class
	}

}

