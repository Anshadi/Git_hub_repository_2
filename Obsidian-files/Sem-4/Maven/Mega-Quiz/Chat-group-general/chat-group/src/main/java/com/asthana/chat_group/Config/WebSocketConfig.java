package com.asthana.chat_group.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(@SuppressWarnings("null") StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();  // addEndpoint matlab kya path hoga and setAllowedOrigins matlab kon kon isse access kar sakta hai and i am using the browser version of the web socket , so sockjs .
    }

    @Override
    public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry registry) {      // it is used to configure the message broker as the web socket is a bi-dir communication protocol so we need to configure the message broker to handle the messages.
        registry.setApplicationDestinationPrefixes("/app");     //+Message Mapping
        registry.enableSimpleBroker("/topic");         //+Send To
    }
}



