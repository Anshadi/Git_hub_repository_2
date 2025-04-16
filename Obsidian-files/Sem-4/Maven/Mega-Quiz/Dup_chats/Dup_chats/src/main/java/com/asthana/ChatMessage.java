package com.asthana;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatMessages")
public class ChatMessage {
    @Id
    private String id;
    private String message;
    private String user;

    // Default constructor
    public ChatMessage() {}

    // Parameterized constructor
    public ChatMessage(String message, String user) {
        this.message = message;
        this.user = user;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
