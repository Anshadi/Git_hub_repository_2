package com.asthana.Task_Schedular_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskSchedular1Application {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedular1Application.class, args);
	}

}
