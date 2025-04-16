package com.asthana.Task_Schedular_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskSchedular2Application {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedular2Application.class, args);
	}

}
