package com.asthana.Task_Schedular_1.Schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TaskSched {

    @Scheduled(cron = "*/3 * * * * *")
    public void task_1() {
        System.out.println("Task 1 is running through Cron Jobs at " + LocalDateTime.now());
    }
    
    @Scheduled(fixedRate = 3000)
    public void task_2() {
        System.out.println("Task 2 is running through Fixed Rate at " + LocalDateTime.now());
    }
}
