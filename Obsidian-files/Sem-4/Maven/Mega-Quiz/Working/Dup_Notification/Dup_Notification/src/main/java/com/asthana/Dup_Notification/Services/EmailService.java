package com.asthana.Dup_Notification.Services;

import java.math.BigDecimal;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.asthana.Dup_Notification.Enums.EmailTemplates;
import com.asthana.Dup_Notification.Records.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    private final JavaMailSender MailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void sentPaymentSuceessEmail(

        String destinationEmail ,
        String customerName ,
        BigDecimal amount ,
        String orderReference
        
    )throws MessagingException{
        MimeMessage mimeMessage = MailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name()); // TO Set the properties of the mime message

        messageHelper.setFrom("anshasthana.2004@gmail.com");


        final String templateName = EmailTemplates.PAYMEMENT_CONFIRMATION.getTemplate();

        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference",orderReference);


        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.PAYMEMENT_CONFIRMATION.getSubject());

        try
        {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true); // Set HTML content for the email
    
            messageHelper.setTo(destinationEmail);
            MailSender.send(mimeMessage);
        }
        catch(MessagingException e){

        logger.warn("Warning - Cannot Send Email to {}",destinationEmail);




    }
}

@Async
public void sendOrderConfirmationEmail(

String destinationEmail,
String customerName ,
BigDecimal amount ,
String orderReference,
List<Product> products

)throws MessagingException {
    
    MimeMessage mimeMessage = MailSender.createMimeMessage();
    MimeMessageHelper messageHelper =  new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

    messageHelper.setFrom("anshasthana.2004@gmail.com");

    final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

    Map<String,Object> variables = new HashMap<>();
    variables.put("CustomerName",customerName);
    variables.put("TotalAmount",amount);
    variables.put("OrderReference",orderReference);
    variables.put("Products",products);

    Context context = new Context();
    context.setVariables(variables);
    messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

    try
    {
        String htmlTemplate = templateEngine.process(templateName,context);
        messageHelper.setText(htmlTemplate,true);

        messageHelper.setTo(destinationEmail);
        MailSender.send(mimeMessage);

        logger.info(String.format("INFO - Email Successfully sent to %s with template %s",destinationEmail,templateName));

        
    }
    catch(MessagingException e){
            
        logger.warn("Warning - Cannot Send Email to {}",destinationEmail);

    }


}
}
