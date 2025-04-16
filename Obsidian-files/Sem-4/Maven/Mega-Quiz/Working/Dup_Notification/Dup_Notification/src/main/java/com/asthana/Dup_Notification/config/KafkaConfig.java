package com.asthana.Dup_Notification.config;

import com.asthana.Dup_Notification.Records.PaymentConfirmation;
import com.asthana.Dup_Notification.Records.OrderConfirmation;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    // Configuration for PaymentConfirmation Consumer
    @Bean
    public ConsumerFactory<String, PaymentConfirmation> paymentConfirmationConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092"); // Replace with your Kafka broker address
        props.put("group.id", "PaymentGroup"); // Replace with your group id
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", ErrorHandlingDeserializer.class);
        props.put("spring.deserializer.value.delegate.class", JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*"); // Allow all packages
        props.put("spring.json.value.default.type", PaymentConfirmation.class.getName());

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentConfirmation> paymentConfirmationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentConfirmation> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConfirmationConsumerFactory());
        return factory;
    }

    // Configuration for OrderConfirmation Consumer
    @Bean
    public ConsumerFactory<String, OrderConfirmation> orderConfirmationConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092"); // Replace with your Kafka broker address
        props.put("group.id", "OrderGroup"); // Replace with your group id
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", ErrorHandlingDeserializer.class);
        props.put("spring.deserializer.value.delegate.class", JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*"); // Allow all packages
        props.put("spring.json.value.default.type", OrderConfirmation.class.getName());

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderConfirmation> orderConfirmationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderConfirmation> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConfirmationConsumerFactory());
        return factory;
    }

    // Configuration for PaymentConfirmation Producer
    @Bean
    public ProducerFactory<String, PaymentConfirmation> paymentConfirmationProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Replace with your Kafka broker address
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, PaymentConfirmation> paymentConfirmationKafkaTemplate() {
        return new KafkaTemplate<>(paymentConfirmationProducerFactory());
    }

    // Configuration for OrderConfirmation Producer
    @Bean
    public ProducerFactory<String, OrderConfirmation> orderConfirmationProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Replace with your Kafka broker address
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, OrderConfirmation> orderConfirmationKafkaTemplate() {
        return new KafkaTemplate<>(orderConfirmationProducerFactory());
    }
}
