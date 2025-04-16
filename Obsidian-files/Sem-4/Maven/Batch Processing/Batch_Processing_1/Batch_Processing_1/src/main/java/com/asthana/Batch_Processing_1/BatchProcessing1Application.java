package com.asthana.Batch_Processing_1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class BatchProcessing1Application {

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(BatchProcessing1Application.class, args)));
    }

    @Bean
    public CommandLineRunner run(JobLauncher jobLauncher, Job job) {
        return args -> {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())            //way to add time
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        };
    }
}
