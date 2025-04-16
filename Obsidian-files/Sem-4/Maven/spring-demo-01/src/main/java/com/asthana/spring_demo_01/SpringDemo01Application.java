package com.asthana.spring_demo_01;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemo01Application implements CommandLineRunner {


	// @Autowired 
	// DB db ;
	public static void main(String[] args) {
		SpringApplication.run(SpringDemo01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// System.out.println(db.getData());

	}

}
