package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.KafkaConsumerService;

@RestController

@RequestMapping("/chat")
public class chatController {

    @Autowired 
    private KafkaConsumerService kafkaConsumerService;
    
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToGroup(@RequestParam("groupId") String groupId)
    {
        kafkaConsumerService.subscribeToGroup(groupId);
        return ResponseEntity.ok("Subscribed to group: " + groupId);
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribeFromGroup(@RequestParam("groupId") String groupId)
    {
        kafkaConsumerService.unsubscribeFromGroup(groupId);
        return ResponseEntity.ok("Unsubscribed from group: " + groupId);
    }
}
