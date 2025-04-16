package com.asthana.Task_Schedular_2.Scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling 
// Enables the Spring scheduling mechanism
public class TaskSchedulerConfig implements SchedulingConfigurer {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);                  // Set pool size to handle concurrent tasks
        scheduler.setThreadNamePrefix("Scheduled-Task-");
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());                                  // Register the custom scheduler
    }
    
   
    @Bean
    public MultipleCalls multipleCalls() {
        return new MultipleCalls();
    }
}
