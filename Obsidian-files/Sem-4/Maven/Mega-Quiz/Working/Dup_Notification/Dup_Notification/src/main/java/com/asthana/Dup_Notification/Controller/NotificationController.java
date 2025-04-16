package com.asthana.Dup_Notification.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

import com.asthana.Dup_Notification.Services.NotificationConsumer;
import com.asthana.Dup_Notification.Records.OrderConfirmation;
import com.asthana.Dup_Notification.Records.PaymentConfirmation;

@RestController
@RequestMapping("/api")
 
public class NotificationController {

    private final NotificationConsumer notificationConsumer;

public NotificationController(NotificationConsumer notificationConsumer) {
        this.notificationConsumer = notificationConsumer;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> handlePaymentNotification(@RequestBody PaymentConfirmation paymentConfirmation)  {
        try {
            notificationConsumer.consumePaymentSuccessNotification(paymentConfirmation);
            return ResponseEntity.ok("Payment confirmation processed succesfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Error processing payment confirmation"+e.getMessage());
        }
    }

    @PostMapping("/order")
    public ResponseEntity<String> handleOrderConfirmation(@RequestBody OrderConfirmation orderConfirmation){
        try{
            notificationConsumer.consumeOrderConfirmationNotification(orderConfirmation);
            return ResponseEntity.ok("Order confirmation processed succesfully");
        }catch (MessagingException e){
            return ResponseEntity.status(500).body("Error processing order confirmation"+e.getMessage());
        }
    }
}
