package com.asthana.spring_demo_01.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studentdto {

    private Long Id;

    private String Name;

    private LocalDate dateOfJoining ;

    Boolean isActive ;

    
}
