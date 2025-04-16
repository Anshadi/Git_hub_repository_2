package com.asthana.Banking_proj_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accountdto {

     private Long Id ;
    private String accountHolderName ;
    private double Balance ;

}
