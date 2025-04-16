package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Service.KafkaProducerService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/chat")
public class chatController {

@Autowired
private KafkaProducerService kafkaProducerService ;




@GetMapping("/sent/{group-id}")
public ResponseEntity<String> sendingMessage(@PathParam("group-id") String groupId , String Message){
return kafkaProducerService.sendMessage(groupId,Message);
}
}


