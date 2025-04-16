package com.asthana.chat_group.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.asthana.chat_group.Entity.MessageEntity;
import com.asthana.chat_group.Repository.ChatRepository;


@Service
public class ChatService {


    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private KafkaTemplate <String , MessageEntity> kafkaTemplate;         //kafka template essential to send message through kafka producer . -- provides method like send() to send message to kafka topic
 
    private final String TOPIC = "chat-messages";


    public void sendMessage(MessageEntity message) {
        kafkaTemplate.send(TOPIC, message);         
        // chatRepository.save(message);  //  save to mongodb -- only saving when kafka has processed the message so that if any network error comes , it doesn't saves message 
    }



     //Instead of bringing whole data , we can define a page using pageable and define its size so that much data i can bring from database and indexing of page in spring boot starts from 0 .

    public List<MessageEntity> getMessages(String sender, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);                 // Pagination settings
        return chatRepository.findBySender(sender, pageable);               // Fetch messages based on sender and pagination
    }
        



    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void consumeMessage(MessageEntity message){
        chatRepository.save(message);                                                   // Saves the message
        listen(message, System.currentTimeMillis());                            //Ideal Way - Call listen() manually
    }
    
    public void listen(@Payload MessageEntity message, @Header("timestamp") long timestamp) {
        System.out.println("Received message: " + message + " at " + timestamp);
    }
    



    
}
