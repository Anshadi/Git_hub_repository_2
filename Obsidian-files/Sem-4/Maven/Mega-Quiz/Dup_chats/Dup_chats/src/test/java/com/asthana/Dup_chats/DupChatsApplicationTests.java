package com.asthana.Dup_chats;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static org.assertj.core.api.Assertions.assertThat;








@SpringBootTest
class DupChatsApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    void testMongoDBConnection() {
        // MongoDB connection string
        String connectionString = "mongodb+srv://asdf:asdf@cluster0.a7veusq.mongodb.net/notifications?retryWrites=true&w=majority&appName=Cluster0";

        // Create a MongoClient instance with the specified connection string
        MongoClient mongoClient = MongoClients.create(connectionString);
    
        // Create a MongoTemplate instance
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "<dbname>");
        
        // Test MongoDB connection by checking if the MongoTemplate bean is not null
        assertNotNull(mongoTemplate);
        
        // Optionally, check the database name to verify the connection
        String dbName = mongoTemplate.getDb().getName();
        System.out.println("Connected to database: " + dbName);
        
        // Create a collection named "hello"
        mongoTemplate.createCollection("hello");
        System.out.println("Collection 'hello' created!");
    
        // Verify that the collection exists
        boolean collectionExists = mongoTemplate.collectionExists("hello");
        assertTrue(collectionExists, "Collection 'hello' should exist in the database.");
    
        // Check if we can perform a simple operation
        try {
            long count = mongoTemplate.getCollection("hello").countDocuments();
            System.out.println("Number of documents in the 'hello' collection: " + count);
            System.out.println("Connection to MongoDB successful!");
        } catch (Exception e) {
            System.err.println("Connection to MongoDB failed: " + e.getMessage());
        } finally {
            mongoClient.close();  // Close the MongoClient after the operations
        }
    }
    }

    




