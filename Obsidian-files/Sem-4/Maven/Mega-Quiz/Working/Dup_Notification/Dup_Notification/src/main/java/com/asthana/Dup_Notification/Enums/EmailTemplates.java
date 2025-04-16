package com.asthana.Dup_Notification.Enums;

import lombok.Getter;

//here we dont have to mention the type of the payment and order confirmation , as they are constants , so they will already be of type emailTemplates .


public enum EmailTemplates {

    PAYMEMENT_CONFIRMATION("payment-confirmation.html","Payment Successfully Processed"),

    ORDER_CONFIRMATION("order-confirmation.html","Order confirmation");
    

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template,String  subject){
        this.template = template;
        this.subject = subject;
    }

    
}

