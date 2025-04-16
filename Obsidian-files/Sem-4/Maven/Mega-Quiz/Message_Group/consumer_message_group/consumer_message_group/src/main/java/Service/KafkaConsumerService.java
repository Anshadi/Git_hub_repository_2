package Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

// Each chat room is a topic and each user is a consumer group

@Service
public class KafkaConsumerService {
    
    @Autowired
    private ConsumerFactory<String, String> consumerFactory;   // Consumer factory makes kafka consumers

    private Map<String, ConcurrentMessageListenerContainer<String, String>> containers = new HashMap<>(); // Containers for containing kafka consumers

    public void subscribeToGroup(String groupId) {
        String topic = "chat-group" + groupId;
        ContainerProperties containerProperties = new ContainerProperties(topic); // for setting the properties of the kafka message listener containers 

        // Create a ConcurrentMessageListenerContainer instead
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties); // Here we are making the object in which we will be doing our actual work

        // Set the message listener for the container
        container.getContainerProperties().setMessageListener(new MessageListener<String, String>() {
            @Override
            public void onMessage( ConsumerRecord<String, String> record) {
                System.out.println("Received message from " + topic + " :" + record.value());
            }
        });


       

        container.setConcurrency(3); // Adjust concurrency as needed




        container.start(); // Starting the group chat room
        containers.put(topic, container); // Add the container to the map of active containers
    }

    // Container to handle subscriptions and unsubscriptions


     // Hume Container to banwana hi padega jisme sabhi users subscribe karke jaaye aur jab unsubscribe kare to wha se hata sake unhe



    public void unsubscribeFromGroup(String groupId) {
        String topic = "chat-group" + groupId;
        ConcurrentMessageListenerContainer<String, String> container = containers.remove(topic);

        if (container != null) {
            container.stop(); // Stop the container if it was found
        } else {
            System.out.println("No active subscription found for topic " + topic);
        }
    }

}


    //Unsubscribe ke liye pehle container banaunga phir usme topic layunga yeh check karne li woh chat room hai ya nahi


    //containers is made to keep track of all group chats and their respective consumers
    // Topic is the name of the chat group  we are currently in
    //container: The listener handling messages for that chat room. The listener is the consumer

