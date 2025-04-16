package com.asthana.Task_Schedular_2.Scheduler;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultipleCalls {
    

    @Scheduled(fixedDelay = 5000)
    public void task1() {
        executeTask("task1");
    }
    @Scheduled(fixedDelay = 5000)
    public void task2() {
        executeTask("Task2");
    }
    @Scheduled(fixedDelay = 5000)
    public void task3() {
        executeTask("Task3");
    }
    @Scheduled(fixedDelay = 5000)
    public void task4() {
        executeTask("Task4");
    }
    @Scheduled(fixedDelay = 5000)
    public void task5() {
        executeTask("Task5");
    }
    @Scheduled(fixedDelay = 5000)
    public void task6() {
        executeTask("Task6");
    }
    @Scheduled(fixedDelay = 5000)
    public void task7() {
        executeTask("Task7");
    }
    @Scheduled(fixedDelay = 5000)
    public void task8() {
        executeTask("Task8");
    }
    @Scheduled(fixedDelay = 5000)
    public void task9() {
        executeTask("Task9");
    }
    @Scheduled(fixedDelay = 5000)
    public void task10() {
        executeTask("Task10");
    }



    public void executeTask(String taskName) {
        try{
            log.info("{} started", taskName);
            Thread.sleep(10000);
            log.info("{} completed", taskName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
