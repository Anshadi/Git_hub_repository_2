package com.asthana.Dup_Notification.Services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.asthana.Dup_Notification.Notifications.Notification;
import com.asthana.Dup_Notification.Records.OrderConfirmation;
import com.asthana.Dup_Notification.Records.PaymentConfirmation;
import com.asthana.Dup_Notification.Repository.NotificationRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import static com.asthana.Dup_Notification.Enums.NotificationType.ORDER_CONFIRMATION;
import static com.asthana.Dup_Notification.Enums.NotificationType.PAYMEMENT_CONFIRMATION;
import static java.lang.String.format;


@RequiredArgsConstructor
@Service

public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    private Logger log = LoggerFactory.getLogger(NotificationConsumer.class);




    @KafkaListener(topics = "payment-topic" , groupId = "PaymentGroup")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s",paymentConfirmation));
        repository.save(
            Notification.builder()
                .type(PAYMEMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build()

        );


        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sentPaymentSuceessEmail(paymentConfirmation.customerEmail(), customerName, paymentConfirmation.amount(),   paymentConfirmation.orderReference());
        



    }







    @KafkaListener(topics = "order-topic" , groupId = "OrderGroup")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
    log.info("Consuming the message from order-topic Topic:: %s",orderConfirmation);

    repository.save(
        Notification.builder() // what it does is it will create a new notification object and set the values of the notification object
        .type(ORDER_CONFIRMATION)
        .notificationDate(LocalDateTime.now())
        .orderConfirmation(orderConfirmation)
        .build()
    );


    var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),customerName,orderConfirmation.totalAmount(),   orderConfirmation.orderReference() ,orderConfirmation.products() );
        

}
}
