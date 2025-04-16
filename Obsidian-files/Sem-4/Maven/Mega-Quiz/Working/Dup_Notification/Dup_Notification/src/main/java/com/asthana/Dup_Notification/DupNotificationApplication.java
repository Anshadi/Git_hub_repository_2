package com.asthana.Dup_Notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// @EnableMongoRepositories(basePackages = "com.asthana.Dup_Notification.Repository")
@EnableAsync
public class DupNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DupNotificationApplication.class, args);
	}

}
