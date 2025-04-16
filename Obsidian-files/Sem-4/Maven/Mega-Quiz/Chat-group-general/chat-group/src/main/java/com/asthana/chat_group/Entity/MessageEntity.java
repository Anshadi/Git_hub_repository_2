package com.asthana.chat_group.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "messages")

@Getter
@Setter
public class MessageEntity {
    
    @Id
    private String id;
    private String sender ;
    private String content ;
    private String timestamp ;
}
