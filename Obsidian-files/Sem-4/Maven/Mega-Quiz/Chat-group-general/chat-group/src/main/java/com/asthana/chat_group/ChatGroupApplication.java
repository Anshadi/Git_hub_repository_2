package com.asthana.chat_group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ChatGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatGroupApplication.class, args);
	}

}
