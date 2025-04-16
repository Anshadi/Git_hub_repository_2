package com.asthana.chat_group.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.asthana.chat_group.Entity.MessageEntity;
import com.asthana.chat_group.Services.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @MessageMapping("/sendMessage")                     //Recieves sended message
    @SendTo("/topic/messages")                          //Broadcasts
    public MessageEntity sendMessage(@Payload MessageEntity message) {
        chatService.sendMessage(message);                 //sends the message to be processed and storing purpose 
        return message;                             // Returns the message to be sent to subscribers
}


    @GetMapping("/messages")
    public List<MessageEntity> getMethodName(@RequestParam String sender , @RequestParam int page , @RequestParam int size) {
        return chatService.getMessages(sender, page, size);             // Pass pagination parameters to the service
    }
    
}


