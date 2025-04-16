package com.asthana.spring_demo_01.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asthana.spring_demo_01.DB;
import com.asthana.spring_demo_01.StudDb;
import com.asthana.spring_demo_01.TeachDb;

@Configuration
public class AppConfig {
    
@Bean
@ConditionalOnProperty( name = "project.mode" , havingValue = "stud")
public DB getStudDbBean(){
    return new StudDb();
    }
    
@Bean
@ConditionalOnProperty( name = "project.mode" , havingValue = "dev")
public DB getTeachDbBean(){
    return new TeachDb();
}

@Bean
public ModelMapper getModelMapperBean(){
  return new ModelMapper() ;  
}

}