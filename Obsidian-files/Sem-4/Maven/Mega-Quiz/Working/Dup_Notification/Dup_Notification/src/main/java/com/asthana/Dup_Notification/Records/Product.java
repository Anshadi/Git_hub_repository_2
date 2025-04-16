package com.asthana.Dup_Notification.Records;

import java.math.BigDecimal;

public record Product (

    Integer productId ,

    String name ,

    String description ,

    BigDecimal price ,

    Integer quantity 
){

} 


